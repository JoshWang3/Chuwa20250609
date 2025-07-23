# hw10 submission

## Q1. Spring Annotation Cheatsheet: List all of the annotations you’ve learned from class and homework in a file named annotations.md.

### Answer:
#### General Stereotype Annotations
- `@Component`  
  Marks a class as a Spring component (generic stereotype).

- `@Service`  
  Marks a class as a service provider (specialization of `@Component` for business logic/service layer).

- `@Repository`  
  Marks a class as a DAO (Data Access Object); provides automatic exception translation for persistence operations.

- `@Controller`  
  Marks a class as a web controller (MVC Controller).

- `@RestController`  
  Combines `@Controller` and `@ResponseBody`; used for REST APIs to return data as JSON/XML directly.

#### Request Mapping Annotations
- `@RequestMapping`  
  Maps HTTP requests to handler methods at class or method level.

- `@GetMapping`  
  Shortcut for `@RequestMapping(method = RequestMethod.GET)`.

- `@PostMapping`  
  Shortcut for `@RequestMapping(method = RequestMethod.POST)`.

- `@PutMapping`  
  Shortcut for `@RequestMapping(method = RequestMethod.PUT)`.

- `@DeleteMapping`  
  Shortcut for `@RequestMapping(method = RequestMethod.DELETE)`.

#### Parameter Binding Annotations
- `@PathVariable`  
  Binds a method parameter to a URI template variable.

- `@RequestParam`  
  Binds a method parameter to a web request parameter.

- `@RequestBody`  
  Binds the HTTP request body to a method parameter.

- `@ResponseBody`  
  Indicates that the return value should be bound to the web response body.

#### Dependency Injection Annotations
- `@Autowired`  
  Injects bean dependencies by type. Used on constructors, fields, or setters.

- `@Qualifier`  
  Used with `@Autowired` to specify which bean to inject when multiple candidates exist.

- `@Primary`  
  Indicates that a bean should be given preference when multiple candidates are qualified to autowire.

- `@Resource`  
  Java annotation for dependency injection by name (alternative to `@Autowired`).

- `@Inject`  
  From javax.inject, similar to `@Autowired`.

#### Bean Definition Annotations
- `@Bean`  
  Indicates that a method produces a bean to be managed by Spring container (typically used in `@Configuration` classes).

- `@Configuration`  
  Indicates that the class declares one or more `@Bean` methods and may be processed by Spring container to generate bean definitions.

- `@ComponentScan`  
  Configures component scanning directives for Spring.

#### Spring Boot Annotations
- `@SpringBootApplication`  
  Combination of `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`; main entry point for Spring Boot applications.

- `@EnableAutoConfiguration`  
  Tells Spring Boot to start adding beans based on classpath settings and other configurations.

#### Transaction Management Annotations
- `@Transactional`  
  Indicates that the method or class should be executed within a transaction context.

- `@EnableTransactionManagement`  
  Enables Spring’s annotation-driven transaction management capability.

#### JPA/Hibernate Entity Annotations
- `@Entity`  
  Marks a class as a JPA entity mapped to a database table.

- `@Table`  
  Specifies the table name and unique constraints for the JPA entity.

- `@Id`  
  Specifies the primary key of an entity.

- `@GeneratedValue`  
  Defines generation strategy for primary key (e.g. `GenerationType.IDENTITY`).

- `@Column`  
  Specifies column details for the field (e.g. name, nullable).

- `@CreationTimestamp`  
  Hibernate annotation to auto-generate creation timestamp.

- `@UpdateTimestamp`  
  Hibernate annotation to auto-generate update timestamp.

#### JPA Relationship Annotations
- `@ManyToOne`  
  Defines many-to-one relationship between entities.

- `@OneToMany`  
  Defines one-to-many relationship between entities.

- `@ManyToMany`  
  Defines many-to-many relationship with a join table.

- `@JoinColumn`  
  Specifies foreign key column in an entity relationship.

