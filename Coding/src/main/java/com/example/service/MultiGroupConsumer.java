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
public class MultiGroupConsumer {
    
    private static final Logger logger = LoggerFactory.getLogger(MultiGroupConsumer.class);

    // Consumer Group 2 - with 2 consumers
    @KafkaListener(topics = "test-topic", groupId = "consumer-group-2")
    public void group2Consumer1(ConsumerRecord<String, String> record, 
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                               @Header(KafkaHeaders.OFFSET) long offset,
                               Acknowledgment acknowledgment) {
        
        logger.info("Group2-Consumer1 received: '{}' from partition: {} offset: {}", 
                   record.value(), partition, offset);
        if (acknowledgment != null) acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "test-topic", groupId = "consumer-group-2")
    public void group2Consumer2(ConsumerRecord<String, String> record, 
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                               @Header(KafkaHeaders.OFFSET) long offset,
                               Acknowledgment acknowledgment) {
        
        logger.info("Group2-Consumer2 received: '{}' from partition: {} offset: {}", 
                   record.value(), partition, offset);
        if (acknowledgment != null) acknowledgment.acknowledge();
    }

    // Consumer Group 3 - with 4 consumers
    @KafkaListener(topics = "test-topic", groupId = "consumer-group-3")
    public void group3Consumer1(ConsumerRecord<String, String> record, 
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                               @Header(KafkaHeaders.OFFSET) long offset) {
        
        logger.info("Group3-Consumer1 received: '{}' from partition: {} offset: {}", 
                   record.value(), partition, offset);
    }

    @KafkaListener(topics = "test-topic", groupId = "consumer-group-3")
    public void group3Consumer2(ConsumerRecord<String, String> record, 
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                               @Header(KafkaHeaders.OFFSET) long offset) {
        
        logger.info("Group3-Consumer2 received: '{}' from partition: {} offset: {}", 
                   record.value(), partition, offset);
    }

    @KafkaListener(topics = "test-topic", groupId = "consumer-group-3")
    public void group3Consumer3(ConsumerRecord<String, String> record, 
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                               @Header(KafkaHeaders.OFFSET) long offset) {
        
        logger.info("Group3-Consumer3 received: '{}' from partition: {} offset: {}", 
                   record.value(), partition, offset);
    }

    @KafkaListener(topics = "test-topic", groupId = "consumer-group-3")
    public void group3Consumer4(ConsumerRecord<String, String> record, 
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                               @Header(KafkaHeaders.OFFSET) long offset) {
        
        logger.info("Group3-Consumer4 received: '{}' from partition: {} offset: {}", 
                   record.value(), partition, offset);
    }
}