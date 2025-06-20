import java.util.*;

class BlogPost {
    private String title;
    private List<String> tags;

    public BlogPost(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public Optional<List<String>> getTags() {
        return Optional.ofNullable(tags); // Prevents NPE
    }

    public String getTitle() {
        return title;
    }
}
