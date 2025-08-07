1. **Write up Example code to demonstrate the three foundmental concepts of OOP.**

   1. **Encapsulation:** Encapsulation is hiding internal data and only exposing it via getters/setters or public methods.

```
class BankAccount {
    private String owner;      // Private: encapsulated
    private double balance;    // Private: encapsulated

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public double getBalance() {   // getter
        return balance;
    }

    public void deposit(double amount) {   // setter
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {   // setter
        if (amount > 0 && balance >= amount) {
            balance -= amount;
        }
    }
}

```

2. **Inheritance:** Inheritance allows one class to inherit fields and methods from another class.

```
class Animal {
    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

class Dog extends Animal {
    public void bark() {
        System.out.println("Woof!");
    }
}
```

3. **Polymorphism:** Polymorphism allows one interface, many forms — i.e., different classes can respond differently to the same method call.

```
class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }
}

class Cow extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Moo!");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        Animal myCat = new Cat();
        Animal myCow = new Cow();

        myAnimal.makeSound();  // Output: Some generic animal sound
        myCat.makeSound();     // Output: Meow!
        myCow.makeSound();     // Output: Moo!
    }
}
```

2. **What is wrapper data type classes (e.g. Integer, Double) in Java and Why we need wrapper class?** 

Wrapper classes are object representations of primitive data types.
| **Primitive Type** | **Wrapper Class** |
|:-:|:-:|
| int | Integer |
| double | Double |
| char | Character |
| boolean | Boolean |
| byte | Byte |
| short | Short |
| long | Long |
| float | Float |

Wrapper Classes use with Java collections, which only work with objects, not primitives.
```
List<Integer> nums = new ArrayList<>();
nums.add(10);  // Automatically converts int to Integer (autoboxing)
```

Wrapper classes provide helpful methods:
```
int max = Integer.max(10, 20);
double parsed = Double.parseDouble("3.14");
```

Primitive types can’t be null, but wrapper objects can:
```
Integer x = null;  // OK
int y = null;      // Compile error
```

Java automatically converts between primitive types and wrappers:
```
Integer x = 5;      // Autoboxing: int → Integer
int y = x;          // Unboxing: Integer → int
```

3. **What is the difference between HashMap and HashTable?** 

**HashMap** is **not thread-safe**, but **faster**. Use Collections.synchronizedMap() or ConcurrentHashMap for thread safety. HashMap **allow null keys and values**. HashMap is part of the Java Collections Framework.
**Hashtable** uses synchronized methods, making it **thread-safe** but **slower** in single-threaded environments. Hashtable **doesn’t has null key and not allow null values**.

4. **What is String pool in Java and why we need String pool? Explain String immunity.** 

The String Pool is a special memory area inside the Java heap where String literals are stored to optimize memory usage and performance. 
The reason we need String pool is because strings are used frequently, and they are immutable, Java optimizes them by reusing common strings. Instead of creating a new object every time you write "hello", Java reuses the same object from the pool.

**String immunity:**

**Security:** Strings are used in file paths, network connections, class loading, etc. If they were mutable, it could be a major security risk.

**Thread Safety:** Immutable objects are naturally thread-safe.

**Caching & Pooling:** Only immutable strings can be reused safely in a pool.

**HashCode Consistency:** String is often used as a key in HashMaps. If they were mutable, changing a string's content would break the map.

5. **Explain garbage collection? Explain types of garbage collection.** 

**Garbage Collection** in Java is an **automatic memory management process** that:
* Identifies and removes **unused objects** from memory.
* Helps avoid **memory leaks** and **OutOfMemoryErrors**.
* Frees up space in the **heap**, where objects are stored.

Types of Garbage Collectors: 
1. Serial GC
* **Single-threaded** collector.
* Best for small applications or single-core systems.
* Uses **stop-the-world** pauses.
* JVM option: -XX:+UseSerialGC

2. Parallel GC (Throughput Collector)
* **Multi-threaded** for **young generation** collection.
* Good **throughput**, but **longer pause times**.
* JVM option: -XX:+UseParallelGC

