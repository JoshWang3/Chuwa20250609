
import java.util.*;

public class Question1_3 {

    public static void main(String[] args) {
        List<Blog>  blogs = Arrays.asList(
                new Blog("A", Arrays.asList("b", "a", "c")),
                new Blog("B", Arrays.asList("a", "b", "c", "d", "e")),
                new Blog("C", Arrays.asList("a", "b")),
                new Blog("D", Arrays.asList("a", "b", "h")),
                new Blog("E", Arrays.asList("f", "g", "c")),
                new Blog("F", Arrays.asList("i", "j", "k")),
                new Blog("G", Arrays.asList("a", "b", "l"))
        );



        System.out.println(sortTags(blogs));

    }

    public static List<String> sortTags(List<Blog> blogs) {

        return blogs.stream()
                .flatMap(s->s.getTags().stream())
                .distinct()
                .sorted()
                .toList();
    }
}




class Blog{
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