package com.chuwa.assignment.utils;

import com.chuwa.assignment.pojos.BlogPost;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GetUniqueTags {
    // Get a sorted list of all unique tags from a list of blog posts (Blog to Tags is 1-to-many relationship)
    public static void main(String[] args) {
        List<BlogPost> posts = List.of(
                new BlogPost("Travel", List.of("attraction", "food", "news")),
                new BlogPost("Pet", List.of("food", "news", "products")),
                new BlogPost("Kids", List.of("food", "products", "playground")));

        List<String> uniqueTags = posts.stream()
                .flatMap(b -> b.getTags().stream())   // flatten to one big tag stream
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(uniqueTags);
    }
}
