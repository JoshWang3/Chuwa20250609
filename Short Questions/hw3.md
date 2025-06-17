ht
 For all questions below, you should write sample code, which can be ran directly on online compilers such as 
tps://www.onlinegdb.com/online_java_compiler (if possible) to explain your answer
1. Write up Example code to demonstrate the three foundmental concepts of OOP.
    1. Encapsulation: 
    Encapsulation means restricting direct access to some components of an object and only allowing access through public methods to achieve goals. To avoid unintended code modifying.

```java
    class Student{
    private String name;
    private int age;
    private String studentId;

    public Student(String name, int age, String studentId) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Age must be positive");
        }
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}
class encapsulationSample {
    public static void main(String[] args) {
        Student student = new Student("Alice", 20, "S12345");
        student.setAge(21); // Update age using setter
        System.out.println(student);
    }
}

``` 
    2. Polymorphism: Polymorphism allows a single method or interface to be used for different types.
```java

class Animal {
    public void sound() {
        System.out.println("Animal makes a sound");
    }

    // Overloaded methods
    public void eat(String food){
        System.out.println("Animal eats " + food);
    }

    public void eat(int foodAmount) {
        System.out.println("Animal eats " + foodAmount + " units of food");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    @Override
    public void sound() {
        System.out.println("Cat meows");
    }
}

public class PolymorphosmSample {
    public static void main(String[] args) {
        Animal myAnimal = new Dog(); // Polymorphism
        myAnimal.sound(); // Output: Dog barks

        myAnimal = new Cat(); // Polymorphism
        myAnimal.sound(); // Output: Cat meows

        myAnimal.eat("meat"); // Calls the String version
        myAnimal.eat(5); // Calls the int version


        /*
         * Dog barks 
         * Cat meows
         * Animal eats meat
         * Animal eats 5 units of food
         */
    }
}
```

3. Inheritance: Inheritance allows a class to inherit fields and methods from another class.

```java

public class inheritanceSample {
    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.start(); // Inherited from Vehicle
        myCar.drive(); // Defined in Car
    }
}

class Vehicle {
    public void start() {
        System.out.println("Vehicle is starting");
    }
}

class Car extends Vehicle {
    public void drive() {
        System.out.println("Car is driving");
    }
}
```

**1. What is wrapper data type classes (e.g. Integer, Double) in Java and Why we need wrapper class?**

    Wrapper classes provide an object representation for primitive data types. They are used when objects are required instead of primitive types.
    Why we need wrapper classes?
    To use primitives in collections (e.g., ArrayList<Integer>).
    For utility methods (e.g., Integer.parseInt()).
    To work with frameworks that require objects (e.g., Reflection, Serialization).


**2. What is the difference between HashMap and HashTable?**

| Feature          | HashMap                                   | HashTable                          |
| ---------------- | ----------------------------------------- | ---------------------------------- |
| Thread Safety    | Not thread-safe                           | Thread-safe                        |
| Performance      | Faster (no synchronization)               | Slower (synchronization)           |
| Null Keys/Values | Allows one null key, multiple null values | Does not allow null keys or values |

```java
import java.util.HashMap;
import java.util.Hashtable;

public class HashMapVsHashTable {
    public static void main(String[] args) {
        // Example with HashMap
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "Apple");
        hashMap.put(2, "Banana");
        hashMap.put(null, "Cherry"); // Allows one null key
        hashMap.put(3, null);       // Allows multiple null values

        System.out.println("HashMap:");
        hashMap.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        // Example with Hashtable
        Hashtable<Integer, String> hashTable = new Hashtable<>();
        hashTable.put(1, "Apple");
        hashTable.put(2, "Banana");
        // hashTable.put(null, "Cherry"); // Throws NullPointerException (null keys not allowed)
        // hashTable.put(3, null);       // Throws NullPointerException (null values not allowed)

        System.out.println("\nHashtable:");
        hashTable.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
    }
}

```

**3. What is String pool in Java and why we need String pool? Explain String immunity.**

A String in Java is immutable, meaning that once a String object is created, its value cannot be changed. Any operation that modifies a String creates a new object instead of altering the original one. The String Pool in Java is a special memory region inside the heap where Java stores String literals. This pool helps in optimizing memory usage by reusing immutable String objects.

```java
String s1 = "Hello";
String s2 = "Hello";
System.out.println(s1 == s2); // Output: true (both refer to the same object in the pool)

String s3 = new String("Hello");
System.out.println(s1 == s3); // Output: false (s3 is created outside the pool)

```

