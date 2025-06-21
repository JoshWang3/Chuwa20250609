
# Java Stream API Exercises

Yujiao Huang

2025 Jun 20 

---

## 1. Solve following questions **using Stream API**, **you should write complete code context** rather than a single Stream API statement (please also write necessary POJO classes or helper code):

### 1.1 Find top 3 longest strings that start with a vowel

```java
import java.util.*;
import java.util.stream.*;

public class VowelStrings {
    public static List<String> findTop3LongestStartingWithVowel(List<String> input) {
        return input.stream()
            // case-insensitive check for leading vowel
            .filter(s -> s.matches("(?i)^[aeiou].*"))
            // sort by length descending
            .sorted(Comparator.comparingInt(String::length).reversed())
            .limit(3)
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList(
            "apple", "orange", "umbrella", "ice", "elephant", "astronomy", "dog"
        );
        List<String> top3 = findTop3LongestStartingWithVowel(words);
        System.out.println("Top 3: " + top3);
        // Expected output: [astronomy, elephant, umbrella]
    }
}
```

---

### 1.2 Return names of departments where average employee salary > 100,000

```java
import java.util.*;
import java.util.stream.*;

class Employee {
    private String name;
    private String department;
    private double salary;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    public String getDepartment() { return department; }
    public double getSalary()      { return salary; }
}

public class DepartmentSalary {
    public static List<String> highPayingDepartments(List<Employee> employees) {
        return employees.stream()
            // group salaries by department
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
            ))
            // filter departments with avg > 100k
            .entrySet().stream()
            .filter(e -> e.getValue() > 100_000)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Employee> team = Arrays.asList(
            new Employee("Amy", "Engineering", 120_000),
            new Employee("Bo",   "Engineering",  95_000),
            new Employee("Cindy", "HR",          110_000),
            new Employee("Daisy",  "HR",          105_000),
            new Employee("Ellen",   "Sales",        90_000)
        );
        System.out.println(highPayingDepartments(team));
        // Expected output: [Engineering, HR]
    }
}
```

---

### 1.3 Get a sorted list of all unique tags from a list of blog posts

```java
import java.util.*;
import java.util.stream.*;

class BlogPost {
    private String title;
    private List<String> tags;

    public BlogPost(String title, List<String> tags) {
        this.title = title;
        this.tags  = tags;
    }
    public List<String> getTags() { return tags; }
}

public class UniqueSortedTags {
    public static List<String> extractSortedUniqueTags(List<BlogPost> posts) {
        return posts.stream()
            .flatMap(post -> post.getTags().stream())
            .distinct()
            .sorted()
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<BlogPost> posts = Arrays.asList(
            new BlogPost("Intro to Java", Arrays.asList("Java", "Streams", "Tutorial")),
            new BlogPost("Advanced Streams", Arrays.asList("Java", "Streams", "Performance")),
            new BlogPost("Spring Boot", Arrays.asList("Java", "Spring"))
        );
        System.out.println(extractSortedUniqueTags(posts));
        // Expected output: [Java, Performance, Spring, Streams, Tutorial]
    }
}
```

---

### 1.4 Return top 5 words by frequency from a paragraph

```java
import java.util.*;
import java.util.stream.*;

public class TopWords {
    public static List<String> top5ByFrequency(String paragraph) {
        return Arrays.stream(paragraph
                .toLowerCase()
                // split on non-word characters
                .split("\W+"))
            .filter(w -> !w.isEmpty())
            .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
            .entrySet().stream()
            // sort by count descending
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(5)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String text = "Java is great. Java streams make data processing easier. " +
                      "With Java streams you can filter, map, and collect.";
        System.out.println(top5ByFrequency(text));
        // Example output: [java, streams, make, data, processing]
    }
}
```

---

### 1.5 Group products by category and sort each group by price descending

```java
import java.util.*;
import java.util.stream.*;

class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name     = name;
        this.category = category;
        this.price    = price;
    }
    public String getCategory() { return category; }
    public double getPrice()    { return price; }
    @Override
    public String toString()    {
        return name + "($" + price + ")";
    }
}

public class ProductsByCategory {
    public static Map<String, List<Product>> groupAndSort(List<Product> products) {
        return products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                // for each category, sort its list by price desc
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> list.stream()
                                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                                .collect(Collectors.toList())
                )
            ));
    }

    public static void main(String[] args) {
        List<Product> catalog = Arrays.asList(
            new Product("Laptop",  "Electronics", 1_200),
            new Product("Phone",   "Electronics",   800),
            new Product("Shirt",   "Clothing",       40),
            new Product("Jeans",   "Clothing",       60),
            new Product("Blender", "Home",          120)
        );
        Map<String, List<Product>> result = groupAndSort(catalog);
        result.forEach((cat, list) ->
            System.out.println(cat + ": " + list)
        );
        // Sample output:
        // Electronics: [Laptop($1200.0), Phone($800.0)]
        // Clothing:    [Jeans($60.0), Shirt($40.0)]
        // Home:        [Blender($120.0)]
    }
}
```

---

## 2. Write code snippet to explain how **Optional** helps prevent null pointer exception, you may use blog-tags, and product-category POJOs to demo.

```java
import java.util.*;

class BlogPost {
    private String title;
    private List<String> tags; // might be null

    public BlogPost(String title, List<String> tags) {
        this.title = title;
        this.tags  = tags;
    }
    public List<String> getTags() { return tags; }
}

class Product {
    private String name;
    private String category; // might be null

    public Product(String name, String category) {
        this.name     = name;
        this.category = category;
    }
    public String getCategory() { return category; }
}

public class OptionalDemo {
    public static void main(String[] args) {
        BlogPost post = new BlogPost("Streams in Java", null);
        // Instead of risking NPE:
        List<String> safeTags = Optional.ofNullable(post.getTags())
                                        .orElse(Collections.emptyList());
        System.out.println("Tags: " + safeTags);

        Product prod = new Product("Laptop", null);
        // Provide default category if missing
        String category = Optional.ofNullable(prod.getCategory())
                                  .orElse("Uncategorized");
        System.out.println("Category: " + category);
    }
}
```

---

## 3. Explain why Java Stream API is required, how does it help on data processing?

1. **Declarative style** 
   Streams let us express *what* we want (filter, map, reduce) rather than *how* to loop. This reduces boilerplate.

2. **Chaining and pipelines** 
   We can chain multiple operations (`filter().map().collect()`) into a single pipeline, improving clarity.

3. **Lazy evaluation** 
   Intermediate operations are only computed when a terminal operation runs. This can optimize performance.

4. **Easy aggregation** 
   Built‑in collectors (`averagingDouble`, `groupingBy`, `counting`) simplify common tasks like sums, averages, grouping, etc.

5. **Parallelism** 
   Switching to parallel processing is as simple as calling `parallelStream()` instead of `stream()`, letting Java handle threading.

Overall, the Stream API brings a functional, expressive, and parallel approach to data processing in Java.
