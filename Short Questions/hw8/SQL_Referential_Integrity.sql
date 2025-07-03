-- Create Student Schema --
-- Create and select the database first. --
CREATE DATABASE IF NOT EXISTS Chuwa; 
USE Chuwa;

DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS school;

-- Create School Schema --
-- The 'school' table is created first as it has no dependencies and is referenced by 'department' table. 
CREATE TABLE school (
    school_id INT PRIMARY KEY,
    school_name VARCHAR(100) NOT NULL,
    city VARCHAR(50),
    established_year INT
);

-- Insert data into the 'school' table.
INSERT INTO school (school_id, school_name, city, established_year)
VALUES
(1, 'University of San Jose', 'San Jose', 1950),
(2, 'College of Sunnyvale', 'Sunnyvale', 1988);

-- The 'department' table is created after 'school' because it contains a
-- foreign key that references the 'school' table (school_id).
-- Create Department Schema --
CREATE TABLE department (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL,
    building VARCHAR(50),
    school_id INT,
    FOREIGN KEY (school_id) REFERENCES school(school_id)
);

-- The 'student' table is created last because it depends on the 'department' table.
-- It contains a foreign key that references the 'department' table (dept_id).
CREATE TABLE student (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    gender ENUM('Male', 'Female') NOT NULL,
    birth_date DATE,
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES department(dept_id)
);

-- Data for the 'department' table is inserted after the table is created.
-- Insert data to department table --
INSERT INTO department (dept_id, dept_name, building, school_id)
VALUES
(101, 'Computer Science', 'Information Hall', 1),
(102, 'Electrical Engineering', 'Main Building A', 1),
(201, 'Law', 'Law School Building', 2);

-- Insert to department
INSERT INTO department VALUES (103, 'Physics', 'Great Building', 2);

-- Data for the 'student' table is inserted after the 'department' and 'student' tables exist.
-- Insert Student Records --
INSERT INTO student (student_id, student_name, gender, birth_date, dept_id)
VALUES
(1001, 'John Zhang', 'Male', '2001-05-01', 101),
(1002, 'Lisa Li', 'Female', '2002-08-12', 102),
(1003, 'Kevin Wang', 'Male', '2000-11-21', 201),
(1004, 'Dawen Wei', 'Male', '2001-05-01', 101), 
(1005, 'Ziwei Zhang', 'Female', '2002-08-12', 102), 
(1006, 'Lang Wang', 'Female', '2000-11-21', 201),
(1007, 'Tyler A.', 'Female', '2002-08-12', 103);

-- Added necessary columns to insert the value. 
INSERT INTO student VALUES (1008, 'Nayina', 'Female', '1965-06-15', 103);

DELETE FROM student WHERE dept_id = 101;
DELETE FROM department WHERE dept_id = 101;

SELECT
    s.student_name,
    d.dept_name,
    sc.school_name
FROM student s
JOIN department d ON s.dept_id = d.dept_id
JOIN school sc ON d.school_id = sc.school_id;
