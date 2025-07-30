package com.chuwa.demo.repository;

import com.chuwa.demo.entity.ConsumedMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumedMessageRepository extends JpaRepository<ConsumedMessage, Long> {

    List<ConsumedMessage> findByTopicAndPartitionIdAndOffsetValue(String topic, Integer partitionId, Long offsetValue);

    List<ConsumedMessage> findByMessageKey(String messageKey);

    List<ConsumedMessage> findByTopicOrderByCreatedAtDesc(String topic);
}
