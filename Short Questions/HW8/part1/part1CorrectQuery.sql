DROP DATABASE IF EXISTS university;
CREATE DATABASE university;
USE university;

CREATE TABLE school (
    school_id INT PRIMARY KEY,
    school_name VARCHAR(100) NOT NULL,
    city VARCHAR(50),
    established_year INT
);

CREATE TABLE department (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL,
    building VARCHAR(50),
    school_id INT,
    FOREIGN KEY (school_id) REFERENCES school(school_id) 
);

CREATE TABLE student (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    gender ENUM('Male', 'Female') NOT NULL,
    birth_date DATE,
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES department(dept_id) ON DELETE CASCADE
);

-- Insert schools
INSERT INTO school VALUES 
(1, 'USC', 'Los Angeles', 1880),
(2, 'UCLA', 'Los Angeles', 1919);

-- Insert departments
INSERT INTO department VALUES 
(101, 'Computer Science', 'Information Hall', 1),
(102, 'Electrical Engineering', 'Main Building A', 1),
(201, 'Law', 'Law School Building', 2);

-- Insert students
INSERT INTO student VALUES 
(1001, 'John Zhang', 'Male', '2001-05-01', 101),
(1002, 'Lisa Li', 'Female', '2002-08-12', 102),
(1003, 'Kevin Wang', 'Male', '2000-11-21', 201);

-- Insert data to student table --
INSERT INTO student (student_id, student_name, gender, birth_date, dept_id)
VALUES 
(1004, 'Dawen Wei', 'Male', '2001-05-01', 101),
(1005, 'Ziwei Zhang', 'Female', '2002-08-12', 102),
(1006, 'Lang Wang', 'Female', '2000-11-21', 201),
(1007, 'Tyler A.', 'Female', '2002-08-12', 101);

-- Insert to department
INSERT INTO department VALUES (103, 'Physics', 'some location', 1);

-- Insert to student
INSERT INTO student VALUES (1008, 'Nayina', 'Female', NULL, NULL);

-- Delete a department
DELETE FROM department WHERE dept_id = 101;