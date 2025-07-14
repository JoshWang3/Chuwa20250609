# hw11 submission

## Q1: Add newly learned annotations to your previous cheatsheet, add explainations for these annotations.
### Answer:
#### General Stereotype Annotations
@Component
Marks a class as a Spring component (generic stereotype).

@Service
Marks a class as a service provider (specialization of @Component for business logic/service layer).

@Repository
Marks a class as a DAO (Data Access Object); provides automatic exception translation for persistence operations.

@Controller
Marks a class as a web controller (MVC Controller).

@RestController
Combines @Controller and @ResponseBody; used for REST APIs to return data as JSON/XML directly.

#### Request Mapping Annotations
@RequestMapping
Maps HTTP requests to handler methods at class or method level.

@GetMapping
Shortcut for @RequestMapping(method = RequestMethod.GET).

@PostMapping
Shortcut for @RequestMapping(method = RequestMethod.POST).

@PutMapping
Shortcut for @RequestMapping(method = RequestMethod.PUT).

@DeleteMapping
Shortcut for @RequestMapping(method = RequestMethod.DELETE).

#### Parameter Binding Annotations
@PathVariable
Binds a method parameter to a URI template variable.

@RequestParam
Binds a method parameter to a web request parameter.

@RequestBody
Binds the HTTP request body to a method parameter.

@ResponseBody
Indicates that the return value should be bound to the web response body.

#### Dependency Injection Annotations
@Autowired
Injects bean dependencies by type. Used on constructors, fields, or setters.

@Qualifier
Used with @Autowired to specify which bean to inject when multiple candidates exist.

@Primary
Indicates that a bean should be given preference when multiple candidates are qualified to autowire.

@Resource
Java annotation for dependency injection by name (alternative to @Autowired).

@Inject
From javax.inject, similar to @Autowired.

#### Bean Definition Annotations
@Bean
Indicates that a method produces a bean to be managed by Spring container (typically used in @Configuration classes).

@Configuration
Indicates that the class declares one or more @Bean methods and may be processed by Spring container to generate bean definitions.

@ComponentScan
Configures component scanning directives for Spring.

#### Spring Boot Annotations
@SpringBootApplication
Combination of @Configuration, @EnableAutoConfiguration, and @ComponentScan; main entry point for Spring Boot applications.

@EnableAutoConfiguration
Tells Spring Boot to start adding beans based on classpath settings and other configurations.

#### Transaction Management Annotations
@Transactional
Indicates that the method or class should be executed within a transaction context.

@EnableTransactionManagement
Enables Spring’s annotation-driven transaction management capability.

#### JPA/Hibernate Entity Annotations
@Entity
Marks a class as a JPA entity mapped to a database table.

@Table
Specifies the table name and unique constraints for the JPA entity.

@Id
Specifies the primary key of an entity.

@GeneratedValue
Defines generation strategy for primary key (e.g. GenerationType.IDENTITY).

@Column
Specifies column details for the field (e.g. name, nullable).

@CreationTimestamp
Hibernate annotation to auto-generate creation timestamp.

@UpdateTimestamp
Hibernate annotation to auto-generate update timestamp.

#### JPA Relationship Annotations
@ManyToOne
Defines many-to-one relationship between entities.

@OneToMany
Defines one-to-many relationship between entities.

@ManyToMany
Defines many-to-many relationship with a join table.

@JoinColumn
Specifies foreign key column in an entity relationship.

@JoinTable
Defines join table for many-to-many relationships.

#### JPA Query Annotations
@NamedQuery
Defines static named JPQL query at entity level.

@Query
Used in repository interface methods to define JPQL or native SQL queries.

@PersistenceContext
Injects an EntityManager to manage JPA operations.

#### Bean Scope and Miscellaneous Annotations
@Scope
Specifies the scope of a bean (singleton, prototype, request, session).

@ResponseStatus
Marks a method or exception class with the status code and reason for the response.

@ControllerAdvice
Allows global exception handling, binding, and model enhancements in controllers.

## Q3: Explain why do we need model mappers in Spring, and in what scanrios we need it.
### Answer:
In Spring and Spring Boot applications, especially those using layered architectures (Controller → Service → Repository), we often need to map between different types of objects—such as converting between Entity objects (used for persistence) and DTO objects (used for API communication). This is where Model Mappers come in.

