package com.chuwa.demo.service;

import com.chuwa.demo.entity.ConsumedMessage;
import com.chuwa.demo.entity.MessageIdempotency;
import com.chuwa.demo.repository.ConsumedMessageRepository;
import com.chuwa.demo.repository.MessageIdempotencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessagePersistenceService {

    private static final Logger logger = LoggerFactory.getLogger(MessagePersistenceService.class);

    @Autowired
    private ConsumedMessageRepository consumedMessageRepository;

    @Autowired
    private MessageIdempotencyRepository idempotencyRepository;

    /**
     * At-Least-Once delivery: Save message without checking for duplicates
     * This ensures every message is processed but may result in duplicates
     */
    @Transactional
    public void saveMessageAtLeastOnce(String messageKey, String messageValue, String topic,
                                       Integer partitionId, Long offsetValue, String consumerGroupId) {
        try {
            ConsumedMessage message = new ConsumedMessage(messageKey, messageValue, topic,
                    partitionId, offsetValue, consumerGroupId);
            consumedMessageRepository.save(message);
            logger.info("Message saved (At-Least-Once): key={}, topic={}, partition={}, offset={}",
                    messageKey, topic, partitionId, offsetValue);
        } catch (Exception e) {
            logger.error("Failed to save message (At-Least-Once): key={}, error={}", messageKey, e.getMessage());
            throw e;
        }
    }

    /**
     * At-Most-Once delivery: Save message only if not already processed
     * This prevents duplicates but may lose messages if processing fails after idempotency check
     */
    @Transactional
    public boolean saveMessageAtMostOnce(String messageKey, String messageValue, String topic,
                                         Integer partitionId, Long offsetValue, String consumerGroupId) {
        try {
            // Check if already processed
            if (idempotencyRepository.existsByTopicAndPartitionIdAndOffsetValue(topic, partitionId, offsetValue)) {
                logger.info("Message already processed (At-Most-Once): key={}, topic={}, partition={}, offset={}",
                        messageKey, topic, partitionId, offsetValue);
                return false;
            }

            // Save idempotency record first
            MessageIdempotency idempotency = new MessageIdempotency(messageKey, topic, partitionId, offsetValue);
            idempotencyRepository.save(idempotency);

            // Save the actual message
            ConsumedMessage message = new ConsumedMessage(messageKey, messageValue, topic,
                    partitionId, offsetValue, consumerGroupId);
            consumedMessageRepository.save(message);

            logger.info("Message saved (At-Most-Once): key={}, topic={}, partition={}, offset={}",
                    messageKey, topic, partitionId, offsetValue);
            return true;

        } catch (DataIntegrityViolationException e) {
            // Handle duplicate key violation (race condition)
            logger.warn("Duplicate message detected (At-Most-Once): key={}, topic={}, partition={}, offset={}",
                    messageKey, topic, partitionId, offsetValue);
            return false;
        } catch (Exception e) {
            logger.error("Failed to save message (At-Most-Once): key={}, error={}", messageKey, e.getMessage());
            throw e;
        }
    }

    /**
     * Get all messages for a specific topic
     */
    public List<ConsumedMessage> getMessagesByTopic(String topic) {
        return consumedMessageRepository.findByTopicOrderByCreatedAtDesc(topic);
    }

    /**
     * Get messages by key
     */
    public List<ConsumedMessage> getMessagesByKey(String messageKey) {
        return consumedMessageRepository.findByMessageKey(messageKey);
    }

    /**
     * Get all consumed messages
     */
    public List<ConsumedMessage> getAllMessages() {
        return consumedMessageRepository.findAll();
    }
}