import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class getUniqueTags {

    public static void main(String[] args) {
        List<Blog> blogs = Arrays.asList(
            new Blog("Post 1", Arrays.asList("Java", "Programming", "Streams")),
            new Blog("Post 2", Arrays.asList("Programming", "Tutorial", "Java")),
            new Blog("Post 3", Arrays.asList("Tutorial", "Streams", "Java"))
        );

        List<String> uniqueTags = blogs.stream().flatMap(blog -> blog.getTags().stream()).distinct().sorted().collect(Collectors.toList());
        
        System.out.println("Unique tags: " + uniqueTags);
    }
}
