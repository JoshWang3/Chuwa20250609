# hw14 submission

## Q1: Explain and compare following concepts, provide specific examples when doing comparison

### Answer:
Testing related:
#### Unit Testing
Tests individual units/components (e.g., methods, classes) in isolation, typically using mocking for dependencies.

Example:

Testing a service method with JUnit and Mockito.
```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserById() {
        User user = new User(1L, "Alice");
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertEquals("Alice", userService.getUserById(1L).getName());
    }
}
```

#### Functional Testing
Tests a specific business functionality or feature to ensure it behaves as expected.

Example:

Test the /register endpoint to ensure it creates a user correctly.

Use @WebMvcTest or tools like Postman, REST-assured.
```java
@WebMvcTest(UserController.class)
class UserControllerFunctionalTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateUserSuccessfully() throws Exception {
        mockMvc.perform(post("/api/users")
            .content("{\"name\": \"Alice\"}")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }
}
```
#### Integration Testing
Tests multiple components/modules together (e.g., controller + service + repository).

Example:

Using @SpringBootTest with H2 in-memory DB.
```java
@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testUserWorkflow() throws Exception {
        mockMvc.perform(post("/api/users")
            .content("{\"name\":\"Bob\"}")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());

        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Bob"));
    }
}
```

#### Regression Testing
Re-runs existing tests to ensure new changes haven’t broken any existing functionality.

Example:

After modifying the user creation logic, re-run all related unit, integration, and functional tests.

#### Smoke Testing
Basic test to check if the critical paths of the application are working after a new build/deployment.

Example:

Verify that:

App starts without error

Health check /actuator/health returns UP

Home endpoint / returns 200 OK

#### Performance Testing
Tests system responsiveness, speed, and scalability under expected load.

Example tools: JMeter, Gatling

Scenario:

Test how /api/users behaves under 1000 concurrent requests and whether response time stays under 200ms.

#### Stress Testing
Tests how the application behaves under extreme/unexpected load (beyond expected limits).

Example:

Simulate 10,000 simultaneous login attempts to see if Spring Security + database connection pool can recover or fail gracefully.
#### A/B Testing
Tests two or more variations of a feature (A vs. B) with different user groups to determine which performs better.

Example:

Serve two versions of the registration flow (one-page vs. multi-page) and compare conversion rates using a feature toggle and analytics.

#### End-to-End Testing
Tests the entire application workflow from the user’s perspective (frontend → backend → DB).

Example tools: Selenium, Cypress

Scenario:

User logs in, browses a product, adds it to the cart, and checks out — the entire journey is validated.

#### User Acceptance Testing
Final phase where real users (or client) test the system to validate that it meets the business requirements.

Example:

Client logs into the deployed QA environment and verifies if the "Generate Invoice" feature works as expected before sign-off.


Environment related:
#### Development
The environment where developers write, test, and debug code during active development.

Key Characteristics:

Frequently changes

Debugging tools enabled

Lower performance requirements

Often runs locally or in a dev server

Uses test data (often mocked or dummy)

Spring Boot Example:

Run with application-dev.properties

Embedded H2 or local MySQL database

Logs in DEBUG level

Swagger UI enabled
```
# application-dev.properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop
logging.level.root=DEBUG
```

#### QA (Quality Assurance)
A controlled environment where testers validate features against business requirements.

Key Characteristics:

Contains more stable builds

Uses real but sanitized data

Automated tests are run here (regression, integration)

May mimic production-like configuration for accuracy

Spring Boot Example:

Run with application-qa.properties

Connects to a shared QA database

CI/CD runs automated tests and deploys here
```
# application-qa.properties
spring.datasource.url=jdbc:mysql://qa-db-server:3306/mydb
spring.jpa.hibernate.ddl-auto=none
logging.level.root=INFO
```

#### Pre-prod/Staging
An environment that is almost identical to production, used for final validation before deployment.

Key Characteristics:

Mirrors production setup (infrastructure, data size, services)

Used for load testing, smoke testing, UAT

Usually connected to production-like services with dummy credentials

Strictly controlled (not for active development)

Spring Boot Example:

Run with application-staging.properties

Same configurations as prod except:

Payments go to sandbox

Emails go to test inbox
```
# application-staging.properties
spring.profiles.active=staging
payment.gateway.url=https://sandbox.payment.com
email.send=false
```

