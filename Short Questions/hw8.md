
## Part 1
1. order of table creation should be: school, department, student
2. department referenced school while school is empty
3. student_id duplicated
4. missing fields in the INSERT instructions
```sql
INSERT INTO department VALUES (102, 'Physics', 888);
INSERT INTO student VALUES (1002, 'Nayina', 111);
```
 5. referenced non-existent dept_id
```sql
(1002, 'Tyler A.', 'Female', '2002-08-12', 666);
```
6. violates foreign key restraint after deletion
```sql
DELETE  FROM department WHERE dept_id =  101;
```
 
## Part 2
![demo_1](demo_1.png)
![demo_2](demo_2.png)
Why need join: manual join creates cartesian product, which is unnecessary and chaotic. This is prevented by JOIN keyword.

inner join - records where the join condition is satisfied in both tables, 
left join - all records in left table and matching record in right table,
right join - all records in right table and matching record in left table, 
full join - all records from both table, matched when possible. 

## Part 3
![demo_3](demo_3.png)
![demo_4](demo_4.png)
1. I didn't create the table, Hibernate created it automatically: 
```java
spring.jpa.hibernate.ddl-auto=update
```
2. No.
```java
@Id  
@GeneratedValue(strategy = GenerationType.IDENTITY)  
private Long id;
``` 
Sets the primary key of the record, and Hibernate ignores any id sent in body. 