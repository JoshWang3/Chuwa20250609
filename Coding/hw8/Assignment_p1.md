# -- Create Student Schema --
Failed. Failed to open the referenced table 'department' since this student table is the first table we create, and we do not have department table.

13:38:23	CREATE TABLE student (     student_id INT PRIMARY KEY,     student_name VARCHAR(100) NOT NULL,     gender ENUM('Male', 'Female') NOT NULL,     birth_date DATE,     dept_id INT,     FOREIGN KEY (dept_id) REFERENCES department(dept_id) )	Error Code: 1824. Failed to open the referenced table 'department'	0.0014 sec



# -- Insert Student Records --
Failed. We do not have the student table.

13:43:08	INSERT INTO student (student_id, student_name, gender, birth_date, dept_id) VALUES  (1001, 'John Zhang', 'Male', '2001-05-01', 101), (1002, 'Lisa Li', 'Female', '2002-08-12', 102), (1003, 'Kevin Wang', 'Male', '2000-11-21', 201)	Error Code: 1146. Table 'chuwa.student' doesn't exist	0.0020 sec



# -- Create School Schema --
Succeeded.



# -- Create Department Schema --
Succeeded.



# -- Insert data to student table --
Failed. Because the student table does not exist yet.

13:47:30	INSERT INTO student (student_id, student_name, gender, birth_date, dept_id) VALUES  (1001, 'Dawen Wei', 'Male', '2001-05-01', 101), (1002, 'Ziwei Zhang', 'Female', '2002-08-12', 102), (1003, 'Lang Wang', 'Female', '2000-11-21', 201), (1002, 'Tyler A.', 'Female', '2002-08-12', 666)	Error Code: 1146. Table 'chuwa.student' doesn't exist	0.0011 sec

After creating the student table, the insert still failed because some dept_id values (like 101) don’t exist in the department table. To make the insert work, all dept_ids must already be in the department table.

13:49:13	INSERT INTO student (student_id, student_name, gender, birth_date, dept_id) VALUES  (1001, 'Dawen Wei', 'Male', '2001-05-01', 101), (1002, 'Ziwei Zhang', 'Female', '2002-08-12', 102), (1003, 'Lang Wang', 'Female', '2000-11-21', 201), (1002, 'Tyler A.', 'Female', '2002-08-12', 666)	Error Code: 1452. Cannot add or update a child row: a foreign key constraint fails (`chuwa`.`student`, CONSTRAINT `student_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `department` (`dept_id`))	0.0035 sec


-- Join three tables
# -- Insert data to department table --
Failed. Because school_id (like 1 and 2) doesn’t exist yet in school table. Need to insert them first.

13:57:50	INSERT INTO department (dept_id, dept_name, building, school_id) VALUES  (101, 'Computer Science', 'Information Hall', 1), (102, 'Electrical Engineering', 'Main Building A', 1), (201, 'Law', 'Law School Building', 2)	Error Code: 1452. Cannot add or update a child row: a foreign key constraint fails (`chuwa`.`department`, CONSTRAINT `department_ibfk_1` FOREIGN KEY (`school_id`) REFERENCES `school` (`school_id`))	0.0040 sec



-- Insert to department
Failed. Because the number of values doesn’t match the number of columns. Table has 4 columns, but only 3 values were given.

14:00:08	INSERT INTO department VALUES (102, 'Physics', 888)	Error Code: 1136. Column count doesn't match value count at row 1	0.00067 sec



-- Insert to student
Failed. Because student table doesn't exist.

14:03:45	INSERT INTO student VALUES (1002, 'Nayina', 111)	Error Code: 1146. Table 'chuwa.student' doesn't exist	0.0011 sec



-- Delete a department
Succeeded.



-- Join three tables
Failed. Because student table doesn't exist.

14:07:15	SELECT      s.student_name,     d.dept_name,     sc.school_name FROM student s JOIN department d ON s.dept_id = d.dept_id JOIN school sc ON d.school_id = sc.school_id LIMIT 0, 1000	Error Code: 1146. Table 'chuwa.student' doesn't exist	0.0011 sec





# Below is the correct version of sequence:

USE information_schema;
DROP DATABASE IF EXISTS chuwa;
CREATE DATABASE chuwa;
USE chuwa;

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

INSERT INTO school (school_id, school_name, city, established_year)
VALUES
(1, 'NYU', 'NYC', 1900),
(2, 'BU', 'Boston', 1800);


-- Insert data to department table --
INSERT INTO department (dept_id, dept_name, building, school_id)
VALUES 
(101, 'Computer Science', 'Information Hall', 1),
(102, 'Electrical Engineering', 'Main Building A', 1),
(201, 'Law', 'Law School Building', 2);

INSERT INTO school (school_id, school_name, city, established_year)
VALUES (888, 'Unknown School', 'TBD', 2000);

-- Insert to department
INSERT INTO department VALUES (202, 'Physics', 'building xx', 888);

-- Insert Student Records --
INSERT INTO student (student_id, student_name, gender, birth_date, dept_id)
VALUES 
(1001, 'John Zhang', 'Male', '2001-05-01', 101),
(1002, 'Lisa Li', 'Female', '2002-08-12', 102),
(1003, 'Kevin Wang', 'Male', '2000-11-21', 201);

INSERT INTO school (school_id, school_name, city, established_year)
VALUES (666, 'Fallback School', 'Nowhere', 2025);

UPDATE department
SET dept_name = 'Physics', building = 'building xx', school_id = 666
WHERE dept_id = 202;

-- Insert data to student table --
INSERT INTO student (student_id, student_name, gender, birth_date, dept_id)
VALUES 
(1004, 'Dawen Wei', 'Male', '2001-05-01', 101),
(1005, 'Ziwei Zhang', 'Female', '2002-08-12', 102),
(1006, 'Lang Wang', 'Female', '2000-11-21', 201),
(1007, 'Tyler A.', 'Female', '2002-08-12', 202);

-- Insert to student
INSERT INTO student VALUES (1008, 'Nayina', 'Female', '2002-01-01', 101);

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