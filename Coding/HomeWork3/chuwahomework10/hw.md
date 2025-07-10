### 1 spring annotation cheatsheet
    @Repostory: 



### 2 set up and run 
    GET http://localhost:8080/api/v1/posts/jpql

    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Tue, 08 Jul 2025 22:59:45 GMT
    
    [
    {
    "id": 1,
    "title": "My First Post",
    "description": "homework",
    "content": "This is a sample post content."
    },
    {
    "id": 2,
    "title": "My Second Post",
    "description": "homework",
    "content": "This is a second sample post content."
    },
    {
    "id": 3,
    "title": "My third Post",
    "description": "homework",
    "content": "This is my third sample post content."
    },
    {
    "id": 4,
    "title": "Is Richard Richy?",
    "description": "Richard is Richy?",
    "content": "This is a new post to discuess if Richard is Richy or not. Acctually Richard and Richy is the same person"
    },
    {
    "id": 5,
    "title": "Chuwa yyds?",
    "description": "Chuwa is a good ICC?",
    "content": "ICC mai jiucai, not sure if it is yyds or not"
    },
    {
    "id": 6,
    "title": "To Jiu Friends",
    "description": "A Poem to Jiucai Friends",
    "content": "Tong shi tian ya lun luo jiu, xiang jian he bi ceng xiang shi"
    },
    {
    "id": 7,
    "title": "happy july 4th",
    "description": "god bless america",
    "content": "a land of free and a home of the braves"
    }
    ]
    Response file saved.
    > 2025-07-08T155945.200.json
    
    Response code: 200; Time: 51ms (51 ms); Content length: 903 bytes (903 B)

retrieve
![Screenshot 2025-07-08 at 3.44.59 PM.png](Screenshot%202025-07-08%20at%203.44.59%E2%80%AFPM.png)

![Screenshot 2025-07-08 at 4.04.01 PM.png](Screenshot%202025-07-08%20at%204.04.01%E2%80%AFPM.png)

search
![Screenshot 2025-07-02 at 5.40.19 PM.png](Screenshot%202025-07-02%20at%205.40.19%E2%80%AFPM.png)

update
![Screenshot 2025-07-08 at 4.44.19 PM.png](Screenshot%202025-07-08%20at%204.44.19%E2%80%AFPM.png)

remove
![Screenshot 2025-07-08 at 5.04.06 PM.png](Screenshot%202025-07-08%20at%205.04.06%E2%80%AFPM.png)

### 3 ~ 4

    List<Post> findAllByTitleContains(String title);

    List<Post> findAllByContentContains(String keyword);

    List<Post> findAllByUserName(); // display compile syntax check

    @Query("select p.title from Post p where p.id % 2 = 0")
    List<String> getPostTitleOfEvenId();

    @Query(value = "select * from posts p order by p.update_date_time desc limit 1", nativeQuery = true)
    Post findLatestPost();


![Screenshot 2025-07-09 at 5.28.46 PM.png](Screenshot%202025-07-09%20at%205.28.46%E2%80%AFPM.png)

![Screenshot 2025-07-09 at 5.22.40 PM.png](Screenshot%202025-07-09%20at%205.22.40%E2%80%AFPM.png)

![Screenshot 2025-07-09 at 5.25.03 PM.png](Screenshot%202025-07-09%20at%205.25.03%E2%80%AFPM.png)

![Screenshot 2025-07-09 at 5.25.57 PM.png](Screenshot%202025-07-09%20at%205.25.57%E2%80%AFPM.png)


### 5 Spring Data JPA Hibernate HikariCP JDBC
    Spring Data JPA (repository abstraction)
          |
        JPA
          |
     Hibernate (JPA implementation)
          |
        JDBC
          |
    Database driver & DB
          ^
     HikariCP (connection pool)


### 6 JPA relationships 

## OneToMany
    @Entity
    public class User {
    
        @Id
        private Long id;
    
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Post> posts;
    
        // getters and setters
    }

    @Entity
    public class Post {
    
        @Id
        private Long id;
    
        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;
    
        // getters and setters
    }
    mappedBy: indicates the owning side field name in the other entity (Post.user).

    cascade: operations that cascade to child entities (e.g. ALL, PERSIST, REMOVE).
    
    fetch: loading strategy
    
    LAZY (default): loads when accessed.
    
    EAGER: loads immediately.

## ManyToOne
    @Entity
    public class Post {
    
        @Id
        private Long id;
    
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User user;
    
        // getters and setters
    }