- `@JoinTable`  
  Defines join table for many-to-many relationships.

#### JPA Query Annotations
- `@NamedQuery`  
  Defines static named JPQL query at entity level.

- `@Query`  
  Used in repository interface methods to define JPQL or native SQL queries.

- `@PersistenceContext`  
  Injects an EntityManager to manage JPA operations.

#### Bean Scope and Miscellaneous Annotations
- `@Scope`  
  Specifies the scope of a bean (singleton, prototype, request, session).

- `@ResponseStatus`  
  Marks a method or exception class with the status code and reason for the response.

- `@ControllerAdvice`  
  Allows global exception handling, binding, and model enhancements in controllers.

## Q2. Hands-on Project Work

### Answer:
Screenshot in Short Questions/hw10/Images

Compile-time syntax check:

If you write a wrong method name like:

```
List<Post> findByNotExistingField(String x);
```

You will get a compile-time error stating it cannot resolve property NotExistingField.

## Q3. Explain the differences and relationships among: Spring Data JPA, Hibernate, HikariCP, JDBC

### Answer:
#### JDBC (Java Database Connectivity)
What it is: The standard low-level Java API for connecting to relational databases.

Uses DriverManager, Connection, Statement, and ResultSet to execute SQL queries.

No ORM features: developers must write SQL manually and map results to Java objects themselves.

Role in stack: JDBC is the foundational technology. All higher-level frameworks (Hibernate, Spring Data JPA) eventually rely on JDBC to communicate with the database.

####  Hibernate
What it is: A Java-based ORM (Object-Relational Mapping) framework that simplifies database interactions by mapping Java classes to database tables.

Implements JPA specification (but can also be used standalone).

Provides advanced features like caching, lazy loading, HQL (Hibernate Query Language), and automatic SQL generation.

Uses JDBC under the hood to execute queries.

Role in stack: Hibernate abstracts JDBC. Instead of manually writing SQL and mapping results, developers work with Java entities, and Hibernate handles SQL generation and execution via JDBC.

####  Spring Data JPA
What it is: A Spring module that simplifies database access by providing a repository abstraction on top of JPA.

Uses JPA interfaces (e.g. JpaRepository) to reduce boilerplate code for CRUD operations.

By default, uses Hibernate as the JPA implementation provider.

Generates queries based on method names and allows custom JPQL or native queries with @Query.

Role in stack: Spring Data JPA uses Hibernate as the ORM provider, which in turn uses JDBC to execute SQL queries. It is the highest abstraction layer, making database operations extremely easy for developers.

####  HikariCP
What it is: A high-performance JDBC connection pooling library used by Spring Boot by default.

Key points: Manages a pool of database connections to reuse them efficiently instead of creating new ones for each request.

Provides fast, lightweight, and reliable connection pooling, outperforming other pools like Tomcat or C3P0.

Role in stack: HikariCP works alongside JDBC to manage database connections efficiently. When Spring Data JPA or Hibernate needs a connection, it requests one from HikariCP, which manages the physical connections to the database.

## Q4. Explain the following annotations with examples: @OneToMany @ManyToOne @ManyToMany

### Answer:
#### 1. @OneToMany
Meaning:
Defines a one-to-many relationship, where one entity is related to multiple instances of another entity.

```
@Entity
public class Post {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    private String title;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;
}
```

```
@Entity
public class Comment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
```

Key configuration properties:
mappedBy: Used on the inverse side of the relationship to indicate which field owns the relationship. In the example, mappedBy = "post" tells JPA that the Comment entity owns the relationship via its post field.

cascade: Defines what operations (persist, remove, merge, refresh, detach) should be cascaded to child entities.  E.g., CascadeType.ALL means when you save or delete a Post, all its Comments will also be saved or deleted.

fetch: Determines how data is loaded. FetchType.LAZY (default): loads comments only when accessed. FetchType.EAGER: loads comments immediately when Post is loaded.

