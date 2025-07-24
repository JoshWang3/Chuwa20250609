# Kafka Consumer Demo Application

This Spring Boot application demonstrates Kafka consumer groups and message consumption patterns.

## Setup Instructions

1. **Start Kafka Cluster**
   ```bash
   # Use the docker-compose file from the provided GitHub repo
   docker-compose up -d
   ```

2. **Create Test Topic**
   ```bash
   # Create topic with 3 partitions
   kafka-topics.sh --create --topic test-topic --bootstrap-server localhost:9092 --partitions 3 --replication-factor 3
   ```

3. **Build and Run Application**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Features Demonstrated

### Single Consumer Group with 3 Consumers
- Consumer Group 1 has 3 consumers processing messages from `test-topic`
- Each consumer processes messages from different partitions
- Manual acknowledgment for better control

### Multiple Consumer Groups
- Consumer Group 2: 2 consumers
- Consumer Group 3: 4 consumers (some will be idle if partitions < consumers)

### Message Delivery Guarantees
- **At-least-once**: Manual acknowledgment after processing
- **Exactly-once**: Idempotent producer configuration
- **At-most-once**: Auto-commit before processing (configurable)

### Custom Partitioning
The producer supports both:
- Default round-robin partitioning
- Key-based partitioning (messages with same key go to same partition)

## API Endpoints

1. **Send Simple Message**
   ```bash
   curl -X POST "http://localhost:8081/api/messages/send?message=Hello World"
   ```

2. **Send Message with Key**
   ```bash
   curl -X POST "http://localhost:8081/api/messages/send-with-key?key=user1&message=User message"
   ```

3. **Send Batch Messages**
   ```bash
   curl -X POST "http://localhost:8081/api/messages/send-batch?count=10"
   ```

## Testing Scenarios

### 1. Basic Consumer Group Testing
- Start application and send messages
- Observe how messages are distributed among 3 consumers
- Take screenshots of logs showing partition assignment

### 2. Consumer Scaling
- Add more consumers to see idle consumers when consumers > partitions
- Remove consumers to see partition rebalancing

### 3. Multiple Consumer Groups
- Each group consumes all messages independently
- Monitor offset progression for each group

### 4. Message Delivery Guarantees
- Test manual acknowledgment vs auto-commit
- Simulate consumer failures to test redelivery

## Monitoring Consumer Offsets

```bash
# Check consumer group offsets
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group consumer-group-1
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group consumer-group-2
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group consumer-group-3
```

## Expected Observations

1. **N >= M**: All consumers active, load balanced
2. **N < M**: Some consumers idle, only N consumers active
3. **Rebalancing**: When consumers join/leave, partitions redistribute
4. **Offset Management**: Each group maintains independent offsets
5. **Fault Tolerance**: Failed consumers don't cause data loss