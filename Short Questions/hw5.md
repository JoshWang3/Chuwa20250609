1. Solve following questions using Stream API, you should write complete code context rather than a
    single Stream API statement (please also write necessary POJO classes or helper code):
    1. Find top 3 longest strings that start with a vowel
```java
import java.util.*;
import java.util.stream.Collectors;

public class Top3LongestString {
    public static void main(String[] args) {
        String[] strings = {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape"};
        
        List<String> str = Arrays.asList(strings);
        List<String> top3Longest = str.stream().filter(s -> s.matches("[aeiouAEIOU].*"))    // Filter strings starting with a vowel
                                       .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length())) // Sort by length in descending order
                                       .limit(3) // Limit to top 3 longest strings
                                       .collect(Collectors.toList()); // Collect to a list
        System.out.println("Top 3 longest strings starting with a vowel: " + top3Longest);
    }    
}

```
    2. Return names of departments where average employee salary > 100,000
```java


public class Employee {
    private String name;
    private int salary;
    
    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
    public String getName() {
        return name;
    }   
    public int getSalary() {
        return salary;
    }
    
}

import java.util.*;

public class Department {
    private String name;
    private List<Employee> employees = new ArrayList<>();

    public Department(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public int getTotalSalary() {
        int total = 0;
        for (Employee employee : employees) {
            total += employee.getSalary();
        }
        return total;
    }    
}


import java.util.*;
import java.util.stream.Collectors;

class FindDept {
    
    public static void main(String[] args) {
        List<Department> departments = Arrays.asList(
            new Department("HR", Arrays.asList(new Employee("Alice", 90000), new Employee("Bob", 110000), new Employee("Eve", 95000))),
            new Department("Engineering", Arrays.asList(new Employee("Charlie", 1200000), new Employee("David", 130000), new Employee("Frank", 105000))),
            new Department("Sales", Arrays.asList(new Employee("Grace", 115000), new Employee("Heidi", 125000), new Employee("Ivan", 98000)))
        );
        // Find departments with average salary greater than 100000
        List<String> depts = departments.stream()
                                .filter(dept -> dept.getEmployees().stream() 
                                .mapToInt(Employee::getSalary)
                                .average()   
                                .orElse(0)> 100000) // Use mapToInt to get the average salary
                                .map(Department::getName) // Map to department names
                                .collect(Collectors.toList()); // Collect department names into a list
        System.out.println("Departments with average salary greater than 100000: " + depts);
    }
}


```
    3. Get a sorted list of all unique tags from a list of blog posts (Blog to Tags is 1-to-many relationship)
```java

import java.util.ArrayList;
import java.util.List;

class Blog {

    
    private String title;
    private List<String> tags = new ArrayList<>();

    public Blog() {
    }

    public Blog(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTags() {
        return tags;
    }
}

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class getUniqueTags {

    public static void main(String[] args) {
        List<Blog> blogs = Arrays.asList(
            new Blog("Post 1", Arrays.asList("Java", "Programming", "Streams")),
            new Blog("Post 2", Arrays.asList("Programming", "Tutorial", "Java")),
            new Blog("Post 3", Arrays.asList("Tutorial", "Streams", "Java"))
        );

        List<String> uniqueTags = blogs.stream().flatMap(blog -> blog.getTags().stream()).distinct().sorted().collect(Collectors.toList());
        
        System.out.println("Unique tags: " + uniqueTags);
    }
}


```
    4. Return top 5 words by frequency from a paragraph
```java

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Top5WordsByFrequency {

    
    public static void main(String[] args) {
        String text = "I taught her to lie before she could spell her name. Told her people were soft, stupid, and always looking for a story that made them feel good about parting with money. Back then, our narrative was the plain truth: an amputee raising his kid on his own. Before long, we noticed how different people responded to different aspects of our lives. Our narrative then developed into a collection of short stories we kept at the ready. I curated those tales, and she delivered them with big eyes and borrowed grief. ";

        List<String> words = Arrays.stream(text.toLowerCase().split("\\W+"))
                .collect(Collectors.groupingBy(word -> word, Collectors.counting())).entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("Top 5 words by frequency: " + words);
    }
}


```
    5. Group products by category and sort each group by price descending
```java


class Product {
    private String name;
    private double price;
    private String category;

    public Product() {
    }

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}


import java.util.*;
import java.util.stream.Collectors;

class getProducts {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 1200.00, "Electronics"),
            new Product("Smartphone", 800.00, "Electronics"),
            new Product("Tablet", 300.00, "Electronics"),
            new Product("Headphones", 150.00, "Accessories"),
            new Product("Smartwatch", 200.00, "Accessories"),
            new Product("Bluetooth Speaker", 100.00, "Accessories"),
            new Product("Camera", 500.00, "Electronics"),
            new Product("Printer", 250.00, "Electronics"),
            new Product("Monitor", 300.00, "Electronics")
        );

        Map<String, List<Product>> productsByCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory, 
                Collectors.collectingAndThen(
                    Collectors.toList(), 
                    list -> list.stream()
                            .sorted(Comparator.comparingDouble(Product::getPrice))
                            .collect(Collectors.toList())
                )));
        

            productsByCategory.forEach((category, productList) -> {
            System.out.println(category + " : " + productList.stream()
                .map(product -> product.getName() + "$ " + product.getPrice())
                .collect(Collectors.joining(", ")));
            });
        }

}
```
1. Write code snippet to explain how Optional helps prevent null pointer exception, you may use blog-tags,
and product-category POJOs to demo.
```java

import java.util.List;
import java.util.Optional;

class Blog {

    
    private String title;
    private List<String> tags;

    public Blog() {
    }

    public Blog(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public Optional<List<String>> getTags() {
        return Optional.ofNullable(tags);
    }
}


import java.util.*;

class OptionalExample {

    
    public static void main(String[] args) {
        Blog blog = new Blog("Java Streams", null);

        // Avoid NullPointerException using Optional
        List<String> tags = blog.getTags()
            .orElse(Collections.emptyList()); // Provide a default value if tags are null

        System.out.println("Tags: " + tags);
    }
}


```

1. Explain why Java Stream API is required, how does it help on data processing?

    1. Stream AI helps to write concise and readable code for data manipullation
    2. Streams support parallelism
    3. Encorages the use of functional progamming concept like lambda expression
    4. Stream support chaining operations, making complex data tranformations easier to implement and save more storage room
    5. Stream eliminates the need for verbose loops and conditional logic.

    How?
    1. data transformation: streams provide method like map(), flatMap() to transform data easily
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
List<String> upperNames = names.stream()
                               .map(String::toUpperCase)
                               .collect(Collectors.toList());
System.out.println(upperNames); // [ALICE, BOB, CHARLIE]


```
    2. Filtering data
```java

List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> evenNumbers = numbers.stream()
                                   .filter(n -> n % 2 == 0)
                                   .collect(Collectors.toList());
System.out.println(evenNumbers); // [2, 4]

```
3. Sotring

```java

List<String> fruits = Arrays.asList("Banana", "Apple", "Orange");
List<String> sortedFruits = fruits.stream()
                                  .sorted()
                                  .collect(Collectors.toList());
System.out.println(sortedFruits); // [Apple, Banana, Orange]

```

4. Reducing

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
int sum = numbers.stream()
                 .reduce(0, Integer::sum);
System.out.println(sum); // 15


```
