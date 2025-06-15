### 1. Write up Example code to demonstrate the three fundamental concepts of OOP.
> 1. Encapsulation:  
> Encapsulation is hiding internal data and only exposing it via getters/setters or public methods.

```java
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

> 2. Inheritance:  
>  Inheritance allows one class to inherit fields and methods from another class.
```java
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

> 3. Polymorphism:  
> Polymorphism allows one interface, many forms — i.e. different classes can respond differently to the same method call.
```java
// ✅ Polymorphism via method overriding

public class Animal {
    public void makeSound() {
        System.out.println("Some sound");
    }
}

public class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}

public class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
}

// In Main method
public class Main {
    public static void main(String[] args) {
        Animal a1 = new Dog();
        Animal a2 = new Cat();

        a1.makeSound(); // Bark
        a2.makeSound(); // Meow
    }
}
```

```java
// ✅ Polymorphism via method overloading

public class Calculator {
    // Overloaded add method with 2 ints
    public int add(int a, int b) {
        return a + b;
    }

    // Overloaded add method with 3 ints
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Overloaded add method with 2 doubles
    public double add(double a, double b) {
        return a + b;
    }
}

// In Main method
public class Main {
  public static void main(String[] args) {
    Calculator calc = new Calculator();

    System.out.println(calc.add(2, 3));         // 5
    System.out.println(calc.add(1, 2, 3));      // 6
    System.out.println(calc.add(2.5, 3.5));     // 6.0
  }
}
```


### 2. What is wrapper data type classes (e.g. Integer, Double) in Java and why we need wrapper class?
A **wrapper class** in Java is an object representation of a primitive data type.

| Primitive Type | Wrapper Class |
|----------------|---------------|
| `int`          | `Integer`     |
| `double`       | `Double`      |
| `boolean`      | `Boolean`     |
| `char`         | `Character`   |
| `byte`         | `Byte`        |
| `short`        | `Short`       |
| `long`         | `Long`        |
| `float`        | `Float`       |


- Wrapper classes bridge the gap between primitive types and object-oriented features in Java.  
- They allow primitives to be used where objects are required, such as in collections, generics, and utility methods.

Examples:  
- **Use with Collections (like ArrayList, HashMap)**  
Java Collections only store objects — not primitive types.
   ```java
   List<Integer> list = new ArrayList<>();
   list.add(5); // Cannot use 'int', but Integer works due to autoboxing
   ```

- **Use with Generics**  
Java Generics **only work with objects**, not primitive types.  
So if you want to store `int`, `double`, etc., you must use their **wrapper classes**: `Integer`, `Double`, etc.
    ```java
    List<Integer> numbers = new ArrayList<>();
    List<int> nums = new ArrayList<>();  // ❌ Compile-time error: Generics do not support primitives 
    ```

- **Utility Methods**  
Wrapper classes provide helpful methods.
    ```java
    int max = Integer.max(10, 20);
    boolean parsed = Boolean.parseBoolean("true");
    ```


### 3. What is the difference between HashMap and HashTable?

| Feature            | `HashMap`                          | `Hashtable`                          |
|--------------------|------------------------------------|--------------------------------------|
| **Thread-safe**    | ❌ Not thread-safe                 | ✅ Thread-safe (synchronized)        |
| **Performance**    | ✅ Faster (no sync overhead)       | ❌ Slower (due to synchronization)   |
| **Null Keys**      | ✅ Allows 1 null key               | ❌ Does NOT allow null keys          |
| **Null Values**    | ✅ Allows multiple null values     | ❌ Does NOT allow null values        |
| **Legacy**         | ✅ Part of Java 1.2 (newer)        | ⚠️ Legacy class from earlier Java    |
| **Use in Modern Code** | ✅ Preferred in modern code    | ❌ Rarely used now                   |

Example:
```java
Map<String, String> map = new HashMap<>();
map.put(null, "value");   // works

Map<String, String> table = new Hashtable<>();
table.put(null, "value"); // throws NullPointerException
```


### 4. What is String pool in Java and why do we need String pool? Explain String immunity.
**String Pool (String Interning)** is a special memory area in the Java heap that stores **unique string literals**.  

Example:
```java
String a = "hello";
String b = "hello";
System.out.println(a == b);  // true (same reference)
```
- "hello" is stored once in the pool.
- Reuses the same object for performance and memory efficiency.  

#### Why do ee need String Pool?  
- Memory Optimization – avoids storing duplicate strings.

#### String Immutability:
String is immutable – once created, it cannot be changed.

```java
String s = "abc";
s.concat("def");  // new String is created for "abcdef"
System.out.println(s);  // still "abc"
```
- Enables string pooling (safe to share across code).
- Ensures thread safety.

#### Summary:
- String Pool = shared memory for string literals
- String is immutable = content cannot change
- These work together to save memory and make strings faster and safer to use


### 5. Explain garbage collection. Explain types of garbage collection.
Garbage Collection (GC) is the **automatic memory management** in Java.  
It removes unused objects from **heap** to free up memory.

```java
MyObject obj = new MyObject();
obj = null;  // eligible for GC
```

