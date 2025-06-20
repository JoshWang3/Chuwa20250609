package hw5.blog;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class socialMedia {
    public static void main(String[] args) {
        blog b1 = new blog();
        blog b2 = new blog();
        blog b3 = new blog();
        blog b4 = new blog();
        blog b5 = new blog();

        tag tag1 = new tag("labubu");
        tag tag2 = new tag("make america great again!");
        tag tag3 = new tag("healthy life style");
        tag tag4 = new tag("dream core");
        tag tag5 = new tag("Tasty food");

        b1.addTag(tag1);
        b2.addTag(tag2);
        b3.addTag(tag3);
        b3.addTag(tag5);
        b4.addTag(tag4);
        b5.addTag(tag3);
        b5.addTag(tag5);

        List<blog> blogs = Arrays.asList(new blog[]{b1, b2, b3, b4, b5});
        List<String> uniquetag = blogs.stream().flatMap(b -> b.getTags().stream())
        .map(tag::getName).distinct().sorted().collect(Collectors.toList());
        uniquetag.stream().forEach(System.out::println);
    }
}
