# HW4

## 1. Write code to demo

1. **Singleton pattern** (both lazy loading and eager loading)
  - Write necessary POJOs together with your core implementation.
  - For your singleton pattern implementation, explain how you would guarantee it is **thread-safe**.
  - *Thread-safe in this scenario means there is truly only one instance in JVM.*
```java
// 1. Lazy Singleton with double-checked locking
class LazySingleton {
   // volatile ensures visibility and prevents reordering
   private static volatile LazySingleton instance;

   // private constructor to prevent instantiation
   private LazySingleton() {}

   // thread-safe lazy instantiation
   public static LazySingleton getInstance() {
      if (instance == null) {
         synchronized (LazySingleton.class) {
            if (instance == null) {
               instance = new LazySingleton();
            }
         }
      }
      return instance;
   }

   public void doWork() {
      System.out.println("Lazy Singleton working...");
   }
}

// 2. Eager Singleton (thread-safe because it's initialized at class loading time)
class EagerSingleton {
   private static final EagerSingleton instance = new EagerSingleton();

   private EagerSingleton() {}

   public static EagerSingleton getInstance() {
      return instance;
   }

   public void doWork() {
      System.out.println("Eager Singleton working...");
   }
}
```
2. **Factory method pattern**

```java
// Factory Method Pattern
abstract class Product {
    abstract void use();
}

class ConcreteProductA extends Product {
    @Override
    void use() {
        System.out.println("Using Product A");
    }
}

class ConcreteProductB extends Product {
    @Override
    void use() {
        System.out.println("Using Product B");
    }
}

abstract class Creator {
    abstract Product createProduct();
}

class CreatorA extends Creator {
    @Override
    Product createProduct() {
        return new ConcreteProductA();
    }
}

class CreatorB extends Creator {
    @Override
    Product createProduct() {
        return new ConcreteProductB();
    }
}
```

3. **Abstract factory pattern**

```java

// 4. Abstract Factory Pattern
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

interface Button {
    void paint();
}

interface Checkbox {
    void check();
}

class WinButton implements Button {
    public void paint() {
        System.out.println("Rendering Windows button");
    }
}

class MacButton implements Button {
    public void paint() {
        System.out.println("Rendering Mac button");
    }
}

class WinCheckbox implements Checkbox {
    public void check() {
        System.out.println("Windows checkbox checked");
    }
}

class MacCheckbox implements Checkbox {
    public void check() {
        System.out.println("Mac checkbox checked");
    }
}

class WinFactory implements GUIFactory {
    public Button createButton() {
        return new WinButton();
    }
    public Checkbox createCheckbox() {
        return new WinCheckbox();
    }
}

class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
```
4. **Builder pattern**

```java

// Builder Pattern
class Computer {
    private String CPU;
    private String RAM;
    private String storage;

    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
    }

    public void specs() {
        System.out.println("CPU: " + CPU + ", RAM: " + RAM + ", Storage: " + storage);
    }

    public static class Builder {
        private String CPU;
        private String RAM;
        private String storage;

        public Builder setCPU(String cpu) {
            this.CPU = cpu;
            return this;
        }

        public Builder setRAM(String ram) {
            this.RAM = ram;
            return this;
        }

        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
```

6. Write necessary POJOs together with your core implementation, for your singleton pattern implementation.  
   Explain how you would guarantee it is **thread-safe** (*i.e., truly only one instance in JVM*).

- LazySingleton:
- 'volatile' ensures visibility between threads and prevents instruction reordering.
- Uses double-checked locking to ensure only one thread creates the instance.
- Guarantees only one instance exists in the JVM, even with multiple threads.

- EagerSingleton:
- Instance is created when the class is loaded using a static final field.
- JVM class loading is thread-safe, so no need for explicit synchronization.
- Ensures a single instance per JVM automatically.
---

## 2. Interface Enhancements in Java 8

Write code to explain how the `default` and `static` keywords work in interfaces since Java 8.

```java


// Demonstrate default and static methods in interface
interface MyInterface {
   // default method with body
   default void greet() {
      System.out.println("Hello from default method!");
   }

   // static method in interface
   static void info() {
      System.out.println("Static method in interface.");
   }
}

class MyClass implements MyInterface {}

class InterfaceDemoTest {
   public static void main(String[] args) {
      MyClass obj = new MyClass();
      obj.greet(); // calls default method
      MyInterface.info(); // calls static method directly from interface
   }
}

```
---

## 3. Java Anonymous Class

Write code to demo **Java anonymous class**.  
You may write your own POJOs (e.g., parent abstract classes, child classes, and interfaces).

```java


//  Anonymous Class demonstration
abstract class Animal {
   abstract void speak();
}

class AnonymousTest {
   public static void main(String[] args) {
      // Anonymous class providing implementation of abstract method
      Animal dog = new Animal() {
         @Override
         void speak() {
            System.out.println("Woof! Woof!");
         }
      };
      dog.speak();
   }
}
```
---

## 4. Lambda Expressions

Write code to explain **Lambda expression** with your own **functional interface**.

```java

//  Lambda expression using custom functional interface
@FunctionalInterface
interface Greeting {
   void sayHello(String name);
}

class LambdaDemo {
   public static void main(String[] args) {
      // Lambda implementation for functional interface
      Greeting g = (name) -> System.out.println("Hello, " + name);
      g.sayHello("Alice");
   }
}

```
---

## 5. Calculator with `BiFunction<T, U, R>` and Lambda Expression

Write a calculator using:

- `BiFunction<T, U, R>` (an internal functional interface provided by JDK),
- and **Lambda expression**.

Your calculator should support two-number operations:

- Addition
- Subtraction
- Multiplication
- Division
```java

//  Calculator using BiFunction and Lambda Expressions
import java.util.function.BiFunction;

class Calculator {
   public static void main(String[] args) {
      // Define operations using BiFunction
      BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
      BiFunction<Integer, Integer, Integer> subtract = (a, b) -> a - b;
      BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
      BiFunction<Integer, Integer, Integer> divide = (a, b) -> b != 0 ? a / b : 0; // check division by zero

      // Apply operations
      System.out.println("Add: " + add.apply(10, 5));
      System.out.println("Subtract: " + subtract.apply(10, 5));
      System.out.println("Multiply: " + multiply.apply(10, 5));
      System.out.println("Divide: " + divide.apply(10, 5));
   }
```