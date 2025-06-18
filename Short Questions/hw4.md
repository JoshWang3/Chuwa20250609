### 1. Design Patterns
#### (1) Singleton Pattern: 
Defnition: A creational pattern that restricts a class to a single instance and gives global access to it—typically by hiding its constructor and exposing a static getInstance() (backed by JVM-safe static initialization or a synchronized/volatile lazy init).

- Eager Load:   
Thread-safe: call DatabaseConnection.getInstance() anywhere, will always get the same object. 
The JVM takes care of thread-safety during static initialization.
```java
public class DatabaseConnection {
    private static final DatabaseConnection INSTANCE = new DatabaseConnection();

    private DatabaseConnection() {
        System.out.println("Connecting to DB...");
    }

    public static DatabaseConnection getInstance() {
        return INSTANCE;
    }

    public void query(String sql) {
        System.out.println("Running SQL -> " + sql);
    }
}

```
- Lazy Load:  
Thread-safe: volatile and double-checked locking ensures exactly one instance, even if two threads race to initialize.
```java
public class LazyDatabaseConnection {
    private static volatile LazyDatabaseConnection instance;

    private LazyDatabaseConnection() {
        System.out.println("Lazy DB connect...");
    }

    public static LazyDatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (LazyDatabaseConnection.class) {
                if (instance == null) {
                    instance = new LazyDatabaseConnection();
                }
            }
        }
        return instance;
    }

    public void query(String sql) {
        System.out.println("Running SQL -> " + sql);
    }
}

```

#### (2) Factory Pattern
Definition: A creational pattern where you hide direct calls to constructors behind a factory interface or method (e.g. createProduct()), so clients request objects from the factory instead of using new. This decouples client code from concrete classes, makes it trivial to add or swap product variants, and keeps object-creation logic in one place.
```java
public interface Notification {
    void notifyUser(String msg);
}

public class EmailNotification implements Notification {
    public void notifyUser(String msg) {
        System.out.println("[Email] " + msg);
    }
}

public class SMSNotification implements Notification {
    public void notifyUser(String msg) {
        System.out.println("[SMS] " + msg);
    }
}

public abstract class NotificationFactory {
    protected abstract Notification createNotification();

    public void send(String message) {
        Notification n = createNotification();
        n.notifyUser(message);
    }
}

public class EmailFactory extends NotificationFactory {
    protected Notification createNotification() {
        return new EmailNotification();
    }
}

public class SMSFactory extends NotificationFactory {
    protected Notification createNotification() {
        return new SMSNotification();
    }
}

```

```java
NotificationFactory factory = new SMSFactory();
factory.send("Your code is 1234");
```
#### (3) Abstract Factory Pattern
Definition: A creational pattern that defines an interface for producing families of related objects (e.g., buttons, textboxes) without tying clients to their concrete classes. You pick a concrete “factory” (like Light or Dark), and it guarantees all products it creates belong together, so you can swap entire product families by switching factories.  
```java
public interface Button  { void render(); }
public interface Textbox { void render(); }

public class LightButton implements Button   { public void render()  { System.out.println("Light button"); } }
public class LightTextbox implements Textbox { public void render()  { System.out.println("Light textbox"); } }

public class DarkButton implements Button    { public void render()  { System.out.println("Dark button");  } }
public class DarkTextbox implements Textbox  { public void render()  { System.out.println("Dark textbox");  } }

public interface WidgetFactory {
    Button createButton();
    Textbox createTextbox();
}

public class LightFactory implements WidgetFactory {
    public Button createButton()   { return new LightButton(); }
    public Textbox createTextbox() { return new LightTextbox(); }
}

public class DarkFactory implements WidgetFactory {
    public Button createButton()   { return new DarkButton(); }
    public Textbox createTextbox() { return new DarkTextbox(); }
}

```
```java
public class App {
    private Button btn;
    private Textbox txt;

    public App(WidgetFactory f) {
        btn = f.createButton();
        txt = f.createTextbox();
    }

    public void draw() {
        btn.render();
        txt.render();
    }

    public static void main(String[] args) {
        String theme = System.getProperty("theme", "light");
        WidgetFactory factory = theme.equals("dark") ? new DarkFactory() : new LightFactory();
        new App(factory).draw();
    }
}

```

### (4) Builder Pattern
Definition: The Builder Pattern is a creational design pattern that separates an object’s construction from its final representation: you use a dedicated Builder class with chainable methods to configure each part of the object step by step, then call build() to assemble and return the finished, often immutable instance. This approach avoids constructors overloaded with numerous parameters, makes optional settings and sensible defaults easy to manage, improves readability by naming each configuration step, and lets you enforce validation or invariants before the object is created.
```java
public class Car {
    private final String engine;
    private final boolean gps;
    private final int seats;
    private final boolean sunroof;

    private Car(Builder b) {
        engine  = b.engine;
        gps     = b.gps;
        seats   = b.seats;
        sunroof = b.sunroof;
    }

    public static class Builder {
        private final String engine;
        private boolean gps     = false;
        private int     seats   = 4;
        private boolean sunroof = false;

        public Builder(String engine) {
            this.engine = engine;
        }

        public Builder gps(boolean val) {
            gps = val; return this;
        }

        public Builder seats(int val) {
            seats = val; return this;
        }

        public Builder sunroof(boolean val) {
            sunroof = val; return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    @Override
    public String toString() {
        return "Car(engine=" + engine
            + ", gps=" + gps
            + ", seats=" + seats
            + ", sunroof=" + sunroof + ")";
    }
}


```

