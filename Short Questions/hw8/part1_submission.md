# hw8 part 1 submission

## Table Creation Order Issue

The script created the student table before creating the department and school tables.

However, student has a foreign key constraint on department, and department references school.

As a result, when trying to create the student table, MySQL threw an error because the referenced table department did not yet exist.

## Invalid Insert Statements

### Inserting into student before creating department and school

```
INSERT INTO student (student_id, student_name, gender, birth_date, dept_id)
VALUES (1001, 'John Zhang', 'Male', '2001-05-01', 101);
```

The referenced dept_id (101) did not exist at this point because department table was not created yet.

### Duplicate Primary Keys

```
INSERT INTO student (student_id, student_name, gender, birth_date, dept_id)
VALUES (1002, 'Lisa Li', 'Female', '2002-08-12', 102),
(1002, 'Tyler A.', 'Female', '2002-08-12', 666);
```

Both records use student_id = 1002, violating the primary key constraint, resulting in insertion failure.

### Invalid Foreign Key References

```
INSERT INTO department VALUES (102, 'Physics', 888);
```

Tried to insert a department with school_id = 888, but school_id 888 does not exist in the school table, violating the foreign key constraint.

### Invalid Column Values

```
INSERT INTO student VALUES (1002, 'Nayina', 111);
```

The insert statement is missing required columns (gender, birth_date, dept_id) and tries to insert 111 where a gender ENUM value is expected, causing a datatype and column count mismatch error.

## Delete Operation Fails Due To Referential Integrity

```
DELETE FROM department WHERE dept_id = 101;
```

This deletion fails if any student still references dept_id = 101 unless ON DELETE CASCADE is defined, which was not in the original table definition.
