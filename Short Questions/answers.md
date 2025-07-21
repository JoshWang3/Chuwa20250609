1. Unit Testing

Definition: Testing individual units or components (usually functions/methods) of code in isolation.

    Focus: Smallest parts of the application logic

    Performed by: Developers

    Tools: JUnit (Java), NUnit (.NET), PyTest (Python)

2. Functional Testing

Definition: Verifies that the software functions as expected based on the specified requirements.

    Focus: Business logic and feature correctness

    Performed by: QA/Testers

    Tools: Selenium, Postman, REST Assured

Example:
Testing that submitting a login form with valid credentials logs the user in successfully.

3. Integration Testing

Definition: Tests the interaction between multiple units/modules to ensure they work together correctly.

    Focus: Interfaces and interactions between modules

    Performed by: Developers or QA

    Tools: Spring Test (Java), Postman for API-level integration

Example:
Test if a UserService correctly fetches user details from a UserRepository and transforms them using a UserMapper.

4. Regression Testing

Definition: Verifies that recent code changes haven’t broken existing functionality.

    Focus: Stability of the system after changes

    Performed by: QA teams (automated or manual)

    Tools: Selenium, Cypress, TestNG

Example:
After updating the login feature to support social login, regression testing ensures standard email-password login still works.

5. Smoke Testing

Definition: A shallow and wide approach to testing critical functionality; ensures the build is stable enough for deeper testing.

    Focus: Major features (build verification)

    Performed by: QA or CI pipelines

    Tools: Simple automated scripts or manual checklists

Example:
Can the app start? Can you log in? Can you navigate to key pages?

6. Performance Testing

Definition: Measures how the system performs under expected load (speed, responsiveness, scalability).

    Focus: Response time, throughput, efficiency

    Performed by: Performance engineers

    Tools: JMeter, Gatling, Locust

Example:
Testing if the system can handle 1000 concurrent users submitting forms without degrading response time.

7. Stress Testing

Definition: Pushes the system beyond its limits to find its breaking point.

    Focus: System stability and error handling under extreme conditions

    Performed by: Performance/QA teams

    Tools: Same as performance testing tools

Example:
Send 10,000 simultaneous login requests to see if the server crashes or gracefully rejects requests.

8. A/B Testing

Definition: Compares two versions (A and B) of a product/feature with real users to determine which performs better.

    Focus: User behavior and experience

    Performed by: Product teams / data analysts

    Tools: Google Optimize, Optimizely

Example:
Group A sees a red “Buy Now” button, Group B sees a green one. The version with better conversion rates is chosen.

9. End-to-End Testing (E2E)

Definition: Tests complete user flows from start to finish, simulating real user scenarios.

    Focus: Full system behavior

    Performed by: QA/Test automation teams

    Tools: Cypress, Selenium, Playwright

Example:
Test that a user can sign up, add items to the cart, check out, and receive an order confirmation email.

10. User Acceptance Testing (UAT)

Definition: Validates that the system meets user/business requirements and is acceptable for release.

    Focus: Business flows, usability, and value

    Performed by: Actual end users or clients

    Tools: Manual or light-weight tools like TestRail

Example:
Client tests a payroll feature to ensure it calculates taxes correctly before going live.

1. Development Environment (DEV)

    Used by: Developers

    Goal: Implement and test code early (unit/component level)

    Data: Fake or dummy data

    Access: All developers

    Spring Boot Example:

        Profile: application-dev.properties

        Embedded H2 DB, dev tools enabled, logs at DEBUG level

    Risks: Unstable code, frequent builds/changes

    2. QA / Testing Environment

    Used by: QA Engineers

    Goal: Test overall application functionality (integration, regression, smoke)

    Data: Clean, structured test data (may mirror prod with obfuscation)

    Spring Boot Example:

        Profile: application-qa.properties

        Connects to test DB, log level INFO, mock external APIs

    Tools: Selenium, Postman, JMeter

    Risks: Can have bugs, but should not crash frequently

    3. Pre-production / Staging Environment

    Used by: QA, DevOps, Business Owners, UAT testers

    Goal: Final environment that mirrors production for realistic testing (including UAT, load testing, release rehearsal)

    Data: Sanitized production data or synthetic data

    Spring Boot Example:

        Profile: application-staging.properties

        Real external API endpoints, security settings turned on

    Practices:

        Performance/load testing

        Final approval before go-live

        Canary testing

    Risks: Should be as stable as production but still not public-facing

    4. Production Environment (PROD)

    Used by: Real users (customers, clients)

    Goal: Run the actual application with live traffic and real transactions

    Data: Real business data

    Spring Boot Example:

        Profile: application-prod.properties

        Hardened security, logging at WARN/ERROR, APM enabled (e.g., New Relic)

    Practices:

        Monitoring, alerting

        Rollback/Hotfix pipelines

        Zero-downtime deployment

    Risks: Any bug can impact real customers and business operations

    package com.chuwa.redbook.service.impl;

