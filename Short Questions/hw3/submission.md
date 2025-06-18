# hw3 submission

## Q1: Write up Example code to demonstrate the three foundmental concepts of OOP. 1. Encapsulation; 2. Polymorphism; 3. Inheritance;

### Answer:
Encapsulation: Person uses private fields and public getters/setters to control access.

Inheritance: Dog and Cat extend the base class Animal.

Polymorphism: Animal references (myDog and myCat) point to subclasses and call overridden methods at runtime.

```java
public class Main {
    public static void main(String[] args) {
        // Encapsulation example
        Person person = new Person();
        person.setName("Alice");
        person.setAge(30);
        System.out.println("Person Name: " + person.getName());
        System.out.println("Person Age: " + person.getAge());

        // Inheritance and Polymorphism example
        Animal myDog = new Dog();  // Polymorphism: Animal reference to Dog object
        myDog.makeSound();

        Animal myCat = new Cat();  // Polymorphism: Animal reference to Cat object
        myCat.makeSound();
    }
}

// 1. Encapsulation: Data is hidden using private access and exposed via public getters/setters
class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }
    public void setName(String newName) {
        name = newName;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int newAge) {
        if (newAge > 0) {
            age = newAge;
        }
    }
}

// 2. Inheritance: Animal is a parent class
class Animal {
    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

// 3. Polymorphism: Dog and Cat override makeSound()
class Dog extends Animal {
    public void makeSound() {
        System.out.println("Woof! Woof!");
    }
}

class Cat extends Animal {
    public void makeSound() {
        System.out.println("Meow! Meow!");
    }
}
```

## Q2: What is wrapper data type classes (e.g. Integer, Double) in Java and Why we need wrapper class?

### Answer:
Wrapper classes in Java provide a way to wrap primitive data types (int, double, char, etc.) into objects.

Why do We Need Wrapper Classes?

Collections like ArrayList can't hold primitives: they can only hold objects.

Utility Methods: wrapper classes provide useful methods (e.g., Integer.parseInt()).

Autoboxing and Unboxing: Java can automatically convert between primitives and objects.

Nullability: primitives can't be null, but wrapper objects can.

```java
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 1. Manual Boxing and Unboxing
        int a = 5;
        Integer aWrapper = Integer.valueOf(a); // Boxing
        int aUnboxed = aWrapper.intValue();    // Unboxing
        System.out.println("Original int: " + a + ", After boxing and unboxing: " + aUnboxed);

        // 2. Autoboxing and Unboxing (Java does it automatically)
        Integer bWrapper = 10;  // Autoboxing
        int b = bWrapper;       // Unboxing
        System.out.println("Autoboxed Integer: " + bWrapper + ", Unboxed int: " + b);

        // 3. Use in Collections (e.g., ArrayList can't use int directly)
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(100);  // Autoboxing int to Integer
        numbers.add(200);
        int sum = 0;
        for (Integer num : numbers) {
            sum += num;   // Unboxing Integer to int
        }
        System.out.println("Sum of numbers in ArrayList: " + sum);

        // 4. Utility method
        String str = "123";
        int parsed = Integer.parseInt(str);
        System.out.println("Parsed int from String: " + parsed);
    }
}
```

## Q4: What is String pool in Java and why we need String pool? Explain String immunity.

### Answer:
The String Pool is a special memory area in the Java heap where Java stores string literals. When you create a string using a literal, Java checks if the same value exists in the String Pool. If it exists, it reuses the reference — no new object is created.

Why Do We Need String Pool? Memory Optimization: Avoids creating multiple duplicate string objects. Performance Boost: Reusing immutable string objects reduces memory and CPU usage.

What is String Immutability? Immutability means once a String object is created, its value cannot be changed. Any operation that seems to change a string actually creates a new String object.

```java
public class Main {
    public static void main(String[] args) {
        // String literals stored in the pool
        String s1 = "hello";
        String s2 = "hello";

        // New String object (not in the pool)
        String s3 = new String("hello");

        // Comparing references
        System.out.println("s1 == s2: " + (s1 == s2)); // true (same pool object)
        System.out.println("s1 == s3: " + (s1 == s3)); // false (s3 is a new object)

        // String immutability
        String s4 = "abc";
        String s5 = s4;
        s4 = s4 + "def";  // Creates a new object, s4 now points to a different object

        System.out.println("\nOriginal s5: " + s5);  // still "abc"
        System.out.println("Modified s4: " + s4);    // now "abcdef"

        // Intern method: manually put string in pool
        String s6 = new String("world").intern();
        String s7 = "world";
        System.out.println("\ns6 == s7: " + (s6 == s7)); // true (both point to pool)
    }
}
```

## Q5: Explain garbage collection? Explain types of garbage collection.

### Answer:
Garbage Collection is Java’s automatic process to:

