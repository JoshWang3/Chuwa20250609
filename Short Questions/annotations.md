1. List all annotations you learned from class and homework, in a markdown file called annotations.md. This will serve as your reference.
    
   - @SpringBootApplication	Main entry point; enables auto-configuration, component scanning, and configuration.
   - @EnableTransactionManagement	Enables annotation-based transaction management (@Transactional).
   - @Entity	Marks a class as a JPA entity.
   - @Table(name = "table_name")Maps the entity to a specific table. Optional if the class name matches the table name.
   - @Id	Marks the primary key.
   - @GeneratedValue	Specifies generation strategy for primary keys (e.g., AUTO, IDENTITY).
   - @ManyToOne, @OneToMany, @OneToOne, @ManyToMany	Define entity relationships.
   - @JoinColumn	Specifies the foreign key column.
   - @MappedBy	Used on the inverse side of a relationship.
   - @Transient	Field is not persisted to the database.
   - @Repository	Indicates that the class is a Spring repository (optional if extending JpaRepository).
   - @Query	Defines custom JPQL or native queries.
   - @Modifying	Used with @Query to indicate a write operation (like UPDATE or DELETE).
   - @Transactional	Declares transactional boundaries (can be used on methods or classes).