'''java
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
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Mock(name = "mockedCommentRepository")
    private CommentRepository commentRepositoryMock;

    @Mock(name = "mockedPostRepository")
    private PostRepository postRepositoryMock;

    @Spy
    private ModelMapper modelMapperMock;


    @InjectMocks
    private CommentServiceImpl commentService;

    private CommentDto commentDto1;
    private CommentDto commentDto2;
    private Comment c1;
    private Comment c2;
    private Post post1;

    @BeforeAll
    static void beforeAll() {logger.info("Testing CommentServiceImpl start");}

    @BeforeEach
    void setUp(){
        logger.info("set up Comment for each test");
        this.post1 = new Post(1L, "p1", "This is post1", "post1",LocalDateTime.now(), LocalDateTime.now());
        this.c1 = new Comment(1, "c1", "dog@gmail.com", "c1 is a comment to post");
        this.c2 = new Comment(2, "c2", "cat@gmail.com", "c2 is a comment to post");
        this.commentDto1 = new CommentDto();
        this.commentDto2 = new CommentDto();
        commentDto1.setId(1);
        commentDto2.setId(2);
        commentDto1.setBody("c1 is a comment to post");
        commentDto2.setBody("c2 is a comment to post");
        commentDto1.setEmail("dog@gmail.com");
        commentDto2.setEmail("cat@gmail.com");
        commentDto1.setName("c1");
        commentDto2.setName("c2");

    }

    @Test
    public void testCreateComment(){

        Mockito.when(postRepositoryMock.findById(1L)).thenReturn(Optional.ofNullable(post1));
        Mockito.when(postRepositoryMock.findById(argThat(value -> !Long.valueOf(1).equals(value)))).thenReturn(Optional.empty());
        Mockito.when(commentRepositoryMock.save(argThat(arg -> arg instanceof Comment))).thenAnswer(invocation -> invocation.getArgument(0));

        CommentDto commentDtoResponse = commentService.createComment(1, commentDto1);
        Assertions.assertNotNull(commentDtoResponse);
        Assertions.assertEquals(commentDtoResponse.getId(),commentDto1.getId());
        Assertions.assertEquals(commentDtoResponse.getBody(),commentDto1.getBody());
        Assertions.assertEquals(commentDtoResponse.getEmail(),commentDto1.getEmail());
        Assertions.assertEquals(commentDtoResponse.getName(),commentDto1.getName());

        commentDtoResponse = commentService.createComment(1, commentDto2);
        Assertions.assertNotNull(commentDtoResponse);
        Assertions.assertEquals(commentDtoResponse.getId(),commentDto2.getId());
        Assertions.assertEquals(commentDtoResponse.getBody(),commentDto2.getBody());
        Assertions.assertEquals(commentDtoResponse.getEmail(),commentDto2.getEmail());
        Assertions.assertEquals(commentDtoResponse.getName(),commentDto2.getName());


        Assertions.assertThrowsExactly(ResourceNotFoundException.class,()->{commentService.createComment(2, commentDto1);});
    }
    @Test
    public void testGetCommentsByPostId(){

        Mockito.when(commentRepositoryMock.findByPostId(1L)).thenReturn(Arrays.asList(c1, c2));
        List<CommentDto> commentDtoList = commentService.getCommentsByPostId(1L);
        Assertions.assertIterableEquals(commentDtoList, Arrays.asList(commentDto1,commentDto2));
    }

    @Test
    public void testGetCommentById() {
        c1.setPost(post1);
        c2.setPost(post1);
        Post post2 = new Post(2L, "p2", "This is post2", "post2", LocalDateTime.now(), LocalDateTime.now());
        Mockito.when(postRepositoryMock.findById(1L)).thenReturn(Optional.ofNullable(post1));
        Mockito.when(postRepositoryMock.findById(2L)).thenReturn(Optional.ofNullable(post2));
        Mockito.when(postRepositoryMock.findById(argThat(value -> !(Long.valueOf(1).equals(value) || Long.valueOf(2).equals(value))))).thenReturn(Optional.empty());
        Mockito.when(commentRepositoryMock.findById(1L)).thenReturn(Optional.ofNullable(c1));
        Mockito.when(commentRepositoryMock.findById(2L)).thenReturn(Optional.ofNullable(c2));
        Mockito.when(commentRepositoryMock.findById(argThat(value -> !(Long.valueOf(1).equals(value) || Long.valueOf(2).equals(value))))).thenReturn(Optional.empty());

        CommentDto response = commentService.getCommentById(1L, 1L);
        Assertions.assertEquals(commentDto1.getId(), response.getId());
        Assertions.assertEquals(commentDto1.getBody(), response.getBody());
        Assertions.assertEquals(commentDto1.getEmail(), response.getEmail());
        Assertions.assertEquals(commentDto1.getName(), response.getName());

        Assertions.assertThrowsExactly(ResourceNotFoundException.class, () -> {
            commentService.getCommentById(3L, 1L);
        });
        Assertions.assertThrowsExactly(ResourceNotFoundException.class, () -> {
            commentService.getCommentById(1L, 4L);
        });
        Assertions.assertThrowsExactly(ResourceNotFoundException.class, () -> {
            commentService.getCommentById(3L, 5L);
        });
        Assertions.assertThrowsExactly(BlogAPIException.class, () -> {
            commentService.getCommentById(2L, 2L);
        });
    }

    @Test
    public void testUpdateComment(){
        c1.setPost(post1);
        c2.setPost(post1);
        Post post2 = new Post(2L, "p2", "This is post2", "post2", LocalDateTime.now(), LocalDateTime.now());
        Mockito.when(postRepositoryMock.findById(1L)).thenReturn(Optional.ofNullable(post1));
        Mockito.when(postRepositoryMock.findById(2L)).thenReturn(Optional.ofNullable(post2));
        Mockito.when(postRepositoryMock.findById(argThat(value -> !(Long.valueOf(1).equals(value) || Long.valueOf(2).equals(value))))).thenReturn(Optional.empty());
        Mockito.when(commentRepositoryMock.findById(1L)).thenReturn(Optional.ofNullable(c1));
        Mockito.when(commentRepositoryMock.findById(argThat(value -> !(Long.valueOf(1).equals(value) || Long.valueOf(2).equals(value))))).thenReturn(Optional.empty());
        Mockito.when(commentRepositoryMock.save(argThat(comment -> comment instanceof Comment))).thenAnswer(invocation -> invocation.getArgument(0));

        CommentDto response = commentService.updateComment(1L, 1L, commentDto2);
        Assertions.assertEquals(commentDto2.getName(), response.getName());
        Assertions.assertEquals(commentDto2.getBody(), response.getBody());
        Assertions.assertEquals(commentDto2.getEmail(), response.getEmail());
        Assertions.assertThrowsExactly(ResourceNotFoundException.class, () -> {
            commentService.updateComment(1L ,5L, commentDto2);
        });
        Assertions.assertThrowsExactly(ResourceNotFoundException.class, () -> {
            commentService.updateComment(3L, 4L, commentDto2);
        });
        Assertions.assertThrowsExactly(ResourceNotFoundException.class, () -> {
            commentService.updateComment(3L, 1L,  commentDto2);
        });
        Assertions.assertThrowsExactly(BlogAPIException.class, () -> {
            commentService.updateComment(2L, 1L, commentDto2);
        });
    }

    @Test
    public void testDeleteComment(){
        c1.setPost(post1);
        Post post2 = new Post(2L, "p2", "This is post2", "post2", LocalDateTime.now(), LocalDateTime.now());
        Mockito.when(postRepositoryMock.findById(1L)).thenReturn(Optional.of(post1));
        Mockito.when(postRepositoryMock.findById(2L)).thenReturn(Optional.of(post2));
        Mockito.when(postRepositoryMock.findById(argThat(value -> !(Long.valueOf(1).equals(value) || Long.valueOf(2).equals(value))))).thenReturn(Optional.empty());
        Mockito.when(commentRepositoryMock.findById(1L)).thenReturn(Optional.ofNullable(c1));

        Mockito.when(commentRepositoryMock.findById(argThat(value -> !(Long.valueOf(1).equals(value) || Long.valueOf(2).equals(value))))).thenReturn(Optional.empty());
        Assertions.assertThrowsExactly(ResourceNotFoundException.class, () -> {
            commentService.deleteComment(1L ,5L);
        });
        Assertions.assertThrowsExactly(ResourceNotFoundException.class, () -> {
            commentService.deleteComment(3L, 4L);
        });
        Assertions.assertThrowsExactly(ResourceNotFoundException.class, () -> {
            commentService.deleteComment(3L, 1L);
        });
        Assertions.assertThrowsExactly(BlogAPIException.class, () -> {
            commentService.deleteComment(2L, 1L);
        });
        commentService.deleteComment(1L, 1L);
        Mockito.verify(commentRepositoryMock, Mockito.times(1)).delete(c1);


    }
}
'''

-------------------------------------------------------------------------------
Test set: com.chuwa.redbook.service.impl.CommentServiceImplTest
-------------------------------------------------------------------------------
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.433 s - in com.chuwa.redbook.service.impl.CommentServiceImplTest


