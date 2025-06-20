# Chuwa hw5

## Q1

### 1

```java
import java.util.*;
import java.util.stream.*;

public class Q1_1_LongestVowelStrings {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("apple", "orange", "banana", "umbrella", "elephant", "ice", "echo");

        List<String> result = input.stream()

            .filter(s -> s.matches("(?i)^[aeiou].*"))   
            .sorted((a, b) -> Integer.compare(b.length(), a.length()))
            .limit(3)
            .collect(Collectors.toList());

        System.out.println("Top 3 longest strings starting with vowel: " + result);
    }
}

```

### 2

```java
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "Engineering", 120000),
            new Employee("Bob", "HR", 80000),
            new Employee("Charlie", "Engineering", 100000),
            new Employee("Dana", "Marketing", 110000)
        );

        List<String> result = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
            ))
            .entrySet().stream()
            .filter(entry -> entry.getValue() > 100000)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        System.out.println("Departments with avg salary > 100k: " + result);
    }
}

// Supporting class: not public
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
    public double getSalary() { return salary; }
}

```

### 3

```java
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<BlogPost> posts = Arrays.asList(
            new BlogPost("Java Streams", Arrays.asList("Java", "Streams", "Functional")),
            new BlogPost("Docker", Arrays.asList("DevOps", "Docker")),
            new BlogPost("Spring Boot", Arrays.asList("Java", "Spring"))
        );

        List<String> result = posts.stream()

            .flatMap(post -> post.getTags().stream())
            .distinct()
            .sorted()
            .collect(Collectors.toList());

        System.out.println("Unique sorted tags: " + result);
    }
}

class BlogPost {
    private String title;
    private List<String> tags;

    public BlogPost(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }
}

```

### 4

```java
import java.util.*;
import java.util.stream.*;

public class Q1_4_TopWords {
    public static void main(String[] args) {
        String paragraph = "Java is great. Java is fast. Java streams are powerful and fast and elegant.";

        List<String> result = Arrays.stream(paragraph.toLowerCase().split("\\W+"))

            .collect(Collectors.groupingBy(word -> word, Collectors.counting()))

            .entrySet().stream()

            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())

            .limit(5)

            .map(Map.Entry::getKey)

            .collect(Collectors.toList());

        System.out.println("Top 5 frequent words: " + result);
    }
}

```

### 5

```java
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("MacBook", "Electronics", 2000),
            new Product("iPhone", "Electronics", 1000),
            new Product("Shirt", "Clothing", 40),
            new Product("Jeans", "Clothing", 70),
            new Product("Blazer", "Clothing", 150)
        );

        List<String> sortedCategories = products.stream()

            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.averagingDouble(Product::getPrice)
            ))
            .entrySet().stream()

            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())

            .map(Map.Entry::getKey)

            .collect(Collectors.toList());

        System.out.println("Categories sorted by average price: " + sortedCategories);
    }
}

class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getCategory() { return category; }
    public double getPrice() { return price; }
}

```

## Q2

```java
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // Product with a category
        Product p1 = new Product("MacBook", new Category("Electronics"));

        // Product with no category (null)
        Product p2 = new Product("Mystery Box", null);

        // Print categories safely using Optional
        printCategoryName(p1);
        printCategoryName(p2);
    }

    // Safe method using Optional
    public static void printCategoryName(Product product) {
        String categoryName = product.getCategory()
            .map(Category::getName)   // if present, get name
            .orElse("Unknown");       // if empty, use fallback

        System.out.println(product.getName() + " category: " + categoryName);
    }
}

// Supporting class
class Product {
    private String name;
    private Optional<Category> category;

    public Product(String name, Category category) {
        this.name = name;
        // Wrap category (could be null) in Optional
        this.category = Optional.ofNullable(category);
    }

    public String getName() { return name; }
    public Optional<Category> getCategory() { return category; }
}

// Another class
class Category {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

```

## Q3

1. Stream API lets you write less code by avoiding manual loops and collections.
2. It supports chaining operations like filter, map, sort, making logic easier to follow.
3. You don’t need to manage result lists manually; collect() handles it.
4. It's easier to read and maintain than nested loops.
5. Built-in methods like groupingBy and reduce simplify complex tasks.
6. Parallel processing is simple using parallelStream().
7. It works well with lambdas and Optional for safer, cleaner code.
8. Promotes immutability and avoids shared mutable state.
9. Reduces bugs caused by null, index errors, or logic mistakes.