**4. Explain garbage collection? Explain types of garbage collection.**

    Garbage Collection in Java is a process managed by the JVM to automatically reclaim memory occupied by objects that are no longer accessible or needed. It eliminates the need for developers to explicitly deallocate memory, helping to avoid memory leaks and other memory management issues.

    How It Works:
    The JVM keeps track of all object references.
    If an object is no longer referenced, it is considered garbage.
    The Garbage Collector identifies and removes these objects, freeing up memory.
    1. Serial Garbage Collector: Uses a single thread to perform garbage collection. Best suited for single-threaded applications.
    2. Parallel Garbage Collector: Uses multiple threads to perform garbage collection. Suitable for multi-threaded applications.
    3. CMS (Concurrent Mark-Sweep) Garbage Collector: Performs garbage collection concurrently with the application threads, reducing pause times.

```java
    public class GarbageCollectorExample {
    public static void main(String[] args) {
        GarbageCollectorExample obj1 = new GarbageCollectorExample();
        GarbageCollectorExample obj2 = new GarbageCollectorExample();

        // Nullify references, making the objects eligible for garbage collection
        obj1 = null;
        obj2 = null;

        // Suggest JVM to run Garbage Collector
        System.gc();

        System.out.println("Garbage collection has been requested.");
    }

    @Override
    protected void finalize() throws Throwable {
        // This method is called by the garbage collector before reclaiming memory
        System.out.println("Garbage collector called. Object is being garbage collected.");
    }
}

```


**5. What are access modifiers and their scopes in Java?** 

   
   | **Modifier**  | **Scope**                                                                  |
   | ------------- | -------------------------------------------------------------------------- |
   | **Private**   | Accessible only within the same class                                      |
   | **Default**   | Accessible within the same package                                         |
   | **Protected** | Accessible within the same package and by subclasses in different packages |
   | **Public**    | Accessible from any class in any package                                   |


**6. Explain final key word? (Filed, Method, Class)**
1. Final Fields; A final field is a constant. Once it is assigned a value, it cannot be modified. It must be initialized either: at the time of declaration, or in the constructor of the class.
```java
public class FinalFieldExample {
    final int MAX_VALUE = 100; // Initialized at declaration

    final int MIN_VALUE;

    // Constructor for initializing the final field
    public FinalFieldExample(int minValue) {
        this.MIN_VALUE = minValue;
    }

    public void displayValues() {
        System.out.println("MAX_VALUE: " + MAX_VALUE);
        System.out.println("MIN_VALUE: " + MIN_VALUE);
    }
}
```
2. Final Methods: A final method cannot be overridden by subclasses.
It is used when you want to prevent modification of the method's behavior in derived classes. Useful in scenarios where the method implementation is critical and should not be altered.
```java
public class FinalMethodExample {
    public final void displayMessage() {
        System.out.println("This is a final method.");
    }
}

public class SubClass extends FinalMethodExample {
    // Uncommenting the below code will cause a compilation error
    // public void displayMessage() {
    //     System.out.println("Cannot override a final method.");
    // }
}

```
3. Final Classes: A final class cannot be extended (inherited). It is used to prevent others from creating subclasses, ensuring the class's implementation remains unchanged.
```java
public final class FinalClassExample {
    public void displayMessage() {
        System.out.println("This is a final class.");
    }
}

// Uncommenting the below code will cause a compilation error
// public class SubClass extends FinalClassExample {
// }

```

**7. Explan static keyword? (Filed, Method, Class). When do we usually use it?**
1. Static Fields: A static field is shared among all instances of the class. It is associated with the class itself, not with any specific object. Changes made to a static field are reflected across all instances of the class.
   When to Use:When you need a variable shared across all instances
```java
public class StaticFieldExample {
    static int count = 0; // Static field

    public StaticFieldExample() {
        count++; // Increment count whenever an object is created
    }

    public static void displayCount() {
        System.out.println("Number of objects created: " + count);
    }
}

public class Main {
    public static void main(String[] args) {
        StaticFieldExample obj1 = new StaticFieldExample();
        StaticFieldExample obj2 = new StaticFieldExample();

        StaticFieldExample.displayCount(); // Output: Number of objects created: 2
    }
}

```
2. Static Methods
A static method belongs to the class rather than an instance. It can be called without creating an object of the class. Static methods can only access static fields or other static methods directly.
    When to Use: When the method does not depend on instance variables or methods.
For utility or helper methods 
```java
public class StaticMethodExample {
    static int square(int num) { // Static method
        return num * num;
    }

    public static void main(String[] args) {
        int result = StaticMethodExample.square(5); // Call without creating an object
        System.out.println("Square of 5: " + result); // Output: Square of 5: 25
    }
}

```
3. Static Classes
A static class can only be a nested class. A static nested class does not require an instance of the outer class to be instantiated. It can access only the static members of the outer class.
    When to Use: When the nested class logically belongs to the outer class but does not require access to its instance variables or methods.
