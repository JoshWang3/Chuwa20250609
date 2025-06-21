import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        //Path filePath = Path.of("strings.json");
        InputStream input = Main.class.getClassLoader().getResourceAsStream("strings.json");

        //String json = Files.readString(filePath);

        ObjectMapper mapper = new ObjectMapper();
        List<String> texts = mapper.readValue(input, new TypeReference<>() {});

        List<String> ans = StreamPractice.topNLongestStrings(texts, 3);
        List<String> ans1 = StreamPractice.topNLongestStrings(texts, 4);


        System.out.println(ans);
        System.out.println(ans1);

        //2

        InputStream incomeInput = Main.class.getClassLoader().getResourceAsStream("incomes.json");
        List<Department> departments = mapper.readValue(incomeInput, new TypeReference<>() {});

        System.out.println(StreamPractice.sixFiguresClub(departments));

        //3
        InputStream blogInput = Main.class.getClassLoader().getResourceAsStream("blogs.json");
        List<Blog> blogs = mapper.readValue(blogInput, new TypeReference<>() {});

        System.out.println(StreamPractice.sortedUniqueTags(blogs));

        //4
        String paragraph = "The U.S. has also replenished stocks of ground-based interceptors for " +
                "the Thaad antimissile system it set up in Israel last year." +
                " Formally known as Terminal High Altitude Area Defense, " +
                "the system is operated by the U.S. " +
                "Army and designed to intercept missiles inside or outside the atmosphere during their final phase of flight, " +
                "known as the terminal phase.";


        System.out.println(StreamPractice.topNWords(paragraph, 5));
        System.out.println(StreamPractice.topNWords(paragraph, 8));

        //5
        InputStream productInput = Main.class.getClassLoader().getResourceAsStream("products.json");
        List<Product> products = mapper.readValue(productInput, new TypeReference<>() {});
        System.out.println(StreamPractice.byCategoryAndPrice(products));



        //6 Optional Wraps nullable values
        //
        //Forces you to think: “What if it’s not there?”
        //
        //Supports fluent, chainable operations like .map(), .filter(), .orElse()
        //
        //Prevents you from calling methods on null references
        Product p2 = new Product();
        p2.setDescription(null);
        String des = p2.getDescription().map(String::toLowerCase).orElse("no description");
        System.out.println(des);

        Product p1 = new Product();
        System.out.println(p1.getName().toLowerCase());


        /*
        Q3:
            because in enterprise level project. Data usually massive and developer deal with complex
            data every day. traditional collection operation is expensive and hard to read.
            stream API here to help developer to deal with complex and massive data. provide a declarative
            functional style data stream pipeline
            where metadata could be modified gracefully and concisely. make code easy to test and read
            Overall, Stream API simplifies complex data transformations, improves readability, and supports immutability and functional programming practices.

        * */

    }
}


class StreamPractice {
    static Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
    static List<String> topNLongestStrings(List<String> texts, int n) {
        return texts.stream().filter(text -> vowels.contains(text.charAt(0)))
                .sorted(Comparator.comparing(String::length).reversed())
                .limit(n)
                .collect(Collectors.toList());

    }

    static List<String> sixFiguresClub(List<Department> departments) {
        return departments.stream()
                .filter(d -> d.getEmployees().stream().anyMatch(e -> e.getSalary() >= 100000))
                .map(Department::getDepartmentName)
                .collect(Collectors.toList());
    }

    static List<String> sortedUniqueTags(List<Blog> blogs) {
        return blogs.stream().map(Blog::getTags)
                .flatMap(List::stream)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    static List<String> topNWords(String paragraph, int n) {
        return Arrays.stream(paragraph.toLowerCase().split("\\W+"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

    }

    static Map<String, List<Product>> byCategoryAndPrice(List<Product> products) {
        return products.stream()
                .collect(
                        Collectors.groupingBy(Product::getCategory,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        (List<Product> list) -> list.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                                                .collect(Collectors.toList())
                                ))
                );


    }





}









