import java.util.*;
import java.util.stream.*;

class Blog {
    String title;
    List<String> tags;
    Blog(String title, List<String> tags) { this.title = title; this.tags = tags; }
}

public class BlogTagExtractor {
    public static void main(String[] args) {
        List<Blog> blogs = Arrays.asList(
                new Blog("Java Streams", Arrays.asList("java", "stream", "lambda")),
                new Blog("Python Guide", Arrays.asList("python", "data", "stream")),
                new Blog("Spring Boot", Arrays.asList("java", "spring", "backend"))
        );

        List<String> uniqueSortedTags = blogs.stream()
                .flatMap(b -> b.tags.stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("All unique sorted tags: " + uniqueSortedTags);
    }
}