Identify and remove objects that are no longer used.

Free up memory to prevent memory leaks.

Reduce the burden of manual memory management.

The JVM tracks object references. If an object is no longer reachable, it becomes eligible for garbage collection.

```java
public class Main {
    public static void main(String[] args) {
        // Object eligible for GC
        MyObject obj = new MyObject("First");
        obj = null; // No reference → eligible for GC

        // Anonymous object (not stored in a variable)
        new MyObject("Second");

        // Suggest JVM to run GC
        System.gc();

        // Give the GC some time to run
        try {
            Thread.sleep(1000); // 1 second pause
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End of main method.");
    }
}

class MyObject {
    private String name;

    MyObject(String name) {
        this.name = name;
        System.out.println("Created: " + name);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Garbage collected: " + name);
    }
}
```

## Q6: What are access modifiers and their scopes in Java?

### Answer:
Access modifiers in Java control visibility (scope) of classes, methods, and variables to other parts of the program.

public: Accessible from anywhere (same class, package, subclass, outside package).

protected: Accessible from same package and subclasses, even in different packages.

default (no keyword): Accessible only within the same package.

private: Accessible only within the same class.

```java
// Class in same file to simulate package-level access
class BaseClass {
    public int publicVar = 1;
    protected int protectedVar = 2;
    int defaultVar = 3;         // default (package-private)
    private int privateVar = 4;

    public void showAccess() {
        System.out.println("Inside BaseClass:");
        System.out.println("publicVar: " + publicVar);
        System.out.println("protectedVar: " + protectedVar);
        System.out.println("defaultVar: " + defaultVar);
        System.out.println("privateVar: " + privateVar);
    }
}

public class Main extends BaseClass {
    public static void main(String[] args) {
        BaseClass obj = new BaseClass();
        obj.showAccess();

        System.out.println("\nAccess from Main class:");
        System.out.println("publicVar: " + obj.publicVar);
        System.out.println("protectedVar: " + obj.protectedVar); // allowed (subclass & same file)
        System.out.println("defaultVar: " + obj.defaultVar);     // allowed (same package)
        // System.out.println("privateVar: " + obj.privateVar);  // ❌ Error: private not accessible
    }
}
```

## Q7: Explain final key word? (Filed, Method, Class)

### Answer:
Field (variable): Value cannot be changed once assigned.

Method: Method cannot be overridden in a subclass.

Class: Class cannot be extended/inherited.

```java
final class FinalClass {
    public void show() {
        System.out.println("Inside final class method");
    }
}

// Uncommenting the below line will cause a compile-time error
// class SubClass extends FinalClass {}  // Cannot inherit from final class

class Base {
    public final void display() {
        System.out.println("Final method cannot be overridden");
    }

    public void greet() {
        System.out.println("Hello from Base");
    }
}

class Derived extends Base {
    // Uncommenting this will cause error:
    // public void display() { System.out.println("Try override"); }

    @Override
    public void greet() {
        System.out.println("Hello from Derived");
    }
}

public class Main {
    public static void main(String[] args) {
        // Final variable (field)
        final int age = 30;
        System.out.println("Age: " + age);

        // Uncommenting this will cause error:
        // age = 40;  // Cannot assign a value to final variable

        FinalClass obj1 = new FinalClass();
        obj1.show();

        Derived obj2 = new Derived();
        obj2.display();  // Calls final method from Base
        obj2.greet();    // Overridden method
    }
}
```

## Q8: Explan static keyword? (Filed, Method, Class). When do we usually use it?

### Answer:
Field (Variable): One copy shared among all instances (class variable)

Method: Can be called without an object. Can only use static data or other static methods

Class: Declares a nested static class that does not require an outer class instance

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Accessing static field and method without object:");
        System.out.println("School Name: " + Student.schoolName); // static field
        Student.printSchool(); // static method

        System.out.println("\nCreating objects to show shared static field:");
        Student s1 = new Student("Alice");
        Student s2 = new Student("Bob");

        s1.show();
        s2.show();

        // Change static field via one object
        s1.schoolName = "Northeastern University";

        System.out.println("\nAfter changing static field:");
        s1.show();
        s2.show();

        // Using static nested class
        System.out.println("\nUsing static nested class:");
        Outer.StaticNestedClass.display();
    }
}

class Student {
    String name;
    static String schoolName = "Default School";

    Student(String name) {
        this.name = name;
    }

    void show() {
        System.out.println(name + " - " + schoolName);
    }

    static void printSchool() {
        System.out.println("School: " + schoolName);
    }
}

