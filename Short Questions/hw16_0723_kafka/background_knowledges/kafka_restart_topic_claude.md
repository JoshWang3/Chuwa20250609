> How to delete the kafka topic "chuwa-yyds" and recreating the topic?

● I'll help you delete and recreate the Kafka topic. Let me first check your current topic configuration to ensure it will
  recreate correctly.

● Read(src\main\java\com\chuwa\demo\config\KafkaProducerConfig.java)
  ⎿  Read 46 lines

● Perfect! Your NewTopic bean is already correctly configured. Here's how to delete and recreate the topic:

  Method 1: Delete and Let Spring Boot Auto-Recreate

  Step 1: Stop your Spring Boot application

  Press Ctrl+C in the terminal where it's running.

  Step 2: Delete the topic

  docker exec -it broker-1 kafka-topics \
    --bootstrap-server localhost:29091 \
    --delete \
    --topic chuwa-yyds

  Step 3: Verify deletion

  docker exec -it broker-1 kafka-topics \
    --bootstrap-server localhost:29091 \
    --list

  You should NOT see chuwa-yyds in the list.

  Step 4: Restart Spring Boot application

  mvn spring-boot:run

  The NewTopic bean in KafkaProducerConfig.java:39-44 will automatically recreate the topic with:
  - 3 partitions
  - Replication factor of 2

  ---
  Method 2: Delete and Manually Recreate

  If you want to manually create the topic with different settings:

  Step 1: Delete the topic

  docker exec -it broker-1 kafka-topics \
    --bootstrap-server localhost:29091 \
    --delete \
    --topic chuwa-yyds

  Step 2: Manually create with custom settings

  docker exec -it broker-1 kafka-topics \
    --bootstrap-server localhost:29091 \
    --create \
    --topic chuwa-yyds \
    --partitions 3 \
    --replication-factor 2

  Step 3: Verify creation

  docker exec -it broker-1 kafka-topics \
    --bootstrap-server localhost:29091 \
    --describe \
    --topic chuwa-yyds

  Expected output:
  Topic: chuwa-yyds       TopicId: xxx    PartitionCount: 3       ReplicationFactor: 2
          Topic: chuwa-yyds       Partition: 0    Leader: 1       Replicas: 1,2   Isr: 1,2
          Topic: chuwa-yyds       Partition: 1    Leader: 2       Replicas: 2,3   Isr: 2,3
          Topic: chuwa-yyds       Partition: 2    Leader: 3       Replicas: 3,1   Isr: 3,1

  ---
  What Gets Reset

  When you delete and recreate the topic:
  - ✅ All messages are deleted
  - ✅ All consumer group offsets for this topic are reset
  - ✅ Partition assignments start fresh
  - ✅ Consumer groups will start from offset based on AUTO_OFFSET_RESET_CONFIG (currently set to "earliest")

  Important Notes

  1. Replication Factor: Your config uses (short) 2 for replication factor, which is fine since you have 3 brokers.
  2. Consumer Offset Reset: After recreation, when consumer groups connect:
    - They'll have no committed offset
    - Will start from "earliest" (beginning) because of the AUTO_OFFSET_RESET_CONFIG setting in your consumer config
  3. Alternative - Just Reset Offsets: If you only want to reset consumer offsets without deleting messages:
  docker exec -it broker-1 kafka-consumer-groups \
    --bootstrap-server localhost:29091 \
    --group consumer-group-1 \
    --reset-offsets \
    --to-earliest \
    --topic chuwa-yyds \
    --execute

  I recommend Method 1 (auto-recreation via Spring Boot) since your NewTopic bean is properly configured!