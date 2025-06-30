import java.util.*;
import java.util.stream.Collectors;

class Blog {
    private final int blogId;
    private final List<String> tags;

    public Blog(int blogId, List<String> tags) {
        this.blogId = blogId;
        this.tags = tags;
    }

    public int getBlogId(){
        return this.blogId;
    }

    public List<String> getTags() {
        return this.tags;
    }
}

public class BlogPost {
    public static void main(String[] args) {
        List<Blog> posts = List.of(
                new Blog(1, Arrays.asList("Java", "Programming", "Basics")),
                new Blog(2, Arrays.asList("Java", "Spring", "Backend")),
                new Blog(3, Arrays.asList("Python", "Data Science", "Machine Learning")),
                new Blog(4, Arrays.asList("JavaScript", "Frontend", "React")),
                new Blog(5, Arrays.asList("Java", "Advanced", "Concurrency"))
        );

        List<String> uniqueTags = posts.stream()
                .flatMap(post -> post.getTags().stream())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(uniqueTags);
    }

}
