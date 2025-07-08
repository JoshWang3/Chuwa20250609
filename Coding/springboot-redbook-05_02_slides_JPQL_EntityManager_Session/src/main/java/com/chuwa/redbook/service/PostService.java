package com.chuwa.redbook.service;

import com.chuwa.redbook.payload.PostDto;
import com.chuwa.redbook.payload.PostStatsDto;

import java.util.List;

/**
 * @author b1go
 * @date 8/22/22 6:51 PM
 */
public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPost();

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);

    List<PostDto> getAllPostWithJPQL();
    PostDto getPostByIdJPQLIndexParameter(Long id, String title);
    PostDto getPostByIdJPQLNamedParameter(Long id, String title);
    PostDto getPostByIdSQLIndexParameter(Long id, String title);
    PostDto getPostByIdSQLNamedParameter(Long id, String title);
    
    // New methods for JPQL and Native SQL queries
    List<PostDto> findPostsWithTitleLongerThan(int minLength);
    List<PostDto> searchPostsByKeywordJPQL(String keyword);
    List<PostStatsDto> getPostStatsByContentLength();
    List<PostDto> findRecentPostsNativeSQL(int days);
}
