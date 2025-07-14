
import java.util.List;
import java.util.Optional;

class Blog {

    
    private String title;
    private List<String> tags;

    public Blog() {
    }

    public Blog(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public Optional<List<String>> getTags() {
        return Optional.ofNullable(tags);
    }
}
