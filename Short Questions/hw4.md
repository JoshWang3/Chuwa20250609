1. Write code to demo 
   1. singleton pattern (both lazy loading and eager loading), 

```java
//----------------------
// Lazy Loading Singleton
//---------------------
public class LazySingleton {
    /*Lazy Loading: The synchronized keyword ensures that only one thread can access the getInstance method at a time, preventing multiple instantiations.*/
    private static LazySingleton instance;

    private LazySingleton() {
        // Private constructor to prevent instantiation
    }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
//----------------------------
// Eager Laoding Singleton
//----------------------------

public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {
        // Private constructor to prevent instantiation
    }

    /*
     * Eager Loading: The instance is created at class loading time, which is inherently thread-safe as the JVM ensures class loading is atomic.
    */
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}

```
   2. factory method pattern
```java


public interface Drink{

    void serveDrink();
} 
    


public class MilkTea implements Drink {

    @Override
    public void serveDrink() {
        System.out.println("Serving a refreshing cup of milk tea!");
    }
}

class Coffee implements Drink {
    public void serveDrink() {
        System.out.println("Serving a hot cup of coffee!");
    }
}


public class DrinkFactory {
    public static Drink getDrink(String type) {
        if (type == null) {
            return null;
        }
        if (type.equalsIgnoreCase("COFFEE")) {
            return new Coffee();
        } else if (type.equalsIgnoreCase("MILKTEA")) {
            return new MilkTea();
        }
        return null;
    }
}


class FactoryMethodDemo {
    public static void main(String[] args) {
        // Create a CoffeeFactory
        DrinkFactory factory = new DrinkFactory();;
        // Use the factory to create a Drink
        Drink coffee = factory.getDrink("COFFEE");
        // Serve the drink
        coffee.serveDrink();

        // Use the factory to create a Drink
        Drink milkTea = factory.getDrink("MILKTEA");
        // Serve the drink
        milkTea.serveDrink();

        /*OUTPUT:
         * Serving a hot cup of coffee!
         * Serving a refreshing cup of milk tea!
         */
    }
}

```
   3. Abstract factory pattern
```java

interface DFactory {
    Drink createDrink();
}

class TeaFactory implements  DFactory {
    @Override
    public Drink createDrink() {
        return new MilkTea();
    }
    
}

class CoffeeFactory implements DFactory{
    @Override
    public Drink createDrink() {
        return new Coffee();
    }
    
}

public class AbstractFactoryDemo {
    DFactory coffeeFactory = new CoffeeFactory();
    Drink coffee = coffeeFactory.createDrink();
    coffee.serveDrink();

    // Create a tea factory
    DFactory teaFactory = new TeaFactory(); 
    Drink milkTea = teaFactory.createDrink();
    milkTea.serveDrink();
}

```

   4. Builder pattern
```java

// Product Class
class Computer {
    private String CPU;
    private String RAM;
    private String storage;

    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", Storage=" + storage + "]";
    }

    // Builder Class
    public static class Builder {
        private String CPU;
        private String RAM;
        private String storage;

        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public Builder setRAM(String RAM) {
            this.RAM = RAM;
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

// Demo
public class BuilderDemo {
    public static void main(String[] args) {
        Computer computer = new Computer.Builder()
                .setCPU("Intel i7")
                .setRAM("16GB")
                .setStorage("1TB SSD")
                .build();
        System.out.println(computer);
    }
}

```
   5. PLEASE DO NOT USE DEMO CODE USED IN CLASS SESSIONS, PLEASE WRITE YOUR OWN CODE.
   6. Write necessary POJOs together with your core implementation, for your singleton pattern implementation, explain how you would guarantee it is thread-safe (thread-safe in this scenario means: there is truely only one instance in JVM).



2. Write code to explain how do default and static keywords work in interfaces since Java 8

```java

interface MyInterface {
    default void defaultMethod() {
        System.out.println("This is a default method.");
    }

    static void staticMethod() {
        System.out.println("This is a static method.");
    }
}

public class DefaultStaticDemo implements MyInterface {
    public static void main(String[] args) {
        MyInterface.staticMethod(); // Call static method directly

        DefaultStaticDemo demo = new DefaultStaticDemo();
        demo.defaultMethod(); // Call default method from instance
    }
}

```

2. Write code to demo Java anonymous class, you may write your own POJOs (e g. parent abstract classes, child classes, and interfaces)
 
```java
abstract class Animal {
    abstract void makeSound();
}

public class AnonymousClassDemo {

    public static void main(String[] args) {
        Animal dog = new Animal(){
            @Override
            void makeSound() {
                System.out.println("Woof!");
            }
        };
        dog.makeSound();
    }
    
}

```

3. Write code to explain Lambda expression with your own functional interface. 

```java
@FunctionalInterface
interface addCalculate {
    void add(int a, int b);
}

public class LambdaDemo {
    public static void main(String[] args) {
        AddCalculate addCal = (a, b) -> System.out.println("Sum: " + (a + b));
        addCal.add(5, 10);
    }
}

```

4. Write a calculator with BiFunction<T,U,R> (an internal functional interface provided by JDK) and Lambda expression. Your calculator should support two-number addition, subtraction,multiplication,division 
operations.

```java


import java.util.function.BiFunction;

public class Calculator {

    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> subtract = (a, b) -> a - b;
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        BiFunction<Integer, Integer, Integer> divide = (a, b) -> {
            if (b == 0) {
                throw new ArithmeticException("Division by zero is not allowed.");
            }
            return a / b;
        };

        System.out.println("Calculator addition: " + add.apply(5, 3));
        System.out.println("Calculator subtraction: " + subtract.apply(5, 3));  
        System.out.println("Calculator multiplication: " + multiply.apply(5, 3));
        System.out.println("Calculator division: " + divide.apply(5, 3));
       
    }
}
```