

# HW3

Author: Yujiao Huang
Date: June 16, 2025

------

## **1. Write up Example code to demonstrate the three foundmental concepts of OOP.**

### **a) Encapsulation**

- In Java, we make fields private and expose only controlled access via public getters and setters.

```java
public class EncapsulationDemo {
    private String name;   // hidden field
    private int age;

    // public getter
    public String getName() {
        return name;
    }
    // public setter
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        if (age >= 0) this.age = age; // validation
    }

    public static void main(String[] args) {
        EncapsulationDemo person = new EncapsulationDemo();
        person.setName("Alice");
        person.setAge(30);
        System.out.println(person.getName() + " is " + person.getAge());
    }
}
```

------

### **b) Polymorphism**

We declare a reference by the superclass or interface type, but at runtime the JVM dispatches to the actual subclass’s overridden method. 

For example, polymorphism allows us to write code against a general type (like Animal) and rely on the JVM to call the correct overridden method (like Dog.speak() vs. Cat.speak()).

```java
class Animal {
    void speak() { System.out.println("Some sound"); }
}
class Dog extends Animal {
    @Override
    void speak() { System.out.println("Woof!"); }
}
public class PolymorphismDemo {
    public static void main(String[] args) {
        Animal a = new Dog(); // Dog at runtime
        a.speak();            // prints "Woof!"
    }
}
```

------

### **c) Inheritance**

A subclass extends a superclass using the extends keyword, inheriting its behavior and optionally overriding or extending it. (A subclass reuses and extends a superclass.)

```java
class Vehicle {
    void start() { System.out.println("Vehicle starting..."); }
}
class Car extends Vehicle {
    void openTrunk() { System.out.println("Trunk opened."); }
}
public class InheritanceDemo {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();       // inherited
        car.openTrunk();   // new behavior
    }
}
```



## **2.  What is **wrapper data type classes (e.g. Integer, Double) in Java and Why we need wrapper class? 

**Interview's answer:**  Wrapper classes like Integer and Double let us treat primitive values as objects—essential for collections and generics—while also giving us parsing and conversion utilities, null support, and seamless autoboxing/unboxing.

**Wrapper classes** (e.g. Integer, Double) are object‐based counterparts to Java’s primitive types (int, double), encapsulating a single value in an object.

**Why:**

- **Object-only APIs**

  Java collections and generics work with objects, not primitives.

- **Utility methods**

  Wrappers provide handy methods like Integer.parseInt(), Double.isNaN(), toString(), etc.

- **Nullability**

  You can assign null to a wrapper to indicate “no value,” which isn’t possible with primitives.

- **Autoboxing / Unboxing**

  Since Java 5, the compiler automatically converts between primitives and their wrappers for you.

> “Because List<int> isn’t valid in Java, we use List<Integer>. Autoboxing then converts 5 → Integer.valueOf(5) when you call list.add(5).”

```java
import java.util.*;

public class WrapperDemo {
    public static void main(String[] args) {
        List<Integer> scores = new ArrayList<>();
        scores.add(85);             // autoboxes int → Integer
        int first = scores.get(0);  // auto-unboxes Integer → int
        System.out.println(first);  // prints 85
    }
}
```

------



## **3. HashMap vs. Hashtable**

**Interview's answer:**  HashMap is unsynchronized and allows null keys/values, while Hashtable is synchronized (thread-safe) and forbids nulls.[Because Hashtable synchronizes every method, it locks the whole table on each access, which hurts throughput—so in modern code we prefer HashMap (and if we need thread safety, ConcurrentHashMap).]

| **Aspect**       | **HashMap**                    | **Hashtable**              |
| ---------------- | ------------------------------ | -------------------------- |
| Synchronization  | Not synchronized               | Synchronized (thread-safe) |
| Null keys/values | One null key, many null values | Neither allowed            |
| Performance      | Faster (no locking)            | Slower (locks whole map)   |

**Example code:**

```java
import java.util.*;

public class MapDemo {
    public static void main(String[] args) {
        // HashMap example (unsynchronized, allows nulls)
        Map<String, Integer> map = new HashMap<>();
        map.put(null, 100);
        map.put("Alice", null);
        System.out.println("HashMap: " + map);

        // Hashtable example (synchronized, no nulls)
        Map<String, Integer> table = new Hashtable<>();
        // table.put(null, 100);      // throws NullPointerException
        // table.put("Bob", null);    // throws NullPointerException
        table.put("Bob", 200);
        System.out.println("Hashtable: " + table);
    }

}
```

