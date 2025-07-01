# 06/30

## Part 1

1. `Department` table should be created before `Student`, because it is referenced in create `Student` table query.
2. There are two query blocks to insert data into `Student` table. However, they have the same `student_id` which violates the primary key constraint. 
    
    ```sql
    INSERT INTO student (student_id, student_name, gender, birth_date, dept_id)
    VALUES 
    (1001, 'John Zhang', 'Male', '2001-05-01', 101),
    (1002, 'Lisa Li', 'Female', '2002-08-12', 102),
    (1003, 'Kevin Wang', 'Male', '2000-11-21', 201);
    
    INSERT INTO student (student_id, student_name, gender, birth_date, dept_id)
    VALUES 
    (1001, 'Dawen Wei', 'Male', '2001-05-01', 101),
    (1002, 'Ziwei Zhang', 'Female', '2002-08-12', 102),
    (1003, 'Lang Wang', 'Female', '2000-11-21', 201),
    (1002, 'Tyler A.', 'Female', '2002-08-12', 666);
    #1001,1002,1003 are used multiple times. 
    ```
    
    Primary key should be unique. We can remove one section or give different `student_id`s.
    
3. It showed a foreign key constraint fails when inserting data to `Department` table.
    
    ```sql
    # school_id = 1 & 2 don't exist in the school table
    INSERT INTO department (dept_id, dept_name, building, school_id)
    VALUES 
    (101, 'Computer Science', 'Information Hall', 1),
    (102, 'Electrical Engineering', 'Main Building A', 1),
    (201, 'Law', 'Law School Building', 2);
    ```
    
    We should insert school_id = 1 & 2 before inserting into `Department` table.
    
4. Another error when inserting data into `Student` table.
    
    ```sql
    # The third attribution should be gender with enum values. 111 is not valid value. 
    INSERT INTO student VALUES (1002, 'Nayina', 111);
    ```
    
    We should change `111` to `Female`.
    
5. It violates the foreign key constraint when deleting `dept_id = 101` since there are students aligning with this department.
    
    ```sql
    DELETE FROM department WHERE dept_id = 101;
    ```
    

## Part 2

1. In normalized databases, data are split into multiple tables. `JOIN` allows to fetch related data across tables through foreign key. If we join tables manually, it may create duplicate data or the queries will be more complicated than using `JOIN`.
2. INNER JOIN shows data which must be shown in both tables. LEFT JOIN shows all data from left table `Student` and data from `Department` which match students. RIGHT JOIN shows all data from right table `Department` and data from `Student` which match department via dept_id.  FULL JOIN (with `UNION`) shows all data which are shown one of tables. 

## Part 3

1. Postman screenshot
    
    ![Screenshot 2025-06-30 at 5.25.27 PM.png](06%2030%20220b9ebb81cb8052b92bfb9cac1cfd6d/Screenshot_2025-06-30_at_5.25.27_PM.png)
    
    No, Spring Boot created it for me based on `entity/Post` class. Yes, we can change this behavior in `application.properties` a line:
    
    ```yaml
    spring.jpa.hibernate.ddl-auto=update
    ```
    
2. No. In `Post.java` , we defined the entity: 
    
    ```java
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    ```
    
    It means although I gave an `id` in POST request, JPA will ignore it and generate id automatically.