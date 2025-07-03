# Short Questions - HW9

## Questions

### 1. Why is it better to use a custom exception class (e.g. ResourceNotFoundException) in your application?

Custom exception classes provide several benefits:

**Code clarity**: A `ResourceNotFoundException` immediately tells developers what went wrong, unlike generic `Exception` classes that require reading error messages.

**Precise error handling**: You can handle different custom exceptions differently. For example, `ResourceNotFoundException` returns 404, while `ValidationException` returns 400.

**Better maintenance**: When you need to change how a specific exception is handled, you only need to find places where that custom exception is thrown, rather than searching through generic exceptions.

### 2. Explain how @Table, @Column, and @Id work. What is the default behavior if you don't use them?

**@Table**: Maps an entity class to a database table. Without it, JPA uses the class name as the table name (e.g., `User` class maps to `user` table).

**@Column**: Maps a field to a database column. Without it, JPA uses the field name as the column name, converting camelCase to snake_case (e.g., `firstName` becomes `first_name`).

**@Id**: Marks the primary key field. This is required - without it, JPA throws an error at startup because it doesn't know which field is the primary key.

### 3. What happens if you don't annotate a class with @Entity, but still try to save it with JPA?

JPA won't recognize the class as an entity and will throw an exception like "Unknown entity" when you try to save it using EntityManager or Repository. The `@Entity` annotation is required for JPA to know which classes are database entities.

### 4. What happens if we forget to annotate a controller with @RestController and only use @RequestMapping?

Without `@RestController`, the method's return value won't be automatically serialized to JSON. `@RestController` combines `@Controller` and `@ResponseBody`. Without it, Spring treats the return value as a view name and tries to find a corresponding view template, likely resulting in a 404 error.

### 5. What is the default naming strategy of JPA for tables and columns when no explicit name is given?

**Table names**: Class name converted to lowercase (e.g., `User` → `user`, `UserProfile` → `userprofile`).

**Column names**: Field names converted from camelCase to snake_case (e.g., `firstName` → `first_name`, `userId` → `user_id`).

Note: This strategy may vary depending on the JPA implementation or configuration.

### 6. How does @PathVariable differ from @RequestParam?

**@PathVariable**: Extracts parameters from the URL path (e.g., `/users/123` where `123` is the path variable). These parameters are required - missing them results in 404.

**@RequestParam**: Extracts parameters from query strings (e.g., `/users?name=john&age=25`). These are required by default but can be made optional with `required=false`.

In short: PathVariable for URL path parameters, RequestParam for query string parameters.

---

## Repository Search Implementation

### Task: Write a method in a repository to find all posts with the title containing a certain keyword

### 1. Repository Methods Added

**PostRepository.java:**
```java
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Method using Spring Data JPA naming convention
    // This will automatically generate: SELECT * FROM posts WHERE title LIKE '%keyword%'
    List<Post> findByTitleContaining(String keyword);
    
    // Alternative method using custom query (more control)
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword%")
    List<Post> findByTitleContainingKeyword(@Param("keyword") String keyword);
    
    // Case-insensitive search method
    List<Post> findByTitleContainingIgnoreCase(String keyword);
    
    // Method to find posts where title OR description contains keyword
    List<Post> findByTitleContainingOrDescriptionContaining(String titleKeyword, String descriptionKeyword);
}
```

### 2. Service Layer Implementation

**PostService.java:**
```java
public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
    PostDto getPostById(Long id);
    List<PostDto> findByTitleContaining(String keyword);  // New search method
}
```

**PostServiceImpl.java:**
```java
@Override
public List<PostDto> findByTitleContaining(String keyword) {
    List<Post> posts = postRepository.findByTitleContaining(keyword);
    return posts.stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
}

// Helper method for entity to DTO conversion
private PostDto mapToDTO(Post post) {
    PostDto postDto = new PostDto();
    postDto.setId(post.getId());
    postDto.setTitle(post.getTitle());
    postDto.setDescription(post.getDescription());
    postDto.setContent(post.getContent());
    return postDto;
}
```

### 3. Controller Layer Implementation

**PostController.java:**
```java
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // Create a new post
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto postResponse = postService.createPost(postDto);
        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }

    // Get all posts
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // Search posts by title containing keyword
    @GetMapping("/search")
    public ResponseEntity<List<PostDto>> searchPostsByTitle(@RequestParam String keyword) {
        List<PostDto> posts = postService.findByTitleContaining(keyword);
        return ResponseEntity.ok(posts);
    }

    // Get post by ID
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        PostDto post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }
}
```

### 4. Test Data Created

**Created the following test posts using POST requests:**

1. **测试帖子** - "这是一个测试帖子的描述"
2. **我的第一篇帖子** - "这是描述信息"
3. **从 Postman 创建的帖子** - "这是通过 Postman API 创建的帖子描述"
4. **Spring Boot 学习指南** - "全面的Spring Boot开发教程"
5. **Java 编程技巧** - "Java开发中的实用技巧分享"
6. **MySQL 数据库优化** - "数据库性能调优指南"
7. **前端开发框架对比** - "React vs Vue vs Angular"

### 5. API Testing Results

**Available Endpoints:**
- `GET /api/v1/posts` - Get all posts
- `GET /api/v1/posts/search?keyword=xxx` - Search posts by keyword
- `GET /api/v1/posts/{id}` - Get post by ID
- `POST /api/v1/posts` - Create new post

**Search Test Results:**

1. **Search for "Spring":**
   ```bash
   curl -s "http://localhost:8080/api/v1/posts/search?keyword=Spring"
   ```
   **Result:** Found 1 post - "Spring Boot 学习指南"

2. **Search for "Java":**
   ```bash
   curl -s "http://localhost:8080/api/v1/posts/search?keyword=Java"
   ```
   **Result:** Found 1 post - "Java 编程技巧"

3. **Search for "Boot":**
   ```bash
   curl -s "http://localhost:8080/api/v1/posts/search?keyword=Boot"
   ```
   **Result:** Found 1 post - "Spring Boot 学习指南" (partial match)

4. **Search for "Python":**
   ```bash
   curl -s "http://localhost:8080/api/v1/posts/search?keyword=Python"
   ```
   **Result:** Empty array `[]` (no matches)

5. **Get specific post by ID:**
   ```bash
   curl -s "http://localhost:8080/api/v1/posts/5"
   ```
   **Result:** Returns "Java 编程技巧" post details

### 6. Key Features Implemented

 **Spring Data JPA Automatic Query Generation** - `findByTitleContaining()` automatically generates `WHERE title LIKE '%keyword%'`

 **Partial Matching** - Search "Boot" finds "Spring Boot 学习指南"

 **Case Sensitive Search** - Default behavior (can be changed with `IgnoreCase` method)

 **Multiple Search Options** - Provided several search method variations

 **REST API Integration** - Full CRUD operations with search functionality

 **Proper Layer Architecture** - Repository → Service → Controller separation

 **Empty Result Handling** - Returns empty array when no matches found

### 7. Usage in Postman

**Search Request Setup:**
- **Method:** GET
- **URL:** `http://localhost:8080/api/v1/posts/search?keyword=Spring`
- **Headers:** None required
- **Body:** None required

**Example Search URLs:**
- `http://localhost:8080/api/v1/posts/search?keyword=Spring`
- `http://localhost:8080/api/v1/posts/search?keyword=Java`
- `http://localhost:8080/api/v1/posts/search?keyword=Boot`
