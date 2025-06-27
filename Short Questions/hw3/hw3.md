# HW3 - Java&OOP

---


## 1. **Write up Example code to demonstrate the three fundamental concepts of OOP.**

* **Encapsulation:**
  * Encapsulation in OOP refers to binding the data and the methods to manipulate that data together in a single unit (class)

```java
public class Product {

    private String name;
    private double price;
    private int number;

    public Product(String name, double price, int number) {
        this.name = name;
        this.price = price;
        this.number = number;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

```

* **Inheritance:**
  * Inheritance lets one class acquire properties and methods of another.

```java
class Animal {
    public void speak() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {  // Dog inherits from Animal
    public void speak() {
        System.out.println("Dog barks");
    }
}

```

* **Polymorphism:**
  * Polymorphism refers to the same object exhibiting different forms and behaviors.
  * Polymorphism allows one interface to be used for a general class of actions. 

```java
public class OOPDemo {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();  // Base class object
        Animal myDog = new Dog();        // Polymorphic behavior

        myAnimal.speak();  // Outputs: Animal makes a sound
        myDog.speak();     // Outputs: Dog barks (runtime polymorphism)
    }
}

```

---

## 2. **What is wrapper data type classes (e.g. Integer, Double) in Java and Why we need wrapper class?**

* **What?**
  * In Java, wrapper classes are object representations of the eight primitive data types.
  * For example: `int` is a primitive type, `Integer` is its corresponding wrapper class
  
* **Why need?**
  * Use with Collections (like ArrayList): Java collections can only store objects, not primitives. So if we want to store numbers in a list, we need to use wrapper classes.
    ```java
    ArrayList<Integer> list = new ArrayList<>();
    list.add(10);  // int 10 is automatically converted to Integer (autoboxing)
    ```
  
  * Access Useful Methods: Wrapper classes come with utility methods. 
    For instance, Integer.parseInt("123") can convert a String to an int. This is something plain int cannot do on its own.
    ```java
    int x = Integer.parseInt("42");
    double y = Double.parseDouble("3.14");
    ```
  
  * Allow null Values: Primitives like int or double can’t be null, but wrapper classes can.
    This is useful when you need to represent "no value" or "not set".
    ```java
    Integer score = null;  // valid
    int age = null;        // compilation error
    ```
  * Autoboxing and Unboxing: 
  
    Java supports automatic conversion between primitives and wrapper classes:
  
    autoboxing: converting a primitive into a wrapper object

    Unboxing: converting a wrapper object back into a primitive
  
    ```java
    Integer a = 5;      // autoboxing (int to Integer)
    int b = a + 2;      // unboxing (Integer to int)
    ```

---

## 3. **What is the difference between HashMap and HashTable?**

* **Synchronization**:
  * HashMap: Not synchronized (not thread-safe).

    ➤ Must be synchronized manually when used in multithreaded environments.
  * Hashtable: Synchronized (thread-safe).
    
    ➤ All methods are synchronized, but this adds performance overhead.

* **Null keys and Values**
  * HashMap allows one null key and multiple null values.
  * HashTable does not allow null keys or null values.

* **Performance**
  * HashMap is Faster while HashTable is slower

---

## 4. **What is String pool in Java and why we need String pool? Explain String immunity.**

* **String pool** is a special memory region in the **heap** where **String literals** are stored.
  * Whenever we create a String literal, Java checks the pool:
    - If the literal already exists, Java returns a reference to the existing object.
    - If not, Java creates a new String object and stores it in the pool.
    
* **Why need String pool**
  * Memory efficiency: Instead of storing duplicate string objects, Java reuses existing ones.
  * Performance: Comparison using == is faster than .equals().
  * Optimization for constants: Since strings are widely used as keys, identifiers, messages, etc., pooling saves a lot of space.

* **String immunity**
  * String immutability means once a String object is created, its value cannot be changed.
    
  ```java
  String name = "Alice";
  name.concat(" Smith"); // returns a new String, doesn't modify `name`
  System.out.println(name); // prints "Alice"
  
  // To change it, must assign the result to a new variable:
  name = name.concat(" Smith");
  System.out.println(name); // prints "Alice Smith"
  ```
    
---

## 5. **Explain garbage collection? Explain types of garbage collection.**


