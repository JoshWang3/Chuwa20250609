import java.util.*;
import java.util.stream.*;

class BlogPost {
    String title;
    List<String> tags;

    BlogPost(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public Optional<List<String>> getTags() {
        return Optional.ofNullable(tags);
    }

}

public class Q1UniqueTags {
    public static void main(String[] args) {
        List<BlogPost> posts = Arrays.asList(
                new BlogPost("Java 8", Arrays.asList("java", "streams", "lambda")),
                new BlogPost("Spring Boot", Arrays.asList("spring", "java", "backend")),
                new BlogPost("Frontend Trends", Arrays.asList("react", "frontend", "javascript")),
                new BlogPost("No Tags", null)
        );

        List<String> uniqueSortedTags = posts.stream()
                .flatMap(post -> post.getTags()
                        .orElse(Collections.emptyList())
                        .stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("All Tags: " + uniqueSortedTags);
    }
}
