package com.chuwa.redbook.service.impl;

import com.chuwa.redbook.RedbookApplication;
import com.chuwa.redbook.dao.CommentRepository;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.ResourceNotFoundException;
import com.chuwa.redbook.payload.CommentDto;
import com.chuwa.redbook.payload.PostDto;
import com.chuwa.redbook.payload.PostResponse;

import com.chuwa.redbook.exception.BlogAPIException;
import com.chuwa.redbook.exception.ResourceNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class CommentServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImplTest.class);
    @Mock
    private CommentRepository commentRepo;
    @Mock
    private PostRepository postRepo;
    @Spy
    private ModelMapper modelMapper = new ModelMapper();

    private Post post;
    private Comment comment;
    private CommentDto commentDto;

    @InjectMocks
    private CommentServiceImpl svc;

    @BeforeAll
    static void beforeAll() {
        logger.info("START test");
    }

    private final long POST_ID = 1L;
    private final long COMMENT_ID = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        logger.info("set up Post for each test");
        this.post = new Post(POST_ID, "xiao ruishi", "wanqu", "wanqu xiao ruishi",
                LocalDateTime.now(), LocalDateTime.now());
        this.comment = new Comment(COMMENT_ID, "joe", "joe@example.com", "test comment");
        this.comment.setPost(post);
        this.commentDto = new CommentDto(COMMENT_ID, "joe", "joe@example.com", "test comment");
    }

    @Test
    void testCreateComment() {
        CommentDto inDto = this.commentDto;
        Post post = this.post;
        Comment saved = this.comment;

        when(postRepo.findById(POST_ID)).thenReturn(Optional.of(post));
        when(commentRepo.save(any(Comment.class))).thenReturn(saved);

        CommentDto out = svc.createComment(POST_ID, inDto);

        assertNotNull(out);
        assertEquals(out.getName(), inDto.getName());
        assertEquals(out.getEmail(), inDto.getEmail());
        assertEquals(out.getBody(), inDto.getBody());

        verify(postRepo).findById(POST_ID);
        verify(commentRepo).save(any(Comment.class));
    }

    @Test
    void testGetCommentById(){
        Post post = this.post;
        Comment c= this.comment;
        when(postRepo.findById(POST_ID)).thenReturn(Optional.of(post));
        when(commentRepo.findById(COMMENT_ID)).thenReturn(Optional.of(c));

        CommentDto out = svc.getCommentById(POST_ID, COMMENT_ID);

        assertNotNull(out);
        assertEquals(out.getId(), COMMENT_ID);
        assertEquals(out.getName(), c.getName());
    }

    @Test
    void testGetCommentById_mismatch_throws() {
        // comment.post.id != POST_ID
        Post other = new Post(999L, null, null, null, null, null);
        comment.setPost(other);

        when(postRepo.findById(POST_ID)).thenReturn(Optional.of(post));
        when(commentRepo.findById(COMMENT_ID)).thenReturn(Optional.of(comment));

        BlogAPIException ex = assertThrows(BlogAPIException.class, () ->
                svc.getCommentById(POST_ID, COMMENT_ID)
        );
        assertEquals("Comment does not belong to post", ex.getMessage());
    }

    @Test
    void testGetCommentsByPostId() {
        when(commentRepo.findByPostId(POST_ID)).thenReturn(List.of(comment));

        List<CommentDto> list = svc.getCommentsByPostId(POST_ID);

        assertNotNull(list);
        assertEquals(1, list.size());
        CommentDto d = list.get(0);
        assertEquals(COMMENT_ID, d.getId());
        assertEquals(comment.getName(),  d.getName());
        assertEquals(comment.getEmail(), d.getEmail());
        assertEquals(comment.getBody(),  d.getBody());
        verify(commentRepo).findByPostId(POST_ID);
    }

    @Test
    void testUpdateComment() {
        CommentDto req = new CommentDto(COMMENT_ID,
                "updated", "upd@example.com", "new body");

        when(postRepo.findById(POST_ID)).thenReturn(Optional.of(post));
        when(commentRepo.findById(COMMENT_ID)).thenReturn(Optional.of(comment));

        Comment updated = new Comment(COMMENT_ID,
                req.getName(), req.getEmail(), req.getBody());
        updated.setPost(post);
        when(commentRepo.save(comment)).thenReturn(updated);

        CommentDto out = svc.updateComment(POST_ID, COMMENT_ID, req);

        assertNotNull(out);
        assertEquals(COMMENT_ID,       out.getId());
        assertEquals("updated",        out.getName());
        assertEquals("upd@example.com",out.getEmail());
        assertEquals("new body",       out.getBody());

        verify(postRepo).findById(POST_ID);
        verify(commentRepo).findById(COMMENT_ID);
        verify(commentRepo).save(comment);
    }

    @Test
    void testUpdateComment_mismatch_throws() {
        // comment belongs to a different post
        Post other = new Post(999L, null, null, null, null, null);
        comment.setPost(other);

        when(postRepo.findById(POST_ID)).thenReturn(Optional.of(post));
        when(commentRepo.findById(COMMENT_ID)).thenReturn(Optional.of(comment));

        BlogAPIException ex = assertThrows(BlogAPIException.class, () ->
                svc.updateComment(POST_ID, COMMENT_ID, commentDto)
        );
        assertEquals("Comment does not belong to post", ex.getMessage());
    }

    @Test
    void testDeleteComment() {
        when(postRepo.findById(POST_ID)).thenReturn(Optional.of(post));
        when(commentRepo.findById(COMMENT_ID)).thenReturn(Optional.of(comment));

        svc.deleteComment(POST_ID, COMMENT_ID);

        verify(commentRepo).delete(comment);
    }

    @Test
    void testDeleteComment_mismatch_throws() {
        // comment belongs to a different post
        Post other = new Post(999L, null, null, null, null, null);
        comment.setPost(other);

        when(postRepo.findById(POST_ID)).thenReturn(Optional.of(post));
        when(commentRepo.findById(COMMENT_ID)).thenReturn(Optional.of(comment));

        BlogAPIException ex = assertThrows(BlogAPIException.class, () ->
                svc.deleteComment(POST_ID, COMMENT_ID)
        );
        assertEquals("Comment does not belong to post", ex.getMessage());
    }

}
