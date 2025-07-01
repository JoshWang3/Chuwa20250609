### part 1

## errors
    no DB created

## error 2
    department table created after school
    school table created after department 

## error 3 
    referntial integrity. no department 101 in db

## error 4 
    duplicate id 
    chagne id or update the row

## error 5
    key value is restricted

## error 6
    missing value 
    delete foreign key need delete its primary key first.


### part 2

## Joins 
    in the real life we usually don't use one table at the time but multiple tables. join keyword provide 
    a way to get desire data from multiple tables at once.
    The JOIN keyword in SQL allows us to combine data from multiple tables based on a related column between them.

    ✔ Without JOINs, each table is isolated. You cannot retrieve integrated information (e.g. student names with their department and school) efficiently.
    
    ✔ Using JOIN:
    
    Retrieves meaningful combined results
    
    Enforces relational database design (normalize data into tables and join when needed)
    
    Avoids data duplication across tables

    INNER JOIN	Returns rows that have matching values in both tables.	Only rows with matches in both tables are returned.
    LEFT JOIN (LEFT OUTER JOIN)	Returns all rows from the left table, and matched rows from the right table. Unmatched right side returns NULLs.	Keeps all left table rows.
    RIGHT JOIN (RIGHT OUTER JOIN)	Returns all rows from the right table, and matched rows from the left table. Unmatched left side returns NULLs.	Keeps all right table rows.
    FULL JOIN (FULL OUTER JOIN)	Returns all rows when there is a match in either left or right table. Rows without matches return NULLs for the other table.	Combines LEFT JOIN and RIGHT JOIN results. Not directly supported in MySQL, simulated with UNION.

![Screenshot 2025-06-30 at 5.08.43 PM.png](Screenshot%202025-06-30%20at%205.08.43%E2%80%AFPM.png)

### part 3

    No. hibernate JDBC create the table. yes.
    
    
    
![Screenshot 2025-06-30 at 5.29.25 PM.png](Screenshot%202025-06-30%20at%205.29.25%E2%80%AFPM.png)

![Screenshot 2025-06-30 at 5.30.32 PM.png](Screenshot%202025-06-30%20at%205.30.32%E2%80%AFPM.png)

![Screenshot 2025-06-30 at 5.33.03 PM.png](Screenshot%202025-06-30%20at%205.33.03%E2%80%AFPM.png)
    
    i did not set id the hibernate will generate the id
    @GeneratedValue(strategy = GenerationType.IDENTITY)