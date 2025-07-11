The Spring Data JPA @Query annotation lets you declare a custom data-access query directly on a repository method, instead of relying on query-derivation from method names.

It is used before JPQL / Native SQL queries as annotations.

```
@Query("SELECT p FROM Post p WHERE p.author = :author")
List<Post> findByAuthor(@Param("author") String author);
```

```
@Query(value = "SELECT * FROM posts WHERE author = :author", nativeQuery = true)
List<Post> findByAuthorNative(@Param("author") String author);
```