3. G1 (Garbage First) GC
* Default GC from Java 9 onwards.
* Divides heap into **regions**.
* Concurrent, **low-pause** collector.
* JVM option: -XX:+UseG1GC
* Good for large heaps (>4 GB).

4. ZGC (Z Garbage Collector)
* Designed for **very large heaps** (multi-terabyte).
* Very **low pause times** (<10ms).
* Most work done **concurrently**.
* JVM option: -XX:+UseZGC (Java 11+)

6. **What are access modifiers and their scopes in Java?** 
| **Modifier**                            | **Class** | **Package** | **Subclass** | **Outside Package**  |
|:---------------------------------------:|:---------:|:-----------:|:------------:|:--------------------:|
| public                                  | yes       | yes         | yes          | yes                  |
| *no modifier* (default/package-private) | yes       | yes         | no           | no                   |
| protected                               | yes       | yes         | yes          | no (unless subclass) |
| private                                 | yes       | no          | no           | no                   |

7. **Explain final key word? (Field, Method, Class)** 

   1. Final Variables (Fields)
   * A final variable can be **assigned only once**.
   * Once assigned, its value **cannot be changed**.
```
public class Config {
    public static final int MAX_USERS = 100;

    final String appName = "MyApp";

    public void setAppName(String newName) {
        // appName = newName; // Error: Cannot assign a value to final variable
    }
}
```

2. Final Methods
* A final method **cannot be overridden** in a subclass.
```
class Animal {
    public final void speak() {
        System.out.println("Animal speaks");
    }
}

class Dog extends Animal {
    // public void speak() {} // Error: Cannot override the final method
}
```

3. Final Classes
* A final class **cannot be extended** (inherited).
```
public final class MathUtils {
    public static int square(int x) {
        return x * x;
    }
}

// class ExtendedMath extends MathUtils {} // Error: Cannot subclass final class
```

8. **Explan static keyword? (Field, Method, Class). When do we usually use it?** 

   1. Static Fields (Variables)
   * Shared across **all instances** of a class.
   * Only **one copy** exists in memory.
```
public class Employee {
    public static int employeeCount = 0; // shared among all Employees

    public Employee() {
        employeeCount++; // increases the shared count
    }
}
```

2. Static Methods
* Can be called **without creating an object**.
* **Cannot access instance (non-static) variables or methods** directly.
```
public class MathUtils {
    public static int add(int a, int b) {
        return a + b;
    }
}

int result = MathUtils.add(3, 4); // no object needed
```

3. Static Nested Classes
* A class defined **within another class** but **not dependent on the outer class's instance**.
```
public class Outer {
    static class Inner {
        void sayHello() {
            System.out.println("Hello from static nested class!");
        }
    }
}

Outer.Inner obj = new Outer.Inner();
obj.sayHello();
```

9. **What is the differences between overriding and overloading?** 

**Overriding:** Overriding occurs when a **subclass** provides a specific implementation of a method that is already defined in its **superclass**. 
```
class Animal {
    public void speak() {
        System.out.println("Animal speaks");
    }
}

class Dog extends Animal {
    @Override
    public void speak() {
        System.out.println("Dog barks");
    }
}
```

**Overloading:** Overloading means having **multiple methods with the same name** in the same class but **different parameter lists**.
```
class Calculator {
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
```

10. **Explain how Java defines a method signature, and how it helps on overloading and overriding.** 

In Java, a method signature consists of: Method Name + Parameter Types (number, type, and order)
**Method overloading** is when multiple methods in the **same class** have the same name but **different signatures**. Java uses **method signature** to differentiate between overloaded methods. Overloading is **resolved at compile-time**.

**Method overriding** is when a **subclass** provides a new implementation for a method already defined in its **superclass**, using the **exact same method signature**.

11. **What is the differences between super and this?** 

This refers to the **current object** — the instance of the class in which the code is executing.
```
public class Person {
    private String name;

    public Person(String name) {
        this.name = name; // 'this.name' refers to the field; 'name' refers to the parameter
    }
}
```

