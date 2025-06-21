package com.example.stream;

import java.util.*;
import java.util.stream.*;

class Blog {
    String title;
    List<String> tags;

    Blog(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }
}

public class Q3UniqueSortedTags {
    public static void main(String[] args) {
        List<Blog> blogs = List.of(
                new Blog("Post1", List.of("java", "stream", "backend")),
                new Blog("Post2", List.of("java", "spring", "api")),
                new Blog("Post3", List.of("cloud", "api"))
        );

        List<String> result = blogs.stream()
                .flatMap(blog -> blog.getTags().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Unique sorted tags:");
        System.out.println(result);
    }
}
