package com.chuwa.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message_idempotency",
        uniqueConstraints = @UniqueConstraint(columnNames = {"topic", "partition_id", "offset_value"}))
public class MessageIdempotency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_key", nullable = false)
    private String messageKey;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "partition_id", nullable = false)
    private Integer partitionId;

    @Column(name = "offset_value", nullable = false)
    private Long offsetValue;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    @PrePersist
    protected void onCreate() {
        processedAt = LocalDateTime.now();
    }

    // Constructors
    public MessageIdempotency() {}

    public MessageIdempotency(String messageKey, String topic, Integer partitionId, Long offsetValue) {
        this.messageKey = messageKey;
        this.topic = topic;
        this.partitionId = partitionId;
        this.offsetValue = offsetValue;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMessageKey() { return messageKey; }
    public void setMessageKey(String messageKey) { this.messageKey = messageKey; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public Integer getPartitionId() { return partitionId; }
    public void setPartitionId(Integer partitionId) { this.partitionId = partitionId; }

    public Long getOffsetValue() { return offsetValue; }
    public void setOffsetValue(Long offsetValue) { this.offsetValue = offsetValue; }

    public LocalDateTime getProcessedAt() { return processedAt; }
    public void setProcessedAt(LocalDateTime processedAt) { this.processedAt = processedAt; }
}