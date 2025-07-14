Model mappers are used to convert between different types of objects, typically between:
1. Entity objects (database layer)
2. DTOs (in APIs or service layers)
3. Form objects (form binding in web app)

Benefits:
1. Mapping separates internal data structures (entities) from external representations (DTOs). This ensures that changes in one layer don't affect others.
2. Mappers allow us to expose only necessary fields in the DTOs and ensure security.
3. DTOs decouple the domain model (entities) from controllers/services.

Scenario:
1. REST APIs: Convert entities (JPA) ↔ DTOs for request/response
2. Form submissions: Map form objects (input) to domain entities
3. Microservices: Map internal models to external API contracts
4. Data Aggregation: Map results from multiple entities into a combined DTO
5. Partial updates (PATCH): Use update-specific DTOs to control which fields are updatable
6. Security: Strip sensitive fields (e.g., passwords, tokens) from response models

Example:
1. Name mismatch:
```
public class UserEntity {
    private String username;
}

public class UserDTO {
    private String user_name;
}
```

2. Nested objects:
```
public class Order {
    private Customer customer;
}

public class OrderDTO {
    private String customerName;
}
```

3. Type mismatch:
```
public class Department {
    private List<Employee> employees;
}

public class DepartmentDTO {
    private Set<EmployeeDTO> employees;
}

```

ModelMapper handles type casting between different data types in the source and target objects using:
1. Built-in converters (int -> Integer)
2. Java reflection APIs
3. Custom converters 