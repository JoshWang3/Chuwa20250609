## Part 1: Annotation & Syntax Reference:

---

## 1. Spring Core Annotations

- `@Component`  
Marks a Java class as a Spring-managed component.

- `@Service`  
Marks a service class (specialization of `@Component`).

- `@Repository`  
Marks a DAO (Data Access Object) class.

- `@Controller`  
Marks a web controller class.

- `@RestController`  
Marks a controller that returns RESTful responses (combines `@Controller` and `@ResponseBody`).

- `@Configuration`  
Marks a class as a source of Spring bean definitions.

- `@Bean`  
Defines a bean inside a `@Configuration` class.

- `@Primary`  
Indicates a preferred bean when multiple candidates are present.

- `@Qualifier`  
Specifies which bean to inject when multiple candidates are available.

---


---
## 2. Dependency Injection Annotations

@AutowiredAutomatically injects a bean by type.

@ValueInjects values from property files.

@Inject (from javax.inject)Alternative to @Autowired.

@Resource (from JSR-250)Alternative injection by name.
---

## 3. Spring Web Annotations (Spring MVC)

- `@RequestMapping`  
Maps web requests to handler methods or classes.

- `@GetMapping`  
Shortcut for `@RequestMapping(method = RequestMethod.GET)`.

- `@PostMapping`  
Shortcut for `@RequestMapping(method = RequestMethod.POST)`.

- `@PutMapping`  
Shortcut for `@RequestMapping(method = RequestMethod.PUT)`.

- `@DeleteMapping`  
Shortcut for `@RequestMapping(method = RequestMethod.DELETE)`.

- `@PathVariable`  
Binds a path variable from the URL to a method parameter.

- `@RequestParam`  
Binds a request parameter to a method parameter.

- `@RequestBody`  
Binds the body of a request to a method parameter.

- `@ResponseBody`  
Indicates that the return value of a method is the response body.

- `@CrossOrigin`  
Enables Cross-Origin Resource Sharing (CORS).

---

## 4. Spring Boot Annotations

- `@SpringBootApplication`  
Combination of `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.

- `@EnableAutoConfiguration`  
Enables Spring Boot's auto-configuration.

- `@ComponentScan`  
Tells Spring where to search for annotated components.

- `@PropertySource`  
Loads properties file.

---

## 5. JPA & Hibernate Annotations

- `@Entity`  
Marks a class as a JPA entity.

- `@Table`  
Specifies the database table name.

- `@Id`  
Marks the primary key field.

- `@GeneratedValue`  
Defines how the primary key is generated.

- `@Column`  
Customizes table column mapping.

- `@OneToOne`  
Defines one-to-one relationship.

- `@OneToMany`  
Defines one-to-many relationship.

- `@ManyToOne`  
Defines many-to-one relationship.

- `@ManyToMany`  
Defines many-to-many relationship.

- `@JoinColumn`  
Specifies the foreign key column.

- `@JoinTable`  
Defines the join table for many-to-many.

- `@Enumerated`  
Maps enum types.

---

## 6. Transaction Management

- `@Transactional`  
Defines transactional boundaries.

---