class Outer {
    static class StaticNestedClass {
        static void display() {
            System.out.println("Hello from static nested class!");
        }
    }
}
```

## Q9: What is the differences between overriding and overloading?

### Answer:
Overloading happens when two or more methods in the same class have the same name but different parameter lists. The return type can be the same or different — what matters is the method signature (number or type of parameters).

It's an example of compile-time polymorphism. The Java compiler decides which method to call based on the method's arguments.

Overriding occurs when a subclass provides a specific implementation of a method that is already defined in its superclass. The method in the subclass must have the same name, return type, and parameters as the method in the superclass.

This is an example of runtime polymorphism, meaning the actual method that gets called is determined at runtime based on the object type.

```java
public class Main {
    public static void main(String[] args) {
        // Method Overloading
        Calculator calc = new Calculator();
        System.out.println("Overloading:");
        System.out.println("Add (int, int): " + calc.add(2, 3));
        System.out.println("Add (double, double): " + calc.add(2.5, 3.7));
        System.out.println("Add (int, int, int): " + calc.add(1, 2, 3));

        // Method Overriding
        System.out.println("\nOverriding:");
        Animal myDog = new Dog(); // runtime polymorphism
        myDog.makeSound(); // Dog’s version is called
    }
}

// Overloading: Same method name, different parameter list
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

// Overriding: Subclass redefines method of superclass
class Animal {
    void makeSound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barks");
    }
}
```

## Q10: Explain how Java defines a method signature, and how it helps on overloading and overriding.

### Answer:
In Java, a method signature is defined as:

The method name and the parameter list (types and order).

It does not include:

The return type

The access modifier (e.g., public, private)

The throws clause

For Overloading:
Java allows multiple methods with the same name, as long as the method signatures are different. This means:

Different number of parameters

Different types or order of parameters

For Overriding:
When a subclass overrides a method from its superclass, it must have exactly the same method signature (name and parameter list). If the signature is different, Java treats it as a new method — not an override.

```java
public class Main {
    public static void main(String[] args) {
        OverloadExample oe = new OverloadExample();
        oe.print("Java");
        oe.print("Java", 3);

        System.out.println();

        Animal animal = new Animal();
        animal.speak("Buddy");

        Dog dog = new Dog();
        dog.speak("Max"); // overriding
        dog.speak("Max", 2); // overloading
    }
}

// Demonstrate Overloading
class OverloadExample {
    void print(String msg) {
        System.out.println("Message: " + msg);
    }

    void print(String msg, int count) {
        System.out.println("Message repeated:");
        for (int i = 0; i < count; i++) {
            System.out.println(msg);
        }
    }
}

// Demonstrate Overriding
class Animal {
    void speak(String name) {
        System.out.println(name + " makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void speak(String name) {
        System.out.println(name + " barks");
    }

    // Overloaded method (not an override)
    void speak(String name, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(name + " barks");
        }
    }
}
```

## Q11: What is the differences between super and this?

### Answer:
this keyword: Refers to the current class instance.

Used to: Access instance variables and methods of the same class. Call another constructor within the same class using this(...).

super keyword: Refers to the parent (superclass) of the current object.

Used to: Access superclass methods or variables. Call the parent constructor using super(...) (must be first line in constructor).

```java
public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Max", "Labrador");
        dog.printInfo();
    }
}

class Animal {
    String name;

    Animal(String name) {
        this.name = name;
        System.out.println("Animal constructor called with name: " + name);
    }

    void speak() {
        System.out.println(name + " makes a sound");
    }
}

class Dog extends Animal {
    String breed;

    Dog(String name, String breed) {
        super(name);           // Calls Animal's constructor
        this.breed = breed;    // Refers to current object's breed
    }

    void printInfo() {
        this.speak();          // Calls speak() in this class or superclass
        System.out.println("Breed is: " + this.breed);
        System.out.println("Accessing name from super: " + super.name);
    }
}
```

## Q12: Explain how equals and hashCode work.

### Answer:
The equals(Object obj) method is used to compare two objects for logical equality (i.e., whether their contents are the same).

The hashCode() method returns an int hash code value for the object. It’s used in hash-based collections (HashMap, HashSet, etc.) to quickly find buckets.

If two objects are equal according to equals(), they must have the same hashCode().

```java
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Alice", 25);
        Person p2 = new Person("Alice", 25);
        Person p3 = new Person("Bob", 30);

        System.out.println("p1.equals(p2): " + p1.equals(p2)); // true
        System.out.println("p1 == p2: " + (p1 == p2));          // false (different memory)

        // Show use in HashSet (requires proper hashCode and equals)
        HashSet<Person> set = new HashSet<>();
        set.add(p1);
        set.add(p2); // Will not be added if equals and hashCode work correctly
        set.add(p3);

        System.out.println("Size of set: " + set.size()); // Should be 2
    }
}

class Person {
    private String name;
    private int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Override equals to compare fields
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same reference
        if (obj == null || getClass() != obj.getClass()) return false;

