
import java.util.*;

class Blog {
    private List<String> tags;

    Blog(List<String> tags) {
        this.tags = tags;
    }

    public Optional<List<String>> getTags() {
        return Optional.ofNullable(tags);
    }
}

public class OptionalDemo {
    public static void main(String[] args) {
        Blog blog = new Blog(null);  // tags 是 null

        List<String> safeTags = blog.getTags().orElse(List.of());

        System.out.println("Safe tags:");
        safeTags.forEach(System.out::println); // 不会报错
    }
}
