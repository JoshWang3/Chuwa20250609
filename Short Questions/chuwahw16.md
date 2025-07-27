# chuwahw16

# Concepts

## 1. Topic

A **topic** is a named logical channel where messages are published. It’s the core abstraction in Kafka.

- Producers write data to a topic.
- Consumers read data from a topic.

Example: A topic named `user-activity` might store all login/logout events.

---

## 2. Partition

A **partition** is a horizontal subdivision of a topic. Each topic consists of one or more partitions.

- Messages within a partition are **ordered**.
- Each partition is **independent** and can be stored on a different broker.
- A topic with multiple partitions enables parallelism.

Example: Topic `orders` with 3 partitions: `orders-0`, `orders-1`, `orders-2`

---

## 3. Broker

A **broker** is a Kafka server that stores data and serves client requests (produce and consume).

- Kafka is deployed as a **cluster** of brokers.
- Each broker hosts some partitions.
- Brokers communicate with Zookeeper (or use Kafka Raft in newer versions).

Example: A cluster with 3 brokers might distribute partitions of a topic among them.

---

## 4. Producer

A **producer** is a client application that publishes messages to Kafka topics.

- It chooses which topic and optionally which partition to send to.
- Kafka producers are asynchronous by default.
- Producers can use key-based partitioning (messages with the same key go to the same partition).

---

## 5. Consumer Group

A **consumer group** is a group of one or more consumers working together to consume data from a topic.

- Each partition is consumed by only one consumer **within** a group.
- Multiple consumer groups can read from the same topic independently.
- Allows **horizontal scaling** of consumers.

Example: If a topic has 3 partitions, and there are 3 consumers in a group, each will handle one partition.

---

## 6. Offset

An **offset** is a unique identifier of a message within a partition.

- Each message in a partition has a sequential offset.
- Consumers track the offset to know which message to read next.
- Offsets can be committed manually or automatically to Kafka (or to Zookeeper in older setups).

---

## 7. Zookeeper

**Zookeeper** is a centralized coordination service used by Kafka (before version 2.8).

- Manages metadata, leader election, broker registration
- Keeps track of brokers and partitions
- Monitors node status

Kafka is moving away from Zookeeper in favor of **KRaft mode**, where Kafka itself handles metadata.

---

## How They Work Together

Let’s tie it all together:

1. A **producer** sends messages to a **topic**, which may be split into **partitions**.
2. Kafka distributes these partitions across multiple **brokers**.
3. Each message within a partition is identified by an **offset**.
4. A **consumer group** reads messages from partitions, with one consumer assigned per partition.
5. Kafka uses **Zookeeper** (or internal metadata in newer versions) to manage broker metadata, partition assignments, and leader election.

# Question 1

## Case 1: **N ≥ M** (Number of partitions is greater than or equal to number of consumers)

### What happens:

- **Each consumer gets at least one partition**
- Some consumers may get **multiple partitions**
- **All consumers are active**

### Example:

- N = 4 partitions
- M = 2 consumers

### Characteristics:

- Good utilization of consumers
- Load may not be perfectly balanced
- Kafka distributes partitions **as evenly as possible**

## Case 2: **N < M** (Number of partitions is less than number of consumers)

### What happens:

- Only **N consumers are active**
- Remaining **M − N consumers are idle**
- Kafka assigns **only one consumer per partition** within the same group

### Example:

- N = 2 partitions
- M = 4 consumers

→ Only 2 consumers are used:

### Characteristics:

- Wasted consumer resources
- Throughput is limited by the number of partitions, not the number of consumers

# Question 2

### How Kafka Brokers Work with Topics

In Kafka, **brokers** are the servers that **store and serve data**, and **topics** are logical channels for organizing messages. Here’s how they coordinate:

---

## 1. What is a Broker?

- A **broker** is a Kafka server (node) that:
    - Stores topic data (messages)
    - Handles producer requests (write)
    - Handles consumer requests (read)
- Kafka is run as a **cluster** of brokers for scalability and fault tolerance

Each broker has a unique ID, like `broker-1`, `broker-2`, etc.

---

