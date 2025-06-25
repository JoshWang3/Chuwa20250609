# HW2 

---

## 1. **Create a POJO Class**

Write a Java POJO (Plain Old Java Object) named `Employee`.

📁 *Code snippets are in `Coding - hw2` package.*

---

## 2. **Object Instantiation and Memory Allocation**

Instantiate at least **two** `Employee` objects and explain JVM memory behavior:

```java
Employee empl = new Employee(...);
```

* `empl` is a reference variable, stored in the **stack** of the current thread.
* The actual `Employee` object is created on the **heap**.
* The object contains fields like `name`, `dateOfBirth`, `department`, `ssn`, `address`.

```java
Department dept = new Department(...);
HomeAddress addr = new HomeAddress(...);
```

* `dept` and `addr` are reference variables stored in the **stack**.
* Their corresponding objects are also created in the **heap**.
* These references are passed to the `Employee` constructor and become part of the `Employee` object.

---

## 3. **Static Content vs Instance Content**

Use a static utility (e.g. `employeeCount`) in `Employee` class to demonstrate the difference:

📁 *Code snippets are in `Coding - hw2` package.*
* **Static Fields/Methods**:

  * Belong to the class.
  * Stored in **Method Area / Metaspace**.
  * Shared by all instances.
  * Initialized once when class is loaded.

* **Instance Fields/Methods**:

  * Belong to each object instance.
  * Stored in the **heap**.
  * Each object gets its own copy.
  * Initialized with each new object.



---

## 4. **Why Global Variables Are Not Recommended**

### ❌ Bad Practice: Global State

```java
public class Globals {
    public static int counter = 0;  // Global variable
}
```

### 🧨 Problems:

* **Breaks Encapsulation**: Any class can modify it.
* **Hard to Debug**: No tracking of changes.
* **Not Thread Safe**:

```java
Runnable task = () -> {
    for (int i = 0; i < 1000; i++) {
        Globals.counter++;  // Race condition!
    }
};
```

---

## 5. **Why Strings Are Immutable in Java**

* **Immutable**: Cannot be changed after creation.
* **New object is returned when modified**.

### 💡 Benefits:

* **Security**: Prevents tampering (e.g. bank URLs).
* **Thread-Safety**: Safe to share across threads.
* **Hashing**: Safe for use as keys in maps.
* **Performance**: Enables string pooling.

---

## 6. **The `final` Keyword – Purpose and Use**

### ① Final Variables: value can't change

```java
final int MAX_USERS = 100;
MAX_USERS = 200; // ❌ Compilation error

final List<String> names = new ArrayList<>();
names.add("Alice");         // ✅ Allowed
names = new ArrayList<>();  // ❌ Not allowed
```

### ② Final Methods: can't be overridden

```java
class Animal {
    public final void speak() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    public void speak() {  // ❌ Cannot override
        System.out.println("Bark");
    }
}
```

### ③ Final Classes: can't be extended

```java
final class Vehicle {}

class Car extends Vehicle { // ❌ Cannot extend
}
```

---

## 7. **Java is Pass-by-Value not**

### ⚠ Why do some people have confusion:

Java **always** passes arguments by value — even object references.

```java
public void changeString(String str) {
    str = "new value";
}

String myStr = "original";
changeString(myStr);
System.out.println(myStr);  // 👉 Still "original"
```

### ✅ Why it looks like pass-by-reference:

Object **references** are passed **by value**, meaning:

* The method receives a copy of the reference.
* You can mutate the object via this reference, but not reassign it.

---

## 8. **Method Overloading and Method Signatures**

### ✅ Example:

```java
public class Printer {
    public void print(String s) {}
    public void print(int i) {}
    public void print(String s, int i) {}
}
```

### 🧠 Method Signature Includes:

* Method name
* Parameter types (in order)
```java
public void printMessage(String msg) {}           // Signature: printMessage(String)
public int printMessage(int code) { return code; } // Signature: printMessage(int)
//These are different method signatures because the parameter types differ.
```


```java
public int add(int a, int b) { return a + b; }       // Signature: add(int, int)
public double add(int x, int y) { return x + y; }    // ❌ Error! Duplicate method
//Even though the return type is different (int vs double), this causes a compile-time error — because the method signature is the same: add(int, int).
```
**Return type, parameter names, exceptions, and modifiers do not affect the method signature.**

---

## 9. **Live Demo Requirement**

---

## 10. **Leetcode Practice – Using Java Collections**

### 🔹 Leetcode 347 – Top K Frequent Elements

📁 *Code snippets are in `Coding - hw2` package.*

---

### 🔹 Leetcode 1 – Two Sum

📁 *Code snippets are in `Coding - hw2` package.*

---

