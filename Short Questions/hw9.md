# Short Questions - HW9

## Questions

### 1. Why is it better to use a custom exception class (e.g. ResourceNotFoundException) in your application?

Custom exception classes provide several benefits:

**Code clarity**: A `ResourceNotFoundException` immediately tells developers what went wrong, unlike generic `Exception` classes that require reading error messages.

**Precise error handling**: You can handle different custom exceptions differently. For example, `ResourceNotFoundException` returns 404, while `ValidationException` returns 400.

**Better maintenance**: When you need to change how a specific exception is handled, you only need to find places where that custom exception is thrown, rather than searching through generic exceptions.

### 2. Explain how @Table, @Column, and @Id work. What is the default behavior if you don't use them?

**@Table**: Maps an entity class to a database table. Without it, JPA uses the class name as the table name (e.g., `User` class maps to `user` table).

**@Column**: Maps a field to a database column. Without it, JPA uses the field name as the column name, converting camelCase to snake_case (e.g., `firstName` becomes `first_name`).

**@Id**: Marks the primary key field. This is required - without it, JPA throws an error at startup because it doesn't know which field is the primary key.

### 3. What happens if you don't annotate a class with @Entity, but still try to save it with JPA?

JPA won't recognize the class as an entity and will throw an exception like "Unknown entity" when you try to save it using EntityManager or Repository. The `@Entity` annotation is required for JPA to know which classes are database entities.

### 4. What happens if we forget to annotate a controller with @RestController and only use @RequestMapping?

Without `@RestController`, the method's return value won't be automatically serialized to JSON. `@RestController` combines `@Controller` and `@ResponseBody`. Without it, Spring treats the return value as a view name and tries to find a corresponding view template, likely resulting in a 404 error.

### 5. What is the default naming strategy of JPA for tables and columns when no explicit name is given?

**Table names**: Class name converted to lowercase (e.g., `User` → `user`, `UserProfile` → `userprofile`).

**Column names**: Field names converted from camelCase to snake_case (e.g., `firstName` → `first_name`, `userId` → `user_id`).

Note: This strategy may vary depending on the JPA implementation or configuration.

### 6. How does @PathVariable differ from @RequestParam?

**@PathVariable**: Extracts parameters from the URL path (e.g., `/users/123` where `123` is the path variable). These parameters are required - missing them results in 404.

**@RequestParam**: Extracts parameters from query strings (e.g., `/users?name=john&age=25`). These are required by default but can be made optional with `required=false`.

In short: PathVariable for URL path parameters, RequestParam for query string parameters.
