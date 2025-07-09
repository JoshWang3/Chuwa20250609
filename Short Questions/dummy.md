### Short Questions

question5:
1. JDBC (Java Database Connectivity)
What it is: A low-level Java API for connecting and executing SQL statements against a database.

Role: The foundation of all database interaction in Java.

Responsibilities:

Open/close connections

Prepare SQL statements

Execute queries and updates

Handle ResultSet

It’s fast and flexible, but verbose and error-prone due to manual resource management.
2. HikariCP
What it is: A high-performance JDBC connection pool.

Role: Manages a pool of reusable database connections to improve efficiency and performance.

Responsibilities:

Controls how connections are reused

Reduces overhead of constantly opening/closing DB connections

Improves scalability and responsiveness

It's the default connection pool in Spring Boot since version 2.x.

3. Hibernate
What it is: A Java ORM (Object-Relational Mapping) framework.

Role: Maps Java classes to database tables and handles SQL generation.

Responsibilities:

Convert Java objects ↔ relational data

Automatically generate SQL (INSERT, UPDATE, DELETE)

Support for lazy loading, caching, and transactions

Hibernate uses JDBC under the hood to talk to the database.

4. Spring Data JPA
What it is: A Spring abstraction layer over JPA (Java Persistence API), typically using Hibernate as the provider.

Role: Simplifies CRUD repository creation, query methods, and transaction handling.

Responsibilities:

Automatically generate repository methods from method names (findByTitle, deleteById)

Reduce boilerplate

Integrate seamlessly with Spring Boot

Spring Data JPA uses Hibernate, which in turn uses JDBC, and JDBC uses HikariCP for connection pooling.



question6:
@OneToMany
Meaning:
One entity (parent) is related to many of another entity (child).

@ManyToOne
Meaning:
Many entities (children) are associated with one entity (parent).

@ManyToMany
Meaning:
Many entities of one type are related to many entities of another type.

question7:
cascade = CascadeType.ALL
Meaning:
This is a shorthand for applying all possible cascade operations

orphanRemoval = true
Meaning:
When you remove a child from the parent's collection, and orphanRemoval = true, the child entity is automatically deleted from the database.

PERSIST	When the parent is saved (EntityManager.persist()), the child is also saved.
MERGE	When the parent is updated/merged (EntityManager.merge()), the child is also merged.
REMOVE	When the parent is deleted (EntityManager.remove()), the child is also deleted.
DETACH	When the parent is detached from the persistence context, the child is also detached.
REFRESH	When the parent is refreshed (EntityManager.refresh()), the child is also refreshed.
ALL	All the above operations will cascade.

question 8:
FetchType.LAZY (Lazy Loading)
Meaning:
The related entity is not immediately loaded from the database. It is only fetched when accessed for the first time.

FetchType.EAGER (Eager Loading)
Meaning:
The related entity is loaded immediately along with the parent entity.

Use Case	Recommended FetchType
Collection relationships (@OneToMany, @ManyToMany)	LAZY (default)
Single entity relationships (@ManyToOne, @OneToOne)	Use EAGER only if always needed
You need performance and control	LAZY + manually fetch via JPQL/Criteria
You want convenience, not worrying about fetching manually	EAGER, but be careful with performance

question 9:
JPQL 是用来对 实体对象（Entity） 而不是数据库表进行查询的语言。它允许开发者以对象的视角操作数据库，支持复杂的查询逻辑（SELECT、JOIN、WHERE、GROUP BY、ORDER BY 等）。
JPQL 查询操作的不是表，而是 Java 类（Entity）
JPQL 查询的字段是实体的属性，而不是数据库列名

question10:
The @Query annotation in Spring Data JPA is used to define custom queries directly on repository interface methods. It can be used to write either:

JPQL (Java Persistence Query Language)

or native SQL queries.

qusetion11:

Feature	EntityManager	SessionFactory
Part of	JPA (standard API)	Hibernate (proprietary API)
Abstraction Level	Higher (vendor-neutral)	Lower (Hibernate-specific)
Produces	EntityManager	Session
Portability	High	Low
Thread Safety	EntityManagerFactory is thread-safe	SessionFactory is thread-safe
Use Case	Recommended for standard apps	Use only for direct Hibernate tuning

question12:
Both Session and SessionFactory are core components in Hibernate, but they serve very different purposes

question13:
Tool	Role in Transaction
Spring @Transactional	Manages transaction boundaries declaratively.
Hibernate Session	Starts, commits, or rolls back low-level transactions.
JDBC	Core connection to DB — ultimately performs COMMIT/ROLLBACK.
EntityManager	JPA abstraction that interacts with the persistence context and manages lifecycle.

question14:
First-Level Cache (Session Scope)
Description:
It is built into Hibernate.

Every Hibernate Session maintains a cache of the entities it loads.

If you load the same entity again in the same session, it is retrieved from the cache, not from the database.

Second-Level Cache (Shared Scope)
Description:
Optional and must be explicitly enabled.

It is a global cache shared across sessions (SessionFactory level).

Uses external providers: EhCache, Infinispan, Caffeine, etc