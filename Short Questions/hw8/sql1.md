1. Need to change the order of table creation. The department table has a foreign key (school_id) referencing school table and the student table has a foreign key (dept_id) referencing the department table. Correct order should be school -> department -> school for correct referencing.
2. There's no insertion for school table, and thus we cannot insert entries into the department table (foreign key reference needed) and subsequently we cannot insert into student table.
3. While inserting into student table, there are duplicate primary keys ((1001, 'Dawen Wei', 'Male', '2001-05-01', 101) and (1001, 'John Zhang', 'Male', '2001-05-01', 101)). The operation cannot be performed.
4. Same thing for department table ((102, 'Electrical Engineering', 'Main Building A', 1) and (102, 'Physics', 888)).
5. While inserting into student table, the record ((1007, 'Tyler A.', 'Female', '2002-08-12', 666);) cannot find reference in the dept table as there's no dept_id = 666.
6. While inserting into department table, the record ((103, 'Physics', 888)) cannot find reference in school table as there's no school_id = 888. And need to update insertion schema or add entry for building_name.
7. The record (1002, 'Nayina', 111) cannot be inserted as student table requires gender to be NOT NULL. Plus we cannot find reference for dept_id = 111. And need to update insertion schema or add entry for birth_date.
8. When performing deletion on the department table (DELETE FROM department WHERE dept_id = 101), we need to first delete all records in student table that has the same dept_id before performing actions on the department table to avoid reference issues.

Final SQL script:
```
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

INSERT INTO school VALUES
(1, 'UNC', 'Chapel Hill', 1789),
(2, 'Rice', 'Houston', 1912);

-- Insert data to department table --

INSERT INTO department (dept_id, dept_name, building, school_id)
VALUES
(101, 'Computer Science', 'Information Hall', 1),
(102, 'Electrical Engineering', 'Main Building A', 1),
(201, 'Law', 'Law School Building', 2);

-- Insert to department --

INSERT INTO department VALUES (103, 'Physics', 2);

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
(1007, 'Tyler A.', 'Female', '2002-08-12', 103);

-- Insert to student 

INSERT INTO student VALUES (1002, 'Nayina', 'Female', 103);

-- Delete all students with dept_id = 101

DELETE FROM student WHERE dept_id = 101;

-- Delete a department

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
