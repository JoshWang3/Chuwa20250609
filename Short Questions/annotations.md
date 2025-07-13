# Spring Annotations Reference

## Core Spring Annotations

### Component Scanning & Stereotypes

#### `@Component`
- **Purpose**: Generic stereotype for any Spring-managed component
- **Usage**: Base annotation for Spring components
```java
@Component
public class MyComponent {
    // Component logic
}
```

#### `@Service`
- **Purpose**: Indicates a business service layer component
- **Usage**: Service layer classes containing business logic
```java
@Service
public class UserService {
    public User findById(Long id) {
        // Business logic
    }
}
```

#### `@Repository`
- **Purpose**: Indicates a data access layer component
- **Usage**: Data access classes, provides exception translation
```java
@Repository
public class UserRepository {
    // Data access logic
}
```

#### `@Controller`
- **Purpose**: Indicates a Spring MVC controller
- **Usage**: Web layer components that handle HTTP requests
```java
@Controller
public class UserController {
    // Web request handling
}
```

#### `@RestController`
- **Purpose**: Combines `@Controller` + `@ResponseBody`
- **Usage**: RESTful web services that return JSON/XML
```java
@RestController
public class UserRestController {
    // REST API endpoints
}
```

### Dependency Injection

#### `@Autowired`
- **Purpose**: Automatic dependency injection
- **Usage**: Constructor, field, or setter injection
```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    // Constructor injection (preferred)
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

#### `@Qualifier`
- **Purpose**: Specifies which bean to inject when multiple candidates exist
- **Usage**: Used with `@Autowired` for disambiguation
```java
@Autowired
@Qualifier("primaryUserService")
private UserService userService;
```

#### `@Primary`
- **Purpose**: Indicates primary bean when multiple candidates exist
- **Usage**: Marks preferred bean for injection
```java
@Service
@Primary
public class PrimaryUserService implements UserService {
    // Primary implementation
}
```

#### `@Value`
- **Purpose**: Injects values from properties files or expressions
- **Usage**: Property injection
```java
@Value("${app.name}")
private String appName;

@Value("#{systemProperties['user.home']}")
private String userHome;
```

## Spring Boot Annotations

#### `@SpringBootApplication`
- **Purpose**: Main Spring Boot application class
- **Usage**: Combines `@Configuration`, `@EnableAutoConfiguration`, `@ComponentScan`
```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

#### `@EnableAutoConfiguration`
- **Purpose**: Enables Spring Boot's auto-configuration
- **Usage**: Automatically configures beans based on classpath
```java
@EnableAutoConfiguration
public class AppConfig {
    // Configuration
}
```

#### `@ConfigurationProperties`
- **Purpose**: Binds external configuration to Java objects
- **Usage**: Type-safe configuration properties
```java
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private String name;
    private String version;
    // getters and setters
}
```

## Configuration Annotations

#### `@Configuration`
- **Purpose**: Indicates a configuration class
- **Usage**: Java-based configuration
```java
@Configuration
public class AppConfig {
    @Bean
    public UserService userService() {
        return new UserService();
    }
}
```

#### `@Bean`
- **Purpose**: Declares a bean definition method
- **Usage**: Method-level annotation in `@Configuration` classes
```java
@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource dataSource() {
        return new HikariDataSource();
    }
}
```

#### `@Profile`
- **Purpose**: Conditional bean registration based on active profiles
- **Usage**: Environment-specific configurations
```java
@Configuration
@Profile("development")
public class DevConfig {
    // Development-specific beans
}
```

#### `@Conditional`
- **Purpose**: Conditional bean creation based on custom conditions
- **Usage**: Advanced conditional logic
```java
@Bean
@Conditional(WindowsCondition.class)
public FileStore fileStore() {
    return new WindowsFileStore();
}
```

## Web MVC Annotations

#### `@RequestMapping`
- **Purpose**: Maps HTTP requests to handler methods
- **Usage**: Class or method level request mapping
```java
@RequestMapping("/users")
public class UserController {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
}
```

