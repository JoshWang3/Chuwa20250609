package hw5.blog;

import java.util.ArrayList;
import java.util.List;

public class blog {
    private List<tag> tags;

    public blog() {
        this.tags = new ArrayList<>();
    }

    public List<tag> getTags() {
        return tags;
    }
    public void addTag(tag t) {
        tags.add(t);
    }
    public void removeTag(tag t) {
        tags.remove(t);
    }
}
