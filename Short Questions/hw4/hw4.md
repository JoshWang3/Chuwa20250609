### 1. Write code to demo for:
 
### 1.1  Singleton pattern (both lazy loading and eager loading)
####  Explain how you guarantee it is thread-safe, meaning: there is truly only one instance in JVM.

> The `Singleton Pattern` ensures that:
>- **Only one instance** is created during the lifetime of the application.
>- That **single instance is globally accessible**.

**Easy Mnemonic:**
- `Eager loading`: The setter is called before the getter.
- `Lazy loading`: The setter is called only during the first getter call.


**Use Cases:**
- Configuration managers
- Logging
- Caches
- Connection pools
- Thread pools


> 1. Eager Loading Singleton:
```java
public class EagerSingleton {
    
    // 1. Private static variable
    private static final EagerSingleton instance = new EagerSingleton();

    // 2. Private constructor prevents instantiation from outside the class
    private EagerSingleton() {}

    // 3. Public static getInstance() method to get the Singleton instance
    public static EagerSingleton getInstance() {
        return instance;
    }
}
```
Why Thread-Safe?
- Instance is created at class loading time. 
- Java’s class loading mechanism guarantees that a class is loaded only once, which naturally ensures thread safety.
- This guarantees a single instance is created before any thread accesses it. All threads will see the same initialized object.


> 2. Lazy Loading Singleton - with Static Inner Class:
```java
public class LazySingleton {

    // Private constructor prevents instantiation from outside the class
    private LazySingleton() {}

    // Private static inner class – loaded only when getInstance() is called
    private static class Holder {
        private static final LazySingleton instance = new LazySingleton();
    }

    // Public static getInstance() method to get the Singleton instance
    public static LazySingleton getInstance() {
        return Holder.instance;
    }
}
```
Why Thread-Safe?
- The inner class is loaded only once when getInstance() is first called.
- Java’s class loading mechanism guarantees that a class is loaded only once, which naturally ensures thread safety.  
- Even if multiple threads call getInstance() at the same time, only one thread will trigger class loading, and other threads will wait.


> 3. Double-Checked Locking Singleton - Lazy + Efficient:
```java
public class DoubleCheckedSingleton {

    // `volatile` ensures visibility across threads and prevents reordering
    private static volatile DoubleCheckedSingleton instance;

    // Private constructor prevents instantiation from outside 
    private DoubleCheckedSingleton() {}

    public static DoubleCheckedSingleton getInstance() {
        // First check (no locking) — fast path for already-initialized instance
        if (instance == null) {
            // Synchronize only the first time
            synchronized (DoubleCheckedSingleton.class) {
                // Second check (with locking) — handles multi-thread race condition
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }
}
```
Why Thread-Safe?
1. `volatile` keyword:
   - Prevents instruction reordering during object creation.
   - Ensures all threads see a fully constructed object.
   - Without it, a thread might see a partially initialized object.

2. Double-checking:
   - Avoids locking once the instance is initialized.
   - Locking only happens once per JVM.

3. JVM Guarantee:
   - Class-level lock on `DoubleCheckedSingleton.class` ensures only one thread can enter the critical section during initialization.
   - Once initialized, all threads return the same reference, avoiding duplicate instances.


---
### 1.2 Factory Method pattern
> The `Factory Method Pattern` defines an interface for creating an object, but lets subclasses decide which class to instantiate.

**Example:**

1. Product Interface
```java
public interface Animal {
    void speak();
}
```

2. Concrete Product Implementations
```java
public class Dog implements Animal {
    public void speak() {
        System.out.println("Woof!");
    }
}

public class Cat implements Animal {
    public void speak() {
        System.out.println("Meow!");
    }
}
```

3. Factory Class with Factory Method
```java
public class AnimalFactory {

    // Factory Method
    public static Animal getAnimal(String type) {
        if (type.equalsIgnoreCase("dog")) {
            return new Dog();
        } else if (type.equalsIgnoreCase("cat")) {
            return new Cat();
        } else {
            throw new IllegalArgumentException("Unknown animal type: " + type);
        }
    }
}
```

4. Client Code
```java
public class Main {
    public static void main(String[] args) {
        Animal dog = AnimalFactory.getAnimal("dog");
        dog.speak(); // Output: Woof!

        Animal cat = AnimalFactory.getAnimal("cat");
        cat.speak(); // Output: Meow!
    }
}
```

**Summary Table:**

| **Component**      | **Description**                                  |
|--------------------|--------------------------------------------------|
| `Animal`           | Interface that defines the common behavior       |
| `Dog`, `Cat`       | Concrete classes implementing `Animal`           |
| `AnimalFactory`    | Factory class that creates `Animal` objects      |
| `getAnimal()`      | Factory Method that returns correct object type  |
| `Main`             | Client code that uses the factory to create objects |


**Benefits:**
- Decouples object creation from usage.
- Adheres to Open/Closed Principle.



