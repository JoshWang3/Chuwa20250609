### Question 1: 

```java
// Main class to run the demonstration
public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Animal("Generic Animal");
        Animal myDog = new Dog("Buddy");
        Animal myCat = new Cat("Whiskers");
        
        System.out.println("My dog's name is: " + myDog.getName()); // Outputs: My dog's name is: Buddy
        myDog.setName("Lucky");
        System.out.println("My dog's new name is: " + myDog.getName()); // Outputs: My dog's new name is: Lucky

        // Polymorphism: Calling the same method on different objects
        myAnimal.makeSound(); // Outputs: The animal makes a sound.
        myDog.makeSound();    // Outputs: The dog barks.
        myCat.makeSound();    // Outputs: The cat meows.
    }
}

// Inheritance: Base class (Superclass)
abstract class Animal {
    // Encapsulation: private field, public getter and setter
    private String name;

    public Animal(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    // Method to be overridden
    public abstract void makeSound() { // Polymorphism: Abstract method
    }
}

// Inheritance: Subclass inheriting from Animal
class Dog extends Animal {
    public Dog(String name) {
        super(name); // Call the constructor of the superclass
    }

    // Polymorphism: Overriding the makeSound method
    @Override
    public void makeSound() {
        System.out.println("The dog barks.");
    }
}

// Inheritance: Another subclass inheriting from Animal
class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    // Polymorphism: Overriding the makeSound method
    @Override
    public void makeSound() {
        System.out.println("The cat meows.");
    }
}
```

### Question 2: 

Wrapper classes in Java are classes that wrap around primitive data types.
Wrapper classes are used in the Collections framework to store objects.
Wrapper class objects can be null, which is useful for representing an absent value.
Wrapper classes provide useful static methods for converting between types and parsing strings.

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10); // Autoboxing: primitive int is converted to Integer object
        numbers.add(20);

        System.out.println("ArrayList of Integers: " + numbers);
        
        String numStr = "123";
        int parsedNum = Integer.parseInt(numStr);
        System.out.println("Parsed integer: " + (parsedNum + 7));
    }
}
```

### Question 3:

HashMap is not synchronized and therefore not thread-safe. 
HashTable is synchronized and thread-safe, but it is slower due to the overhead of synchronization.

```java
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // --- 1. Demonstrate HashMap is NOT thread-safe ---
        Map<Integer, String> hashMap = new HashMap<>();
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                hashMap.put(i, "T1");
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
                hashMap.put(i, "T2");
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Actual HashMap size: " + hashMap.size());
        // The expected size is not guaranteed to be 2000 due to synchronization issues.
        
        // --- 2. Demonstrate Hashtable IS thread-safe ---
        Map<Integer, String> hashtable = new Hashtable<>();
        
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                hashtable.put(i, "T3");
            }
        });

        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                hashtable.put(i, "T4");
            }
        });
        
        t3.start();
        t4.start();
        t3.join();
        t4.join();

        // The size will be exactly 2000 because Hashtable's methods are synchronized
        System.out.println("Actual Hashtable size: " + hashtable.size());
    }
}

```

Question 4: 

The String pool is a special storage area in the Java heap. 
When you create a String literal, the JVM checks the String pool first. 
If the string already exists, a reference to the existing string is returned. 
If not, a new String object is created in the pool.

We need the String pool for memory efficiency and performance. 
By reusing strings, we reduce the overall memory footprint and speed up creation.

String immutability means that once a String object is created, its value cannot be changed. 
Any operation on editing the string actually creates a new String object. 

```java
public class Main {
    public static void main(String[] args) {
        // s1 and s2 refer to the SAME object in the String pool
        String s1 = "hello";
        String s2 = "hello";

        // s3 is created using the 'new' keyword, so it's a NEW object in the heap
        String s3 = new String("hello");

        System.out.println("s1 == s2: " + (s1 == s2)); // true (same object)
        System.out.println("s1 == s3: " + (s1 == s3)); // false (different objects)
        
        // To compare the actual content of strings, use .equals()
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // true (same content)
    }
}
```

### Question 5: 

While the implementation details are complex, major types of garbage collectors in modern JVMs include:

Serial GC: Uses a single thread for garbage collection. 
Parallel GC: Uses multiple threads for garbage collection to speed up the process. 
Garbage-First GC: It divides the heap into regions and prioritizes collecting from regions with the most garbage.
Z Garbage Collector: A low-latency garbage collector designed for applications that require very short pause times.

```java
public class Main {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1 = null;
        list2 = null;
        System.gc();
        System.out.println("Garbage collection completed.");
    }
}
```

### Question 6: 

Public modifiers allow access from any other class.
Protected modifiers allow access within the same package and subclasses.
Default (no modifier) allows access within the same package only.
Private modifiers restrict access to the same class only.

```java
public class Parent {
    public String publicVar = "Public Variable";
    protected String protectedVar = "Protected Variable";
    String defaultVar = "Default Variable";
    private String privateVar = "Private Variable";
}

