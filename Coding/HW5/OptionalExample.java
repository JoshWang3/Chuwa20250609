import java.util.*;

class OptionalExample {

    
    public static void main(String[] args) {
        Blog blog = new Blog("Java Streams", null);

        // Avoid NullPointerException using Optional
        List<String> tags = blog.getTags()
            .orElse(Collections.emptyList()); // Provide a default value if tags are null

        System.out.println("Tags: " + tags);
    }
}

