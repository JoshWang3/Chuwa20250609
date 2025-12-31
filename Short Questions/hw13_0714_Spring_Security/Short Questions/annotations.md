# Annotations from hw10 JPA2

## 1. Annotation in Main Entry file like RedbookApplication.java

### `@SpringBootApplication`
A convenience annotation that combines @Configuration, @EnableAutoConfiguration, and @ComponentScan. It is typically placed on the main application class.
表示整个程序的入口

## 2. Annotations in Controller.java

### `@RestController`
A shortcut for @Controller and @ResponseBody, commonly used in RESTful web services.

It indicates:
它表示：

This class is a Spring MVC Controller.
这个类是一个 Spring MVC 控制器。

Every method’s return value will be serialized (typically as JSON) and sent directly in the HTTP response body.
每个方法的返回值将被序列化（通常是 JSON 格式），并直接发送在 HTTP 响应体中。

### `@RequestMapping`:
@RequestMapping only maps HTTP requests to handler methods or classes.@RequestMapping 
仅将 HTTP 请求映射到处理器方法或类。

It does not tell Spring that the class is a controller and does not handle response serialization.
它不会告诉 Spring 这个类是一个控制器，也不会处理响应序列化。

### `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`
Specific HTTP method shortcuts for `@RequestMapping`.
特定 HTTP 方法的快捷方式。



## 3. Annotations in Entity(like Post.java):

### `@Entity`
Marks a Java class as a JPA entity, indicating that instances of this class can be persisted to a database.
将 Java 类标记为 JPA 实体，表示该类的实例可以持久化到数据库中。

### `@Table`
Used in conjunction with `@Entity`, this annotation allows you to specify details about the database table to which the entity is mapped, such as the table name, schema, and unique constraints. 
与 `@Entity` 结合使用时，此注解允许您指定实体映射到的数据库表的详细信息，例如表名、模式以及唯一约束。

### `@Id`
Designates a field within an entity class as the primary key of the corresponding database table.
指定实体类中的一个字段作为相应数据库表的主键。

### `@GeneratedValue`
Used with `@Id`, this annotation specifies the strategy for generating primary key values (e.g., identity, sequence, auto).
与 `@Id` 结合使用时，此注解指定生成主键值的策略（例如，identity、sequence、auto）。

### `@Column`
Provides fine-grained control over the mapping of an entity field to a database column, including the column name, length, nullability, and uniqueness.
提供对实体字段映射到数据库列的细粒度控制，包括列名、长度、可空性和唯一性。

### `@Transient`
Indicates that a field should not be persisted to the database and should be ignored by JPA. 
指示该字段不应持久化到数据库，JPA 应忽略该字段。

### Relationship Annotations:关系注解：

### `@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`

These annotations define the different types of relationships between entities (one-to-one, one-to-many, many-to-one, and many-to-many).
这些注解定义了实体之间不同类型的关系（一对一、一对多、多对一和多对多）。

### `@JoinColumn`

Used to specify the foreign key column in a relationship.
用于指定关系中的外键列。

### `@JoinTable`

Used in many-to-many relationships to define the join table that manages the association. 
在多对多关系中，用于定义管理关联的连接表。


## 4. Annotations in Repository.java:

### `@Query`

The `@Query` annotation in Spring Data JPA provides a mechanism to define custom database queries directly within repository interface methods. This offers flexibility beyond the automatically generated query methods based on method names.
Spring Data JPA 中的 `@Query` 注解提供了一种机制，可以直接在仓库接口方法中定义自定义数据库查询。这提供了比基于方法名自动生成的查询方法更大的灵活性。

#### Key features and usage:主要特点和用法：

#### Custom Queries:  自定义查询：

It allows you to write custom JPQL (Java Persistence Query Language) or native SQL queries.
它允许你编写自定义的 JPQL（Java 持久化查询语言）或原生 SQL 查询。

#### JPQL:

