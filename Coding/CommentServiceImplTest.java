package com.chuwa.redbook.service.impl;

import com.chuwa.redbook.dao.CommentRepository;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.BlogAPIException;
import com.chuwa.redbook.exception.ResourceNotFoundException;
import com.chuwa.redbook.payload.CommentDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImplTest.class);

    @Mock
    private PostRepository postRepositoryMock;

    @Mock
    private CommentRepository commentRepositoryMock;

    @Mock(name="modelMapper")
    private ModelMapper mockedModelMapper;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Post post;
    private Comment comment;
    private CommentDto commentDto;


    @BeforeAll
    static void beforeAll() {
        logger.info("START test");
    }

    @BeforeEach
    void setUp() {
        logger.info("set up Post for each test");

        post = new Post();
        post.setId(1L);

        this.comment = new Comment(2L, "John", "john@example.com", "Nice post!");
        comment.setPost(post);

        this.commentDto = new CommentDto();
        commentDto.setId(2L);
        commentDto.setName("John");
        commentDto.setEmail("john@example.com");
        commentDto.setBody("Nice post!");
    }

    @Test
    void testCreateCommentWithMockedModelMapper() {
        Mockito.when(mockedModelMapper.map(ArgumentMatchers.any(CommentDto.class), ArgumentMatchers.eq(Comment.class))).thenReturn(comment);
        Mockito.when(mockedModelMapper.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class))).thenReturn(commentDto);
        Mockito.when(commentRepositoryMock.save(ArgumentMatchers.any())).thenReturn(comment);

        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(post));
        CommentDto commentResponse = commentService.createComment(1L, commentDto);

        assertEquals(commentDto.getName(), commentResponse.getName());
        assertEquals(commentDto.getBody(), commentResponse.getBody());
        assertEquals(commentDto.getEmail(), commentResponse.getEmail());
    }

    @Test
    void testCreateComment_postNotFound() {
        Mockito.when(postRepositoryMock.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> commentService.createComment(1L, commentDto));
    }

    @Test
    void testGetCommentsByPostId() {
        Mockito.when(commentRepositoryMock.findByPostId(ArgumentMatchers.anyLong()))
                .thenReturn(Collections.singletonList(comment));
        Mockito.when(mockedModelMapper.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class))).thenReturn(commentDto);

        List<CommentDto> result = commentService.getCommentsByPostId(1L);
        assertEquals(1, result.size());
        CommentDto actual = result.get(0);
        assertEquals("John", actual.getName());
        assertEquals("john@example.com", actual.getEmail());
        assertEquals("Nice post!", actual.getBody());
    }

    @Test
    void testGetCommentById() {
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(comment));
        Mockito.when(mockedModelMapper.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class))).thenReturn(commentDto);

        CommentDto commentResponse = commentService.getCommentById(1L, 2L);
        assertEquals("John", commentResponse.getName());
        assertEquals("john@example.com", commentResponse.getEmail());
        assertEquals("Nice post!", commentResponse.getBody());
    }

    @Test
    void testGetCommentById_commentNotBelongToPost() {
        Post otherPost = new Post();
        otherPost.setId(99L);
        comment.setPost(otherPost);

        Mockito.when(postRepositoryMock.findById(1L)).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(2L)).thenReturn(Optional.of(comment));

        BlogAPIException ex = Assertions.assertThrows(BlogAPIException.class, () -> commentService.getCommentById(1L, 2L));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getHttpStatus());
    }

    @Test
    void testGetCommentById_ResourceNotFoundException() {
        Mockito.when(postRepositoryMock.findById(1L)).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new ResourceNotFoundException("Comment", "id", 2L));

        Assertions.assertThrows(ResourceNotFoundException.class, () -> commentService.getCommentById(1L, 2L));
    }

    @Test
    void testUpdateComment() {
        String updatedBody = "UPDATED - " + comment.getBody();
        commentDto.setBody(updatedBody);

        // deep copy
        Comment updatedComment = new Comment(comment.getId(), comment.getName(), comment.getEmail(), updatedBody);
        updatedComment.setPost(post);

        // define the behaviors
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(comment));
        Mockito.when(commentRepositoryMock.save(ArgumentMatchers.any(Comment.class)))
                .thenReturn(updatedComment);
        Mockito.when(mockedModelMapper.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class)))
                .thenReturn(commentDto);

        // execute
        CommentDto result = commentService.updateComment(1L, 2L, commentDto);

        // assertions
        Assertions.assertNotNull(result);
        Assertions.assertEquals(commentDto.getName(), result.getName());
        Assertions.assertEquals(commentDto.getEmail(), result.getEmail());
        Assertions.assertEquals(commentDto.getBody(), result.getBody());
    }

    @Test
    void testUpdateComment_CommentDoesNotBelongToPost() {
        // Arrange
        Post postFromRequest = new Post();
        postFromRequest.setId(1L); // This is the one passed into the method

        Post actualPostInComment = new Post();
        actualPostInComment.setId(99L); // This is the one assigned to the comment (mismatch)

        comment.setPost(actualPostInComment); // Simulate mismatch

        Mockito.when(postRepositoryMock.findById(1L)).thenReturn(Optional.of(postFromRequest));
        Mockito.when(commentRepositoryMock.findById(2L)).thenReturn(Optional.of(comment));

        // Act & Assert
        BlogAPIException ex = Assertions.assertThrows(BlogAPIException.class, () -> {
            commentService.updateComment(1L, 2L, commentDto);
        });

        assertEquals(HttpStatus.BAD_REQUEST, ex.getHttpStatus());
        assertEquals("Comment does not belong to post", ex.getMessage());
    }

    @Test
    public void testUpdateComment_PostNotFound() {
        // post not found
        Mockito.when(postRepositoryMock.findById(1L))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                commentService.updateComment(1L, 2L, commentDto));
    }

    @Test
    public void testUpdateComment_CommentNotFound() {
        // post found
        Mockito.when(postRepositoryMock.findById(1L))
                .thenReturn(Optional.of(post));
        // comment not found
        Mockito.when(commentRepositoryMock.findById(2L))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                commentService.updateComment(1L, 2L, commentDto));
    }

    @Test
    void testDeleteCommentById() {
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(this.comment));
        Mockito.doNothing().when(commentRepositoryMock).delete(ArgumentMatchers.any(Comment.class));
        commentService.deleteComment(1L, 2L);
        Mockito.verify(commentRepositoryMock, Mockito.times(1)).delete(ArgumentMatchers.any(Comment.class));
    }

    @Test
    void testDeleteComment_PostNotFound() {
        Mockito.when(postRepositoryMock.findById(1L))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            commentService.deleteComment(1L, 2L);
        });
    }

    @Test
    void testDeleteComment_CommentNotFound() {
        Mockito.when(postRepositoryMock.findById(1L))
                .thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(2L))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            commentService.deleteComment(1L, 2L);
        });
    }

    // This method is never used
    @Test
    void testCommentServiceMapperUtil() {
        // Arrange
        Comment comment = new Comment(1L, "Alice", "alice@example.com", "hello world");

        // Act
        CommentDto dto = CommentServiceImpl.commentServiceMapperUtil(comment);

        // Assert
        assertEquals("Alice", dto.getName());
        assertEquals("alice@example.com", dto.getEmail());
        assertEquals("hello world", dto.getBody());
    }

    @Test
    void testDeleteComment_CommentDoesNotBelongToPost() {
        // Arrange
        Post requestedPost = new Post();
        requestedPost.setId(1L); // The post ID passed into the method

        Post commentActualPost = new Post();
        commentActualPost.setId(99L); // Different post assigned to the comment

        comment.setPost(commentActualPost); // Simulate mismatch

        Mockito.when(postRepositoryMock.findById(1L)).thenReturn(Optional.of(requestedPost));
        Mockito.when(commentRepositoryMock.findById(2L)).thenReturn(Optional.of(comment));

        // Act & Assert
        BlogAPIException ex = Assertions.assertThrows(BlogAPIException.class, () -> {
            commentService.deleteComment(1L, 2L);
        });

        assertEquals(HttpStatus.BAD_REQUEST, ex.getHttpStatus());
        assertEquals("Comment does not belong to post", ex.getMessage());
    }
}