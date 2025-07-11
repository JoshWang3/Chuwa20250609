A transaction in a relational database is a unit of work that bundles one or more SQL operations into a single, all-or-nothing action. It must satisfy the ACID properties.

Spring’s Transaction Management:
1. Spring Boot auto-configures a PlatformTransactionManager (for example, DataSourceTransactionManager for JDBC or JpaTransactionManager for JPA/Hibernate).
2. @Transactional