
## Core JPA Entity Annotations

**@Entity** - Marks a class as a JPA entity that maps to a database table

**@Table** - Specifies the database table name and schema details for an entity

**@Id** - Designates a field as the primary key of an entity

**@Column** - Defines column mapping properties like name, length, nullable, unique constraints

**@GeneratedValue** - Specifies how primary key values are automatically generated (AUTO, IDENTITY, SEQUENCE, TABLE)

**@IdClass** - Specifies a composite primary key class for entities with multiple @Id fields

## Relationship Annotations

**@ManyToOne** - Defines a many-to-one relationship between entities

**@OneToMany** - Defines a one-to-many relationship between entities

**@OneToOne** - Defines a one-to-one relationship between entities

**@ManyToMany** - Defines a many-to-many relationship between entities

**@JoinColumn** - Specifies the foreign key column for entity relationships

**@JoinTable** - Defines the join table for many-to-many relationships

**@JoinColumns** - Groups multiple @JoinColumn annotations for composite foreign keys

## Query and Persistence Annotations

**@NamedQuery** - Defines a named JPQL query at the entity level

**@NamedQueries** - Groups multiple @NamedQuery annotations

**@Query** - Defines custom JPQL or native SQL queries in repository methods

**@Modifying** - Indicates that a @Query method performs insert, update, or delete operations

**@Param** - Binds method parameters to query parameters

**@PersistenceContext** - Injects an EntityManager into a field or method

**@PersistenceUnit** - Injects an EntityManagerFactory into a field or method

## Spring Data Repository Annotations

**@Repository** - Marks a class as a Spring Data repository component

**@NoRepositoryBean** - Prevents Spring from creating repository instances for intermediate repository interfaces

## Transaction Management Annotations

**@Transactional** - Defines transaction boundaries and properties for methods or classes

**@EnableTransactionManagement** - Enables Spring's annotation-driven transaction management

**@Rollback** - Controls rollback behavior in test methods

## Testing Annotations

**@Test** - Marks a method as a test method (JUnit)

**@DataJpaTest** - Configures Spring Boot test slice for JPA repositories

**@AutoConfigureTestDatabase** - Configures test database settings

**@Sql** - Executes SQL scripts before or after test methods

**@TestPropertySource** - Specifies property sources for test configuration

