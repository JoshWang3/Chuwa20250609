import java.util.*;
import java.util.stream.*;

// 1.3. get a sorted list of all unique tags from a list of blog posts (Blog to Tags is 1-to-many relationship)
public class UniqueBlogTags {
    public static void main(String[] args) {
        List<Blog> blogs = Arrays.asList(
                new Blog("A", Arrays.asList("python", "django", "react")),
                new Blog("B", Arrays.asList("java", "aws")),
                new Blog("c", Arrays.asList("python", "html", "c++"))
        );
        // .map()	Stream<List<String>>
        //.flatMap()	Stream<String>
        List<String> allUniqueTags = blogs.stream()
                .flatMap(blog -> blog.getTags().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("A sorted list of all unique tags: " + allUniqueTags);
    }
}

class Blog {
    private String title;
    private List<String> tags;
    public Blog(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }
    public List<String> getTags() {
        return tags;
    }
}