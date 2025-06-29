CREATE SCHEMA `chuwa_hw8` DEFAULT CHARACTER SET utf8 ;
USE chuwa_hw8;

-- [1] Create School Schema FIRST (no dependencies) --
CREATE TABLE school (
    school_id INT PRIMARY KEY,
    school_name VARCHAR(100) NOT NULL,
    city VARCHAR(50),
    established_year INT
);

-- [2] Create Department Schema (depends on school) --
CREATE TABLE department (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL,
    building VARCHAR(50),
    school_id INT,
    FOREIGN KEY (school_id) REFERENCES school(school_id)
);

-- [3] Create Student Schema (depends on department) --
CREATE TABLE student (
     student_id INT PRIMARY KEY,
     student_name VARCHAR(100) NOT NULL,
     gender ENUM('Male', 'Female') NOT NULL,
     birth_date DATE,
     dept_id INT,
     FOREIGN KEY (dept_id) REFERENCES department(dept_id) ON DELETE CASCADE
);

-- [4] Insert School Records (must come before department inserts) --
INSERT INTO school (school_id, school_name, city, established_year)
VALUES 
(1, 'Engineering School', 'Seattle', 1990),
(2, 'Law School', 'Boston', 1985);

-- [5] Insert Department Records (school_id exists) --
INSERT INTO department (dept_id, dept_name, building, school_id)
VALUES 
(101, 'Computer Science', 'Information Hall', 1),
(102, 'Electrical Engineering', 'Main Building A', 1),
(201, 'Law', 'Law School Building', 2),
(202, 'Physics', 'Science Block', 1);  -- Fixed: Changed from duplicate 102 to new 202

-- [6] Insert Student Records (dept_id exists, valid gender values) --
INSERT INTO student (student_id, student_name, gender, birth_date, dept_id)
VALUES 
(1001, 'Dawen Wei', 'Male', '2001-05-01', 101),
(1002, 'Ziwei Zhang', 'Female', '2002-08-12', 102),
(1003, 'Lang Wang', 'Female', '2000-11-21', 201),
(1004, 'Tyler A.', 'Female', '2002-08-12', 202),  -- Fixed: dept_id 666 → valid 202
(1005, 'Nayina', 'Female', '2001-09-01', 101);    -- Fixed: added full data & valid gender


-- [7] Delete a department
DELETE FROM department WHERE dept_id = 101;


-- [8] Join all three tables --
SELECT 
    s.student_name,
    d.dept_name,
    sc.school_name
FROM student s
JOIN department d ON s.dept_id = d.dept_id
JOIN school sc ON d.school_id = sc.school_id;
