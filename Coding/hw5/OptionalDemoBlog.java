package hw5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalDemoBlog {
    public static void postBlog(Blog blog){
        // Business logic: a blog should only be posted if it has at least one tag.
        // Optional.ofNullable: safely create an Optional even if the input is null
        // if the value is null, return optional.empty() which creates an empty Optional to prevent potential NullPointerException later
        Optional<List<String>> optionalTags = Optional.ofNullable(blog.getTags())
                .filter(tags -> !tags.isEmpty()); // filter() is to prevent empty list []
        // Using isPresent() to check if the Optional contains a value
        if (optionalTags.isPresent()) {
            System.out.println("Posting blog: " + blog.getTitle());
        } else {
            System.out.println("Cannot post blog: " + blog.getTitle() + " — tags missing.");
        }
    }

    public static void main(String[] args) {
        Blog blog1 = new Blog("Java 8 New Feature", Arrays.asList("Java", "Stream", "Lambda"));
        Blog blog2 = new Blog("Multi-threading in Java", null);

        postBlog(blog1);
        postBlog(blog2);
    }
}
