//package com.chuwa.demo.service;
//
//import com.chuwa.demo.entity.MessageEntity;
//import com.chuwa.demo.repository.MessageRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.stereotype.Service;
//import org.springframework.kafka.support.KafkaHeaders;
//
//import java.time.LocalDateTime;
//
//@Service
//public class AtLeastOnceConsumer {
//
//    @Autowired
//    private MessageRepository messageRepository;
//
//    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
//    public void listen(String message,
//                       @Header(KafkaHeaders.RECEIVED_KEY) String key,
//                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
//                       @Header(KafkaHeaders.GROUP_ID) String groupId) {
//
//        // Simulate saving the message (may happen more than once)
//        MessageEntity entity = new MessageEntity();
//        entity.setMessageKey(key);
//        entity.setMessageValue(message);
//        entity.setTopic(topic);
//        entity.setConsumerGroup(groupId);
//        entity.setReceivedAt(LocalDateTime.now());
//
//        messageRepository.save(entity); // Kafka commits offset after this
//        System.out.println("Saved (At-Least-Once): " + message);
//    }
//}