        Person other = (Person) obj;
        return this.age == other.age && this.name.equals(other.name);
    }

    // Override hashCode to be consistent with equals
    @Override
    public int hashCode() {
        return name.hashCode() + age * 31;
    }
}
```

## Q13: What is the Java load sequence?

### Answer:
The Java load sequence refers to the order in which the Java Virtual Machine (JVM) loads classes, initializes variables, and executes constructors when you run a Java program.

Class is Loaded by JVM when it is first referenced.

Static Variables and Static Blocks are initialized once, in the order they appear.

When an object is created: Instance Variables and Instance Initialization Blocks are run (in order). Constructor is executed.

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Main method starts");
        new Child();  // Trigger object creation
    }
}

class Parent {
    static {
        System.out.println("Parent static block");
    }

    {
        System.out.println("Parent instance block");
    }

    Parent() {
        System.out.println("Parent constructor");
    }
}

class Child extends Parent {
    static {
        System.out.println("Child static block");
    }

    {
        System.out.println("Child instance block");
    }

    Child() {
        System.out.println("Child constructor");
    }
}
```

## Q14: What is Polymorphism ? And how Java implements it ?

### Answer:
Polymorphism literally means "many forms". In Java, it allows the same method name or interface to behave differently depending on the object or context.

There are two types:

1. Compile-Time Polymorphism (Method Overloading)
   Same method name with different parameter types or counts in the same class. Decided by the compiler at compile time.

2. Runtime Polymorphism (Method Overriding)
   A subclass provides a specific implementation of a method that is already defined in the superclass. Resolved at runtime using dynamic method dispatch

```java
public class Main {
    public static void main(String[] args) {
        // Compile-time polymorphism (overloading)
        Calculator calc = new Calculator();
        System.out.println("Sum(int, int): " + calc.add(2, 3));
        System.out.println("Sum(double, double): " + calc.add(2.5, 3.7));

        System.out.println("\nRuntime polymorphism (overriding):");

        Animal a1 = new Animal();
        Animal a2 = new Dog();  // Parent reference to child object
        Animal a3 = new Cat();

        a1.speak();  // Animal sound
        a2.speak();  // Dog barks
        a3.speak();  // Cat meows
    }
}

// Compile-time polymorphism: method overloading
class Calculator {
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
}

// Runtime polymorphism: method overriding
class Animal {
    void speak() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    @Override
    void speak() {
        System.out.println("Cat meows");
    }
}
```

## Q15: What is Encapsulation ? How Java implements it? And why we need encapsulation?

### Answer:
Encapsulation is about hiding internal state and requiring all interaction with that data to go through controlled methods (getters/setters).

Java uses:

private access modifiers to hide fields from outside classes.

public getter and setter methods to control read/write access to those fields.

Why Do We Need Encapsulation?

Protect internal data from unintended access or corruption

Hide implementation details and expose only what’s necessary (abstraction)

Easier to maintain and modify code (you can change internals without affecting external code)

Improve security by controlling how data is accessed

```java
public class Main {
    public static void main(String[] args) {
        Person person = new Person();

        // Setting values through setter
        person.setName("Alice");
        person.setAge(25);

        // Getting values through getter
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());

        // Try setting invalid age
        person.setAge(-5);  // Should be rejected
    }
}

class Person {
    // Private fields (hidden from outside)
    private String name;
    private int age;

    // Public getter
    public String getName() {
        return name;
    }

    // Public setter with no validation
    public void setName(String name) {
        this.name = name;
    }

    // Getter
    public int getAge() {
        return age;
    }

    // Setter with validation
    public void setAge(int age) {
        if (age >= 0 && age <= 150) {
            this.age = age;
        } else {
            System.out.println("Invalid age: " + age);
        }
    }
}
```

## Q16: Compare interface and abstract class with use cases.

### Answer:
1. Interface:

A contract that defines what a class must do, but not how.

All methods are abstract by default (prior to Java 8).

Supports multiple inheritance (a class can implement multiple interfaces).

Methods are public and abstract by default.

From Java 8+, can include default and static methods.

2. Abstract Class:

A class that can’t be instantiated and may contain both abstract and concrete methods.

Used when classes share a common base and some shared code.

Supports constructors, fields, and access modifiers.

Can only be inherited by one subclass (single inheritance).

```java
public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.makeSound();          // Abstract class method
        dog.walk();               // Interface method
    }
}

// Interface defines a contract
interface Walkable {
    void walk(); // public abstract by default
}

// Abstract class defines shared behavior and structure
abstract class Animal {
    abstract void makeSound(); // Must be implemented
    void breathe() {
        System.out.println("Animal breathes...");
    }
}

// Dog extends abstract class and implements interface
class Dog extends Animal implements Walkable {
    @Override
    void makeSound() {
        System.out.println("Dog barks");
    }

    @Override
    public void walk() {
        System.out.println("Dog walks on four legs");
    }
}
```
