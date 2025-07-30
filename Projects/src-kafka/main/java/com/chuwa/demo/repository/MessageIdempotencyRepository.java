
package com.chuwa.demo.repository;

import com.chuwa.demo.entity.MessageIdempotency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageIdempotencyRepository extends JpaRepository<MessageIdempotency, Long> {

    Optional<MessageIdempotency> findByTopicAndPartitionIdAndOffsetValue(String topic, Integer partitionId, Long offsetValue);

    boolean existsByTopicAndPartitionIdAndOffsetValue(String topic, Integer partitionId, Long offsetValue);
}