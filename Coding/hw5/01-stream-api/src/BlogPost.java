import java.util.*;

class BlogPost {
    private String title;
    private List<String> tags;

    public BlogPost(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }
}