```java
    Car myCar = new Car.Builder("V6")
            .gps(true)
            .seats(2)
            .sunroof(true)
            .build();

    System.out.println(myCar);
```
### 2. Write code to explain how default and static keywords work in interfaces since Java 8.  
Default method: An instance-level method in an interface that comes with a **built-in** implementation, which implementing classes can **use as-is** or **override as needed**.  
```java
// Default Method Example
public interface CoffeeMachine {
    void brewEspresso();
    void addWater(int ml);

    default void makeAmericano() {  // default method: shared implementation
        brewEspresso();
        addWater(100);
        System.out.println("☕ Your Americano is ready!");
    }
}

public class MyCoffeeMachine implements CoffeeMachine {
    @Override
    public void brewEspresso() {
        System.out.println("Brewing espresso...");
    }

    @Override
    public void addWater(int ml) {
        System.out.println("Adding " + ml + "ml of water");
    }
}

// Usage
CoffeeMachine machine = new MyCoffeeMachine();
machine.makeAmericano();

```
Static method: A static utility method in an interface that must be called via InterfaceName.method() and **cannot be inherited or overridden** by implementing classes.  
```java
// Static Method Example
public interface UnitConverter {
    static double milesToKilometers(double miles) {  // static utility method
        return miles * 1.60934;
    }

    static double kilometersToMiles(double km) {      // static utility method
        return km / 1.60934;
    }
}

// Usage
double km = UnitConverter.milesToKilometers(5.0);
System.out.println("5 miles = " + km + " km");
```
### 3. Write code to demo a Java anonymous class—you may write your own POJOs (e.g., parent abstract classes, child classes, and interfaces).  
Definition: An anonymous inner class is a local class **without a name**, **declared and instantiated all at once in a single expression (using new SuperType() { … })**. It either **extends a concrete class** or **implements an interface**, provides method overrides inline, and can capture final or effectively final variables from its enclosing scope.
```java
// Define an abstract Greeting
public abstract class Greeting {
    public abstract void greet(String name);
}

public class Greeter {
    private Greeting greeting;
    public void setGreeting(Greeting g) { this.greeting = g; }
    public void doGreet() {
        greeting.greet("World");
    }
}

public class Demo {
    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        // anonymous subclass: implement Greeting on the fly
        greeter.setGreeting(new Greeting() {
            @Override
            public void greet(String name) {
                System.out.println("Hello, " + name + "!");
            }
        });
        greeter.doGreet();
    }
}

```
### 4. Write code to explain a Lambda expression using your own functional interface.  
Definition: A lambda expression (introduced in Java 8) is a short, anonymous function-like construct that provides an inline implementation of a functional interface. It consists of a parameter list, the -> arrow token, and a body (either an expression or block), allowing you to pass behavior as data and eliminate boilerplate class or anonymous-inner-class code.  
```java
// Functional interface with a single abstract method
@FunctionalInterface
interface Calculator {
    int calculate(int x, int y);
}

public class Demo {
    public static void main(String[] args) {
        // Lambda: implement calculate() inline
        Calculator add = (a, b) -> a + b;
        System.out.println("Sum: " + add.calculate(5, 3));
    }
}

```

```java
// Calculator add = (a, b) -> a + b;
// equivalent to the lambda version without using a lambda

Calculator add = new Calculator() {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
};

```
### 5. Write a calculator using BiFunction<T, U, R> (an internal functional interface provided by the JDK) and a Lambda expression. Your calculator should support two-number addition, subtraction, multiplication, and division operations.  
```java
import java.util.function.BiFunction;

public class CalculatorDemo {
    public static void main(String[] args) {
        // addition
        BiFunction<Double, Double, Double> add = (a, b) -> a + b;
        // subtraction
        BiFunction<Double, Double, Double> subtract = (a, b) -> a - b;
        // multiplication
        BiFunction<Double, Double, Double> multiply = (a, b) -> a * b;
        // division
        BiFunction<Double, Double, Double> divide = (a, b) -> a / b;    

        double x = 12.0, y = 3.0;
        System.out.println(x + " + " + y + " = " + add.apply(x, y));
        System.out.println(x + " - " + y + " = " + subtract.apply(x, y));
        System.out.println(x + " * " + y + " = " + multiply.apply(x, y));
        System.out.println(x + " / " + y + " = " + divide.apply(x, y));
    }
}

```