#### `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`
- **Purpose**: HTTP method-specific request mappings
- **Usage**: Shorthand for `@RequestMapping` with specific HTTP methods
```java
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) { }

@PostMapping("/users")
public User createUser(@RequestBody User user) { }

@PutMapping("/users/{id}")
public User updateUser(@PathVariable Long id, @RequestBody User user) { }

@DeleteMapping("/users/{id}")
public void deleteUser(@PathVariable Long id) { }
```

#### `@RequestParam`
- **Purpose**: Binds request parameters to method parameters
- **Usage**: Query parameters and form data
```java
@GetMapping("/users")
public List<User> getUsers(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
    return userService.findAll(page, size);
}
```

#### `@PathVariable`
- **Purpose**: Binds URI template variables to method parameters
- **Usage**: RESTful path parameters
```java
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) {
    return userService.findById(id);
}
```

#### `@RequestBody`
- **Purpose**: Binds HTTP request body to method parameter
- **Usage**: JSON/XML request body binding
```java
@PostMapping("/users")
public User createUser(@RequestBody User user) {
    return userService.save(user);
}
```

#### `@ResponseBody`
- **Purpose**: Binds method return value to HTTP response body
- **Usage**: Convert return value to JSON/XML
```java
@RequestMapping("/users/{id}")
@ResponseBody
public User getUser(@PathVariable Long id) {
    return userService.findById(id);
}
```

#### `@ResponseStatus`
- **Purpose**: Marks method/exception class with status code and reason
- **Usage**: Custom HTTP status codes
```java
@PostMapping("/users")
@ResponseStatus(HttpStatus.CREATED)
public User createUser(@RequestBody User user) {
    return userService.save(user);
}
```

## Data & JPA Annotations

#### `@Entity`
- **Purpose**: Marks a class as JPA entity
- **Usage**: Database table mapping
```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "email", unique = true)
    private String email;
}
```

#### `@Table`
- **Purpose**: Specifies database table details
- **Usage**: Table name and constraints
```java
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User { }
```

#### `@Id`
- **Purpose**: Marks primary key field
- **Usage**: Entity primary key
```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

#### `@GeneratedValue`
- **Purpose**: Specifies primary key generation strategy
- **Usage**: Auto-generated primary keys
```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

#### `@Column`
- **Purpose**: Specifies column mapping details
- **Usage**: Column constraints and properties
```java
@Column(name = "email", nullable = false, unique = true, length = 100)
private String email;
```

#### `@JoinColumn`
- **Purpose**: Specifies foreign key column
- **Usage**: Relationship mapping
```java
@ManyToOne
@JoinColumn(name = "department_id")
private Department department;
```

#### `@OneToMany`, `@ManyToOne`, `@OneToOne`, `@ManyToMany`
- **Purpose**: Define entity relationships
- **Usage**: JPA associations
```java
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
private List<Order> orders;

@ManyToOne
@JoinColumn(name = "department_id")
private Department department;
```

## Validation Annotations

#### `@Valid`
- **Purpose**: Triggers validation of nested objects
- **Usage**: Method parameters and fields
```java
@PostMapping("/users")
public User createUser(@Valid @RequestBody User user) {
    return userService.save(user);
}
```

#### `@NotNull`, `@NotEmpty`, `@NotBlank`
- **Purpose**: Null and empty validation
- **Usage**: Field validation
```java
public class User {
    @NotNull
    private Long id;
    
    @NotEmpty
    private String name;
    
    @NotBlank
    private String email;
}
```

#### `@Size`, `@Min`, `@Max`
- **Purpose**: Size and range validation
- **Usage**: Constraint validation
```java
@Size(min = 2, max = 50)
private String name;

@Min(18)
@Max(100)
private Integer age;
```

#### `@Email`, `@Pattern`
- **Purpose**: Format validation
- **Usage**: String format constraints
```java
@Email
private String email;

@Pattern(regexp = "^[0-9]{10}$")
private String phoneNumber;
```

## Transaction Management

