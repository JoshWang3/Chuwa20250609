# Ryan Ma HW3 Answers and Sample code



## 1. Write up Example code to demonstrate the three fundamental concepts of OOP
- Encapsulation is about hiding internal data and exposing behavior through getters and setters.
```java
class Animal {
    private String name;
    
    public Animal() {}
    
    public Animal(String name) {
        this.name = name;
    }
    
    public String getter() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void getName() {
        System.out.println("some sound");
    }
}
```
- Polymorphism means an object can take many forms and exhibiting different behaviors.
```java
// Overload
class PolyTest {
  public void add(int a, int b){
    System.out.println(a + b);
  }
  public void add(String a, String b) {
    System.out.println(a + b);
  }
}

class Main {
  public static void main(String[] args) {
    PolyTest obj = new PolyTest();
    obj.add(1, 2); // print out 3
    obj.add("a", "b"); // print out "ab";
  }
}
```
- Inheritance lets one class extend another to reuse and specialize behavior.
```java
// Dog extend Animal
class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    // Override
    public void makeSound() {
        System.out.println("Dog Bark");
    }
}
```

## 2. What is wrapper data type classes (e.g. Integer, Double) in Java and Why we need wrapper class?
- In Java, wrapper classes are object versions of the 8 primitive data types.

  | Primitive | Wrapper Class |
  |-----------|---------------|
  | `int`     | `Integer`     |
  | `double`  | `Double`      |
  | `boolean` | `Boolean`     |
  | `char`    | `Character`   |
  | `byte`    | `Byte`        |
  | `short`   | `Short`       |
  | `long`    | `Long`        |
  | `float`   | `Float`       |

- Collections can't store primitives
```java
import java.util.ArrayList;
import java.util.List;

List<int> list = new ArrayList<>(); // not working
List<Integer> list = new ArrayList<>();
```
- Wrapper classes provide useful methods
```java
int a = 66;
String s = Integer.toString(a);
int b = Integer.parseInt("123");
```
- Java can automatically convert between primitive and wrapper classes:
```java
int a = 66;
Integer obj = a;
int b = obj;
```

## 3. What is the difference between HashMap and HashTable?
| Feature                     | `HashMap`                  | `Hashtable`                   |
|-----------------------------|----------------------------|-------------------------------|
| **Thread-safe?**            | ❌ No (not synchronized)    | ✅ Yes (synchronized)          |
| **Performance**             | ✅ Faster (no locking)      | ❌ Slower (due to locking)     |
| **Allows `null` keys?**     | ✅ Yes (1 null key allowed) | ❌ No                          |
| **Allows `null` values?**   | ✅ Yes                      | ❌ No                          |
| **Legacy?**                 | ❌ Modern class (Java 1.2+) | ✅ Legacy class (pre-Java 1.2) |
| **Preferred for new code?** | ✅ Yes                      | ❌ No                          |

## 4. What is String Pool in Java and why we need String Pool? Explain String immutability.
- The String Pool is a special area in the Java heap memory that stores String literals to save memory and improve performance.
```java
class StrPool {
    public static void main(String[] arg) {
      String s1 = "job";
      String s2 = "job";
      System.out.println(s1 == s2); // Return True
    }
}
```
- Memory Efficiency and Faster Comparisons
- String immutability means once a String is created, it can't be changed
- The reason why string is immutable?
  - No Setter
  - String class is final class
  - Java stores string literals in a shared string pool in Java heap memory

## 5. Explain garbage collection? Explain types of garbage collection.
- Garbage Collection is the process by which Java automatically removes objects from memory that are no longer in use,
free up space and preventing memory leaks.
```java
class GarbageCollection{
  public static void main(String[] arg) {
    String s = "job";
    s = null; // now the object is unreachable
  }
}
```
- Types of Garbage Collection.

  | GC Type                | Multithreaded | Pause Time           | Best For                              | Notes                                                                  |
  |------------------------|---------------|----------------------|---------------------------------------|------------------------------------------------------------------------|
  | **Serial GC**          | ❌ No          | ❌ Long               | Small, single-threaded apps           | Simple and memory-efficient; uses one thread for both minor & major GC |
  | **Parallel GC**        | ✅ Yes         | ❌ Medium/Long        | High-throughput batch apps            | Uses multiple threads; prioritizes throughput over pause time          |
  | **CMS** *(deprecated)* | ✅ Yes         | ✅ Short              | Low-latency apps (pre-Java 9)         | Concurrent GC to reduce pause; may cause fragmentation                 |
  | **G1 GC**              | ✅ Yes         | ✅ Balanced           | Most modern apps (default in Java 9+) | Splits heap into regions, focuses on predictable short pauses          |
  | **ZGC**                | ✅ Yes         | ✅ Very short (<10ms) | Large heap, low-latency apps          | Works concurrently; handles 100GB+ heaps with minimal pause            |
  | **Shenandoah**         | ✅ Yes         | ✅ Very short         | Low-latency and high-scale apps       | Similar to ZGC; developed by Red Hat; Java 12+                         |

## 6. What are access modifiers and their scopes in Java
- Access modifiers control who can see or use a class, method or variables. They are key to Encapsulation.
- Four types of access modifiers

  | Modifier                  | Scope / Accessibility                                             |
  |---------------------------|-------------------------------------------------------------------|
  | `public`                  | Declarations are visible everywhere                               |
  | `protected`               | Declarations are visible within the package or all subclasses     |
  | *(default)* (no modifier) | Declarations are visible only within the package(package private) |
  | `private`                 | Declarations are visible within the class only                    |
