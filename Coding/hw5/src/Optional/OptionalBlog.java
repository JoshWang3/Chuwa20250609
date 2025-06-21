package Optional;

public class OptionalBlog {
    private String id;
    private OptionalTag tag;

    public OptionalBlog(String id, OptionalTag tag) {
        this.id = id;
        this.tag = tag;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTag(OptionalTag tag) {
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public OptionalTag getTag() {
        return tag;
    }
}
