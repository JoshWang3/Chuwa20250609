package com.chuwa.redbook.service.impl;

import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.*;
import com.chuwa.redbook.payload.PostDto;
import com.chuwa.redbook.payload.PostResponse;
import com.chuwa.redbook.service.PostService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author b1go
 * @date 8/22/22 6:56 PM
 */
@Service
public class PostServiceImpl implements PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private PostRepository postRepository;

    /**
     * use this modelMapper to replace the mapToDto, mapToEntity methods.
     */
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto) {
        logger.info("Service: Creating post with title: {}", postDto.getTitle());
        
        // Regex validation checks
        logger.debug("Validating post fields for title: {}", postDto.getTitle());
        validatePostTitle(postDto.getTitle());
        validatePostDescription(postDto.getDescription());
        validatePostContent(postDto.getContent());
        logger.debug("Validation completed successfully for post: {}", postDto.getTitle());
        
        try {
            // covert DTO to Entity
            Post post = modelMapper.map(postDto, Post.class);
            logger.debug("Mapped DTO to entity for post: {}", postDto.getTitle());

            // 调用Dao的save 方法，将entity的数据存储到数据库MySQL
            // save()会返回存储在数据库中的数据
            Post savedPost = postRepository.save(post);
            logger.info("Successfully saved post to database with ID: {}", savedPost.getId());

            // 将save() 返回的数据转换成controller/前端 需要的数据，然后return给controller
            PostDto result = modelMapper.map(savedPost, PostDto.class);
            logger.debug("Mapped entity back to DTO for post ID: {}", savedPost.getId());
            return result;
        } catch (Exception e) {
            logger.error("Failed to create post: {}", e.getMessage(), e);
            throw new InternalServerException("Failed to create post: " + e.getMessage());
        }
    }

    /**
     * 此处练习了lambda， stream API
     * @return
     */
    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    /**
     * 此处顺便练习Optional
     * @param id
     * @return
     */
    @Override
    public PostDto getPostById(long id) {
        // Validate ID
        if (id <= 0) {
            throw new ValidationException("Post ID must be greater than 0");
        }
        
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        //  Question, why do we need to find it out firstly?
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatePost = postRepository.save(post);
        return modelMapper.map(updatePost, PostDto.class);
    }

    @Override
    public void deletePostById(long id) {
        //  Question, why do we need to find it out firstly?
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create pageable instance

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
//        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Post> pagePosts = postRepository.findAll(pageRequest);

        // get content for page abject
        List<Post> posts = pagePosts.getContent();
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNo(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLast(pagePosts.isLast());
        return postResponse;
    }

    // Regex validation methods
    private void validatePostTitle(String title) {
        if (title == null || !title.matches("^[a-zA-Z0-9\\s\\-_.,!?'\"()]{3,100}$")) {
            throw new ValidationException("Title must be 3-100 characters, alphanumeric with basic punctuation");
        }
    }

    private void validatePostDescription(String description) {
        if (description == null || !description.matches("^[a-zA-Z0-9\\s\\-_.,!?'\"()\\n\\r]{10,500}$")) {
            throw new ValidationException("Description must be 10-500 characters, no special symbols");
        }
    }

    private void validatePostContent(String content) {
        if (content == null || !content.matches("^[a-zA-Z0-9\\s\\-_.,!?'\"()\\n\\r@#$%&*+=/<>{}\\[\\]]{20,5000}$")) {
            throw new ValidationException("Content must be 20-5000 characters, standard text allowed");
        }
    }
}