#### 2. @ManyToOne
Meaning:
Defines a many-to-one relationship, where multiple instances of an entity relate to one instance of another entity.

```
@Entity
public class Comment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
```

Key configuration properties:
fetch: FetchType.EAGER (default): loads the referenced Post immediately with Comment. FetchType.LAZY: loads Post only when accessed.

cascade: Usually not set on @ManyToOne because you typically don’t cascade operations from child to parent.

#### 3. @ManyToMany
Meaning:
Defines a many-to-many relationship, where multiple instances of one entity are related to multiple instances of another entity.

```
@Entity
public class Student {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();
}
```

```
@Entity
public class Course {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    private String title;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
}
```

Key configuration properties:

mappedBy: Used on the inverse side to indicate which field owns the relationship.  In this example, Course has mappedBy = "courses" pointing to the courses field in Student.

cascade: Defines operations to cascade. For example, CascadeType.ALL saves associated entities automatically.

fetch: FetchType.LAZY (default): loads associated entities only when accessed. FetchType.EAGER: loads associated entities immediately.

@JoinTable: Defines the join table name and its join columns for mapping the many-to-many relationship.

## Q5. Explain cascade = CascadeType.ALL and orphanRemoval = true. Also describe all other CascadeType values

### Answer:
#### 1. cascade = CascadeType.ALL
   Meaning:
   CascadeType.ALL is a shortcut that applies all cascade operations to the related entity. It combines: PERSIST MERGE REMOVE REFRESH DETACH

```
@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
private List<Comment> comments;
```

If you save (persist) a Post, its comments are also saved.

If you merge (update) a Post, its comments are updated.

If you delete (remove) a Post, its comments are deleted.

If you refresh a Post, its comments are reloaded from DB.

If you detach a Post, its comments are detached from persistence context.

#### 2. orphanRemoval = true
   Meaning:
   When set to true, if an entity is removed from the collection (no longer referenced), it will be deleted from the database.

```
@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Comment> comments;
```

If you remove a Comment from Post.comments list, that comment will be deleted from the database.

Difference from cascade REMOVE:

Cascade REMOVE: deletes child when parent is deleted.

orphanRemoval: deletes child when it is removed from the collection, even if the parent is not deleted.

#### 3. Other CascadeType values
   CascadeType.PERSIST
   What it does:
   When you save (persist) the parent entity, the related child entity is also persisted automatically.

Example:
Saving a Post also saves its comments if CascadeType.PERSIST is used.

CascadeType.MERGE
What it does:
When you merge (update) the parent entity, the changes to related child entities are also merged.

Example:
Updating a Post will also update its comments.

CascadeType.REMOVE
What it does:
When you delete (remove) the parent entity, the related child entities are also deleted.

Example:
Deleting a Post deletes its comments.

CascadeType.DETACH
What it does:
When the parent entity is detached from the persistence context, the child entities are also detached.

Example:
Detaching a Post detaches its comments from the EntityManager, so they are no longer managed.

CascadeType.REFRESH
What it does:
When the parent entity is refreshed from the database, the related child entities are also refreshed.

Example:
Calling entityManager.refresh(post) also refreshes its comments from DB.

## Q6. Explain FetchType.LAZY and FetchType.EAGER, and describe when to use each.

### Answer:
#### 1. FetchType.LAZY
   Meaning:
   Data is loaded on demand (lazily).

When you load the parent entity, related child entities are not loaded immediately. They are fetched only when accessed.

```
@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
private List<Comment> comments;
```

When you load a Post, its comments are not fetched from the database immediately.

If you later call post.getComments(), then a separate SQL query runs to fetch the comments.

Advantages:

Performance efficient when you don't always need the related data.

Reduces initial query execution time and memory usage.

Disadvantages:

Causes LazyInitializationException if you try to access the data after the session is closed (e.g. outside of a transaction in Spring Boot).

