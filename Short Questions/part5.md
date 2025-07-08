# Hibernate and Entity Management

## 11. EntityManager vs SessionFactory

### EntityManager
- JPA standard interface
- Higher level abstraction
- Used in Spring Boot applications
- Database-independent

### SessionFactory
- Hibernate-specific interface
- Lower level, direct Hibernate access
- Provides more Hibernate features
- Factory that creates Session objects

### Key Differences

| Aspect | EntityManager | SessionFactory |
|--------|---------------|----------------|
| Standard | JPA (portable) | Hibernate only |
| Abstraction | Higher level | Lower level |
| Features | Standard JPA | Full Hibernate |
| Spring Integration | Default choice | Manual setup |

### Relationship
EntityManager internally uses Hibernate Session. SessionFactory creates Session objects that EntityManager wraps.

*Note: Screenshots from codebase would show EntityManager injection in Spring services and SessionFactory configuration in Hibernate setup.*

## 12. SessionFactory vs Session

### SessionFactory
- **Purpose**: Factory that creates Session instances
- **Lifecycle**: One per application, long-lived
- **Thread Safety**: Thread-safe, can be shared
- **Cost**: Expensive to create, heavy object
- **Role**: Holds configuration and metadata

### Session
- **Purpose**: Performs actual database operations
- **Lifecycle**: One per request/transaction, short-lived
- **Thread Safety**: Not thread-safe, cannot be shared
- **Cost**: Cheap to create, lightweight
- **Role**: First-level cache and persistence context

### Summary
SessionFactory is the factory that creates Session objects. SessionFactory is created once and reused, while Session objects are created for each database operation and then closed.