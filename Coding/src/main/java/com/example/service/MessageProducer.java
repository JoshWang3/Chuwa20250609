package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class MessageProducer {
    
    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    public void sendMessage(String topic, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Message sent successfully: '{}' to partition: {} with offset: {}", 
                           message, 
                           result.getRecordMetadata().partition(), 
                           result.getRecordMetadata().offset());
            }
            
            @Override
            public void onFailure(Throwable ex) {
                logger.error("Failed to send message: '{}'", message, ex);
            }
        });
    }
    
    public void sendMessageWithKey(String topic, String key, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, message);
        
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Message with key '{}' sent: '{}' to partition: {} with offset: {}", 
                           key, message, 
                           result.getRecordMetadata().partition(), 
                           result.getRecordMetadata().offset());
            }
            
            @Override
            public void onFailure(Throwable ex) {
                logger.error("Failed to send message with key '{}': '{}'", key, message, ex);
            }
        });
    }
}