package Coding;
import java.util.*;

class Blog {
    private String title;
    private List<String> tags;

    public Blog(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public Optional<List<String>> getTags() {
        return Optional.ofNullable(tags); // 关键：返回 Optional
    }

    public String getTitle() {
        return title;
    }
}

public class BlogDemo {
    public static void main(String[] args) {
        Blog b1 = new Blog("Java Stream", Arrays.asList("java", "stream", "lambda"));
        Blog b2 = new Blog("Empty Tags", null); // tags 为 null

        // 使用 Optional 安全访问
        printFirstTag(b1);
        printFirstTag(b2); // 不会抛出 NullPointerException
    }

    static void printFirstTag(Blog blog) {
        String firstTag = blog.getTags()
                .flatMap(tags -> tags.stream().findFirst()) // 提取第一个 tag
                .orElse("No tags");
        System.out.println(blog.getTitle() + " -> " + firstTag);
    }
}