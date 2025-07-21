package com.chuwa.redbook.service.impl;

import com.chuwa.redbook.dao.CommentRepository;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.BlogAPIException;
import com.chuwa.redbook.payload.CommentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock(name = "modelMapper")
    private ModelMapper mockedModelMapper;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Post post;
    private Comment comment;
    private CommentDto dto;

    @BeforeEach
    void setup() {
        post = new Post();
        post.setId(1L);

        comment = new Comment();
        comment.setId(10L);
        comment.setPost(post);
        comment.setBody("new comment");

        dto = new CommentDto();
        dto.setId(10L);
        dto.setBody("new comment");
    }

    @Test
    void testCreateComment_success() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(mockedModelMapper.map(dto, Comment.class)).thenReturn(comment);
        when(commentRepository.save(any())).thenReturn(comment);
        when(mockedModelMapper.map(comment, CommentDto.class)).thenReturn(dto);

        CommentDto result = commentService.createComment(1L, dto);

        assertEquals("new comment", result.getBody());
    }

    @Test
    void testGetCommentsByPostId() {
        when(commentRepository.findByPostId(1L)).thenReturn(Arrays.asList(comment));
        when(mockedModelMapper.map(comment, CommentDto.class)).thenReturn(dto);

        List<CommentDto> result = commentService.getCommentsByPostId(1L);

        assertEquals(1, result.size());
    }

    @Test
    void testGetCommentById_valid() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(10L)).thenReturn(Optional.of(comment));
        when(mockedModelMapper.map(comment, CommentDto.class)).thenReturn(dto);

        CommentDto result = commentService.getCommentById(1L, 10L);
        assertEquals("new comment", result.getBody());
    }

    @Test
    void testGetCommentById_invalidPostMismatch() {
        Post another = new Post(); another.setId(2L);
        comment.setPost(another);

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(10L)).thenReturn(Optional.of(comment));

        BlogAPIException ex = assertThrows(BlogAPIException.class, () ->
                commentService.getCommentById(1L, 10L));

        assertEquals("Comment does not belong to post", ex.getMessage());
    }

    @Test
    void testUpdateComment() {
        // prepare
        String updatedBody = "UPDATED - " + comment.getBody();

        // updated request
        CommentDto requestDto = new CommentDto();
        requestDto.setName(comment.getName());
        requestDto.setEmail(comment.getEmail());
        requestDto.setBody(updatedBody);

        // comment
        Comment updatedComment = new Comment();
        updatedComment.setId(comment.getId());
        updatedComment.setPost(comment.getPost());
        updatedComment.setName(comment.getName());
        updatedComment.setEmail(comment.getEmail());
        updatedComment.setBody(updatedBody);

        // mock
        when(postRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(post));

        when(commentRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(comment));

        when(commentRepository.save(ArgumentMatchers.any(Comment.class)))
                .thenReturn(updatedComment);

        when(mockedModelMapper.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class)))
                .thenReturn(requestDto);

        CommentDto responseDto = commentService.updateComment(1L, 10L, requestDto);

        assertNotNull(responseDto);
        assertEquals(updatedBody, responseDto.getBody());
    }



    @Test
    void testDeleteComment_valid() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(10L)).thenReturn(Optional.of(comment));

        commentService.deleteComment(1L, 10L);
        verify(commentRepository, times(1)).delete(comment);
    }

    @Test
    void testCommentServiceMapperUtil() {
        Comment c = new Comment();
        c.setId(123L);
        c.setBody("test");

        CommentDto dto = CommentServiceImpl.commentServiceMapperUtil(c);
        assertEquals(123L, dto.getId());
        assertEquals("test", dto.getBody());
    }
}
