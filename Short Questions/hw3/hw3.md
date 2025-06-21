# 6.13 HW3 - Java Core

For all questions below, you should write sample code, which can be ran directly on online compilers such as https://www.onlinegdb.com/online_java_compiler (if possible) to explain your answer

1. Write up Example code to demonstrate the three foundmental concepts of OOP.

   - **Encapsulation**

     Encapsulation means hiding data (variables) and the method that operates on that data inside the class using the access modifier private, and these attributes can only be accessed or modified by the public methods like getter/setter.

     ```java
     class People {
         private String ssn;
         
         public String getSsn() {
             return ssn;
         }
         
         public void setSsn(String ssn) {
             if (isValidSsn(ssn)) {
                 this.ssn = ssn;
             } else {
                 System.out.println("This is a invalid ssn.");
             }
         }
         
         private boolean isValidSsn(String ssn) {
             return ssn != null && ssn.matches("\\d{3}-\\d{2}-\\d{4}");
         }
         
     	public static void main(String[] args) {
     	    People p1 = new People();
     	    p1.setSsn("invalid-ssn");  // This is a invalid ssn.
     	    p1.setSsn("123-45-6789");  
         	System.out.println("p1's ssn: "+ p1.getSsn()); // p1's ssn: 123-45-6789
     	}
     }
     ```

   - **Polymorphism**

     Polymorphism means the same object can perform different forms and behaviors. There are 2 types of polymorphism: one is **static polymorphism** happened in compile time, referring to **overloading** in the same class; the another one is **dynamic polymorphism** happened in run time, referring to **overriding** in the subclass.

     ```java
     interface Animal {
         public void makeSound();
     }
     
     class Dog implements Animal {
         // Overriding: different implementation
         @Override
         public void makeSound() {
             System.out.println("Woof!");
         }
         
         // Overloading: same method name but different parameter
         public void makeSound(int times) {
             StringBuilder sb = new StringBuilder();
             for (int i = 0; i < times; i++) {
                 sb.append("Woof!");
             }
             System.out.println(sb.toString());
         }
     }
     
     class Cat implements Animal {
         @Override
         public void makeSound() {
             System.out.println("Meow!");
         }
     }
     
     public class Main {
         public static void main(String[] args) {
             Animal animal1 = new Dog(); //interface reference
             Animal animal2 = new Cat();
             Dog animal3 = new Dog();
             
             animal1.makeSound();  // Woof!
             animal2.makeSound();  // Meow!
             animal3.makeSound(3);  // Woof!Woof!Woof!
         }
     }
     ```

   - **Inheritance**

     Inheritance means an "is-a" relationship between classes. A class can acquire fields and methods from its superclass, which improves code reuse.

     ```java
     // Parent class (superclass)
     class Phone {
         public void call() {
             System.out.println("Making a phone call.");
         }
         
         public void text() {
             System.out.println("Texting a message.");
         }
     }
     
     // Child class (subclass)
     class Smartphone extends Phone {
         public void browse() {
             System.out.println("Browsing the web");
         }
     }
     
     public class Main {
         public static void main(String[] args) {
             Smartphone phone1 = new Smartphone();
             phone1.call();  // Making a phone call.
             phone1.text();  // Texting a message.
             phone1.browse();  // Browsing the web
         }
     }
     ```

     

2. What is **wrapper data type classes** (e.g. Integer, Double) in Java and Why we need wrapper class?

   Wrapper classes are wrapping the primitive data type into **objects** 

   We need them because some certain places, like generics, collections, frameworks, and APIs, require objects for use, and they also provide useful utility methods.

   There is a easy conversion between primitive types and wrapper classes called **autoboxing and unboxing**.

   ```java
   List<Integer> list = new ArrayList<>();
   list.add(10);  // Autoboxing: int -> Integer automatically
   int num = list.get(0);  // Unboxing: Integer -> int automatically
   ```

   

