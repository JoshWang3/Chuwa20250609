import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;

// POJO Classes
class Employee {
    private String name;
    private String department;
    private double salary;
    
    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
    
    // Getters
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    
    @Override
    public String toString() {
        return "Employee{name='" + name + "', department='" + department + "', salary=" + salary + "}";
    }
}

class Blog {
    private String title;
    private String author;
    private List<String> tags;
    
    public Blog(String title, String author, List<String> tags) {
        this.title = title;
        this.author = author;
        this.tags = tags != null ? tags : new ArrayList<>();
    }
    
    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public List<String> getTags() { return tags; }
    
    @Override
    public String toString() {
        return "Blog{title='" + title + "', author='" + author + "', tags=" + tags + "}";
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
    
    // Getters
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    
    @Override
    public String toString() {
        return "Product{name='" + name + "', category='" + category + "', price=" + price + "}";
    }
}

public class StreamAPIExamples {
    
    public static void main(String[] args) {
        System.out.println("=== Java 8 Stream API Examples ===\n");
        
        // Problem 1: Find top 3 longest strings that start with a vowel
        problem1();
        
        // Problem 2: Return names of departments where average employee salary > 100,000
        problem2();
        
        // Problem 3: Get sorted list of unique tags from blog posts
        problem3();
        
        // Problem 4: Return top 5 words by frequency from paragraph
        problem4();
        
        // Problem 5: Group products by category and sort by price descending
        problem5();
        
        // Optional demonstration
        optionalDemo();
    }
    
    // Problem 1: Find top 3 longest strings that start with a vowel
    public static void problem1() {
        System.out.println("1. Top 3 longest strings starting with vowel:");
        
        List<String> strings = Arrays.asList(
            "apple", "elephant", "banana", "orange", "umbrella", 
            "cat", "dog", "internet", "application", "university",
            "book", "education", "artificial", "intelligence"
        );
        
        List<String> result = strings.stream()
            .filter(s -> s.matches("^[aeiouAEIOU].*"))  // starts with vowel
            .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length()))  // longest first
            .limit(3)
            .collect(Collectors.toList());
        
        result.forEach(System.out::println);
        System.out.println();
    }
    
    // Problem 2: Return names of departments where average employee salary > 100,000
    public static void problem2() {
        System.out.println("2. Departments with average salary > 100,000:");
        
        List<Employee> employees = Arrays.asList(
            new Employee("John", "Engineering", 120000),
            new Employee("Jane", "Engineering", 130000),
            new Employee("Bob", "Sales", 80000),
            new Employee("Alice", "Sales", 90000),
            new Employee("Charlie", "Finance", 110000),
            new Employee("Diana", "Finance", 105000),
            new Employee("Eve", "HR", 70000),
            new Employee("Frank", "HR", 75000)
        );
        
        List<String> departments = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment,
                    Collectors.averagingDouble(Employee::getSalary)))
            .entrySet().stream()
            .filter(entry -> entry.getValue() > 100000)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        
        departments.forEach(System.out::println);
        System.out.println();
    }
    
    // Problem 3: Get sorted list of unique tags from blog posts
    public static void problem3() {
        System.out.println("3. Sorted unique tags from blog posts:");
        
        List<Blog> blogs = Arrays.asList(
            new Blog("Java 8 Features", "Author1", Arrays.asList("java", "programming", "streams")),
            new Blog("Spring Boot Guide", "Author2", Arrays.asList("spring", "java", "framework")),
            new Blog("Database Design", "Author3", Arrays.asList("database", "sql", "design")),
            new Blog("Advanced Java", "Author4", Arrays.asList("java", "advanced", "programming"))
        );
        
        List<String> uniqueTags = blogs.stream()
            .flatMap(blog -> blog.getTags().stream())  // flatten all tags
            .distinct()  // unique tags
            .sorted()  // alphabetical order
            .collect(Collectors.toList());
        
        uniqueTags.forEach(System.out::println);
        System.out.println();
    }
    
    // Problem 4: Return top 5 words by frequency from paragraph
    public static void problem4() {
        System.out.println("4. Top 5 words by frequency:");
        
        String paragraph = "Java is a programming language. Java is popular. " +
                          "Programming with Java is fun. Java programming requires practice. " +
                          "Practice makes programming better.";
        
        Map<String, Long> wordFrequency = Arrays.stream(paragraph.toLowerCase()
                .replaceAll("[^a-zA-Z\\s]", "")  // remove punctuation
                .split("\\s+"))
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        
        List<Map.Entry<String, Long>> topWords = wordFrequency.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(5)
            .collect(Collectors.toList());
        
        topWords.forEach(entry -> 
            System.out.println(entry.getKey() + ": " + entry.getValue()));
        System.out.println();
    }
    
    // Problem 5: Group products by category and sort by price descending
    public static void problem5() {
        System.out.println("5. Products grouped by category, sorted by price (desc):");
        
        List<Product> products = Arrays.asList(
            new Product("iPhone", "Electronics", 999.99),
            new Product("MacBook", "Electronics", 1299.99),
            new Product("AirPods", "Electronics", 199.99),
            new Product("T-Shirt", "Clothing", 29.99),
            new Product("Jeans", "Clothing", 79.99),
            new Product("Jacket", "Clothing", 149.99),
            new Product("Novel", "Books", 19.99),
            new Product("Textbook", "Books", 99.99)
        );
        
        Map<String, List<Product>> groupedProducts = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory,
                    Collectors.collectingAndThen(
                            Collectors.toList(),
                            list -> list.stream()
                                    .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                                    .collect(Collectors.toList())
                    )));
        
        groupedProducts.forEach((category, productList) -> {
            System.out.println("Category: " + category);
            productList.forEach(p -> System.out.println("  " + p));
        });
        System.out.println();
    }
    
    // Optional demonstration to prevent NullPointerException
    public static void optionalDemo() {
        System.out.println("=== Optional Demo - Preventing NullPointerException ===");
        
        List<Blog> blogs = Arrays.asList(
            new Blog("Java Guide", "Author1", Arrays.asList("java", "programming")),
            new Blog("Empty Tags Blog", "Author2", null),  // null tags
            new Blog("Spring Tutorial", "Author3", Arrays.asList("spring", "framework"))
        );
        
        System.out.println("Without Optional (risky):");
        // This could throw NullPointerException
        try {
            blogs.forEach(blog -> {
                // Risky: direct access could cause NPE
                if (blog.getTags() != null && !blog.getTags().isEmpty()) {
                    System.out.println(blog.getTitle() + " has " + blog.getTags().size() + " tags");
                }
            });
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\nWith Optional (safe):");
        blogs.forEach(blog -> {
            Optional<List<String>> tags = Optional.ofNullable(blog.getTags());
            
            // Safe access with Optional
            String message = tags
                .filter(tagList -> !tagList.isEmpty())
                .map(tagList -> blog.getTitle() + " has " + tagList.size() + " tags")
                .orElse(blog.getTitle() + " has no tags");
            
            System.out.println(message);
        });
        
        // Finding first blog with specific tag using Optional
        System.out.println("\nFinding first blog with 'java' tag:");
        Optional<Blog> javaBlog = blogs.stream()
            .filter(blog -> Optional.ofNullable(blog.getTags())
                    .map(tags -> tags.contains("java"))
                    .orElse(false))
            .findFirst();
        
        javaBlog.ifPresentOrElse(
            blog -> System.out.println("Found: " + blog.getTitle()),
            () -> System.out.println("No blog with 'java' tag found")
        );
    }
} 