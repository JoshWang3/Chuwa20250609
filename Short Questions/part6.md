# Database Fundamentals

## 13. SQL Transactions

### What is a Transaction?
A transaction is a group of database operations that are treated as a single unit. All operations either succeed together (commit) or fail together (rollback).

### ACID Properties
- **Atomicity**: All or nothing
- **Consistency**: Database remains valid
- **Isolation**: Transactions don't interfere with each other
- **Durability**: Changes are permanent once committed

### Spring Transaction Management
- Uses `@Transactional` annotation
- Automatically handles commit/rollback
- Creates transaction boundaries around methods
- Supports declarative transaction management

### Hibernate Transaction Management
- Works with Spring's transaction management
- Session is bound to transaction
- Automatic dirty checking within transaction
- First-level cache operates within transaction scope

### How They Work Together
Spring manages transaction lifecycle, Hibernate Session participates in Spring-managed transactions. When method with `@Transactional` starts, Spring begins transaction and Hibernate Session joins it.

## 14. Hibernate Caching

### First-Level Cache (Session Scope)
- **Scope**: Single Session/EntityManager
- **Lifecycle**: Lives with the Session
- **Automatic**: Always enabled, cannot be disabled
- **Purpose**: Ensures object identity within session
- **Location**: Memory within Session

### Second-Level Cache (Shared/Global Scope)
- **Scope**: SessionFactory level, shared across sessions
- **Lifecycle**: Lives with SessionFactory
- **Optional**: Must be explicitly enabled and configured
- **Purpose**: Improves performance across sessions
- **Location**: Shared memory or external cache provider

### Key Differences

| Aspect | First-Level Cache | Second-Level Cache |
|--------|------------------|-------------------|
| Scope | Session only | SessionFactory wide |
| Sharing | Not shared | Shared across sessions |
| Control | Always enabled | Must be configured |
| Performance | Prevents duplicate queries in session | Prevents database hits across sessions |
| Data Consistency | Guaranteed | Requires cache invalidation |

### When Each Cache Helps
- **First-Level**: When accessing same entity multiple times in one transaction
- **Second-Level**: When same entities are accessed frequently across different transactions