#### Types of Garbage Collection:

| Garbage Collector | Description                            | Use Case / When to Use                 | Default?           |
|-------------------|----------------------------------------|----------------------------------------|--------------------|
| **Serial GC**     | Single-threaded, simple, stop-the-world | Small apps or single-core systems      | ❌ (can use with `-XX:+UseSerialGC`) |
| **Parallel GC**   | Multi-threaded, high throughput         | CPU-heavy applications with long-running tasks | ❌ (use with `-XX:+UseParallelGC`) |
| **CMS (Concurrent Mark-Sweep)** | Low pause time, concurrent collection | Interactive apps needing low pause     | ❌ Deprecated since Java 9 |
| **G1 GC**         | Region-based, predictable pause times   | Default GC in Java 9+                  | ✅ (default from Java 9+) |
| **ZGC**           | Low-latency, scalable, pause <10ms      | Large heap apps needing minimal pause  | ❌ (use with `-XX:+UseZGC`) |
| **Shenandoah**    | Low-pause GC, concurrent compaction     | Large applications needing low latency | ❌ (Red Hat/OpenJDK variant) |

---

Summary:
- G1 GC is the default in modern Java.  
- Use ZGC or Shenandoah for ultra-low-latency needs.  
- Serial GC is good for small, simple apps.


### 6. What are access modifiers and their scopes in Java?

| Modifier    | Class | Package | Subclass | Outside Package |
|-------------|:-----:|:-------:|:--------:|:---------------:|
| `public`    | ✅    | ✅      | ✅       | ✅              |
| `protected` | ✅    | ✅      | ✅       | ❌              |
| `default`    | ✅    | ✅      | ❌       | ❌              |
| `private`   | ✅    | ❌      | ❌       | ❌              |

- **`public`**: Accessible everywhere.
- **`protected`**: Accessible within same package and subclasses (even in other packages).
- **`default (no keyword)`**: Accessible only within the same package.
- **`private`**: Accessible only within the same class.

Example:
```java
public class Example {
    public int a;         // accessible everywhere
    protected int b;      // accessible in subclass or same package
    int c;                // accessible in same package only
    private int d;        // accessible inside this class only
}
```


### 7. Explain final key word? (Field, Method, Class)
`final` = no change.  

|    | Meaning                                 | Example                                |
|----|-----------------------------------------|----------------------------------------|
| **Field** | Value cannot be changed (constant)      | `final int MAX = 100;`                 |
| **Method** | Method cannot be overridden             | `final void show() {}`                 |
| **Class** | Class cannot be inherited (no subclass) | `final class Animal {}`                |

Example:
```java
final class Animal {
    final int age = 5;

    final void speak() {
        System.out.println("Sound");
    }
}
```


### 8. Explain static keyword (Field, Method, Class). When do we use it?
`static` = **belongs to the class, not the instance**.

|            | Meaning                                           | Example                                |
|------------|---------------------------------------------------|----------------------------------------|
| **Field**  | Shared by all instances (class-level variable)    | `static int count;`                    |
| **Method** | Can be called without creating an object          | `static void print()`                  |
| **Class**  | Static nested class (no access to outer instance) | `static class Helper {}`              |

#### When to Use:
- Share data across all objects → use `static` field
- Utility methods → use `static` method (e.g. `Math.max()`)
- Nested helper classes that don’t need outer class instance → use `static class`


Examples:
```java
public class Counter {
    static int count = 0;  // shared field by all instances of this class

    static void increment() {
        count++;
    }
}
```

```java
public class Outer {

    // Static nested class
    static class Helper {
        static void sayHello() {
            System.out.println("Hello from static nested class!");
        }
    }

    public static void main(String[] args) {
        // No need to create Outer object
        Outer.Helper.sayHello();
    }
}
```
- A `static class` is a **nested class** that does **not need access** to an outer class instance.
- A static class is useful for grouping helper logic inside another class
  without needing access to outer class variables.



### 9. What is the differences between overriding and overloading?
- **Overriding** = a **subclass provides a new version** of a method that is already defined in its superclass.  

- **Overloading** = define **multiple methods with the same name**  
  but **different parameter lists** in the same class.

|                     | **Overriding**                         | **Overloading**             |
|---------------------|----------------------------------------|-----------------------------|
| **Return Type**     | Must be same                           | Can be same or different    |
| **Runtime/Compile** | Resolved at runtime <br/>(Dynamic Polymorphism) | Resolved at compile time <br/>(Static Polymorphism) |

Overriding Example:
```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Bark");
    }
}
```
Overloading Example:
```java
class Printer {
    void print(String s) {
        System.out.println(s);
    }

    void print(int n) {
        System.out.println(n);
    }
}
```


### 10. Explain how Java defines a method signature, and how it helps on overloading and overriding.
**Method Signature** = Method Name + Parameter Types in order
- Includes: **method name** and **parameter types (in order)**.
- Does **not** include: return type.


