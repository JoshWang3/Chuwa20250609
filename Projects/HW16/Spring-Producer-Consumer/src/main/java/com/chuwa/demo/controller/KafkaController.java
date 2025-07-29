package com.chuwa.demo.controller;

import com.chuwa.demo.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/publish")
    public String publishMessage(@RequestParam(value = "key", required = false) String key, @RequestParam("message") String message) {
        if (key == null || key.isEmpty()) {
            kafkaProducerService.sendMessage(message);
        }
        else{
            kafkaProducerService.sendMessage(key, message);
        }
        return "Message published successfully";
    }
}
