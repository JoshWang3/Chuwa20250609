import java.util.*;
import java.util.stream.*;

public class BlogTagProcessor {
    public static void main(String[] args) {
        List<BlogPost> blogPosts = Arrays.asList(
                new BlogPost("Java Streams", Arrays.asList("Java", "Streams", "Functional")),
                new BlogPost("Spring Boot", Arrays.asList("Spring", "Java", "Backend")),
                new BlogPost("Docker Basics", Arrays.asList("DevOps", "Docker", "Backend"))
        );

        List<String> sortedUniqueTags = blogPosts.stream()
                .flatMap(blog -> blog.getTags().stream())  // Flatten all tag lists
                .distinct()                                         // Remove duplicates
                .sorted()                                           // Sort alphabetically
                .collect(Collectors.toList());                      // Collect to List

        System.out.println("Sorted Unique Tags: " + sortedUniqueTags);
    }
}