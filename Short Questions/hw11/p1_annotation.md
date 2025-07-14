### ModelMapper and Exceptions
- @OneToMany : This annotation defines a one-to-many relationship between two entities in JPA, where one entity can be associated with multiple instances of another entity, such as a Post having multiple Comments.
- @ManyToOne : This annotation defines a many-to-one relationship between two entities in JPA, where multiple instances of one entity can be associated with a single instance of another entity, such as multiple Comments belonging to one Post.
- @JoinColumn : This annotation specifies the foreign key column used for joining an entity association or element collection, defining which column in the database table represents the relationship between entities.
- @Component : This annotation marks a Java class as a Spring-managed component, making it eligible for auto-detection during classpath scanning and automatic registration as a bean in the Spring IoC container.
- @RequestParam : This annotation binds HTTP request parameters to method parameters in Spring MVC controllers, allowing you to extract query parameters, form data, or other request parameters from the HTTP request.
- @Configuration : This annotation indicates that a class declares one or more - @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.
- @Bean : This annotation is used on methods within - @Configuration classes to indicate that the method produces a bean to be managed by the Spring container, typically used for third-party library objects that need to be registered in the IoC container.
- @ControllerAdvice : This annotation enables global exception handling across the whole application, allowing you to handle exceptions thrown by any controller in a centralized manner rather than handling them individually in each controller.
- @ExceptionHandler : This annotation is used within - @ControllerAdvice classes to define methods that handle specific exceptions, allowing you to customize the response when particular exceptions are thrown during request processing.
- @JsonProperty : This annotation is used to map JSON property names to Java object fields during serialization and deserialization, allowing you to customize how JSON data is converted to and from Java objects.


### Validation
- @NotEmpty - Ensures fields are not null or empty 
  - `@NotEmpty(message = "Name should not be null or empty")`

- @Size - Validates string length constraints: 
  - `@Size(min = 2, message = "Post title should have at least 2 characters")`

- @Email - Validates email format
- @Valid - Triggers validation on controller parameters
  - `public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {}`


Based on the IOC project in the documents, here are the key annotations used:

### Inversion of Control (IOC)



**@Bean**: Indicates that a method produces a bean to be managed by the Spring container. Used in @Configuration classes to define beans programmatically.

**@Component**: Generic stereotype annotation for any Spring-managed component. Indicates that an annotated class is a "component" and will be auto-detected during classpath scanning.

**@Primary**: Indicates that a bean should be given preference when multiple candidates are qualified to autowire a single-valued dependency.

**@Qualifier**: Used together with @Autowired to specify which bean to inject when multiple beans of the same type exist.


**@Scope**: Defines the scope of a bean (singleton, prototype, request, session, etc.). In the example, @Scope("prototype") creates a new instance each time the bean is requested.


**@Autowired**: Marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection facilities. Can be applied to constructors, fields, and setter methods.


