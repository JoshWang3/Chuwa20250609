PostController.java

```
    @GetMapping("/jpql-title")
    public ResponseEntity<List<PostDto>> getByTitleJPQL(
            @RequestParam("title") String title) {
        return ResponseEntity.ok(postService.getPostsByTitleJPQL(title));
    }

    @GetMapping("/jpql-native")
    public ResponseEntity<List<PostDto>> getByDescriptionNative(
            @RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(postService.getPostsByDescriptionJPQL(keyword));
    }
```

PostRepository.java

```
    @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Post> findByTitleWithJPQL(@Param("title") String title);


    @Query(
            value = "SELECT * FROM posts WHERE description LIKE %:keyword%",
            nativeQuery = true
    )
    List<Post> findByDescription(@Param("keyword") String keyword);
```

PostService.java

```
    List<PostDto> getPostsByTitleJPQL(String title);
    List<PostDto> getPostsByDescriptionJPQL(String string);    
```

PostServiceImpl.java

```
    @Override
    public List<PostDto> getPostsByTitleJPQL(String title) {
        return postRepository
                .findByTitleWithJPQL(title)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByDescriptionJPQL(String keyword) {
        return postRepository
                .findByDescription(keyword)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
```