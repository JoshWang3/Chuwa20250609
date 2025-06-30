# hw4 submission

## Q1: singleton pattern (both lazy loading and eager loading), factory method pattern, Abstract factory pattern, Builder pattern

### Answer:
Demo Code in:

Singleton ensures only one instance of a class exists in the entire JVM.

Lazy Initialization Singleton:

Uses double-checked locking to ensure: Instance is only created once. Only the first access uses synchronization.

Eager Initialization Singleton:

Instance is created when the class is loaded.

Automatically thread-safe because the JVM loads classes in a thread-safe manner.

Not memory-efficient if the object is never used.

Factory Pattern:

Hide the object creation logic and return the appropriate subclass based on input.

Abstract Factory Pattern:

Create families of related objects without specifying their concrete classes.

Builder Pattern:

Construct complex objects step-by-step, especially when they have optional fields.

## Q2: Write code to explain how do default and static keywords work in interfaces since Java 8

### Answer:
`default` method:
Provides a default implementation inside an interface. Can be overridden by implementing classes

`static` method:
Belongs to the interface itself, not inherited. Must be called using InterfaceName.method()

Interfaces can evolve without breaking existing implementations

```java
interface Calculator {
    // Default method (can be overridden by implementing classes)
    default int add(int a, int b) {
        return a + b;
    }

    // Static method (cannot be overridden)
    static int multiply(int a, int b) {
        return a * b;
    }
}

class BasicCalculator implements Calculator {
    // Optionally override default method
    @Override
    public int add(int a, int b) {
        System.out.println("Custom add from BasicCalculator");
        return a + b + 1; // Intentional custom logic
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== default method ===");
        Calculator calc = new BasicCalculator();
        int result1 = calc.add(5, 3);  // Uses overridden default method
        System.out.println("Result of add: " + result1);

        System.out.println("\n=== static method ===");
        int result2 = Calculator.multiply(5, 3); // Called using interface name
        System.out.println("Result of multiply: " + result2);
    }
}
```

## Q3: Write code to demo Java anonymous class, you may write your own POJOs (e.g. parent abstract classes, child classes, and interfaces)

### Answer:
To quickly implement a one-off class without creating a named subclass

Often used in event listeners, callbacks, and functional interfaces (before Java 8 lambdas)

```java
abstract class Animal {
    abstract void makeSound();
}

interface Greeting {
    void sayHello();
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Anonymous class from abstract class ===");

        Animal dog = new Animal() {
            @Override
            void makeSound() {
                System.out.println("Woof! (This is an anonymous Dog)");
            }
        };
        dog.makeSound();

        System.out.println("\n=== Anonymous class from interface ===");

        Greeting greeter = new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("Hello there! (from an anonymous class)");
            }
        };
        greeter.sayHello();

        System.out.println("\n=== Anonymous class as method argument ===");

        greetUser(new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("Hello, this is passed anonymously into a method!");
            }
        });
    }

    public static void greetUser(Greeting g) {
        g.sayHello();
    }
}
```

## Q4: Write code to explain Lambda expression with your own functional interface.

### Answer:
Lambda Expressions makes code concise and readable

Used with Java APIs like Stream, Collections, Runnable, etc.

Avoids writing anonymous inner classes

```java
// Custom functional interface
@FunctionalInterface
interface Calculator {
    int operate(int a, int b);
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Lambda expression demo ===");

        // Using lambda to implement Calculator interface
        Calculator addition = (a, b) -> a + b;
        Calculator subtraction = (a, b) -> a - b;
        Calculator multiply = (a, b) -> a * b;

        int x = 10, y = 5;

        System.out.println("Addition: " + addition.operate(x, y));      // 15
        System.out.println("Subtraction: " + subtraction.operate(x, y)); // 5
        System.out.println("Multiplication: " + multiply.operate(x, y)); // 50
    }
}
```

## Q5: Write a calculator with BiFunction<T,U,R> (an internal functional interface provided by JDK) and Lambda expression. Your calculator should support two-number addition, subtraction,multiplication,division operations.

### Answer:
```java
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        // Declare calculator operations using BiFunction and lambdas
        BiFunction<Double, Double, Double> add = (a, b) -> a + b;
        BiFunction<Double, Double, Double> subtract = (a, b) -> a - b;
        BiFunction<Double, Double, Double> multiply = (a, b) -> a * b;
        BiFunction<Double, Double, Double> divide = (a, b) -> {
            if (b == 0) {
                System.out.println("Error: Division by zero!");
                return 0.0;
            }
            return a / b;
        };

        double x = 12.0;
        double y = 4.0;

        System.out.println("=== Lambda Calculator using BiFunction ===");
        System.out.println(x + " + " + y + " = " + add.apply(x, y));
        System.out.println(x + " - " + y + " = " + subtract.apply(x, y));
        System.out.println(x + " * " + y + " = " + multiply.apply(x, y));
        System.out.println(x + " / " + y + " = " + divide.apply(x, y));
    }
}
```
