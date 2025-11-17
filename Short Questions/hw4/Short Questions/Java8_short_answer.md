## 🧩 1. Design Patterns Demonstration

Q: Write code to demo 
1. singleton pattern (both lazy loading and eager loading), 
2. factory method pattern
3. Abstract factory pattern
4. Builder pattern
5. PLEASE **DO NOT** USE DEMO CODE USED IN CLASS SESSIONS, PLEASE WRITE YOUR OWN CODE.
6. Write necessary POJOs together with your core implementation, for your **singleton pattern** implementation, explain how you would guarantee it is **thread-safe** (thread-safe in this scenario means: there is **truely only one** instance in JVM).

A:
### (a) Singleton Pattern — Eager Loading

```java
// EagerSingleton.java
public class EagerSingleton {
    // Instance created at class loading time
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {
        System.out.println("Eager Singleton instance created.");
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public void showMessage() {
        System.out.println("Hello from Eager Singleton!");
    }

    public static void main(String[] args) {
        EagerSingleton s1 = EagerSingleton.getInstance();
        EagerSingleton s2 = EagerSingleton.getInstance();
        System.out.println(s1 == s2); // true
    }
}
```

---

### (b) Singleton Pattern — Lazy Loading (Thread-Safe)

```java
// LazySingleton.java
public class LazySingleton {
    private static volatile LazySingleton instance;

    private LazySingleton() {
        System.out.println("Lazy Singleton instance created.");
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) { // thread-safe
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    public void display() {
        System.out.println("Hello from Lazy Singleton!");
    }
}
```

*✅ Thread safety* is guaranteed because the synchronized block ensures only one thread creates the instance.

---

### (c) Factory Method Pattern

```java
// Factory pattern demo
interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Square implements Shape {
    public void draw() {
        System.out.println("Drawing Square");
    }
}

class ShapeFactory {
    public Shape getShape(String type) {
        if (type.equalsIgnoreCase("circle")) return new Circle();
        if (type.equalsIgnoreCase("square")) return new Square();
        return null;
    }

    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Shape shape1 = factory.getShape("circle");
        shape1.draw();
        Shape shape2 = factory.getShape("square");
        shape2.draw();
    }
}
```

---

### (d) Abstract Factory Pattern

```java
// Abstract Factory
interface Button {
    void paint();
}

class WindowsButton implements Button {
    public void paint() { System.out.println("Render Windows Button"); }
}

class MacButton implements Button {
    public void paint() { System.out.println("Render Mac Button"); }
}

interface GUIFactory {
    Button createButton();
}

class WindowsFactory implements GUIFactory {
    public Button createButton() { return new WindowsButton(); }
}

class MacFactory implements GUIFactory {
    public Button createButton() { return new MacButton(); }
}

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        GUIFactory factory = new WindowsFactory();
        factory.createButton().paint();

        factory = new MacFactory();
        factory.createButton().paint();
    }
}
```

---

### (e) Builder Pattern

```java
class Computer {
    private String CPU;
    private String GPU;
    private int RAM;

    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.GPU = builder.GPU;
        this.RAM = builder.RAM;
    }

    public static class Builder {
        private String CPU;
        private String GPU;
        private int RAM;

        public Builder CPU(String CPU) { this.CPU = CPU; return this; }
        public Builder GPU(String GPU) { this.GPU = GPU; return this; }
        public Builder RAM(int RAM) { this.RAM = RAM; return this; }

        public Computer build() {
            return new Computer(this);
        }
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + CPU + ", GPU=" + GPU + ", RAM=" + RAM + "]";
    }

    public static void main(String[] args) {
        Computer pc = new Computer.Builder().CPU("Intel i7").GPU("RTX 4070").RAM(32).build();
        System.out.println(pc);
    }
}
```

---

## 💡 2. Default and Static Methods in Interfaces

Q: Write code to explain how do default and static keywords work in interfaces since Java 8
A:
```java
interface Vehicle {
    default void start() {
        System.out.println("Vehicle is starting...");
    }

    static void showType() {
        System.out.println("Vehicle type: Generic");
    }
}

class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car engine started!");
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        Vehicle v = new Car();
        v.start();            // calls overridden default
        Vehicle.showType();   // static method call via interface
    }
}
```

*Default methods* allow interfaces to evolve without breaking existing implementations.

---

## 🧠 3. Anonymous Class Example

Q: Write code to demo Java anonymous class, you may write your own POJOs (e.g. parent abstract classes, child classes, and interfaces)
A:
```java
abstract class Animal {
    abstract void makeSound();
}

public class AnonymousDemo {
    public static void main(String[] args) {
        Animal cat = new Animal() {
            @Override
            void makeSound() {
                System.out.println("Meow!");
            }
        };
        cat.makeSound();
    }
}
```

*Anonymous inner classes* are used to instantiate abstract classes or interfaces inline.

---

## ⚙️ 4. Lambda Expression and Functional Interface

Q: Write code to explain Lambda expression with your own functional interface. 
A:
```java
@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

public class LambdaExample {
    public static void main(String[] args) {
        MathOperation add = (a, b) -> a + b;
        MathOperation multiply = (a, b) -> a * b;
        System.out.println("5 + 3 = " + add.operate(5, 3));
        System.out.println("5 * 3 = " + multiply.operate(5, 3));
    }
}
```

A *functional interface* has exactly one abstract method, making it usable with lambdas.

---

## 🧮 5. Calculator Using `BiFunction<T,U,R>` and Lambda

Q:Write a calculator with BiFunction<T,U,R> (an internal functional interface provided by JDK) and Lambda expression. Your calculator should support two-number addition, subtraction,multiplication,division operations.
A:
```java
import java.util.function.BiFunction;
import java.util.Scanner;

public class BiFunctionCalculator {
    public static void main(String[] args) {
        BiFunction<Double, Double, Double> add = (a, b) -> a + b;
        BiFunction<Double, Double, Double> sub = (a, b) -> a - b;
        BiFunction<Double, Double, Double> mul = (a, b) -> a * b;
        BiFunction<Double, Double, Double> div = (a, b) -> b != 0 ? a / b : Double.NaN;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first number: ");
        double x = sc.nextDouble();
        System.out.print("Enter second number: ");
        double y = sc.nextDouble();

        System.out.println("Add: " + add.apply(x, y));
        System.out.println("Subtract: " + sub.apply(x, y));
        System.out.println("Multiply: " + mul.apply(x, y));
        System.out.println("Divide: " + div.apply(x, y));
        sc.close();
    }
}
```

This uses the **functional interface `BiFunction<T,U,R>`** to define arithmetic operations concisely.
