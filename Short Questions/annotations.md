# Spring & Spring Boot Annotation Quick Guide

*A purpose‑first cheat sheet—code.*

---

## 1 · Component Stereotypes (IoC)

* **@Component** – Generic marker that turns a class into a Spring‑managed bean.
* **@Service** – Same as @Component but semantically signals *business logic*.
* **@Repository** – Indicates a persistence adapter and activates exception‑translation.
* **@Controller** – MVC controller returning a view template name.
* **@RestController** – Combines @Controller + @ResponseBody for JSON/XML APIs.

## 2 · Bean Lifecycle & Scope

* **@PostConstruct** – Runs once right after dependency injection finishes.
* **@PreDestroy** – Invoked just before the bean leaves the container.
* **@Scope** – Overrides the default singleton scope (e.g., *prototype*, *request*, *session*).

## 3 · Web Request Mapping

* **@RequestMapping** – Base mapping; lets you set path, method, consumes, produces, etc.
* **@GetMapping / @PostMapping / @PutMapping / @DeleteMapping** – Shorthand for common HTTP verbs.
* **@RequestBody** – Binds the HTTP payload to a method parameter.
* **@PathVariable** – Extracts values embedded in the URI template.
* **@RequestParam** – Reads key‑value pairs from the query string.

## 4 · Persistence (JPA / Hibernate)

* **@Entity** – Declares a domain object as a table row representation.
* **@Table** – Customises table name or schema.
* **@Id** – Marks the primary key.
* **@Column** – Fine‑grained column mapping and constraints.
* **@CreationTimestamp** – Auto‑sets creation time on insert.
* **@UpdateTimestamp** – Auto‑updates modification time on update.

## 5 · Exception Handling

* **@ControllerAdvice** – Global advice for controllers (e.g., exception mapping, model attributes).
* **@ExceptionHandler** – Method‑level handler for specific exceptions.
* **@ResponseStatus** – Associates an HTTP status code with a method or custom exception.

## 6 · Bootstrapping & Configuration

* **@SpringBootApplication** – Meta‑annotation bundling @Configuration, @EnableAutoConfiguration, and @ComponentScan.
* **@Configuration** – Marks a class containing bean factory methods.
* **@EnableAutoConfiguration** – Lets Spring Boot configure beans based on the classpath.
* **@ComponentScan** – Defines the packages that should be scanned for components.
* **@Bean** – Registers the return value of a method as a bean.

## 7 · Security

* **@PreAuthorize** – Evaluates a SpEL expression before method execution to enforce access rules.

## 8 · Aspect‑Oriented Programming

* **@Aspect** – Declares a class containing cross‑cutting concerns.
* **@Pointcut** – Reusable predicate describing join points.
* **@Before / @After / @AfterReturning / @AfterThrowing / @Around** – Advice types that hook into the pointcut.

---

### At‑a‑Glance Tips

* **Component stereotypes** differ mainly by *intent*; they behave the same to the container.
* Prefer **constructor injection**—it keeps dependencies explicit and works well with immutability.
* For REST, default to **@RestController**; for server‑side rendered pages, stick with **@Controller**.