## 2. What is a Topic?

- A **topic** is a named logical stream of messages
- Topics are divided into **partitions** for parallelism

## 3. How Brokers Work with Topics

### A. Topic Creation

- When a topic is created (manually or automatically), Kafka assigns its **partitions** across available brokers
- Each **partition** resides on exactly one broker (primary) and may be **replicated** on others

### B. Partition Assignment

Example:

Topic `orders` with 3 partitions in a 3-broker cluster:

```

CopyEdit
Partition 0 → Broker 1
Partition 1 → Broker 2
Partition 2 → Broker 3

```

Kafka will also create **replicas** for fault tolerance:

```

CopyEdit
Partition 0 replicas → Broker 1 (leader), Broker 2 (follower)
Partition 1 replicas → Broker 2 (leader), Broker 3
...

```

---

### C. Producers

- Producers send messages to a **specific topic**
- Kafka broker receiving the request will route it to the broker **owning the target partition**
- Routing is based on:
    - Partition key (if given)
    - Round-robin (if key is not given)

---

### D. Consumers

- Consumers ask Kafka for messages from a specific topic and partition
- Kafka routes the request to the **broker that hosts that partition**
- Kafka ensures only **one consumer per partition** in a consumer group

---

## 4. Leader and Replica Coordination

- Each partition has a **leader** broker that handles read/write
- **Followers** replicate the data for redundancy
- **Zookeeper** (or Kafka’s internal controller in KRaft mode) tracks which broker is leader for which partition

---

## 5. Broker Failure Handling

- If a broker (say Broker 1) fails, a follower partition on another broker (say Broker 2) is promoted to **leader**
- Kafka continues serving traffic without data loss, assuming replication is up-to-date

# Question 3

In Apache Kafka, **consumers pull messages** from topics — Kafka is a **pull-based** messaging system.

---

## How It Works

1. **Producers** push messages to Kafka topics.
2. **Consumers** **pull** messages from Kafka by polling the broker for new records.

# Question 4

Avoiding **duplicate consumption** of messages in Kafka is a critical part of designing **reliable, idempotent** consumer applications. While Kafka guarantees **at-least-once delivery** by default, you can achieve **exactly-once processing** with proper strategies.

---

## Root Cause of Duplicate Consumption

Kafka may deliver the same message more than once if:

- The consumer crashes after processing but **before committing the offset**
- Offset commits happen **before processing completes**, and then processing fails

---

## Strategies to Avoid Duplicate Processing

### 1. **Use Manual Offset Commit — After Processing**

Ensure offsets are committed **only after** the message is successfully processed.

```java
java
CopyEdit
ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
for (ConsumerRecord<String, String> record : records) {
    process(record); // your logic
    consumer.commitSync(); // commit after success
}

```

### Why it helps:

If the consumer crashes before `commitSync()`, Kafka will redeliver the message — but you **haven’t processed it yet**, so it’s safe.

---

### 2. **Make the Processing Idempotent**

Design your business logic so that **processing the same message multiple times has no side effects**.

**Example:**

- Use unique message IDs
- If `orderId` already exists in DB, skip or ignore
- Insert with `INSERT IGNORE` or `ON CONFLICT DO NOTHING`

This prevents writing duplicate records even if the message is redelivered.

---

### 3. **Use Transactions in Kafka (Exactly-Once Semantics)**

Kafka supports **exactly-once processing** using:

- **Idempotent producer**
- **Transactional producer + consumer**

You must:

- Consume messages within a transaction
- Produce output + commit offset in the same transaction

**Steps:**

1. Enable `enable.idempotence=true` in producer
2. Use transactional APIs:
    
    ```java
    java
    CopyEdit
    producer.initTransactions();
    producer.beginTransaction();
    // process and send
    producer.send(...)
    consumer.commitSync();
    producer.commitTransaction();
    
    ```
    

**Requires Kafka version 0.11+**

---

### 4. **Deduplication Layer or Log**

Store a log of processed message IDs (like UUIDs or Kafka offsets), and skip duplicates.

# Question 5

## What Happens When Consumers Go Down?

### 1. **Rebalance is Triggered**

