# 06/20

1. A stream is a sequence of elements that supports sequential and parallel operations from a collection/array. Intermediate operations include `filter()`, `map()`, `sorted()`. Terminal operations include `collect()`, `forEach()`, `reduce()`. It’s good for readable code, chain operations easily, parallel processing.
    - Top 3 longest strings that start with a vowel
        
        ```java
        import java.util.*;
        import java.util.stream.*;
        
        class TopThreeLongestStringsWithVowel {
        	public static List<String> findThreeStrings(List<String> words) {
        		List<String> result = words.stream()
                        .filter(word -> word.matches("(?i)^[aeiou].*"))
                        .sorted(Comparator.comparingInt(word -> ((String) word).length()).reversed())
                        .limit(3)
                        .collect(Collectors.toList());
                return result;
        	}
        	public static void main(String[] args) {
        		List<String> words = Arrays.asList("apple", "orange", "umbrella", "grape", "elephant", "ice", "avocado");
        		System.out.println(findThreeStrings(words));
        	}
        }
        ```
        
    - Departments with average salary > 100,000
        
        ```java
        import java.util.*;
        import java.util.stream.*;
        
        class Employee {
            private String name;
            private double salary;
            public Employee() {}
        
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
        }
        class Department {
            private String departName;
            private List<Employee> employees;
            public Department() {}
            public Department(String departName,  List<Employee> employees) {
                this.departName = departName;
                this.employees = employees;
            }
            public String getDepartName() {
                return departName;
            }
            public void setDepartName(String departName) {
                this.departName = departName;
            }
            public List<Employee> getEmployees() {
                return employees;
            }
            public void setEmployees(List<Employee> employees) {
                this.employees = employees;
            }
        }
        public class Demo {
            public static void main(String[] args) {
                List<Department> departments = List.of(
                        new Department("Engineering", List.of(new Employee("Eric", 203419), new Employee("Kate", 150000))),
                        new Department("HR", List.of(new Employee("Lucy", 50000.34), new Employee("Tom", 70468.99))),
                        new Department("Accounting", List.of(new Employee("Jack", 40000), new Employee("Alice", 70000.68))),
                        new Department("Marketing", List.of(new Employee("LuLu", 123000.33), new Employee("Tracy", 185000.57)))
                );
        
                List<String> highAvgSalaryDeparts = departments.stream()
                        .filter(depart -> depart.getEmployees().stream().mapToDouble(Employee::getSalary).average().orElse(0) > 100000.0)
                        .map(Department::getDepartName)
                        .collect(Collectors.toList());
        
                System.out.println(highAvgSalaryDeparts);
        
            }
        }
        ```
        
    - Sorted list of all unique tags from a list of blog posts
        
        ```java
        import java.util.*;
        import java.util.stream.*;
        
        class Post {
            private String title;
            private String content;
            private List<String> tags;
            public Post() {}
            public Post(String title, String content, List<String> tags) {
                this.title = title;
                this.content = content;
                this.tags = tags;
            }
            public String getTitle() {
                return title;
            }
            public void setTitle(String title) {
                this.title = title;
            }
            public String getContent() {
                return content;
            }
            public void setContent(String content) {
                this.content = content;
            }
            public List<String> getTags() {
                return tags;
            }
            public void setTags(List<String> tags) {
                this.tags = tags;
            }
        }
        public class BlogTags {
            public static void main(String[] args) {
                List<Post> posts = List.of(
                        new Post("Weather On Sunday", "lili lilil ilili aa aaa aa shudshda sdjbj", List.of("Sun", "Weather", "Sunday", "Weekend", "Relax")),
                        new Post("Cherry", "lili lilil ilili aa aaa aa shudshda sdjbj", List.of("Fruit", "Cherry", "Sweet", "Red", "Weekend", "Relax")),
                        new Post("Dog", "lili lilil ilili aa aaa aa shudshda sdjbj", List.of("Animal", "Pet", "Relax", "Sunday")),
                        new Post("Cat", "lili lilil ilili aa aaa aa shudshda sdjbj", List.of("Animal", "Pet", "Home", "Relax")),
                        new Post("Car", "lili lilil ilili aa aaa aa shudshda sdjbj", List.of("Expensive", "Car", "Transportation", "ElectronicCar"))
                );
        
                List<String> allUniqueTags = posts.stream()
                        .flatMap(post -> post.getTags().stream()).distinct().sorted().collect(Collectors.toList());
                System.out.println(allUniqueTags);
        
            }
        }
        ```
        
    - Top 5 frequent words in paragraph
        
        ```java
        import java.util.*;
        import java.util.stream.*;
        
        public class TopFreqWords {
            public static List<String> topFiveFreqWordsInPara(String paragraph) {
                List<String> words = Arrays.asList(paragraph.toLowerCase().split("\\W+"));
                List<String> result = words.stream().collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                        .entrySet().stream()
                        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                        .limit(5)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
        
                return result;
            }
            public static void main(String[] args) {
                String paragraph = "Hello World! Nice to meet you! Hello Jack! Nice to see you!";
                System.out.println(topFiveFreqWordsInPara(paragraph));
        
            }
        }
        ```
        
    - Group products by category, sort each group by price descending
        
        ```java
        import java.util.*;
        import java.util.stream.*;
        
        class Product {
            private String category;
            private String name;
            private double price;
            public Product() {}
            public Product(String category, String name, double price) {
                this.category = category;
                this.name = name;
                this.price = price;
            }
            public String getCategory() {
                return category;
            }
            public void setCategory(String category) {
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
        }
        
        public class ProductFilter {
            public static void main(String[] args) {
                List<Product> products = Arrays.asList(
                        new Product("Electronics", "TV", 4999.99),
                        new Product("Electronics", "Laptop", 1999.99),
                        new Product("Kitchen", "Knife", 19.99),
                        new Product("Kitchen", "Cutting Board", 199.99),
                        new Product("Furniture", "Chair", 120.99)
                );
        
                Map<String, List<Product>> productsByCategory = products.stream()
                        .collect(Collectors.groupingBy(Product::getCategory,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed()).collect(Collectors.toList())
                                )));
        
                productsByCategory.forEach((category, list) -> {
                    System.out.println(category + ": " + list);
                    list.forEach(product -> System.out.println(product.getName() + " - " + product.getPrice()));
                });
            }
        }
        ```
        
2. Optional is a wrapper an object that may or may not be null. It helps avoid `NullPointerException` by making the possibility of null and safer to handle. 
    
    ```java
    import java.util.*;
    
    class Post {
        private String title;
        private String content;
        private List<String> tags;
        public Post() {}
        public Post(String title, String content, List<String> tags) {
            this.title = title;
            this.content = content;
            this.tags = tags;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getContent() {
            return content;
        }
        public void setContent(String content) {
            this.content = content;
        }
        public List<String> getTags() {
            return tags;
        }
        public void setTags(List<String> tags) {
            this.tags = tags;
        }
    }
    public class BlogTags {
        public static void main(String[] args) {
            Post post = new Post("AAA", "lili lilil ilili aa aaa aa shudshda sdjbj", null);
    
            List<String> tags = Optional.ofNullable(post.getTags())
                    .orElse(List.of("general"));
    
            System.out.println("Tags: " + tags);
    
        }
    }
    ```
    
3. Java Stream API is required to write cleaner, more readable, and parallelizable code when working with collections. Stream API helps working on chainable operations, and parallelism. It encourages immutability.