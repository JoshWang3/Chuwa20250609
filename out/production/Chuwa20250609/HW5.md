# HW5: ava 8 Features
@ Jun 19, 2025 _Gloria Wang_

## 1. Stream API
### 1. Find top 3 longest strings that start with a vowel
> Demo code can be checked in Top3LongestVowelStrings package

```Java
package Top3LongestVowelStrings;

import java.util.*;
import java.util.stream.*;

public class Top3LongestVowelStrings.Top3LongestVowelStrings {

    // Method to find Vowel String
    private static boolean isVowel(String s) {
        if (s == null || s.length() == 0) return false;
        char firstChar = Character.toLowerCase(s.charAt(0));
        return firstChar == 'a' || firstChar == 'e' || firstChar == 'i' || firstChar == 'o' || firstChar == 'u';
    }

    public static void main(String[] args) {
        List<String> input = Arrays.asList("apple", "dog", "burger", "cat");

        // Stream API to get top 3 longest strings with vowel
        List<String> result = input.stream()
                .filter(Top3LongestVowelStrings::isVowel)
                .sorted(Comparator.comparingInt(String::length).reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(result);

    }
}

// output: [apple]
```
### 2. Return names of departments where average employee salary > 100,000
> Demo code can be checked in DepartmentAverageEmployeeSalary package

#### Department Class 
```Java
package DepartmentAverageEmployeeSalary;

public class Department {
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }
}
```
#### Employee Class
```Java
package DepartmentAverageEmployeeSalary;

public class Employee {
    private String name;
    private double salary;
    private Department department;

    public Employee(String name, double salary, Department department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }
    public Department getDepartment() { return department; }
}
```

#### Main DepartmentAverageEmployeeSalary Class
```Java
package DepartmentAverageEmployeeSalary;
import java.util.*;
import java.util.stream.*;

public class DepartmentAverageEmployeeSalary {

    public static void main(String[] args) {
        Department fryCook = new Department("Fry Cook");
        Department janitorial = new Department("Janitorial");
        Department cashier = new Department("Cashier");
        Department management = new Department("Management");
        Department science = new Department("Science");
        Department education = new Department("Education");


        List<Employee> employees = Arrays.asList(
                new Employee("SpongeBob", 95000, fryCook),
                new Employee("Patrick", 80000, janitorial),
                new Employee("Squidward", 105000, cashier),
                new Employee("Mr. Krabs", 160000, management),
                new Employee("Sandy", 120000, science),
                new Employee("Plankton", 99000, science),
                new Employee("Pearl", 100500, management),
                new Employee("Mrs. Puff", 115000, education)
        );

        List<String> result = employees.stream()
                // group each employee by department and for each group calculate average salary
                .collect(Collectors.groupingBy(e -> e.getDepartment().getName(), Collectors.averagingDouble(Employee::getSalary)))
                .entrySet().stream() // convert Map's set of key-value pair into a new Stream
                .filter(entry -> entry.getValue() > 100000) // keep average salary > 100000
                .map(Map.Entry::getKey) // transforms each filters entry into its key
                .collect(Collectors.toList());

        System.out.println(result);
    }
}

// output: [Education, Science, Management, Cashier]
```

### 3. Get a sorted list of all unique tags from a list of blog posts (Blog to Tags is 1-to-many relationship)
> Demo code can be checked in BlogPosts package
#### Blog
```Java
package BlogPosts;

import java.util.List;

public class Blog {
    private String title;
    private List<String> tags;

    public Blog(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public String getTitle() { return title; }
    public List<String> getTags() { return tags; }
}
```

#### UniqueTagBlogPosts Main Class
```Java
package BlogPosts;

import java.util.*;
import java.util.stream.*;

public class UniqueTagBlogPosts {
    public static void main(String[] args) {

        List<Blog> blogs = Arrays.asList(
                new Blog("SpongeBob’s Secret Krabby Patty Recipe",
                        Arrays.asList("spongebob", "recipe", "burger", "krabbypatty", "food")
                ),
                new Blog( "Who Earns the Most? A Deep Dive into Bikini Bottom Employee Salaries",
                        Arrays.asList("spongebob", "salary", "bikinibottom", "jobs", "funfacts")
                ),
                new Blog( "How to Be a Model Employee: Lessons from SpongeBob",
                        Arrays.asList("spongebob", "career", "employee", "workethic", "motivation")
                ),
                new Blog(
                        "Inside Bikini Bottom: Where Does Everyone Live?",
                        Arrays.asList("spongebob", "bikinibottom", "homes", "realestate", "community")
                )
        );

        List<String> uniqueTags = blogs.stream()
                .flatMap(blog -> blog.getTags().stream()) // flatten all tag lists into one stream of tags
                .distinct() // remove duplicates
                .sorted() // sorted tags
                .collect(Collectors.toList());

        System.out.println(uniqueTags);
    }
}

// output: [bikinibottom, burger, career, community, employee, food, funfacts, homes, jobs, krabbypatty, motivation, realestate, recipe, salary, spongebob, workethic]
```

### 4. Return top 5 words by frequency from a paragraph
> Demo code can be checked in Top5Words package