## ManyToMany
    @Entity
    public class Student {
    
        @Id
        private Long id;
    
        @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
        )
        private List<Course> courses;
    
        // getters and setters
    }

    @Entity
    public class Course {
    
        @Id
        private Long id;
    
        @ManyToMany(mappedBy = "courses")
        private List<Student> students;
    
        // getters and setters
    }
    @JoinTable: defines join table name and join columns.


    mappedBy @OneToMany, @ManyToMany	Defines non-owning side to avoid redundant join tables.
    cascade	 All	                    Defines operations that cascade (e.g. save/delete children).
    fetch	 All                        Defines when related entities are loaded (LAZY or EAGER).


### 7. cascade option in JPA
    CascadeType	Description
    orphanRemoval = true Automatically deletes child entities (orphans) removed from a collection or dissociated from the parent.
    PERSIST	When the parent entity is persisted (entityManager.persist(parent)), persist child entities as well.
    MERGE	When the parent is merged (entityManager.merge(parent)), merge child entities too. Useful for updating detached entities.
    REMOVE	When the parent is removed (entityManager.remove(parent)), delete child entities as well.
    DETACH	When the parent is detached (entityManager.detach(parent)), detach child entities from the persistence context. They will no longer be managed.
    REFRESH	When the parent is refreshed (entityManager.refresh(parent)), refresh child entities from the database too.
    ALL	Applies all of the above cascade types. Equivalent to specifying each individually.

### 8 fetch type
    LAZY: load when access 
    EAGER: load immediately
    Default to LAZY for collections (@OneToMany, @ManyToMany) to avoid performance issues.
    Use EAGER only when necessary, especially for single-valued associations (@ManyToOne, @OneToOne) where you always require the related entity.

### 9. JPQL
    JPQL - java pesistence query language. it create a bridge to complie sql style query
    it can use java entity name instead of actual table name like sql.
    @Query("select p.title from Post p where p.id % 2 = 0")
    List<String> getPostTitleOfEvenId();

### 10. @Query annotation
    @Query annotation tells JPA what operation to run and give a way to use sql or sql like syntax.
    set natvieQuery = true.

### 11. EntityManager vs SessionFactory
    EntityManager
    API Type: JPA standard API
    
    Purpose: Manages persistence operations (CRUD, queries, transactions) for entities.
    
    Obtained from: EntityManagerFactory

    SessionFactory
    API Type: Hibernate native API
    
    Purpose: Builds Session instances for interacting with DB.
    
    Obtained from: Configuration object in Hibernate

    EntityManagerFactory (JPA)
    |
    |-- creates --> EntityManager (JPA)
                        |
                        |-- implemented by --> Session (Hibernate)
                        |
    SessionFactory (Hibernate native)
    |
    |-- creates --> Session (Hibernate)

![Screenshot 2025-07-09 at 7.45.56 PM.png](Screenshot%202025-07-09%20at%207.45.56%E2%80%AFPM.png)

![Screenshot 2025-07-09 at 7.46.20 PM.png](Screenshot%202025-07-09%20at%207.46.20%E2%80%AFPM.png)

### 12. SessionFactory vs Session
    
    SessionFactory: Heavyweight factory, created once, builds Session instances.
    Session: Lightweight, used for DB operations within a transaction scope.

    Aspect	Session	SessionFactory
    Type	Lightweight	Heavyweight
    Purpose	Represents a single unit of work. Used for CRUD and queries.	Factory for creating Session objects. Holds configuration and metadata.
    Lifecycle	Short-lived (per transaction or request).	Long-lived (singleton per app).
    Thread safety	Not thread-safe (each thread should use its own Session).	Thread-safe (can be shared across threads).
    Analogy	Like a Connection in JDBC.	Like a DataSource or connection pool in JDBC.

    SessionFactory ➔ creates ➔ Session


### 13 sql transaction 
    transaction is set of sql operation implements ACID. when it called the inside operations
    executes sequntially but if one failed all failed and roll back to last succesful step.
    Uses Spring Transaction Management (Declarative Transactions).

    You annotate methods with @Transactional.
    
    Spring opens a transaction before the method executes, commits if successful, or rolls back on exceptions.
    Uses its own Session and Transaction APIs directly if used without Spring.

    Begins transaction via session.beginTransaction() and commits via transaction.commit().


### 14. hibernate cache
    Caching improves performance by reducing database queries.
    first level - mandatory and associate with session scope
    second level - optional and share across sessions within the same sessionFactory

    nabled by default?	Yes	No (must configure)
    Scope	Per Session	Shared across Sessions (SessionFactory scope)
    Usage	Prevents duplicate queries within a session	Reduces DB load by caching entities across sessions
    Configuration	No setup needed	Requires cache provider setup (Ehcache, Redis, etc.)
    Cleared when?	When session is closed or cleared	Can persist beyond session lifecycles (until evicted or invalidated)


    
    
    
    

    



    

    



    
    
    