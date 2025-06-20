import java.util.*;

class Product {
    private String name;
    private BlogPost blogPost;

    public Product(String name, BlogPost blogPost) {
        this.name = name;
        this.blogPost = blogPost;
    }

    public Optional<BlogPost> getBlogPost() {
        return Optional.ofNullable(blogPost); // Prevents NPE
    }

    public String getName() {
        return name;
    }
}