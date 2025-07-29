package com.chuwa.demo.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerService {
    @Value("${spring.kafka.consumer.group-id}")
    private String consumerGroupId;
    @Value("${kafka.topic.name}")
    private String topic;

    @Value("${spring.kafka.consumer.group-id-2}")
    private String consumerGroupId2;

    //How do kafka consumers "consume" messages from broker
    //Kafka consumer: poll
    //Read the topic-partion on assigned broker, by offset

    //What will Kafka consumer do when above operation failed
    //If above operation fails: indicate assigned broker is down
    //The consumer will read from the new leader
    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenGroupFoo(String message, Acknowledgment ack) {
        System.out.println("Received message: " + message + " from group: " + consumerGroupId + " with topic: " + topic);
        // {"messageKey":1234 //idempotency key
        // }
//        try {
//            //process and persist message
//        } catch (Exception e) {
//            //send to dead letter queue
//        }
        //commit to offset here
        //return
        ack.acknowledge();
    }
    @KafkaListener(
            topics   = "${kafka.topic.name}",
            groupId  = "${spring.kafka.consumer.group-id-2}",
            concurrency = "2"
    )
    public void listenGroupFoo2(String message,Acknowledgment ack) {
        System.out.println("Received message: " + message + " from group: " + consumerGroupId2 + " with topic: " + topic);
        ack.acknowledge();
    }
}
