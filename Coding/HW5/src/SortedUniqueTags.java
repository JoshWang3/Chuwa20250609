import java.util.*;
import java.util.stream.Collectors;

class BlogPost {
    String title;
    List<String> tags;

    public BlogPost(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }
}

public class SortedUniqueTags {
    public static List<String> getSortedUniqueTags(List<BlogPost> posts) {
        return posts.stream()
                .flatMap(p -> p.getTags().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {
        List<BlogPost> posts = List.of(
                new BlogPost("Intro to Java", List.of("Java", "Programming")),
                new BlogPost("Advanced Java", List.of("Java", "Streams")),
                new BlogPost("Python Basics", List.of("Python", "Programming"))
        );
        System.out.println(getSortedUniqueTags(posts));
    }
}
