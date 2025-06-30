## Part 1

**1. Create Student Schema**
- Error occurred due to a failed reference to dept_id in the department table, as the table had not been created yet.

**2. Insert Student Records**
- error 2 Cannot add or update a child row: a foreign key constraint fails (`sql_practice`.`student`, CONSTRAINT `student_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `department` (`dept_id`))
- Still can not get refer to department id, need to insert data in department table first

**3. INSERT INTO department VALUES (102, 'Physics', 888)**
- Column count doesn't match value count at row 1
- It should have four columns data (dept_id, dept_name, building, school_id). The school_id is missing, violating the foreign key constraint.
- Plus, department table already have 102 department id, should update to new department id

**4. Insert data to student table INSERT INTO student (student_id, student_name, gender, birth_date, dept_id)**
VALUES 
(1001, 'Dawen Wei', 'Male', '2001-05-01', 101),
(1002, 'Ziwei Zhang', 'Female', '2002-08-12', 102),	
(1003, 'Lang Wang', 'Female', '2000-11-21', 201),
(1002, 'Tyler A.', 'Female', '2002-08-12', 666); **
- Duplicate primary key again, update to a unique one
- Cannot add or update a child row: a foreign key constraint fails  for Tyler A., missing dept_id references to department table

**5. Insert to student INSERT INTO student VALUES (1002, 'Nayina', 111);**
- Missing column value of birth_date and duplicate student_id (primary key should be unique). Also, missing column value for gender.
- No department id 111 in department table, should refer to other entries.


**6. Delete a department DELETE FROM department WHERE dept_id = 101;**
- > 1451 - Cannot delete or update a parent row: a foreign key constraint fails (`sql_practice`.`student`, CONSTRAINT `student_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `department` (`dept_id`))
- We can not delete department id 101 entry since it violate foreign key constraint, or we need to delete all data with downstream dependencies.

```mysql
-- Updated version --
-- Create School Schema --
CREATE TABLE school (
    school_id INT PRIMARY KEY,
    school_name VARCHAR(100) NOT NULL,
    city VARCHAR(50),
    established_year INT
);

-- Create Department Schema --
CREATE TABLE department (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL,
    building VARCHAR(50),
    school_id INT,
    FOREIGN KEY (school_id) REFERENCES school(school_id)
);

-- Create Student Schema --
CREATE TABLE student (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    gender ENUM('Male', 'Female') NOT NULL,
    birth_date DATE,
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES department(dept_id)
);

-- Insert data to school table --
INSERT INTO school (school_id, school_name) VALUES
(1, 'Engineering School'),
(2, 'Law School');

-- Insert data to department table --
INSERT INTO department (dept_id, dept_name, building, school_id)
VALUES 
(101, 'Computer Science', 'Information Hall', 1),
(102, 'Electrical Engineering', 'Main Building A', 1),
(201, 'Law', 'Law School Building', 2);


-- Insert to department
INSERT INTO department VALUES (103, 'Physics', 888, 1);

-- Insert Student Records --
INSERT INTO student (student_id, student_name, gender, birth_date, dept_id)
VALUES 
(1001, 'John Zhang', 'Male', '2001-05-01', 101),
(1002, 'Lisa Li', 'Female', '2002-08-12', 102),
(1003, 'Kevin Wang', 'Male', '2000-11-21', 201);

-- Insert data to student table --
INSERT INTO student (student_id, student_name, gender, birth_date, dept_id)
VALUES 
(1004, 'Dawen Wei', 'Male', '2001-05-01', 101),
(1005, 'Ziwei Zhang', 'Female', '2002-08-12', 102),	
(1006, 'Lang Wang', 'Female', '2000-11-21', 201),
(1007, 'Tyler A.', 'Female', '2002-08-12', 102);

-- Insert to student
INSERT INTO student VALUES (1008, 'Nayina', 'Female', '2002-08-12', 101);


-- Delete a department
DELETE FROM student WHERE dept_id = 101; -- remove foreign key constraints data
DELETE FROM department WHERE dept_id = 101;

-- Join three tables
SELECT 
    s.student_name,
    d.dept_name,
    sc.school_name
FROM student s
JOIN department d ON s.dept_id = d.dept_id
JOIN school sc ON d.school_id = sc.school_id;
```
![result] (./images/result_referential_integrity.png)

## Part 2
Please try attached SQL queries (with same data and schema setup as above), explain why we need join keyword, and compare inner join, left join, right join, and full join.

- Every student is matched with every department and every school, which leads to:
- **Many irrelevant combinations**
- **Inefficient result sets** especially with large datasets
Consider using proper `JOIN` conditions to avoid unnecessary data combinations.
- left join: Returns all students even if a student’s dept_id has no match, then dept_name is NULL.
- right join: Returns all departments, even if no students are assigned to them.
- outer join: Combines all results from LEFT and RIGHT JOIN.
- inner join: return student only have a dept_id in department table
![manualJoin] (./images/manual_join.png)
![withJoin] (./images/with_join.png)
![leftJoin] (./images/left_join.png)
![rightJoin] (./images/right_join.png)
![innerJoin] (./images/inner_join.png)
![fullJoin] (./images/full_join_with_union.png)

## Part 3
![r1] (./images/Q3_r1.png)
![r2] (./images/Q3_r2.png)
![r3] (./images/Q3_r3.png)
![r4] (./images/Q3_r4.png)
**1. Did you create the table POSTS in the database? if not, who did it for you? Can I change this behavior? (Hint: look at the application.properties file)**
If I didn’t manually create the `POSTS` table, it was likely auto-generated by **Spring Boot with Hibernate**, based on JPA entity classes. I can disable the behavior by setting application.properties file `spring.jpa.hibernate.ddl-auto = none` 

**2. Is your id in the database same as what you set in your request? why does this happen? (Hint: search the annotations used in your code)**

**No**, it is not. This is because of the JPA annotations on the `id` field in the entity class.

The `@GeneratedValue(strategy = GenerationType.IDENTITY)` annotation tells JPA to let the **database automatically generate the `id`** using its **auto-increment (identity) mechanism**.

Even if you manually set the `id` in your request, it will be ignored during insertion. The database will generate a new unique `id` when the entity is persisted.

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

