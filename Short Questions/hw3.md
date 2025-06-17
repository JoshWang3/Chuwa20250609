## HW III

#### 1. Write up Example code to demonstrate the three fundamental concepts of OOP. (Encapsulation, Polymorphism, Inheritance)
```java 
// Base class 
class Animal {
    // Private fields (Encapsulation)
    private String name;
    private int age;
    
    // Constructor
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Getters (Encapsulation - controlled access)
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    // Setters (Encapsulation - controlled modification)
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAge(int age) {
        if (age > 0) {  // Validation logic
            this.age = age;
        }
    }
    
    // Method to be overridden (Polymorphism)
    public void makeSound() {
        System.out.println("The animal makes a sound");
    }
    
    // Common method
    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

// Derived class (INHERITANCE)
class Dog extends Animal {
    private String breed;  // Additional field specific to Dog
    
    public Dog(String name, int age, String breed) {
        super(name, age);  // Call parent constructor
        this.breed = breed;
    }
    
    // Getter for breed
    public String getBreed() {
        return breed;
    }
    
    // Override parent method (POLYMORPHISM)
    @Override
    public void makeSound() {
        System.out.println(getName() + " says: Woof!");
    }
    
    // Dog-specific method
    public void wagTail() {
        System.out.println(getName() + " is wagging its tail");
    }
}

// Another derived class (INHERITANCE)
class Cat extends Animal {
    private boolean isIndoor;
    
    public Cat(String name, int age, boolean isIndoor) {
        super(name, age);
        this.isIndoor = isIndoor;
    }
    
    // Override parent method (POLYMORPHISM)
    @Override
    public void makeSound() {
        System.out.println(getName() + " says: Meow!");
    }
    
    // Cat-specific method
    public void scratch() {
        System.out.println(getName() + " is scratching");
    }
}
```
Summary:
- encapsulation
  - controlled field access through getters 
  - controlled field modification through setters 
- inheritance
  - inherit common properties and methods
  - reduce code repetition, more manageable 
- polymorphism 
  - children classes, same method, different behaviors (overriding)
  - same class, different parameter number or types (overloading)


#### 2. What is wrapper data type classes (e.g. Integer, Double) in Java? 
Wrapper classes in Java are classes that "wrap" primitive data types into objects. Each primitive type has a corresponding wrapper class:
- int → Integer
- double → Double
- boolean → Boolean
- char → Character
- byte → Byte
- short → Short
- long → Long
- float → Float

**Why we need wrapper class?**
1. Collections Framework Compatibility - Java collections (ArrayList, HashMap, etc.) can only store objects, not primitives
2. Object-Oriented Features - Wrapper classes provide useful static methods for conversion and manipulation:
```java
String str = "123";
int num = Integer.parseInt(str);  // String to int
String binary = Integer.toBinaryString(42);  // "101010"
int max = Integer.MAX_VALUE;  // 2147483647
```
3. Some methods require objects as parameters:
   `public <T> void processValue(T value) {}`


#### 3. What is the difference between HashMap and HashTable?
- HashMap
  - not synchronized, meaning it's not thread-safe. Multiple threads can access it simultaneously. 
  - Faster because there's no synchronization overhead 
  - Null is allowed for both key and value
- HashTable (legacy )
  - synchronized and thread-safe. Only one thread can access it at a time.
  - Slower due to synchronization costs
  - Null is not allowed, will throw null pointer exception 

#### 4. What is String pool in Java?
- The String Constant Pool is a special memory area in the Java heap space where String literals are stored. 
- When you create a String literal, the JVM first checks if an identical String already exists in the pool:
  - If it exists, the reference to the existing String is returned
  - If it doesn't exist, a new String is created and added to the pool, a new reference is returned 

**Why We Need String Pool**
- We need the String pool because strings are frequently used in Java applications, and without pooling, creating multiple identical strings would waste significant memory
- The pool enables memory optimization through string interning, 
- improves performance by reusing existing string objects instead of creating duplicates.

