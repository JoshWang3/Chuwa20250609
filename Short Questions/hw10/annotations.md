## Spring Annotation Cheatsheet

Covering core Spring, Spring Boot, and Spring Data JPA

## ✅ Core Spring Annotations

| Annotation            | Description                                                              |
|-----------------------|--------------------------------------------------------------------------|
| `@Component`          | Generic stereotype for any Spring-managed component.                     |
| `@Service`            | Specialization of `@Component` for service-layer classes.                |
| `@Repository`         | Indicates DAO component; enables exception translation.                  |
| `@Controller`         | Marks a web controller class (used in Spring MVC).                       |
| `@RestController`     | Combines `@Controller` + `@ResponseBody`, returning JSON/XML by default. |
| `@Autowired`          | Automatically injects dependencies by type.                              |
| `@Qualifier`          | Disambiguates dependency injection when multiple beans exist.            |
| `@Value("${...}")`    | Injects values from properties (e.g., `application.properties`).         |
| `@PostConstruct`      | Method runs after dependency injection is done.                          |
| `@PreDestroy`         | Method runs before bean is destroyed.                                    |
| `@Scope("prototype")` | Changes default singleton scope to prototype.                            |

---

## 🛠 Configuration & Lifecycle

| Annotation                         | Description                                      |
|------------------------------------|--------------------------------------------------|
| `@Configuration`                   | Marks a class as a source of bean definitions.   |
| `@Bean`                            | Declares a bean inside a `@Configuration` class. |
| `@Import({Config.class})`          | Imports configuration classes.                   |
| `@PropertySource("classpath:...")` | Loads properties file.                           |

---

## 🌐 Spring MVC / Web

| Annotation                    | Description                                             |
|-------------------------------|---------------------------------------------------------|
| `@RequestMapping`             | Maps HTTP requests to methods (`GET`, `POST`, etc.).    |
| `@GetMapping`, `@PostMapping` | Shortcut annotations for `@RequestMapping(method=...)`. |
| `@RequestParam`               | Extracts query parameters or form fields.               |
| `@PathVariable`               | Binds URL path variable to method parameter.            |
| `@RequestBody`                | Binds request body to Java object (e.g., JSON → POJO).  |
| `@ResponseBody`               | Sends return value directly as response body.           |
| `@ModelAttribute`             | Binds form data to model object.                        |
| `@SessionAttributes`          | Persists model attributes in HTTP session.              |
| `@ExceptionHandler`           | Handles exceptions at controller level.                 |

---

## 🚀 Spring Boot

| Annotation                                 | Description                                                              |
|--------------------------------------------|--------------------------------------------------------------------------|
| `@SpringBootApplication`                   | Combines `@Configuration`, `@EnableAutoConfiguration`, `@ComponentScan`. |
| `@EnableAutoConfiguration`                 | Enables Spring Boot's auto-configuration feature.                        |
| `@ComponentScan`                           | Specifies packages to scan for components.                               |
| `@ConfigurationProperties(prefix = "app")` | Binds external properties to Java POJO.                                  |
| `@SpringBootTest`                          | Used in testing to load the full Spring context.                         |
| `@TestConfiguration`                       | Special `@Configuration` used in test classes.                           |

---

## 🧠 Spring Data JPA

| Annotation                                             | Description                                                              |
|--------------------------------------------------------|--------------------------------------------------------------------------|
| `@Entity`                                              | Declares a JPA entity.                                                   |
| `@Table(name = "my_table")`                            | Maps entity to a table.                                                  |
| `@Id`                                                  | Primary key.                                                             |
| `@GeneratedValue`                                      | Auto-generates primary key (strategy options: `AUTO`, `IDENTITY`, etc.). |
| `@Column(name = "...")`                                | Maps field to a DB column.                                               |
| `@OneToMany`, `@ManyToOne`, `@OneToOne`, `@ManyToMany` | Defines entity relationships.                                            |
| `@JoinColumn(name = "...")`                            | Specifies foreign key column.                                            |
| `@Repository`                                          | Enables JPA exception translation and scanning.                          |
| `@Query("SELECT ...")`                                 | Defines custom JPQL or native SQL queries.                               |
| `@Modifying`                                           | Marks update/delete queries in `@Query`.                                 |
| `@Transactional`                                       | Declares method/class as transactional.                                  |

---

## 🧪 Testing

| Annotation           | Description                                                |
|----------------------|------------------------------------------------------------|
| `@SpringBootTest`    | Loads full application context for integration testing.    |
| `@WebMvcTest`        | Tests only web layer (e.g., controllers).                  |
| `@DataJpaTest`       | Tests only JPA components (repositories).                  |
| `@MockBean`          | Creates a mock bean and replaces real bean in the context. |
| `@TestConfiguration` | Supplies additional beans/config for tests.                |

---