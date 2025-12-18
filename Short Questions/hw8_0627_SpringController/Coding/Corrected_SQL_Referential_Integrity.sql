DROP DATABASE test;
CREATE DATABASE test;
USE test;

DROP TABLE student;
DROP TABLE department;
DROP TABLE school;
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
    FOREIGN KEY (dept_id) REFERENCES department(dept_id) ON DELETE CASCADE
);


-- Insert to school
INSERT INTO school VALUES (1, 'Harvard University', 'Cambridge', 1636),(2, 'Harvard University', 'Williamsburg', 1693);


-- Insert data to department table --
INSERT INTO department (dept_id, dept_name, building, school_id)
VALUES 
(101, 'Computer Science', 'Information Hall', 1),
(102, 'Electrical Engineering', 'Main Building A', 1),
(201, 'Law', 'Law School Building', 2);


-- Insert Student Records --
INSERT INTO student (student_id, student_name, gender, birth_date, dept_id)
VALUES 
(1001, 'John Zhang', 'Male', '2001-05-01', 101),
(1002, 'Lisa Li', 'Female', '2002-08-12', 102),
(1003, 'Kevin Wang', 'Male', '2000-11-21', 201);


INSERT INTO department (dept_id, dept_name, building, school_id)
VALUES 
(666, 'Computer Science', 'Engineer School', 2);


-- Insert data to student table (override)--
INSERT INTO student (student_id, student_name, gender, birth_date, dept_id)
VALUES 
(1001, 'Dawen Wei', 'Male', '2001-05-01', 101),
(1002, 'Ziwei Zhang', 'Female', '2002-08-12', 102),
(1003, 'Lang Wang', 'Female', '2000-11-21', 201),
(1002, 'Tyler A.', 'Female', '2002-08-12', 666)
ON DUPLICATE KEY UPDATE
student_name = VALUES(student_name),
gender = VALUES(gender),
birth_date = VALUES(birth_date),
dept_id = VALUES(dept_id);



INSERT INTO school VALUES (888, 'Yale University', 'New Haven', 1701);



-- Insert to department
INSERT INTO department (dept_id, dept_name, school_id) VALUES (102, 'Physics', 888)
ON DUPLICATE KEY UPDATE
dept_name = VALUES(dept_name),
school_id = VALUES(school_id);

INSERT INTO department VALUES (111, 'Medicine', 'Biology building', 1);

-- Insert to student
INSERT INTO student (student_id, student_name, dept_id) VALUES (1002, 'Nayina', 111)
ON DUPLICATE KEY UPDATE
student_name = VALUES(student_name),
dept_id = VALUES(dept_id);


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

