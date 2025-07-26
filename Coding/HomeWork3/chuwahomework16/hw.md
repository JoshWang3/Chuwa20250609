### 1. N partitions >= < M consumers 
    when N >= M no consumers will be idle but maybe resources waste 
        each consumer reeived one or more parititons
        some may handle multiple partions
        allows max parelle works
    when N < M consumer could be idle causing lags
        each partition will still be consumed by only one consumer with a group
        may result in under utilizaiton of resources


### 2. topics logic in broker 
    broker is sort of server that contains different topics. topic is what logic is like request of 
    payment, commnents, etc. a topic split into partitions and distributed across brokers in a kafka cluster
    kafka assigns each partitions to one leader


### 3. push or pull
    consumer pull messages: from topics records are pull by consumers.


### 4. avoid duplicate consumption
    🔹 1. Commit Offset After Successful Processing
 
    while (true) {
    val records = consumer.poll(Duration.ofMillis(100))
    for (record in records) {
    process(record) // Your business logic
    consumer.commitSync() // Only after success
    }
    }
    ✅ Guarantees at-least-once, but avoids duplicates if processing is atomic and deterministic.
    
    🔹 2. Use Idempotent Processing
    Ensure your business logic can safely reprocess the same message:
    
    Use unique transaction IDs or message IDs.
    
    Store them in DB or cache to deduplicate.

    INSERT IGNORE INTO orders(id, ...) VALUES(...)
    or

    if (!processedCache.contains(record.key)) {
    process(record)
    processedCache.add(record.key)
    }
    🔹 3. Exactly-Once Processing with Kafka Streams
    Kafka Streams provides exactly-once semantics (EOS):

    val props = Properties()
    props[StreamsConfig.PROCESSING_GUARANTEE_CONFIG] = StreamsConfig.EXACTLY_ONCE_V2
    ✅ Works best when using Kafka Streams + Kafka-backed state stores.
    
    ⚠️ More expensive and complex.
    
    🔹 4. Transactional Producers + Consumers
    Kafka allows end-to-end exactly-once if both:
    
    Producer uses transactions
    
    Consumer is configured with read_committed isolation
    

    producer.initTransactions()
    producer.beginTransaction()
    producer.send(...)
    producer.sendOffsetsToTransaction(...)
    producer.commitTransaction()

    🔹 5. Avoid Auto-Commit
    Disable automatic offset commits:
    
    kotlin
    Copy
    Edit
    props["enable.auto.commit"] = "false"
    Manually commit after processing.
    
    🔹 6. Handle Rebalancing Carefully
    Use consumer.pause() and consumer.resume() during rebalances.

    Or handle in-flight message completion before shutdown using ConsumerRebalanceListener.



### 5. consumers down

    data will not loss. because kafka is guranteed delivery 


### 6. entire consumer group down
    data will not loss. 


### 7. consumer lag and how to resolve 
    consumer lag is when end offset is more than current offset.
    end offset (latest message in partition) 
     >
    current offset (where consumer is reading from)

    increse concurrency 
    batch processing
    parallelize processing 
    Asynchronous I/O: non-blocking DB/writing operations. 

### 8. kafka tracks message delivery
    using offsets which managed by the consumer group 

    🔹 1. Each partition is a log
    Messages are stored in order, each with a unique offset (e.g., 0, 1, 2…).
    
    🔹 2. Consumers pull messages
    A consumer reads from an offset.
    
    After processing, it commits the offset to Kafka (or external store).
    
    🔹 3. Kafka stores committed offsets
    In the internal topic: __consumer_offsets
    
    This is how Kafka knows how far each consumer group has progressed.




| Offset Strategy                        | Guarantee Type                   | Behavior                                                       |
| -------------------------------------- | -------------------------------- | -------------------------------------------------------------- |
| **After processing** (`manual commit`) | ✅ **At-least-once**              | If consumer crashes before commit → message reprocessed        |
| **Before processing** (`auto commit`)  | ⚠️ **At-most-once**              | If crash occurs → message lost                                 |
| **Kafka Streams / Transactions**       | ✅ **Exactly-once** (with config) | Commit offset with processing result in a single atomic action |





### 9. kafka vs RabbitMQ vs MySql
    kafka is distributed event streaming not only message queue like RabbitMQ.
    kafka is pull based and message will be retained after consume 
    message queue is push based and message is fire and forget
    SQL has schema but kafka is schemaless 



### 10. 

    2. after increase consumers, the original partition is not enough -> lag happens 

    1. 3 consumers in single group
![Screenshot 2025-07-25 at 4.22.35 PM.png](Screenshot%202025-07-25%20at%204.22.35%E2%80%AFPM.png)

    
    2. each consumer troup is consuing message as expected
![Screenshot 2025-07-25 at 6.58.43 PM.png](Screenshot%202025-07-25%20at%206.58.43%E2%80%AFPM.png)



