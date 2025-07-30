package com.chuwa.demo.service;

import com.chuwa.demo.entity.KafkaMessage;
import com.chuwa.demo.repository.KafkaMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class MessageStatsService {
    
    @Autowired
    private KafkaMessageRepository messageRepository;
    
    public Map<String, Object> getDeliveryStats() {
        Map<String, Object> stats = new HashMap<>();
        
        Long atLeastOnceCount = messageRepository.countByDeliveryType("AT_LEAST_ONCE");
        Long atMostOnceCount = messageRepository.countByDeliveryType("AT_MOST_ONCE");
        
        List<KafkaMessage> atLeastOnceMessages = messageRepository.findByDeliveryTypeOrderByProcessedAtDesc("AT_LEAST_ONCE");
        List<KafkaMessage> atMostOnceMessages = messageRepository.findByDeliveryTypeOrderByProcessedAtDesc("AT_MOST_ONCE");
        
        List<KafkaMessage> failedMessages = messageRepository.findByStatusOrderByProcessedAtDesc("FAILED");
        List<KafkaMessage> completedMessages = messageRepository.findByStatusOrderByProcessedAtDesc("COMPLETED");
        List<KafkaMessage> duplicateMessages = messageRepository.findByStatusOrderByProcessedAtDesc("DUPLICATE");
        
        stats.put("atLeastOnceCount", atLeastOnceCount);
        stats.put("atMostOnceCount", atMostOnceCount);
        stats.put("totalMessages", atLeastOnceCount + atMostOnceCount);
        stats.put("failedCount", failedMessages.size());
        stats.put("completedCount", completedMessages.size());
        stats.put("duplicateCount", duplicateMessages.size());
        
        stats.put("atLeastOnceMessages", atLeastOnceMessages);
        stats.put("atMostOnceMessages", atMostOnceMessages);
        stats.put("failedMessages", failedMessages);
        
        return stats;
    }
    
    public List<KafkaMessage> getAllMessages() {
        return messageRepository.findAll();
    }
    
    public void clearAllMessages() {
        messageRepository.deleteAll();
    }
}