# Annotations

## Annotations used for IoC
### @Component
This annotation is used to define a Spring Bean. It is a general-purpose stereotype indicating a component (Bean) managed by the Spring container. It can be applied to any layer in the application, such as Controller, Service, DAO, etc.
```java
@Component
public class MyService {
    public void doSomething() {
        System.out.println("Doing something...");
    }
}
```

### @Controller
Typically used on the Controller layer to mark the class as a Spring-managed Bean.
```java
@Controller
public class MyController {
    @RequestMapping("/hello")
    public String sayHello() {
        return "hello";
    }
}
```

### @Service
Typically used on the Service layer to mark the class as a Spring-managed Bean.
```java
@Service
public class UserService {
    public String getUserName() {
        return "Zeliang Yin";
    }
}
```

### @Repository
Typically used on the DAO layer to mark the class as a Spring-managed Bean.
```java
@Repository
public class UserRepository {
    public String findUserById(int id) {
        return "Zeliang Yin";
    }
}
```

### @PostConstruct
Executed automatically after the Spring Bean is initialized. Commonly used for setup tasks such as loading configuration, connecting to resources, or logging.
```java
@Component
public class MyBean {

    @PostConstruct
    public void init() {
        System.out.println("MyBean initialized!");
    }
}
```

### @PreDestroy
Executed before the Bean is destroyed to perform any cleanup operations.
```java
@Component
public class MyBean {

    @PreDestroy
    public void destroy() {
        System.out.println("MyBean is about to be destroyed. Performing cleanup.");
    }
}
```

### @Scope
Defines the scope of the Bean, telling Spring how to manage and instantiate it.

| Scope | Description |
| --- | --- |
| singleton | Default. A single shared instance exists in the Spring container. |
| prototype | A new instance is created every time the Bean is requested. |
| request | A new Bean is created for every HTTP request (Web applications only). |
| session | A new Bean is created for every HTTP session (Web applications only). |
| application | A single Bean is shared across the entire ServletContext (Web applications only). |
| websocket | A new Bean is created for every WebSocket session (WebSocket applications only). |

```java
@Component
@Scope("prototype")
public class MyPrototypeBean {
    public MyPrototypeBean() {
        System.out.println("A new MyPrototypeBean instance created");
    }
}
```


### @Autowired
Used to automatically inject dependencies, simplifying the need for constructors or setters.
```java
@Service
public class UserService {
    public void sayHi() {
        System.out.println("Hello from UserService");
    }
}

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public void handleRequest() {
        userService.sayHi(); // Automatically injected, no need to manually `new`
    }
}
```

## Annotations used by Controller
### @RestController
Used to create RESTful Web Services. It combines `@Controller` and `@ResponseBody`. It indicates that the class is a controller, and the returned data from its methods will be written directly to the HTTP response body (usually in JSON or XML format), not rendered as a view (HTML page).

| Feature | `@Controller` | `@RestController` |
| --- | --- | --- |
| Return Type | Defaults to view name (HTML page) | Defaults to JSON, XML, etc. |
| Needs `@ResponseBody` | Yes | No |
| Usage | Traditional MVC for views | RESTful API for data |


### @RequestMapping
Maps HTTP requests to handler methods or classes. It supports multiple HTTP methods (GET, POST, PUT, DELETE, etc.) and can specify URL path, request method, request parameters, etc.

### @GetMapping
Shortcut for `@RequestMapping` used specifically for HTTP GET requests.

### @PostMapping
Shortcut for `@RequestMapping` used specifically for HTTP POST requests.

### @PutMapping
Shortcut for `@RequestMapping` used specifically for HTTP PUT requests.

### @DeleteMapping
Shortcut for `@RequestMapping` used specifically for HTTP DELETE requests.

### @RequestBody
Binds the HTTP request body to a method parameter. Commonly used in POST, PUT, or PATCH requests.

### @PathVariable
Used to extract dynamic values from the URL path in RESTful APIs.

### @RequestParam
Used to extract query parameters from the URL, typically in the form of key-value pairs.

```java
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users")
    public User getUser(@RequestParam(defaultValue = "10") int age) {
        return new User("Zeliang", 25);
    }

    @PostMapping("/users")
    public String createUser(@RequestBody User user) {
        return "User created: " + user.getName();
    }

    @PutMapping("/users")
    public String updateUser(@RequestBody User user) {
        return "User updated: " + user.getName();
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "User with ID " + id + " deleted";
    }
}
```


## Annotations used by Repository
### `@Entity`
Marks a class as a JPA entity (a table in the database).
```java
@Entity
public class User {
    @Id
    private Long id;
}
```