Why Do We Need Model Mappers?

Separation of Concerns

Entities represent database models.

DTOs represent the data structure for API requests/responses.

Mapping between the two keeps the layers decoupled.

Security & Data Control

DTOs help prevent exposing sensitive entity fields (e.g., passwords, internal IDs).

You can customize what is sent to the client.

Data Transformation

Sometimes, DTO fields are computed or derived from multiple entity fields.

Maintainability

Easier to refactor code as models evolve without breaking APIs or persistence.

Example:

Add Dependency (Maven):

```java
<dependency>
<groupId>org.modelmapper</groupId>
<artifactId>modelmapper</artifactId>
<version>3.1.1</version>
</dependency>
```

Configuration:

```java
@Configuration
public class AppConfig {
@Bean
public ModelMapper modelMapper() {
return new ModelMapper();
}
}
```

Mapping:

```java
@Autowired
private ModelMapper modelMapper;

public UserDTO convertToDto(User user) {
return modelMapper.map(user, UserDTO.class);
}

public User convertToEntity(UserDTO dto) {
return modelMapper.map(dto, User.class);
}
```

## Q4: Provide 3 examples in which model mapper will NOT map succesfully, explain why.
### Answer:

```java
```

Example 1: Mismatched Field Names

ModelMapper maps by matching field names. If DTO and Entity field names are different, it won’t map automatically.

Entity

```java
public class User {
private String firstName;
private String lastName;
}
```

DTO

```java
public class UserDTO {
private String givenName; // different name
private String familyName;
}
```

Issue

```java
User user = new User("John", "Doe");
UserDTO dto = modelMapper.map(user, UserDTO.class);

// dto.givenName and dto.familyName will be null! 
```

Fix: You need to configure explicit mappings:

```java
modelMapper.typeMap(User.class, UserDTO.class).addMappings(mapper -> {
mapper.map(User::getFirstName, UserDTO::setGivenName);
mapper.map(User::getLastName, UserDTO::setFamilyName);
});
```

Example 2: Nested or Complex Object Mapping

Problem: ModelMapper won’t automatically map nested objects unless configured recursively.

Entity
```java
public class Order {
private Customer customer;
}

public class Customer {
private String name;
}
```

DTO
```java
public class OrderDTO {
private String customerName;
}
```

Issue
```java
OrderDTO dto = modelMapper.map(order, OrderDTO.class);
// customerName will be null because ModelMapper doesn't know to get customer.name
```

Fix Manually define the custom mapping:
```java
modelMapper.typeMap(Order.class, OrderDTO.class).addMappings(mapper -> {
mapper.map(src -> src.getCustomer().getName(), OrderDTO::setCustomerName);
});
```

Example 3: Incompatible Data Types

Problem: ModelMapper fails silently or throws an exception when field types are incompatible.

Entity
```java
public class Product {
private double price; // primitive
}
```

DTO
```java
public class ProductDTO {
private String price; // string representation
} 
```

Issue
```java
ProductDTO dto = modelMapper.map(product, ProductDTO.class);
// ModelMapper cannot auto-convert double to String
```

Fix: Register a custom converter:
```java
Converter<Double, String> doubleToString = ctx -> ctx.getSource() == null ? null : String.valueOf(ctx.getSource());
modelMapper.addConverter(doubleToString);
```

## Q5: Explain how model mapper cast different data types between source object and target class.
### Answer:
1. ModelMapper comes with several built-in converters that handle simple type casting automatically:

```java
public class Product {
private double price;
}

public class ProductDTO {
private String price;
}
```
```java
Product product = new Product();
product.setPrice(29.99);

ProductDTO dto = modelMapper.map(product, ProductDTO.class);
// dto.price => "29.99"
```
This works because ModelMapper uses a built-in converter for double → String.

2. If the types are too different or complex (e.g., List<String> to String, or a custom object to int), ModelMapper may:

Throw a ConfigurationException

Silently skip the field

Set the value to null