* **Garbage Collection (GC)** in Java is an automatic memory management process that identifies and reclaims memory occupied by objects that are no longer referenced by the running application. 

* **Types of Garbage Collectors**
  1. Serial Garbage Collector
     * Single-threaded.
     * Best for small applications or systems with limited CPU.
     * JVM option: -XX:+UseSerialGC
  2. Parallel Garbage Collector (Throughput GC)
     * Uses multiple threads to speed up GC.
     * Focuses on high throughput, suitable for applications with long uptime.
     * JVM option: -XX:+UseParallelGC
  3. CMS (Concurrent Mark Sweep) Garbage Collector
     * Minimizes pause time by doing most of the work concurrently with the application.
     * Phased out in newer JDKs (deprecated since Java 9).
     * JVM option: -XX:+UseConcMarkSweepGC
  4. G1 (Garbage First) Garbage Collector
     * Divides the heap into regions.
     * Collects the regions with the most garbage first.
     * Balances throughput and low latency.
     * Default in Java 9 and above.
     * JVM option: -XX:+UseG1GC
  5. Z Garbage Collector (ZGC)
     * Low-latency GC designed for large heaps (multi-terabyte).
     * Pauses are usually <10ms.
     * JVM option: -XX:+UseZGC (Java 11+)
  6. Shenandoah GC
     * Also designed for low pause times, like ZGC.
     * Works concurrently with running threads.
     * JVM option: -XX:+UseShenandoahGC (Java 12+)


---


## 6. **What are access modifiers and their scopes in Java?**

* public: Accessible everywhere.
* protected: Accessible within same package and subclasses (even in other packages).
* default (no keyword): Accessible only within the same package.
* private: Accessible only within the same class.

| Modifier    | Same Class | Same Package | Subclass (diff package) | Other Packages |
| ----------- | ---------- | ------------ | ----------------------- | -------------- |
| `public`    | ✅          | ✅            | ✅                       | ✅              |
| `protected` | ✅          | ✅            | ✅                       | ❌              |
| `default`   | ✅          | ✅            | ❌                       | ❌              |
| `private`   | ✅          | ❌            | ❌                       | ❌              |


---

## 7. **Explain final key word? (Field, Method, Class)**

| Use on | Effect                                   |
|--------|------------------------------------------|
| Field  | Value cannot be changed after assignment |
| Method | Cannot be overridden                     |
| Class  | Cannot have subclasses (no inheritance)  |



---
## 8. **Explain static keyword? (Filed, Method, Class). When do we usually use it?**

* When something is declared as static, it belongs to the class itself, not to any specific object.

| Usage                 | Description                                                        | Characteristics                                     | Common Use Cases                                         |
| --------------------- | ------------------------------------------------------------------ | --------------------------------------------------- | -------------------------------------------------------- |
| `static` Field        | Belongs to the **class**, not any specific instance                | Shared across all objects of the class              | Global counters, constants (`public static final`)       |
| `static` Method       | Can be called **without creating an object**                       | Cannot access instance members; only static members | Utility methods (e.g., `Math.max()`), `main` method      |
| `static` Nested Class | A nested class that doesn’t require an instance of the outer class | Cannot access non-static members of the outer class | Organizing helper classes logically within outer classes |

* **When to Use static**
  * When the data or method does not depend on object state
  * For utility methods that don’t need instance context
  * For shared constants or counters
  * For factory methods (`static SomeClass create(...)`)
  * For main methods: `public static void main(String[] args)`
  
---
## 9. **What is the differences between overriding and overloading?**

* Overriding is used for customizing inherited behavior.
    ```java
    class Animal {
        void speak() {
            System.out.println("Animal speaks");
        }
    }
    
    class Dog extends Animal {
        @Override
        void speak() {
            System.out.println("Dog barks");
        }
    }
    
    ```
* Overloading is used for flexibility in calling methods with different parameter types or counts.

    ```java
    class Printer {
        void print(String text) {
            System.out.println(text);
        }
    
        void print(int number) {
            System.out.println(number);
        }
    
        void print(String text, int times) {
            for (int i = 0; i < times; i++) {
                System.out.println(text);
            }
        }
    }
    
    ```

