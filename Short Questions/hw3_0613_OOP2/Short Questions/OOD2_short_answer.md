### **1\. Demonstrate 3 Fundamental Concepts of OOP**

```java
// Encapsulation, Polymorphism, Inheritance Example
class Animal {
    private String name; // Encapsulation: private field

    public Animal(String name) { this.name = name; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void speak() { System.out.println(name + " makes a sound"); }
}

class Dog extends Animal { // Inheritance
    public Dog(String name) { super(name); }

    @Override
    public void speak() { // Polymorphism (overriding)
        System.out.println(getName() + " barks");
    }
}

class Cat extends Animal {
    public Cat(String name) { super(name); }
    @Override
    public void speak() { System.out.println(getName() + " meows"); }
}

public class OOPDemo {
    public static void main(String[] args) {
        Animal a1 = new Dog("Rex");
        Animal a2 = new Cat("Mimi");

        a1.speak(); // Rex barks
        a2.speak(); // Mimi meows
    }
}
```

---

### **2\. Wrapper Data Types**

Wrapper data type can be used to encapsulate  a primitive data type into an object, enabling their use in contexts where only objects are permitted.

boolean->Boolean
byte->Byte
char->Character
short->Short
int->Integer
long->Long
float->Float
double->Double

We need them for **collections**, **generics**, and **utility methods**.

```java
public class WrapperDemo {
    public static void main(String[] args) {
        int primitive = 5;
        Integer wrapped = primitive; // Autoboxing
        int unwrapped = wrapped; // Unboxing

        System.out.println(wrapped.compareTo(3)); // 1
    }
}
```

---

### **3\. HashMap vs Hashtable**

-   **HashMap**: Non-synchronized, allows `null` key/values, faster.

-   **Hashtable**: Synchronized, legacy, no `null` key/value.


```java
import java.util.*;

public class MapDemo {
    public static void main(String[] args) {
        Map<Integer,String> hashMap = new HashMap<>();
        Map<Integer,String> hashTable = new Hashtable<>();
        hashMap.put(null, "value"); // Allowed
        // hashTable.put(null, "value"); // Throws NullPointerException
    }
}
```

---

### **4\. String Pool & Immutability**

```java
public class StringPoolDemo {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "Hello"; 
        String s3 = new String("Hello");

        System.out.println(s1 == s2); // true (pool)
        System.out.println(s1 == s3); // false
    }
}
```

Why we need String Pool:
The **String Constant Pool** allows reuse of string literals.
This **reduces memory footprint** since identical string literals reference **the same object**.

---

### **5\. Garbage Collection**

Java GC removes unreachable objects.  
Types: **Serial, Parallel, CMS, G1**.

```java
public class GCDemo {
    public static void main(String[] args) {
        GCDemo obj = new GCDemo();
        obj = null;
        System.gc(); // Request GC
    }
    @Override
    protected void finalize() {
        System.out.println("Object collected");
    }
}
```

This code create a new object `GCDemo()` and make it eligible for GC by pointing obj to null.

`finalize()` is a callback invoked once before the object is collected. It is used for cleanup (e.g., releasing resources). Deprecated in Java 9 and will be removed in future versions because it’s unreliable.

By calling `System.gc();` it requests the JVM to run the garbage collector.

You may see "Object collected" printed.

---

### **6\. Access Modifiers**

1️⃣ Class-Level Access

| Modifier    | Same Package | Different Package                     |
| ----------- | ------------ | ------------------------------------- |
| **public**  | ✅ Accessible | ✅ Accessible (import and instantiate) |
| **default** | ✅ Accessible | ❌ Not accessible                      |

2️⃣ Member-Level Access (Fields / Methods / Constructors)

| Modifier      | Same Class | Same Package | Inside Subclass | Non-Subclass Different Package |
| ------------- | ---------- | ------------ | ---------------------------- | ------------------------------ |
| **public**    | ✅          | ✅            | ✅                            | ✅                              |
| **protected** | ✅          | ✅            | ✅                            | ❌ *(unless through subclass)*  |
| **default**   | ✅          | ✅            | diff package: ❌              | ❌                              |
| **private**   | ✅          | ❌            | ❌                            | ❌                              |

---

### **7\. `final` Keyword**

-   **Field**: Constant

-   **Method**: Cannot override

-   **Class**: Cannot extend


```java
final class FinalClass { }
class Demo {
    final int CONST = 10;
    final void display() { }
}
```

---

### **8\. `static` Keyword**

-   **Field**: Shared

-   **Method**: Called without object

-   **Class**: Nested static class


```java
class StaticDemo {
    static int count = 0;
    static void increment() { count++; }
    public static class ListNode {
        public int value;
        public ListNode next;
        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }
}
```

---

### **9\. Overriding vs Overloading**

-   **Overriding**: Runtime, same signature, subclass

-   **Overloading**: Compile-time, different parameters, same class


---

### **10\. Method Signature**

Defined by **method name + parameter types** (not return type). Helps differentiate overloads.

---

### **11\. `super` vs `this`**

-   `super`: Parent class reference

-   `this`: Current object reference


---

### **12\. `equals` and `hashCode`**

Used for comparing objects and storing in hash-based collections.

---

### **13\. Java Load Sequence**

1.  Static blocks

2.  Instance blocks

3.  Constructor


---

### **14\. Polymorphism**

Same interface, different behavior (example in #1). Implemented via **method overriding** and **interfaces**.

---

### **15\. Encapsulation**

Wrapping data (fields) with access control (getters/setters).  
We need it for **data hiding** and **maintainability** (see #1).

---

### **16\. Interface vs Abstract Class**

-   **Interface**: Pure abstraction, multiple inheritance, default/static methods (Java 8).

-   **Abstract class**: Partial abstraction, single inheritance.


```java
interface Flyable { void fly(); }
abstract class Bird { abstract void eat(); }
```

---







