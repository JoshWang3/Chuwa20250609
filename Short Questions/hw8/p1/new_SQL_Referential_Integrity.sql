-- drop existing tables to avoid errors if re-running the script
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS school;

-- 1) Create parent table: school
CREATE TABLE school (
  school_id INT PRIMARY KEY,
  school_name VARCHAR(100) NOT NULL,
  city VARCHAR(50),
  established_year INT
);

-- 2) Create parent table: department (depends on school)
CREATE TABLE department (
  dept_id    INT PRIMARY KEY,
  dept_name  VARCHAR(100) NOT NULL,
  building   VARCHAR(50),
  school_id  INT,
  FOREIGN KEY (school_id) REFERENCES school(school_id)
);

-- 3) Create child table: student (depends on department)
CREATE TABLE student (
  student_id INT PRIMARY KEY,
  student_name VARCHAR(100) NOT NULL,
  gender ENUM('Male','Female') NOT NULL,
  birth_date DATE,
  dept_id INT,
  FOREIGN KEY (dept_id) REFERENCES department(dept_id)
);

-- 4) Insert data into school (parent table)
INSERT INTO school (school_id, school_name, city, established_year) VALUES
  (1, 'Harvard University', 'Cambridge', 1636),
  (2, 'Massachusetts Institute of Technology', 'Cambridge', 1861);

-- 5) Insert data into department (parent table)
INSERT INTO department (dept_id, dept_name, building, school_id) VALUES
  (101, 'Computer Science', 'Information Hall', 1),
  (102, 'Electrical Engineering','Main Building A', 1),
  (201, 'Law', 'Law School Building', 2);

-- 6) Insert data into student (child table)
--    Ensure dept_id values exist in department, remove duplicate PKs and invalid dept_ids
INSERT INTO student (student_id, student_name, gender, birth_date, dept_id) VALUES
  (1001, 'John Zhang', 'Male',   '2001-05-01', 101),
  (1002, 'Lisa Li',    'Female', '2002-08-12', 102),
  (1003, 'Kevin Wang', 'Male',   '2000-11-21', 201);
