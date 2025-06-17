# 06/17

1. Demo Encapsulation, Polymorphism, Inheritance
    
    ```java
    class Person {
    	private String name; //Encapsulation
    	private int ssn;
    	
    	public Person(String name, int ssn) {
    		this.name = name;
    		this.ssn = ssn;
    	}
    	public String getName() { //Encapsulation
    		return name;
    	}
    	public void setName(String name) { //Encapsulation
    		this.name = name;
    	}
    	public int getSSN() {
    		return ssn;
    	}
    	public void setSSN(int ssn) {
    		this.ssn = ssn;
    	}
    	
    	void printInfo(String name) {
    		System.out.println("This person is: " + name);
    	}
    }
    
    class Employee extends Person { // Inheritance
    	private String department;
    	public Employee(String name, int ssn, String department) {
    		super(name, ssn); // Inheritance
    		this.department = department;
    	}
    	public String getDepartment() {
    		return department;
    	}
    	public void setDepartment(String depart) {
    		this.department = depart;
    	}
    	@Override //runtime Polymorphism
    	void printInfo(String name) {
    		super.printInfo(name);
    		System.out.println("Hello World");
    	}
    }
    public class Main{
    	public static void main(String[] args) {
    	    Employee e = new Employee("Alice", 123456790, "IT");
    	    e.printInfo(e.getName());
    	}
    }
    ```
    
2. Wrapper classes are used to wrap primitive data types into objects. E.g., `int VS Integer`, `double VS Double`, `boolean VS Boolean`, `char VS Character`. Converting primitive types into objects allows to apply Java features that only work with objects. 
    1. Use in Collections (like List, Map) 
        
        ```java
        List<Integer> list = new ArrayList<>(); //valid
        List<int> list1 = new ArrayList<>(); //invalid
        ```
        
    2. Use Utility Methods
        
        ```java
        int min = Integer.min(1, 2); //static utility method
        ```
        
    3. Support Autoboxing and Unboxing (automatically converts between primitives and wrapper classes)
        
        ```java
        Boolean x = true; //autoboxing: int -> Integer
        boolean y = x; // unboxing: Integer -> int
        ```
        
    4. Work with Null Values
        
        ```java
        Double m = null; //valid
        double n = null; // error
        ```
        
3. HashMap VS Hashtable
    1. HashMap allows 1 null key and multiple null values while Hashtable doesn’t. 
    2. HashMap is non-synchronized key-value map while Hashtable is synchronized. It means multiple threads can access and modify the same data at the same time if using HashMap. It’s not thread-safe data structure. 
    3. HashMap is faster than Hashtable due to synchronization.
    4. HashMap uses Iterator while Hashtable uses Enumeration. Iterator allows to detect structurally modifications during iteration, so it is fail-fast. 
    5. HashMap is added in the Java later than Hashtable. Now, we usually use `ConcurrentHashMap` instead of Hashtable.
4.  The string pool is a memory optimization feature in Java where string literals are stored in a special memory area in Java heap. Example:
    
    ```java
    String a = "Hello World";
    String b = "Hello World";
    String c = new String("Hello World");
    System.out.println(a == b); // true - same object 
    System.out.println(a == c); //false - c is a new object
    ```
    
    The advantages if string pool include:
    
    1. Memory Efficiency: common string literals can be reused to reduce memory usages
    2. Faster performance by using `==` to compare string
    3. Automatic optimization by the JVM behind the scenes for all string literals
    
    Strings are immutable. It can’t change once created, so it is possible to be stored in string pool. Immutable objects can be shared between threads safely. It can be cached safely.
    
5. Garbage Collection is the process by which the JVM automatically finds and removes objects that won’t be reached or used to reduce memory usage. If there are no more references to an object, it can be deleted. Java uses “Tracing Garbage Collection” by Mark and Sweep phases. There are multiple types of garbage collectors: Serial GC, Parallel GC, G1 (garbage-first) GC, Z GC, and Shenandoah GC. The parallel GC is default GC for high throughput needed. Serial GC is simple and single-threaded for small apps or low memory. Z GC is a low-latency collector and scalable which can be used for real-time/low-latency systems. G1 GC can be used for general-purpose apps. Shenandoah GC is similar to Z GC.
6. Access modifiers are private, public, protected, package-private (default) which can be defined the visibility of classes, methods, and variables. `private` can only be accessed within the same class. `protected` can be accessed in the same class, same package, and subclass (other package). `public`  can be accessed in any classes and packages. (default) can be accessed in the same package and class. Choosing the right modifier is good for encapsulation, modularity, and security.
7. The keyword `final` means can’t be changed after assignment. It will be applied in variable, method, and class. 
    
    Usually we use it for configuration values and invariants. 
    
    ```java
    public class A {
    	public static void main (String[] args) {
    		final int a = 10;
    		// a = 20; Will show error 
    	}
    }
    ```
    
    If we use it in method, we can’t override it in subclass. 
    
    ```java
    public class A {
    	public final void printInfo() {
    		System.out.println("Can't be overridden");
    	}
    }
    
    public class B extends A {
    	//public void printInfo(); Will show error
    }
    ```
    
    If we use it in class, we can’t extend it in other classes. We usually use it for utility classes. 
    
