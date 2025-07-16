package com.example.jpademo.controller;

import com.example.jpademo.model.Post;
import com.example.jpademo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @GetMapping("/search")
    public List<Post> searchPosts(@RequestParam String keyword) {
        return postRepository.findByTitleContainingIgnoreCase(keyword);
    }
}
