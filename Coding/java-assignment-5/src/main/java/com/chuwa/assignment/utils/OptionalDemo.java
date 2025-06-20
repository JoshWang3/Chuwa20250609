package com.chuwa.assignment.utils;

import com.chuwa.assignment.pojos.BlogPost;
import com.chuwa.assignment.pojos.Product;

import java.util.*;                 // List, Arrays, Optional
import java.util.stream.Collectors;

public class OptionalDemo {

    public static void main(String[] args) {

        /* sample data --------------------------------------------------- */
        List<BlogPost> posts = Arrays.asList(
                new BlogPost("Java Streams", List.of("Java", "Streams")),
                new BlogPost("Missing-Tags Article", null)          // tags == null
        );

        Product iphone     = new Product("iPhone", "Electronics", 999);
        Product mysteryBox = new Product("Mystery Box", null, 19.99); // category == null
        List<Product> products = List.of(iphone, mysteryBox);

        /* extract ALL tags safely --------------------------------------- */
        List<String> allTags = posts.stream()
                .map(post -> Optional                 // JDK Optional
                        .ofNullable(post.getTags())           // Optional<List<String>>
                        .orElse(List.of()))                   // empty list if null
                .flatMap(List::stream)                        // flatten lists → Stream<String>
                .distinct()                                   // remove duplicates
                .collect(Collectors.toList());

        System.out.println("All tags = " + allTags);          // prints [Java, Streams]
    }
}

// Explanation:
// Optional provides a safe way to handle potential null values without needing explicit null checks.
// In this case, we safely access the tags list of each BlogPost, which may be null.
// Instead of writing if (post.getTags() != null) ..., we use Optional.ofNullable(post.getTags())
// and supply an empty list as a fallback using .orElse(List.of()).
// This avoids NullPointerException and makes the code cleaner.
