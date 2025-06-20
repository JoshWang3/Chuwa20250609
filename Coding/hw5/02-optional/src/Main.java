import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Simulate missing blogPost or missing tags
        Product product = new Product("Laptop", null);

        // Traditional way (unsafe, may throw NullPointerException)
        // List<String> tags = product.getBlogPost().getTags(); ❌ NPE risk

        // Safe way using Optional
        List<String> tags = product.getBlogPost()
                .flatMap(BlogPost::getTags)        // unwraps Optional<BlogPost> → Optional<List<String>>
                .orElse(Collections.emptyList());  // fallback to empty list

        System.out.println("Tags: " + tags);
    }
}
