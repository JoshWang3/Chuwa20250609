package Optional;

import BlogPosts.Blog;
import java.util.*;

public class BlogTagsDemo {
    public static void main(String[] args) {
        Blog blog = new Blog("SpongeBob", null);

        // without optional: -> might NPE if forget manually check
        int tagCount = 0;
        List<String> tags = blog.getTags();
        if (tags != null) {
            tagCount = tags.size();
        }
        System.out.println("Number of tags without Optional: " + tagCount); // output: Number of tags without Optional: 0


        // with optional:
        int tagCountOptional = Optional.ofNullable(blog.getTags())
                .map(List::size)
                .orElse(0);

        System.out.println("Number of tags with Optional: " + tagCountOptional); // output: Number of tags with Optional: 0
    }
}
