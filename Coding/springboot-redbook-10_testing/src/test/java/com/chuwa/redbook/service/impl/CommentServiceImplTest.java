package com.chuwa.redbook.service.impl;

import com.chuwa.redbook.dao.CommentRepository;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.BlogAPIException;
import com.chuwa.redbook.exception.ResourceNotFoundException;
import com.chuwa.redbook.payload.CommentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Post testPost;
    private Comment testComment;
    private CommentDto testCommentDto;

    @BeforeEach
    void setUp() {
        // Initialize test data
        testPost = new Post();
        testPost.setId(1L);
        testPost.setTitle("Test Post");
        testPost.setDescription("Test Description");
        testPost.setContent("Test Content");

        testComment = new Comment();
        testComment.setId(1L);
        testComment.setName("John Doe");
        testComment.setEmail("john@example.com");
        testComment.setBody("Great post!");
        testComment.setPost(testPost);

        testCommentDto = new CommentDto();
        testCommentDto.setId(1L);
        testCommentDto.setName("John Doe");
        testCommentDto.setEmail("john@example.com");
        testCommentDto.setBody("Great post!");
    }

    @Test
    void createComment_Success() {
        // Given
        long postId = 1L;
        when(postRepository.findById(postId)).thenReturn(Optional.of(testPost));
        when(modelMapper.map(testCommentDto, Comment.class)).thenReturn(testComment);
        when(commentRepository.save(any(Comment.class))).thenReturn(testComment);
        when(modelMapper.map(testComment, CommentDto.class)).thenReturn(testCommentDto);

        // When
        CommentDto result = commentService.createComment(postId, testCommentDto);

        // Then
        assertNotNull(result);
        assertEquals(testCommentDto.getId(), result.getId());
        assertEquals(testCommentDto.getName(), result.getName());
        assertEquals(testCommentDto.getEmail(), result.getEmail());
        assertEquals(testCommentDto.getBody(), result.getBody());
        
        verify(postRepository).findById(postId);
        verify(commentRepository).save(any(Comment.class));
        verify(modelMapper, times(2)).map(any(), any());
    }

    @Test
    void createComment_PostNotFound_ThrowsResourceNotFoundException() {
        // Given
        long postId = 999L;
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> commentService.createComment(postId, testCommentDto)
        );
        
        assertEquals("Post not found with id : '999'", exception.getMessage());
        verify(postRepository).findById(postId);
        verify(commentRepository, never()).save(any());
    }

    @Test
    void getCommentsByPostId_Success() {
        // Given
        long postId = 1L;
        Comment comment2 = new Comment();
        comment2.setId(2L);
        comment2.setName("Jane Doe");
        comment2.setEmail("jane@example.com");
        comment2.setBody("Nice article!");
        
        CommentDto commentDto2 = new CommentDto();
        commentDto2.setId(2L);
        commentDto2.setName("Jane Doe");
        commentDto2.setEmail("jane@example.com");
        commentDto2.setBody("Nice article!");
        
        List<Comment> comments = Arrays.asList(testComment, comment2);
        when(commentRepository.findByPostId(postId)).thenReturn(comments);
        when(modelMapper.map(testComment, CommentDto.class)).thenReturn(testCommentDto);
        when(modelMapper.map(comment2, CommentDto.class)).thenReturn(commentDto2);

        // When
        List<CommentDto> result = commentService.getCommentsByPostId(postId);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(testCommentDto.getName(), result.get(0).getName());
        assertEquals(commentDto2.getName(), result.get(1).getName());
        
        verify(commentRepository).findByPostId(postId);
        verify(modelMapper, times(2)).map(any(Comment.class), eq(CommentDto.class));
    }

    @Test
    void getCommentsByPostId_EmptyList() {
        // Given
        long postId = 1L;
        when(commentRepository.findByPostId(postId)).thenReturn(Arrays.asList());

        // When
        List<CommentDto> result = commentService.getCommentsByPostId(postId);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(commentRepository).findByPostId(postId);
    }

    @Test
    void getCommentById_Success() {
        // Given
        long postId = 1L;
        long commentId = 1L;
        when(postRepository.findById(postId)).thenReturn(Optional.of(testPost));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(testComment));
        when(modelMapper.map(testComment, CommentDto.class)).thenReturn(testCommentDto);

        // When
        CommentDto result = commentService.getCommentById(postId, commentId);

        // Then
        assertNotNull(result);
        assertEquals(testCommentDto.getId(), result.getId());
        assertEquals(testCommentDto.getName(), result.getName());
        
        verify(postRepository).findById(postId);
        verify(commentRepository).findById(commentId);
        verify(modelMapper).map(testComment, CommentDto.class);
    }

    @Test
    void getCommentById_PostNotFound_ThrowsResourceNotFoundException() {
        // Given
        long postId = 999L;
        long commentId = 1L;
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> commentService.getCommentById(postId, commentId)
        );
        
        assertEquals("Post not found with id : '999'", exception.getMessage());
        verify(postRepository).findById(postId);
        verify(commentRepository, never()).findById(anyLong());
    }

    @Test
    void getCommentById_CommentNotFound_ThrowsResourceNotFoundException() {
        // Given
        long postId = 1L;
        long commentId = 999L;
        when(postRepository.findById(postId)).thenReturn(Optional.of(testPost));
        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> commentService.getCommentById(postId, commentId)
        );
        
        assertEquals("Comment not found with id : '999'", exception.getMessage());
        verify(postRepository).findById(postId);
        verify(commentRepository).findById(commentId);
    }

    @Test
    void getCommentById_CommentDoesNotBelongToPost_ThrowsBlogAPIException() {
        // Given
        long postId = 1L;
        long commentId = 1L;
        
        Post anotherPost = new Post();
        anotherPost.setId(2L);
        
        Comment commentFromAnotherPost = new Comment();
        commentFromAnotherPost.setId(1L);
        commentFromAnotherPost.setPost(anotherPost);
        
        when(postRepository.findById(postId)).thenReturn(Optional.of(testPost));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(commentFromAnotherPost));

        // When & Then
        BlogAPIException exception = assertThrows(
            BlogAPIException.class,
            () -> commentService.getCommentById(postId, commentId)
        );
        
        assertEquals("Comment does not belong to post", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        
        verify(postRepository).findById(postId);
        verify(commentRepository).findById(commentId);
    }

    @Test
    void updateComment_Success() {
        // Given
        long postId = 1L;
        long commentId = 1L;
        
        CommentDto updateRequest = new CommentDto();
        updateRequest.setName("Updated Name");
        updateRequest.setEmail("updated@example.com");
        updateRequest.setBody("Updated body");
        
        Comment updatedComment = new Comment();
        updatedComment.setId(1L);
        updatedComment.setName("Updated Name");
        updatedComment.setEmail("updated@example.com");
        updatedComment.setBody("Updated body");
        updatedComment.setPost(testPost);
        
        CommentDto updatedDto = new CommentDto();
        updatedDto.setId(1L);
        updatedDto.setName("Updated Name");
        updatedDto.setEmail("updated@example.com");
        updatedDto.setBody("Updated body");
        
        when(postRepository.findById(postId)).thenReturn(Optional.of(testPost));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(testComment));
        when(commentRepository.save(any(Comment.class))).thenReturn(updatedComment);
        when(modelMapper.map(updatedComment, CommentDto.class)).thenReturn(updatedDto);

        // When
        CommentDto result = commentService.updateComment(postId, commentId, updateRequest);

        // Then
        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        assertEquals("updated@example.com", result.getEmail());
        assertEquals("Updated body", result.getBody());
        
        verify(postRepository).findById(postId);
        verify(commentRepository).findById(commentId);
        verify(commentRepository).save(any(Comment.class));
        verify(modelMapper).map(updatedComment, CommentDto.class);
    }

    @Test
    void updateComment_PostNotFound_ThrowsResourceNotFoundException() {
        // Given
        long postId = 999L;
        long commentId = 1L;
        CommentDto updateRequest = new CommentDto();
        
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> commentService.updateComment(postId, commentId, updateRequest)
        );
        
        assertEquals("Post not found with id : '999'", exception.getMessage());
        verify(postRepository).findById(postId);
        verify(commentRepository, never()).findById(anyLong());
    }

    @Test
    void updateComment_CommentNotFound_ThrowsResourceNotFoundException() {
        // Given
        long postId = 1L;
        long commentId = 999L;
        CommentDto updateRequest = new CommentDto();
        
        when(postRepository.findById(postId)).thenReturn(Optional.of(testPost));
        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> commentService.updateComment(postId, commentId, updateRequest)
        );
        
        assertEquals("Comment not found with id : '999'", exception.getMessage());
        verify(postRepository).findById(postId);
        verify(commentRepository).findById(commentId);
    }

    @Test
    void updateComment_CommentDoesNotBelongToPost_ThrowsBlogAPIException() {
        // Given
        long postId = 1L;
        long commentId = 1L;
        CommentDto updateRequest = new CommentDto();
        
        Post anotherPost = new Post();
        anotherPost.setId(2L);
        
        Comment commentFromAnotherPost = new Comment();
        commentFromAnotherPost.setId(1L);
        commentFromAnotherPost.setPost(anotherPost);
        
        when(postRepository.findById(postId)).thenReturn(Optional.of(testPost));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(commentFromAnotherPost));

        // When & Then
        BlogAPIException exception = assertThrows(
            BlogAPIException.class,
            () -> commentService.updateComment(postId, commentId, updateRequest)
        );
        
        assertEquals("Comment does not belong to post", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        
        verify(postRepository).findById(postId);
        verify(commentRepository).findById(commentId);
        verify(commentRepository, never()).save(any());
    }

    @Test
    void deleteComment_Success() {
        // Given
        long postId = 1L;
        long commentId = 1L;
        
        when(postRepository.findById(postId)).thenReturn(Optional.of(testPost));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(testComment));

        // When
        commentService.deleteComment(postId, commentId);

        // Then
        verify(postRepository).findById(postId);
        verify(commentRepository).findById(commentId);
        verify(commentRepository).delete(testComment);
    }

    @Test
    void deleteComment_PostNotFound_ThrowsResourceNotFoundException() {
        // Given
        long postId = 999L;
        long commentId = 1L;
        
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> commentService.deleteComment(postId, commentId)
        );
        
        assertEquals("Post not found with id : '999'", exception.getMessage());
        verify(postRepository).findById(postId);
        verify(commentRepository, never()).findById(anyLong());
        verify(commentRepository, never()).delete(any());
    }

    @Test
    void deleteComment_CommentNotFound_ThrowsResourceNotFoundException() {
        // Given
        long postId = 1L;
        long commentId = 999L;
        
        when(postRepository.findById(postId)).thenReturn(Optional.of(testPost));
        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> commentService.deleteComment(postId, commentId)
        );
        
        assertEquals("Comment not found with id : '999'", exception.getMessage());
        verify(postRepository).findById(postId);
        verify(commentRepository).findById(commentId);
        verify(commentRepository, never()).delete(any());
    }

    @Test
    void deleteComment_CommentDoesNotBelongToPost_ThrowsBlogAPIException() {
        // Given
        long postId = 1L;
        long commentId = 1L;
        
        Post anotherPost = new Post();
        anotherPost.setId(2L);
        
        Comment commentFromAnotherPost = new Comment();
        commentFromAnotherPost.setId(1L);
        commentFromAnotherPost.setPost(anotherPost);
        
        when(postRepository.findById(postId)).thenReturn(Optional.of(testPost));
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(commentFromAnotherPost));

        // When & Then
        BlogAPIException exception = assertThrows(
            BlogAPIException.class,
            () -> commentService.deleteComment(postId, commentId)
        );
        
        assertEquals("Comment does not belong to post", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        
        verify(postRepository).findById(postId);
        verify(commentRepository).findById(commentId);
        verify(commentRepository, never()).delete(any());
    }

    @Test
    void commentServiceMapperUtil_Success() {
        // Given
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setName("Test Name");
        comment.setEmail("test@example.com");
        comment.setBody("Test Body");

        // When
        CommentDto result = CommentServiceImpl.commentServiceMapperUtil(comment);

        // Then
        assertNotNull(result);
        assertEquals(comment.getId(), result.getId());
        assertEquals(comment.getName(), result.getName());
        assertEquals(comment.getEmail(), result.getEmail());
        assertEquals(comment.getBody(), result.getBody());
    }
} 