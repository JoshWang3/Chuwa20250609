package com.chuwa.redbook.controller;

import com.chuwa.redbook.dao.PostCustomRepository;
import com.chuwa.redbook.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for testing custom JPA repository methods.
 */
@RestController
@RequestMapping("/api/v1/posts/custom")
public class PostCustomController {

    @Autowired
    private PostCustomRepository postCustomRepository;

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
}