#### Production
The live environment where actual users access the system.

Key Characteristics:

High stability, security, and performance

Real customer data

Monitoring and alerting enabled

Strict change management and deployment policies

Spring Boot Example:

Run with application-prod.properties

Logging is minimal (WARN, ERROR)

Security features enabled

Real services connected
```
# application-prod.properties
spring.datasource.url=jdbc:mysql://prod-db-server:3306/mydb
spring.jpa.hibernate.ddl-auto=validate
logging.level.root=WARN
management.endpoints.web.exposure.include=health,info
```

## Q2: Write unit test for CommentServiceImpl.java: https://github.com/CTYue/springboot-redbook/blob/10_testing/src/main/java/com/chuwa/redbook/service/impl/CommentServiceImpl.java
### Answer:
Added CommentServiceImplTest.java

```java
package com.chuwa.redbook.service.impl;

import com.chuwa.redbook.dao.CommentRepository;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.exception.BlogAPIException;
import com.chuwa.redbook.exception.ResourceNotFoundException;
import com.chuwa.redbook.payload.CommentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CommentServiceImplTest {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        postRepository = mock(PostRepository.class);
        modelMapper = new ModelMapper();
        commentService = new CommentServiceImpl(commentRepository, postRepository, modelMapper);
    }

    @Test
    void testCreateComment_success() {
        Post post = new Post();
        post.setId(1L);

        CommentDto dto = new CommentDto("John", "john@example.com", "Hello world");
        Comment comment = modelMapper.map(dto, Comment.class);
        comment.setPost(post);

        Comment savedComment = modelMapper.map(dto, Comment.class);
        savedComment.setId(100L);
        savedComment.setPost(post);

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.save(any(Comment.class))).thenReturn(savedComment);

        CommentDto result = commentService.createComment(1L, dto);

        assertEquals("John", result.getName());
        assertEquals("john@example.com", result.getEmail());
        assertEquals("Hello world", result.getBody());
    }

    @Test
    void testCreateComment_postNotFound() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        CommentDto dto = new CommentDto("John", "john@example.com", "Hello world");

        assertThrows(ResourceNotFoundException.class, () -> {
            commentService.createComment(1L, dto);
        });
    }

    @Test
    void testGetCommentsByPostId() {
        List<Comment> commentList = List.of(
                new Comment(1L, "a", "a@example.com", "comment 1"),
                new Comment(2L, "b", "b@example.com", "comment 2")
        );

        when(commentRepository.findByPostId(1L)).thenReturn(commentList);

        List<CommentDto> result = commentService.getCommentsByPostId(1L);

        assertEquals(2, result.size());
    }

    @Test
    void testGetCommentById_success() {
        Post post = new Post();
        post.setId(1L);

        Comment comment = new Comment(100L, "c", "c@example.com", "some comment");
        comment.setPost(post);

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(100L)).thenReturn(Optional.of(comment));

        CommentDto result = commentService.getCommentById(1L, 100L);

        assertEquals("c", result.getName());
    }

    @Test
    void testGetCommentById_wrongPost() {
        Post post = new Post(); post.setId(1L);
        Post otherPost = new Post(); otherPost.setId(2L);

        Comment comment = new Comment(100L, "c", "c@example.com", "some comment");
        comment.setPost(otherPost);

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(100L)).thenReturn(Optional.of(comment));

        assertThrows(BlogAPIException.class, () -> {
            commentService.getCommentById(1L, 100L);
        });
    }

    @Test
    void testUpdateComment_success() {
        Post post = new Post(); post.setId(1L);
        Comment comment = new Comment(100L, "old", "old@example.com", "old");
        comment.setPost(post);

        CommentDto updatedDto = new CommentDto("new", "new@example.com", "new");

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(100L)).thenReturn(Optional.of(comment));
        when(commentRepository.save(comment)).thenReturn(comment);

        CommentDto result = commentService.updateComment(1L, 100L, updatedDto);

        assertEquals("new", result.getName());
    }

    @Test
    void testDeleteComment_success() {
        Post post = new Post(); post.setId(1L);
        Comment comment = new Comment(1L, "x", "x@example.com", "to delete");
        comment.setPost(post);

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        commentService.deleteComment(1L, 1L);

        verify(commentRepository, times(1)).delete(comment);
    }
}
```