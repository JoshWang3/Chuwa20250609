## Annotation & Syntax Reference
### 1. Spring Annotation Cheatsheet
### List all of the annotations you’ve learned from class and homework in a file named annotations.md. This will serve as your Spring annotation reference.

@Configuration：Marks a class as a source of bean definitions.(Class level only)
@Bean: Defines a bean to be managed by Spring container.
@Component: Generic stereotype for Spring-managed components.(Class level)
@ComponentScan: Enables component scanning for specified packages.(Class level)
@Autowired: Automatic dependency injection. (Field, Constructor, Method levels)
@Qualifier: Specify which bean to inject when multiple candidates exist.(Field, Parameter)
@Value: Inject values from properties files.(Field, Parameter)
@Service: Business logic layer component.(Class)
@Repository: Data access layer component.(Class)
@Controller: Web layer component (Spring MVC) (Class)
@RestController: RESTful web service controller.(Class)
@RequestMapping: Map HTTP requests to handler methods.(Class, Method levels)
@GetMapping / @PostMapping / @PutMapping / @DeleteMapping: Shorthand for specific HTTP methods.(Method level)
@PathVariable: Extract values from URI path.(Parameter level only)
@RequestParam: Extract query parameters.
@RequestBody: Bind HTTP request body to method parameter.
@ResponseBody: Bind method return value to HTTP response body.
@Valid: Trigger validation of request body.
@NotNull / @NotBlank / @NotEmpty: Validation constraints.
@Transactional: Declarative transaction management.(Class, Method)
@Profile: Conditional bean registration based on active profiles.(Class)
@Conditional: Conditional bean creation based on custom conditions.(Class, Method)
@Cacheable: Cache method results.(Method level)
@CacheEvict: Remove entries from cache.
@SpringBootTest: Load complete application context for integration tests.(Test class level)
@MockBean: Mock Spring beans in test context.(Test fields)
@TestConfiguration: Test-specific configuration.(Test configuration classes)

