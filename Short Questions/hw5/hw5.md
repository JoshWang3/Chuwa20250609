### 1. Solve following questions using Stream API.
**Write the necessary POJO classes and helper code rather than a single Stream API statement.**

> I pasted all code below, but question 1 source code is in `Coding/hw5/01-stream-api/src`.

---
#### 1.1. Find top 3 longest strings that start with a vowel.
```java
import java.util.*;
import java.util.stream.*;

public class VowelStringFilter {

    // Store only lowercase vowels
    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');

    public static void main(String[] args) {
        List<String> words = Arrays.asList(
                "Apple", "orange", "banana", "Umbrella", "Eagle", "igloo", "car", "owl", "ink", "airplane"
        );

        List<String> top3LongestVowelWords = words.stream()
                .filter(word -> !word.isEmpty() && isVowel(word.charAt(0)))
                .sorted((a, b) -> b.length() - a.length())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Top 3 longest vowel-starting words: " + top3LongestVowelWords);
    }

    // Convert input to lowercase before checking
    private static boolean isVowel(char ch) {
        return VOWELS.contains(Character.toLowerCase(ch));
    }
}
```

Output:  
```
Top 3 longest vowel-starting words: [airplane, Umbrella, orange]
```

---
#### 1.2. Return names of departments where average employee salary > 100,000.
```java
import java.util.*;
import java.util.stream.Collectors;

// Employee POJO
class Employee {
    private String name;
    private double salary;
    private String department;

    public Employee(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String getName() { return name; }

    public double getSalary() { return salary; }

    public String getDepartment() { return department; }
}

public class StreamDepartmentSalaryFilter {
    public static void main(String[] args) {
        // Sample data
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 120000, "Engineering"),
                new Employee("Bob", 90000, "Engineering"),
                new Employee("Charlie", 105000, "HR"),
                new Employee("David", 95000, "HR"),
                new Employee("Eva", 130000, "Finance"),
                new Employee("Frank", 115000, "Finance")
        );

        // Stream API to filter departments with avg salary > 100000
        List<String> result = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 100000)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("Departments with avg salary > 100,000: " + result);
    }
}
```
Output: 
```
Departments with avg salary > 100,000: [Engineering, Finance]
```


Note:  
```
`entrySet().stream()`	converts map entries to stream.
```

---
#### 1.3. Get a sorted list of all unique tags from a list of blog posts (Blog to Tags is 1-to-many relationship).
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

    public List<String> getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }
}

public class BlogTagProcessor {
    public static void main(String[] args) {
        List<BlogPost> blogPosts = Arrays.asList(
            new BlogPost("Java Streams", Arrays.asList("Java", "Streams", "Functional")),
            new BlogPost("Spring Boot", Arrays.asList("Spring", "Java", "Backend")),
            new BlogPost("Docker Basics", Arrays.asList("DevOps", "Docker", "Backend"))
        );

        List<String> sortedUniqueTags = blogPosts.stream()
            .flatMap(blog -> blog.getTags().stream())       // Flatten all tag lists
            .distinct()                                     // Remove duplicates
            .sorted()                                       // Sort alphabetically
            .collect(Collectors.toList());                  // Collect to List

        System.out.println("Sorted Unique Tags: " + sortedUniqueTags);
    }
}
```
Output: 
```
Sorted Unique Tags: [Backend, DevOps, Docker, Functional, Java, Spring, Streams]
```


---
#### 1.4. Return top 5 words by frequency from a paragraph.
```java
import java.util.*;
import java.util.stream.Collectors;

