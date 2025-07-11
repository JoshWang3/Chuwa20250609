-- Spring

Bean Configuration:

@Component: Marks a class as a Spring-managed component.
@Service: Marks a class as a service component (specialized @Component).
@Repository: Marks a class as a DAO component.
@Controller: Marks a class as a Spring MVC controller.
@RestController: @Controller + @ResponseBody.
@Configuration: Indicates that the class declares one or more @Bean methods.
@Bean: Declares a bean from a method in a @Configuration class.

Dependency Injection:

@Autowired: Automatically injects dependencies by type.
@Primary – Marks a bean as the default for autowiring.
@Inject – Dependency injection (same as @Autowired).
@Resource – Injects dependency by bean name.

Scope:

@Scope("singleton") – Default Spring bean scope.
@Scope("prototype") – New bean instance per request.
@PostConstruct – Method to run after dependency injection is done.

-- Spring MVC

Request Mapping:

@RequestMapping: Maps HTTP requests to handler methods.
@GetMapping: @RequestMapping(method = RequestMethod.GET).
@PostMapping: @RequestMapping(method = RequestMethod.POST).
@PutMapping: PUT request handlers.
@DeleteMapping: DELETE request handlers.

Request Parameters and Path Variables:

@PathVariable: Binds URI template variable to method parameter.
@RequestParam: Binds query string or form data to method parameter.
@RequestBody: Binds the body of the HTTP request to method parameter.
@ResponseBody: Binds return value to web response body.

-- Spring Data JPA

@Entity: Declares a persistent Java class.
@Table: Specifies the table name in the database.
@Id: Specifies the primary key.
@GeneratedValue: Provides generation strategy for primary keys.
@Column: Specifies column details.
@OneToOne: Defines one-to-one relationship.
@OneToMany: Defines one-to-many relationship.
@ManyToOne: Defines many-to-one relationship.
@ManyToMany: Defines many-to-many relationship.
@JoinColumn: Specifies the foreign key column.
@JoinTable: Defines join table for many-to-many relationship.
@CreationTimestamp: Automatically sets timestamp when entity is created.
@UpdateTimestamp: Automatically sets timestamp when entity is updated.
@Transactional: Marks method/class as transactional.

-- Query

@Query: Defines custom JPQL or native queries.
@NamedQuery: Declares a named JPQL query.
@NamedQueries: Container for multiple @NamedQuery.
@Param: Binds method parameter to named query parameter.

-- Spring Boot

@SpringBootApplication – Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan.

--Exception Handling

@ResponseStatus – Specifies HTTP status for exception.
@ControllerAdvice – Global exception handler or controller helper.

-- GraphQL
@QueryMapping – Marks a method as a GraphQL query handler.
@MutationMapping – Marks a method as a GraphQL mutation handler.
@Argument – Binds GraphQL argument to method parameter.

-- Lombok

@Getter, @Setter – Generates getters/setters.
@AllArgsConstructor, @NoArgsConstructor – Generates constructors.
@Data – Combines @Getter, @Setter, @ToString, @EqualsAndHashCode, and @RequiredArgsConstructor.