---
### 1.3 Abstract Factory pattern
> The `Abstract Factory Pattern` provides an interface for creating families of objects **without specifying their concrete classes**.

**Example: GUI Toolkit**
1. Abstract Product Interfaces
```java
public interface Button {
    void paint();
}

public interface Checkbox {
    void paint();
}
```

2. Concrete Products for Windows
```java
public class WindowsButton implements Button {
    public void paint() {
        System.out.println("Rendering a Windows-style button.");
    }
}

public class WindowsCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Rendering a Windows-style checkbox.");
    }
}
```

3. Concrete Products for Mac
```java
public class MacButton implements Button {
    public void paint() {
        System.out.println("Rendering a Mac-style button.");
    }
}

public class MacCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Rendering a Mac-style checkbox.");
    }
}
```

4. Abstract Factory
```java
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
```

5. Concrete Factories
```java
public class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }

    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

public class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }

    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
```

6. Client Code
```java
public class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void renderUI() {
        button.paint();
        checkbox.paint();
    }

    public static void main(String[] args) {
        GUIFactory factory;

        // Switch between OS factories
        factory = new WindowsFactory();  // or new MacFactory();

        Application app = new Application(factory);
        app.renderUI();
    }
}
```

**Summary Table:**

| **Component**              | **Description**                                              |
|----------------------------|--------------------------------------------------------------|
| `Button`, `Checkbox`       | Abstract product interfaces defining the contract            |
| `WindowsButton`, `MacButton`<br>`WindowsCheckbox`, `MacCheckbox` | Concrete products implementing the interfaces            |
| `GUIFactory`               | Abstract factory interface declaring creation methods        |
| `WindowsFactory`, `MacFactory` | Concrete factories that create Windows or Mac components     |
| `Application`              | Client code using a factory to create and render components  |


**Benefits:**
- Keeps code decoupled from concrete implementations.
- Adheres to Open/Closed and Dependency Inversion principles.



---
### 1.4 Builder pattern
> The `Builder Pattern` is used to construct complex objects step-by-step.  
> It helps create immutable objects with many optional parameters.

**Example: Building a `User` object**
1. Target Class (`User`)
```java
public class User {
    // Required fields
    private final String name;
    private final String email;

    // Optional fields
    private final int age;
    private final String phone;

    // Private constructor – only the builder can call it
    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
        this.phone = builder.phone;
    }

    // Static nested Builder class
    public static class Builder {
        private final String name;
        private final String email;
        private int age = 0;
        private String phone = "";

        public Builder(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
```

2. Client Code
```java
public class Main {
    public static void main(String[] args) {
        User user = new User.Builder("Alice", "alice@example.com")
                .age(30)
                .phone("123-456-7890")
                .build();
    }
}
```

**Summary Table:**

| **Component**         | **Description**                                                 |
|------------------------|-----------------------------------------------------------------|
| `User`                | The complex object being constructed                            |
| `User.Builder`        | Static inner builder class that sets up fields step-by-step     |
| `age()`, `phone()`    | Chained setter methods for optional fields                      |
| `build()`             | Final method that constructs and returns the `User` object      |
| `Main`                | Client code that uses the builder to create a `User` instance   |


**Benefits:**
- Clean construction of immutable objects.
- Avoids telescoping constructor anti-pattern.



---
### 2. Write code to explain how do default and static keywords work in interfaces since Java 8.
> A `default` method in an interface is a method that provides a concrete implementation so that classes implementing the interface **do not have to override it** unless they want to customize the behavior.
>
> A `static` method in an interface is not inherited by implementing classes. It belongs only to the interface itself and is used to define utility/helper methods.


|          | `default` method                          | `static` method                          |
|----------|--------------------------------------------|-------------------------------------------|
| Access   | Through implementing class instance        | Through interface name                    |
| Purpose  | Provide default behavior for subclasses    | Utility/helper methods                    |
| Override | Can be overridden in implementing class    | Cannot be overridden                      |

Example:

```java
interface MyInterface {

    // Static method – belongs to the interface itself
    static void staticMethod() {
        System.out.println("Static method in interface");
    }

    // Default method – can be overridden by implementing classes
    default void defaultMethod() {
        System.out.println("Default method in interface");
    }
}


class MyClass implements MyInterface {

    // Overriding default method
    @Override
    public void defaultMethod() {
        System.out.println("Overridden default method in class");
    }
}


public class Main {
    public static void main(String[] args) {
        MyClass obj = new MyClass();

        // Call overridden default method
        obj.defaultMethod();  // Output: Overridden default method in class

        // Call static method using interface name
        MyInterface.staticMethod();  // Output: Static method in interface
    }
}
```



---
### 3. Write code to demo Java anonymous class.  
> An **anonymous class** is an unnamed inner class used to create an **instance of a class (usually an interface or abstract class)** on the fly with overridden methods.

**Example: Implementing an Interface**

