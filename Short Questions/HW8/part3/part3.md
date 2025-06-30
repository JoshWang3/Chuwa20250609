1. No, I did not create the table manually.
Hibernate (through Spring Boot) created the posts table automatically when the application started.
This behavior is controlled by the following setting in application.properties file.
We can change this behavior by modifying this line in application.properties: ```spring.jpa.hibernate.ddl-auto=update```
2. the id is not the same as the one in the request
this is because ```@GeneratedValue(strategy = GenerationType.IDENTITY)``` tells the database to auto-generate the ID