3. What is the difference between **HashMap** and **HashTable**?

   **HashTable (legacy class):**

   - Thread-safe, slower performance due to locking(synchronized)
   - Doesn't allow null key or null values

   **HashMap (modren usage, in Collection Framework):**

   - Not thread-safe, can use ConcurrentHashMap for thread-safe concerns.

   - Allow 1 null key and null values

     

4. What is **String pool** in Java and why we need String pool? Explain String immunity.

   String pool is a special area inside the heap memory in JVM where all string literals are stored. 

   When a new String literal is created, JVM will check if the same value already exists in the String Pool first; if there is, the existing object reference is reused; if there isn't, a new String object will be created and placed into the pool.

   ```java
   String s1 = "Hello";
   String s2 = "Hello";
   String s3 = "World";
   String s4 = new String("Hello");
   System.out.println(s1 == s2); 
   // true, s1 and s2 are the same value, and they point to the same object reference in the pool
   System.out.println(s1 == s3); 
   // false, s1 and s2 are not the same value, a new String object created and placed into the pool
   System.out.println(s1 == s4);
   // false, even if s1 and s4 have the same value, but we use "new" to create a new String object, not from the pool
   ```

   We need Spring pool because because strings are heavily used in Java programs, and creating many duplicate string objects would waste memory. With Spring pool, we can save memory by reusing the same objects in the pool. This reuse is possible because **String is immutable**. In the JDK implementation, String is an immutable class, which is a final class with internal private final variable char[] and no setter methods.

   

5. Explain **garbage collection**? Explain types of garbage collection.

   Garbage collection is an automatic memory management process by removing unused objects from **heap** memory.

   There are 3 main types of garbage collection:

   - Serial GC: single-threaded, ideal for small applications with limited resources

   - Parallel GC: multiple threads, suitable for throughput-first applications

   - G1 GC: multi-threaded, concurrent collector designed for large heaps and predictable pause times

     

6. What are **access modifiers** and their scopes in Java?

   Access modifiers are keywords used to set the access level for classes, attributes, methods and constructors.

   - `public`: can be accessed from anywhere

   - `private`: can be accessed only within the same class

   - `protected`: can be accessed from anywhere **within the same package and any subclass (even in other packages)**

   - No modifier(default): can be accessed from anywhere within the same package

     

7. Explain **final** key word? (Field, Method, Class)

   `final` keyword non-access modifier used to indicate that something cannot be changed, which can be used on a variable/field, method, and class.

   ```java
   // final variable/field
   final int MAX_VALUE = 100;
   // MAX_VALUE = 200;  // Compile-time error
   
   // final method
   class Parent{
       public final void finalMethod() {
           System.out.println("Final method in Parent");
       }
   }
   
   class Child extends Parent{
       // public void finalMethod() {
           // System.out.println("Final method in Child"); // Compile-time error: Cannot be overriden
       }
   }
   
   // final class
   final class FinalClass {
       public void show() {
           System.out.println("Inside final class");
       }
   }
   
   // class SubClass extends FinalClass {} // Compile error: Cannot inherit from final class
   ```

   

