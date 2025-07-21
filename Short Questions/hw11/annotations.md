# Spring Annotation Cheatsheet

- @RestController combines @Controller and @ResponseBody, used to define REST-style controllers that return JSON or XML responses.  
Example: @RestController @RequestMapping("/users") public class UserController { @GetMapping public List<User> getUsers() { return userService.findAll(); } }

- @RequestMapping defines URL path and request method mappings.  
Example: @RequestMapping(value = "/users", method = RequestMethod.GET) public List<User> getUsers() { ... }

- @GetMapping / @PostMapping / @PutMapping / @DeleteMapping are shortcuts for @RequestMapping to handle GET, POST, PUT, DELETE requests respectively.  
Example: @GetMapping("/users/{id}") public User getUser(@PathVariable Long id) { ... }

- @PathVariable binds URL path variables to method parameters.  
Example: @GetMapping("/users/{id}") public User getUser(@PathVariable Long id) { ... }

- @RequestParam extracts values from request parameters.  
Example: @GetMapping("/search") public List<User> search(@RequestParam String keyword) { ... }

- @RequestBody automatically maps the JSON request body to a Java object.  
Example: @PostMapping("/users") public User createUser(@RequestBody User user) { ... }

- @Autowired automatically injects beans from the Spring container.  
Example: @Autowired private UserRepository userRepository;

- @Service marks a service layer bean.  
Example: @Service public class UserService { ... }

- @Repository marks a data access layer bean and enables exception translation.  
Example: @Repository public interface UserRepository extends JpaRepository<User, Long> { }

- @Entity marks a class as a JPA entity mapped to a database table.  
Example: @Entity @Table(name = "users") public class User { @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id; @Column(nullable = false) private String name; }

- @Table specifies the database table name for an entity class.  
Example: @Table(name = "users")

- @Id identifies the primary key field of an entity.  
Example: @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

- @GeneratedValue defines the strategy for primary key generation.  
Example: @GeneratedValue(strategy = GenerationType.IDENTITY)

- @Column maps entity fields to database columns and configures column properties.  
Example: @Column(name = "description", nullable = false) private String description;

- @Query defines custom JPQL or native SQL queries.  
Example (JPQL): @Query("SELECT u FROM User u WHERE u.name = :name") User findByName(@Param("name") String name);  
Example (Native SQL): @Query(value = "SELECT * FROM users WHERE name = :name", nativeQuery = true) User findByNameNative(@Param("name") String name);

- @ManyToOne / @OneToMany / @ManyToMany define relationships between entities.  
Example: @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") private User user;
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL) private List<Post> posts;  
@ManyToMany(fetch = FetchType.LAZY) @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id")) private Set<Role> roles;

- @Transactional declares a method or class as transactional; if an exception occurs, the transaction will roll back.  
Example: @Transactional public void saveUser(User user) { ... }

- @EnableTransactionManagement enables annotation-driven transaction management.  
Example: @Configuration @EnableTransactionManagement public class AppConfig { ... }

- @PersistenceContext injects the EntityManager for custom JPA

- @ExceptionHandler: Method Level & used to handle the specific exceptions and sending the custom responses to the client

- ControllerAdvice: Class level & make class as a bean

- @Valid: Triggers validation logic on request body data.
