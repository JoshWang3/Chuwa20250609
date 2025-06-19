### Short Questions
1. Solve following questions using Stream API.
    - Find top 3 longest strings that start with a vowel
    '''java
   
    class Solution{

        public static List<String> solve(String[] strs){
           Set<Character> vowelSet = new HashSet<>();
           vowelSet.add('a');
           vowelSet.add('e');
           vowelSet.add('i');
           vowelSet.add('o');
           vowelSet.add('u');
           List<String> res = Arrays.stream(strs).filter(s -> s != null && s.length() != 0 && vowelSet.contains(s.charAt(0)))
                              .sorted((a,b)->{return b.length() - a.length();})
                              .limit(3)
                              .toList();
           return res;
       }
    }


    public class Main{
    
        public static void main(String[] args) {
            String[] input = new String[]{"aelkjflsdk", "gvjaslkdfj", "asdflasdjfl","ladskfjalskdfjsaldkf","elkdfjsldfkjsdlfkj","sadf","abs"};
            List<String> res = Solution.solve(input);
            System.out.println(res);
	    }
    }
    
    '''

    - Return names of departments where average employee salary > 100,000:
    '''java
    import java.util.*;
    import java.util.stream.Collectors;

    class Employee{
        private String name;
        private String department;
        private double salary;

        public Employee(String name, String department, double salary){
            this.name = name;
            this.department = department;
            this.salary = salary;
        }
    
        public String getName(){
            return this.name;
        }
    
        public String getDepartment(){
            return this.department;
        }
    
        public double getSalary(){
            return this.salary;
        }

    }   


    class Solution{
        public static List<String> solve(List<Employee> employees){
        List<String> res = employees.stream()
                            .collect(Collectors.groupingBy(
                            Employee::getDepartment,
                            Collectors.averagingDouble(Employee::getSalary)))
                            .entrySet().stream()
                            .filter(entry -> entry.getValue() > 100000)
                            .map(Map.Entry::getKey)
                            .collect(Collectors.toList());

        return res;
	
        }
    }


    public class Main
    {
        public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "Engineering", 120000),
            new Employee("Bob", "Engineering", 110000),
            new Employee("Charlie", "HR", 90000),
            new Employee("David", "HR", 95000),
            new Employee("Eve", "Sales", 130000),
            new Employee("Frank", "Sales", 105000)
        );
        List<String> res = Solution.solve(employees);
        System.out.println(res);

	    }
    }
    '''

    - Get a sorted list of all unique tags from a list of blog posts(Blog to Tags is 1-to-many relationship):
    
    '''java
    class Blog{
    List<String> tags;
    String title;

        public Blog(String title, List<String> tags){
            this.title = title;
            this.tags = tags;
        }
    
        public List<String> getTags(){
            return tags;
        }
    }



    class Solution{
        public static List<String> solve(List<Blog> blogs){
            return blogs.stream()
                    .flatMap(blog -> blog.getTags().stream())
                    .collect(Collectors.groupingBy(
                    tag -> tag, Collectors.counting()
                    )).entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .map(Map.Entry::getKey)
                   .collect(Collectors.toList());
        }
        }


    public class Main
    {
        public static void main(String[] args) {
        List<Blog> posts = Arrays.asList(
            new Blog("Java Streams", Arrays.asList("java", "stream", "api")),
            new Blog("Functional Programming", Arrays.asList("java", "functional", "lambda")),
            new Blog("Sorting in Java", Arrays.asList("java", "stream", "sorting"))
        );
        List<String> res = Solution.solve(posts);
        System.out.println(res);

	    }
    }
    '''

    - Return top 5 words by frequency from a paragraph
    '''java
    class Solution{
        public static List<String> solve(String paragraph){
            //here we assume the paragraph is a long unsplitted String
            return Arrays.stream(paragraph.split(" ")).collect(Collectors.groupingBy(
                        word -> word, Collectors.counting()))
                        .entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(5)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
          
            }
        }


        public class Main
        {
            public static void main(String[] args) {
                String p = "aslkdfjas laksdjflks lkasjflalsdfjlksdjf kjhsdf a b f s csd. sdfalkj ssf. asdlkfjlk sdafl kj a b ssf. a a b b b b b b b c c c c c c d d d d ";
                List<String> res = Solution.solve(p);
                System.out.println(res);
	        }
        }
    '''
    
    - Group products by category and sort each group by price descending
    '''java
    class Product{
        private String name;
        private String category;
        private double price;
    
        public Product(String name, String category, double price){
            this.name = name;
            this.category = category;
            this.price = price;
        }
    
        public double getPrice(){
            return this.price;
        }
    
        public String getName(){
            return this.name;
        }
    
        public String getCategory(){
            return this.category;
        }
    
        @Override
        public String toString(){
            return this.name + " " + this.category + " " + this.price;
        }
    }



    class Solution{
        public static Map<String, List<Product>> solve(List<Product> products){

            return products.stream().collect(
                Collectors.groupingBy(
                    product -> product.getCategory(), Collectors.collectingAndThen(
                        Collectors.toList(), list->list.stream()
                                         .sorted(Comparator.comparing(Product::getPrice).reversed())
                                         .collect(Collectors.toList())
                        )
                )
            );
        }
    }


    public class Main
    {
        public static void main(String[] args) {
            List<Product> products = Arrays.asList(
                new Product("Tesla", "Car", 10000),
                new Product("Apple", "Computer", 1000),
                new Product("Toyota", "Car", 20000),
                new Product("Lexus", "Car", 30000),
                new Product("Lenovo", "Computer", 1199)
            );
            Map<String,List<Product>> res = Solution.solve(products);
            System.out.println(res);
	    }
    }
    '''

2. Write code snippet to explain how Optional helps prevent null pointer exception, you may use blog-tags, and product-category POJOs to demo.
    '''java
   class Product{
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price){
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public double getPrice(){
        return this.price;
    }

    public String getName(){
        return this.name;
    }

    public String getCategory(){
        return this.category;
    }

    @Override
    public String toString(){
        return this.name + " " + this.category + " " + this.price;
        }
    }


    //this case the problem may raise NullPointerException
    class Solution{
    /*
        public static String getProductCategory(Product p){
        //if we don't check whether p == null, program will raise a NullPointerException
            return p.getCategory();
        }
    */
    /*
        This method returns an empty string if p is null
        but if p has multiple levels of nullable values
        it can get tedious

        public static String getProductCategory(Product p){
            if(p == null) return "";
            else return p.getCategory();
        }
    */
    
    //here we use optional
    public static String getProductCategory(Product p){
        Optional<String> res = Optional.ofNullable(p).map(Product::getCategory);
        return res.orElse("No Available information");
        }


    }


    public class Main
    {
        public static void main(String[] args) {
            Product p = new Product("Tesla", "Car", 10000);
            System.out.println(Solution.getProductCategory(null));
            System.out.println(Solution.getProductCategory(p));
	    }
    }
3. Explain why Java Stream API is required, how does it help on data processing? 
    Java Stream API is required because it makes data processing concise, expressive and efficient, while eliminating 
    much of the complexity and verbosity of the traditional iteration and control flow logic. It hides the implementation of
    processing data and focuses on the business logic rather than focuses on implementation details.
