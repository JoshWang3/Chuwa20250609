# hw9 submission

## Q1. Why is it better to use a custom exception class (e.g. ResourceNotFoundException) in your application?

### Answer:
Using custom exception classes provides:

it clearly indicates the type of error (e.g. resource not found, bad request).
you can catch specific exceptions and return meaningful HTTP status codes and messages.
avoids generic exceptions everywhere.
integrates well with `@ControllerAdvice` to handle specific exceptions globally in your API.

## Q2. Explain how `@Table`, `@Column`, and `@Id` work. What is the default behavior if you don’t use them?

### Answer:
`@Table`

    - Maps an entity class to a specific database table.
    - Example: `@Table(name="posts")` maps to table `posts`.
    - Default: If not used, JPA uses the class name as the table name.

`@Column`

    - Maps a class field to a specific database column.
    - Allows customization (e.g. `@Column(name="post_title")`).
    - Default: If not used, JPA maps the field name directly to the column name.

`@Id`

    - Marks a field as the **primary key**.
    - No default: If not specified, JPA does not know which field is the identifier, and saving the entity will fail.

## Q3. What happens if you don’t annotate a class with `@Entity`, but still try to save it with JPA?

### Answer:
JPA will **throw a runtime exception** (e.g. `MappingException`) because it does not recognize the class as a persistent entity.

Without `@Entity`, the class is not mapped to any database table.

## Q4. What happens if we forget to annotate a controller with `@RestController` and only use `@RequestMapping`?

### Answer:
`@RequestMapping` only maps URLs to methods but does not declare the class as a controller.

Without `@RestController` or `@Controller`, Spring will not detect it as a controller bean, and it will not handle any web requests.

## Q5. What is the default naming strategy of JPA for tables and columns when no explicit name is given?

### Answer:
JPA uses the **class name as table name** and **field names as column names** by default.

For example, class `Post` with field `title` maps to table `Post` and column `title` unless overridden.

## Q6. How does `@PathVariable` differ from `@RequestParam`?**

### Answer:
`@PathVariable`: Extracts values **from the URL path itself**.<br>Example: `/posts/{id}` maps `{id}` to a method parameter.

`@RequestParam`: Extracts values **from query parameters**.<br>Example: `/posts?id=5` maps `id` to a method parameter.

## Hands On:Write a method in a repository to find all posts with the title containing a certain keyword. (Create some test posts if necessary)

### Task Implementation:

### **1. Modified PostRepository.java**

Added:

```
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // Finds posts with title containing the keyword (case-insensitive)
    List<Post> findByTitleContainingIgnoreCase(String keyword);
}
```

### **2. Modified PostService.java**

Added:

```
import java.util.List;

public interface PostService {
    List<Post> getPostsByTitleKeyword(String keyword);
}

```

### **3. Modified PostServiceImpl.java**

Added:

```
@Override
public List<Post> getPostsByTitleKeyword(String keyword) {
        return postRepository.findByTitleContainingIgnoreCase(keyword);
        }
```

### **4. Modified PostController.java**

Added:

```
@GetMapping("/search")
public List<Post> searchPostsByTitle(@RequestParam String keyword) {
        return postService.getPostsByTitleKeyword(keyword);
        }
```

### **Test with Postman**
Method: GET

URL: http://localhost:8088/posts/search?keyword=Chuwa

It will return all posts with titles containing “Chuwa” (case-insensitive).
