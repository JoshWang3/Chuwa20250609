import java.util.*;

class BlogPostOptional {
    private String title;
    private Optional<List<String>> tags;

    public BlogPostOptional(String title, List<String> tags) {
        this.title = title;
        this.tags = Optional.ofNullable(tags);
    }

    public Optional<List<String>> getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }
}

class ProductOptional {
    private String name;
    private Optional<String> category;

    public ProductOptional(String name, String category) {
        this.name = name;
        this.category = Optional.ofNullable(category);
    }

    public Optional<String> getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }
}


public class OptionalDemo {
    public static void main(String[] args) {
        // BlogPost: one with tags, one without
        BlogPostOptional post1 = new BlogPostOptional("Intro to Java", Arrays.asList("Java", "Streams"));
        BlogPostOptional post2 = new BlogPostOptional("Empty Post", null); // tags = null

        // Safe access using Optional
        System.out.println("Tags for post1:");
        post1.getTags().ifPresent(tags ->
                tags.forEach(System.out::println)
        );

        System.out.println("Tags for post2:");
        post2.getTags()
                .orElse(Collections.emptyList())
                .forEach(System.out::println);

        // Product: one with category, one without
        ProductOptional p1 = new ProductOptional("iPhone", "Electronics");
        ProductOptional p2 = new ProductOptional("Mystery Box", null);

        System.out.println("Category of " + p1.getName() + ": " +
                p1.getCategory().orElse("Uncategorized"));

        System.out.println("Category of " + p2.getName() + ": " +
                p2.getCategory().orElse("Uncategorized"));
    }
}