**String immutability**
- String immutability means that once a String object is created, its content cannot be changed or modified. 
- When you perform operations like concatenation or replacement on a string, Java creates a new String object rather than modifying the original one. 
- This immutability is crucial for the String pool to work correctly because it 
  - ensures that multiple references to the same pooled string won't interfere with each other
  - maintains thread safety without synchronization (improved performance)
  -  allows strings to be safely used as keys in hash-based collections like HashMap
```java 
public class StringPoolDemo {
    public static void main(String[] args) {
        // String pool demonstration
        String s1 = "Hello";           // Created in string pool
        String s2 = "Hello";           // Points to same object in pool
        String s3 = new String("Hello"); // Created in heap, not pool
        
        System.out.println("s1 == s2: " + (s1 == s2));  // true (same reference)
        System.out.println("s1 == s3: " + (s1 == s3));  // false (different references)
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // true (same content)
        
        // String immutability demonstration
        String original = "Java";
        String modified = original.concat(" Programming");
        
        System.out.println("Original: " + original);     // "Java" (unchanged)
        System.out.println("Modified: " + modified);     // "Java Programming" (new object)
        System.out.println("Same object? " + (original == modified)); // false
        
        // To actually change the reference, you need assignment
        name = name.toUpperCase();
        System.out.println("Name after assignment: " + name); // Now "JOHN"
    }
}
```

#### 5. Explain garbage collection? Explain types of garbage collection.
Garbage collection is an automatic memory management process in Java that identifies and removes objects from memory that are no longer referenced or reachable by the application. The JVM's garbage collector runs periodically to free up memory occupied by unused objects, preventing memory leaks and OutOfMemoryError exceptions.
1. Serial Garbage Collector
The Serial GC uses a **single thread** for garbage collection and is suitable for small applications with single-core machines. It pauses the application during collection, making it ideal for desktop applications where pause times are acceptable.
1. Parallel Garbage Collector (Throughput Collector)
The Parallel GC uses **multiple threads** for garbage collection, making it faster than Serial GC on multi-core systems. It's designed for applications that prioritize throughput over low latency and is the default collector (Java 8) for server applications.
1. Concurrent Mark Sweep (CMS) Collector
The CMS collector performs most of its work concurrently with the application threads, resulting in shorter pause times. It's suitable for applications that require low latency but may suffer from fragmentation issues over time.
1. G1 Garbage Collector (Garbage First)
The G1 collector divides the heap into regions and collects garbage incrementally, providing predictable pause times. It's designed for large heap applications that need both high throughput and low latency, making it suitable for server applications with strict response time requirements.
1. ZGC (Z Garbage Collector)
ZGC is a low-latency collector that keeps pause times under 10ms regardless of heap size. It's designed for applications requiring extremely low latency and can handle very large heaps efficiently.
1. Shenandoah GC
Shenandoah is another low-pause collector that performs garbage collection concurrently with application threads. It aims to provide consistent low pause times independent of heap size, making it suitable for responsive applications.

#### 6. What are access modifiers and their scopes in Java?
Access modifiers in Java are keywords used to define the visibility or scope of classes, methods, and variables. The four main access modifiers are:

- Public – accessible from any other class.
- Protected – accessible within the same package and by **subclasses** in other packages.
- Default (no modifier) – accessible only within the same package.
- Private – accessible only within the same class.

[Ref] https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html

#### 7. Explain final key word? (Filed, Method, Class)
The final keyword is used to restrict modification. 
- When applied to a field/variable, it means the variable can be assigned only once and cannot be changed afterward. 
- When used with a method, it prevents the method from being **overridden** in any subclass. 
- When applied to a class, it means the class cannot be **extended** or **subclassed**.


#### 8. Explain static keyword? (Filed, Method, Class). When do we usually use it?
In Java, the static keyword is used to indicate that a field, method, or class belongs to the class itself rather than to instances of the class. We typically use static when behavior or data should be shared across all instances, or when instantiating a class is unnecessary.

- Static Field: A static variable is shared among all instances of a class. It is used when you want to have a common value for all objects, such as a counter to track the number of objects created.

