# HW5 - Java 8

---

## 1. Solve following questions using Stream API, you should write complete code context rather than a single Stream API statement (please also write necessary POJO classes or helper code):

### 1.1 Find top 3 longest strings that start with a vowel

```java
package hw5;

import java.util.Arrays;
import java.util.List;

/**
 *  Find top 3 longest strings that start with a vowel.
 */
public class Top3LongestStrings {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("apple", "orange", "banana", "Umbrella", "honey", "egg", "sun", "icecream", "ice", "owl", "grape", "lemon");

        List<String> top3LongestVowelStarts = input.stream()
                .filter(s -> s.matches("(?i)^[aeiou].*"))
                .sorted((a, b) -> Integer.compare(b.length(), a.length()))
                .limit(3)
                .toList();
        System.out.println(top3LongestVowelStarts);

    }
}

```
---

### 1.2 Return names of departments where average employee salary > 100,000

- POJO: Employee
```java
package hw5;

public class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}


```

- POJO: Department
```java
package hw5;


import java.util.List;

public class Department {
    private String deptName;
    private List<Employee> employees;

    public Department(String deptName, List<Employee> employees) {
        this.deptName = deptName;
        this.employees = employees;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptName='" + deptName + '\'' +
                ", employees=" + employees +
                '}';
    }
}

```

- Department Filter Demo
```java
package hw5;

import java.util.Arrays;
import java.util.List;

/**
 * Return names of departments where average employee salary > 100,000
 */
public class DepartmentFilter {
    public static void main(String[] args) {
        List<Department> deptList = List.of(
                new Department("Engineering", Arrays.asList(
                        new Employee("Alice", 120000),
                        new Employee("Bob", 110000)
                )),
                new Department("Sales", Arrays.asList(
                        new Employee("Helen", 150000),
                        new Employee("John", 100000),
                        new Employee("Michael", 80000)
                )),
                new Department("HR", Arrays.asList(
                        new Employee("Mike", 65000),
                        new Employee("Linda", 80000)
                )),
                new Department("Finance", Arrays.asList(
                        new Employee("Jessy", 95000),
                        new Employee("Crystal", 85000)
                ))
        );

        List<String> result = deptList.stream()
                .filter(d -> d.getEmployees().stream()
                        .mapToDouble(Employee::getSalary)
                        .average()
                        .orElse(0) > 100000)
                .map(Department::getDeptName)
                .toList();

        System.out.println("Departments with average salary > 100,000: " + result);
    }

}

```

---

### 1.3 Get a sorted list of all unique tags from a list of blog posts (Blog to Tags is 1-to-many relationship)

- POJO: BlogPost
 ```java
package hw5;

import java.util.List;

public class BlogPost {
    private String title;
    private List<String> tags;

    public BlogPost(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "title='" + title + '\'' +
                ", tags=" + tags +
                '}';
    }
}

```

- Blog Tags Extractor Demo
```java
package hw5;

import java.util.Arrays;
import java.util.List;

/**
 * Get a sorted list of all unique tags from a list of blog posts
 */
public class BlogTagsExtractor {
    public static void main(String[] args) {
        List<BlogPost>  blogPosts = Arrays.asList(
                new BlogPost("Java Streams", Arrays.asList("Java Streams", "Java 8", "Backend", "Java")),
                new BlogPost("Spring Boot Guide", Arrays.asList("Java", "Spring", "Backend")),
                new BlogPost("Frontend Tips", Arrays.asList("Javascript", "CSS", "HTML"))
        );

        List<String> uniqueSortedTags = blogPosts.stream()
                .flatMap(blogPost -> blogPost.getTags().stream())
                .distinct()
                .sorted()
                .toList();

        System.out.println("Sorted unique tags: " + uniqueSortedTags);
        // Sorted unique tags: [Backend, CSS, HTML, Java, Java 8, Java Streams, Javascript, Spring]
    }
}

```
---

### 1.4 Return top 5 words by frequency from a paragraph
```java
package hw5;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Return top 5 words by frequency from a paragraph
 */
public class Top5FrequentWords {
    public static void main(String[] args) {
        String demoParagraph = "Earlier this year, Dries Buytaert announced the Starshot Initiative, a bold vision for the future of Drupal. This initiative aims to enhance the ‘Drupal CMS’ by including a set of useful and common modules, offering an improved site-building experience for ambitious site builders.\n" +
                "A key part of this initiative is the development of the Experience Builder, a new layout engine that will revolutionise the way pages are built with layouts and Paragraphs.\n" +
                "As a Certified Drupal Supplier and a supporter of the Drupal Association, Morpht recognises the significance of the Starshot initiative. We've pledged financial support to the DA to help advance these groundbreaking developments, which we believe will have a lasting impact on the Drupal community.";

        List<String> top5FrequentWords = Arrays.stream(demoParagraph.toLowerCase().split("\\W+"))
                .filter(word -> !word.isBlank())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .toList();

        System.out.println("Top 5 frequent words: " + top5FrequentWords);
        // Top 5 frequent words: [the, a, of, drupal, initiative]
    }

}

```
---