```java
public class OuterClass {
    static int outerStaticVar = 10;

    static class StaticNestedClass {
        void display() {
            System.out.println("Outer static variable: " + outerStaticVar);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        OuterClass.StaticNestedClass nestedObj = new OuterClass.StaticNestedClass();
        nestedObj.display(); // Output: Outer static variable: 10
    }
}
```

**8. What is the differences between overriding and overloading?**

Overriding occurs when a subclass provides a specific implementation for a method that is already defined in its parent class.Method signature (name, parameters, and return type) must be exactly the same as in the parent class.
```java
class Parent {
    void display() {
        System.out.println("This is the parent class method.");
    }
}

class Child extends Parent {
    @Override
    void display() {
        System.out.println("This is the child class method.");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent obj = new Child();
        obj.display(); // Output: This is the child class method.
    }
}

```
Overloading occurs when two or more methods in the same class have the same name but differ in their parameter list (type, number, or both). Methods must have the same name but different parameter lists.
Return type can be different, but it does not play a role in distinguishing overloaded methods.Overloading is resolved at compile-time.

```java
class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.add(2, 3));          // Output: 5
        System.out.println(calc.add(2.5, 3.5));      // Output: 6.0
        System.out.println(calc.add(1, 2, 3));       // Output: 6
    }
}

```

| **Aspect**            | **Overriding**                                         | **Overloading**                                                             |
| --------------------- | ------------------------------------------------------ | --------------------------------------------------------------------------- |
| **Definition**        | Redefining a method in a subclass.                     | Defining multiple methods with the same name but different parameter lists. |
| **Inheritance**       | Requires inheritance (parent-child relationship).      | Does not require inheritance.                                               |
| **Method Signature**  | Must be exactly the same as the parent class method.   | Must differ in the parameter list (type, number, or both).                  |
| **Return Type**       | Must be the same or covariant.                         | Can be different, but it doesn't distinguish methods.                       |
| **Polymorphism Type** | Runtime polymorphism.                                  | Compile-time polymorphism.                                                  |
| **Access Modifiers**  | Cannot reduce the visibility of the overridden method. | No such restriction.                                                        |
| **Annotations**       | Often uses `@Override` annotation.                     | No annotation is required.                                                  |



**9.  Explain how Java defines a method signature, and how it helps on overloading and overriding.**

  In Java, a method signature is defined by the method's name and its parameter list. It does not include the return type or any exceptions thrown by the method. 
  In Java, we can have multiple methods with the same name in a class as long as their parameter lists are different. This is known as method overloading. Overloading relies on different parameter lists (type, number, or both) to distinguish between methods
  When a subclass provides a specific implementation for a method that is already defined in its superclass, it is overriding the method. The method signature (name and parameter list) must be exactly the same as the method in the superclass.
  Method signatures help the Java compiler distinguish between overloaded methods within the same class and ensure correct method overriding in subclasses.

**10.  What is the differences between super and this?**

    this refers to the current instance of the class. Used to access the current object's members. this helps in differentiating between instance variables and method parameters when they have the same name.It can be used to call another constructor in the same class.

```java
        class Example {
        int value;

        Example(int value) {
            this.value = value; // Refers to the instance variable 'value'
        }
    }

```
    super refers to the parent of the current object. It is used to access members of the superclass and helps in calling the superclass's constructor or methods that are overridden in the subclass.
```java
class Parent {
    int value = 10;
}

class Child extends Parent {
    int value = 20;

    void display() {
        System.out.println(this.value); // Refers to Child's value
        System.out.println(super.value); // Refers to Parent's value
    }
}

```

**11.  Explain how equals and hashCode work.**

The equals() method is used to compare two objects for equality. By default, the implementation in the Object class checks for reference equality 

```java
public boolean equals(Object obj) {
    return (this == obj);
}

```

The hashCode() method returns an integer value, called the hash code, which is used to determine the bucket location for an object in hash-based collections. The default implementation of hashCode() in the Object class generates a hash code based on the memory address of the object.

    The relationship between equals() and hashCode() is defined by the following rules:

    1. If two objects are equal (as determined by equals()), they must have the same hash code.
    2. If two objects have the same hash code, they are not necessarily equal (collisions can occur).
    3. If equals() is overridden, hashCode() must also be overridden to maintain consistency.