- Kafka detects that one or more consumers are down (e.g., due to heartbeat timeout).
- It triggers a **rebalance** within the consumer group.

### 2. **Partitions Are Reassigned**

- The partitions that were assigned to the down consumer are reassigned to the remaining active consumers.
- The new consumers start consuming from the **last committed offset** for each partition.

---

## Why No Data Loss Occurs

### Because Kafka:

- **Stores all messages in partitions durably** (on disk, for a configured retention time)
- **Uses committed offsets** to track consumer progress
- **Retains unconsumed messages** as long as they are within the retention period

So even if a consumer crashes, the messages it hadn’t acknowledged (committed) will **remain in the partition**, and another consumer can resume from the last committed point.

---

## Key Requirements to Avoid Data Loss

| Config / Behavior | Description |
| --- | --- |
| `enable.auto.commit=false` | Commit offsets manually **after** processing |
| Durable broker setup | Kafka must persist messages (default behavior) |
| Replication factor ≥ 2 | Ensures message durability if a broker fails |
| Consumers must commit offsets properly | If offsets are committed too early, reprocessing may be incorrect |

---

## Example

1. Partition P0 is assigned to `Consumer-1`
2. `Consumer-1` processes up to offset 500, commits
3. `Consumer-1` crashes
4. `Consumer-2` takes over P0
5. `Consumer-2` resumes from offset **501**

→ **No data loss**, and processing continues from the last committed point.

# Question 6

If an **entire Kafka consumer group is down**, **data will not be lost** — **as long as Kafka is properly configured** and the messages are within the **retention period**.

---

## What Happens When a Consumer Group Is Down

1. **No consumers are running** in the group.
2. **No partitions are being consumed.**
3. **Kafka continues to retain messages** in the topic partitions.
4. When the consumer group comes back up, it can resume consuming messages from the **last committed offsets**.

---

## Why Data Is Not Lost

Kafka ensures durability by:

- **Persisting messages to disk**
- **Storing messages for a configurable retention period** (e.g. 7 days, infinite, etc.)
- Not deleting messages just because no one is consuming them

Unless messages expire due to retention policy, **they remain safe**.

# Question 7

**Consumer lag** is the difference between:

> The latest offset available in a partition
> 
> 
> **−**
> 
> **The latest offset committed by a consumer group**
> 

---

### Why It Matters

Consumer lag tells you:

- How far **behind** your consumer is
- Whether your consumer is **keeping up with the producer**
- If your system is at risk of **message delay or backlog**

---

### Example

| Partition | Latest Offset (Producer) | Committed Offset (Consumer) | **Lag** |
| --- | --- | --- | --- |
| 0 | 150 | 140 | 10 |
| 1 | 200 | 200 | 0 |
| 2 | 170 | 160 | 10 |

**Total lag = 20**

→ This consumer group is 20 messages behind

# Question 8

Kafka itself **does not track message delivery to consumers** in the traditional sense (like a delivery receipt). Instead, Kafka uses a **pull-based model** and relies on **offsets** and **consumer coordination** to determine how far messages have been consumed.

Here’s how Kafka tracks message delivery **indirectly and efficiently**:

---

## 1. **Kafka Stores All Messages in Partitions**

- Messages are written to **topic partitions** as an **append-only log**.
- Each message is assigned a **sequential offset**.
- Messages remain in Kafka for a **configured retention period**, regardless of whether they've been consumed.

Kafka does **not delete a message** just because it’s been delivered.

---

## 2. **Consumers Track Their Own Progress via Offsets**

Consumers keep track of **which messages they’ve processed** by **committing offsets**.

### Two main types:

- **Committed Offset**: Last offset successfully processed and stored (in Kafka or Zookeeper)
- **Current Log End Offset**: Latest message offset available in the partition

### Message Delivery = Tracked by offset difference:

> If a consumer commits offset 123, it means it has successfully processed messages up to and including offset 122.
> 

---

## 3. **Offset Storage**

- Kafka stores committed offsets in a **special internal topic**:
    
    `__consumer_offsets`
    