```java
public class Animal {
    public String type = "Mammal";   // accessible everywhere
    protected String name = "Tiger"; // accessible in subclass or same package
    String sound = "Roar";           // default (package private)
    private int age = 5;             // only within this class
}
```

## 7. Explain final key word? (Field, Method, Class)
- Final Variable/Field: value can not be changed once assigned.
```java
  final int a = 666;
  //a = 6666; // Compile error: can not assign a value to final variable
```
- Final Method: method can not be overridden in a subclass.
```java
class Animal {
    public final void makeSound() {
        System.out.println("some Sound");
    }
}
class Dog extends Animal {
    // This will cause a compile error
    public void makeSound() {
        System.out.println("Bark");
    }
}
```
- Final Class: class can not be extended(inherited)
```java
final class Math{
    public static final double PI = 3.14;
}
// This will cause error as Math can not be extended.
class SchoolMath extends Math {
    
}
```

## 8. Explain static keyword? (Field, Method, Class). When do we usually use it?
- Static Field/Variable: Shared by all instances of the class. Good for constants or shared counters.
```java
class Counter {
    public static int count = 0;
    
    public Counter() {
        count += 1;
    }
}


new Counter();
new Counter();
System.out.println(Counter.count); //2
```
- Static Method: Belongs to the class, not instances. It can be called without creating an object.
```java
class Math {
    public static int square(int x) {
        return x * x;
    }
}

int result = Math.square(2); // get 4 without creating new instances.
```
- Static Class: it like a regular class inside another class, but does not depend on the outer class instance.
```java
class Outer {
    static class Inner {
        void speak() {
            System.out.println("jobs");
        }
    }
}

Outer.Inner obj = new Outer.Inner();
obj.speak();
```

## 9. What is the differences between overriding and overloading?
- Overriding: a subclass provides a new version of a method that is already defined in its superclass.
```java
class Animal {
  public void makeSound() {
    System.out.println("some Sound");
  }
}
class Dog extends Animal {
  // This will cause a compile error
  public void makeSound() {
    System.out.println("Bark");
  }
}
```
- Overloading: In the same class, multiple methods share same name but have different parameters.
```java
public class OverloadPractice {
    public static class Practice {
        public int add(int a, int b) {
            return a + b;
        }

        public String add(String a, int b) {
            return a + b;
        }

        public String add(String a, String b) {
            return a + b;
        }
    }

    public static void main(String[] args) {
        Practice p1 = new Practice();
        System.out.println(p1.add(1, 2));
        System.out.println(p1.add("a", 3));
        System.out.println(p1.add("a", "b"));
    }
}
```
- Comparison Table

  | Feature             | Overriding                            | Overloading                                |
  |---------------------|---------------------------------------|--------------------------------------------|
  | Where?              | Subclass (inherits from parent class) | Same class                                 |
  | Signature           | Same method name and parameters       | Same method name, **different** parameters |
  | Return type         | Can change (with some rules)          | Can vary                                   |
  | Inheritance needed? | ✅ Yes                                 | ❌ No                                       |
  | Polymorphism type   | ✅ Runtime Polymorphism                | ✅ Compile-time Polymorphism                |
  | Purpose             | Change behavior in child class        | Increase method flexibility                |


## 10. Explain how Java defines a method signature, and how it helps on overloading and overriding.
- In Java, a method signature is defined by: Method name + parameter types(order & type only)
- it does not include: Return Type, Access Modifiers, Exceptions, Parameter Names.
- Overloading = Sme method name, different signature;
- Overriding = same method name and signature;

# 11. What is the differences between super and this?
- Comparison Table:

| Keyword | Refers To                      | Used In...                                                    |
|---------|--------------------------------|---------------------------------------------------------------|
| `this`  | **Current object**             | Accessing current class's variables, methods, or constructors |
| `super` | **Parent (superclass) object** | Accessing superclass’s variables, methods, or constructors    |
- Common uses of "this":
```java
class Dog {
    private String name;
    
    public Dog(String name) {
        this.name = name; // this.name = field, name = parameter
    }
}
```
```java
someMthod(this); // Pass current object
```
- Common uses of "super":
```java
class Animal {
    private String name;
    
    public Animal(String name) {
        this.name = name;
    }
}
class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
}
```

## 12. Explain how equals and hashCode work.
- equals() Checks if two objects are logically equal.
- By default, behaves like ==, you override it to compare values/fields.
```java
class Person {
    private String name;
    
    public Person() {}
  
    public Person(String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person p)) return false;

        return this.name = p.name;
        
    }
}
```
- hashCode: Used by hash-based collections to determine bucket location. returns an int hash value for the object.

## 13. What is Java Load Sequence?
- Static Variable
- Static Block
- Instance variables
- instance block
- Constructor

## 14. What is Polymorphism? And how java implements it?
- Please check question 1

## 15. What is Encapsulation? How Java implement it? And why we need encapsulation?
- Please check question 1
- Data protection; Control over how data is accessed or modified; Improve maintainability

## 16 Compare interface and abstract class with use cases.

- Interface
```java
public interface Animal {
    void makeSound();
}

public class Cat implements Animal {
    void makeSound() {
        System.out.println("Meow");
    }
}
```
- Abstract
```java
public abstract class Animal {
    abstract void makeSound();
    
    void sleep() {
        System.out.println("Sleeping...");
    }
}

class Dog extends Animal {
    void makeSound() {
        System.out.println("Bark");
    }
}
```