By default, the value attribute of @Query expects a JPQL query, which operates on entities and their relationships. 
默认情况下， value 属性期望 @Query 为 JPQL 查询，该查询作用于实体及其关系。

```java

    /**
     * JPQL
     * use Entity name other than database table name.
     * index Parameters
     * @return post
     */
    @Query("select p from Post p where p.id = ?1 or p.title = ?2")
    Post getPostByIDOrTitleWithJPQLIndexParameters(Long id, String title);

    /**
     * JPQL
     * use Entity name other than database table name.
     * index Parameters
     * @return post
     */
    @Query("select p from Post p where p.id = :key or p.title = :title")
    Post getPostByIDOrTitleWithJPQLNamedParameters(@Param("key") Long id,
                                                   @Param("title") String title);
```

- Native SQL: To use native SQL, set the nativeQuery attribute to true. This allows you to write database-specific SQL queries.
原生 SQL：要使用原生 SQL，将 nativeQuery 属性设置为 true 。这允许你编写特定于数据库的 SQL 查询。

```java
    /**
     * SQL
     * use database table name.
     * index Parameters
     * @return post
     */
    @Query(value = "select * from posts p where p.id = ?1 or p.title = ?2", nativeQuery = true)
    Post getPostByIDOrTitleWithSQLIndexParameters(Long id, String title);
```
#### Parameters:  参数：

You can pass parameters to your queries using:
您可以使用以下方式向您的查询传递参数：

- Positional parameters: `?1`, `?2`, etc., corresponding to the method's argument order.
位置参数： `?1` 、 `?2` 等，对应于方法参数的顺序。

- Named parameters: :paramName, which are then bound using the `@Param("paramName")` annotation on the method arguments.
命名参数： :paramName ，这些参数通过在方法参数上使用 @Param("paramName") 注解进行绑定。

`@Modifying`:

For update or delete operations using `@Query`, you must also annotate the method with `@Modifying` to indicate that the query modifies the database.
使用 `@Query` 进行更新或删除操作时，您还必须用 `@Modifying` 注解该方法，以表明该查询会修改数据库。

```java
    @Modifying
    @Query("UPDATE User u SET u.active = false WHERE u.id = ?1")
    void deactivateUser(Long userId);
```

- Precedence: Queries defined with `@Query` take precedence over named queries (defined with `@NamedQuery` or in orm.xml). 
优先级：使用 `@Query` 定义的查询优先级高于命名查询（使用 `@NamedQuery` 定义或在 orm.xml 中定义的）。

#### When to use @Query:何时使用 `@Query` ：

- When the derived query methods (e.g., `findByLastNameAndFirstName`) become too complex or insufficient for your needs.
当衍生查询方法（例如 `findByLastNameAndFirstName` ）变得过于复杂或无法满足您的需求时。

- When you need to perform more complex queries involving joins, aggregations, or specific database functions not easily expressed by derived methods.
当你需要执行涉及连接、聚合或特定数据库函数的复杂查询，而这些函数难以通过派生方法表达时。

- When you want to optimize query performance by writing highly specific and optimized queries.
当你希望通过编写高度特定和优化的查询来优化查询性能时。



## 5. Other Annotations

### `@Autowired`
Used for automatic dependency injection.
用于自动依赖注入。

### `@Service`, `@Repository`, `@Controller`, `@Component`

Stereotype annotations to categorize Spring-managed components based on their roles.
@Service、@Repository、@Controller、@Component：根据其角色对 Spring 管理的组件进行分类的模板注解。


### `@Configuration`
Indicates a class that contains Spring bean definitions.
指示一个包含 Spring Bean 定义的类。


### `@Bean`
Used to explicitly declare a Spring bean within a configuration class.
用于在配置类中显式声明一个 Spring Bean。


### `@EnableAutoConfiguration`
Enables Spring Boot's auto-configuration mechanism.
启用 Spring Boot 的自动配置机制。


