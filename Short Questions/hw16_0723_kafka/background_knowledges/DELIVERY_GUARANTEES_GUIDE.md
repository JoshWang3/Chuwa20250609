# Kafka Delivery Guarantees Demo Guide

This guide demonstrates the three message delivery guarantees in Kafka: **At-Most-Once**, **At-Least-Once**, and **Exactly-Once**.

## Overview

### 1. At-Most-Once Delivery
- **Guarantee**: Messages may be **lost** but are **never redelivered**
- **Configuration**:
  - Producer: `acks=1` (leader only), `retries=0`
  - Consumer: `enable.auto.commit=true` (commits before processing)
- **Use Cases**: Metrics, logs, monitoring data where occasional loss is acceptable
- **Risk**: If consumer crashes during processing, message is lost

### 2. At-Least-Once Delivery
- **Guarantee**: Messages are **never lost** but may be **redelivered**
- **Configuration**:
  - Producer: `acks=all` (all replicas), `retries=MAX`
  - Consumer: `enable.auto.commit=false` (manual commit after processing)
- **Use Cases**: Most common pattern - works well with idempotent consumers
- **Risk**: If consumer crashes after processing but before commit, message is reprocessed

### 3. Exactly-Once Delivery
- **Guarantee**: Messages delivered **exactly once** - no loss, no duplication
- **Configuration**:
  - Producer: `enable.idempotence=true`, `transactional.id` set, `acks=all`
  - Consumer: `isolation.level=read_committed`, manual commit
- **Use Cases**: Financial transactions, critical data processing
- **Risk**: Higher latency and overhead

---

## Configuration Comparison

| Setting | At-Most-Once | At-Least-Once | Exactly-Once |
|---------|--------------|---------------|--------------|
| **Producer** |
| acks | `1` | `all` | `all` |
| retries | `0` | `Integer.MAX_VALUE` | `Integer.MAX_VALUE` |
| enable.idempotence | `false` | `false` | `true` |
| transactional.id | - | - | `exactly-once-tx-` |
| **Consumer** |
| enable.auto.commit | `true` | `false` | `false` |
| isolation.level | `read_uncommitted` | `read_uncommitted` | `read_committed` |
| commit strategy | Auto (before processing) | Manual (after processing) | Manual (after processing) |

---

## Testing Instructions

### Prerequisites

1. **Start Kafka cluster**:
   ```bash
   docker-compose up -d
   ```

2. **Start Spring Boot application**:
   ```bash
   mvn spring-boot:run
   ```

3. **Verify topics created**:
   ```bash
   docker exec -it broker-1 kafka-topics \
     --bootstrap-server localhost:29091 \
     --list
   ```
   You should see: `delivery-guarantee-demo`

---

### Test 1: At-Most-Once Delivery

**Scenario**: Send messages that will be consumed with potential loss.

#### Step 1: Send normal messages
```bash
# Send a message
curl -X POST "http://localhost:8088/delivery-guarantee/at-most-once?message=test1"

# Check console output - you should see:
# [AT-MOST-ONCE PRODUCER] Sending message: test1
# [AT-MOST-ONCE CONSUMER] Processing completed
```

#### Step 2: Test message loss
```bash
# Send a message that will cause consumer to crash
curl -X POST "http://localhost:8088/delivery-guarantee/test/at-most-once/crash"

# Check console output:
# [AT-MOST-ONCE CONSUMER] Simulating crash - message lost!
# Message is LOST because offset was already auto-committed
```

#### Step 3: Check stats
```bash
curl http://localhost:8088/delivery-guarantee/stats

# Response shows how many messages each consumer processed
```

**Expected Result**: Messages with "crash-at-most-once" are lost and never reprocessed.

---

### Test 2: At-Least-Once Delivery

**Scenario**: Send messages that are guaranteed to be delivered at least once.

#### Step 1: Send normal messages
```bash
# Send messages
curl -X POST "http://localhost:8088/delivery-guarantee/at-least-once?message=test1"
curl -X POST "http://localhost:8088/delivery-guarantee/at-least-once?message=test2"

# Check console output:
# [AT-LEAST-ONCE PRODUCER] Message sent successfully to partition X, offset Y
# [AT-LEAST-ONCE CONSUMER] Processing completed
# [AT-LEAST-ONCE CONSUMER] Offset committed
```

#### Step 2: Test message redelivery
```bash
# Send a message that will cause consumer to crash before committing
curl -X POST "http://localhost:8088/delivery-guarantee/test/at-least-once/crash"

# Check console output:
# [AT-LEAST-ONCE CONSUMER] Simulating crash before commit
# Message will be REDELIVERED because offset was NOT committed
```

#### Step 3: Observe redelivery
```bash
# Wait a few seconds, then check consumer stats
curl http://localhost:8088/delivery-guarantee/stats

# The consumer will reprocess the message and you'll see duplicate processing
```

**Expected Result**: Messages are redelivered if consumer crashes before committing.

---

### Test 3: Exactly-Once Delivery

**Scenario**: Send messages with exactly-once guarantee using transactions.

#### Step 1: Send single message
```bash
# Send a message transactionally
curl -X POST "http://localhost:8088/delivery-guarantee/exactly-once?key=txn-1&message=important-data"

# Check console output:
# [EXACTLY-ONCE PRODUCER] Message sent transactionally
# [EXACTLY-ONCE PRODUCER] Transaction committed
# [EXACTLY-ONCE CONSUMER] Processing completed
# [EXACTLY-ONCE CONSUMER] Storing with idempotency key: txn-1
```

