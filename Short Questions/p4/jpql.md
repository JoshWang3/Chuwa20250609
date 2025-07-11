JPQL is the object-oriented query language defined by the Java Persistence API that works with the entity classes and their mapped attributes.

e.g. ```SELECT p FROM Post p WHERE p.title LIKE CONCAT('%', :kw, '%')```

SQL operates directly on database tables and columns.

e.g. 
```
SELECT id, title, content
FROM post
WHERE title LIKE '%' || ? || '%';
```