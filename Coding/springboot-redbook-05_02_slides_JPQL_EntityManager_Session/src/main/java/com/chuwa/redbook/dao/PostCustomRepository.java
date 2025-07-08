package com.chuwa.redbook.dao;

import com.chuwa.redbook.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Custom repository demonstrating Spring Data JPA naming conventions
 * and query method creation from method names.
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
     * Example of invalid method name that would cause runtime error.
     * Uncommenting this will cause: "No property 'titlee' found for type 'Post'"
     * 
     * Try uncommenting and running ./mvnw compile to see the error.
     */
    // Post findByTitlee(String title);  // Invalid - would cause error
    
    /**
     * Combine multiple properties in query method name.
     * Generated query: SELECT * FROM posts WHERE title = ? AND description = ?
     */
    List<Post> findByTitleAndDescription(String title, String description);
}
