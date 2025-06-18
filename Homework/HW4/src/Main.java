// Questions:
// 1. Write code to demo:
//    1.1 Singleton pattern (lazy loading & eager loading), thread-safe guarantee
//    1.2 Factory Method pattern
//    1.3 Abstract Factory pattern
//    1.4 Builder pattern
//    1.5 Please write your own code (do not reuse class-session demos)
//    1.6 Include necessary POJOs and explain how thread-safety is achieved
//
// 2. Write code to explain how default and static keywords work in interfaces since Java 8
//
// 3. Write code to demo Java anonymous classes, using your own abstract classes or interfaces
//
// 4. Write code to explain Lambda expressions with your own functional interface
//
// 5. Write a calculator using BiFunction<T,U,R> and Lambda expressions supporting +, -, *, /

// 1.1 Lazy-loaded, thread-safe Singleton
class LazySingleton {
    private static volatile LazySingleton instance;
    private LazySingleton() {}

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
}

// 1.1 Eager-loaded Singleton
class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    private EagerSingleton() {}
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}

// 1.2 Factory Method Pattern
interface Product {
    void use();
}
class ProductA implements Product {
    public void use() { System.out.println("Using ProductA"); }
}
class ProductB implements Product {
    public void use() { System.out.println("Using ProductB"); }
}
abstract class Creator {
    public abstract Product factoryMethod();
    public void operate() {
        Product p = factoryMethod();
        p.use();
    }
}
class CreatorA extends Creator {
    public Product factoryMethod() { return new ProductA(); }
}
class CreatorB extends Creator {
    public Product factoryMethod() { return new ProductB(); }
}

// 1.3 Abstract Factory Pattern
interface AbstractFactory {
    Product createA();
    Product createB();
}
class ConcreteFactory1 implements AbstractFactory {
    public Product createA() { return new ProductA(); }
    public Product createB() { return new ProductB(); }
}
class ConcreteFactory2 implements AbstractFactory {
    public Product createA() { return new ProductA(); }
    public Product createB() { return new ProductB(); }
}

// 1.4 Builder Pattern
class Person {
    private final String firstName;
    private final String lastName;
    private final int age;

    private Person(Builder b) {
        this.firstName = b.firstName;
        this.lastName = b.lastName;
        this.age = b.age;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private int age;

        public Builder firstName(String fn) { this.firstName = fn; return this; }
        public Builder lastName(String ln)  { this.lastName = ln; return this; }
        public Builder age(int a)           { this.age = a;  return this; }
        public Person build() { return new Person(this); }
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", age=" + age;
    }
}

// 2. Default & static methods in interfaces (Java 8+)
interface MyInterface {
    void abstractMethod();
    default void defaultMethod() {
        System.out.println("This is a default method.");
    }
    static void staticMethod() {
        System.out.println("This is a static method.");
    }
}

class InterfaceImpl implements MyInterface {
    public void abstractMethod() {
        System.out.println("Implemented abstractMethod");
    }
}

// 3. Anonymous Class Demo
abstract class Animal {
    abstract void sound();
}
interface Greeter {
    void greet();
}

// 4. Lambda with custom functional interface
@FunctionalInterface
interface Operation {
    int apply(int a, int b);
}

// 5. Calculator using BiFunction and Lambdas
import java.util.function.BiFunction;

public class DemoPatternsAndFeatures {
    public static void main(String[] args) {
        // Test Singletons
        System.out.println(LazySingleton.getInstance() == LazySingleton.getInstance());
        System.out.println(EagerSingleton.getInstance() == EagerSingleton.getInstance());

        // Test Factory Method
        new CreatorA().operate();
        new CreatorB().operate();

        // Test Abstract Factory
        AbstractFactory f = new ConcreteFactory1();
        f.createA().use();
        f.createB().use();

        // Test Builder
        Person p = new Person.Builder()
                .firstName("Alice")
                .lastName("Smith")
                .age(28)
                .build();
        System.out.println(p);

        // Default & static interface methods
        MyInterface impl = new InterfaceImpl();
        impl.abstractMethod();
        impl.defaultMethod();
        MyInterface.staticMethod();

        // Anonymous classes
        Animal dog = new Animal() { void sound() { System.out.println("Woof"); } };
        dog.sound();
        Greeter greet = () -> System.out.println("Hello from lambda-style anonymous!");
        greet.greet();

        // Lambda Operation
        Operation add = (x, y) -> x + y;
        Operation sub = (x, y) -> x - y;
        System.out.println("5-2=" + sub.apply(5,2));
        System.out.println("5+2=" + add.apply(5,2));

        // BiFunction calculator
        BiFunction<Double,Double,Double> addF = (a,b) -> a + b;
        BiFunction<Double,Double,Double> subF = (a,b) -> a - b;
        BiFunction<Double,Double,Double> mulF = (a,b) -> a * b;
        BiFunction<Double,Double,Double> divF = (a,b) -> a / b;
        System.out.println("10*3=" + mulF.apply(10.0,3.0));
        System.out.println("10/2=" + divF.apply(10.0,2.0));
    }
}

/*
 Thread-safety of LazySingleton:
 - "instance" is declared volatile to prevent instruction reordering.
 - Double-checked locking in getInstance() ensures only one object is created under concurrency.
*/
