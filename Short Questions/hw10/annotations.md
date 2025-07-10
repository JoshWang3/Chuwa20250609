# Comprehensive List of Java Annotations

## Entity and Table Mapping (JPA/Hibernate)
- **@Entity**: Marks a class as a persistent entity to be mapped with a database table.
- **@Table**: Specifies the name of the database table associated with the entity.
- **@Id**: Marks a field as the primary key.
- **@GeneratedValue(strategy = GenerationType.IDENTITY)**: Specifies the strategy for primary key generation (e.g., auto-increment).
- **@Column**: Specifies the details of a column in the database table.
- **@Transient**: Marks a field to be ignored by the persistence framework (not mapped to the database).
- **@Lob**: Indicates that a field is a large object (e.g., binary data or text).

## Relationships Between Entities
- **@OneToOne**: Defines a one-to-one relationship between entities.
- **@ManyToOne**: Defines a many-to-one relationship between entities.
- **@OneToMany**: Defines a one-to-many relationship between entities.
- **@ManyToMany**: Defines a many-to-many relationship between entities.
- **@JoinColumn**: Specifies the foreign key column for relationships.
- **@JoinTable**: Specifies the join table for a many-to-many relationship.

## Queries and Persistence
- **@NamedQuery**: Defines a named query for an entity.
- **@Query**: Specifies a custom query using JPQL or native SQL.
- **@Param**: Used to bind method parameters to query parameters.
- **@PersistenceContext**: Injects an EntityManager for persistence operations.

## Spring Framework Core Annotations
- **@Component**: Marks a class as a Spring-managed component.
- **@Service**: Indicates that a class provides business logic and is a service layer component.
- **@Repository**: Indicates that a class is a repository and interacts with the database.
- **@Controller**: Marks a class as a Spring MVC controller to handle HTTP requests.
- **@RestController**: A combination of **@Controller** and **@ResponseBody**, used for REST APIs.
- **@Autowired**: Automatically injects dependencies into a Spring-managed component.
- **@Qualifier**: Specifies which bean to inject when multiple beans of the same type exist.
- **@Value**: Injects values into fields from properties or environment variables.

## HTTP Request Mappings (Spring MVC)
- **@RequestMapping**: Maps HTTP requests to handler methods in a controller. Can be used for all HTTP methods.
- **@GetMapping**: Maps HTTP GET requests to specific handler methods.
- **@PostMapping**: Maps HTTP POST requests to specific handler methods.
- **@PutMapping**: Maps HTTP PUT requests to specific handler methods.
- **@DeleteMapping**: Maps HTTP DELETE requests to specific handler methods.
- **@PatchMapping**: Maps HTTP PATCH requests to specific handler methods.

## Validation and Constraints
- **@Valid**: Marks a parameter or field for validation.
- **@NotNull**: Ensures that a field or parameter is not null.
- **@Size**: Specifies size constraints for a field (e.g., length or collection size).
- **@Min** and **@Max**: Specifies minimum and maximum value constraints.
- **@Pattern**: Specifies a regex pattern that a field must match.

## Testing (JUnit and Spring Testing)
- **@Test**: Marks a method as a test case.
- **@BeforeEach**: Runs before each test method.
- **@AfterEach**: Runs after each test method.
- **@BeforeAll**: Runs once before all test methods in a class.
- **@AfterAll**: Runs once after all test methods in a class.
- **@MockBean**: Creates a mock bean for testing purposes in Spring.
- **@SpringBootTest**: Used for integration testing in Spring Boot applications.

## Other Common Annotations
- **@Override**: Indicates that a method overrides a method in a superclass.
- **@Deprecated**: Marks a method or class as deprecated.
- **@SuppressWarnings**: Suppresses compiler warnings for a specific block of code.
- **@FunctionalInterface**: Indicates that an interface is a functional interface (has exactly one abstract method).
- **@Retention**: Specifies how long annotations are retained (e.g., runtime, compile time).
- **@Target**: Specifies where an annotation can be applied (e.g., method, field, class).
- **@Documented**: Indicates that the annotation should be included in JavaDocs.

## Security (Spring Security)
- **@PreAuthorize**: Specifies security constraints before a method is executed.
- **@PostAuthorize**: Specifies security constraints after a method is executed.
- **@Secured**: Defines security roles that can access a method.
- **@RolesAllowed**: Specifies roles allowed to execute a method.

## Configuration and Initialization
- **@Configuration**: Marks a class as a configuration class in Spring.
- **@Bean**: Defines a bean to be managed by the Spring container.
- **@PostConstruct**: Marks a method to be executed after dependency injection is done.
- **@PreDestroy**: Marks a method to be executed before the bean is destroyed.

