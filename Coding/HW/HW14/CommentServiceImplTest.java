package com.chuwa.redbook.service.impl;

import com.chuwa.redbook.dao.CommentRepository;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.BlogAPIException;
import com.chuwa.redbook.exception.ResourceNotFoundException;
import com.chuwa.redbook.payload.CommentDto;
import com.chuwa.redbook.payload.PostDto;
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

import java.time.LocalDateTime;
import java.util.*;


@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImplTest.class);


    @Mock
    private CommentRepository commentRepositoryMock;

    @Mock
    private PostRepository postRepositoryMock;

    @Mock(name="modalMapper")
    private ModelMapper modelMapperMock;

    private Post post;
    private PostDto postDto;
    private Comment comment;
    private CommentDto commentDto;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeAll
    static void beforeAll () {logger.info("Start Test");}

    @BeforeEach
    void setUp() {
        logger.info("Set up Post and Comment");

        this.post = new Post(1L, "xiao ruishi", "wanqu", "wanqu xiao ruishi",
                LocalDateTime.now(), LocalDateTime.now());
        this.postDto = new PostDto();
        postDto.setId(1L);
        postDto.setTitle("xiao ruishi");
        postDto.setContent("wanqu xiao ruishi");
        postDto.setDescription("wanqu");

        this.comment = new Comment(1L, "test name", "testemail@test.com", "test body");
        this.commentDto = new CommentDto();
        commentDto.setId(1L);
        commentDto.setName("test name");
        commentDto.setEmail("testemail@test.com");
        commentDto.setBody("test body");

    }

    // test createComment
    @Test
    public void testCreateComment() {

        Mockito.when(modelMapperMock.map(ArgumentMatchers.any(CommentDto.class), ArgumentMatchers.eq(Comment.class))).thenReturn(comment);
        Mockito.when(modelMapperMock.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class))).thenReturn(commentDto);

        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(post));


        Mockito.when(commentRepositoryMock.save(ArgumentMatchers.any())).thenReturn(comment);

        CommentDto commentResponse = commentService.createComment(post.getId(), commentDto);

        Assertions.assertEquals(comment.getPost().getId(), post.getId());

        Assertions.assertEquals(commentDto.getBody(), commentResponse.getBody());
        Assertions.assertEquals(commentDto.getEmail(), commentResponse.getEmail());
        Assertions.assertEquals(commentDto.getName(), commentResponse.getName());
    }

    // test creat comment error throw
    @Test
    public void testCreateComment_PostNotFound() {
        Mockito.when(modelMapperMock.map(ArgumentMatchers.any(CommentDto.class), ArgumentMatchers.eq(Comment.class))).thenReturn(comment);
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenThrow(new ResourceNotFoundException("Post", "id", 1L));

        Assertions.assertThrows(ResourceNotFoundException.class,() -> commentService.createComment(1L, commentDto));
    }

    @Test
    public void testGetCommentsByPostId() {
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        Mockito.when(commentRepositoryMock.findByPostId(ArgumentMatchers.anyLong())).thenReturn(comments);
        Mockito.when(modelMapperMock.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class))).thenReturn(commentDto);

        List<CommentDto> commentDtos = commentService.getCommentsByPostId(post.getId());

        Assertions.assertNotNull(commentDtos);
        Assertions.assertEquals(1, commentDtos.size());
        Assertions.assertEquals(commentDto.getId(), commentDtos.get(0).getId());
        Assertions.assertEquals(commentDto.getName(), commentDtos.get(0).getName());
        Assertions.assertEquals(commentDto.getBody(), commentDtos.get(0).getBody());
        Assertions.assertEquals(commentDto.getEmail(), commentDtos.get(0).getEmail());
    }

    @Test
    public void testGetCommentById() {
        comment.setPost(post);
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(comment));

        Mockito.when(modelMapperMock.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class))).thenReturn(commentDto);

        CommentDto commentResponse = commentService.getCommentById(post.getId(), comment.getId());
        Assertions.assertEquals(commentDto.getId(), commentResponse.getId());
        Assertions.assertEquals(commentDto.getName(), commentResponse.getName());
        Assertions.assertEquals(commentDto.getBody(), commentResponse.getBody());
        Assertions.assertEquals(commentDto.getEmail(), commentResponse.getEmail());
    }

    @Test
    public void testGetCommentById_PostNotFound() {
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new ResourceNotFoundException("Post", "id", 1L));

        Assertions.assertThrows(ResourceNotFoundException.class, () -> commentService.getCommentById(1L, 1L));

    }

    @Test
    public void testGetCommentById_CommentNotFound() {
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong())).thenThrow(new ResourceNotFoundException("Comment", "id", 1L));

        Assertions.assertThrows(ResourceNotFoundException.class, () -> commentService.getCommentById(1L, 1L));
    }
