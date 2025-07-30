package com.chuwa.demo.service;

import com.chuwa.demo.entity.KafkaMessage;
import com.chuwa.demo.repository.KafkaMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class AtLeastOnceConsumerService {
    
    private static final Logger log = LoggerFactory.getLogger(AtLeastOnceConsumerService.class);
    
    @Autowired
    private KafkaMessageRepository messageRepository;
    
    @Value("${spring.kafka.consumer.group-id}")
    private String consumerGroupId;
    
    @Value("${kafka.topic.name}")
    private String topic;
    
    @KafkaListener(
        topics = "${kafka.topic.name}", 
        groupId = "at-least-once-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    @Transactional
    public void consumeAtLeastOnce(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String receivedTopic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset,
            Acknowledgment acknowledgment) {
        
        log.info("AT-LEAST-ONCE: Processing message with key: {}", key);
        
        try {
            KafkaMessage kafkaMessage = new KafkaMessage();
            kafkaMessage.setMessageKey(key);
            kafkaMessage.setMessageContent(message);
            kafkaMessage.setTopic(receivedTopic);
            kafkaMessage.setPartitionId(partition);
            kafkaMessage.setOffsetValue(offset);
            kafkaMessage.setConsumerGroup("at-least-once-group");
            kafkaMessage.setProcessedAt(LocalDateTime.now());
            kafkaMessage.setDeliveryType("AT_LEAST_ONCE");
            kafkaMessage.setStatus("PROCESSING");
            
            // Check if message already exists (for idempotency)
            if (messageRepository.existsByMessageKey(key)) {
                log.warn("AT-LEAST-ONCE: Duplicate message detected with key: {}, skipping processing", key);
                kafkaMessage.setStatus("DUPLICATE");
                messageRepository.save(kafkaMessage);
                acknowledgment.acknowledge();
                return;
            }
            
            // Simulate processing logic
            processMessage(message, key);
            
            kafkaMessage.setStatus("COMPLETED");
            messageRepository.save(kafkaMessage);
            
            // Commit offset only after successful processing and persistence
            acknowledgment.acknowledge();
            
            log.info("AT-LEAST-ONCE: Successfully processed and committed message with key: {}", key);
            
        } catch (Exception e) {
            log.error("AT-LEAST-ONCE: Error processing message with key: {}, error: {}", key, e.getMessage());
            
            // Save failed message for retry logic
            KafkaMessage failedMessage = new KafkaMessage();
            failedMessage.setMessageKey(key + "_failed_" + System.currentTimeMillis());
            failedMessage.setMessageContent(message);
            failedMessage.setTopic(receivedTopic);
            failedMessage.setPartitionId(partition);
            failedMessage.setOffsetValue(offset);
            failedMessage.setConsumerGroup("at-least-once-group");
            failedMessage.setProcessedAt(LocalDateTime.now());
            failedMessage.setDeliveryType("AT_LEAST_ONCE");
            failedMessage.setStatus("FAILED");
            failedMessage.setRetryCount(1);
            
            try {
                messageRepository.save(failedMessage);
            } catch (Exception dbException) {
                log.error("Failed to save error message: {}", dbException.getMessage());
            }
            
            // Don't acknowledge - message will be redelivered
            throw e;
        }
    }
    
    private void processMessage(String message, String key) {
        // Simulate business logic processing
        log.info("AT-LEAST-ONCE: Processing business logic for message: {} with key: {}", message, key);
        
        // Simulate potential failure (uncomment to test retry behavior)
        // if (Math.random() < 0.3) {
        //     throw new RuntimeException("Simulated processing failure");
        // }
        
        try {
            Thread.sleep(100); // Simulate processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}