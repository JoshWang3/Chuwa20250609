# hw16 submission

## Q1: Explain following concepts, and how they coordinate with each other:Topic, Partition, Broker, Consumer group, Producer, Offset, Zookeeper

### Answer:
| Concept        | Role                                                      | Works With                          |
| -------------- | --------------------------------------------------------- | ----------------------------------- |
| Topic          | Logical message category                                  | Partitions, Producers, Consumers    |
| Partition      | Unit of parallelism within a topic                        | Offset, Broker                      |
| Broker         | Kafka server storing messages and serving clients         | Zookeeper, Partitions               |
| Consumer Group | Group of consumers sharing work                           | Partitions, Offsets                 |
| Producer       | Sends messages to Kafka topics                            | Brokers, Partitions                 |
| Offset         | Index of a message within a partition                     | Used by consumers to track progress |
| Zookeeper      | Cluster manager and metadata store (older Kafka versions) | Brokers, Topic configuration        |

## Q2: Given N (number of partitions) and M (number of consumers,) what will happen when N>=M and N<M respectively?

### Answer:
| Case      | Description                    | Behavior                                             | Efficiency |
| --------- | ------------------------------ | ---------------------------------------------------- | ---------- |
| **N ≥ M** | More partitions than consumers | Partitions are fairly distributed; some get multiple | ✅ Good     |
| **N < M** | More consumers than partitions | Some consumers idle; can't share partition           | ⚠️ Poor    |

## Q3: Explain how brokers work with topics?

### Answer:
These partitions are distributed across multiple brokers for scalability and fault tolerance.

When You Create a Topic:

You specify the number of partitions and replication factor.

Kafka assigns each partition to one or more brokers.

One broker becomes the leader of a partition.

Other brokers hold replicas (followers) if replication is configured.

## Q4: Are messages pushed to consumers or consumers pull messages from topics?

### Answer:
Kafka Uses Pull-based Messaging

How It Works:

Producers push (publish) messages to a Kafka topic.

Consumers poll the Kafka broker for new messages.

Kafka responds with a batch of messages (if available).

Consumers process the batch and optionally commit offsets.

## Q5: How to avoid duplicate consumption of messages?

### Answer:
| Strategy                      | Description                                                     | Best For                            |
| ----------------------------- | --------------------------------------------------------------- | ----------------------------------- |
| Idempotent processing         | Process message in a way that re-processing has no side effects | All use cases                       |
| Manual offset commit          | Commit offset **after** processing is confirmed                 | Precise delivery guarantees         |
| Idempotent producer           | Prevent duplicate production due to retries                     | High-throughput producer            |
| Kafka EOS (Exactly Once)      | Full transactional guarantee (producer → broker → consumer)     | Financial transactions, strict apps |
| Deduplication via cache/store | Store processed message IDs to prevent re-processing            | Cross-system deduplication          |

## Q6: What will happen if some consumers are down in a consumer group? Will data loss occur? Why?

### Answer:
TL;DR:

No data loss will occur, assuming:

Kafka is correctly configured

Messages are not expired due to retention settings

Offsets are not committed prematurely

How Kafka Handles It:

1. Rebalance Triggered
   When a consumer dies (e.g., crashes or disconnects), Kafka detects the failure through heartbeat timeouts (default ~10 seconds).

A rebalance is triggered: remaining consumers are reassigned the partitions previously owned by the failed consumer.

2. Message Reprocessing (if offset not committed)
   If the failed consumer did not commit offsets, the new consumer will re-read uncommitted messages from that partition.

If it already committed, those messages are not reprocessed.

3. No Data Loss (Kafka Guarantees Durability)
   Kafka stores messages durably on disk (in partitions) for a configured retention period (e.g., 7 days).

Even if no consumer is online, data stays in Kafka until consumed or expired.

## Q7: What will happen if an entire consumer group is down? Will data loss occur? Why?

### Answer:
Kafka Will NOT Delete the Messages

