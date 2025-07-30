package com.chuwa.demo.repository;

import com.chuwa.demo.entity.KafkaMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface KafkaMessageRepository extends JpaRepository<KafkaMessage, Long> {
    
    Optional<KafkaMessage> findByMessageKey(String messageKey);
    
    boolean existsByMessageKey(String messageKey);
    
    @Query("SELECT km FROM KafkaMessage km WHERE km.deliveryType = ?1 ORDER BY km.processedAt DESC")
    List<KafkaMessage> findByDeliveryTypeOrderByProcessedAtDesc(String deliveryType);
    
    @Query("SELECT COUNT(km) FROM KafkaMessage km WHERE km.deliveryType = ?1")
    Long countByDeliveryType(String deliveryType);
    
    List<KafkaMessage> findByStatusOrderByProcessedAtDesc(String status);
}