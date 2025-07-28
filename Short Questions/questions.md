1. Given N (number of partitions) and M (number of consumers,) what will happen when N>=M and N<M
   respectively?

    N>=M: This is the optimal case. Kafka assigns each consumer at least one partition and all partitions are actively consumed.
    N < M: Kafka can only assign one consumer per partition, so M - N consumers will be idle. Some consumers will do nothing because Kafka doesn't allow multiple consumers in the same group to consume the same partition.

2. Explain how brokers work with topics?
   
    A broker is a Kafka server that manages topic data, handles read/write requests, and ensures data durability and availability. When producers and consumers interact with a topic, they are communicating with the brokers that manage that topic’s data.
    1. Topic Creation
       When a topic is created (either manually or automatically), it is configured with:
       1. Number of partitions 
       2. Replication factor

    Kafka then distributes those partitions across available brokers in the cluster.
   2. Partition Placement
      1. Each partition of a topic is stored on a broker. 
      2. If the replication factor is >1, then replicas of that partition are stored on other brokers for fault tolerance. 
         1. One broker is the leader for a partition (handles reads/writes). 
         2. Other brokers with replicas are followers (they replicate the leader’s data).
3. Are messages pushed to consumers or consumers pull messages from topics?
   
    Consumers pull messages from topics, which allows them to have control in their pace.
4. How to avoid duplicate consumption of messages?

   Avoiding duplicate message consumption in Kafka requires managing both how consumers read messages and how offsets are committed.
   1. Commit Offsets After Processing
   2. Use Idempotent Processing
   3. Enable Exactly-Once Semantics (EOS)
   4. Use Consumer Groups Effectively

5. What will happen if some consumers are down in a consumer group? Will data loss occur? Why?

    There will not be any data loss because Kafka will reassign failed partitions to the remaining active consumers in the group. (data are stored on brokers)
   
6. What will happen if an entire consumer group is down? Will data loss occur? Why?
   
    Same as above. Kafka retains all messages on its brokers and messages are stored in partitions, waiting for consumers to read them until expiration. Also, the offset will not change until a new consumer reads last commited offset.
7. Explain consumer lag and how to resolve it?

   Consumer lag is the difference between the latest message offset in a Kafka partition and the last committed offset by a consumer in a consumer group. (How many messages are waiting to be processed by a consumer.)
   
    Solution:
   1. Scale up the Consumer Group
      1. Add more consumers to the group if partitions > consumers. 
      2. Kafka will rebalance partitions, improving parallelism.
   2. Optimize Consumer Logic
      1. Reduce message processing time:
         1. Batch processing 
         2. Efficient data handling (e.g., bulk DB writes)
         3. Async I/O
      2. Use faster algorithms or caching.
   3. Tune Fetch and Poll Settings
      1. Increase max bytes and poll intervals:
      2. Balance throughput with latency.
   4. Commit Offsets Efficiently
      1. Use asynchronous or batched commits. 
      2. Avoid committing offsets too frequently or too rarely.
   5. Check for Partition Imbalance
8. Explain how Kafka tracks message delivery?

   Kafka tracks message delivery primarily through offsets. Consumers will commit offest to report their progress after a message is processed.

9. Compare Kafka vs RabbitMQ, compare messageing frameworks vs MySql (Why Kafka)?

   Kafka vs RabbitMQ:
   | Feature                      | **Apache Kafka**                              | **RabbitMQ**                                 |
   | ---------------------------- | --------------------------------------------- | -------------------------------------------- |
   | **Model**                    | Log-based, distributed, pub-sub               | Traditional message broker (queue-based)     |
   | **Message Storage**          | Persistent log (messages kept for fixed time) | Messages removed after acknowledgment        |
   | **Delivery Semantics**       | At-most-once, At-least-once, Exactly-once     | At-most-once, At-least-once                  |
   | **Message Replay**           | Yes — via offsets                           | No — once consumed, message is gone        |
   | **Ordering Guarantees**      | Guaranteed per-partition                      | FIFO per queue, but can break with consumers |
   | **Scalability**              | Designed for horizontal scalability           | Harder to scale across clusters              |
   | **Performance / Throughput** | Very high (millions of msgs/sec)              | Lower than Kafka (good for complex routing)  |
   | **Use Case Fit**             | Big data pipelines, streaming analytics       | Task queues, RPC, request-response systems   |
   | **Consumer Model**           | Pull-based                                    | Push-based                                   |
   | **Built-in Retention**       | Yes (configurable by time/size)               | No — messages deleted once acknowledged      |
   | **Message Routing**          | Simple (partitioning & topic keys)            | Advanced (exchanges, bindings, routing keys) |
   | **Backpressure Handling**    | Consumers pull when ready                     | Needs tuning; may drop messages or slow down |

    Messaging system vs MySQL:
   | Feature                     | **Kafka / Messaging Frameworks**                     | **MySQL / Traditional RDBMS**                 |
   | --------------------------- | ---------------------------------------------------- | --------------------------------------------- |
   | **Purpose**                 | Real-time, streaming message transport               | Structured data storage & querying            |
   | **Data Flow**               | Event-based, publish-subscribe                       | CRUD-based transactional model                |
   | **Latency**                 | Millisecond-level (streaming)                        | Higher — optimized for transactional access   |
   | **Scalability**             | High (partitioning, distributed logs)                | Vertical scaling limits                       |
   | **Replayability**           | Yes (via offsets/log retention)                    | No — you can’t re-query past updates easily |
   | **Throughput**              | Very high (write-heavy systems)                      | Moderate; bottlenecked by disk and locks      |
   | **Durability of Events**    | Configurable retention (by time/size)                | Data persists until explicitly deleted        |
   | **Integration & Pipelines** | Easily connects to Spark, Flink, Hadoop, Druid, etc. | Requires ETL pipelines or custom integrations |
   | **Schema Evolution**        | Avro/Protobuf + Schema Registry support              | Schema migrations are complex                 |

