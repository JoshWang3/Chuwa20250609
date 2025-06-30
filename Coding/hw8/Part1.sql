-- Create School table first because Department references it
CREATE TABLE school (
    school_id INT PRIMARY KEY,
    school_name VARCHAR(100) NOT NULL,
    city VARCHAR(50),
    established_year INT
);

-- Insert School Records
INSERT INTO school (school_id, school_name, city, established_year)
VALUES 
(1, 'Engineering School', 'Beijing', 1990),
(2, 'Law School', 'Shanghai', 1985);

-- Create Department table next because Student references it
CREATE TABLE department (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL,
    building VARCHAR(50),
    school_id INT,
    FOREIGN KEY (school_id) REFERENCES school(school_id)
);

-- Insert data to department table
INSERT INTO department (dept_id, dept_name, building, school_id)
VALUES 
(101, 'Computer Science', 'Information Hall', 1),
(102, 'Electrical Engineering', 'Main Building A', 1),
(201, 'Law', 'Law School Building', 2);

-- Create Student table last because it references department
CREATE TABLE student (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    gender ENUM('Male', 'Female') NOT NULL,
    birth_date DATE,
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES department(dept_id)
);

-- Insert Student Records
INSERT INTO student (student_id, student_name, gender, birth_date, dept_id)
VALUES 
(1001, 'John Zhang', 'Male', '2001-05-01', 101),
(1002, 'Lisa Li', 'Female', '2002-08-12', 102),
(1003, 'Kevin Wang', 'Male', '2000-11-21', 201);

-- The following inserts had issues:
-- (a) Duplicate student_id values
-- (b) dept_id or school_id references that do not exist
-- They are commented out for your review

-- INSERT INTO department VALUES (102, 'Physics', 888); -- 888 school_id not exist
-- INSERT INTO student VALUES (1002, 'Nayina', 111); -- missing gender and birth_date columns and invalid dept_id

-- Delete a department (will fail if students still reference it unless you handle ON DELETE CASCADE)
-- DELETE FROM department WHERE dept_id = 101;

-- Join three tables
SELECT 
    s.student_name,
    d.dept_name,
    sc.school_name
FROM student s
JOIN department d ON s.dept_id = d.dept_id
JOIN school sc ON d.school_id = sc.school_id;