public class TopWordsByFrequency {
    public static void main(String[] args) {
        String paragraph = 
            "Two roads diverged in a yellow wood,\n" +
            "And sorry I could not travel both\n" +
            "And be one traveler, long I stood\n" +
            "And looked down one as far as I could\n" +
            "To where it bent in the undergrowth;";

        // Clean and split paragraph into words
        List<String> words = Arrays.stream(paragraph
                        .toLowerCase()                         // convert to lowercase
                        .replaceAll("[^a-z\\s]", "")           // remove punctuation
                        .split("\\s+"))                        // split by whitespace
                .collect(Collectors.toList());

        // Count frequency of each word and sort by frequency descending
        List<Map.Entry<String, Long>> topWords = words.stream()
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))  // sort by frequency desc
                .limit(5)                                                    // top 5
                .collect(Collectors.toList());

        // Print the top 5 words and their counts
        topWords.forEach(entry ->
                System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
```

Output:  
```
and: 3  
i: 3  
in: 2  
could: 2  
one: 2  
```

---
#### 1.5. Group products by category and sort each group by price descending.
```java
import java.util.*;
import java.util.stream.Collectors;

class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return name + " - $" + price;
    }
}

public class GroupProductsSimple {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Apple", "Fruits", 1.5),
                new Product("Banana", "Fruits", 1.0),
                new Product("Orange", "Fruits", 2.0),
                new Product("Carrot", "Vegetables", 1.2),
                new Product("Broccoli", "Vegetables", 2.5),
                new Product("Tomato", "Vegetables", 1.8)
        );

        Map<String, List<Product>> groupedSorted = products.stream()
                // Step 1: Group by category
                .collect(Collectors.groupingBy(Product::getCategory))  // Map<String, List<Product>>
                .entrySet()
                .stream()
                // Step 2: Sort each group by price descending
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // category name
                        entry -> entry.getValue().stream()
                                .sorted(Comparator.comparing(Product::getPrice).reversed())
                                .collect(Collectors.toList())  // sorted List<Product>
                ));

        // Print the result
        groupedSorted.forEach((category, list) -> {
            System.out.println("Category: " + category);
            list.forEach(System.out::println);
            System.out.println();
        });
    }
}
```

Output:  
```
Category: Fruits  
Orange - $2.0  
Apple - $1.5  
Banana - $1.0  
    
Category: Vegetables  
Broccoli - $2.5  
Tomato - $1.8  
Carrot - $1.2  
```



---
### 2. Write code snippet to explain how Optional helps prevent null pointer exception.   
**You may use blog-tags, and product-category POJOs to demo.**
> I pasted all code below, but question 2 source code is in `Coding/hw5/02-opetional/src`.

```java
import java.util.*;

class BlogPost {
    private String title;
    private List<String> tags;

    public BlogPost(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public Optional<List<String>> getTags() {
        return Optional.ofNullable(tags); // Prevents NPE
    }

    public String getTitle() {
        return title;
    }
}

class Product {
    private String name;
    private BlogPost blogPost;

    public Product(String name, BlogPost blogPost) {
        this.name = name;
        this.blogPost = blogPost;
    }

    public Optional<BlogPost> getBlogPost() {
        return Optional.ofNullable(blogPost); // Prevents NPE
    }

    public String getName() {
        return name;
    }
}

public class OptionalExample {
    public static void main(String[] args) {
        // Simulate missing blogPost or missing tags
        Product product = new Product("Laptop", null);

        // Traditional way (unsafe, may throw NullPointerException)
        // List<String> tags = product.getBlogPost().getTags(); ❌ NPE risk

        // Safe way using Optional
        List<String> tags = product.getBlogPost()
            .flatMap(BlogPost::getTags) // unwraps Optional<BlogPost> → Optional<List<String>>
            .orElse(Collections.emptyList()); // fallback to empty list

        System.out.println("Tags: " + tags);
    }
}
```
Output:  
```
Tags: [ ]
```


---
### 3. Explain why Java Stream API is required. How does it help on data processing?

**Why Java Stream API is required?**  
- **Declarative** - focuses on *what* to do, not *how*.
- **Chain multiple operations** (e.g., `filter → map → collect`)
- **Supports lazy eval & parallelism** (`parallelStream()`)
- **Doesn’t modify original data**


**How does it help on data processing?**

| Operation      | Purpose                         |
|----------------|----------------------------------|
| `filter()`     | Select matching elements         |
| `map()`        | Transform each element           |
| `collect()`    | Aggregate results                |
| `sorted()`     | Sort elements                    |
| `distinct()`   | Remove duplicates                |
| `groupingBy()` | Group by property                |

 