### 1.5 Group products by category and sort each group by price descending
- POJO: Product
```java
package hw5;

public class Product {
    private String productName;
    private String category;
    private double price;

    public Product(String productName, String category, double price) {
        this.productName = productName;
        this.category = category;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}

```

- Demo
```java
package hw5;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Group products by category and sort each group by price descending
 */
public class ProductGrouper {
    public static void main(String[] args) {
        List<Product> productList = Arrays.asList(
                new Product("iPhone", "Electronics", 999.0),
                new Product("Samsung TV", "Electronics", 1499.0),
                new Product("Banana", "Groceries", 0.99),
                new Product("Apple", "Groceries", 1.29),
                new Product("Macbook pro", "Electronics", 1200.0),
                new Product("Bread", "Groceries", 2.49),
                new Product("T-Shirt", "Clothing", 19.99),
                new Product("Jacket", "Clothing", 89.99),
                new Product("Jeans", "Clothing", 49.99)
        );

        // key: category
        // value: product list sorted my price descending
        Map<String, List<Product>> groupedProducts = productList.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory, Collectors.collectingAndThen(
                                Collectors.toList(), list -> list.stream()
                                        .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                                        .toList()
                        )
                ));

        groupedProducts.forEach((category, products) -> {
            System.out.println("Category: " + category);
            products.forEach(System.out::println);

        });

        //Category: Groceries
        //Product{productName='Bread', category='Groceries', price=2.49}
        //Product{productName='Apple', category='Groceries', price=1.29}
        //Product{productName='Banana', category='Groceries', price=0.99}
        //Category: Clothing
        //Product{productName='Jacket', category='Clothing', price=89.99}
        //Product{productName='Jeans', category='Clothing', price=49.99}
        //Product{productName='T-Shirt', category='Clothing', price=19.99}
        //Category: Electronics
        //Product{productName='Samsung TV', category='Electronics', price=1499.0}
        //Product{productName='Macbook pro', category='Electronics', price=1200.0}
        //Product{productName='iPhone', category='Electronics', price=999.0}

    }
}

```
---

## 2. Write code snippet to explain how Optional helps prevent null pointer exception, you may use blog-tags, and product-category POJOs to demo.
```java
package hw5;

import java.util.Optional;

class Address {
    private String city;
    private String state;

    public Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

class Student {
    private String name;
    private Address address; // might be null
    public Student(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }
    // Return Optional wrapper to avoid null checks
    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }
}

public class OptionalWithStudentDemo {
    public static void main(String[] args) {
        Student s1 = new Student("Helen", new Address("Seattle", "WA"));
        Student s2 = new Student("Brian", null);

        // Safely access address and print city
        s1.getAddress()
                .map(Address::getCity)
                .ifPresent(city -> System.out.println(s1.getName() + "'s city: " + city));
        // Helen's city: Seattle

        // No crash, nothing is printed for s2
        s2.getAddress()
                .map(Address::getCity)
                .ifPresent(city -> System.out.println(s1.getName() + "'s city: " + city));
        

        // Provide fallback value if address or city is missing
        String city = s2.getAddress()
                .map(Address::getCity)
                .orElse("City is not available.");
        System.out.println(s2.getName() + "'s city: " + city);
        // Brian's city: City is not available.
    }


}

```

- Without `Optional`, accessing `student.getAddress().getCity()` directly could throw NullPointerException.

- Using Optional:
  - We avoid null checks like if `(student.getAddress() != null)`
  - Chain calls safely with `.map(...)`
  - Use `.orElse(...)` to provide defaults


---

## 3. Explain why Java Stream API is required, how does it help on data processing?
- Problem Before Streams, codes are:
  - ❌ Verbose and hard to read
  - ❌ Not declarative (too focused on how, not what)
  - ❌ Parallel processing is hard

- Stream API Helps:
  - ✅ Shorter and more readable
  - ✅ Focuses on what you want, not how to do it
  - ✅ Composable (chainable steps)
  - ✅ Supports parallelism (.parallelStream())