```Java
package Top5Words;

import java.util.*;
import java.util.stream.*;

public class Top5Words {
    public static void main(String[] args) {
        String paragraph = "One sunny day in Bikini Bottom, SpongeBob SquarePants and his best friend, Patrick Star, decided to go jellyfishing. They grabbed their nets and headed to Jellyfish Fields, excited for a fun day of catching jellyfish.\n" +
                "\n" +
                "“Ready, Patrick?” SpongeBob asked, his eyes sparkling with joy.\n" +
                "\n" +
                "“Ready! Let’s catch the biggest jellyfish ever!” Patrick replied, bouncing with excitement.\n" +
                "\n" +
                "They ran through the fields, their nets swishing in the air. They laughed and shouted as they chased colorful jellyfish, their giggles echoing through the underwater landscape. SpongeBob caught a big, purple jellyfish and cheered, “Look, Patrick! I got one!”\n" +
                "\n" +
                "“Wow! That’s so cool!” Patrick said, clapping his hands. “Let’s catch more!”\n" +
                "\n" +
                "The two friends spent hours catching jellyfish, jumping around, and playing tag. They watched as jellyfish danced in the water, their tentacles waving like ribbons. After a long day of fun, SpongeBob and Patrick decided to head back to the Krusty Krab for some delicious Krabby Patties.\n" +
                "\n" +
                "“I can’t wait to eat!” SpongeBob said, his tummy rumbling." +
                "“Me too! I’m super hungry!” Patrick agreed.\n" +
                "\n" +
                "As they walked back, they chatted about their favorite jellyfish and how they would tell stories to their friends. But when they reached the Krusty Krab, something strange was happening. The doors were locked, and there was no one inside." +
                "“Where is everyone?” SpongeBob wondered, scratching his head.\n" +
                "\n" +
                "Suddenly, they spotted Mr. Krabs looking worried. “SpongeBob! Patrick! You won’t believe what happened! Plankton stole the secret Krabby Patty recipe!”" +
                "“Oh no!” SpongeBob exclaimed. “We have to get it back!”";

        // split paragraph into words (lowercase, remove punctuation)
        String[] words = paragraph.toLowerCase().replaceAll("[^a-z]", " ").split("\\s+");

        // count frequency
        Map<String, Long> wordCounts = Arrays.stream(words)
                .filter(w -> !w.isEmpty()) // removes any empty strings
                .collect(Collectors.groupingBy(w -> w, Collectors.counting())); // groups the words in the stream by the word itself 

        // sort by frequency get top 5
        List<String> top5words = wordCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())) // sort from high freq -> low freq
                .limit(5) // top 5
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(top5words);
    }
}

// output: [they, the, spongebob, jellyfish, and]
```
### 5. Group products by category and sort each group by price descending
> Demo code can be checked in ProductGroupSortedByPrice package

#### Product Class
```Java
package ProductGroupedByPrice;

public class Product {
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

    @Override
    public String toString() {
        return name + "($" + price + ")";
    }
}
```

#### ProductGroupSortedByPrice

```Java
package ProductGroupedByPrice;

import java.util.*;
import java.util.stream.*;

public class ProductGroupSortedByPrice {
    public static void main(String[] args) {

        List<Product> products = Arrays.asList(
                new Product("Krabby Patty", "Food", 2.99),
                new Product("Kelp Shake", "Drink", 3.49),
                new Product("Chum Burger", "Food", 1.99),
                new Product("Soda", "Drink", 1.49),
                new Product("Golden Spatula", "Merch", 25.00),
                new Product("Bikini Bottom Hat", "Merch", 15.00),
                new Product("Coral Bits", "Food", 3.79)
        );

        Map<String, List<Product>> groupedByPrice = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                                        .collect(Collectors.toList())
                        )
                ));

        groupedByPrice.forEach((category, productList) -> {
            System.out.println(category + ":" + productList);
        });
    }
}

/*
output:
    Merch:[Golden Spatula($25.0), Bikini Bottom Hat($15.0)]
    Drink:[Kelp Shake($3.49), Soda($1.49)]
    Food:[Coral Bits($3.79), Krabby Patty($2.99), Chum Burger($1.99)]
 */
 ```

## 2. How Optional helps prevent null pointer exception
> Demo code can be checked in Optional package

#### Without Optional
- have to manually check if tags is null before calling `tags.size()`
- if we forget this check and just write `blog.getTags().size()` -> could get a `NullPointerException` if tags is `null`

#### With Optional
- `Optional.ofNullable(blog.getTags())` wraps the possibly-null tags list in an Optional
- if tags is `null`, `.map(...)` is skipped and goes straight to `.orElse(0)`, providing a default value

```Java
package Optional;

import BlogPosts.Blog;
import java.util.*;

public class BlogTagsDemo {
    public static void main(String[] args) {
        Blog blog = new Blog("SpongeBob", null);

        // without optional: -> might NPE if forget manually check
        int tagCount = 0;
        List<String> tags = blog.getTags();
        if (tags != null) {
            tagCount = tags.size();
        }
        System.out.println("Number of tags without Optional: " + tagCount); // output: Number of tags without Optional: 0


        // with optional:
        int tagCountOptional = Optional.ofNullable(blog.getTags())
                .map(List::size)
                .orElse(0);

        System.out.println("Number of tags with Optional: " + tagCountOptional); // output: Number of tags with Optional: 0
    }
}
```
#### More Example Use Product Category to Demo
```Java
```