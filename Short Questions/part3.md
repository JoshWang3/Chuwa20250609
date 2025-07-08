# JPA and Hibernate Core Concepts

## 5. Technology Comparison

### JDBC
- Lowest level database access technology
- Write raw SQL statements
- Manual connection and result set management

### HikariCP
- Connection pool library
- Manages database connections for better performance
- Default connection pool in Spring Boot

### Hibernate
- ORM framework that maps objects to database tables
- Implements JPA specification
- Write code with objects instead of SQL

### Spring Data JPA
- Spring's wrapper around JPA
- Provides Repository interfaces for easy CRUD operations
- Uses Hibernate underneath

**Relationship:**
```
Spring Data JPA -> Hibernate -> JDBC -> HikariCP -> Database
```

## 6. JPA Relationships

### @OneToMany
One entity relates to many others.

```java
@Entity
public class User {
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;
}

@Entity
public class Order {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
```

### @ManyToOne
Many entities relate to one entity.

```java
@Entity
public class Order {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
```

### @ManyToMany
Many entities relate to many others.

```java
@Entity
public class Student {
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;
}

@Entity
public class Course {
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;
}
```

### Configuration Properties

**mappedBy**
- Indicates which side owns the relationship
- Only on the non-owning side
- Points to the property name on the owning side

**cascade**
- Defines operations that cascade to related entities
- Example: `cascade = CascadeType.ALL`

**fetch**
- Defines when related data is loaded
- `FetchType.LAZY` or `FetchType.EAGER`

## 7. Cascade Options

### CascadeType.ALL + orphanRemoval = true
```java
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Order> orders;
```
- Applies all cascade operations
- `orphanRemoval = true` deletes child entities when removed from parent

### Individual CascadeType Values

**PERSIST**
- When parent is saved, children are saved too
```java
@OneToMany(cascade = CascadeType.PERSIST)
```

**MERGE**
- When parent is updated, children are updated too
```java
@OneToMany(cascade = CascadeType.MERGE)
```

**REMOVE**
- When parent is deleted, children are deleted too
```java
@OneToMany(cascade = CascadeType.REMOVE)
```

**DETACH**
- When parent is detached from persistence context, children are detached too
```java
@OneToMany(cascade = CascadeType.DETACH)
```

**REFRESH**
- When parent is refreshed, children are refreshed too
```java
@OneToMany(cascade = CascadeType.REFRESH)
```

## 8. Fetch Types

### FetchType.LAZY
- Data is loaded only when accessed
- Better performance for large datasets
- Default for @OneToMany and @ManyToMany

```java
@OneToMany(fetch = FetchType.LAZY)
private List<Order> orders; // Orders loaded only when orders.get() is called
```

**When to use:**
- Large collections
- Data not always needed
- Performance is important

### FetchType.EAGER
- Data is loaded immediately with parent entity
- Can cause performance issues
- Default for @ManyToOne and @OneToOne

```java
@ManyToOne(fetch = FetchType.EAGER)
private User user; // User loaded immediately with Order
```

**When to use:**
- Small datasets
- Related data always needed
- Avoiding LazyInitializationException

### Best Practices
- Use LAZY by default
- Use EAGER only when necessary
- Consider using @EntityGraph for complex scenarios
- Be careful with N+1 query problems
