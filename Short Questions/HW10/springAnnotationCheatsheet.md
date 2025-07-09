# Spring Annotations Cheat Sheet

---

## 1. Core Stereotype Annotations

### `@Component`

**Used to declare a general Spring-managed bean.**

```java
@Component
public class MyHelper { ... }
```

### `@Service`

**Used for service layer business logic.**

```java
@Service
public class UserService { ... }
```

### `@Repository`

**Used for DAO layer with exception translation.**

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> { }
```

### `@Controller`

**Used for MVC controllers.**

```java
@Controller
public class HomeController { ... }
```

### `@RestController`

**Used for REST APIs returning JSON/XML.**

```java
@RestController
@RequestMapping("/api")
public class UserController { ... }
```

---

## 2. Dependency Injection

### `@Autowired`

**Used for automatic dependency injection by type.**

```java
@Autowired
private UserService userService;
```

### `@Qualifier`

**Used to specify the bean to inject when multiple candidates exist.**

```java
@Autowired
@Qualifier("mySpecificBean")
private MyBean myBean;
```

### `@Value`

**Used to inject values from properties or expressions.**

```java
@Value("${server.port}")
private int serverPort;
```

---

## 3. Spring Configuration

### `@Configuration`

**Used to define configuration classes.**

```java
@Configuration
public class AppConfig { ... }
```

### `@Bean`

**Used to define beans in configuration classes.**

```java
@Bean
public MyService myService() {
    return new MyServiceImpl();
}
```

---

## 4. Request Mapping

### `@RequestMapping` / `@GetMapping` / `@PostMapping`

**Used to map HTTP requests to handler methods.**

```java
@GetMapping("/users")
public List<User> getUsers() { ... }
```

### `@PathVariable`

**Used to extract URI path variables.**

```java
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) { ... }
```

### `@RequestParam`

**Used to extract query parameters.**

```java
@GetMapping("/search")
public List<User> search(@RequestParam String name) { ... }
```

### `@RequestBody`

**Used to bind request body to an object.**

```java
@PostMapping("/users")
public User create(@RequestBody User user) { ... }
```

### `@ResponseBody`

**Used to return data in the HTTP response body.**

```java
@ResponseBody
public User getUser() { ... }
```

---

## 5. Spring Boot Annotation

### `@SpringBootApplication`

**Used for Spring Boot applications, enabling auto-configuration and component scanning.**

```java
@SpringBootApplication
public class MyApp { 
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
```

---

## 6. JPA Annotations

### `@Entity`

**Used to declare a JPA entity for ORM.**

```java
@Entity
public class User { ... }
```

### `@Id`

**Used to mark the primary key.**

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

### `@GeneratedValue`

**Used to specify PK generation strategy.**

### `@Table`

**Used to specify the database table name.**

```java
@Table(name = "users")
```

### `@Column`

**Used to specify column mapping.**

```java
@Column(name = "email")
private String email;
```

### `@OneToMany`, `@ManyToOne`, etc.

**Used to define entity relationships.**

```java
@OneToMany(mappedBy = "user")
private List<Post> posts;
```

### `@JoinColumn`

**Used to define foreign key columns.**

```java
@ManyToOne
@JoinColumn(name = "user_id")
private User user;
```

---

## 7. Transaction Management

### `@Transactional`

**Used to execute methods within a transaction.**

```java
@Transactional
public void transfer() { ... }
```

---

## 8. Caching

### `@Cacheable`

**Used to cache method results.**

```java
@Cacheable("users")
public User getUser(Long id) { ... }
```

### `@CacheEvict`

**Used to clear cache entries.**

```java
@CacheEvict(value = "users", allEntries = true)
public void clearCache() { ... }
```

---

## 9. Validation Annotations

**Used for bean validation:**

```java
@NotNull
@Size(min = 2, max = 30)
@Email
@Min(18)
@Max(60)
```

Example:

```java
@Size(min = 2, max = 30)
private String name;
```

---

