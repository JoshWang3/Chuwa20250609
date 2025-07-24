package com.example.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {
    
    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @KafkaListener(topics = "test-topic", groupId = "consumer-group-1", 
                   containerFactory = "kafkaListenerContainerFactory")
    public void consumer1(ConsumerRecord<String, String> record, 
                         @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                         @Header(KafkaHeaders.OFFSET) long offset,
                         Acknowledgment acknowledgment) {
        
        logger.info("Consumer-1 received message: '{}' from partition: {} with offset: {}", 
                   record.value(), partition, offset);
        
        // Simulate processing time
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Manual acknowledgment
        acknowledgment.acknowledge();
        logger.info("Consumer-1 acknowledged message with offset: {}", offset);
    }

    @KafkaListener(topics = "test-topic", groupId = "consumer-group-1", 
                   containerFactory = "kafkaListenerContainerFactory")
    public void consumer2(ConsumerRecord<String, String> record, 
                         @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                         @Header(KafkaHeaders.OFFSET) long offset,
                         Acknowledgment acknowledgment) {
        
        logger.info("Consumer-2 received message: '{}' from partition: {} with offset: {}", 
                   record.value(), partition, offset);
        
        // Simulate processing time
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Manual acknowledgment
        acknowledgment.acknowledge();
        logger.info("Consumer-2 acknowledged message with offset: {}", offset);
    }

    @KafkaListener(topics = "test-topic", groupId = "consumer-group-1", 
                   containerFactory = "kafkaListenerContainerFactory")
    public void consumer3(ConsumerRecord<String, String> record, 
                         @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                         @Header(KafkaHeaders.OFFSET) long offset,
                         Acknowledgment acknowledgment) {
        
        logger.info("Consumer-3 received message: '{}' from partition: {} with offset: {}", 
                   record.value(), partition, offset);
        
        // Simulate processing time
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Manual acknowledgment
        acknowledgment.acknowledge();
        logger.info("Consumer-3 acknowledged message with offset: {}", offset);
    }
}