Solve following questions **using Stream API, you should write complete code context** rather than a single Stream API statement (please also write necessary POJO classes or helper code):

1. Find top 3 longest strings that start with a vowel

   ```java
   import java.util.*;
   import java.util.stream.*;
   
   public class VowelLongestStrings {
       public static void main(String[] args) {
           List<String> words = Arrays.asList("apple", "banana", "orange", "umbrella", "elephant", "ice", "octopus", "grape");
   
           List<String> result = words.stream()
                   .filter(s -> s.matches("(?i)^[aeiou].*"))
                   .sorted(Comparator.comparingInt(String::length).reversed())
                   .limit(3)
                   .collect(Collectors.toList());
   
           System.out.println(result); // 示例输出：[elephant, umbrella, octopus]
       }
   }
   
   ```

   

2. Return names of departments where average employee salary > 100,000

```java
class Employee {
    String name;
    double salary;
    public Employee(String name, double salary) { this.name = name; this.salary = salary; }
}

class Department {
    String name;
    List<Employee> employees;
    public Department(String name, List<Employee> employees) { this.name = name; this.employees = employees; }
}

public class DepartmentAvgSalary {
    public static void main(String[] args) {
        List<Department> departments = Arrays.asList(
            new Department("IT", Arrays.asList(new Employee("A", 120_000), new Employee("B", 110_000))),
            new Department("HR", Arrays.asList(new Employee("C", 60_000), new Employee("D", 70_000))),
            new Department("Finance", Arrays.asList(new Employee("E", 105_000), new Employee("F", 115_000)))
        );

        List<String> result = departments.stream()
                .filter(d -> d.employees.stream().mapToDouble(e -> e.salary).average().orElse(0) > 100_000)
                .map(d -> d.name)
                .collect(Collectors.toList());

        System.out.println(result); // [IT, Finance]
    }
}

```



1. Get a sorted list of all unique tags from a list of blog posts (Blog to Tags is 1-to-many relationship)

```java
class Blog {
    String title;
    List<String> tags;
    public Blog(String title, List<String> tags) { this.title = title; this.tags = tags; }
}

public class BlogTags {
    public static void main(String[] args) {
        List<Blog> blogs = Arrays.asList(
            new Blog("A", Arrays.asList("java", "stream", "coding")),
            new Blog("B", Arrays.asList("java", "spring", "backend")),
            new Blog("C", Arrays.asList("coding", "life"))
        );

        List<String> uniqueSortedTags = blogs.stream()
                .flatMap(blog -> blog.tags.stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(uniqueSortedTags); // [backend, coding, java, life, spring, stream]
    }
}

```



1. Return top 5 words by frequency from a paragraph

```java
public class TopWords {
    public static void main(String[] args) {
        String paragraph = "Java is great. Java stream API is powerful. Stream and lambda make Java code concise. Java Java Java!";

        List<String> top5Words = Arrays.stream(paragraph.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+"))
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(top5Words); // [java, is, stream, api, powerful]
    }
}

```



1. Group products by category and sort each group by price descending

```java
class Product {
    String name;
    String category;
    double price;
    public Product(String name, String category, double price) {
        this.name = name; this.category = category; this.price = price;
    }
}

public class GroupProductByCategory {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("iPhone", "Electronics", 999),
            new Product("TV", "Electronics", 799),
            new Product("T-shirt", "Clothing", 39),
            new Product("Jeans", "Clothing", 59)
        );

        Map<String, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(
                        p -> p.category,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble((Product p) -> p.price).reversed())
                                        .collect(Collectors.toList())
                        )
                ));

        grouped.forEach((cat, ps) -> {
            System.out.println(cat + ": " + ps.stream().map(p -> p.name + "($" + p.price + ")").collect(Collectors.joining(", ")));
        });
        // 输出：
        // Electronics: iPhone($999.0), TV($799.0)
        // Clothing: Jeans($59.0), T-shirt($39.0)
    }
}

```

Write code snippet to explain how **Optional** helps prevent null pointer exception, you may use blog-tags and product-category POJOs to demo.



```java
import java.util.Optional;

class Product2 {
    String name;
    Category2 category;
    public Product2(String name, Category2 category) { this.name = name; this.category = category; }
}

class Category2 {
    String name;
    public Category2(String name) { this.name = name; }
}

public class OptionalDemo {
    public static void main(String[] args) {
        Product2 p = new Product2("iPhone", null);

        // 传统写法可能NPE
        // String categoryName = p.category.name; // NPE!

        // Optional 防护
        String catName = Optional.ofNullable(p)
                .map(product -> product.category)
                .map(category -> category.name)
                .orElse("Unknown");

        System.out.println(catName); // 输出：Unknown
    }
}

```

Explain why Java Stream API is required, how does it help on data processing?

Java Stream API really simplifies data processing. It lets me chain together filters, maps, sorts, and groupings in a readable way, instead of using clunky for-loops. It’s also safer—there’s less chance of errors with manual iteration.

If I need better performance, I can easily switch to parallel streams to take advantage of multiple CPU cores. Plus, it works seamlessly with collections like List and Map.

In short, Stream API helps me write cleaner, more efficient, and scalable code for handling data.