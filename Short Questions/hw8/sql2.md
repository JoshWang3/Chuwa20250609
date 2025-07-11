Manual join: ![manual.png](manual.png)

Join: ![join.png](join.png)

Manual join2: ![manual2.png](manual2.png)
No JOIN clause and result will contain every student with every department with every school.

Join2: ![join2.png](join2.png)
Only students with s.dept_id = d.dept_id and d.school_id = sc.school_id; are returned.

JOIN explicitly sets the join rule with ON clause that prevents the risk of forgetting the WHERE clause when using implicit join.

Inner join: ![inner.png](inner.png)
Combines student and department table with s.dept_id = d.dept_id;

Left join: ![left.png](left.png)
All rows from left table with matching in the right table.

Right join: ![right.png](right.png)
All rows from right table with matching in the left table.

Full join: ![full.png](full.png)
All matching rows from left and right table (left UNION right)