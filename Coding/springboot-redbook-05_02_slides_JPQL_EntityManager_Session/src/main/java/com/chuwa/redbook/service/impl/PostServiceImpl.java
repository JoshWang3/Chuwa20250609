package com.chuwa.redbook.service.impl;

import com.chuwa.redbook.dao.PostCustomRepository;
import com.chuwa.redbook.dao.PostJPQLRepository;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.ResourceNotFoundException;
import com.chuwa.redbook.payload.PostDto;
import com.chuwa.redbook.payload.PostStatsDto;
import com.chuwa.redbook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author b1go
 * @date 8/22/22 6:56 PM
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    PostJPQLRepository postJPQLRepository;
    
    @Autowired
    private PostCustomRepository postCustomRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savedPost = postRepository.save(post);
        PostDto postResponse = mapToDTO(savedPost);
        return postResponse;
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos = posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatePost = postRepository.save(post);
        return mapToDTO(updatePost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> getAllPostWithJPQL() {
        return postJPQLRepository.getAllPostWithJPQL().stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostByIdJPQLIndexParameter(Long id, String title) {
        Post post = postRepository.getPostByIDOrTitleWithJPQLIndexParameters(id, title);
        return mapToDTO(post);
    }

    @Override
    public PostDto getPostByIdJPQLNamedParameter(Long id, String title) {
        Post post = postRepository.getPostByIDOrTitleWithJPQLNamedParameters(id, title);
        return mapToDTO(post);
    }

    @Override
    public PostDto getPostByIdSQLIndexParameter(Long id, String title) {
        Post post = postRepository.getPostByIDOrTitleWithSQLIndexParameters(id, title);
        return mapToDTO(post);
    }

    @Override
    public PostDto getPostByIdSQLNamedParameter(Long id, String title) {
        Post post = postRepository.getPostByIDOrTitleWithSQLNamedParameters(id, title);
        return mapToDTO(post);
    }
    
    // ========== New JPQL and Native SQL Query Methods ==========
    
    @Override
    public List<PostDto> findPostsWithTitleLongerThan(int minLength) {
        List<Post> posts = postCustomRepository.findPostsWithTitleLongerThan(minLength);
        return posts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public List<PostDto> searchPostsByKeywordJPQL(String keyword) {
        List<Post> posts = postCustomRepository.searchPostsByKeywordJPQL(keyword);
        return posts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public List<PostStatsDto> getPostStatsByContentLength() {
        List<Object[]> results = postCustomRepository.getPostStatsByContentLength();
        return results.stream()
                .map(result -> new PostStatsDto(
                    (String) result[0],  // content_category
                    ((Number) result[1]).longValue()  // post_count
                ))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<PostDto> findRecentPostsNativeSQL(int days) {
        List<Post> posts = postCustomRepository.findRecentPostsNativeSQL(days);
        return posts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private PostDto mapToDTO(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    private Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