### `@Qualifier`
Used to resolve ambiguity when multiple beans of the same type are available for autowiring.
用于在存在多个相同类型的 Bean 可供自动装配时解决歧义。

### `@Value`
Injects values from properties files or environment variables.
从属性文件或环境变量中注入值。

### `@Transactional`
Manages transactional behavior for methods or classes.
管理方法或类的事务行为。

### `@ExceptionHandler`
Handles exceptions thrown by specific handler methods.
处理特定处理器方法抛出的异常。

## 6. Annotation of Annotation:

```java
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    ...
}
```
@Target and @Retention are on each Annotation.

### `@Target`

The @Target annotation in Spring JPA, similar to its usage in standard Java, is a meta-annotation that specifies the applicable locations for a custom annotation. It dictates where a particular annotation can be used within the code.
Spring JPA 中的 @Target 注释，与它在标准 Java 中的使用类似，是一个元注释，用于指定定制注释的适用位置。它规定特定注释可以在代码中的哪些地方使用。

| Element Type |	Element to be Annotated |
|------|------|
|Type |	Class, interface or enumeration|
|Field |	Field|
|Method |	Method |
|Constructor | Constructor|
|Local_Variable |	Local variable| 
|Annotation_Type |	Annotation Type |
|Package | PACKAGE|
|Type_Parameter	|Type Parameter |
|Parameter |	Formal Parameter|

While @Target is not a direct JPA annotation itself (like @Entity or @Id), it becomes relevant in Spring JPA when defining custom annotations that might be used alongside JPA entities or repositories.
虽然 @Target 本身不是 JPA 注释（如 @Entity 或 @Id ），但在 Spring JPA 中定义可能用于 JPA 实体或仓库的定制注释时，它变得相关。

### `@Retention`

The `@Retention` annotation in Java, and consequently in Spring Boot, is a meta-annotation used to specify how long an annotation with the annotated type is to be retained. It determines at what point in the program's lifecycle the annotation information will be available.

Java 中的 `@Retention` 注解，以及在 Spring Boot 中，是一个元注解，用于指定被注解类型的注解应保留多久。它决定了注解信息在程序生命周期中的哪个阶段可用。
There are three main RetentionPolicy values that can be applied:

可以应用三种主要的 RetentionPolicy 值：

1. #### `RetentionPolicy.SOURCE`:

- Annotations with this policy are retained only in the source code.
采用此策略的注解仅保留在源代码中。

- They are discarded by the compiler during compilation and are not included in the .class file.
它们在编译期间被编译器丢弃，并且不包含在 .class 文件中。

- These are typically used for compile-time processing, such as by IDEs, code generators, or for documentation purposes (e.g., @Override, @SuppressWarnings).
它们通常用于编译时处理，例如 IDE、代码生成器或用于文档目的（例如 @Override 、 @SuppressWarnings ）。

2. #### `RetentionPolicy.CLASS`:

- Annotations with this policy are stored in the .class file during compilation.
采用此策略的注解在编译期间存储在 .class 文件中。

- However, they are not retained by the Java Virtual Machine (JVM) at runtime and cannot be accessed through reflection.
然而，它们在运行时不会被 Java 虚拟机（JVM）保留，也无法通过反射来访问。

- This policy is often used for annotations that provide metadata for tools that process bytecode, but not for runtime behavior.
这项策略通常用于为处理字节码的工具提供元数据的注解，但不用于运行时行为。

3. #### `RetentionPolicy.RUNTIME`:

- Annotations with this policy are stored in the .class file and are also retained by the JVM at runtime.
采用这项策略的注解存储在 .class 文件中，并且在运行时由 JVM 保留。

- This allows them to be accessed and processed reflectively by the application at runtime.
这使得它们可以在运行时被应用程序通过反射访问和处理。

