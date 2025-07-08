package com.chuwa.redbook.controller;

import com.chuwa.redbook.dao.PostCustomRepository;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.payload.PostDto;
import com.chuwa.redbook.payload.PostStatsDto;
import com.chuwa.redbook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for testing custom JPA repository methods,
 * JPQL queries, and native SQL queries.
 */
@RestController
@RequestMapping("/api/v1/posts/custom")
public class PostCustomController {

    @Autowired
    private PostCustomRepository postCustomRepository;
    
    @Autowired
    private PostService postService;

    // ========== Spring Data JPA Method Name Queries ==========
    
    /**
     * Get post by exact title match.
     * GET /api/v1/posts/custom/title/{title}
     */
    @GetMapping("/title/{title}")
    public Post getPostByTitle(@PathVariable String title) {
        return postCustomRepository.findByTitle(title);
    }

    /**
     * Search posts by keyword in description.
     * GET /api/v1/posts/custom/search?keyword=Spring
     */
    @GetMapping("/search")
    public List<Post> searchPostsByKeyword(@RequestParam String keyword) {
        return postCustomRepository.findByDescriptionContaining(keyword);
    }

    /**
     * Find posts matching both title and description.
     * GET /api/v1/posts/custom/advanced?title=xxx&description=yyy
     */
    @GetMapping("/advanced")
    public List<Post> getPostsByTitleAndDescription(
            @RequestParam String title, 
            @RequestParam String description) {
        return postCustomRepository.findByTitleAndDescription(title, description);
    }
    
    // ========== JPQL Query Endpoints ==========
    
    /**
     * JPQL: Find posts with title longer than specified length.
     * GET /api/v1/posts/custom/jpql/title-length?minLength=10
     */
    @GetMapping("/jpql/title-length")
    public List<PostDto> findPostsWithLongTitles(@RequestParam(defaultValue = "10") int minLength) {
        return postService.findPostsWithTitleLongerThan(minLength);
    }
    
    /**
     * JPQL: Search posts by keyword in title or description (case-insensitive).
     * GET /api/v1/posts/custom/jpql/search?keyword=spring
     */
    @GetMapping("/jpql/search")
    public List<PostDto> searchPostsJPQL(@RequestParam String keyword) {
        return postService.searchPostsByKeywordJPQL(keyword);
    }
    
    // ========== Native SQL Query Endpoints ==========
    
    /**
     * Native SQL: Get post statistics by content length categories.
     * GET /api/v1/posts/custom/sql/stats
     */
    @GetMapping("/sql/stats")
    public List<PostStatsDto> getPostStatistics() {
        return postService.getPostStatsByContentLength();
    }
    
    /**
     * Native SQL: Find posts created in the last N days.
     * GET /api/v1/posts/custom/sql/recent?days=7
     */
    @GetMapping("/sql/recent")
    public List<PostDto> getRecentPosts(@RequestParam(defaultValue = "7") int days) {
        return postService.findRecentPostsNativeSQL(days);
    }
}