public class Child extends Parent {
    public void display() {
        System.out.println("Public: " + publicVar); // Accessible
        System.out.println("Protected: " + protectedVar); // Accessible
        System.out.println("Default: " + defaultVar); // Accessible within the same package
        System.out.println("Private: " + privateVar); // Not accessible, would cause a compiling error
    }
}
```
### Question 7:
When applied to a variable, it means the variable's value cannot be changed after initialization. 
When applied to a method, it means the method cannot be overridden in subclasses. 
When applied to a class, it means the class cannot be subclassed.

```java
// 1. final class: Cannot be extended
final class FinalClass {
}

class AnotherClass extends FinalClass {} // This would cause a compilation error

class ParentClass {
    // 2. final variable: A constant
    public final int MY_CONSTANT = 42;

    public void changeConstant() {
         MY_CONSTANT = 100; // This would cause a compilation error
    }

    // 3. final method: Cannot be overridden
    public final void showMessage() {
        System.out.println("This is a final method.");
    }
}

class ChildClass extends ParentClass {
     @Override
     public void showMessage() { // This would cause a compilation error
         System.out.println("Trying to override.");
     }
}

public class Main {
    public static void main(String[] args) {
        ParentClass obj = new ParentClass();
        System.out.println("Constant value: " + obj.MY_CONSTANT);
        obj.showMessage();
    }
}
```
### Question 8:

Static Field: A static variable is shared among all instances of a class. 
Static Method: A static method can be called without creating an instance of the class. 
It can only access other static members of the class directly.
Static Class: A class can only be static if it's an inner class. 
A static class can be instantiated without an instance of the outer class.

When to use static:
For properties that are common to all objects.
For utility methods that don't depend on the state of a specific object.
For maintaining a shared state, like a counter for all objects of a class.

```java
class Car {

    private String model;
    
    public static int numberOfCars = 0;

    public Car(String model) {
        this.model = model;
        numberOfCars++; 
    }

    public String getModel() {
        return model;
    }
    
    public static void displayTotalCars() {
        System.out.println("Total number of cars: " + numberOfCars);
        System.out.println(model); // Error: Cannot access non-static field
    }
}

public class Main {
    public static void main(String[] args) {
        Car.displayTotalCars();

        Car car1 = new Car("Toyota");
        Car car2 = new Car("Honda");

        System.out.println("Car 1 model: " + car1.getModel());
        System.out.println("Number of cars created: " + Car.numberOfCars);
        
        Car.displayTotalCars();
    }
}
```

### Question 9: 
Overriding provides a specific implementation of a method that is already provided by its superclass.
Overloading allows multiple methods in the same class to have the same name but different parameters.

```java
class MathOperations {
    // Overloading: Same method name, different parameter types or counts
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
}

class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Cat extends Animal {
    // Overriding: Same signature as the superclass method
    @Override
    public void makeSound() {
        System.out.println("Cat meows");
    }
}

public class Main {
    public static void main(String[] args) {
        MathOperations math = new MathOperations();
        System.out.println("Sum (int): " + math.add(2, 3));
        System.out.println("Sum (double): " + math.add(2.5, 3.5));
        
        Animal myAnimal = new Cat();
        myAnimal.makeSound(); // Calls the Cat's version
    }
}
```

### Question 10:
In Java, a method signature consists of the method's name and parameters' number, type, and order.
Overloading is possible because the compiler can distinguish between methods with the same name as long as their signatures (i.e., their parameter lists) are different.
For a method to be overridden in a subclass, it must have the exact same method signature as the method in the superclass. This ensures that when you call the method on an object, the JVM knows exactly which version (the superclass's or the subclass's) to execute.

```java
class MathOperations {
    // Overloading: Same method name, different parameter types or counts
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }
}

class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Cat extends Animal {
    // Overriding: Same signature as the superclass method
    @Override
    public void makeSound() {
        System.out.println("Cat meows");
    }
}

public class Main {
    public static void main(String[] args) {
        MathOperations math = new MathOperations();
        System.out.println("Sum (int): " + math.add(2, 3));
        System.out.println("Sum (double): " + math.add(2.5, 3.5));

        Animal myAnimal = new Cat();
        myAnimal.makeSound(); // Calls the Cat's version
    }
}
```

### Question 11:
"This" is a reference variable that refers to the current object in a method or constructor.
"Super" is a reference variable that refers to the superclass of the current object.

```java
class Person {
    String name;
    
    public Person(String name) {
        this.name = name;
    }

    public void display() {
        System.out.println("Name: " + name);
    }
}

class Employee extends Person {
    String empId;

    public Employee(String name, String empId) {
        super(name);
        this.empId = empId;
    }
    
    @Override
    public void display() {
        super.display();
        System.out.println("Employee ID: " + this.empId);
    }
}

public class Main {
    public static void main(String[] args) {
        Employee emp = new Employee("Jane Doe", "E12345");
        emp.display();
    }
}
```

### Question 12:
**equals(Object obj)**: 
This method checks if two objects are "equal" in terms of their content or state. The default implementation in the Object class simply checks if two references point to the same memory location (==). 
It can be overridden to provide a meaningful comparison of the object's fields.

**hashCode()**:
This method returns an integer representation of the object.

If obj1.equals(obj2) is true, then obj1.hashCode() must be equal to obj2.hashCode().
If obj1.equals(obj2) is false, their hash codes do not have to be different.

```java
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;