- Many Spring Boot annotations, such as @Component, @Service, @Repository, and custom annotations that need to be processed by Spring's dependency injection or AOP mechanisms, use RetentionPolicy.RUNTIME to enable their functionality.
许多 Spring Boot 注解，如 @Component 、 @Service 、 @Repository ，以及需要通过 Spring 依赖注入或 AOP 机制处理的自定义注解，使用 RetentionPolicy.RUNTIME 来启用其功能。

In the context of Spring Boot, @Retention(RetentionPolicy.RUNTIME) is crucial for most of Spring's core annotations and any custom annotations you create that need to be read and acted upon by the Spring framework during application startup or execution. This allows Spring to discover components, configure beans, apply aspects, and perform other runtime operations based on the annotation metadata.
在 Spring Boot 的上下文中， @Retention(RetentionPolicy.RUNTIME) 对于大多数 Spring 的核心注解以及任何需要在应用程序启动或执行期间被 Spring 框架读取和处理的自定义注解都至关重要。这使得 Spring 能够根据注解元数据发现组件、配置 Bean、应用切面并执行其他运行时操作。


# Annotations from hw11 IOC

## Spring Bean Annotations:

The bean.xml file  is a Spring configuration file that serves several important purposes:

1. **Bean Definition and Configuration:** This XML file defines Spring beans (Java objects managed by the Spring IoC container). In your file, there's a bean defined with id "dataNucleusChuwaNoComponent" and class "com.chuwa.springbasic.components.impl.DataNucleusChuwaNoComponent".

2. **Lifecycle Management:** The bean definition includes lifecycle callbacks:

- init-method="init": Specifies a method to be called after the bean is instantiated
- destroy-method="destroy": Specifies a method to be called when the bean is being destroyed
3. **Component Scanning:** The `<context:component-scan>` element tells Spring to scan the package "com.chuwa.springbasic" and its sub-packages for components annotated with stereotypes like @Component, @Service, @Repository, and @Controller.

4. **XML Schema Definitions:** The file includes XML namespace declarations and schema locations that define the structure and validation rules for the Spring configuration elements.

This configuration approach is part of Spring's Dependency Injection mechanism, allowing you to externalize the configuration of application components and their dependencies outside of your Java code. When a Spring application starts, it reads this XML file to understand which objects to create, how to configure them, and how to wire them together.

While modern Spring applications often use Java-based configuration with annotations, XML configuration like this is still used, especially in legacy applications or when explicit external configuration is preferred.



Java-based configuration with annotations is a modern approach to configuring Spring applications that reduces or eliminates the need for XML configuration files like bean.xml. Let me explain this approach in more detail:

**Java-based Configuration with Annotations**
Java-based configuration uses Java classes and annotations instead of XML to define beans and configure the Spring application context. Here's how it works:

**Core Components:**
### 1. **@Configuration Classes** 
These are Java classes annotated with @Configuration that serve as a replacement for XML configuration files.

```java
@Configuration
public class AppConfig {
    // Bean definitions go here
}
```
### 2. **@Bean Methods** 
Methods within @Configuration classes that are annotated with @Bean define beans to be managed by Spring.

```java
@Configuration
public class AppConfig {
    @Bean
    public DataNucleusChuwaNoComponent dataNucleusComponent() {
        return new DataNucleusChuwaNoComponent();
    }
    
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public DataNucleusChuwaNoComponent dataNucleusComponentWithLifecycle() {
        return new DataNucleusChuwaNoComponent();
    }
}
```
### 3. **Component Scanning** 
@ComponentScan replaces the XML <context:component-scan> element:

```java
@Configuration
@ComponentScan(basePackages = "com.chuwa.springbasic")
public class AppConfig {
    // Configuration code
}
```

### 4. **Stereotype Annotations** 
Classes can be automatically registered as beans using annotations like:

- @Component: Generic component
- @Service: Business service layer
- @Repository: Data access layer
- @Controller: Web controller
- @RestController: REST API controller

### 5. **Dependency Injection Annotations**

