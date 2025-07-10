## Spring Core Annotations

#### Component Scanning / Stereotype Annotations

| Annotation    | Description                                                  |
| ------------- | ------------------------------------------------------------ |
| `@Component`  | Generic Spring-managed component (used for beans)            |
| `@Service`    | Specialized `@Component` for service layer                   |
| `@Repository` | Specialized `@Component` for DAO layer; enables exception translation |
| `@Autowired`  | Automatically injects a dependency by type                   |



## Spring Web Annotations

#### Controller & Request Mapping

| Annotation        | Description                                                  |
| ----------------- | ------------------------------------------------------------ |
| `@RestController` | Combines `@Controller` + `@ResponseBody`, suitable for REST APIs |
| `@RequestMapping` | Maps HTTP requests to classes or methods (can specify path & method) |
| `@GetMapping`     | Shortcut for `@RequestMapping(method = GET)`                 |
| `@PostMapping`    | Shortcut for `@RequestMapping(method = POST)`                |
| `@PutMapping`     | Shortcut for `@RequestMapping(method = PUT)`                 |
| `@DeleteMapping`  | Shortcut for `@RequestMapping(method = DELETE)`              |
| `@ResponseStatus` | Specifies the HTTP status code to return from a method or **exception handler** (e.g., 404, 201) |

#### Request Handling

| Annotation      | Description                                                  |
| --------------- | ------------------------------------------------------------ |
| `@RequestBody`  | Binds HTTP request body to a method parameter (typically a DTO) |
| `@PathVariable` | Binds a URI template variable to a method parameter          |
| `@RequestParam` | Binds query parameters (e.g., `?id=1`) to a method parameter |



## JPA Annotations

#### Entity Definitions

| Annotation          | Description                                                  |
| ------------------- | ------------------------------------------------------------ |
| `@Entity`           | Declares a class as a JPA entity                             |
| `@Table`            | Customizes the table name and constraints for the entity     |
| `@Id`               | Marks the primary key field                                  |
| `@GeneratedValue`   | Configures how the ID value is generated (e.g., auto-increment) |
| `@Column`           | Configures column properties (name, length, nullable, etc.)  |
| `@UniqueConstraint` | Declares unique constraints at table level (used inside `@Table`) |

#### Timestamping

| Annotation           | Description                                         |
| -------------------- | --------------------------------------------------- |
| `@CreationTimestamp` | Auto-fills timestamp when entity is first persisted |
| `@UpdateTimestamp`   | Auto-fills timestamp whenever entity is updated     |

#### JPA Query

| Annotation    | Description                                              |
| ------------- | -------------------------------------------------------- |
| `@Query`      | Defines a custom JPQL/SQL query inline                   |
| `@NamedQuery` | Pre-defined JPQL query stored with the entity            |
| `@Param`      | Binds a method parameter to a named parameter in a query |

#### Transaction & Persistence Context

| Annotation            | Description                                                  |
| --------------------- | ------------------------------------------------------------ |
| `@Transactional`      | Declares a method or class should be wrapped in a DB transaction |
| `@PersistenceContext` | Injects an `EntityManager` for direct JPA operations         |



## Spring Boot Testing Annotations 

| Annotation        | Description                                                |
| ----------------- | ---------------------------------------------------------- |
| `@SpringBootTest` | Loads the full application context for integration testing |
| `@Test`           | Marks a method as a test (JUnit 5/4)                       |