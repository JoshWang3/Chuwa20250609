# Kafka Consumer Demo - Test Results

## Environment Setup ✅

- **Kafka Cluster**: Single broker running on localhost:9092
- **Zookeeper**: Running on localhost:2181
- **Kafka UI**: Available at http://localhost:8080
- **Spring Application**: Running on localhost:8081

## Topic Configuration ✅

```bash
Topic: test-topic	TopicId: 55nIBfpKQ46KYecuJCK5vQ	PartitionCount: 3	ReplicationFactor: 1	Configs: 
	Topic: test-topic	Partition: 0	Leader: 1	Replicas: 1	Isr: 1
	Topic: test-topic	Partition: 1	Leader: 1	Replicas: 1	Isr: 1
	Topic: test-topic	Partition: 2	Leader: 1	Replicas: 1	Isr: 1
```

## Consumer Groups Verification ✅

### Consumer Group 1 (3 consumers)
- **Group ID**: consumer-group-1
- **Consumers**: 3 active consumers
- **Partition Assignment**: Each consumer handles 1 partition (optimal distribution)

```
PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG
    1          3               3               0
    2          2               2               0
    0          2               2               0
```

### Consumer Group 2 (2 consumers)
- **Group ID**: consumer-group-2
- **Consumers**: 2 active consumers
- **Partition Assignment**: One consumer handles 2 partitions, another handles 1

```
PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG
    0          2               2               0
    1          3               3               0
    2          2               2               0
```

### Consumer Group 3 (4 consumers, N > M scenario)
- **Group ID**: consumer-group-3
- **Consumers**: 4 consumers configured, but only 3 active (1 idle as expected)
- **Partition Assignment**: 3 consumers active, 1 idle (demonstrates N < M scenario)

## Message Production and Consumption Tests ✅

### Test 1: Simple Message
```bash
curl -X POST "http://localhost:8081/api/messages/send?message=Hello%20from%20Kafka%20Consumer%20Demo"
```
**Result**: Message successfully sent and consumed by all consumer groups

### Test 2: Batch Messages
```bash
curl -X POST "http://localhost:8081/api/messages/send-batch?count=5"
```
**Result**: 5 messages distributed across partitions and consumed by different consumers

### Test 3: Key-based Partitioning
```bash
curl -X POST "http://localhost:8081/api/messages/send-with-key?key=user123&message=User%20specific%20message"
```
**Result**: Message with key routed to partition 0, consumed by all groups

## Consumer Behavior Observations

### Load Balancing Within Groups
- **Consumer Group 1**: Perfect load balancing (3 consumers, 3 partitions)
- **Consumer Group 2**: 2 consumers sharing 3 partitions (one consumer handles 2 partitions)
- **Consumer Group 3**: 4 consumers, but only 3 active (1 idle due to partition limit)

### Message Processing
- **Manual Acknowledgment**: All messages properly acknowledged after processing
- **Processing Time**: Different consumers have different processing times (800ms, 1000ms, 1500ms)
- **No Message Loss**: All messages successfully processed by all consumer groups

### Consumer Group Independence
- Each consumer group maintains independent offsets
- All groups receive and process the same messages
- No interference between groups

## Key Findings

1. **N >= M (Partitions >= Consumers)**: Consumer Group 1 and 2 demonstrate optimal and sub-optimal scenarios
2. **N < M (Partitions < Consumers)**: Consumer Group 3 shows idle consumers when consumers exceed partitions
3. **Fault Tolerance**: Manual acknowledgment ensures no message loss
4. **Partition Assignment**: Kafka automatically balances partitions among available consumers
5. **Independent Processing**: Each consumer group processes messages independently

## Application Logs Sample

```
Consumer-1 received message: 'Batch message 2' from partition: 1 with offset: 2
Consumer-2 received message: 'Hello from Kafka Consumer Demo' from partition: 1 with offset: 1
Consumer-3 received message: 'User specific message' from partition: 0 with offset: 1
Group2-Consumer1 received: 'User specific message' from partition: 0 offset: 1
Group3-Consumer2 received: 'User specific message' from partition: 0 offset: 1
```

## Conclusion

The Kafka consumer demo successfully demonstrates:
- Multiple consumer groups with different consumer counts
- Proper partition assignment and load balancing
- Message delivery guarantees with manual acknowledgment
- Key-based partitioning functionality
- Consumer group independence and offset management

All requirements from HW14 have been successfully implemented and verified.