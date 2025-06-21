import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// 2(Optional Check). get a sorted list of all unique tags from a list of blog posts (Blog to Tags is 1-to-many relationship)
public class OptionalPrevention {
    public static void main(String[] args) {
        List<BlogOptional> blogs = Arrays.asList(
                new BlogOptional("A", Arrays.asList("python", "django", "react")),
                new BlogOptional("B", Arrays.asList("java", "aws")),
                new BlogOptional("c", Arrays.asList("python", "html", "c++")),
                new BlogOptional("d", null)
        );
        // .map()	Stream<List<String>>
        //.flatMap()	Stream<String>
        List<String> allUniqueTags = blogs.stream()
                .flatMap(blog -> Optional.ofNullable(blog.getTags())
                                        .orElse(Collections.emptyList())
                                        .stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("A sorted list of all unique tags: " + allUniqueTags);
    }
}

class BlogOptional {
    private String title;
    private List<String> tags;
    public BlogOptional(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }
    public List<String> getTags() {
        return tags;
    }
}