```java
public class Order {
private List<String> itemIds;
}

public class OrderDTO {
private String itemIds;
}
```
```java
OrderDTO dto = modelMapper.map(order, OrderDTO.class);
// itemIds will be null – no default converter for List<String> → String
```

3. To handle unsupported type conversions, you can register custom converters.

Example: Convert LocalDate → String
```java
Converter<LocalDate, String> dateToString = ctx ->
ctx.getSource() == null ? null : ctx.getSource().toString();

modelMapper.addConverter(dateToString);
```

Now ModelMapper can map:
```java
public class Person {
private LocalDate birthDate;
}

public class PersonDTO {
private String birthDate;
}
```

## Q6: Add your own API exceptions so that when something wrong happens in service layer, your rest API will return your customized response and status code.
### Answer:

```java
```

## Q7: Explain how Controller Advices work, is there any other approach to do same/similar global API exception handling?
### Answer:
@ControllerAdvice is a specialized component in Spring used to handle global exceptions (and other cross-cutting concerns) across multiple controllers.

It works with @ExceptionHandler to catch exceptions thrown by any @RestController or @Controller in your application and respond uniformly.

Example: Global Exception Handling with @ControllerAdvice
```java
@RestControllerAdvice // or just @ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralError(Exception ex) {
        return new ResponseEntity<>("Something went wrong: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

@RestControllerAdvice = @ControllerAdvice + @ResponseBody (used for REST APIs).

When a controller throws an exception, Spring looks for a matching @ExceptionHandler.

If none is found in the controller itself, it checks global handlers marked with @ControllerAdvice.

The first handler with a matching exception type is invoked.

Alternatives to @ControllerAdvice
Approach	Description	Use Case
ExceptionHandler in each controller	Use @ExceptionHandler inside each controller class	If handling is controller-specific
Filter or Interceptor	Create a Filter or HandlerInterceptor to catch exceptions	For logging or auth-related exceptions
ResponseEntityExceptionHandler	Extend Spring’s built-in class to override common exception handling	When you want to handle default Spring exceptions like MethodArgumentNotValidException
Aspect-Oriented Programming (AOP)	Use @Aspect to catch exceptions in service layer	When exception handling logic is not tied to HTTP responses

## Q8: What's the difference between throwing a regular exception and a customized API exception that will be eventually thrown to Controller Advice codes? Please provide screenshots to explain your findings.
### Answer:

```java
```

## Q9: Write some regular expression to restrict the value of attributes that your Post or Comment can have. You may use https://regex101.com/ to construct and test/validate your regular expression.
### Answer:

```java
```

## Q10: Explain Spring framework fundamental principles. And how can they help build business applications?
### Answer:
1. Dependency Injection (DI) / Inversion of Control (IoC)
   Spring promotes loose coupling by letting the framework manage object creation and wiring dependencies instead of hardcoding them.

Example
```java
@Component
public class OrderService {
private final PaymentService paymentService;

    @Autowired
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```
Benefits: Easy to swap implementations (e.g., mock vs real), improves testability, flexibility, and maintainability.

2. Aspect-Oriented Programming (AOP)
   Used to separate cross-cutting concerns like logging, security, transactions, etc., from business logic.

Example
```java
@Aspect
@Component
public class LoggingAspect {
@Before("execution(* com.example.service.*.*(..))")
public void logBefore(JoinPoint joinPoint) {
System.out.println("Executing method: " + joinPoint.getSignature());
}
}
```
Benefits: Clean code, centralized handling of repetitive tasks, better modularity.

3. Modularity
   Spring is modular—you can use only what you need (e.g., Spring Web, Spring Data, Spring Security), instead of the whole framework.

Benefits: Lightweight and flexible architecture.

4. Transaction Management
   Spring provides a consistent programming model for transaction management (declarative and programmatic).

Example
```java
@Transactional
public void transferFunds(Account from, Account to, BigDecimal amount) {
// Debit from and credit to accounts
}
```
Benefits: Simplifies working with databases and ensures data consistency.

5. Convention over Configuration
   Spring Boot extends Spring by providing auto-configuration and sensible defaults to reduce boilerplate code.

Example
```java
@SpringBootApplication
public class App {
public static void main(String[] args) {
SpringApplication.run(App.class, args);
}
}
```
Benefits: Fast development, minimal setup, production-ready defaults.

6. Testability
   Spring supports integration with JUnit, Mockito, and Testcontainers, allowing dependency injection and test context setup.

Example
```java
@SpringBootTest
public class OrderServiceTest {
@Autowired
private OrderService orderService;

