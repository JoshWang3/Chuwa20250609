package com.chuwa.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consumed_messages")
public class ConsumedMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_key")
    private String messageKey;

    @Column(name = "message_value", nullable = false, columnDefinition = "TEXT")
    private String messageValue;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "partition_id")
    private Integer partitionId;

    @Column(name = "offset_value")
    private Long offsetValue;

    @Column(name = "consumer_group_id")
    private String consumerGroupId;

    @Column(name = "processing_status")
    private String processingStatus = "PROCESSED";

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Constructors
    public ConsumedMessage() {}

    public ConsumedMessage(String messageKey, String messageValue, String topic,
                           Integer partitionId, Long offsetValue, String consumerGroupId) {
        this.messageKey = messageKey;
        this.messageValue = messageValue;
        this.topic = topic;
        this.partitionId = partitionId;
        this.offsetValue = offsetValue;
        this.consumerGroupId = consumerGroupId;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMessageKey() { return messageKey; }
    public void setMessageKey(String messageKey) { this.messageKey = messageKey; }

    public String getMessageValue() { return messageValue; }
    public void setMessageValue(String messageValue) { this.messageValue = messageValue; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public Integer getPartitionId() { return partitionId; }
    public void setPartitionId(Integer partitionId) { this.partitionId = partitionId; }

    public Long getOffsetValue() { return offsetValue; }
    public void setOffsetValue(Long offsetValue) { this.offsetValue = offsetValue; }

    public String getConsumerGroupId() { return consumerGroupId; }
    public void setConsumerGroupId(String consumerGroupId) { this.consumerGroupId = consumerGroupId; }

    public String getProcessingStatus() { return processingStatus; }
    public void setProcessingStatus(String processingStatus) { this.processingStatus = processingStatus; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}