```
public class Person {
    private String name;
    private int age;

    public Person(String name) {
        this(name, 0); // call another constructor
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

Super refers to the **parent class (superclass)** of the current object.
```
class Animal {
    public Animal(String type) {
        System.out.println("Animal: " + type);
    }
}

class Dog extends Animal {
    public Dog() {
        super("Dog"); // calls the constructor in Animal
    }
}
```

```
class Animal {
    public void speak() {
        System.out.println("Animal speaks");
    }
}

class Dog extends Animal {
    public void speak() {
        super.speak(); // call superclass version
        System.out.println("Dog barks");
    }
}
```

12. **Explain how equals and hashCode work.** 

**equals(Object obj) method:** Used to **compare two objects for logical equality**.
```
Object a = new Object();
Object b = new Object();
System.out.println(a.equals(b)); // false, different references
```

**hashCode() method:** Returns an int used for **hash-based collections** like HashMap, HashSet, etc. If two objects are **equal (**equals() **returns** true**)**, they **must have the same hashCode**. This ensures consistent behavior in hash-based collections.

13. What is the Java load sequence? 

**1. Loading**
* The class bytecode is **read into memory** by the **ClassLoader** (e.g., Bootstrap, Extension, or Application ClassLoader).
* A Class object is created in the JVM to represent the loaded class.
**2. Linking**
Linking connects the loaded class with the JVM runtime.
* **Verification**: Checks the bytecode is structurally correct and safe.
* **Preparation**: Memory is allocated for static variables and initialized with default values (e.g., 0, null, false).
* **Resolution**: All symbolic references (like field/method references) are resolved into direct references.
**3. Initialization**
* This is when **static blocks** and **static variables** are actually executed/set with assigned values.
* The class constructor <client>() is called **once per class**.
* Happens **only when the class is first actively used**, e.g.:
  * Creating an instance
  * Accessing a static method or field
  * Reflectively using Class.forName()
**4. Usage**
* Instance creation
* Method calls
* Field access
⠀**5. Unloading**
* The class is **unloaded** by the JVM **only when the ClassLoader itself is garbage collected**.
* Typically happens in dynamic systems like application servers or plugins.

14. What is Polymorphism ? And how Java implements it ? 

**Polymorphism** means **"many forms"**, and allows objects to be treated as instances of their **parent class rather than their actual class**.
In Java, polymorphism lets us:
* Use a single interface to represent different types.
* Write code that works on the superclass but behaves differently depending on the subclass.

**Java Implements Polymorphism**
**Overloading:**
* Resolved by the **Java compiler**.
* Done by checking method **signature** (method name + parameter types).

⠀**Overriding:**
* Handled by the **JVM** using **dynamic dispatch**.
* JVM uses **v-tables (virtual method tables)** internally to decide which method to call at runtime.

15. What is Encapsulation ? How Java implements it? And why we need encapsulation? 

**Encapsulation** means **bundling the data (fields) and the methods (functions) that operate on that data into a single unit (a class)** and **restricting direct access** to some of the object’s components.

**Java Implements Encapsulation**
Java uses **access modifiers** to implement encapsulation:
1 Make class fields private so they **cannot be accessed directly** from outside the class.
2 Provide **public** getter **and** setter **methods** to access and update the values.

16. Compare interface and abstract class with use cases.

**Interface:**
* When you want to define a **contract** for what a class must do, without specifying how it should do it.
* When you want to support **multiple inheritance** of type (since Java doesn’t support multiple inheritance via classes).
* Good for **API design**.
```
public interface Flyable {
    void fly();
}

public class Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("Bird flies");
    }
}
```

**Abstract Class:**
* When you want to provide **base functionality** and enforce certain methods in subclasses.
* When you want to have **shared state or fields**.
* Use it when classes are **closely related** and should share some default behavior.
```
public abstract class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(name + " is eating");
    }

    public abstract void makeSound();  // force subclasses to implement
}

public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Bark!");
    }
}
```