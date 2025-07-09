## 1. **Spring MVC (Web Layer)**
| Annotation        | Description                                                                        |
|-------------------|------------------------------------------------------------------------------------|
| `@RestController` | Marks a class as a RESTful controller (combines `@Controller` and `@ResponseBody`) |
| `@Controller`     | Indicates a Spring MVC controller                                                  |
| `@RequestMapping` | Maps HTTP requests to controller methods or classes                                |
| `@GetMapping`     | Maps HTTP GET requests                                                             |
| `@PostMapping`    | Maps HTTP POST requests                                                            |
| `@PutMapping`     | Maps HTTP PUT requests                                                             |
| `@DeleteMapping`  | Maps HTTP DELETE requests                                                          |
| `@PatchMapping`   | Maps HTTP PATCH requests                                                           |
| `@PathVariable`   | Binds URL template variables to method parameters                                  |
| `@RequestParam`   | Binds query parameters or form data to method parameters                           |
| `@RequestBody`    | Binds the body of the HTTP request to a method parameter                           |
| `@ResponseBody`   | Indicates the return value should be written to the HTTP response body             |

---

## 2. **Dependency Injection (DI)**

| Annotation       | Description                                                           |
|------------------|-----------------------------------------------------------------------|
| `@Autowired`     | Automatically injects dependencies                                    |
| `@Qualifier`     | Specifies which bean to inject when multiple are available            |
| `@Component`     | Generic stereotype for a Spring-managed bean                          |
| `@Service`       | Indicates a service component                                         |
| `@Repository`    | Indicates a repository/DAO component                                  |
| `@Bean`          | Declares a bean to be managed by Spring inside `@Configuration` class |
| `@Configuration` | Indicates a class that provides bean definitions                      |

---

## 3. **JPA (Persistence Layer)**

| Annotation                                             | Description                                             |
|--------------------------------------------------------|---------------------------------------------------------|
| `@Entity`                                              | Declares a class as a JPA entity (mapped to a table)    |
| `@Id`                                                  | Marks the primary key field                             |
| `@GeneratedValue`                                      | Specifies generation strategy for primary keys          |
| `@Table`                                               | Specifies the table name (optional if default is fine)  |
| `@Column`                                              | Customizes column mapping                               |
| `@ManyToOne`, `@OneToMany`, `@OneToOne`, `@ManyToMany` | Defines relationships between entities                  |
| `@JoinColumn`                                          | Customizes the foreign key column                       |
| `@Query`                                               | Used to write custom JPQL or native queries             |
| `@Param`                                               | Binds method parameters to named parameters in `@Query` |

---

## 4. **Spring Boot Specific**

| Annotation                 | Description                                                                       |
|----------------------------|-----------------------------------------------------------------------------------|
| `@SpringBootApplication`   | Combination of `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan` |
| `@EnableAutoConfiguration` | Enables auto-configuration of Spring context                                      |
| `@ComponentScan`           | Scans for components in specified packages                                        |

---

## 5. **Validation**

| Annotation                             | Description                                                |
|----------------------------------------|------------------------------------------------------------|
| `@Valid` or `@Validated`               | Triggers validation on request bodies or method parameters |
| `@NotNull`, `@NotEmpty`, `@Size`, etc. | Bean Validation API (Hibernate Validator) annotations      |

---

## 6. **Testing**

| Annotation        | Description                                            |
|-------------------|--------------------------------------------------------|
| `@SpringBootTest` | Loads the full Spring application context for tests    |
| `@WebMvcTest`     | Loads only the web layer for controller testing        |
| `@DataJpaTest`    | Loads only the JPA layer for repository testing        |
| `@MockBean`       | Injects mock beans into the Spring context for testing |

---

## 7. **Transactional & Caching**

| Annotation       | Description                                          |
|------------------|------------------------------------------------------|
| `@Transactional` | Marks methods or classes to run within a transaction |
| `@Cacheable`     | Caches the result of a method                        |
| `@CacheEvict`    | Removes data from cache                              |

---