8. Explan **static** keyword? (Filed, Method, Class). When do we usually use it?

   `static` means belonging to the class, not to any specific instance. It can be applied to variables (fields), methods, blocks, and nested classes. `static` members can be accessed directly using the class name **without creating an instance**.

   - **Static variable**: gets created when the first instance of the class gets created. There is only a copy of it and all the instances of this class share this copy. Usually used when data is shared across all instances, like global count, configuration, constants.

       ```java
       class Student {
           private String name;
           private int id;
       
           // Static variable: shared across all instances
           private static int nextId = 1;
       
           public Student(String name) {
               this.name = name;
               this.id = nextId;
               nextId++;  // update static variable for next student
           }
       
           public void printInfo() {
               System.out.println("Student Name: " + name + ", ID: " + id);
           }
       }
       
       public class Main {
           public static void main(String[] args) {
               Student s1 = new Student("Alice");
               Student s2 = new Student("Bob");
               Student s3 = new Student("Charlie");
       
               s1.printInfo();  // Student Name: Alice, ID: 1
               s2.printInfo();  // Student Name: Alice, ID: 2
               s3.printInfo();  // Student Name: Alice, ID: 3
           }
       }
       ```

   - **Static block**: only executed once when JVM loads the class.

     ```java
     public class Main {
         static {
             System.out.println("Static block executed.");
         }
     
         public static void main(String[] args) {
             System.out.println("Main method.");
         }
     }
     // Static block executed.
     // Main method.
     ```

   - **Static method**: method can be called without instantiating an object. Usually used in utility/helper methods like Math, Collections, Arrays, Integer.

     ```java
     class MathUtil {
         public static int add(int a, int b) {
             return a + b;
         }
     }
     
     public class Main {
         public static void main(String[] args) {
             int res = MathUtil.add(5, 3);  // without instantiating an instance
             System.out.println(res);
         }
     }
     ```

     **Static methods cannot directly access non-static (instance) variables or methods**. Because static methods exist before any instance is created. Non-static variables belong to instances. If there’s no instance, there's no non-static variable to refer to.

     ```java
     class Example {
         private int count = 0;  // non-static variable
     
         public static void staticMethod() {
             // System.out.println(count);  // Compile-time error
         }
     }
     ```

   - **Static nested class**: does not require outer class instance

     ```java
     class StaticDemo {
         static class Helper {
             void helperMethod() {
                 System.out.println("Static nested class.");
             }
         }
     }
     
     public class Main {
         public static void main(String[] args) {
             StaticDemo.Helper helper = new StaticDemo.Helper();  // No need to create StaticDemo object
             helper.helperMethod();
         }
     }
     ```

     

9. What is the differences between **overriding** and **overloading**?

    **Overloading method**: Same method name, but different parameters; happens in the same class.

    **Overriding method**: Same method signature, different implementation in the subclass.

    The code example can be found in Q1 polymorphism part.

    

10. Explain how Java defines a **method signature**, and how it helps on overloading and overriding.

    Method Signature = Method Name + Parameter List (types + order + number)

    Overloading means the method names are the same, but the parameter lists are different.

    Overriding means the method signatures are the same.

    

11. What is the differences between **super** and **this**?

     **super**: Refers to the immediate parent class. Used to call superclass constructors or access overridden methods and hidden variables.

     ```java
     class Animal {
         // parent constructor
         public Animal(String name) {
             System.out.println("Animal: " + name);
         }
         // parent method
         public void eat() {
             System.out.println("Animal is eating");
         }
     }
     
     class Dog extends Animal {
         public Dog() {
             super("Dog");  // call parent constructor
         }
         
         public void eat() {
             super.eat();  // call parent method
             System.out.println("Dog is eating");
         }
     }
     
     
     public class Main {
         public static void main(String[] args) {
             Dog d = new Dog();
             d.eat();
         }
     }
     
     // output:
     // Animal: Dog
     // Animal is eating
     // Dog is eating
     ```

     **this**: Refers to the current object of the class. Used for accessing current instance members or constructor chaining.

     ```java
     class Person {
         private String name;
     
         public Person(String name) {
             this.name = name;
             // this.name: instance variable
             // name : parameter
             // use "this" to assign the value of constructor parameter "name" to the instance variable "name", avoiding name shadowing.
         }
     }
     ```

     ```java
     class Example {
         public Example() {
             this(10);  // call another constructor in the same class
         }
     
         public Example(int value) {
             System.out.println("Value: " + value);
         }
     }
     
     ```

     

