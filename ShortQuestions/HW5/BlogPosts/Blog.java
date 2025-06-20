package BlogPosts;

import java.util.List;

public class Blog {
    private String title;
    private List<String> tags;

    public Blog(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public String getTitle() { return title; }
    public List<String> getTags() { return tags; }
}