- @Autowired: Injects dependencies
- @Qualifier: Specifies which bean to inject when multiple candidates exist
- @Value: Injects property values

### 6. **Lifecycle Annotations**

- @PostConstruct: Method to call after bean initialization
- @PreDestroy: Method to call before bean destruction

### Example 1
For your specific DataNucleusChuwaNoComponent class, the Java-based configuration equivalent to your XML would be:

```java
@Configuration
@ComponentScan(basePackages = "com.chuwa.springbasic")
public class AppConfig {
    
    @Bean(name = "myName", initMethod = "init", destroyMethod = "destroy")
    @Primary
    public JpaChuwa dataNucleusChuwaNoComponent() {
        return new DataNucleusChuwaNoComponent();
    }
}
```
```java
import com.chuwa.springbasic.components.JpaChuwa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DependencyInjectionByTypeByName {

//    Doesn't work because [myName] must be a valid Java class or interface
//    @Autowired
//    private myName byType;
    
    @Autowired
    private JpaChuwa myName;
    
    @Autowired
    @Qualifier("myName")
    private JpaChuwa jpaChuwaQualifier;
}
```

### Example 2
#### XML approach:

![xml](./img/xml_2.png)
`@Component` class:
![xml](./img/component.png)
![xml](./img/xml_3.png)
no `@Component` on class:
![xml](./img/xml_1.png)

#### Annotation approach:

![annotation](./img/annotation_2.png)
`@Component` class:
![annotation](./img/component.png)
![annotation](./img/component_2.png)
no `@Component` on class:
![annotation](./img/annotation_3.png)
![annotation](./img/annotation_1.png)


**Advantages of Java-based Configuration:**

1. Type safety - Compile-time checking catches errors earlier
2. Better refactoring support - IDE tools work better with Java code than XML
3. Improved testability - Easier to unit test configuration
4. More powerful - Full power of Java language for configuring beans
5. Centralized configuration - Configuration logic in Java classes instead of scattered XML files
Most modern Spring applications use a mix of annotation-based configuration and minimal XML, or eliminate XML entirely in favor of Java configuration. Spring Boot, which builds on top of Spring, takes this approach even further with convention-over-configuration and auto-configuration capabilities.



## Annotations on input validattion
### Javax
#### @NotEmpty
#### @Size
#### @Pattern

```java
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PostDto {
    private Long id;
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    @NotEmpty
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character, and be at least 8 characters long"
    )
}
```
#### @Valid
```java
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping()
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        PostDto postResponse = postService.createPost(postDto);
        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }
}
```
#### @ControllerAdvice
```java
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Validation,
     * if invalid, then throw back exceptions/errors
     * 1. payload -> Rule
     * 2. @Valid -> where apply Rule, if, invalid, throw exception
     * 3. global exception -> accept and handle
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
```


### jakarta
In Spring Boot, **input validation** for controller methods is typically done using **Java Bean Validation (JSR-380)** annotations from the `javax.validation` or `jakarta.validation` package.  
These annotations ensure that incoming request data (usually in DTOs) meets defined constraints **before reaching your business logic**.

---



#### **DTO (Data Transfer Object)**
##### @NotBlank
```java
import jakarta.validation.constraints.*;

public class PostDTO {

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @Pattern(regexp = "^[a-zA-Z0-9 .,!?-]{10,500}$", 
             message = "Content must be 10–500 characters, only letters, digits, and punctuation allowed")
    private String content;

    @Positive(message = "User ID must be positive")
    private Long userId;

    // getters and setters
}
```

#### **Controller**
##### @Validated
```java
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @PostMapping
    public ResponseEntity<String> createPost(@Validated @RequestBody PostDTO postDTO) {
        return ResponseEntity.ok("Post created successfully!");
    }
}
```

If a request fails validation, Spring automatically throws a `MethodArgumentNotValidException`, which can be handled globally using your `@ControllerAdvice`.

---

### 🧠 **2\. How It Works**