------



## **4.What is **String pool in Java and why we need String pool? Explain String immunity.

- **Pool:** literals (e.g. "foo") are interned in a shared pool.
- **Why:** memory & performance (reuse identical literals).
- **Immutability:** once created, a String cannot change—any “modification” creates a new object.

```java
public class StringPoolDemo {
    public static void main(String[] args) {
        String a = "hello";
        String b = "hello";
        String c = new String("hello");
        System.out.println(a == b);        // true: same pool reference
        System.out.println(a == c);        // false: new object
        c = c.intern();                    // now points to pool
        System.out.println(a == c);        // true

        String s = "foo";
        s.concat("bar");                 // does NOT change s
        System.out.println(s);             // prints "foo"
    }
}
```



## **5.  Explain **garbage collection**? Explain types of garbage collection**

- **What:** JVM automatically reclaims unused objects.
- **Major types:**
  - Serial GC (single-threaded)
  - Parallel GC (throughput-oriented)
  - CMS (Concurrent Mark-Sweep)
  - G1 GC (region-based, low-pause)

```java
public class GCDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 1_000_000; i++) {
            new Object();                // become garbage immediately
        }
        System.gc();                     // suggest GC (not guaranteed)
        System.out.println("Done");
    }
}
```

------



## **6. What are **access modifiers and their scopes in Java?

**Interview answer:** In Java, if you don’t specify a modifier, it’s package-private. protected adds subclass visibility across packages, while public opens it everywhere, and private locks it down to just that class.

 **Access modifiers** in Java control where a class, method, or field can be seen and used. There are four levels:

| **Modifier**  | **Class** | **Package** | **Subclass (same or different pkg)** | **World (anywhere)** |
| ------------- | --------- | ----------- | ------------------------------------ | -------------------- |
| **public**    | ✔         | ✔           | ✔                                    | ✔                    |
| *(default)*   | ✔         | ✔           | ✘                                    | ✘                    |
| **protected** | ✔         | ✔           | ✔                                    | ✘                    |
| **private**   | ✔         | ✘           | ✘                                    | ✘                    |

### **1.** **public**

- **Scope:** Anywhere—visible to all classes in all packages.
- **Use case:** Public APIs, entry-point classes, utility methods.

### **2.** **(default)**(no keyword)

- **Scope:** Only within the *same package*.
- **Use case:** Internal implementation classes or members not meant for external packages.

### **3.** **protected**

- **Scope:**
  - *Same package:* visible to all.
  - *Subclasses:* visible even if in different packages.
- **Use case:** Member methods/fields intended for subclasses’ use but hidden from unrelated classes.

### **4.** **private**

- **Scope:** Only within the *defining class*.
- **Use case:** Internal data hiding; forces access via getters/setters (encapsulation).

------

#### Example code:

```java
package com.example;

public class A {
    public    int  pubField;      // visible everywhere
               int  pkgField;      // default: only com.example
    protected int  protField;     // com.example + subclasses elsewhere
    private   int  privField;     // only class A

    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.pubField);
        System.out.println(a.pkgField);
        System.out.println(a.protField);
        System.out.println(a.privField);
    }
}
```

- In another class in **com.example**, you can access pubField, pkgField, and protField but not privField.
- In a subclass in a **different package**, you can access only pubField and protField.

------



## **7.** Explain **final** key word? (Filed, Method, Class)

- **Field:** value can’t change after initialization.
- **Method:** can’t be overridden.
- **Class:** can’t be extended.

```java
final class Constants {
    public static final double PI = 3.14159; // final field
    public final void show() {              // final method
        System.out.println("Const");
    }
}
// class Extended extends Constants {}   // COMPILE ERROR
```

------



## **8.** Explan **static** keyword? (Filed, Method, Class). When do we usually use it?

- **Field:** one copy per class (shared).

- **Method:** can be called without an instance.

- **Nested Class:** can exist without outer instance.

  

  **When to Use static:**

  - **Constants:** public static final for unchanging values.
  - **Utilities:** Stateless methods that don’t depend on instance data.
  - **Shared State:** Counters, caches, or registries where a single shared copy makes sense.
  - **Nested Types:** Helper classes purely for organization, without binding to an instance.

