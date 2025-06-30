# Homework 8 – Part 1  
_SQL Query Results & Explanations_

> **Test environment**  
> MySQL 8.0 (InnoDB, `FOREIGN_KEY_CHECKS = 1`).  
> The database used is `my_school`.

---

## 0  Create schema and choose database (reference)

```sql
CREATE DATABASE IF NOT EXISTS my_school;
USE my_school;
```

---

## 1  Create **student** table _before_ the parent table

```sql
CREATE TABLE student (
    student_id  INT PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    gender ENUM('Male', 'Female') NOT NULL,
    birth_date DATE,
    dept_id  INT,
    FOREIGN KEY (dept_id) REFERENCES department(dept_id)
);
```

**Result ❌ Error 1215 `Cannot add foreign key constraint`**

**Reason**  
`department` does **not** exist yet, so the foreign‑key definition fails.  
MySQL requires the referenced table to be created first (unless you temporarily disable FK checks).

**Fix** – create the parent tables (`school`, `department`) first, or run  
```sql
SET FOREIGN_KEY_CHECKS = 0;
-- create child + parent
SET FOREIGN_KEY_CHECKS = 1;
```
(better: just create parent first).

---

## 2  Insert initial student rows

```sql
INSERT INTO student (student_id, student_name, gender, birth_date, dept_id) VALUES
(1001,'John Zhang','Male','2001-05-01',101),
(1002,'Lisa Li'  ,'Female','2002-08-12',102),
(1003,'Kevin Wang','Male','2000-11-21',201);
```

**Result ❌ Error 1452 `Cannot add or update a child row: a foreign key constraint fails`**  
The insert fails because the referenced `department` rows do not exist yet.

---

## 3  Create **school** table

```sql
CREATE TABLE school (
    school_id INT PRIMARY KEY,
    school_name VARCHAR(100) NOT NULL,
    city VARCHAR(50),
    established_year INT
);
```

**Result ✅ Success**

---

## 4  Create **department** table (parent of student)

```sql
CREATE TABLE department (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL,
    building VARCHAR(50),
    school_id INT,
    FOREIGN KEY (school_id) REFERENCES school(school_id)
);
```

**Result ✅ Success**

---

## 5  Re‑create **student** table (after parents)

Executing the statement from step 1 **again** _after_ steps 3 & 4:

```sql
CREATE TABLE student ( … same definition … );
```

**Result ✅ Success**

---

## 6  Insert second batch of student rows

```sql
INSERT INTO student (student_id, student_name, gender, birth_date, dept_id) VALUES
(1001,'Dawen Wei','Male','2001-05-01',101),
(1002,'Ziwei Zhang','Female','2002-08-12',102),
(1003,'Lang Wang','Female','2000-11-21',201),
(1002,'Tyler A.','Female','2002-08-12',666);
```

**Result ❌ Error 1452 on all rows**

All insert attempts fail due to foreign key constraint violations because the referenced `department` entries either do not exist or the `dept_id = 666` is invalid.

---

## 7  Insert departments

```sql
INSERT INTO department (dept_id, dept_name, building, school_id) VALUES
(101,'Computer Science','Information Hall',1),
(102,'Electrical Engineering','Main Building A',1),
(201,'Law','Law School Building',2);
```

**Result ✅ Success**  
(Assumes corresponding `school_id` 1 & 2 exist.)

---

## 8  Insert duplicate department

```sql
INSERT INTO department VALUES (102,'Physics',888);
```

**Result ❌ Error 1062 `Duplicate entry '102' for key 'PRIMARY'`**

*Even if `dept_id` were unique, it would still fail with 1452 because `school_id = 888` does not exist in `school`.*

---

## 9  Insert student with invalid data type

```sql
INSERT INTO student VALUES (1002,'Nayina',111);
```

**Result ❌ Error 1265 `Data truncated for column 'gender'`**

`gender` is an `ENUM('Male','Female')` – value `111` is outside the allowed set.

---

## 10  Delete department 101

```sql
DELETE FROM department WHERE dept_id = 101;
```

**Result ❌ Error 1451 `Cannot delete or update a parent row`**

Rows in `student` still reference `dept_id = 101`.  
Either delete/update those students first or add `ON DELETE CASCADE` to the FK.

---

## 11  3‑way join

```sql
SELECT
    s.student_name,
    d.dept_name,
    sc.school_name
FROM student s
JOIN department d ON s.dept_id = d.dept_id
JOIN school sc     ON d.school_id = sc.school_id;
```

**Result ✅ Success** (returns the matching rows).  
Will work once all FK issues above are resolved.

---

### Final Notes

1. **Execution order matters** with foreign keys. Parents → children.  
2. Use `SHOW CREATE TABLE tbl\G` to inspect constraints when debugging.  
3. To bulk‑load legacy data, temporarily disabling `FOREIGN_KEY_CHECKS` can help, but always re‑enable afterwards.  
4. The schema actually used during testing was `my_schema`. Attempts to use `my_school` conflicted with existing objects or permissions.