Examples:
```java
void print(String msg)        // Signature: print(String)
void print(int number)        // Signature: print(int)
void print(String msg, int n) // Signature: print(String, int)
```
> Overloading and Method Signature:
- Overloading = same name, but different signatures.
- Java uses method signature to choose the correct method at compile time

> Overriding and Method Signature:
- Overriding = same name, and same signature.
- Java uses method signature to match and override superclass method at runtime.



### 11. What is the differences between super and this?
> this = refers to current class  
> super = refers to parent class  

`this` Example:
```java
public class Person {
    String name;
    int age;

    // Constructor 1
    public Person(String name) {
        this(name, 0);  // calls Constructor 2
    }

    // Constructor 2
    public Person(String name, int age) {
        this.name = name;      // refers to current object's field
        this.age = age;
    }

    public void introduce() {
        System.out.println("Hi, I'm " + this.name + ", age " + this.age);
    }
}
```

`super` Example:
```java
class Animal {
    String type;

    // Constructor 1
    public Animal() {
        this("Unknown");
    }

    // Constructor 2
    public Animal(String type) {
        this.type = type;
    }

    public void speak() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    String name;

    public Dog(String name) {
        super("Dog");  // calls Animal(String type)
        this.name = name;
    }

    @Override
    public void speak() {
        super.speak();  // calls Animal's speak()
        System.out.println(name + " says Bark");
    }
}
```

Summary:
- **this(...)** → calls another constructor in the same class.
- **super(...)** → calls a constructor from the parent class.
- **this.field / super.method()** → help clarify which class you’re referencing.


### 12. Explain how `equals()` and `hashCode()` work.
> `equals()` checks if **two objects are logically equal**.
- Default (in `Object` class): compares memory address (like `==`).
- Override it to compare **object content**.

  ```java
  @Override
  public boolean equals(Object obj) {
      if (this == obj) return true;
      if (!(obj instanceof Person)) return false;
      Person other = (Person) obj;
      return this.id == other.id;
  }
  ```

> `hashCode()` returns an **integer** that represents the object in hash-based collections like:
> - `HashMap`
> - `HashSet`
> - `Hashtable`  
>
> Java uses `hashCode()` to **locate the bucket** where the object should be stored.


### 13. What is the Java load sequence?
> Java Load Sequence Order: 
> 1. Static variables
> 2. Static blocks
> 3. Instance variables
> 4. Instance initialization blocks
> 5. Constructor

Example:
```java
public class Demo {

  // 1️⃣ Static variable initialized when class is loaded
  static int staticVar = initStaticVar();

  // 2️⃣ Static block runs once when class is loaded
  static {
    System.out.println("Static block executed");
  }

  // 3️⃣ Instance variable initialized when object is created
  int instanceVar = initInstanceVar();

  // 4️⃣ Instance block runs before constructor
  {
    System.out.println("Instance block executed");
  }

  // 5️⃣ Constructor runs last
  public Demo() {
    System.out.println("Constructor executed");
  }

  // Helper method for static variable
  static int initStaticVar() {
    System.out.println("Static variable initialized");
    return 100;
  }

  // Helper method for instance variable
  int initInstanceVar() {
    System.out.println("Instance variable initialized");
    return 200;
  }

  
  // Main method
  public static void main(String[] args) {
    System.out.println("Creating Demo object...");
    Demo demo = new Demo();
  }
}
```
Output:
```
Static variable initialized
Static block executed
Creating Demo object...
Instance variable initialized
Instance block executed
Constructor executed
```


### 14. What is Polymorphism ? And how Java implements it?
> Polymorphism allows one interface, many forms — i.e. different classes can respond differently to the same method call.
 
**Implementation:**  
see Q1.


### 15. What is Encapsulation ? How Java implements it? And why we need encapsulation?
> Encapsulation is hiding internal data and only exposing it via getters/setters or public methods.

**Implementation:**   
see Q1.

> Why we need encapsulation?
1. Protects data
2. Controls access
3. Enables abstraction – hides complex logic behind simple interfaces


### 16. Compare interface and abstract class with use cases.

|                          | **Interface**                                             | **Abstract Class**                          |
|--------------------------|-----------------------------------------------------------|---------------------------------------------|
| **Purpose**              | Defines a contract                                        | Defines base behavior                       |
| **Methods**              | Only abstract (Java 7)<br> Add default / static (Java 8+) | Can have abstract and concrete methods      |
| **Fields**               | `public static final` only <br/>(Constants)                    | Can have any fields                         |
| **Constructor**          | ❌ No constructor                                          | ✅ Can have constructor                      |
| **Multiple Inheritance** | ✅ Allowed                                                 | ❌ Not allowed                               |
| **Use Case**             | Common behavior across unrelated classes                  | Shared code and behavior in related classes |

**Interface** use case:
```java
interface Flyable {
    void fly();
}

class Bird implements Flyable {
    public void fly() {
        System.out.println("Bird flying");
    }
}
```

**Abstract Class** use case:
```java
abstract class Animal {
    void eat() {
        System.out.println("Animal eats");
    }

    abstract void makeSound();
}

class Dog extends Animal {
    void makeSound() {
        System.out.println("Bark");
    }
}
```