- Static Method: A static method can be called without creating an instance of the class. It cannot access instance variables or methods directly. It is commonly used for utility or helper methods (e.g., Math.pow()).

- Static Class: Only nested (inner) classes can be declared static. A static nested class can be instantiated without an outer class instance and cannot access instance members of the outer class directly.

```java
public class StaticExample {

    // Static field
    static int count = 0;

    // Instance field
    String name;

    // Constructor
    public StaticExample(String name) {
        this.name = name;
        count++; // Increments shared count for every new object
    }

    // Static method
    static void displayCount() {
        System.out.println("Total objects created: " + count);
    }

    // Static nested class
    static class Helper {
        static void showMessage() {
            System.out.println("This is a static nested class.");
        }
    }

    public static void main(String[] args) {
        StaticExample obj1 = new StaticExample("Alice");
        StaticExample obj2 = new StaticExample("Bob");

        // Calling static method without creating an object
        StaticExample.displayCount();

        // Calling static nested class method
        StaticExample.Helper.showMessage();
    }
}
```

#### 9. What is the differences between overriding and overloading?
- Overriding occurs when a subclass provides a specific implementation of a method already defined in its superclass with the same signature, enabling **runtime** polymorphism. 
- Overloading, on the other hand, happens when multiple methods in the same class share the same name but differ in parameter types or numbers or order, allowing **compile-time** polymorphism.
 (compile-time > runtime )
```java
class Animal {
    // Method to be overridden
    void sound() {
        System.out.println("Animal makes a sound");
    }
    
    // Overloaded method (different parameters)
    void sound(String type) {
        System.out.println("Animal makes a " + type + " sound");
    }
}

class Dog extends Animal {
    // Overriding the sound method
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

public class Test {
    public static void main(String[] args) {
        Animal myAnimal = new Dog();
        
        // Compile-time: method overloading decides which method to call
        myAnimal.sound();          // Calls overridden method (runtime polymorphism)
        myAnimal.sound("loud");   // Calls overloaded method (compile-time polymorphism)
    }
}
```

#### 10. Explain how Java defines a method signature, and how it helps on overloading and overriding.
- In Java, a method signature is defined by the method's name and the parameter types (order and type, does not include parameter names), but it does not include the return type or exceptions. 
- This signature helps in method overloading by allowing multiple methods in the same class to have the same name but different parameter lists. 
- For method overriding, the subclass method must have the exact same signature as the superclass method (same parameter types, numbers, and order).
```java 
class Animal {
    // Method to be overridden
    void sound() {
        System.out.println("Animal makes a sound");
    }

    // Overloaded methods (same name, different parameters)
    void eat() {
        System.out.println("Animal eats food");
    }

    void eat(String food) {
        System.out.println("Animal eats " + food);
    }
}

class Dog extends Animal {
    // Overriding the sound() method
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.sound();          // Animal makes a sound
        animal.eat();            // Animal eats food
        animal.eat("bones");     // Animal eats bones

        Dog dog = new Dog();
        dog.sound();             // Dog barks (overridden)
        dog.eat();               // Animal eats food (inherited)
        dog.eat("meat");         // Animal eats meat (inherited overloaded)
    }
}
```

#### 11. What is the differences between super and this in ?
In Java, `this` refers to the current instance of the class, allowing access to its fields and methods, while `super` refers to the immediate parent class, enabling access to its methods and constructors, especially when **overridden** in the child class.
```java
class Parent {
    void show() {
        System.out.println("Parent's show method");
    }
}

class Child extends Parent {
    void show() {
        System.out.println("Child's show method");
    }
    
    void display() {
        this.show();     // Calls Child's show method
        super.show();    // Calls Parent's show method
    }
}

public class Test {
    public static void main(String[] args) {
        Child obj = new Child();
        obj.display();
    }
}
```

#### 12. Explain how equals and hashCode work.
- In Java, the `equals()` method is used to compare two objects for logical equality based on their content. 
- The `hashCode()` method returns an **integer hash code value** for the object, which is used in hashing-based collections like HashMap and HashSet to efficiently locate objects. 
- When `equals()` is overridden, `hashCode()` must also be overridden to ensure that equal objects have the same hash code, maintaining the general contract between these two methods.
```java 
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                // same reference
        if (obj == null || getClass() != obj.getClass()) return false;
        Person other = (Person) obj;
        return age == other.age && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        return result;
    }
}
```