| Feature                 | **Overriding**                                                     | **Overloading**                                                                                      |
| ----------------------- | ------------------------------------------------------------------ | ---------------------------------------------------------------------------------------------------- |
| Definition              | Redefining a method from the **parent class** in a **child class** | Defining **multiple methods** with the **same name** in the **same class**, but different parameters |
| Purpose                 | To provide **specific implementation** for a method in subclass    | To perform **similar tasks** in different ways                                                       |
| Class Relationship      | Requires **inheritance** (parent-child classes)                    | Can happen **within a single class** (no inheritance needed)                                         |
| Method Signature        | Must have the **same name, return type, and parameters**           | Must have the **same name** but **different parameters**                                             |
| Runtime or Compile-time | Happens at **runtime** (dynamic binding)                           | Happens at **compile-time** (static binding)                                                         |
| Access Modifier         | Can be **more accessible** (e.g., protected → public)              | Not applicable                                                                                       |
| Return Type             | Must be the **same** or a **subtype** (covariant return)           | Can be different                                                                                     |
| Polymorphism Type       | **Runtime polymorphism**                                           | **Compile-time polymorphism**                                                                        |


---
## 10. **Explain how Java defines a method signature, and how it helps on overloading and overriding.**
* Java defines a method signature as: method name + parameters (types and order). It does NOT include the return type, access modifiers, or exceptions.
* **How Method Signature Helps with Overloading**
  * Overloading = same method name, but different signatures within the same class.
  * Since Java defines a method's identity by its name + parameter list, the compiler uses the method signature to distinguish which overloaded method to call.
* **How Method Signature Helps with Overriding**
  * Overriding = subclass provides a new implementation of a method defined in the superclass, using the exact same signature.
  * Java uses the signature to match the method in the subclass to the one in the superclass.
  * If the signature doesn’t match exactly, it's not considered overriding, and will either get a compile-time error or end up with overloading instead.

---
## 11. **What is the differences between super and this?**

* In Java, both `super` and `this` are reference keywords, but they are used in different contexts to refer to different objects.
* Use `this` when referring to the current object.
* Use `super` when referring to the immediate parent class — especially for calling parent constructors or overridden methods.

 | Feature           | `this`                               | `super`                               |
  | ----------------- | ------------------------------------ | ------------------------------------- |
  | Refers to         | Current class instance               | Parent class instance                 |
  | Calls constructor | Constructor in **same class**        | Constructor in **superclass**         |
  | Accesses fields   | Fields and methods of current class  | Fields and methods of parent class    |
  | Context           | Mostly in class's own implementation | Mostly in subclass extending a parent |

---
## 12. **Explain how equals and hashCode work.**

* In Java, the equals() and hashCode() methods are used to compare objects and to store them efficiently in data structures like HashMap, HashSet, and Hashtable. 
* They work together, and it's important to override them correctly when creating custom classes.

* `equals(Object obj)` Method
  *  The `equals()` method checks whether two objects are logically equal — i.e., if their content or meaning is the same.
  * Default behavior: The default implementation (in class Object) checks if two references point to the same object in memory (same identity). 
  * Custom behavior: Override it to compare field values of two different objects.

* `hashCode()` Method
  * `The hashCode()` method returns an integer representation (hash value) of the object. It’s used primarily in hash-based collections to quickly locate objects.
  * If two objects are equal (`equals()` returns true), they must return the same hash code.
  * Hash collections like `HashMap`, `HashSet`, and `Hashtable` use hashCode() to decide where to store an object internally.

* `equals()` and `hashCode()` Contract
  * if you override `equals()`, you must also override `hashCode()` to maintain the following contract:
    * If `a.equals(b)` is true, then `a.hashCode() == b.hashCode()` must be true.
    * If `a.equals(b)` is false, their hash codes can be equal or not.
    * If `hashCode()` is not overridden, collections may fail to find your object.

* **Summary**
  * `equals()` checks if two objects are logically equal.
  * `hashCode()` returns an integer hash used for storing/finding objects in hash-based collections.
  * Always override both if using objects as keys in a map or storing them in a set.
  * Two equal objects must return the same hash code.

---
## 13. **What is the Java load sequence?**

1. Class Loading
   * The JVM loads the class into memory from the `.class` file on disk.
   * This is done once per class by the ClassLoader when the class is first referenced.

2. Static Variable Initialization & Static Blocks
   * All `static` variables are initialized in the order they appear in the class.
   * All `static` blocks are executed in the order they appear.
   * This step also happens once, at the time of class loading.
   
