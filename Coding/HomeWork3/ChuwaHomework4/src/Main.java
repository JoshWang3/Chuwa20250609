import java.util.function.BiFunction;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //singleton
        EagerSingleton eagerSingleton = EagerSingleton.getInstance();
        System.out.println(eagerSingleton.getClass().hashCode());

        LazySingleton lazySingleton = LazySingleton.getInstance();
        System.out.println(lazySingleton.getClass().hashCode());

        //factory
        Animal dog = AnimalFactory.getAnimal("dog");
        Animal cat = AnimalFactory.getAnimal("cat");
        dog.speak();
        cat.speak();

        //abstract factory
        UIFactory mac = new MacFactory();
        UIFactory windows = new MacFactory();

        mac.createButton().click();
        windows.createButton().click();
        mac.createTextBox().type();
        windows.createTextBox().type();

        //builder
        User userA = new User.Builder().setAge(18).setName("Aaron").build();
        User userB = new User.Builder().setAge(22).setName("Burr").setEmail("Burr@email.com").build();
        System.out.println("User A:  " + userA.toString() + " user B: " + userB.toString());

        //default in interface java 8
        Car mazda = new Car();
        mazda.drive();
        mazda.start();
        Vehicle toyota = new Car();
        toyota.drive();
        toyota.start();
        Vehicle.service();










    }
}



// 1. Singleton Pattern
// ======================

// --- Eager Singleton ---
class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();
    private EagerSingleton() {}
    public static EagerSingleton getInstance() {
        return instance;
    }
}

// --- Lazy Singleton (Thread-Safe using Double-Checked Locking) ---
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

// 2. Factory Method Pattern
// =========================

interface Animal {
    void speak();
}

class Dog implements Animal {
    public void speak() {
        System.out.println("Woof!");
    }
}

class Cat implements Animal {
    public void speak() {
        System.out.println("Meow!");
    }
}

class AnimalFactory {
    public static Animal getAnimal(String type) {
        return switch (type.toLowerCase()) {
            case "dog" -> new Dog();
            case "cat" -> new Cat();
            default -> throw new IllegalArgumentException("Unknown type");
        };
    }
}

// 3. Abstract Factory Pattern
// ===========================

interface Button {
    void click();
}
interface TextBox {
    void type();
}

class MacButton implements Button {
    public void click() { System.out.println("Mac Button Clicked"); }
}
class MacTextBox implements TextBox {
    public void type() { System.out.println("Typing on Mac TextBox"); }
}

class WindowsButton implements Button {
    public void click() { System.out.println("Windows Button Clicked"); }
}
class WindowsTextBox implements TextBox {
    public void type() { System.out.println("Typing on Windows TextBox"); }
}

interface UIFactory {
    Button createButton();
    TextBox createTextBox();
}

class MacFactory implements UIFactory {
    public Button createButton() { return new MacButton(); }
    public TextBox createTextBox() { return new MacTextBox(); }
}

class WindowsFactory implements UIFactory {
    public Button createButton() { return new WindowsButton(); }
    public TextBox createTextBox() { return new WindowsTextBox(); }
}

// 4. Builder Pattern
// ==================

class User {
    private final String name;
    private final int age;
    private final String email;

    @Override
    public String toString() {
        return "name: " + name + ", age: " + age + ", email: " + email;
    }

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
    }

    public static class Builder {
        private String name;
        private int age;
        private String email;

        public Builder setName(String name) { this.name = name; return this; }
        public Builder setAge(int age) { this.age = age; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public User build() { return new User(this); }
    }
}

// 5. Default and Static in Interface (Java 8+)
// ============================================

interface Vehicle {
    void drive();

    default void start() {
        System.out.println("Vehicle starting...");
    }

    static void service() {
        System.out.println("Vehicle servicing...");
    }
}

class Car implements Vehicle {
    public void drive() {
        System.out.println("Car driving");
    }
}

// 6. Anonymous Class Demo
// ========================

abstract class Greeting {
    abstract void greet();
}

class GreetingDemo {
    public static void main(String[] args) {
        Greeting hello = new Greeting() {
            void greet() {
                System.out.println("Hello from anonymous class");
            }
        };
        hello.greet();
    }
}

// 7. Lambda Expression with Custom Functional Interface
// ======================================================

@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

class LambdaDemo {
    public static void main(String[] args) {
        MathOperation add = (a, b) -> a + b;
        MathOperation mul = (a, b) -> a * b;

        System.out.println("Add: " + add.operate(2, 3));
        System.out.println("Mul: " + mul.operate(2, 3));
    }
}

// 8. Calculator with BiFunction and Lambda
// =========================================


class Calculator {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> sub = (a, b) -> a - b;
        BiFunction<Integer, Integer, Integer> mul = (a, b) -> a * b;
        BiFunction<Integer, Integer, Integer> div = (a, b) -> b != 0 ? a / b : 0;

        System.out.println("Add: " + add.apply(10, 5));
        System.out.println("Sub: " + sub.apply(10, 5));
        System.out.println("Mul: " + mul.apply(10, 5));
        System.out.println("Div: " + div.apply(10, 5));
    }
}
