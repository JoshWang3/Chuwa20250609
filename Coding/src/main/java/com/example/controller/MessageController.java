package com.example.controller;

import com.example.service.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    
    @Autowired
    private MessageProducer messageProducer;
    
    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        messageProducer.sendMessage("test-topic", message);
        return ResponseEntity.ok("Message sent: " + message);
    }
    
    @PostMapping("/send-with-key")
    public ResponseEntity<String> sendMessageWithKey(@RequestParam String key, 
                                                     @RequestParam String message) {
        messageProducer.sendMessageWithKey("test-topic", key, message);
        return ResponseEntity.ok("Message sent with key '" + key + "': " + message);
    }
    
    @PostMapping("/send-batch")
    public ResponseEntity<String> sendBatchMessages(@RequestParam int count) {
        for (int i = 1; i <= count; i++) {
            String message = "Batch message " + i + " - " + System.currentTimeMillis();
            messageProducer.sendMessage("test-topic", message);
        }
        return ResponseEntity.ok("Sent " + count + " messages");
    }
}