8. The `static` keyword means the members belongs to the class. It can be applied to variables, methods, classes. 
    1. Static Variable - shared across all instances of the class
        
        ```java
        public class A {
        	public static int count = 0;
        }
        public class B {
        	public static void main(String[] args) {
        		A.count++; // don't need to create an object
        	}
        }
        ```
        
    2. Static Method
        
        ```java
        public class A {
        	public static int add(int a, int b) {
        		return a + b;
        	}
        }
        
        public class B {
        	public static void main(String[] args) {
        		A.add(1, 2);
        	}
        }
        ```
        
    3. Static Class - can be used to define static nested classes, Used when the nested class does not need access to outer class instance
        
        ```java
        public class A {
        	static class B {
        		void printInfo() {
        			System.out.println("Hello World");
        		}
        	}
        }
        ```
        
9. Overloading VS Overriding
    1. Method overloading means multiple methods with the same name but with different (number / type of) parameters within the same class. Overriding means same method name and same signature in a subclass, replacing parent method. 
    2. The return type of overloading method can be different while overriding must be same/covariant type. 
    3. Overloading’s method signature must be different while overriding must be the same. 
    4. Overloading can be compile-time polymorphism while overriding is runtime polymorphism.
    5. Overriding can’t reduce visibility while overloading doesn’t have restrictions
    
    ```java
    //Overloading
    public class A {
    	// Overload with different parameters
    	public double add(double a, double b) {
    		return a + b;
      }
      public double add(int a, double b) {
        return a + b;
      }
    }
    ```
    
    ```java
    //Overriding
    public class Person {
    	void printInfo(String name) {
    		System.out.println("This person is: " + name);
    	}
    }
    
    public class Employee extends Person {
    	@Override
    	void printInfo(String name) {
    		System.out.println("This employee is: " + name);
    	}
    }
    ```
    
10. Method signature includes (1) method name, (2) number, type, and order of parameters. Overloading is based on different method signatures (different parameter list). Overriding is based on same method signature as in the superclass. Return type also must be same or covariant. 
11. `this` vs `super`
    1. `this` means the current object - instance of the current class. `super` means the parent class - immediate superclass of the current class.
    2. `this` accesses instance variables/object or call another constructor in the same class.
        
        ```java
        public class Student {
        	String name;
        	public Student() {
        		this("Alice"); // calls the other constructor.
        	}
        	public Student(String name) {
        		this.name = name;
        	}
        	
        	public Student getInfo() {
        		return this; 
        	}
        }
        ```
        
    3. `super` accesses superclass variables or call superclass methods or constructor.
        
        ```java
        public class Person {
        	public Person(String name) {
        		System.out.println("Hello from " + name);
        	}
        	void printInfo(String name) {
        		System.out.println("This person is: " + name);
        	}
        }
        
        public class Employee extends Person {
        	public Employee(String name) {
        		super(name); // calls constructor in Person
        	}
        	@Override
        	void printInfo(String name) {
        		super.printInfo(name);
        		System.out.println("Hello World");
        	}
        }
        ```
        
12. `equals()` checks if two objects are logically “equal” (have the same content). It can be used for reference equality by default. `hashCode()` returns an Integer that is used by hash-based collections to find objects. If `equals()` returns true, `hashCode()` of all objects returns the same values. 
13. Class loading is the process by which JVM loads classes into memory when they are first used. There are 4/5 steps: loading, linking, initialization, (optional) using, unloading. 
    1. Loading: the class is read from a `.class` file. Bytecode is brought into JVM memory. 
        
        ```java
        Class<?> clazz = Class.forName("com.example.Employee");
        ```
        
    2. Linking:
        1. Verification: bytecode is checked for correctness and security.
        2. Preparation: static fields are created and assigned default values.
        3. Resolution: all symbolic references are resolved (e.g. method and field references)
    3. Initialization: all static variables and blocks are initialized in order of appearance. 
    4. Using (Runtime Execution): Its static fields are used and methods are executed. New objects can be created. Class is fully usable at runtime. 
    5. Unloading (Garbage Collected): class is removed from memory if no longer referenced. 
14. Polymorphism allows one interface to behave differently depending on the context or data type. There are two types: compile-time polymorphism and runtime polymorphism. Compile-time polymorphism applies via overloading while runtime polymorphism applies via overriding. 
15. Encapsulation provides controlled access to restrict direct access to internal details of a class. In Java, we use access modifiers to control visibility through `private` variables, and `public` getter and setter methods. It improves security, code maintainability, and data integrity. 
16. Interface VS Abstract Class
    1. Interface defines a contract(what to do) while abstract class defines a base class(what and partly how to do).
    2. Interface can have default/static methods while abstract class can have both abstract and concrete methods. 
    3. Interface can only use `public static final` for variables while abstract class without access modifier restriction.
    4. Abstract class can have constructors but can’t extend multiple abstract classes. Multiple interface can be implemented in one class. 
    5. Interface can have public methods while abstract class doesn’t have restriction.