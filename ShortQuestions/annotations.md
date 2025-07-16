# Spring & Java Annotations Reference

### Bean Definition & Configuration
- `@Component` – Marks a class as a Spring-managed component.
- `@Service` – Marks a class as a service component (specialized `@Component`).
- `@Repository` – Marks a class as a DAO component (with persistence exception translation).
- `@Controller` – Marks a class as a Spring MVC controller.
- `@RestController` – Shortcut for `@Controller` + `@ResponseBody`.
- `@Configuration` – Indicates that the class declares one or more `@Bean` methods.
- `@Bean` – Declares a bean from a method in a `@Configuration` class.  
  - Often used for third-party libraries where we can’t annotate source classes directly.
- `@ComponentScan` – Specifies packages to scan for annotated components.
- `@EnableAutoConfiguration` – Enables auto-configuration based on classpath and properties.

### Dependency Injection
- `@Autowired` – Automatically injects dependencies by type.
- `@Qualifier` – Used with `@Autowired` to resolve ambiguity by bean name.
- `@Primary` – Marks a bean as the default for autowiring if multiple candidates exist.
- `@Inject` – Java standard annotation for dependency injection (same as `@Autowired`).
- `@Resource` – Injects dependency by bean name.
- DI Resolution Priority:
  - `@Qualifier` > `@Primary` > By Name > By Type > Exception


### Scope & Lifecycle
- `@Scope("singleton")` – Default Spring bean scope.
- `@Scope("prototype")` – New bean instance per request.
- `@PostConstruct` – Method to run after dependency injection is done.
- `@PreDestroy` – Method to run before bean is removed from container.
- Singleton beans are created during container startup.
- Prototype beans are created each time they are requested (e.g., via `getBean()`).

## Spring MVC Annotations

### Request Mapping
- `@RequestMapping` – Maps HTTP requests to handler methods.
- `@GetMapping` – Shortcut for `@RequestMapping(method = RequestMethod.GET)`.
- `@PostMapping` – Shortcut for `@RequestMapping(method = RequestMethod.POST)`.
- `@PutMapping` – Shortcut for PUT request handlers.
- `@DeleteMapping` – Shortcut for DELETE request handlers.

### Request Parameters and Path Variables
- `@PathVariable` – Binds URI template variable to method parameter.
- `@RequestParam` – Binds query string or form data to method parameter.
- `@RequestBody` – Binds the body of the HTTP request to a method parameter.
- `@ResponseBody` – Indicates that return value should be bound to web response body.

## Spring Data JPA Annotations

- `@Entity` – Declares a persistent Java class.
- `@Table` – Specifies the table name in the database.
- `@Id` – Specifies the primary key.
- `@GeneratedValue` – Provides generation strategy for primary keys.
- `@Column` – Specifies column details.
- `@OneToOne` – Defines one-to-one relationship.
- `@OneToMany` – Defines one-to-many relationship.
- `@ManyToOne` – Defines many-to-one relationship.
- `@ManyToMany` – Defines many-to-many relationship.
- `@JoinColumn` – Specifies the foreign key column.
- `@JoinTable` – Defines join table for many-to-many relationship.
- `@CreationTimestamp` – Automatically sets timestamp when entity is created.
- `@UpdateTimestamp` – Automatically sets timestamp when entity is updated.
- `@Transactional` – Marks method/class as transactional.

## Query Annotations

- `@Query` – Defines custom JPQL or native queries.
- `@NamedQuery` – Declares a named JPQL query.
- `@NamedQueries` – Container for multiple `@NamedQuery`.
- `@Param` – Binds method parameter to named query parameter.

## Spring Boot Annotations
- `@SpringBootApplication` – Combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.

## Exception Handling
- `@ResponseStatus` – Specifies HTTP status for exception.
- `@ControllerAdvice` – Global exception handler or controller helper.
- `@ExceptionHandler` – Handles specific exceptions inside a method.
  - Can be applied method-level inside `@ControllerAdvice` to catch defined exceptions.
  - Can use `Exception.class` to catch all uncaught exceptions globally.

## Validation Annotations (JSR-303)
- `@Valid` – Enables validation on request bodies (usually used in @RequestBody parameters). 
- `@NotEmpty` – Field must not be null or empty.
- `@Size(min = x, max = y)` – Validates the length of the field.
- `@Email` – Validates the field is a valid email address.

To enable validation:
- Add spring-boot-starter-validation dependency.
- Annotate fields in DTOs.
- Annotate controller parameter with `@Valid`.
- Customize global errors using `@ControllerAdvice`.

## Spring Actuator 
- Enables endpoints for monitoring  app.
- Common annotations and configs:
- `@SpringBootApplication` (required)
- `management.endpoints.web.exposure.include=* in application.properties`

## GraphQL Annotations (Spring GraphQL)
- `@QueryMapping` – Marks a method as a GraphQL query handler.
- `@MutationMapping` – Marks a method as a GraphQL mutation handler.
- `@Argument` – Binds GraphQL argument to method parameter.

## Lombok Annotations 
- `@Getter`, `@Setter` – Generates getters/setters.
- `@AllArgsConstructor`, `@NoArgsConstructor` – Generates constructors.
- `@Data` – Combines `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`, and `@RequiredArgsConstructor`.

## Spring Security–Specific Annotations
- `@EnableWebSecurity` - Enables Spring Security’s web security support and provides the Spring MVC integration.
- `@EnableGlobalMethodSecurity(prePostEnabled = true)` - Allows the use of method-level security with annotations like `@PreAuthorize`.
- `@PreAuthorize("hasRole('ROLE')")` - Checks the caller has a specific role before allowing access to the method or endpoint.
- `@EnableRedisHttpSession` - Enables Redis-based HTTP session management.

## JWT & Token Handling
- `@Value("${property.key}")` - Injects values from application.properties (used in JwtTokenProvider).