3. Instance Variable Initialization
   * When an object is created, all non-static (instance) variables are first assigned their default values (0, null, false, etc.).
   * Then they are initialized with any explicit assignments provided in the code.
   
4. Instance Initializer Blocks
   * These are blocks like { ... } that are not inside any method or constructor.
   * They run after instance variables are initialized, and before the constructor, in the order they appear.
   
5. Constructor Execution
   * Finally, the constructor is called.
   * If the class has a superclass, then the parent constructor and initialization steps are executed first (via an implicit or explicit `super()` call).

---
## 14. **What is Polymorphism ? And how Java implements it ?**

* **What**
  * Polymorphism refers to the same object exhibiting different forms and behaviors.
  * Polymorphism allows one interface to be used for a general class of actions.

```java
public class OOPDemo {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();  // Base class object
        Animal myDog = new Dog();        // Polymorphic behavior

        myAnimal.speak();  // Outputs: Animal makes a sound
        myDog.speak();     // Outputs: Dog barks (runtime polymorphism)
    }
}
```
* **How Java Implements Polymorphism**
  * Method Overloading → Resolved at compile time by method signature.
  * Method Overriding → Resolved at runtime using virtual method tables (vtable) maintained by the JVM.
  * Inheritance and interfaces allow polymorphic references — you can use a parent class or interface as a reference type and assign a subclass object to it.
  * Dynamic dispatch allows Java to invoke the correct overridden method at runtime.

---
## 15. **What is Encapsulation ? How Java implements it? And why we need encapsulation?**
* **What**
  * Encapsulation is the process of bundling data (fields) and methods (functions) that operate on that data within a single unit (usually a class).

* **How Java implements it**
  * Private fields: Make class variables private so they cannot be accessed directly from outside the class.
  * Public getters/setters: Provide public methods to get or set private field values safely.
  * Access modifiers: Control visibility at the class, method, and field level (private, default, protected, public).

* **Why Do We Need Encapsulation**
  * Control Access to Data: Prevent unauthorized or invalid changes to internal state.
  * Improve Code Maintainability: Changing internal implementation won’t affect other classes that use it.
  * Increase Security: Sensitive data (like passwords, balances, etc.) is protected from outside interference.
  * Encourage Abstraction: Users don’t need to know how a class works internally — they just use its public methods.
  * Reduce Coupling: External code doesn’t depend directly on internal structure, making it easier to change and extend.

---
## 16. **Compare interface and abstract class with use cases.**

* **Definition**
  * Interface: A contract that defines what a class must do, without saying how.
  * Abstract Class: A base class that can define both what and how — it can have both abstract and concrete methods.

| Feature             | Interface                                                                                      | Abstract Class                                                    |
| ------------------- | ---------------------------------------------------------------------------------------------- | ----------------------------------------------------------------- |
| Inheritance support | A class can implement **multiple interfaces**                                                  | A class can extend **only one abstract class**                    |
| Method types        | Only **abstract methods** (before Java 8); Java 8+ supports **default** and **static** methods | Can have both **abstract and concrete** methods                   |
| Variables           | All variables are **public static final** (constants)                                          | Can have instance variables (any modifier)                        |
| Constructors        | ❌ Cannot have constructors                                                                     | ✅ Can have constructors                                           |
| Access modifiers    | Methods are implicitly **public**                                                              | Can use **any modifier** (private, protected, etc.)               |
| Purpose             | To define a **contract or capability**                                                         | To define a **base template or partial implementation**           |
| Use case            | Use when unrelated classes share behavior (e.g., `Flyable`, `Serializable`)                    | Use when classes share a base structure (e.g., `Animal`, `Shape`) |

* **Example: Interface**

```java
interface Flyable {
    void fly(); // abstract method
}

class Bird implements Flyable {
    public void fly() {
        System.out.println("Bird is flying");
    }
}
// Use case: Multiple classes (e.g., Bird, Airplane) need to be "flyable" but are otherwise unrelated.
```

* **Example: Abstract**

```java
abstract class Animal {
    abstract void makeSound(); // abstract method
    void sleep() {
        System.out.println("Sleeping...");
    }
}

class Dog extends Animal {
    void makeSound() {
        System.out.println("Bark");
    }
}
// Several related classes (Dog, Cat) inherit shared logic (like sleep()), but implement their own behavior.
```

---



