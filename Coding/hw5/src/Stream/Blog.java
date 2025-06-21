package Stream;

import java.util.List;

public class Blog {
    private String id;
    private List<Tag> tags;

    public Blog(String id, List<Tag> tags) {
        this.id = id;
        this.tags = tags;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public List<Tag> getTags() {
        return tags;
    }
}
