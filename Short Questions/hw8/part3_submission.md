# hw8 part 3 submission

Screenshot in Short Questions/hw8/Images/part3.png

## Q1. Did you create the table POSTS in the database? if not, who did it for you? Can I change this behavior?

### Answer:

I did NOT manually create the POSTS table. Spring Boot + Hibernate automatically created it for me.

In application.properties, the line:

```
spring.jpa.hibernate.ddl-auto=update
```

This setting tells Hibernate to: Check if the table exists. If not, create it or update its schema to match entity definitions.

I can change this behavior.

For example:

create – drop and recreate tables every startup.

validate – validate schema, throw error if mismatch.

none – do nothing to the schema.

## Q2. Is your id in the database same as what you set in your request? why does this happen?

### Answer:

My id in the database is NOT the same as what I set in my request.

In my entity class (Post.java), the id field is annotated with:

```
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@GeneratedValue(strategy = GenerationType.IDENTITY) tells the database to auto-generate the id (usually auto-increment).
```

Therefore, id is managed by the database upon insert.
