# JPA & Hibernate Annotations

## Entity & Table Mapping
- `@Entity` — Marks a class as a JPA entity (mapped to a DB table)
- `@Table(name = "table_name")` — Specifies the table name if it differs from the class name
- `@Id` — Marks the primary key
- `@GeneratedValue(strategy = GenerationType.X)` — Auto-generates ID (e.g., AUTO, IDENTITY, SEQUENCE)
- `@Column(name = "column_name")` — Maps a field to a DB column
- `@Transient` — Excludes a field from persistence

## Relationships
- `@OneToOne` — One-to-one relationship
- `@OneToMany(mappedBy = "...")` — One-to-many (child collection)
- `@ManyToOne` — Many-to-one (foreign key)
- `@ManyToMany(mappedBy = "...")` — Many-to-many
- `@JoinColumn(name = "column_name")` — Foreign key column
- `@JoinTable(...)` — Specifies join table for `@ManyToMany`

## Fetching & Cascading
- `fetch = FetchType.LAZY` — Lazy loading
- `fetch = FetchType.EAGER` — Eager loading
- `cascade = CascadeType.ALL` — Cascade all operations
- `orphanRemoval = true` — Remove child if unlinked

## Caching
- `@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)` — Hibernate second-level cache support

## Temporal
- `@Temporal(TemporalType.DATE | TIME | TIMESTAMP)` — Maps Java `Date` to SQL temporal type

## Auditing
- `@CreationTimestamp` — Automatically sets creation date/time
- `@UpdateTimestamp` — Automatically updates modification timestamp

---

# Spring Data JPA Annotations

## Repositories
- `@Repository` — Marks a class as a repository
- `@EnableJpaRepositories` — Enables JPA repositories in Spring config

## Query Methods
- `@Query("...")` — Custom JPQL or SQL query
- `@Modifying` — Marks modifying queries (`UPDATE`, `DELETE`)
- `@Param("name")` — Binds method parameters to query placeholders

## Transactions
- `@Transactional` — Declares a method to be transactional
- `@Rollback(false)` — Disables rollback in tests

---

# Spring Boot Web & REST Annotations

## REST Controllers & Routes
- `@RestController` — Combines `@Controller` and `@ResponseBody`
- `@RequestMapping("/path")` — Maps HTTP requests to controller methods (any method)
- `@GetMapping("/path")` — Maps HTTP GET requests
- `@PostMapping("/path")` — Maps HTTP POST requests
- `@PutMapping("/path")` — Maps HTTP PUT requests
- `@DeleteMapping("/path")` — Maps HTTP DELETE requests
- `@PatchMapping("/path")` — Maps HTTP PATCH requests

## Request Handling
- `@RequestBody` — Binds JSON from the request body to a method parameter
- `@RequestParam("name")` — Binds query parameters (e.g. `?name=value`)
- `@PathVariable("id")` — Binds path variables from the URL (e.g. `/users/{id}`)
- `@ResponseStatus(HttpStatus.OK)` — Sets custom response status
- `@CrossOrigin` — Enables CORS for specific controllers or methods

---

# Spring Boot Dependency Injection & Lifecycle

- `@Component` — Generic Spring-managed component
- `@Service` — Marks a service class
- `@Controller` — Marks a web controller (used with Thymeleaf, etc.)
- `@Autowired` — Injects dependencies automatically
- `@PostConstruct` — Method runs after bean initialization
- `@PreDestroy` — Method runs before bean destruction
- `@Value("${...}")` — Injects property value from application.yml/properties

---

# Validation Annotations (Spring Boot + Bean Validation)

- `@NotNull`
- `@Size(min = ..., max = ...)`
- `@Email`
- `@Min`, `@Max`
- `@Pattern(regexp = "...")`
- `@Valid` — Triggers validation on method parameters or request bodies
