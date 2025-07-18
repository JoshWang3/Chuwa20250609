## List all of the annotations you've learned from class and homework in a file named annotations.md. This will serve as your Spring annotation reference.

### Core Spring Annotations
- @Configuration: Marks a class as a source of bean definitions.(Class level only)
- @Bean: Defines a bean to be managed by Spring container.
- @Component: Generic stereotype for Spring-managed components.(Class level)
- @ComponentScan: Enables component scanning for specified packages.(Class level)
- @Autowired: Automatic dependency injection. (Field, Constructor, Method levels)
- @Qualifier: Specify which bean to inject when multiple candidates exist.(Field, Parameter)
- @Value: Inject values from properties files.(Field, Parameter)

## Stereotype Annotations
- @Service: Business logic layer component.(Class)
- @Repository: Data access layer component.(Class)
- @Controller: Web layer component (Spring MVC) (Class)
- @RestController: RESTful web service controller.(Class)

## Web Annotations
- @RequestMapping: Map HTTP requests to handler methods.(Class, Method levels)
- @GetMapping / @PostMapping / @PutMapping / @DeleteMapping: Shorthand for specific HTTP methods.(Method level)
- @PathVariable: Extract values from URI path.(Parameter level only)
- @RequestParam: Extract query parameters.
- @RequestBody: Bind HTTP request body to method parameter.
- @ResponseBody: Bind method return value to HTTP response body.

## Validation Annotations

- @Valid: Trigger validation of request body.
- @NotNull / @NotBlank / @NotEmpty: Validation constraints.

## Configuration & Conditional Annotations
- @ConfigurationProperties: Bind configuration properties to Java objects.(Class level)
- @Primary: Mark a bean as the primary choice when multiple beans exist.(Class, Method)
- @Lazy: Initialize bean only when first needed.(Class, Method, Field)
- @Scope: Define the lifecycle scope of a bean.(Class, Method)
- @Profile: Conditional bean registration based on active profiles.(Class)
- @Conditional: Conditional bean creation based on custom conditions.(Class, Method)

## Transaction & Cache Annotations
- @Transactional: Declarative transaction management.(Class, Method)
- @Cacheable: Cache method results.(Method level)
- @CacheEvict: Remove entries from cache.(Method level)

## Exception Handling
- @ExceptionHandler: Handle specific exceptions in controller methods.(Method level)
- @ControllerAdvice: Global exception handling across all controllers.(Class level)

## Async Processing
- @Async: Execute method asynchronously in separate thread.(Method level)

## Testing Annotations
- @SpringBootTest: Load complete application context for integration tests.(Test class level)
- @MockBean: Mock Spring beans in test context.(Test fields)
- @TestConfiguration: Test-specific configuration.(Test configuration classes)
- @TestPropertySource: Specify custom properties for test classes.(Test class level)

## JPA Relationship Annotations
- @OneToMany: Map one entity to multiple related entities.(Field, Method)
- @ManyToOne: Map multiple entities to one related entity.(Field, Method)
- @OneToOne: Map one entity to exactly one related entity.(Field, Method)
- @ManyToMany: Map multiple entities to multiple related entities.(Field, Method)
- @JoinColumn: Specify foreign key column for relationships.(Field, Method)
- @JoinTable: Define join table for many-to-many relationships.(Field, Method)

## JPA Entity Annotations
- @Entity: Mark a class as a database entity.(Class level)
- @Table: Specify database table details.(Class level)
- @Id: Mark field as primary key.(Field level)
- @GeneratedValue: Auto-generate primary key values.(Field level)
- @Column: Map field to database column.(Field level)
- @Temporal: Specify date/time type for database.(Field level)
- @Enumerated: Map enum to database.(Field level)
- @Transient: Exclude field from database mapping.(Field level)
- @Embedded: Embed another class in current entity.(Field level)
- @Embeddable: Mark class as embeddable in entities.(Class level)

## Security Annotations
- @EnableWebSecurity: Enable Spring Security configuration.(Class level)
- @PreAuthorize: Method-level security check before execution.(Method level)
- @PostAuthorize: Method-level security check after execution.(Method level)
- @Secured: Role-based access control.(Method level)
- @RolesAllowed: JSR-250 annotation for role-based security.(Method level)

## AOP (Aspect-Oriented Programming) Annotations
- @Aspect: Mark a class as an aspect.(Class level)
- @Before: Execute advice before method execution.(Method level)
- @After: Execute advice after method execution.(Method level)
- @Around: Execute advice around method execution.(Method level)
- @AfterReturning: Execute advice after successful method return.(Method level)
- @AfterThrowing: Execute advice after method throws exception.(Method level)
- @Pointcut: Define reusable pointcut expressions.(Method level)

## Scheduling & Async Annotations
- @EnableScheduling: Enable scheduled task execution.(Class level)
- @Scheduled: Schedule method execution at fixed intervals.(Method level)
- @EnableAsync: Enable asynchronous method execution.(Class level)

## Lifecycle Annotations
- @PostConstruct: Execute method after bean initialization.(Method level)
- @PreDestroy: Execute method before bean destruction.(Method level)

## Additional Web Annotations
- @CrossOrigin: Enable CORS for controller methods.(Class, Method levels)
- @RequestHeader: Extract HTTP request headers.(Parameter level)
- @CookieValue: Extract cookie values from request.(Parameter level)
- @SessionAttribute: Access session attributes.(Parameter level)
- @ModelAttribute: Bind model attributes to method parameters.(Method, Parameter levels)

## Validation Annotations
- @Size: Validate string/collection size.(Field level)
- @Min / @Max: Validate numeric minimum/maximum values.(Field level)
- @Email: Validate email format.(Field level)
- @Pattern: Validate against regular expression.(Field level)