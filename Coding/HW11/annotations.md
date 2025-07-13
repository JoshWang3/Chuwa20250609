# Spring Annotations Reference

---

## Configuration (Dependency Injection)
- **`@Configuration`** Declares configuration class
- **`@Bean`** Declares a Spring-managed bean
- **`@Autowired`** Injects dependencies automatically 
- **`@Qualifier`** Specifies which bean to inject when multiple are available 
- **`@Component`** Marks a class as a Spring-managed component
- **`@Service`** Specialized component for service layer
- **`@Repository`** The class is responsible for data access and persistence logic
- **`@SpringBootApplication`** Combination of `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`
- **`@ComponentScan`** – Specifies packages to scan for components
- **`@EnableAutoConfiguration`** – Enables Spring Boot’s auto-configuration mechanism
- **`@Inject`** – Java standard annotation (JSR-330) for dependency injection
- **`@Resource`** – Java EE annotation that injects dependencies by name

## REST (Web)
- **`@Controller`** Web controller in Spring MVC
- **`@RestController`**  Combines `@Controller` and `@ResponseBody`
- **`@RequestMapping`** Maps web requests to controller methods
- **`@GetMapping`** Maps HTTP GET requests 
- **`@PostMapping`** Maps HTTP POST requests  
- **`@PutMapping`** Maps HTTP PUT requests 
- **`@DeleteMapping`** Maps HTTP DELETE requests 
- **`@PatchMapping`** Maps HTTP PATCH requests 
- **`@PathVariable`** Binds URI path variable to method parameter
- **`@RequestParam`** Binds query parameter to method parameter
- **`@RequestBody`** – Binds HTTP request body to an object
- **`@ResponseBody` – Indicates the return value should be written to the HTTP response body   
- **`@ModelAttribute`** – Binds values to a model or method argument.
- **`@ExceptionHandler`** – Handles specific exceptions and returns a response
- **`@ControllerAdvice`** – Global exception handler and binder across controllers

## JPA (Persistence Layer)
- **`@Entity`** Declares a class as a JPA entity (mapped to a table)
- **`@Id`** Marks the primary key field
- **`@GeneratedValue`** Specifies generation strategy for primary keys
- **`@Table`** Specifies the table name (optional if default is fine)
- **`@Column`** Customizes column mapping
- **`@ManyToOne`, `@OneToMany`, `@OneToOne`, `@ManyToMany`** Defines relationships between entities
- **`@JoinColumn`** Customizes the foreign key column
- **`@Query`** Used to write custom JPQL or native SQL
- **`@Param`** Binds method parameters to named parameters in `@Query` 
                   
## TEST
- **`@SpringBootTest`** – Loads Spring context for integration tests.
- **`@TestConfiguration`** – Custom configuration for tests.
- **`@Transactional`** – Wraps method in a database transaction.