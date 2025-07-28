1. Topic

   A topic is a category or feed name to which records are sent by producers.
2. Partition

   Each topic is split into partitions, which are ordered, immutable sequences of records. Each record in a partition has a unique offset.
   1. Partitions allow Kafka to scale horizontally. 
   2. Each partition is hosted on one broker at a time. 
   3. Records in partitions are strictly ordered.
3. Broker

   A broker is a Kafka server that stores topic data and serves producers and consumers. Each broker can handle thousands of partitions and manage part of the workload.
   1. A Kafka cluster is made up of multiple brokers. 
   2. Partitions are distributed across brokers for load balancing.
4. Consumer Group

   A consumer group is a set of consumers working together to consume messages from a topic.
   1. Each partition is consumed by only one consumer in the group at a time (ensures parallel processing without duplication). 
   2. Multiple consumer groups can consume the same topic independently (for different processing needs).
5. Producer
   A producer is an application that sends (publishes) messages to a Kafka topic. 
   1. Producers choose which topic and partition to send messages to. 
   2. They can publish in round-robin fashion or based on keys (which help ensure ordering).
6. Offset
   An offset is a unique identifier (number) for each record within a partition. It marks the record’s position. 
   1. Consumers use offsets to track what they've read. 
   2. Offsets can be committed (saved) to allow fault-tolerant consumption.
7. Zookeeper
   Zookeeper is a centralized service used by Kafka for:
   1. Managing broker metadata and configurations 
   2. Leader election for partitions (which broker is in charge of a partition)
   3. Detecting broker failures

Workflow:
1. Producer sends messages to a Kafka topic (e.g., "user_activity"). 
2. The topic is split into partitions (e.g., 3 partitions) for scalability. 
3. Each partition is stored on a different broker. 
4. A consumer group with 3 consumers reads from the topic. Each consumer handles one partition. 
5. As consumers read messages, they keep track of their position using offsets. 
6. Kafka uses Zookeeper to manage which broker is the leader for each partition and to coordinate broker metadata (in older setups).