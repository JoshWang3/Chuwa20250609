package Coding;
import java.util.*;
import java.util.stream.*;

class Blog {
    String title;
    List<String> tags;

    public Blog(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public List<String> getTags() { return tags; }
}

public class UniqueSortedTags {
    public static void main(String[] args) {
        List<Blog> blogs = Arrays.asList(
                new Blog("Java", Arrays.asList("code", "java", "stream")),
                new Blog("Python", Arrays.asList("code", "python", "ml")),
                new Blog("AI", Arrays.asList("ml", "ai", "deep-learning"))
        );

        List<String> tags = blogs.stream()
                .flatMap(blog -> blog.getTags().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(tags); // 示例输出：[ai, code, deep-learning, java, ml, python, stream]
    }
}
