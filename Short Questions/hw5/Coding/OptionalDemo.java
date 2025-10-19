import java.util.*;

class BlogPost {
    String title;
    List<String> tags;
    BlogPost(String title, List<String> tags) { this.title = title; this.tags = tags; }
}


class Product {
    String name;
    String category;
    double price;
    Product(String name, String category, double price) {
        this.name = name; this.category = category; this.price = price;
    }
}

public class OptionalDemo {
    public static void main(String[] args) {
        BlogPost blog = new BlogPost("Stream API", null); // tags missing

        // Without Optional — would throw NPE
        // System.out.println(blog.tags.size());

        // With Optional
        int tagCount = Optional.ofNullable(blog)
                .map(b -> b.tags)
                .map(List::size)
                .orElse(0);

        System.out.println("Number of tags safely counted: " + tagCount);

        // Another example with product-category
        Product p = new Product("Unknown", null, 0);
        String categoryName = Optional.ofNullable(p)
                .map(prod -> prod.category)
                .orElse("Uncategorized");
        System.out.println("Category: " + categoryName);
    }
}
