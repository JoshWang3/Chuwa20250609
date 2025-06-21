
## Question 1
1. 
```java
import java.util.*;
import java.util.stream.*;

public class VowelStringFinder {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cat", "dog", "elephant", "octopus", "oct");
        
        List<String> result = words.stream()
            .filter(s -> "aeiouAEIOU".indexOf(s.charAt(0)) != -1)
            .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length()))
            .limit(3)
            .collect(Collectors.toList());
        
        System.out.println(result);
    }
}
```
2. 
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
    
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
}

public class HighSalaryDepartments {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "Engineering", 120000),
            new Employee("Bob", "Engineering", 110000),
            new Employee("Charlie", "Sales", 80000),
            new Employee("Diana", "Sales", 75000),
            new Employee("Eve", "Marketing", 95000),
            new Employee("Frank", "Marketing", 125000)
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
        
        System.out.println(result);
    }
}
```
3. 
```java
import java.util.*;
import java.util.stream.*;

class BlogPost {
    private String title;
    private List<String> tags;
    
    public BlogPost(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }
    
    public String getTitle() { return title; }
    public List<String> getTags() { return tags; }
}

public class BlogTagExtractor {
    public static void main(String[] args) {
        List<BlogPost> posts = Arrays.asList(
            new BlogPost("Java Basics", Arrays.asList("java", "programming", "beginner")),
            new BlogPost("Advanced Java", Arrays.asList("java", "advanced", "streams")),
            new BlogPost("Python Guide", Arrays.asList("python", "programming", "tutorial")),
            new BlogPost("Stream API", Arrays.asList("java", "streams", "functional"))
        );
        
        List<String> result = posts.stream()
            .flatMap(post -> post.getTags().stream())
            .distinct()
            .sorted()
            .collect(Collectors.toList());
        
        System.out.println(result);
    }
}
```
4. 
```java
import java.util.*;
import java.util.stream.*;
import java.util.function.Function;

public class WordFrequencyAnalyzer {
    public static void main(String[] args) {
        String paragraph = "Java is a programming language. Java is platform independent. " +
                          "Programming in Java is fun. Java developers love Java because " +
                          "Java is versatile and Java is widely used.";
        
        List<Map.Entry<String, Long>> topWords = Arrays.stream(paragraph.toLowerCase()
                .replaceAll("[^a-zA-Z\\s]", "")
                .split("\\s+"))
            .filter(word -> !word.isEmpty())
            .collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
            ))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(5)
            .collect(Collectors.toList());
        
        System.out.println("Top 5 words:");
        topWords.forEach(entry -> 
            System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
```
5. 
```java
import java.util.*;
import java.util.stream.*;

class Product {
    private String name;
    private String category;
    private double price;
    
    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
    
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
}

public class ProductCategorizer {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("iPhone", "Electronics", 999.99),
            new Product("Samsung TV", "Electronics", 1299.99),
            new Product("Headphones", "Electronics", 199.99),
            new Product("T-Shirt", "Clothing", 29.99),
            new Product("Jeans", "Clothing", 79.99),
            new Product("Jacket", "Clothing", 149.99)
        );
        
        Map<String, List<Product>> result = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> list.stream()
                        .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                        .collect(Collectors.toList())
                )
            ));
        
        result.forEach((category, productList) -> {
            System.out.println(category + ": " + productList);
        });
    }
}
```


## Question 2

```java
import java.util.*;

class Product {
    private String name;
    private String category;
    private double price;
    
    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
    
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
}

class ProductService {
    private List<Product> products = Arrays.asList(
        new Product("iPhone", "Electronics", 999.99),
        new Product("TV", "Electronics", 1299.99)
    );
    
    public Product findProductNPE(String name) {
        return products.stream()
            .filter(p -> p.getName().equals(name))
            .findFirst()
            .orElse(null);
    }
    
    public Optional<Product> findProduct(String name) {
        return products.stream()
            .filter(p -> p.getName().equals(name))
            .findFirst();
    }
}

public class OptionalDemo {
    public static void main(String[] args) {
        ProductService service = new ProductService();
	       
        try {
            Product product = service.findProductNPE("NonExistent");
            System.out.println("Price: " + product.getPrice());
        } catch (NullPointerException e) {
            System.out.println("NPE");
        }// Print: NPE
	       
        double price = service.findProduct("NonExistent")
            .map(Product::getPrice)
            .orElse(0.0);
        System.out.println("Price: " + price);  // Print: Price: 0.0
    }
}
``` 

## Question 3
Java needed functional programming capabilities that focus on the operation rather than the iteration mechanics.
- describe what to do, not how to do it. 
- built-in parallel processing
- comprehensive operations
- efficient memory usage