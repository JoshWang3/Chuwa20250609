# HW5
# Java Stream API & Optional Assignment
## 1. Solve the following questions using Stream API
### 1.1 Find top 3 longest strings that start with a vowel
```java
import java.util.*;
import java.util.stream.*;

public class VowelStrings {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "orange", "banana", "umbrella", "egg", "ink", "owl", "car");
        List<String> result = words.stream()
            .filter(w -> w.matches("(?i)^[aeiou].*"))
            .sorted((a, b) -> Integer.compare(b.length(), a.length()))
            .limit(3)
            .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

### 1.2 Return names of departments where average employee salary > 100,000

```java
class Employee {
    String name;
    String department;
    double salary;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
}

public class DepartmentFilter {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "Engineering", 120000),
            new Employee("Bob", "HR", 90000),
            new Employee("Charlie", "Engineering", 130000),
            new Employee("David", "Marketing", 95000)
        );

        List<String> highAvgSalaryDepts = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)))
            .entrySet().stream()
            .filter(e -> e.getValue() > 100000)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        System.out.println(highAvgSalaryDepts);
    }
}
```

### 1.3 Get a sorted list of all unique tags from a list of blog posts
```java
class BlogPost {
    String title;
    List<String> tags;

    public BlogPost(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public List<String> getTags() { return tags; }
}

public class BlogTagProcessor {
    public static void main(String[] args) {
        List<BlogPost> posts = Arrays.asList(
            new BlogPost("Java Streams", Arrays.asList("Java", "Streams", "Functional")),
            new BlogPost("Spring Boot", Arrays.asList("Java", "Spring")),
            new BlogPost("Microservices", Arrays.asList("Architecture", "Spring"))
        );

        List<String> sortedTags = posts.stream()
            .flatMap(post -> post.getTags().stream())
            .distinct()
            .sorted()
            .collect(Collectors.toList());

        System.out.println(sortedTags);
    }
}
```

### 1.4 Return top 5 words by frequency from a paragraph

```java
public class WordFrequency {
    public static void main(String[] args) {
        String paragraph = "Java Java stream stream stream code code tutorial tutorial tutorial tutorial example";
        List<String> topWords = Arrays.stream(paragraph.toLowerCase().split("\\s+"))
            .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
            .entrySet().stream()
            .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
            .limit(5)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        System.out.println(topWords);
    }
}
```

### 1.5 Group products by category and sort each group by price descending

```java
class Product {
    String name;
    String category;
    double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getName() { return name; }
}

public class ProductGrouper {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 1200),
            new Product("Smartphone", "Electronics", 800),
            new Product("Shampoo", "Personal Care", 15),
            new Product("Toothpaste", "Personal Care", 5),
            new Product("TV", "Electronics", 1500)
        );

        Map<String, List<Product>> grouped = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory,
                Collectors.collectingAndThen(Collectors.toList(),
                    list -> list.stream()
                        .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                        .collect(Collectors.toList())
                )));

        grouped.forEach((cat, list) -> {
            System.out.println(cat + ": " + list.stream().map(Product::getName).collect(Collectors.toList()));
        });
    }
}
```
---

## 2. Explain Optional
Write a code snippet to explain how **Optional** helps prevent null pointer exceptions.  
You may use `blog-tags`, and `product-category` POJOs to demonstrate.

- Prevents manual null checks + Improves readability and safety +Encourages functional programming with map, filter, orElse

```java
import java.util.*;

class Product {
    private String name;
    private Category category;

    public Product(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public Optional<Category> getCategory() {
        return Optional.ofNullable(category); // Avoid NullPointerException
    }

    public String getName() {
        return name;
    }
}

class Category {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class OptionalExample {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", new Category("Electronics")),
            new Product("Shoes", null) // category is null!
        );

        for (Product p : products) {
            // Using Optional to avoid NullPointerException
            String catName = p.getCategory()
                              .map(Category::getName)
                              .orElse("Uncategorized");

            System.out.println(p.getName() + " -> " + catName);
        }
    }
}
```
---

## 3. Why Java Stream API?
Explain **why Java Stream API is required** and how it helps in **data processing**.
- Java Stream API makes it easier to process collections, build pipelines, and write cleaner, more expressive code — especially useful in large-scale enterprise applications.
```java
// Old way
for (String s : list) {
    if (s.startsWith("A")) result.add(s.toLowerCase());
}

// Stream API
list.stream().filter(s -> s.startsWith("A")).map(String::toLowerCase).collect(Collectors.toList());
```