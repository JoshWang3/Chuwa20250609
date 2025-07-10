# hw8 part 2 submission

## Why do we need the JOIN keyword?

The JOIN keyword explicitly specifies how tables are combined based on related columns (foreign keys).

Without JOIN, using a comma-separated list of tables results in a Cartesian product (every row from the first table is combined with every row from the second), which is inefficient and often produces meaningless results unless manually filtered with WHERE.

## Comparison of INNER JOIN, LEFT JOIN, RIGHT JOIN, and FULL JOIN

### INNER JOIN
Returns only rows with matching values in both tables.

Excludes unmatched rows from either table.

### LEFT JOIN (or LEFT OUTER JOIN)
Returns all rows from the left table (student), with matched rows from the right table (department).

If no match exists, NULLs are returned for right table columns.

### RIGHT JOIN (or RIGHT OUTER JOIN)
Returns all rows from the right table (department), with matched rows from the left table (student).

If no match exists, NULLs are returned for left table columns.

### FULL JOIN (FULL OUTER JOIN)
Returns all rows when there is a match in either left or right table.

If no match exists, NULLs are returned for columns from the table without a match.

MySQL does not support FULL JOIN directly; it is achieved using UNION of LEFT and RIGHT joins.
