import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Blog blog = new Blog(5, null);

        List<String> tags = blog.getTags();
        //Without Optional, if tags is empty, call any method will get a NullPointerException.
        // System.out.println(tags.size()); will get error.

        //Traditional Method
        if (tags != null) {
            System.out.println(tags.size());
        }

        // Optional Method
        int size;
        size = Optional.ofNullable(tags).map(List::size).orElse(0);
        System.out.println(size);
    }
}
