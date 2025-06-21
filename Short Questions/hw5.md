### 1. Solve the following questions using Stream API, you should write complete code context rather than a single Stream API statement (please also write necessary POJO classes or helper code):

##### (1) Find top 3 longest strings that start with a vowel
```java
import java.util.stream.*;

public class VowelStringsFinder {
    
    public static void main(String[] args) {
        // Sample data
        List<String> strings = Arrays.asList("the", "world", "should", "task", "you", "to", "recite,", "What", "merit", 
                "For", "you", "in", "me", "can", "nothing", "worthy", "prove", "Unless", "you", "would", "devise", 
                "And", "hang", "more", "praise", "upon", "deceased", "I", "Than", "niggard", "truth",  "willingly"
        );
        
        List<String> top3LongestVowelStrings = strings.stream()
            .filter(s -> "aeiouAEIOU".indexOf(s.charAt(0)) != -1)  
            .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length()))  
            .limit(3)  
            .collect(Collectors.toList());
        
        System.out.println("Input strings: " + strings);
        
        System.out.println("Strings starting with vowel (sorted by length):");
        strings.stream()
            .filter(s -> "aeiouAEIOU".indexOf(s.charAt(0)) != -1)
            .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length()))
            .forEach(s -> System.out.println("  " + s + " (length: " + s.length() + ")"));
        
        System.out.println();
        System.out.println("Top 3 longest: " + top3LongestVowelStrings);
    }
}
```


