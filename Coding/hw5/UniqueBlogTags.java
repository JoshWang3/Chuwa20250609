import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class BlogPost {
    private String title;
    private List<String> tags;

    public BlogPost(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }
}

public class UniqueBlogTags {

    public static void main(String[] args) {
        List<BlogPost> posts = Arrays.asList(
                new BlogPost("Intro to Java", Arrays.asList("java", "programming", "beginner")),
                new BlogPost("Advanced Java Streams", Arrays.asList("java", "streams", "advanced")),
                new BlogPost("Database Basics", Arrays.asList("sql", "database", "beginner")),
                new BlogPost("Functional Programming in Java", Arrays.asList("java", "functional", "streams"))
        );

        List<String> uniqueSortedTags = posts.stream()
                .flatMap(post -> post.getTags().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("All unique tags, sorted alphabetically: " + uniqueSortedTags);
    }
}