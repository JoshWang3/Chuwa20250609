### Short Questions

question1:
1. Clearer semantics (self-documenting code)
2. Better error handling at the controller layer
3. Improved debugging and logging
4. Supports fine-grained exception handling
5. Encapsulate additional metadata if needed
 6. Separation of concerns

question2:

@Table:
Specifies the name of the database table that the entity maps to.

Optional — if not used, the default table name is the class name (e.g., Post → post or Post).

Without:  Will map to a table named post (lowercase by default, depending on DB settings).
@Column
Maps the field to a specific column in the database.
Without:JPA will use the field name as column name (e.g., title) and apply default settings (nullable = true, etc.)

@Id
Marks the field as the primary key of the entity.

Must be present — every JPA entity must have a unique ID.\
Without: The class won’t be a valid JPA entity.

question3:
If a class is not annotated with @Entity, JPA will not recognize it as a persistent entity, and trying to save it will result in a runtime exception.
why @Entity is essential? 
Because @Entity tells JPA:
This class should be mapped to a database table
Scan and process this class during app startup
Register it in the persistence unit for transactions, CRUD, etc.

question4:
If you forget to annotate a controller with @RestController (or @Controller) and only use @RequestMapping, then Spring Boot will not register that class as a controller at all
The methods inside that class will not be called

question5:
By default, JPA uses the class and field names directly as the table and column names — with some behavior depending on the JPA provider
Spring Boot uses Hibernate as the default JPA provider, and Hibernate applies a physical naming strategy

question6:
 @PathVariable and @RequestParam are both used in Spring Boot to extract values from the incoming HTTP request — but they are used for different parts of the URL.
                 @PathVariable	                            @RequestParam
From where?	     Extracts data from the URL path	          Extracts data from the query string
Syntax	         /posts/{id}	                              /posts?id=123
Use case	       When the data is part of the URL itself	    When data is passed as key=value params
Required?	       Required by default	                      Optional by default
Example URL	     /posts/123	                                /posts?id=123