#### 2. FetchType.EAGER
   Meaning:
   Data is loaded immediately (eagerly) along with the parent entity.

When you load the parent, child entities are fetched at the same time via a JOIN or separate select queries.

```
@ManyToOne(fetch = FetchType.EAGER)
private Post post;
```

When you load a Comment, its related Post is fetched immediately.

Advantages:

Easy to use when you always need related data.

Avoids LazyInitializationException since data is already loaded.

Disadvantages:

Can cause performance issues due to unnecessary loading of related data, especially if the relationship has a large number of records (e.g. many comments).

#### 3. When to use each?
LAZY: When you don’t always need the related data, or want to optimize performance by loading only when required. Recommended for collections (e.g. @OneToMany).

EAGER: When you always need the related data with the parent entity. Often used for single relationships like @ManyToOne or @OneToOne where the data is critical.

## Q7. Explain what JPQL is and how it differs from SQL. Include examples.

### Answer:
#### 1. What is JPQL?
   JPQL (Java Persistence Query Language): A query language for JPA (Java Persistence API).

Similar to SQL but queries Java entity objects instead of database tables directly.

Uses entity class names and field names, not table names or column names.

Provides an object-oriented approach to querying databases.

#### 2. What is SQL?
   SQL (Structured Query Language): The standard database query language.

Directly queries tables and columns in a relational database.

Uses table names and column names defined in the database schema.

No concept of Java entities or object relationships.

#### 3. Example Comparison
   Assume you have this entity:
   ```
   @Entity
   public class Post {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String title;

   private String content;
   }
   ```

   JPQL Example:
   ```
   @Query("SELECT p FROM Post p WHERE p.title = :title")
   List<Post> findByTitle(@Param("title") String title);
   ```

Post is the entity class name, not the table name.

p.title is the entity field, not the database column.

Equivalent SQL Example:
```
SELECT * FROM posts WHERE title = 'Spring Boot';
```

posts is the database table name.

title is the database column name.

## Q8. Explain what the @Query annotation does, where it is used, and how to use it for both JPQL and native queries.

### Answer:
####  1. What does @Query do?
Definition:

@Query is used to define custom queries directly on repository methods in Spring Data JPA.

Purpose:

Allows writing queries beyond JPA method naming conventions.

Supports JPQL and native SQL queries.

#### 2. Where is @Query used?
Used in repository interfaces, e.g. JpaRepository or CrudRepository.

```
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
@Query("SELECT p FROM Post p WHERE p.title = :title")
Post findPostByTitle(@Param("title") String title);
}
```

#### 3. Using @Query with JPQL
JPQL Example:

```
@Query("SELECT p FROM Post p WHERE p.title = :title")
Post findPostByTitle(@Param("title") String title);
```

Explanation:

The query uses entity class name Post instead of table name.

p.title refers to the entity field, not the database column.

#### 4. Using @Query with native SQL
Native Query Example:

```
@Query(value = "SELECT * FROM posts WHERE title = :title", nativeQuery = true)
Post findPostByTitleNative(@Param("title") String title);
```

Explanation:

Uses actual table and column names from the database.

Requires nativeQuery = true attribute to indicate it is a native SQL query.

#### 5. Why use @Query?
When queries are too complex for Spring Data JPA method naming conventions.

For performance-optimized native SQL queries.

When using database-specific functions or features not supported by JPQL.

## Q9. EntityManager vs SessionFactory: Compare EntityManager and SessionFactory. Include screenshots from the codebase to illustrate their relationship.

### Answer:
#### 1. What is EntityManager?
Definition: EntityManager is the main JPA interface used to interact with the persistence context.

Key points:

Provides APIs to insert, update, delete, and query entities.

Manages the lifecycle of entities within a persistence context.

Obtained via @PersistenceContext in Spring.

#### 2. What is SessionFactory?
Definition: SessionFactory is a Hibernate-specific interface used to create Session objects.

