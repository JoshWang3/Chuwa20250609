## Part1:
* Before we create a table, we need to create a database, and use that database
* Before we create the student table, there is a foreign key which is references to the department table, however the department table does not exist right now
* How to fix it: we need to create the department table first, however, department table also have the reference to the school table
* what we need to do is to create the school table at first, then create the department table, then student table
* also not only the create part. we also need to insert the data from school to department to student. Since if foregin key does not exist, there will error in inserting part.
* For insert part, since the student_id is primary key, there should be no duplicate value for the primary key. This happened in student table
* And for other insert, `INSERT INTO department VALUES (102, 'Physics', 888);` since there are four cols, there should be 4 cols, rather than 3 cols.
* and for delete the department, since the `dept_id = 101`, which be used in the other table, cannot be deleted