#### `@Transactional`
- **Purpose**: Declarative transaction management
- **Usage**: Method or class level transactions
```java
@Service
@Transactional
public class UserService {
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public User save(User user) {
        return userRepository.save(user);
    }
}
```

## Security Annotations

#### `@EnableWebSecurity`
- **Purpose**: Enables Spring Security configuration
- **Usage**: Security configuration classes
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Security configuration
}
```

#### `@EnableGlobalMethodSecurity`
- **Purpose**: Enables method-level security annotations
- **Usage**: Used with @EnableWebSecurity to enable @PreAuthorize, @PostAuthorize, etc.
```java
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Security configuration with method-level security enabled
}
```

#### `@PreAuthorize`, `@PostAuthorize`
- **Purpose**: Method-level security
- **Usage**: Authorization checks
```java
@PreAuthorize("hasRole('ADMIN')")
public void deleteUser(Long id) {
    userRepository.deleteById(id);
}

@PostAuthorize("returnObject.owner == authentication.name")
public User getUser(Long id) {
    return userRepository.findById(id);
}
```

## Testing Annotations

#### `@SpringBootTest`
- **Purpose**: Integration testing with Spring Boot
- **Usage**: Full application context loading
```java
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    
    @Test
    void testFindById() {
        // Integration test
    }
}
```

#### `@WebMvcTest`
- **Purpose**: Web layer testing
- **Usage**: Test Spring MVC components
```java
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService;
}
```

#### `@DataJpaTest`
- **Purpose**: JPA repository testing
- **Usage**: Test data layer components
```java
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private UserRepository userRepository;
}
```

#### `@MockBean`
- **Purpose**: Mock beans in Spring test context
- **Usage**: Replace beans with mocks
```java
@SpringBootTest
class UserServiceTest {
    @MockBean
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;
}
```

## Scheduling & Async

#### `@EnableScheduling`
- **Purpose**: Enables scheduled task execution
- **Usage**: Configuration classes
```java
@Configuration
@EnableScheduling
public class SchedulingConfig {
    // Scheduling configuration
}
```

#### `@Scheduled`
- **Purpose**: Marks methods for scheduled execution
- **Usage**: Periodic task execution
```java
@Component
public class ScheduledTasks {
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        // Scheduled task
    }
    
    @Scheduled(cron = "0 0 12 * * ?")
    public void dailyTask() {
        // Daily at noon
    }
}
```

#### `@EnableAsync`
- **Purpose**: Enables asynchronous method execution
- **Usage**: Configuration classes
```java
@Configuration
@EnableAsync
public class AsyncConfig {
    // Async configuration
}
```

#### `@Async`
- **Purpose**: Marks methods for asynchronous execution
- **Usage**: Non-blocking method calls
```java
@Service
public class EmailService {
    @Async
    public CompletableFuture<String> sendEmail(String recipient, String message) {
        // Async email sending
        return CompletableFuture.completedFuture("Email sent");
    }
}
```

## Cache Annotations

#### `@EnableCaching`
- **Purpose**: Enables Spring's caching feature
- **Usage**: Configuration classes
```java
@Configuration
@EnableCaching
public class CacheConfig {
    // Caching configuration
}
```

#### `@Cacheable`
- **Purpose**: Triggers cache population
- **Usage**: Method-level caching
```java
@Service
public class UserService {
    @Cacheable(value = "users", key = "#id")
    public User findById(Long id) {
        // Expensive operation
        return userRepository.findById(id);
    }
}
```

#### `@CacheEvict`
- **Purpose**: Triggers cache eviction
- **Usage**: Cache invalidation
```java
@CacheEvict(value = "users", key = "#user.id")
public User updateUser(User user) {
    return userRepository.save(user);
}
```

## Common Patterns

### REST API Controller Pattern
```java
@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        User updatedUser = userService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
```

### Service Layer Pattern
```java
@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }
    
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
```

### Configuration Class Pattern
```java
@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class DatabaseConfig {
    
    @Value("${spring.datasource.url}")
    private String url;
    
    @Bean
    @Primary
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        return new HikariDataSource(config);
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
```