12. Explain how `equals` and `hashCode` work.

     Every class inherits these two methods from `Object` class in Java.

     `equals()` checks logical equality. By default, `Object.equals()` checks reference equality (like ==), but we can **override it to provide logical equality check**, and we need to override hashcode() as well.

     ```java
     class Person {
         String name;
         int age;
     
         @Override
         public boolean equals(Object o) {
             if (this == o) return true;
             if (o == null || getClass() != o.getClass()) return false;
             Person other = (Person) o;
             return age == other.age && name.equals(other.name);
         }
         
         @Override
     	public int hashCode() {
         	return Objects.hash(name, age);
     	}
         
     }
     ```

     If `object_1.equals(object_2)` returns true, We can imply both objects are logically equal, but we can't say they are the same object in memory(reference equality).

     `hashCode()` generates an integer value for hash-based collections. It should be overridden when overriding equal() method, because the **two object must have the same hashcode** if they are equals according to equal() method.

     

13. What is the Java **load sequence**?

     **Static Initialization**: only once when a class is first loaded into the JVM

     1. Static variables
     2. Static blocks

     **Instance Initialization**: every time when a new instance of a class is created using the `new` keyword

     3. Instance variables 
     4. Instance initialization blocks

     **Constructor Execution**

     5. Constructor

     ```java
     class Main {
     
         // Static variable
         static int staticVar = initializeStaticVar();
     
         // Static block
         static {
             System.out.println("Static block run only once");
         }
     
         // Instance variable
         int instanceVar = initializeInstanceVar();
     
         // Instance initialization block
         {
             System.out.println("Instance block");
         }
     
         // Constructor
         public Main() {
             System.out.println("Constructor");
         }
     
         // Static method to initialize static variable
         static int initializeStaticVar() {
             System.out.println("Static variable initialized");
             return 1;
         }
     
         // Method to initialize instance variable
         int initializeInstanceVar() {
             System.out.println("Instance variable initialized");
             return 2;
         }
     
         public static void main(String[] args) {
             System.out.println("-- Main starts -- ");
             new Main();
             System.out.println("-- create a new object --");
             new Main();
         }
     }
     
     // Static variable initialized
     // Static block
     // Main starts
     // Instance variable initialized
     // Instance block
     // Constructor
     // ----
     // Instance variable initialized
     // Instance block
     // Constructor
     ```

     

14. What is **Polymorphism**? And how Java implements it?

     Same as Q1 - Polymorphism.

     

15. What is **Encapsulation**? How Java implements it? And why we need encapsulation?

     Same as Q1 - Encapsulation. 

     We need encapsulation because we need to protect internal data and provide controlled access, allowing safe and clear interaction with objects.

     

16. Compare **interface** and **abstract class** with use cases.

     Interface defines what a class can do (feature). It can have **abstract methods** and since Java 8, it can have `default` and `static` methods. The variables are declared within the interface and are automatically public static final. A class can implement multiple interfaces.

     Abstract class defines what a class is. It can have **both abstract methods** (without implementation) and **concrete methods**. A class can only extends one abstract class.

     ```java
     interface Flyable {
         void fly();
     }
     
     class Bird implements Flyable {
         @Override
         public void fly() {
             System.out.println("Bird is flying");
         }
     }
     
     class Airplane implements Flyable {
         @Override
         public void fly() {
             System.out.println("Airplane is flying");
         }
     }
     
     public class Main {
         public static void main(String[] args) {
             Bird b = new Bird();
             Airplane a = new Airplane();
             b.fly();
             a.fly();
         }
     }
     
     // Bird is flying
     // Airplane is flying
     ```

     ```java
     abstract class Animal {
         String name;
     	// constructor
         public Animal(String name) {
             this.name = name;
         }
     
         public void sleep() {
             System.out.println(name + " is sleeping");  //concrete method
         }
     
         public abstract void makeSound();  // abstract method
     }
     
     class Dog extends Animal {
         public Dog(String name) {
             super(name);
         }
     
         @Override
         public void makeSound() {
             System.out.println("Woof");
         }
     }
     
     public class Main {
         public static void main(String[] args) {
             Dog d = new Dog("Puppy");
             d.sleep();
             d.makeSound();
         }
     }
     
     // Puppy is sleeping
     // Woof
     ```

     

