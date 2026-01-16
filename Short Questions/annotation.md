- `@SpringBootApplication`  
  Marks the main class of a Spring Boot application. Combines `@Configuration`, `@EnableAutoConfiguration` and `@ComponentScan`.

- `@Component`  
  Marks a class as a Spring component, making it a managed bean.

- `@Service`  
  Specialized `@Component` for service-layer classes, typically containing business logic.

- `@Repository`  
  Specialized `@Component` for data-access classes. Enables exception translation.

- `@Controller`  
  Specialized `@Component` for Spring MVC controllers that handle web requests.

- `@RestController`  
  Combines `@Controller` and `@ResponseBody`. Handles web requests and returns JSON/XML responses directly.

- `@Autowired`  
  Automatically injects dependencies into a field, setter method, or constructor.

- `@Value`  
  Injects values from properties files into fields, setter methods, or constructor parameters.

- `@Configuration`  
  Indicates that a class contains Spring bean definitions.

- `@Bean`  
  Declares a method that returns a Spring bean to be managed by the Spring container.

- `@EnableAutoConfiguration`  
  Automatically configures your Spring application based on the dependencies present on the classpath.

- `@ComponentScan`  
  Configures component-scanning directives for `@Configuration` classes; specifies base packages to scan for annotated components.

- `@RequestMapping`  
  Maps web requests to specific handler classes or handler methods.

- `@GetMapping`  
  Shortcut for `@RequestMapping(method = RequestMethod.GET)`. Handles GET requests.

- `@PostMapping`  
  Shortcut for `@RequestMapping(method = RequestMethod.POST)`. Handles POST requests.

- `@PutMapping`  
  Shortcut for `@RequestMapping(method = RequestMethod.PUT)`. Handles PUT requests.

- `@DeleteMapping`  
  Shortcut for `@RequestMapping(method = RequestMethod.DELETE)`. Handles DELETE requests.

- `@PatchMapping`  
  Shortcut for `@RequestMapping(method = RequestMethod.PATCH)`. Handles PATCH requests.

- `@RequestParam`  
  Binds a web request parameter to a method parameter.

- `@PathVariable`  
  Binds a URI template variable to a method parameter.

- `@RequestBody`  
  Binds the body of a web request to a method parameter.

- `@ResponseBody`  
  Indicates that the return value of a method should be used as the HTTP response body.

- `@CrossOrigin`  
  Enables Cross-Origin Resource Sharing (CORS) on a method or class.

- `@ExceptionHandler`  
  Defines a method to handle exceptions thrown by request handler methods.

- `@ControllerAdvice`  
  Allows you to handle exceptions across the whole application, not just in an individual controller.

- `@RestControllerAdvice`  
  Combines `@ControllerAdvice` and `@ResponseBody`. Applies to REST controllers.

- `@RequestScope`  
  Indicates that a bean is request-scoped.

- `@SessionScope`  
  Indicates that a bean is session-scoped.

- `@ApplicationScope`  
  Indicates that a bean is application-scoped.

- `@SessionAttributes`  
  Used to store model attributes in the session.

- `@ModelAttribute`  
  Binds a method parameter or method return value to a named model attribute.

- `@Async`  
  Indicates that a method should be executed asynchronously.


