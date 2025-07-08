# JPQL and @Query Guide

## 9. JPQL Overview

### What is JPQL?
Java Persistence Query Language - a query language that works with JPA entities instead of database tables.

### JPQL vs SQL Differences

**SQL:**
- Uses table and column names
- Database-specific
```sql
SELECT * FROM users WHERE email = 'john@example.com'
```

**JPQL:**
- Uses entity and property names
- Database-independent
```java
SELECT u FROM User u WHERE u.email = 'john@example.com'
```

### Key Differences Examples

**Basic Query:**
```java
// SQL
SELECT * FROM users WHERE age > 18

// JPQL
SELECT u FROM User u WHERE u.age > 18
```

**Joins:**
```java
// SQL
SELECT u.* FROM users u JOIN orders o ON u.id = o.user_id

// JPQL  
SELECT u FROM User u JOIN u.orders o
```

**Parameters:**
```java
// SQL
SELECT * FROM users WHERE name = ?

// JPQL
SELECT u FROM User u WHERE u.name = :name
```

## 10. Using the @Query Annotation

### What @Query Does
Defines custom queries in Repository interfaces. Lets you write your own JPQL or native SQL instead of using method names.

### Where It's Used
In Repository interfaces:

```java
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);
}
```

### JPQL Queries

**Basic JPQL:**
```java
@Query("SELECT u FROM User u WHERE u.active = true")
List<User> findActiveUsers();
```

**With Parameters:**
```java
@Query("SELECT u FROM User u WHERE u.age > :minAge")
List<User> findUsersOlderThan(@Param("minAge") int age);
```

**Update Query:**
```java
@Modifying
@Query("UPDATE User u SET u.active = false WHERE u.id = :id")
void deactivateUser(@Param("id") Long id);
```

### Native SQL Queries

**Basic Native:**
```java
@Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
User findByEmailNative(@Param("email") String email);
```

**Complex Native:**
```java
@Query(value = "SELECT COUNT(*) FROM users WHERE created_date >= :date", nativeQuery = true)
int countUsersCreatedAfter(@Param("date") LocalDate date);
```

### Key Points
- Use `@Param` to map parameters
- Use `@Modifying` for UPDATE/DELETE operations
- Set `nativeQuery = true` for SQL instead of JPQL
- JPQL works with entities, SQL works with tables