#### 13. What is the Java load sequence?
In Java, the load sequence is as follows: first, **static variables and static blocks** are loaded and executed once when the class is first loaded; next, **instance variables** and instance blocks are initialized in the order they appear when an object is created; finally, the **constructor** is called to complete the object creation process.
(debug to show the load sequences)
Static var → Static block → Main → Instance var → Instance block → Constructor.
```java
public class LoadSequenceDemo {

    // Static variable
    static int staticVar = initStaticVar();

    // Static block
    static {
        System.out.println("Static block executed");
    }

    // Instance variable
    int instanceVar = initInstanceVar();

    // Instance block
    {
        System.out.println("Instance block executed");
    }

    // Constructor
    LoadSequenceDemo() {
        System.out.println("Constructor executed");
    }

    // Static method to initialize static variable
    static int initStaticVar() {
        System.out.println("Static variable initialized");
        return 10;
    }

    // Method to initialize instance variable
    int initInstanceVar() {
        System.out.println("Instance variable initialized");
        return 20;
    }

    public static void main(String[] args) {
        System.out.println("Main method started");
        LoadSequenceDemo obj = new LoadSequenceDemo();
    }
}
```

#### 14. What is Polymorphism ? And how Java implements it ?
- Polymorphism in Java is the ability of an object to take on many forms, allowing a single interface to represent different underlying data types. 
- Java implements polymorphism in two main ways: 
  - **compile-time** polymorphism (method **overloading**; same class, multiple methods) 
  - **runtime** polymorphism (method **overriding** through inheritance and dynamic method dispatch; subclass, same signature).
- Example code: see Q9


#### 15. What is Encapsulation ? How Java implements it? And why we need encapsulation?
- Encapsulation is a fundamental concept in object-oriented programming that involves bundling data (variables) and methods (functions) that operate on the data into **a single unit**, typically a class, and restricting direct access to some of the object's components.

- Java implements encapsulation by declaring variables as **private** and providing **public getter and setter** methods to access and update their values.

- We need encapsulation to protect the internal state of an object, enhance code maintainability, and enforce control over how data is accessed or modified.

#### 16. Compare interface and abstract class with use cases.
Before Java 8, interfaces in Java could **only declare abstract methods** without any implementation, and they couldn’t have any instance variables except static final constants. In contrast, abstract classes could have both abstract and concrete methods with implementations, and they could also have instance variables to maintain state.

In Java 8, an interface can have default and static methods with implementations, but cannot hold **state (instance variables)**, while an abstract class can have both implemented and abstract methods and can maintain state through instance variables.

```java

// Interface with a default method
interface MyInterface {
    void abstractMethod();

    default void defaultMethod() {
        System.out.println("This is a default method in the interface.");
    }

    static void staticMethod() {
        System.out.println("This is a static method in the interface.");
    }
}

// Abstract class with implemented and abstract methods, and a field
abstract class MyAbstractClass {
    int value = 10; // instance variable (state)

    abstract void abstractMethod();

    void concreteMethod() {
        System.out.println("This is a concrete method in the abstract class. Value = " + value);
    }
}

// Concrete class implementing the interface
class InterfaceImpl implements MyInterface {
    public void abstractMethod() {
        System.out.println("Implemented abstract method from interface.");
    }
}

// Concrete class extending the abstract class
class AbstractClassImpl extends MyAbstractClass {
    void abstractMethod() {
        System.out.println("Implemented abstract method from abstract class.");
    }
}

public class Main {
    public static void main(String[] args) {
        MyInterface i = new InterfaceImpl();
        i.abstractMethod();
        i.defaultMethod();
        MyInterface.staticMethod();

        MyAbstractClass a = new AbstractClassImpl();
        a.abstractMethod();
        a.concreteMethod();
    }
}

```

