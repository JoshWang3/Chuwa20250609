package com.chuwa.demo.controller;

import com.chuwa.demo.entity.ConsumedMessage;
import com.chuwa.demo.service.KafkaProducerService;
import com.chuwa.demo.service.MessagePersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private MessagePersistenceService messagePersistenceService;

    @PostMapping("/publish")
    public String publishMessage(@RequestParam("key") String key, @RequestParam("message") String message) {
        kafkaProducerService.sendMessage(key, message);
        return "Message published successfully";
    }

    @GetMapping("/messages")
    public List<ConsumedMessage> getAllMessages() {
        return messagePersistenceService.getAllMessages();
    }

    @GetMapping("/messages/topic/{topic}")
    public List<ConsumedMessage> getMessagesByTopic(@PathVariable String topic) {
        return messagePersistenceService.getMessagesByTopic(topic);
    }

    @GetMapping("/messages/key/{key}")
    public List<ConsumedMessage> getMessagesByKey(@PathVariable String key) {
        return messagePersistenceService.getMessagesByKey(key);
    }
}