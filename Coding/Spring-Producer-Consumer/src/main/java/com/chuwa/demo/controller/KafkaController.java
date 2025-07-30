package com.chuwa.demo.controller;

import com.chuwa.demo.entity.KafkaMessage;
import com.chuwa.demo.service.KafkaProducerService;
import com.chuwa.demo.service.MessageStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class KafkaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;
    
    @Autowired
    private MessageStatsService messageStatsService;

    @PostMapping("/publish")
    public String publishMessage(@RequestParam("key") String key, @RequestParam("message") String message) {
        kafkaProducerService.sendMessage(key, message);
        return "Message published successfully with key: " + key;
    }
    
    @PostMapping("/publish-batch")
    public String publishBatchMessages(@RequestParam(value = "count", defaultValue = "10") int count) {
        for (int i = 1; i <= count; i++) {
            String key = "batch-key-" + i;
            String message = "Batch message " + i + " - Testing delivery guarantees";
            kafkaProducerService.sendMessage(key, message);
        }
        return "Published " + count + " messages successfully";
    }
    
    @GetMapping("/stats")
    public Map<String, Object> getMessageStats() {
        return messageStatsService.getDeliveryStats();
    }
    
    @GetMapping("/messages")
    public List<KafkaMessage> getAllMessages() {
        return messageStatsService.getAllMessages();
    }
    
    @DeleteMapping("/clear")
    public String clearAllMessages() {
        messageStatsService.clearAllMessages();
        return "All messages cleared successfully";
    }
    
    @GetMapping("/test-duplicate")
    public String testDuplicateMessage() {
        String key = "duplicate-test-key";
        String message = "This is a duplicate test message";
        
        // Send same message twice to test idempotency
        kafkaProducerService.sendMessage(key, message);
        kafkaProducerService.sendMessage(key, message);
        
        return "Sent duplicate messages with key: " + key;
    }
}