    @Test
    public void testPlaceOrder() {
        // test logic
    }
}
```

How These Principles Help in Building Business Applications
Principle	Business Impact
DI/IoC	Enables maintainable, testable code, reducing technical debt
AOP	Helps implement enterprise features like logging, audit, security transparently
Modularity	Lets you scale the architecture incrementally
Transaction Management	Ensures business logic runs reliably with ACID properties
Convention over Configuration	Speeds up development, reduces setup effort
Testability	Promotes robust CI/CD and faster feedback loops

## Q11: Explain different types of dependency injection, explain their suitable use cases, and why fielde injection is not recommended in general. Please provide necessary code snippets and screenshots if possible.
### Answer:
1. Constructor Injection
   Dependencies are passed through the constructor of the class.

Example
```java
@Component
public class OrderService {

    private final PaymentService paymentService;

    @Autowired // Optional in Spring 4.3+ if only one constructor
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```
Use Case

Immutability: Good for required dependencies

Testability: Easy to test with constructor injection

Null-safety: Ensures required fields are initialized

2. Setter Injection (Optional Dependencies)
   Spring calls a public setter method to inject the dependency.

Example
```java
@Component
public class NotificationService {

    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
```
Use Case

When a dependency is optional

When using configuration from external sources

When circular dependencies exist (though best to avoid these)

3. Field Injection 🚫 (Not Recommended)
   Spring injects dependencies directly into fields via reflection.

Example
```java
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;
}
```

Best Practice:

Use Constructor Injection for mandatory dependencies.

Use Setter Injection for optional or late-set dependencies.

Avoid Field Injection unless absolutely necessary (e.g., in legacy code or small prototypes).

## Q12: Explain different types of application context in Spring framework, with screenshots. You may take https:// github.com/CTYue/springIOC for reference.
### Answer:
1. ClassPathXmlApplicationContext
   Description: Loads the bean definitions from an XML file located in the classpath.

Use case: Legacy applications or when using XML-based configuration.

Example from project:
```java
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
UserService userService = context.getBean(UserService.class);
userService.save();
```
applicationContext.xml in the resources folder 

2. FileSystemXmlApplicationContext
Description: Loads the context definition from an XML file in the filesystem, outside the classpath.

Use case: When configuration files are not bundled with the application (e.g., for externalized config).

Example:
```java
ApplicationContext context = new FileSystemXmlApplicationContext("C:/configs/applicationContext.xml");
```
the XML file on disk and the Java class reading it using this path.

3. AnnotationConfigApplicationContext
Description: Loads Spring beans using Java-based configuration with annotations (no XML).

Use case: Modern Spring apps (including Spring Boot) prefer this over XML.

Example:
```java
@Configuration
@ComponentScan(basePackages = "com.ctyue")
public class AppConfig { }

public class Main {
public static void main(String[] args) {
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
UserService userService = context.getBean(UserService.class);
userService.save();
}
}
```

@Configuration class 

4. WebApplicationContext
Description: A specialized version of ApplicationContext for web applications. It integrates with the ServletContext and is used in Spring MVC.

Use case: Only used in Spring web projects. Spring Boot handles this automatically.

Usage: Usually defined in web.xml or configured by Spring Boot auto-configuration.

Screenshot idea: In a web project, show how a DispatcherServlet uses WebApplicationContext.



```java
```

## Q13: Compare @Component and @Bean and in which scenario they should be used.
### Answer:
@Component is an annotation you place on a class to tell Spring to automatically detect and register it as a bean during classpath scanning. It works with annotations like @Service, @Repository, and @Controller, which are just specializations of @Component.

Use @Component when you're working with your own classes — for example, a service class or a utility component you've written. When Spring scans the package, it will detect the class and automatically add it to the application context.

Example:
```java
@Component
public class EmailService {
public void send(String message) {
System.out.println("Email sent: " + message);
}
}
```
This class will be automatically discovered and managed by Spring.

@Bean, on the other hand, is used on a method inside a class annotated with @Configuration. It tells Spring, “run this method and register the returned object as a bean.” This approach gives you full control over the bean creation process, including custom initialization logic.

Use @Bean when you need to create and configure third-party classes, or if the instantiation of the bean is complex and cannot be easily handled with @Component.

Example:
```java
@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```
In this case, BCryptPasswordEncoder is a class from a third-party library, so you can't annotate it directly with @Component. Instead, you define it in a @Bean method.

## Q14: Explain Spring bean scopes and how to pick the correct bean scope.
### Answer:
In Spring, a bean scope defines the lifecycle and visibility of a bean — that is, how long the bean will live and where it will be shared. By default, all Spring beans are singleton, but Spring supports several scopes to suit different application needs.

1. Singleton (Default Scope)
   Meaning: Only one instance of the bean is created per Spring container.

When to use: Most services, repositories, and components that don’t hold user-specific state.

Example:
```java
@Component
public class UserService { }
```
This will be a singleton bean unless you specify otherwise.

2. Prototype
   Meaning: A new instance is created every time the bean is requested from the Spring container.

When to use: For stateful or short-lived beans, such as objects that are used temporarily and discarded.

Example:
```java
@Scope("prototype")
@Component
public class TempFileHandler { }
```
Each request to this bean returns a new instance.

3. Request (Web-aware scope)
   Meaning: One bean instance per HTTP request.

When to use: For web applications where you need user/request-specific state (e.g., request data, session attributes).

Example:
```java
@Scope("request")
@Component
public class RequestTracker { }
```
Only works in a web application context.

4. Session
   Meaning: One bean instance per HTTP session.

When to use: When you want to store user-specific state across multiple requests, like shopping carts or login info.

Example:
```java
@Scope("session")
@Component
public class UserSessionData { }
```

5. Application
   Meaning: One bean instance per ServletContext (shared across the whole application).

When to use: For shared application-wide data, like caching, configuration, or global metrics.

Example:
```java
@Scope("application")
@Component
public class AppStats { }
```

6. WebSocket (Specialized use case)
   Meaning: One bean instance per WebSocket session.

When to use: For applications using WebSockets where you need session-based state.

How to Pick the Correct Scope?

Use singleton for stateless, reusable services (default and most common).

Use prototype for beans that hold temporary or user-specific data and shouldn't be shared.

Use request/session/application only in web applications when managing web-specific state.

Use WebSocket scope when building real-time apps like chat systems.

## Q15: Explain the difference between bean id and bean class.
### Answer:
Bean Class

The bean class refers to the Java class from which the bean is instantiated. It defines the type and behavior of the bean.

Example:
```java
@Component
public class EmailService {
// Business logic here
}
```
Here, EmailService is the bean class. It’s the actual Java class that Spring will create and manage as a bean.

Bean ID

The bean id is the name by which the bean is registered in the Spring ApplicationContext. It's how you refer to or autowire the bean.

When using @Component, the default bean id is the class name with the first letter in lowercase (e.g., emailService).

You can also explicitly set the id using annotations like @Component("customName") or in XML configuration.

Example:
```java
@Component("mailer")
public class EmailService {
}
```
Now the bean id is "mailer" instead of the default "emailService".

## Q16: Explain that when a bean has multiple alternative implementations, how will Spring decide which bean implementation to inject/autowire?
### Answer:
In Spring, if multiple beans implement the same interface (or extend the same class), and you autowire that interface, Spring won’t know which one to inject unless you specify it explicitly.

If you don’t guide Spring, it will throw a NoUniqueBeanDefinitionException.

Example
```java
public interface PaymentService {
void pay(double amount);
}

@Component
public class PaypalPaymentService implements PaymentService {
public void pay(double amount) {
System.out.println("Paid with PayPal: $" + amount);
}
}

@Component
public class StripePaymentService implements PaymentService {
public void pay(double amount) {
System.out.println("Paid with Stripe: $" + amount);
}
}
```

Now if you do this:
```java
@Autowired
private PaymentService paymentService;
```
Spring will fail with an error like:

No qualifying bean of type 'PaymentService' available: expected single matching bean but found 2
