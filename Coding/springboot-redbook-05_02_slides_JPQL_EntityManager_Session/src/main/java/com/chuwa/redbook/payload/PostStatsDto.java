package com.chuwa.redbook.payload;

/**
 * DTO for post statistics results.
 */
public class PostStatsDto {
    private String contentCategory;
    private Long postCount;
    
    public PostStatsDto() {
    }
    
    public PostStatsDto(String contentCategory, Long postCount) {
        this.contentCategory = contentCategory;
        this.postCount = postCount;
    }
    
    public String getContentCategory() {
        return contentCategory;
    }
    
    public void setContentCategory(String contentCategory) {
        this.contentCategory = contentCategory;
    }
    
    public Long getPostCount() {
        return postCount;
    }
    
    public void setPostCount(Long postCount) {
        this.postCount = postCount;
    }
}
