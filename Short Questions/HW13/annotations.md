```
@RestController
@RequestMapping
@PostMapping
@GetMapping
@PutMapping
@DeleteMapping

@Configuration
@Bean
@Component
@Autowired

@Repository
@Transactional
@PersistenceContext
@Query

@Entity
@Table
@NamedQuery
@Id
@GeneratedValue
@Column
@CreationTimestamp
@UpdateTimestamp

@ResponseStatus

@Service

@SpringBootApplication

@SpringBootTest
@Test

@ControllerAdvice: Define global exception handling for Spring mvc controller
@ExceptionHandler: Make method to be a exception handler to handle particular exception types
@NotBlank: Validation rule that the field should not blank
@Pattern: Validation rule that the field should align specific regex
@Valid: Used in controller, make spring to validate the @RequestBody dto

@Value: Could inject properties in application.properties
@PreAuthorize: Specify the required role for api method, check before the method
@EnableWebSecurity:Import spring web security config,could declare filter chain
@EnableGlobalMethodSecurity: Turn on method level security, allow secure methods with annotations like @PreAuthorize
```