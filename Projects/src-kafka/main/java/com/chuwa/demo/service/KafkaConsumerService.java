package com.chuwa.demo.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Autowired
    private MessagePersistenceService messagePersistenceService;

    @Value("${spring.kafka.consumer.group-id}")
    private String consumerGroupId;

    /**
     * At-Least-Once delivery consumer
     * Manual acknowledgment ensures message is processed before committing offset
     * This guarantees delivery but may result in duplicates on failure/retry
     */
    @KafkaListener(topics = "chuwa-yyds",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenAtLeastOnce(
            ConsumerRecord<String, String> record,
            Acknowledgment acknowledgment) {

        try {
            String topic = record.topic();
            int partition = record.partition();
            long offset = record.offset();

            logger.info("At-Least-Once Consumer - Received message: key={}, value={}, topic={}, partition={}, offset={}",
                    record.key(), record.value(), topic, partition, offset);

            // Process and save the message
            messagePersistenceService.saveMessageAtLeastOnce(
                    record.key(),
                    record.value(),
                    topic,
                    partition,
                    offset,
                    consumerGroupId
            );

            // Manually acknowledge after successful processing
            acknowledgment.acknowledge();
            logger.info("At-Least-Once Consumer - Message processed and acknowledged: key={}", record.key());

        } catch (Exception e) {
            logger.error("At-Least-Once Consumer - Error processing message: key={}, error={}",
                    record.key(), e.getMessage(), e);
            // Don't acknowledge on error - message will be redelivered
            throw e;
        }
    }

    /**
     * At-Most-Once delivery consumer
     * Auto-commit with idempotency check ensures no duplicates but may lose messages on failure
     */
    @KafkaListener(topics = "chuwa-yyds",
            containerFactory = "atMostOnceListenerContainerFactory",
            groupId = "consumer_group_1-at-most-once")
    public void listenAtMostOnce(
            ConsumerRecord<String, String> record) {

        try {
            String topic = record.topic();
            int partition = record.partition();
            long offset = record.offset();

            logger.info("At-Most-Once Consumer - Received message: key={}, value={}, topic={}, partition={}, offset={}",
                    record.key(), record.value(), topic, partition, offset);

            // Process and save the message with idempotency check
            boolean saved = messagePersistenceService.saveMessageAtMostOnce(
                    record.key(),
                    record.value(),
                    topic,
                    partition,
                    offset,
                    consumerGroupId + "-at-most-once"
            );

            if (saved) {
                logger.info("At-Most-Once Consumer - Message processed successfully: key={}", record.key());
            } else {
                logger.info("At-Most-Once Consumer - Message skipped (already processed): key={}", record.key());
            }

        } catch (Exception e) {
            logger.error("At-Most-Once Consumer - Error processing message: key={}, error={}",
                    record.key(), e.getMessage(), e);
            // With auto-commit, offset is already committed, so message is lost on error
        }
    }
}