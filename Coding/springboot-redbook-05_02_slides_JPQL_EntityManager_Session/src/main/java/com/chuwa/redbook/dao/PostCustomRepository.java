package com.chuwa.redbook.dao;

import com.chuwa.redbook.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Custom repository demonstrating Spring Data JPA naming conventions,
 * JPQL queries, and native SQL queries.
 */
@Repository
public interface PostCustomRepository extends JpaRepository<Post, Long> {
    
    /**
     * Find a post by exact title match.
     * Spring Data JPA will generate: SELECT * FROM posts WHERE title = ?
     */
    Post findByTitle(String title);
    
    /**
     * Find posts where description contains the given keyword.
     * Generated query: SELECT * FROM posts WHERE description LIKE %keyword%
     */
    List<Post> findByDescriptionContaining(String keyword);
    
    /**
     * Combine multiple properties in query method name.
     * Generated query: SELECT * FROM posts WHERE title = ? AND description = ?
     */
    List<Post> findByTitleAndDescription(String title, String description);
    
    // ========== JPQL Queries ==========
    
    /**
     * JPQL Query: Find posts where title length is greater than specified value.
     * Uses entity name 'Post' and property names, not table/column names.
     */
    @Query("SELECT p FROM Post p WHERE LENGTH(p.title) > :minLength ORDER BY p.createDateTime DESC")
    List<Post> findPostsWithTitleLongerThan(@Param("minLength") int minLength);
    
    /**
     * JPQL Query: Search posts by keyword in title or description.
     * Demonstrates JPQL string functions and OR conditions.
     */
    @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Post> searchPostsByKeywordJPQL(@Param("keyword") String keyword);
    
    // ========== Native SQL Queries ==========
    
    /**
     * Native SQL Query: Get post statistics by content length ranges.
     * Uses actual table and column names from database.
     */
    @Query(value = "SELECT " +
                   "CASE " +
                   "  WHEN LENGTH(content) < 50 THEN 'Short' " +
                   "  WHEN LENGTH(content) < 100 THEN 'Medium' " +
                   "  ELSE 'Long' " +
                   "END as content_category, " +
                   "COUNT(*) as post_count " +
                   "FROM posts " +
                   "GROUP BY content_category " +
                   "ORDER BY post_count DESC", 
           nativeQuery = true)
    List<Object[]> getPostStatsByContentLength();
    
    /**
     * Native SQL Query: Find posts created in the last N days.
     * Uses H2 database-compatible date functions.
     */
    @Query(value = "SELECT * FROM posts " +
                   "WHERE create_date_time >= DATEADD('DAY', -:days, CURRENT_TIMESTAMP) " +
                   "ORDER BY create_date_time DESC", 
           nativeQuery = true)
    List<Post> findRecentPostsNativeSQL(@Param("days") int days);
    
    /**
     * Example of invalid method name that would cause runtime error.
     * Uncommenting this will cause: "No property 'titlee' found for type 'Post'"
     */
    // Post findByTitlee(String title);  // Invalid - would cause error
}