-   When you annotate a DTO with `@Validated` or `@Valid`, Spring Boot:
    
    1.  Checks incoming request JSON against all validation annotations.
        
    2.  If any field fails, it **stops the request** before calling the controller method.
        
    3.  A default 400 Bad Request is returned with details about which field failed.

# Annotations  for Spring Security

Spring Security offers various annotations for securing applications at the method level. These annotations provide fine-grained control over access to methods based on roles, permissions, and custom expressions.
Spring Security 提供了多种注解来在方法级别上保护应用程序。这些注解基于角色、权限和自定义表达式，为方法访问提供了细粒度的控制。
## Method-Level Security Annotations:方法级别安全注解：
- `@EnableMethodSecurity`: This annotation is used at the class level (typically on a @Configuration class) to enable Spring's method-level security. It allows the other method security annotations to be processed.
`@EnableMethodSecurity` ：此注解用于类级别（通常在 @Configuration 类上），以启用 Spring 的方法级别安全。它允许其他方法安全注解被处理。
- `@Secured`: This annotation specifies a list of roles required to access a method. If the authenticated user does not possess at least one of the specified roles, access is denied.
`@Secured` :该注解指定了访问一个方法所需的角色列表。如果经过身份验证的用户不具备至少一个指定角色，则拒绝访问。

```java
    @Secured("ROLE_ADMIN")
    public void adminOnlyMethod() {
        // ...
    }
```
- `@PreAuthorize`: This annotation allows for more complex authorization rules using Spring Expression Language (SpEL). It evaluates an expression before the method execution.
`@PreAuthorize` :该注解允许使用 Spring 表达式语言（SpEL）配置更复杂的授权规则。它在方法执行之前评估一个表达式。
```java
    @PreAuthorize("hasRole('ROLE_USER') and authentication.name == #username")
    public void userSpecificMethod(String username) {
        // ...
    }
```
- `@PostAuthorize`: Similar to @PreAuthorize, but the expression is evaluated after the method execution. This is useful for checking conditions related to the method's return value.
`@PostAuthorize` :与 @PreAuthorize 类似，但表达式在方法执行后进行评估。这对于检查与方法返回值相关的条件很有用

```java
    @PostAuthorize("returnObject.owner == authentication.name")
    public MyObject getMyObject(long id) {
        // ...
        return myObject;
    }
```
- `@PreFilter`: This annotation is used to filter collections passed as method parameters. The SpEL expression determines which elements to keep.
`@PreFilter` :该注解用于过滤作为方法参数传递的集合。SpEL 表达式决定保留哪些元素。
```java
    @PreFilter("filterObject.owner == authentication.name")
    public void processItems(List<Item> items) {
        // ...
    }
```
- `@PostFilter`: This annotation filters collections returned by a method. The SpEL expression determines which elements to include in the returned collection.
`@PostFilter` :该注解用于过滤方法返回的集合。SpEL 表达式决定返回集合中包含哪些元素。
```java
    @PostFilter("filterObject.status == 'ACTIVE'")
    public List<Product> getActiveProducts() {
        // ...
        return allProducts;
    }
```
- JSR-250 Annotations (if enabled): Spring Security also supports the standard JSR-250 annotations for authorization:
JSR-250 注解（如果启用）：Spring Security 还支持标准的 JSR-250 授权注解：
    - `@RolesAllowed`: Equivalent to @Secured, specifying roles.
    - `@RolesAllowed` :等同于 @Secured ，指定角色。
    - `@DenyAll`: Denies access to all users.
    - `@DenyAll` :拒绝所有用户访问。
    - `@PermitAll`: Permits access to all users (including unauthenticated).
    - `@PermitAll` :允许所有用户访问（包括未认证用户）。
These annotations provide powerful mechanisms for implementing robust and flexible security rules in Spring applications.
这些注解为 Spring 应用程序提供了强大的机制，用于实现健壮和灵活的安全规则。