class Student {
    private String studentId;
    private String name;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public String toString() {
        return name;
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("101", "Alice");
        Student s2 = new Student("101", "Alice V2"); // Same ID, different name

        System.out.println("s1.equals(s2): " + s1.equals(s2)); // true (because ID is the same)
        System.out.println("s1.hashCode(): " + s1.hashCode());
        System.out.println("s2.hashCode(): " + s2.hashCode());
        
        Map<Student, String> studentGrades = new HashMap<>();
        studentGrades.put(s1, "A");
        
        // Because s1 and s2 are "equal", this will find the entry for s1
        System.out.println("Grade for s2: " + studentGrades.get(s2)); // Prints "A"
    }
}
```

### Question 13: 
Java Class Loading Sequence:
1. **Loading**: The class loader loads the class file into memory.
2. **Linking**: The class is linked, which includes verification, preparation (allocating memory for static variables), and resolution (resolving symbolic references).
3. **Initialization**: The class's static initializers and static blocks are executed.

```java
class MyClass {
    // 3. Static variable initialized with actual value
    public static String staticField = "Static Field Initialized";

    // 4. Static block executed
    static {
        System.out.println("Static block executed.");
    }

    // 5. Instance block executed when an object is created
    {
        System.out.println("Instance block executed.");
    }

    // 6. Constructor executed
    public MyClass() {
        System.out.println("Constructor executed.");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Main method started.");
        
        // 1. Class 'MyClass' is requested for the first time
        // 2. Loading, Linking, and Initialization happens here
        System.out.println(MyClass.staticField);
        
        System.out.println("Creating instance: ");
        new MyClass();
    }
}
// Expected Output:
// Main method started.
// Static block executed.
// Static Field Initialized
// Creating instance:
// Instance block executed.
// Constructor executed.
```

### Question 14:
Polymorphism is the ability of an object to take on many forms. 
In OOP, it means that a single action can be performed in different ways.
Java primarily implements polymorphism through method overriding and method overloading.
Runtime Polymorphism:
This is achieved through method overriding. The decision of which method to call is made at runtime, based on the actual object type.
Compile-time Polymorphism: 
This is achieved through method overloading. The compiler decides which method to call at compile time, based on the method's signature.

```java
// Runtime Polymorphism Example
class Shape {
    void draw() {
        System.out.println("Drawing a shape");
    }
}

class Circle extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing a circle");
    }
}

class Square extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing a square");
    }
}

public class Main {
    public static void main(String[] args) {
        // A reference of type Shape can hold an object of type Circle or Square
        Shape myShape;
        
        myShape = new Circle();
        myShape.draw(); // At runtime, Java calls the Circle's draw() method

        myShape = new Square();
        myShape.draw(); // At runtime, Java calls the Square's draw() method
    }
}
```

### Question 15:
Encapsulation is the mechanism of bundling the data (variables) and the code that operates on the data (methods) together as a single unit. 
It also involves restricting direct access to some of an object's components, which is a key part of data hiding.

How to implement encapsulation in Java:
1. Use private variables to restrict direct access.
2. Provide public getter and setter methods to access and modify the private variables.

Why do we need encapsulation:
1. Control: It gives you control over the data. You can ensure that values are in a valid state before they are assigned.
2. Flexibility and Maintenance: The internal implementation of a class can be changed without affecting the code that uses it, as long as the public methods remain the same.
3. Security: Data hiding protects the object's internal state from outside interference and misuse.

```java
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance > 0) {
            this.balance = initialBalance;
        }
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(100.0);
        
        // Cannot access the balance directly
        account.balance = -500; // Compile Error
        
        account.deposit(50.0);
        System.out.println("Current Balance: " + account.getBalance());
        
        account.deposit(-20.0); // Validation in action
        System.out.println("Current Balance: " + account.getBalance());
    }
}
```

### Question 16:
Use an Abstract Class when you have a group of tightly related classes that share common code.
Use an Interface when you want to define a role or behavior that unrelated classes can share. 

```java
// Use Case: Abstract Class for related objects
abstract class Vehicle {
    protected String brand;
    
    public Vehicle(String brand) {
        this.brand = brand;
    }
    
    public String getBrand() {
        return brand;
    }
    
    abstract void startEngine();
}

class Car extends Vehicle {
    public Car(String brand) {
        super(brand);
    }
    
    @Override
    void startEngine() {
        System.out.println("Car engine starts with a key.");
    }
}


// Use Case: Interface for defining a capability
interface Flyable {
    void fly(); 
}

class Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("Bird is flapping its wings to fly.");
    }
}

class Airplane implements Flyable {
    @Override
    public void fly() {
        System.out.println("Airplane is using its engines to fly.");
    }
}

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car("Ford");
        System.out.println("My car is a " + myCar.getBrand());
        myCar.startEngine();
        
        System.out.println("---");
        
        Flyable flyer1 = new Bird();
        Flyable flyer2 = new Airplane();
        
        flyer1.fly();
        flyer2.fly();
    }
}
```