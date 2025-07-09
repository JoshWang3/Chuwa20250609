Looking through the Spring Boot project code, here are all the annotations used:

## Spring Boot & Spring Framework Annotations

**Application & Configuration:**
- `@SpringBootApplication` - Main application class configuration
- `@Repository` - Marks classes as data access components
- `@Service` - Marks classes as service layer components
- `@RestController` - Combines `@Controller` and `@ResponseBody` for REST endpoints
- `@Autowired` - Dependency injection

**Web/HTTP Annotations:**
- `@RequestMapping` - Maps HTTP requests to handler methods
- `@PostMapping` - Maps HTTP POST requests
- `@GetMapping` - Maps HTTP GET requests
- `@PutMapping` - Maps HTTP PUT requests
- `@DeleteMapping` - Maps HTTP DELETE requests
- `@PathVariable` - Binds URI template variables to method parameters
- `@RequestParam` - Binds request parameters to method parameters
- `@RequestBody` - Binds HTTP request body to method parameters
- `@ResponseStatus` - Sets HTTP status code for exceptions

## JPA/Hibernate Annotations

**Entity Mapping:**
- `@Entity` - Marks class as JPA entity
- `@Table` - Specifies database table details
- `@Id` - Marks primary key field
- `@GeneratedValue` - Configures primary key generation strategy
- `@Column` - Maps entity field to database column
- `@UniqueConstraint` - Defines unique constraints
- `@NamedQuery` - Defines named JPQL queries

**Timestamp Annotations:**
- `@CreationTimestamp` - Automatically sets creation timestamp
- `@UpdateTimestamp` - Automatically sets update timestamp

**JPA Repository:**
- `@Query` - Defines custom JPQL or SQL queries
- `@Param` - Names parameters in custom queries

## Transaction & Persistence

- `@Transactional` - Marks methods/classes as transactional
- `@PersistenceContext` - Injects EntityManager

## Testing Annotations

- `@SpringBootTest` - Loads complete Spring application context for tests
- `@Test` - Marks methods as JUnit test methods

## Enum Values Used

**JPA Strategy:**
- `GenerationType.IDENTITY` - Auto-increment primary key generation

**Query Parameters:**
- `nativeQuery = true` - Indicates native SQL query
- `nullable = false` - Column cannot be null
- `strategy = GenerationType.IDENTITY` - Primary key generation strategy

The project demonstrates a comprehensive use of Spring Boot's annotation-driven configuration for building a REST API with JPA/Hibernate persistence layer.