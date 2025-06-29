### hw 8

### Part 1
```sql
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
                         FOREIGN KEY (dept_id) REFERENCES department(dept_id)
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
    (202, 'Physics', 'Science Block', 1); -- 🔧 Fixed: Changed from duplicate 102 to new 202

-- [6] Insert Student Records (dept_id exists, valid gender values) --
INSERT INTO student (student_id, student_name, gender, birth_date, dept_id)
VALUES
    (1001, 'Dawen Wei', 'Male', '2001-05-01', 101),
    (1002, 'Ziwei Zhang', 'Female', '2002-08-12', 102),
    (1003, 'Lang Wang', 'Female', '2000-11-21', 201),
    (1004, 'Tyler A.', 'Female', '2002-08-12', 202), -- 🔧 Fixed: dept_id 666 → valid 202
    (1005, 'Nayina', 'Female', '2001-09-01', 101);     -- 🔧 Fixed: added full data & valid gender

-- [7] Join all three tables --
SELECT
    s.student_name,
    d.dept_name,
    sc.school_name
FROM student s
         JOIN department d ON s.dept_id = d.dept_id
         JOIN school sc ON d.school_id = sc.school_id;


-- [8] DELETE FROM department WHERE dept_id = 101; --
-- Option 1: Use ON DELETE CASCADE to allow automatic deletion of students in that department
-- Recreate student table with cascading delete
DROP TABLE IF EXISTS student;
CREATE TABLE student (
     student_id INT PRIMARY KEY,
     student_name VARCHAR(100) NOT NULL,
     gender ENUM('Male', 'Female') NOT NULL,
     birth_date DATE,
     dept_id INT,
     FOREIGN KEY (dept_id) REFERENCES department(dept_id) ON DELETE CASCADE
);

-- Now, this delete will also remove any students in department 101
DELETE FROM department WHERE dept_id = 101;


-- Option 2: Manually delete students before deleting the department
-- Step 1: Delete all students referencing dept_id = 101
DELETE FROM student WHERE dept_id = 101;

-- Step 2: Now it's safe to delete the department
DELETE FROM department WHERE dept_id = 101;


-- Option 3: Prevent deletion if students exist (default FK behavior)
-- Check if any students exist in the department
SELECT * FROM student WHERE dept_id = 101;

-- If no rows are returned, then it's safe to delete:
DELETE FROM department WHERE dept_id = 101;


-- Use Option 1 if you want automatic cleanup.
-- Use Option 2 or 3 if you prefer manual or restricted deletion.
```

**In Summary, for SQL Referential Integrity:**
1. **Create tables in proper order:**
    - First: `school`
    - Then: `department` (which references `school`)
    - Then: `student` (which references `department`)

2. **Insert data only after referenced rows exist in parent tables.**
    - Insert into `school` first
    - Then into `department`
    - Finally into `student`

3. **Avoid duplicate primary key values.**
    - Ensure each `PRIMARY KEY` (e.g., `student_id`, `dept_id`) is unique.

4. **Validate `ENUM` values strictly.**
    - Only insert values defined in the enum (e.g., `'Male'`, `'Female'`)

5. **Define cascading behavior if needed.**
    - Use `ON DELETE CASCADE` or `ON DELETE SET NULL` when deleting parent rows should affect child rows.

6. **Avoid raw `INSERT INTO table VALUES (...)`**
    - Prefer `INSERT INTO table (column1, column2, ...) VALUES (...)`
    - Ensures clarity and avoids errors from column order mismatch.  



---
### Part 2







----
### Part 3

Create a postman request to generate a record in database:  
![Postman.png](images/part3/Postman.png)

![Database.png](images/part3/Database.png)

**Questions:**
1. Did you create the table `posts` in the database?   
   If not, who did it for you? Can I change this behavior?   
   (Hint: look at the `application.properties` file.)

**No**, I did not manually create the `posts` table in the database.  
Hibernate created the table automatically at runtime.  
This behavior is controlled by:
```properties
spring.jpa.hibernate.ddl-auto=update
```
in the `application.properties` file.

This line makes Hibernate compare the structure of our **Java entity classes** (e.g., `Post`) to the tables, columns, types, and constraints defined in the **database** (e.g., `redbook`), then update the **database** as needed to match the **Java entity classes**.

I can change this behavior by changing the value of `spring.jpa.hibernate.ddl-auto`:  

| Value        | Behavior                                                                 |
|--------------|--------------------------------------------------------------------------|
| `none`       | Do nothing (schema must be created manually)                             |
| `validate`   | Validate that the schema matches entities. Throws error if mismatch.     |
| `update`     | Update the schema to match the entities. (Default for development)       |
| `create`     | Drop and recreate schema on each app start                               |
| `create-drop`| Same as `create`, but also drops schema when app stops                   |

Recommendation:
- For **development**, `update` is fine.
- For **production**, use `validate` or `none` to avoid accidental schema changes.


---
2. Is the `id` in the database the same as what you set in your request?   
   Why does this happen? (Hint: search the annotations used in your code.)  

**No**, the `id` in the database is **auto-generated**, not taken from my request.  
This is due to the annotation `@GeneratedValue` in code:

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

- `@Id` → Marks the field as the **primary key**.

- `@GeneratedValue(...)` → Tells Hibernate to let the database generate the ID automatically.

- `GenerationType.IDENTITY` → Uses the database's auto-increment feature to generate unique IDs.

In Summary,
- In my HTTP request, the `id`  is ignored.
- The database auto-generates a new, unique ID for each inserted row.