```java
public class StaticDemo {
    static int count = 0;      // shared across all instances

    public StaticDemo() {
        count++;
    }

    public static void printCount() {   // no instance needed
        System.out.println("Count = " + count);
    }

    static class Helper {               // static nested class
        static void help() { System.out.println("Helping"); }
    }

    public static void main(String[] args) {
        new StaticDemo();
        new StaticDemo();
        StaticDemo.printCount();        // Count = 2
        StaticDemo.Helper.help();
    }
}
```

------



## **9. Overriding vs. Overloading**

- **Overloading:** same method name, different parameter list (compile-time).
- **Overriding:** subclass redefines a superclass method with identical signature (runtime polymorphism).

------



## **10. Explain how Java defines a **method signature**, and how it helps on overloading and overriding.**

- Defined by method name + parameter types (order matters).
- Return type & throws do not form the signature.
- **Overloading:** different signatures in same class.
- **Overriding:** same signature in subclass.

------



## **11.** **super **vs. **this**

- this(...) calls another constructor in the same class.
- super(...) calls a constructor in the parent class.
- this.field vs. super.field distinguish between current and parent.

------



## **12.** Explain how equals and hashCode work.

- **Contract:** equal objects must have same hashCode().
- Used by hash-based collections (HashMap, HashSet).

```java
import java.util.*;
public class Person {
   private String name;
   public Person(String name) { this.name = name; }
   @Override
   public boolean equals(Object o) {
       if (this == o) return true;
       if (!(o instanceof Person)) return false;
       return name.equals(((Person)o).name);
   }
   @Override
   public int hashCode() {
       return name.hashCode();
   }
   public static void main(String[] args) {
       Set<Person> set = new HashSet<>();
       set.add(new Person("Bob"));
       System.out.println(set.contains(new Person("Bob"))); // true
   }
}
```

------



## **13. What is the Java **load sequence**?**

Java’s class loading happens in three phases—**Loading**, **Linking**, and **Initialization**—and then each new follows its own object-creation steps.

1. **Loading**
   - JVM locates and reads the .class file into memory, creating a Class object.
2. **Linking** (three sub-steps)
   - **Verification:** Ensures bytecode is well-formed and secure.
   - **Preparation:** Allocates class-level (static) fields and sets them to default values (0, false, null).
   - **Resolution:** Converts symbolic references (like method and class names) into direct pointers.
3. **Initialization**
   - Executes static field initializers and static { … } blocks in the order they appear.

------

When we do new MyClass():

1. **Memory allocation:** JVM reserves space and zeroes out all instance fields.

2. **Default init:** Primitives become 0/false, object refs become null.

3. **Instance initializers:** Runs any instance field initializers and { … } blocks.

4. **Constructor call:** Finally invokes your constructor body.

   

**Example code:**

```java
public class LoadDemo {
    static { 
        System.out.println("1. static initializer"); 
    }
    { 
        System.out.println("3. instance initializer"); 
    }
    public LoadDemo() {
        System.out.println("4. constructor"); 
    }
    public static void main(String[] args) {
        System.out.println("2. main start");
        new LoadDemo();
    }
}
```

**Expected output:**

```java
1. static initializer   // class initialization
2. main start           // after init, entering main()
3. instance initializer // object creation step 3
4. constructor          // object creation step 4
```

------



## **14. What is **Polymorphism ? And how Java implements it ? 

Java implements it via dynamic method dispatch: the JVM decides at runtime which overridden method to invoke based on the actual object type (see Q1-b).

------



## **15. What is **Encapsulation ? How Java implements it? And why we need encapsulation? 

Hiding internal state (private fields) and exposing a controlled API (public getters/setters).

**Why:** maintain invariants, reduce coupling, improve maintainability (see Q1-a).



## **16. Compare **interface and **abstract class** with use cases.

| **Aspect**           | **Interface**            | **Abstract Class**       |
| -------------------- | ------------------------ | ------------------------ |
| Multiple inheritance | ✔                        | ✘ (only one superclass)  |
| Can have state       | Only static final fields | Can have instance fields |
| Constructors         | ✘                        | ✔                        |
| Default methods      | ✔ (since Java 8)         | n/a                      |

**Use case:**

- Interface when we need a contract and multiple inheritance of type (e.g. Comparable, Serializable).
- Abstract class when we want to share code/state among related classes (e.g. AbstractList in the JDK).