Kafka continues to retain messages in the topic partitions based on the configured retention policy (default is 7 days).

No data loss will occur as long as:

The messages are still within the retention window

Replication is enabled to guard against broker failure

Key Concepts to Understand:

1. Kafka Is Decoupled by Design
   Producers write to Kafka independently of consumers.

Kafka persists messages on disk in partitions.

2. Messages Are Not Deleted on Consumption
   Kafka doesn’t delete a message just because it’s been read.

Messages are deleted only after the retention period expires (e.g., time-based or size-based).

3. Consumer Groups Track Offsets
   When a consumer group restarts, it resumes from its last committed offset.

If the group was manually committing offsets, unprocessed messages can still be re-consumed.

## Q8: Explain consumer lag and how to resolve it?

### Answer:
Consumer Lag is the difference between:

The latest message offset in a partition minus

The last committed offset by the consumer group

| Step                      | Purpose                                    |
| ------------------------- | ------------------------------------------ |
| Add consumers             | Increase parallelism                       |
| Optimize logic            | Reduce time per message                    |
| Tune configs              | Prevent timeouts and improve throughput    |
| Use manual offset commits | Avoid losing unprocessed messages          |
| Monitor and alert         | Proactively catch lag before it’s critical |

## Q9: Explain how Kafka tracks message delivery?

### Answer:
1. Producer-Side Delivery Tracking

How Kafka Ensures a Message Is Delivered to a Broker

Kafka producers track message delivery using acks (acknowledgment) settings and idempotence:

| `acks` Setting | Description                                 | Delivery Guarantee             |
| -------------- | ------------------------------------------- | ------------------------------ |
| `acks=0`       | Producer does **not wait** for any response | Fastest, but risk of data loss |
| `acks=1`       | Leader broker **acknowledges write**        | Safe for many cases            |
| `acks=all`     | **All replicas** must acknowledge           | Strongest delivery guarantee   |

2. Broker-Side: Message Storage and Durability
   Kafka writes messages to disk in the topic’s partition and keeps them based on retention policies (e.g., 7 days or until topic size exceeds limit).

Messages are not deleted after being read.

They are only deleted based on time or size retention.

Kafka uses write-ahead logs to persist messages before acknowledging them to the producer.

3. Consumer-Side: Tracking Delivery with Offsets

Kafka tracks whether a consumer has received (and "processed") a message using offsets.

Offset = Position in a Partition Log

Each partition is an ordered, append-only log.

Each message has a unique offset (e.g., offset 0, 1, 2…).

## Q10: Compare Kafka vs RabbitMQ, compare messageing frameworks vs MySql (Why Kafka)?

### Answer:
| Feature                  | **Apache Kafka**                              | **RabbitMQ**                                     |
| ------------------------ | --------------------------------------------- | ------------------------------------------------ |
| **Model**                | Distributed **log-based**, pub-sub            | Traditional **queue-based**, message broker      |
| **Message Storage**      | Persistent, **append-only log**, retains data | Messages usually deleted after consumption       |
| **Retention**            | Time/size-based (e.g., 7 days)                | Deleted upon acknowledgment                      |
| **Ordering**             | **Guaranteed per partition**                  | FIFO per queue (with care)                       |
| **Throughput**           | Very high (millions/sec)                      | Good (tens/hundreds of thousands/sec)            |
| **Use Case**             | Event streaming, real-time analytics          | Task queues, job dispatching                     |
| **Consumer Model**       | **Pull-based** (consumer polls)               | **Push-based**                                   |
| **Replay Support**       | Yes (by replaying from a previous offset)     | No (unless messages are requeued manually)       |
| **Scaling**              | Highly scalable with partitions               | Harder to scale; queues must be explicitly split |
| **Built-in persistence** | Yes, replicated logs (disk-based)             | Yes, but less optimized for long-term storage    |
| **Exactly-once Support** | Yes (with transactions)                       | No (only at-least-once or best-effort)           |
