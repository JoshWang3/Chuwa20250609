package com.chuwa.demo.service;

import com.chuwa.demo.entity.KafkaMessage;
import com.chuwa.demo.repository.KafkaMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class AtMostOnceConsumerService {
    
    private static final Logger log = LoggerFactory.getLogger(AtMostOnceConsumerService.class);
    
    @Autowired
    private KafkaMessageRepository messageRepository;
    
    @Value("${spring.kafka.consumer.group-id}")
    private String consumerGroupId;
    
    @Value("${kafka.topic.name}")
    private String topic;
    
    @KafkaListener(
        topics = "${kafka.topic.name}", 
        groupId = "at-most-once-group",
        containerFactory = "kafkaListenerContainerFactory"
    )
    @Transactional
    public void consumeAtMostOnce(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String receivedTopic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset,
            Acknowledgment acknowledgment) {
        
        log.info("AT-MOST-ONCE: Received message with key: {}", key);
        
        // Acknowledge immediately to ensure at-most-once delivery
        acknowledgment.acknowledge();
        log.info("AT-MOST-ONCE: Acknowledged message with key: {} before processing", key);
        
        try {
            KafkaMessage kafkaMessage = new KafkaMessage();
            kafkaMessage.setMessageKey(key);
            kafkaMessage.setMessageContent(message);
            kafkaMessage.setTopic(receivedTopic);
            kafkaMessage.setPartitionId(partition);
            kafkaMessage.setOffsetValue(offset);
            kafkaMessage.setConsumerGroup("at-most-once-group");
            kafkaMessage.setProcessedAt(LocalDateTime.now());
            kafkaMessage.setDeliveryType("AT_MOST_ONCE");
            kafkaMessage.setStatus("PROCESSING");
            
            // Save initial record
            messageRepository.save(kafkaMessage);
            
            // Process the message
            processMessage(message, key);
            
            // Update status to completed
            kafkaMessage.setStatus("COMPLETED");
            messageRepository.save(kafkaMessage);
            
            log.info("AT-MOST-ONCE: Successfully processed message with key: {}", key);
            
        } catch (Exception e) {
            log.error("AT-MOST-ONCE: Error processing message with key: {}, error: {}", key, e.getMessage());
            
            // Save failed message - but offset is already committed, so message is lost
            try {
                KafkaMessage failedMessage = new KafkaMessage();
                failedMessage.setMessageKey(key + "_failed_" + System.currentTimeMillis());
                failedMessage.setMessageContent(message);
                failedMessage.setTopic(receivedTopic);
                failedMessage.setPartitionId(partition);
                failedMessage.setOffsetValue(offset);
                failedMessage.setConsumerGroup("at-most-once-group");
                failedMessage.setProcessedAt(LocalDateTime.now());
                failedMessage.setDeliveryType("AT_MOST_ONCE");
                failedMessage.setStatus("FAILED");
                failedMessage.setRetryCount(0); // No retry in at-most-once
                
                messageRepository.save(failedMessage);
                log.warn("AT-MOST-ONCE: Message with key: {} failed but will not be retried (at-most-once semantics)", key);
                
            } catch (Exception dbException) {
                log.error("AT-MOST-ONCE: Failed to save error record: {}", dbException.getMessage());
            }
        }
    }
    
    private void processMessage(String message, String key) {
        // Simulate business logic processing
        log.info("AT-MOST-ONCE: Processing business logic for message: {} with key: {}", message, key);
        
        // Simulate potential failure (uncomment to test failure behavior)
        // if (Math.random() < 0.2) {
        //     throw new RuntimeException("Simulated processing failure");
        // }
        
        try {
            Thread.sleep(50); // Simulate processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}