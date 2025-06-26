package hw5;

import java.util.List;

// Get a sorted list of all unique tags from a list of blog posts (Blog to Tags is 1-to-many relationship)
public class Blog {
    private String title;
    private List<String> tags;

    public Blog(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTags() {
        return tags;
    }
}