**12.  What is the Java load sequence?**

    The Java load sequence refers to the process by which Java loads, links, and initializes classes and interfaces during runtime. 

    1. Class Loading: Classes in Java are loaded into memory by the class loader when they are first referenced during runtime. The class loader ensures that the bytecode of the class is read and loaded into the JVM. The class loading process involves:
       1. Bootstrap Class Loader: Loads core Java classes (e.g., classes from java.lang and java.util).
       2. Extension Class Loader: Loads classes from the lib/ext directory.
       3. Application Class Loader: Loads classes from the application's classpath.
    2. Linking: After a class is loaded, it undergoes linking. Linking has three sub-steps:
       1. Verification: Ensures that the bytecode is valid and follows JVM specifications.
       2. Preparation: Allocates memory for static variables and initializes them to default values.
       3. Resolution: Resolves symbolic references in the class.
    3. Initialization: Once the class is linked, it is initialized. This involves:
       1. Static Initializers: Executing static blocks and initializing static variables.
       2. Instance Initialization: When an object is created, instance variables are initialized, and the constructor is executed.

**13. What is Polymorphism ? And how Java implements it ?**
    
    polymorphism allows methods, objects, or classes to take on multiple forms, enabling flexibility and reusability. iT allows the same interface or method to behave differently based on the object that invokes it.
    1. Compile-Time Polymorphism (Static Polymorphism): Achieved through method overloading.
    2. Run-Time Polymorphism (Dynamic Polymorphism): Achieved through method overriding.

    Implementation: see code sample in Q1.


**14. What is Encapsulation ? How Java implements it? And why we need encapsulation?** 
    
    Encapsulation is the process of wrapping data (variables) and methods (functions) together into a single unit, typically a class. Encapsulation restricts direct access to some of the object's components, which helps prevent accidental interference and misuse of the data. It is about hiding the internal details of how an object works and only exposing a controlled interface to interact with it.

    Implementation:
    1. declare class variables as private
    2. create public getter setter method
    3. classes act as container of data and method

    Why need encapsulation?
    1. Data hiding: Encapsulation hides the internal implementation details of a class, ensuring that sensitive data is not accessible directly by external code.This protects the object from unintended modifications.
    2. Control Access: By using getter and setter methods, you can control how the variables are accessed or modified.
    3. Improves Code Maintainability: Encapsulation makes the code modular and easier to maintain. Changes to the internal implementation do not affect external code as long as the interface remains the same.
    4. Enhances Security: Encapsulation ensures that the internal state of an object is protected from unauthorized or invalid access.
    5. Flexibility and Reusability: By exposing only the necessary parts of an object, encapsulation makes it easier to reuse code.

    In conclusion: encapsulation improves code security, made code more modular and easy to test and supprots abstrations.


**15. Compare interface and abstract class with use cases.**

    Interface:An interface is a blueprint of a class that contains only abstract methods (before Java 8) or default/static methods (from Java 8 onwards).It specifies what a class must do but not how it does it. 
    When to use:
    1. Use an interface when a class needs to inherit behavior from multiple sources.
    2. Interfaces are best when we want to define a contract that multiple classes must follow.

```java
interface Animal {
    void sound(); // Abstract method
}

class Dog implements Animal {
    public void sound() {
        System.out.println("Dog barks");
    }
}

class Cat implements Animal {
    public void sound() {
        System.out.println("Cat meows");
    }
}


```

    Abstract Class: An abstract class is a class that can have both abstract methods (without implementation) and concrete methods (with implementation). It provides partial abstraction and can include fields, constructors, and methods.

    When to use:
    1. Use an abstract class when we need to provide a base class with shared functionality for derived classes.
    2. Abstract classes are useful when we want to provide some default behavior while forcing subclasses to implement specific methods

```java
abstract class Vehicle {
    abstract void start(); // Abstract method
    void stop() { // Concrete method
        System.out.println("Vehicle stopped");
    }
}

class Car extends Vehicle {
    void start() {
        System.out.println("Car started");
    }
}

```

| **Feature**                          | **Interface**                                                                       | **Abstract Class**                                                 |
| ------------------------------------ | ----------------------------------------------------------------------------------- | ------------------------------------------------------------------ |
| **Methods**                          | Can have only abstract methods (before Java 8) or default/static methods (Java 8+). | Can have both abstract and concrete methods.                       |
| **Fields**                           | Can only have public, static, and final fields (constants).                         | Can have instance variables (non-static fields).                   |
| **Inheritance**                      | A class can implement multiple interfaces (multiple inheritance).                   | A class can extend only one abstract class (single inheritance).   |
| **Constructors**                     | Cannot have constructors.                                                           | Can have constructors.                                             |
| **Access Modifiers**                 | Methods in an interface are implicitly `public`.                                    | Methods can have any access modifier (public, protected, private). |
| **Use of `implements` or `extends`** | A class implements an interface.                                                    | A class extends an abstract class.                                 |
| **Default Implementation**           | Java 8 introduced default methods with implementation in interfaces.                | Abstract classes can always have methods with implementation.      |