##### (2)Return names of departments where average employee salary > 100,000
```java
import java.util.stream.*;

public class DepartmentSalaryAnalyzer {
    
    // Employee POJO
    static class Employee {
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
            return String.format("Employee{name='%s', department='%s', salary=$%.2f}", 
                               name, department, salary);
        }
    }
    
    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
            new Employee("A A", "Engineering", 250000),
            new Employee("B B", "Engineering", 160000),
            new Employee("C C", "Engineering", 73000),
            new Employee("D D", "Sales", 169000),
            new Employee("E E", "Sales", 100000),
            new Employee("F F", "Sales", 70000),
            new Employee("G G", "HR", 96000),
            new Employee("H H", "HR", 650000),

        );
        
        // Calculate average salary by department and filter > 100,000
        List<String> highPayingDepartments = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment,
                     Collectors.averagingDouble(Employee::getSalary)))
            .entrySet().stream()
            .filter(entry -> entry.getValue() > 100000)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        
        System.out.println(" Departments with Average Salary > $100,000");
        System.out.println("All employees:");
        employees.forEach(System.out::println);
        
        System.out.println("\nSalary analysis by department:");
        Map<String, Double> avgSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment,
                     Collectors.averagingDouble(Employee::getSalary)));
        
        avgSalaryByDept.forEach((dept, avgSalary) -> 
            System.out.printf("  %s: $%.2f%s%n", 
                            dept, avgSalary, 
                            avgSalary > 100000 ? " ✓" : ""));
        
        System.out.println("\nDepartments with average salary > $100,000:");
        System.out.println(highPayingDepartments);
    }
}
```
##### (3)Get a sorted list of all unique tags from a list of blog posts (Blog to Tags is 1-to-many relationship)
flatMap() -- flattens each post’s List<String> into one continuous stream of tag strings.  
distinct() -- removes any duplicates.  
sorted() -- orders them alphabetically.  
collect() -- gathers the final stream into a List<String>.  
```java

import java.util.*;
import java.util.stream.*;

class BlogPost {
    private final String blogName;
    private final List<String> tags;
    public BlogPost(String blogName, List<String> tags) {
        this.blogName = blogName;
        this.tags  = tags;
    }
    public List<String> getTags() { return tags; }
    @Override
    public String toString() {
        return title + " → " + tags;
    }
}

public class UniqueTagsExample {
    public static void main(String[] args) {
        // 1. Prepare some sample posts, each with 1–many tags
        List<BlogPost> posts = Arrays.asList(
                new BlogPost("Post 1", Arrays.asList("java", "spring")),
                new BlogPost("Post 2", Arrays.asList("java", "kotlin")),
                new BlogPost("Post 3", Arrays.asList("spring", "hibernate"))
        );

        // 2. Extract, dedupe, sort, and collect tags
        List<String> sortedUniqueTags = posts.stream()
                .flatMap(p -> p.getTags().stream())   // flatten all tag lists
                .distinct()                           // remove duplicates
                .sorted()                             // sort alphabetically
                .collect(Collectors.toList());        // gather into a List

        // 3. Print out the result
        System.out.println("Sorted unique tags:" + sortedUniqueTags);
    }
}


```
##### (4) Return top 5 words by frequency from a paragraph
```java
import java.util.*;
import java.util.stream.*;

public class TopWordsExample {
    public static void main(String[] args) {
        // 1. Sample paragraph
        String paragraph =
                "Java streams are cool. Streams let you process data in a functional style. " +
                        "Java developers love streams because streams are powerful.";

        // 2. Compute top 5 words by frequency
        List<String> top5 = Arrays.stream(paragraph.toLowerCase().split("\\W+"))
                // drop any empty tokens
                .filter(word -> !word.isEmpty())
                // count occurrences
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                // stream over Map entries
                .entrySet().stream()
                // sort by count desc
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                        // tie-break by word asc
                        .thenComparing(Map.Entry.comparingByKey()))                            
                .limit(5)
                // extract the word
                .map(Map.Entry::getKey)
                // collect into a List
                .collect(Collectors.toList());                                             

        // 3. Print results
        System.out.println("Paragraph: " + paragraph);
        System.out.println("Top 5 words by frequency: " + top5);
    }
}

```
##### (5) Group products by category and sort each group by price descending
```java
import java.util.*;
import java.util.stream.*;

public class ProductGroupingExample {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Espresso Machine", "Home Appliances", 249.99),
                new Product("Blender", "Home Appliances", 89.99),
                new Product("OLED TV", "Electronics", 1299.99),
                new Product("Gaming Laptop", "Electronics", 1599.99),
                new Product("Running Shoes", "Footwear", 119.99),
                new Product("Leather Boots", "Footwear", 179.99)
        );

        // Start a stream over the products list
        Map<String, List<Product>> grouped = products.stream()
                // Collect items into a Map keyed by category
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        // After grouping, sort each list by price descending
                        Collectors.collectingAndThen(
                                // first, gather into a List<Product>
                                Collectors.toList(),
                                // then stream that list
                                list -> list.stream()
                                        // sort the stream
                                        .sorted(                       
                                                Comparator
                                                        .comparingDouble(Product::getPrice)
                                                        .reversed()           // in reverse (highest first)
                                        )
                                        // collect sorted items back into a List<Product>
                                        .collect(Collectors.toList()) 
                        )
                ));

        // Print each category with its sorted products
        grouped.forEach((category, list) ->
                System.out.println(category + ": " + list)
        );
    }
}

class Product {
    private final String name;
    private final String category;
    private final double price;

    public Product(String name, String category, double price) {
        this.name  = name;
        this.category = category;
        this.price = price;
    }

    public String getCategory() { return category; }
    public double getPrice()    { return price; }

    @Override
    public String toString() {
        return name + "($" + price + ")";
    }
}

```
### 2. Write code snippet to explain how Optional helps prevent null pointer exception, you may use blog-tags, and product-category POJOs to demo.
```java
import java.util.*;
import java.util.stream.*;

public class OptionalNullSafetyDemo {

    // BlogPost POJO: tags list might be null
    static class BlogPost {
        private final String title;
        private final List<String> tags;  // could be null

        public BlogPost(String title, List<String> tags) {
            this.title = title;
            this.tags  = tags;
        }
        public List<String> getTags() { return tags; }
        @Override
        public String toString() {
            return title + " → " + tags;
        }
    }

    // Product POJO: category might be null
    static class Product {
        private final String name;
        private final String category;  // could be null

        public Product(String name, String category) {
            this.name     = name;
            this.category = category;
        }
        public String getCategory() { return category; }
        @Override
        public String toString() {
            return name + " (" + category + ")";
        }
    }

    public static void main(String[] args) {
        // 1. Sample data with some nulls
        List<BlogPost> posts = Arrays.asList(
            new BlogPost("Intro to Java", Arrays.asList("java", "basics")),
            new BlogPost("Missing Tags Post", null),                  
            new BlogPost("Streams Guide", Arrays.asList("streams"))
        );
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics"),
            new Product("Mystery Item", null)                         
        );

        List<String> allTags = posts.stream()
                // get the tags list (might be null)
                .map(BlogPost::getTags)
                // wrap in Optional and default to empty list if null
                .map(tags -> Optional.ofNullable(tags).orElse(Collections.emptyList()))
                // flatten lists into one stream of tag strings
                .flatMap(List::stream)
                // remove duplicate tags
                .distinct()
                // collect into a List<String>
                .collect(Collectors.toList());

        // output the null-safe tag list
        System.out.println("All tags (null-safe): " + allTags);

        // for each product, obtain category or fallback if null
        products.forEach(p -> {
            // wrap category in Optional, supply default if null
            String category = Optional.ofNullable(p.getCategory())
                    .orElse("Miscellaneous");
            // print product with resolved category
            System.out.println(p + " → category = " + category);
        });
    }
}

```
### 3. Explain why Java Stream API is required, how does it help on data processing?
(1) Pipeline of Functions
- Chaining operations: Streams let you link multiple transformations into a single pipeline.  
- Lazy evaluation: The pipeline can optimize, fuse steps, or short-circuit (e.g. limit(3)) without processing the entire data set.

