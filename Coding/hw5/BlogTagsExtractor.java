package hw5;

import java.util.Arrays;
import java.util.List;

/**
 * Get a sorted list of all unique tags from a list of blog posts
 */
public class BlogTagsExtractor {
    public static void main(String[] args) {
        List<BlogPost>  blogPosts = Arrays.asList(
                new BlogPost("Java Streams", Arrays.asList("Java Streams", "Java 8", "Backend", "Java")),
                new BlogPost("Spring Boot Guide", Arrays.asList("Java", "Spring", "Backend")),
                new BlogPost("Frontend Tips", Arrays.asList("Javascript", "CSS", "HTML"))
        );

        List<String> uniqueSortedTags = blogPosts.stream()
                .flatMap(blogPost -> blogPost.getTags().stream())
                .distinct()
                .sorted()
                .toList();

        System.out.println("Sorted unique tags: " + uniqueSortedTags);
        // Sorted unique tags: [Backend, CSS, HTML, Java, Java 8, Java Streams, Javascript, Spring]
    }
}