//
    @Test
    public void testGetCommentById_PostAndCommentNotMatch() {
        Comment newComment =  new Comment(999L, "test2 name", "test2@gmail.com", "test2 body");
        Post newPost = new Post(2L, "aaaaa", "bbbbb", "ccccc", LocalDateTime.now(), LocalDateTime.now());
        newComment.setPost(newPost);

        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(newComment));

        BlogAPIException ex = Assertions.assertThrows(BlogAPIException.class, () -> {
            commentService.getCommentById(post.getId(), newComment.getId());
        });

        Assertions.assertNotNull(ex);
        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals("Comment does not belong to post", ex.getMessage());

    }

    @Test
    public void testUpdateComment() {
        comment.setPost(post);

        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(comment));

        Comment updatedComment = new Comment();
        updatedComment.setName("updated" + comment.getName());
        updatedComment.setId(comment.getId());
        updatedComment.setBody(comment.getBody());
        updatedComment.setEmail(comment.getEmail());
        updatedComment.setPost(comment.getPost());

        CommentDto updatedCommentDto = new CommentDto();
        updatedCommentDto.setName(updatedComment.getName());
        updatedCommentDto.setId(updatedComment.getId());
        updatedCommentDto.setBody(updatedComment.getBody());
        updatedCommentDto.setEmail(updatedComment.getEmail());

        Mockito.when(commentRepositoryMock.save(ArgumentMatchers.any())).thenReturn(updatedComment);

        Mockito.when(modelMapperMock.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class))).thenReturn(updatedCommentDto);

        CommentDto ResponseDto = commentService.updateComment(post.getId(), comment.getId(), updatedCommentDto);

        Assertions.assertNotNull(ResponseDto);
        Assertions.assertEquals(updatedCommentDto.getName(), ResponseDto.getName());
        Assertions.assertEquals(updatedCommentDto.getBody(), ResponseDto.getBody());
        Assertions.assertEquals(updatedCommentDto.getEmail(), ResponseDto.getEmail());
    }

    @Test
    public void testUpdateComment_PostNotFound() {
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new ResourceNotFoundException("Post", "id", 1L));

        Assertions.assertThrows(ResourceNotFoundException.class, () -> commentService.updateComment(1L, 1L, commentDto));
    }

    @Test
    public void testUpdateComment_PostAndCommentNotMatch() {
        Comment newComment =  new Comment(999L, "test2 name", "test2@gmail.com", "test2 body");
        Post newPost = new Post(2L, "aaaaa", "bbbbb", "ccccc", LocalDateTime.now(), LocalDateTime.now());
        newComment.setPost(newPost);

        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(newComment));

        BlogAPIException ex = Assertions.assertThrows(BlogAPIException.class, () -> {
            commentService.updateComment(post.getId(), newComment.getId(), commentDto);
        });

        Assertions.assertEquals("Comment does not belong to post", ex.getMessage());
    }

    @Test
    public void testDeleteComment() {
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(comment));

        Mockito.doNothing().when(commentRepositoryMock).delete(ArgumentMatchers.any(Comment.class));
        comment.setPost(post);

        commentService.deleteComment(1L, 1L);
        Mockito.verify(commentRepositoryMock, Mockito.times(1)).delete(ArgumentMatchers.any(Comment.class));

    }

    @Test
    public void testDeleteComment_PostAndCommentNotMatch() {
        Comment newComment =  new Comment(999L, "test2 name", "test2@gmail.com", "test2 body");
        Post newPost = new Post(2L, "aaaaa", "bbbbb", "ccccc", LocalDateTime.now(), LocalDateTime.now());
        newComment.setPost(newPost);


        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(newComment));

        BlogAPIException ex = Assertions.assertThrows(BlogAPIException.class, () -> {
            commentService.deleteComment(post.getId(), newComment.getId());
        });

        Assertions.assertEquals("Comment does not belong to post", ex.getMessage());
    }

    @Test
    public void testCommentServiceMapperUtil() {
        CommentDto dto = commentService.commentServiceMapperUtil(comment);

        Assertions.assertEquals(dto.getName(), comment.getName());
        Assertions.assertEquals(dto.getEmail(), comment.getEmail());
        Assertions.assertEquals(dto.getBody(), comment.getBody());
        Assertions.assertEquals(dto.getId(), comment.getId());

    }
}