(2) Readability & Maintainability:   
    Fewer lines of code: No explicit loops or temporary collections.  

(3) Can be parallelized: 
- Simply switch to parallelStream() and the same pipeline can run across multiple CPU cores.  
- The framework handles thread-safety of the pipeline’s internal data structures, so you don’t have to manage threads directly.

(4) No Storage：a stream does not keep its intermediate results in collections or buffers. Instead, each element is pulled through the pipeline and processed on-the-fly—filter, map, sort, etc.—without building temporary lists or arrays.

(5) Can be infinite: o a stream’s ability to represent an unbounded sequence of elements without ever storing them all in memory up front. Because streams are lazy, can build sources like:
```java
Stream<Long> naturals = Stream.iterate(1L, n -> n + 1);
Stream<Double> randoms  = Stream.generate(Math::random);
```
Neither of these ever precomputes or buffers every element—instead, each value is produced on demand as you pull from the stream. You then use short-circuiting terminal operations (e.g. .limit(100), .findFirst()) to consume just as many items as you need. This lets you work with conceptually infinite data sources (all natural numbers, an endless random sequence, etc.) without running out of memory.

(6) Can be created from collections, arrays, Files Lines, Methods in Stream, IntStream etc.  
By decoupling data source from processing logic, can apply the same pipeline of operations (filter, map, reduce, collect, etc.) to lists, arrays, file lines, numeric ranges, or dynamically generated values, which dramatically simplifies and unifies your data-processing code.

##### How does it help on data processing?  
A: By expressing data transformations as composable pipelines of declarative operations (filter, map, flatMap, sorted, collect, etc.), the Stream API lets you process collections, arrays, file lines, or even infinite sequences without boilerplate loops or mutable accumulators; its lazy evaluation and short-circuiting ensure you only do the minimal work needed, and with a simple switch to parallelStream() you can transparently leverage multiple CPU cores, making your data-processing logic both more concise and more performant.
```java
```

```java
```

```java
```