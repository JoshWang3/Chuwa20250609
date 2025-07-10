# annotations

| Annotation | Description | Category |
| --- | --- | --- |
| `@Component` | Generic Spring-managed component. | Core |
| `@Controller` | Marks a Spring MVC controller. | Core / Web |
| `@Service` | Marks a service class. | Core |
| `@Repository` | Marks a DAO and enables exception translation. | Core / Persistence |
| `@Configuration` | Defines a class with Spring bean definitions. | Core |
| `@Bean` | Declares a Spring bean from a method. | Core |
| `@Autowired` | Injects dependencies automatically by type. | Dependency Injection |
| `@Qualifier` | Specifies the exact bean to inject when multiple exist. | Dependency Injection |
| `@Value` | Injects values from properties or SpEL. | Dependency Injection |
| `@Inject` | JSR-330 equivalent of `@Autowired`. | Dependency Injection |
| `@Resource` | JSR-250: injects dependency by name. | Dependency Injection |
| `@PostConstruct` | Runs method after bean initialization. | Lifecycle |
| `@PreDestroy` | Runs method before bean destruction. | Lifecycle |
| `@SpringBootApplication` | Combines `@Configuration`, `@ComponentScan`, `@EnableAutoConfiguration`. | Spring Boot |
| `@EnableAutoConfiguration` | Enables Spring Boot's auto-configuration. | Spring Boot |
| `@ComponentScan` | Scans packages for annotated components. | Spring Boot |
| `@RequestMapping` | Maps HTTP requests to handler methods. | Web |
| `@GetMapping` | Shortcut for `@RequestMapping(method = GET)`. | Web |
| `@PostMapping` | Shortcut for `@RequestMapping(method = POST)`. | Web |
| `@PutMapping` | Shortcut for `@RequestMapping(method = PUT)`. | Web |
| `@DeleteMapping` | Shortcut for `@RequestMapping(method = DELETE)`. | Web |
| `@PatchMapping` | Shortcut for `@RequestMapping(method = PATCH)`. | Web |
| `@RequestParam` | Binds HTTP query parameters to method arguments. | Web |
| `@PathVariable` | Binds URI template variables to method arguments. | Web |
| `@RequestBody` | Binds HTTP request body to a method argument. | Web |
| `@ResponseBody` | Returns method output directly as HTTP response body. | Web |
| `@RestController` | Combines `@Controller` and `@ResponseBody`. | Web |
| `@EnableWebSecurity` | Enables Spring Security configuration. | Security |
| `@PreAuthorize` | Authorizes method execution using SpEL. | Security |
| `@Secured` | Restricts access to methods based on roles. | Security |
| `@RolesAllowed` | JSR-250 alternative to `@Secured`. | Security |
| `@Transactional` | Defines transaction boundaries on methods or classes. | Persistence / TX |
| `@EnableTransactionManagement` | Enables annotation-based transaction management. | Persistence / TX |
| `@Entity` | Declares a JPA entity. | Persistence / JPA |
| `@Id` | Marks a primary key in JPA. | Persistence / JPA |
| `@Column` | Maps a field to a database column. | Persistence / JPA |
| `@Table` | Specifies the table name for an entity. | Persistence / JPA |
| `@SpringBootTest` | Loads full Spring context for integration tests. | Testing |
| `@WebMvcTest` | Loads only the Spring MVC components for tests. | Testing |
| `@DataJpaTest` | Loads only JPA components for tests. | Testing |
| `@MockBean` | Adds a mock into the Spring context. | Testing |