### `@Table`
Specifies the name of the table in the database.
```java
@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;
}
```


### `@Id`
Specifies the primary key of an entity.
```java
@Id
private Long id;
```


### `@Column`
Maps a field to a specific column in the table.
```java
@Column(name = "name", nullable = false)
private String name;
```


### `@CreationTimestamp`
Automatically sets the timestamp when the entity is first persisted (inserted into the database).
```java
@CreationTimestamp
private LocalDateTime createdDateTime;
```


### `@UpdateTimestamp`
Automatically updates the timestamp whenever the entity is updated (any change is saved to the DB).
```java
@UpdateTimestamp
private LocalDateTime updatedDateTime;
```


## Annotations used for exception handling
### `@ControllerAdvice`
Used for global exception handling, data binding, and model attribute configuration across all `@Controller` classes.



### `@ExceptionHandler`
Used inside `@ControllerAdvice` or any `@Controller` to handle specific exceptions thrown during controller method execution. It lets you catch exceptions and return a custom response instead of a default error.

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", 404);
        body.put("message", ex.getMessage());
        body.put("data", null);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", 500);
        body.put("message", "Internal server error: " + ex.getMessage());
        body.put("data", null);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```


### `@ResponseStatus`
Marks a class or method with a specific **HTTP status code** to return to the client when an exception occurs or a response is returned.
```java
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```



## Annotations used for application configuration
### `@SpringBootApplication`
Combines three key annotations: `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan` to simplify Spring Boot app configuration.


### `@Configuration`
Marks the class as a source of Spring Bean definitions.


### `@EnableAutoConfiguration`
Automatically configures the application based on the classpath dependencies.


### `@ComponentScan`
Scans the specified package and its sub-packages for Spring components.

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```


### `@Bean`
Used to explicitly declare a Bean in a method. Must be used inside a class annotated with `@Configuration`.
```java
@Configuration
public class MyConfig {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```



## Annotations used for security
### `@PreAuthorize`
Used to authorize method access before execution.
```java
@PreAuthorize("hasRole('ADMIN')")
public void deleteUser(Long id) {
    // only ADMIN can access
}
```


## Annotations used for AOP
### `@Aspect`
Marks a class as an aspect containing cross-cutting concerns.
```java
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.service.*.*(..))")
    public void logBeforeMethod() {
        System.out.println("Method is about to be called...");
    }
}
```


### `@PointCut`
Defines reusable expressions that match join points.
```java
@Pointcut("execution(* com.example.service.*.*(..))")
public void allServiceMethods() {}
```


### `@Before`
Executes before the matched method (join point).
```java
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.service.UserService.*(..))")
    public void logBeforeServiceMethod(JoinPoint joinPoint) {
        System.out.println("Calling method: " + joinPoint.getSignature().getName());
    }
}
```


### `@After`
Executes after the matched method (join point).
```java
@Aspect
@Component
public class AuditAspect {

    @After("execution(* com.example.service.UserService.*(..))")
    public void afterAnyUserServiceMethod(JoinPoint joinPoint) {
        System.out.println("Finished method: " + joinPoint.getSignature().getName());
    }
}
```


### `@AfterReturning`
Executes after the method returns successfully.
```java
@Aspect
@Component
public class LoggingAspect {

    @AfterReturning(
        pointcut = "execution(* com.example.service.UserService.getUserById(..))",
        returning = "user"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object user) {
        System.out.println("Method returned successfully: " + joinPoint.getSignature().getName());
        System.out.println("Returned value: " + user);
    }
}
```


### `@AfterThrowing`
Executes only when a method throws an exception.
```java
@Aspect
@Component
public class ExceptionLogger {

    @AfterThrowing(
        pointcut = "execution(* com.example.service.UserService.*(..))",
        throwing = "ex"
    )
    public void logException(JoinPoint joinPoint, Throwable ex) {
        System.out.println("Exception in method: " + joinPoint.getSignature().getName());
        System.out.println("Exception: " + ex.getMessage());
    }
}
```


### `@Around`
Intercepts method execution. Can run code before and after, modify return values, or handle exceptions.
```java
@Aspect
@Component
public class TimingAspect {

    @Around("execution(* com.example.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("Before: " + joinPoint.getSignature().getName());

        Object result;
        try {
            result = joinPoint.proceed(); // proceed with the original method
        } catch (Throwable ex) {
            System.out.println("Exception: " + ex.getMessage());
            throw ex; // rethrow if needed
        }

        long duration = System.currentTimeMillis() - start;
        System.out.println("After: " + joinPoint.getSignature().getName() + " took " + duration + "ms");

        return result;
    }
}
```
