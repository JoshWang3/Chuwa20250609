import Stream.Blog;
import Stream.Department;
import Stream.Tag;
import Stream.Product;
import Optional.OptionalBlog;
import Optional.OptionalTag;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> strs = new ArrayList<>(Arrays.asList("apple", "banana", "umbrella", "appointment", "impossible"));
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'i', 'o', 'e', 'u'));
        List<String> vowelArr = strs.stream().filter(str -> vowels.contains(str.charAt(0))).sorted((a, b) -> b.length() - a.length()).limit(3).collect(Collectors.toList());
        System.out.println(vowelArr);
        // appointment, impossible, umbrella

        List<Department> departments = new ArrayList<>(Arrays.asList(new Department("technology", 200000),
                new Department("sales", 150000), new Department("human resources", 90000),
                new Department("security", 80000)));

        List<String> departmentNames = departments.stream()
                .filter(department -> department.getAvgSalary() > 100000)
                .map(department -> department.getName()).collect(Collectors.toList());

        System.out.println(departmentNames);
        // technology, sales

        Tag tag1 = new Tag("1", "aa");
        Tag tag2 = new Tag("2", "b1");
        Tag tag3 = new Tag("3", "ab");
        Tag tag4 = new Tag("4", "b2");

        List<Blog> blogs = new ArrayList<>(Arrays.asList(new Blog("1", new ArrayList<>(Arrays.asList(tag1, tag2))),
                new Blog("2", new ArrayList<>(Arrays.asList(tag1, tag3, tag4)))));


        List<String> tags = blogs.stream()
                .flatMap(blog -> blog.getTags().stream())
                .map(tag -> tag.getName())
                .sorted()
                .distinct().collect(Collectors.toList());
        System.out.println(tags);
        // aa, ab, b1, b2

        String paragraph = "When crafting responses, it is essential to adhere strictly to the specified language without incorporating any others. Additionally, consider any relevant modifiers during the response generation process, but refrain from mentioning them explicitly. Ensure that the content is structured in paragraphs rather than lists, and if multiple paragraphs are produced, label them with incrementing numbers. The writing should be concise and formal, avoiding unnecessary embellishments.";
        Stream<String> stream = Arrays.stream(paragraph.split("[,\\.\\s]+"));
        Map<String, Long> wordCount = stream
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        List<String> top5Words = wordCount.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .map(Map.Entry::getKey)
                .limit(5)
                .collect(Collectors.toList());

        System.out.println(top5Words);
        // the, paragraphs, them, is, and

        List<Product> products = Arrays.asList(
                new Product("apple", "produce", 2),
                new Product("banana", "produce", 1),
                new Product("shrimp", "seafood", 5),
                new Product("shirt", "clothes", 20),
                new Product("sweater", "clothes", 50),
                new Product("ipad", "clothes", 300),
                new Product("iphone", "electronics", 1000)
        );

        Map<String, Double> groupedCategories = products.stream()
                .collect(Collectors.groupingBy(
                        product -> product.getCategory(),
                        Collectors.averagingDouble(Product::getPrice)
                ));
//        System.out.println(groupedCategories);
        List<String> sortedCategories = groupedCategories.entrySet().stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(sortedCategories);
        // electronics, clothes, seafood, produce

        // Optional
        OptionalTag optionalTag = new OptionalTag("1", null);
        OptionalBlog optionalBlog = new OptionalBlog("1", optionalTag);

        Optional<String> tagName = Optional.ofNullable(optionalBlog.getTag().getName());
        System.out.println(tagName);
        // return empty
    }
}