#### Step 2: Send batch of messages
```bash
# Send batch transactionally (all-or-nothing)
curl -X POST "http://localhost:8088/delivery-guarantee/exactly-once/batch?keyPrefix=batch&count=5"

# Check console output:
# [EXACTLY-ONCE PRODUCER] Batch of 5 messages sent in transaction
# [EXACTLY-ONCE PRODUCER] Batch transaction committed
# Consumer processes all 5 messages
```

#### Step 3: Verify no duplicates
```bash
# Send the same message multiple times
curl -X POST "http://localhost:8088/delivery-guarantee/exactly-once?key=duplicate-test&message=test"
curl -X POST "http://localhost:8088/delivery-guarantee/exactly-once?key=duplicate-test&message=test"
curl -X POST "http://localhost:8088/delivery-guarantee/exactly-once?key=duplicate-test&message=test"

# With idempotency, duplicate detection would prevent reprocessing
# (In this demo, we process all, but show the pattern)
```

**Expected Result**: Messages are delivered exactly once, even with retries or failures.

---

## Observing Kafka Internals

### Check Consumer Group Offsets

```bash
# At-Most-Once consumer group
docker exec -it broker-1 kafka-consumer-groups \
  --bootstrap-server localhost:29091 \
  --describe \
  --group at-most-once-group

# At-Least-Once consumer group
docker exec -it broker-1 kafka-consumer-groups \
  --bootstrap-server localhost:29091 \
  --describe \
  --group at-least-once-group

# Exactly-Once consumer group
docker exec -it broker-1 kafka-consumer-groups \
  --bootstrap-server localhost:29091 \
  --describe \
  --group exactly-once-group
```

### View Messages in Topic

```bash
# View all messages (including uncommitted for at-most-once and at-least-once)
docker exec -it broker-1 kafka-console-consumer \
  --bootstrap-server localhost:29091 \
  --topic delivery-guarantee-demo \
  --from-beginning

# View only committed messages (for exactly-once)
docker exec -it broker-1 kafka-console-consumer \
  --bootstrap-server localhost:29091 \
  --topic delivery-guarantee-demo \
  --from-beginning \
  --isolation-level read_committed
```

### Check Transaction Markers

```bash
# View transaction markers in the topic
docker exec -it broker-1 kafka-console-consumer \
  --bootstrap-server localhost:29091 \
  --topic delivery-guarantee-demo \
  --from-beginning \
  --property print.transaction=true
```

---

## API Endpoints

### Information
- `GET /delivery-guarantee/info` - Get detailed explanation of all delivery guarantees

### Sending Messages
- `POST /delivery-guarantee/at-most-once?message=...` - Send with at-most-once
- `POST /delivery-guarantee/at-least-once?message=...` - Send with at-least-once
- `POST /delivery-guarantee/exactly-once?key=...&message=...` - Send with exactly-once
- `POST /delivery-guarantee/exactly-once/batch?keyPrefix=...&count=5` - Send batch exactly-once

### Testing
- `POST /delivery-guarantee/test/at-most-once/crash` - Simulate consumer crash (message lost)
- `POST /delivery-guarantee/test/at-least-once/crash` - Simulate consumer crash (message redelivered)

### Monitoring
- `GET /delivery-guarantee/stats` - View consumer processing counts
- `POST /delivery-guarantee/reset` - Reset consumer counters

---

## Key Takeaways

### At-Most-Once
- ✅ **Fastest** - lowest latency
- ❌ **Can lose messages**
- ✅ No duplicates
- 📝 Use for: metrics, logs, non-critical data

### At-Least-Once
- ✅ **Never loses messages**
- ❌ **Can duplicate messages**
- ✅ Most common pattern
- 📝 Use for: most applications (with idempotent consumers)

### Exactly-Once
- ✅ **No loss, no duplication**
- ❌ **Higher latency**
- ✅ Strongest guarantee
- 📝 Use for: financial transactions, critical data

---

## Implementation Best Practices

### For At-Least-Once (Most Common)
1. Make your consumer **idempotent**
2. Use unique keys (e.g., transaction ID, order ID)
3. Check for duplicates before processing
4. Use database unique constraints
5. Commit offset only after successful processing

### For Exactly-Once
1. Enable transactions on producer
2. Set `isolation.level=read_committed` on consumer
3. Use idempotency keys
4. Commit offsets within the transaction
5. Handle transaction failures properly

### Error Handling
1. **At-Most-Once**: Log errors, accept loss
2. **At-Least-Once**: Retry processing, send to DLQ if persistent failure
3. **Exactly-Once**: Roll back transaction, retry with exponential backoff

---

## Troubleshooting

### Issue: Exactly-once not working
- Ensure `transactional.id` is unique per producer instance
- Check that consumer is using `read_committed` isolation level
- Verify transaction timeout is adequate

### Issue: Messages being reprocessed
- Check if consumer is committing offsets properly
- Verify `enable.auto.commit` setting matches your pattern
- Ensure processing completes before session timeout

### Issue: Messages being lost
- Check producer `acks` configuration
- Verify broker replication factor
- Check consumer offset commit strategy

---

## Cleanup

```bash
# Stop the application
# Ctrl+C in the terminal

# Delete the test topic
docker exec -it broker-1 kafka-topics \
  --bootstrap-server localhost:29091 \
  --delete \
  --topic delivery-guarantee-demo

# Reset consumer groups (optional)
docker exec -it broker-1 kafka-consumer-groups \
  --bootstrap-server localhost:29091 \
  --delete \
  --group at-most-once-group

docker exec -it broker-1 kafka-consumer-groups \
  --bootstrap-server localhost:29091 \
  --delete \
  --group at-least-once-group

docker exec -it broker-1 kafka-consumer-groups \
  --bootstrap-server localhost:29091 \
  --delete \
  --group exactly-once-group
```
