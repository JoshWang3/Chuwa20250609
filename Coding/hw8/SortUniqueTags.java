package Coding.hw8;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Blog {
    String title;
    List<String> tags;

    public Blog(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public String getTitle() {return title;}
    public List<String> getTags() {return tags;}
}
public class SortUniqueTags {
    public static void main(String[] args) {
        List<Blog> blogs = Arrays.asList(new Blog("1", Arrays.asList("java", "python")),
                new Blog("2", Arrays.asList("javascript", "typescript")),
                new Blog("3", Arrays.asList("java", "c++")));

        List<String> ans = blogs.stream()
                .flatMap(blog -> blog.getTags().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(ans);
    }
}
