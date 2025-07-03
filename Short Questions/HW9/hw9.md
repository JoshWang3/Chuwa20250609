# 07/02

1. there are some reasons:
    1. A custom exception can improve the readability and clarity of code. It’s good for showing the exact nature of the error, which can help developers track details of error. 
    2. A custom exception can define specific errors differently in the code. 
    3. When the developer maps the custom exception to HTTP status codes using `@ControllerAdvice`. It’s good for RESTful compliance. 
    4. It’s easier for debugging through custom exception message and logging information. 
    5. It’s easier to maintain clearer and cleaner codebase. 
2. In Spring Boot, `@Entity` marks a class as a JPA entity, which is required. Without `@Entity`, Spring Boot wont treat the class as a table-mapped entity. However, `@Table` is not required. If we don’t use `@Table`, the table name will default to the class name. 
    
    ```java
    @Entity
    @Table(name = "student")
    public class Student { ... }
    
    // or
    @Entity
    public class Student { ... }
    ```
    
    `@Id` refers the primary key of the table, which is mandatory. If we don’t use it, Spring Boot will fail with a mapping exception like `AnnotationException`.
    
    `@Column` customize how fields map to DB columns. If missing it, the column name will be same as the field name and other constrains (like nullable, length, etc) will be set by Java type defaults. 
    
3. There will be a runtime error at application startup or during the first persistence operation. An exception like 
    
    ```
    jakarta.persistence.PersistenceException: 
    Unable to locate persister: com.example.model.User
    ```
    
    will be shown. Spring Boot usually walks through all classes annotated with `@Entity` during the application startup and registers them in the EntityManager. If it’s not annotated, the class isn’t registered as a managed type. Hibernate has no table mapping and JPA can’t generate SQL for it. 
    
4. Spring Boot won’t recognize the class as a web controller. The request mapping won’t work. The class is not registered as a controller bean in Spring context. No endpoint is exposed. It will return a 404 Not Found status code. 
    
    Spring uses component scanning to detect controller classes with `@RestController` or `@Controller`. It will allow to register a class as a spring bean and mark it as a web component for handling HTTP requests. 
    
5. It applies a physical naming strategy to convert names. Java camelCase is converted to snake_case. 
6. `@PathVariable` extracts data from the URI path. Example: `/users/{id}` id is the path variable. It’s used for identifying resources. 
    
    ```java
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // URL: /users/42 → id = 42
    }
    ```
    
    `@RequestParam` extracts data from the query string. 
    
    ```java
    @GetMapping("/users")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        // URL: /users?email=test@example.com → email = test@example.com
    }
    ```
    
    [`email=test@example.com`](mailto:email=test@example.com) after question mark is the request param. It’s used for filtering, pagination, search, etc.
    

## Hands on:

1. Changes in codebase
    
    `PostService.java`
    
    ```java
    // new abstract method in this interface
    List<PostDto> getPostsByTitle(String titleKeyWords);
    ```
    
    `PostServiceImpl.java` 
    
    ```java
    // Implements the method here
        public List<PostDto> getPostsByTitle(String titleKeyWords) {
            String keyword = titleKeyWords.trim().toLowerCase();
            List<Post> posts = postRepository.findAll();
            List<Post> postsWithKeyword = posts.stream().filter(post -> post.getTitle().toLowerCase().contains(titleKeyWords)).collect(Collectors.toList());
            List<PostDto> postDtos = postsWithKeyword.stream().map(this::mapToDTO).collect(Collectors.toList());
            return postDtos;
        }
    ```
    
    `PostController.java`
    
    ```java
    // add new api public access 
    @GetMapping("/title/{titleKeyWords}")
        public List<PostDto> getPostsByTitle(@PathVariable String titleKeyWords) {
            return postService.getPostsByTitle(titleKeyWords);
        }
     // also change to aviod Ambiguous handler methods
     @GetMapping("/id/{id}")
        public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
            return ResponseEntity.ok(postService.getPostById(id));
        }
     
    ```
    
2. Test screenshot in Postman
    
    keyword: fun
    
    ![Screenshot 2025-07-02 at 9.06.27 PM.png](07%2002%20224b9ebb81cb80ecbf21c99664b31fa4/Screenshot_2025-07-02_at_9.06.27_PM.png)
    
    The results are correct which are matched data in DB.