Key points:

SessionFactory is configured once per application and is thread-safe.

Session is similar to JPA’s EntityManager. It provides methods to interact with the database.

SessionFactory is part of Hibernate’s native API, not JPA.

#### 3. Relationship between EntityManager and SessionFactory
Integration:

When using Hibernate as the JPA provider, EntityManager is implemented internally by Hibernate’s Session.

SessionFactory creates Sessions, while JPA’s EntityManagerFactory creates EntityManagers.

## Q10. Explain the role of Session and how it differs from SessionFactory.

### Answer:
#### 1. What is SessionFactory?
Definition: SessionFactory is a Hibernate interface used to create Session objects.

Key Points:

Represents a heavyweight, thread-safe object.

Typically created once per application during startup.

Maintains the configuration details, connection pool, and caching strategies.

Provides openSession() or getCurrentSession() to obtain a Session.

#### 2. What is Session?
Definition: Session is a Hibernate interface that represents a single unit of work with the database.

Key Points:

Not thread-safe.

Provides methods for CRUD operations: save(), update(), delete(), get(), load().

Represents a connection with the database and manages the persistence context for the operation scope.

Equivalent to JPA’s EntityManager.

#### 3. Key Differences
*    Feature	SessionFactory	Session
*    Definition	Factory to create Session objects	Represents a unit of work with DB
*    Thread Safety	Thread-safe, shared across app	Not thread-safe, used per operation/thread
*    Lifecycle	Exists for entire application lifecycle	Short-lived, per transaction or request
*    Responsibility	Holds configuration and connection pool	Provides APIs for CRUD operations
*    Analogy	Like a car factory producing cars	Like a car used for driving

#### 4. Practical Usage
SessionFactory

Initialized once at application startup.

Heavy resource allocation (connection pools, caching strategies).

Session

Created per database transaction or user request.

Always closed after use to free resources.

## Q11. Explain what a transaction is in the context of relational databases, and how Spring/Hibernate manage transactions.

### Answer:
#### 1. What is a Transaction in Relational Databases?
Definition: A transaction is a sequence of one or more SQL operations executed as a single unit of work.

Key Properties: ACID

#### 2. How does Spring manage transactions?
Spring Transaction Management: Uses @Transactional annotation to demarcate transaction boundaries declaratively.

Handles commit or rollback automatically based on method execution.

#### 3. How does Hibernate manage transactions?
Hibernate Transaction Management:

Uses Session and Transaction objects for programmatic transaction management.

## Q12. Explain Hibernate’s caching mechanisms. Compare: First-Level Cache (session scope) Second-Level Cache (shared/global scope)

### Answer:
#### 1. What is caching in Hibernate?
Definition: Caching is a mechanism to reduce database access by storing frequently accessed data in memory, improving performance.

Types in Hibernate:

First-Level Cache

Second-Level Cache

#### 2. First-Level Cache
Scope: Session scope (per Hibernate Session).

Enabled by default, cannot be disabled.

How it works:

When you load an entity using session.get() or session.load(), Hibernate stores it in the session cache.

If you request the same entity again within the same session, Hibernate returns it from the cache, avoiding an additional SQL query.

#### 3. Second-Level Cache
Scope: SessionFactory scope (shared across multiple sessions).

Not enabled by default; requires explicit configuration and an external caching provider (e.g. Ehcache, Redis).

How it works:
Stores entity data beyond individual sessions, making it accessible across sessions in the same application.

Useful for frequently read entities that don’t change often.

#### 4. Comparison Table
*    Feature	First-Level Cache	Second-Level Cache
*    Scope	Session	SessionFactory (shared)
*    Enabled by default?	Yes	No
*    Shared across sessions?	No	Yes
*    Configuration needed?	No	Yes (needs external cache provider)
*    Use case	Avoid duplicate queries within a session	Improve performance for frequently read data across sessions