```java
interface Greeting {
    void sayHello();
}


public class Main {
    public static void main(String[] args) {
        
        // Anonymous class implementing the Greeting interface
        Greeting greeting = new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("Hello from anonymous class!");
            }
        };

        
        greeting.sayHello();  // Output: Hello from anonymous class!
    }
}
```

**Example: Extending an Abstract Class**
```java
abstract class Animal {
    abstract void makeSound();

    void sleep() {
        System.out.println("Sleeping...");
    }
}


public class Main {
    public static void main(String[] args) {
        
        // Anonymous class extending abstract class Animal
        Animal dog = new Animal() {
            @Override
            void makeSound() {
                System.out.println("Woof! Woof!");
            }
        };

        dog.makeSound();  // Output: Woof! Woof!
        dog.sleep();      // Output: Sleeping...
    }
}
```

**Purpose:** 
- Provide quick one-time use implementation without creating a named class.
- Useful when the class is used once and does not require reuse.
- Commonly used for event handling, threads, or short-lived behavior.



---
### 4. Write code to explain Lambda expression with your own functional interface.

> `Lambda expressions` provide a concise way to write anonymous functions.
>- Syntax:  `(parameters) -> { body }`

> A `functional interface` is an interface that contains exactly one abstract method. It can have multiple default or static methods, but only one method that must be implemented.
>- `@FunctionalInterface` annotation:  Optional but recommended for clarity and compile-time checking.

> Lambda is the implementation of the abstract method in the functional interface.


**Example:**

Step 1: Define a Functional Interface
```java
@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}
```

Step 2: Use Lambda to Implement the Interface
```java
public class Main {
    public static void main(String[] args) {
        // Lambda: (a, b) -> a + b
        MathOperation add = (a, b) -> a + b;

        // Lambda: (a, b) -> a * b
        MathOperation multiply = (a, b) -> a * b;

        System.out.println("Sum: " + add.operate(3, 4));  // Output: Sum: 7
        System.out.println("Product: " + multiply.operate(3, 4));  // Output: Product: 12
    }
}
```
 
> **A Side Note on Lambda:**
> 
> In Java, a lambda expression can **access variables from the outer scope** (called **captured variables**) **only if those variables are effectively final** — meaning they are not modified after being assigned.
> 
> This means:
>- You can use a final variable.
>- You can use a non-final variable, only if you never modify it.
>- You can use object references, but you must not reassign it to a new object.

**Examples:**
1. Final variable ✅
```java
final int x = 10;

Runnable r = () -> {
    System.out.println("x = " + x);  // OK
};

r.run();
```

2. Non-final, but not modified ✅
```java
int y = 20;

Runnable r = () -> {
    System.out.println("y = " + y);  // OK
};

r.run();

// ❌ If you later try y = 30;, the compiler will complain.
```

3. Object variable (only internal state changes) ✅
```java
class Data {
    int value = 5;
}

Data d = new Data();

Runnable r = () -> {
    System.out.println("d.value = " + d.value);  // OK
};

d.value = 10;  // still OK
        
r.run();

// ✅ Allowed because d still points to the same memory address — only the internal value changed.
```

4. Reassigning object variable ❌
```java
Data d = new Data();

Runnable r = () -> {
    System.out.println(d.value);  // ❌ compile error if d is reassigned later
};

d = new Data();  // ❌ compiler error because you're changing d's memory address (i.e., d now points to a new object).
```

**Summary Table:**

| Variable Type          | Allowed in Lambda? | Explanation                                        |
| ---------------------- | ------------------ | -------------------------------------------------- |
| `final int x = 10;`    | ✅ Yes              | `x` is `final`, so never changes                   |
| `int y = 20;`          | ✅ Yes              | not `final`, but not changed = "effectively final" |
| `Data d = new Data();` | ✅ Yes              | object not reassigned, still same memory address   |
| `d = new Data();`      | ❌ No               | reassignment changes the reference                 |



---
### 5. Write a calculator with `BiFunction<T,U,R>` (an internal functional interface provided by JDK) and Lambda expression.
#### Your calculator should support two-number addition, subtraction, multiplication, division operations.
> **BiFunction<T, U, R>** is a built-in functional interface in Java.

**Syntax:**
```java
@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
}
```
It takes two inputs `T` and `U`, and returns a result `R`.


**Calculator Code:**
```java
import java.util.function.BiFunction;

public class Calculator {
    public static void main(String[] args) {
        
        // Define operations using lambda and BiFunction
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> subtract = (a, b) -> a - b;
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        BiFunction<Integer, Integer, Integer> divide = (a, b) -> {
            if (b == 0) {
                throw new ArithmeticException("Cannot divide by zero");
            }
            return a / b;
        };

        // Use the operations
        System.out.println("Add: " + add.apply(10, 5));            // 15
        System.out.println("Subtract: " + subtract.apply(10, 5));  // 5
        System.out.println("Multiply: " + multiply.apply(10, 5));  // 50
        System.out.println("Divide: " + divide.apply(10, 5));      // 2
    }
}
```
 




