- Each consumer group stores its offsets separately
- These offsets are **used to resume consumption** after restarts

---

## 4. **Consumer Lag as an Indicator of Delivery Progress**

Kafka does not track per-message delivery per consumer.

Instead, **consumer lag** is used to infer how far behind a consumer is:

```
text
CopyEdit
Consumer lag = Log End Offset (latest) - Committed Offset
```

If lag is zero, the consumer is fully caught up.

# Question 9

| Feature | **Kafka** | **RabbitMQ** |
| --- | --- | --- |
| **Design Model** | Distributed commit log (Pub/Sub + stream) | Message broker (classic queue model) |
| **Message Ordering** | Guaranteed **within partition** | FIFO per queue (but not across consumers) |
| **Throughput** | Extremely high (~millions/sec) | Moderate (~tens/hundreds of thousands/sec) |
| **Latency** | Slightly higher (batch-focused) | Lower latency (good for short messages) |
| **Message Retention** | **Time- or size-based retention** (configurable) | **Deletes after consumption** (by default) |
| **Persistence** | Durable, even after consumption | Durable or transient (configurable) |
| **Consumer Model** | Pull-based (consumers poll) | Push-based |
| **Replay Support** | Yes (by offset) — great for event sourcing | No (must implement retry mechanism) |
| **Scaling** | Excellent — partitioning across brokers | Queue sharding required |
| **Use Case Fit** | Event streaming, analytics, logs, CDC | Task queues, real-time jobs, RPC |
| **Exactly Once Support** | Supported (via transactions + EOS) | Harder, needs custom setup |
| **Built-in Clustering** | Yes (brokers + Zookeeper/KRaft) | Yes (Erlang clustering) |
| **Backpressure Handling** | Excellent (consumer lag shows imbalance) | Less transparent |

| Point | **Kafka / Messaging** | **MySQL / DB** |
| --- | --- | --- |
| **Purpose** | Event streaming, messaging, decoupling | Structured storage, querying (CRUD) |
| **Latency Sensitivity** | Millisecond-range ingestion | Slower (ACID overhead, row locks) |
| **Concurrency Handling** | Scales via partitions and consumer groups | Transactions get heavier with concurrency |
| **Retention & Replay** | Retains messages for **replay / audit / recovery** | No native replay of data events |
| **Decoupling Components** | Enables async, loosely coupled architectures | DB enforces sync + tight integration |
| **Scalability** | Horizontally scalable (brokers) | Not designed for high-volume ingestion |
| **Event Ordering** | Partition-wise strict ordering | No inherent ordering semantics |
| **Schema Flexibility** | Schemaless (Avro/JSON/Protobuf optional) | Fixed table schema |
| **Use Cases** | Log processing, data pipelines, CDC, real-time apps | CRUD apps, reporting, storage |

# Question 10

## part1

![image 1](101./.jpg)

## part2

**Extra consumers are idle**:

- Kafka can only assign **at most one consumer per partition**.
- **Extra consumers do not receive any data.**

## part3

- Each group had its own partition assignments
- All groups showed **0 lag**, and **offsets advanced independently**
- **Offsets were not shared between groups**

## part4

![image 2](104./.jpg)

## part5

```java
@Bean
public ProducerFactory<String, String> producerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    // At most once settings:
    configProps.put(ProducerConfig.ACKS_CONFIG, "0"); // fire-and-forget
    configProps.put(ProducerConfig.RETRIES_CONFIG, 0); // no retry on failure
    configProps.put(ProducerConfig.LINGER_MS_CONFIG, 0); // send immediately

    return new DefaultKafkaProducerFactory<>(configProps);
}

```

This is at-most-once guarantee, so with this guarantee, it will be very fast to deliver messages, since even messages lost, the code don’t retry send it.

 

## part6

```java
public class KafkaProducerService {

    @Value("${kafka.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final AtomicInteger counter = new AtomicInteger(0);

    public void sendMessage(String message) {
        int partition = counter.getAndIncrement() % 3; // assuming 3 partitions
        kafkaTemplate.send(topicName, partition, null, message);
    }

}

```

![image 3](106./.jpg)