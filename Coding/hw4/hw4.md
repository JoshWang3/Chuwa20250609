# Java Design Patterns and Modern Features Demo

### Design Patterns (Separate Java Files)
- `EagerSingleton.java` - Eager loading singleton pattern
- `LazySingleton.java` - Lazy loading singleton pattern  
- `FactoryMethodDemo.java` - Factory method pattern
- `AbstractFactoryDemo.java` - Abstract factory pattern
- `BuilderPatternDemo.java` - Builder pattern
- `AllPatternsDemo.java` - Comprehensive demonstration of all patterns

### How to Run
```bash
# Compile all Java files
javac *.java

# Run individual pattern demonstrations
java EagerSingleton
java LazySingleton
java FactoryMethodDemo
java AbstractFactoryDemo
java BuilderPatternDemo

# Run comprehensive demonstration
java AllPatternsDemo
```

## 1. Singleton Pattern

### Eager Loading Singleton

```java
public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    
    private EagerSingleton() {}
    
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
    
    public void performTask() {
        System.out.println("Eager Singleton performing task");
    }
}
```

### Lazy Loading Singleton (Thread-Safe)

```java
public class LazySingleton {
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
    
    public void performTask() {
        System.out.println("Lazy Singleton performing task");
    }
}
```

### Thread Safety Explanation

**Thread-Safe Guarantee Methods:**
1. **Eager Loading**: Thread-safe by default since static final field is initialized at class loading
2. **Lazy Loading with Double-Checked Locking**: 
   - Uses `volatile` keyword to prevent instruction reordering
   - Double-checked locking pattern minimizes synchronization overhead
   - First check avoids synchronization after initialization
   - Second check inside synchronized block ensures only one thread creates instance

## 2. Factory Method Pattern

```java
interface Vehicle {
    void start();
    void stop();
}

class Car implements Vehicle {
    public void start() { System.out.println("Car engine started"); }
    public void stop() { System.out.println("Car engine stopped"); }
}

class Motorcycle implements Vehicle {
    public void start() { System.out.println("Motorcycle engine started"); }
    public void stop() { System.out.println("Motorcycle engine stopped"); }
}

abstract class VehicleFactory {
    public abstract Vehicle createVehicle();
    
    public void operateVehicle() {
        Vehicle vehicle = createVehicle();
        vehicle.start();
        vehicle.stop();
    }
}

class CarFactory extends VehicleFactory {
    public Vehicle createVehicle() { return new Car(); }
}

class MotorcycleFactory extends VehicleFactory {
    public Vehicle createVehicle() { return new Motorcycle(); }
}
```

## 3. Abstract Factory Pattern

```java
interface Engine { void start(); }
interface Wheel { void rotate(); }

class CarEngine implements Engine {
    public void start() { System.out.println("Car engine starting"); }
}

class CarWheel implements Wheel {
    public void rotate() { System.out.println("Car wheel rotating"); }    
}

class TruckEngine implements Engine {
    public void start() { System.out.println("Truck engine starting"); }
}

class TruckWheel implements Wheel {
    public void rotate() { System.out.println("Truck wheel rotating"); }
}

interface VehicleComponentFactory {
    Engine createEngine();
    Wheel createWheel();
}

class CarComponentFactory implements VehicleComponentFactory {
    public Engine createEngine() { return new CarEngine(); }
    public Wheel createWheel() { return new CarWheel(); }
}

class TruckComponentFactory implements VehicleComponentFactory {
    public Engine createEngine() { return new TruckEngine(); }
    public Wheel createWheel() { return new TruckWheel(); }
}

class VehicleAssembler {
    private Engine engine;
    private Wheel wheel;
    
    public VehicleAssembler(VehicleComponentFactory factory) {
        this.engine = factory.createEngine();
        this.wheel = factory.createWheel();
    }
    
    public void assembleVehicle() {
        engine.start();
        wheel.rotate();
    }
}
```

## 4. Builder Pattern

```java
class Computer {
    private String processor;
    private String memory;
    private String storage;
    
    private Computer(ComputerBuilder builder) {
        this.processor = builder.processor;
        this.memory = builder.memory;
        this.storage = builder.storage;
    }
    
    public String toString() {
        return "Computer{processor='" + processor + "', memory='" + memory + 
               "', storage='" + storage + "'}";
    }
    
    public static class ComputerBuilder {
        private String processor;
        private String memory;
        private String storage;
        
        public ComputerBuilder setProcessor(String processor) {
            this.processor = processor;
            return this;
        }
        
        public ComputerBuilder setMemory(String memory) {
            this.memory = memory;
            return this;
        }
        
        public ComputerBuilder setStorage(String storage) {
            this.storage = storage;
            return this;
        }
        
        public Computer build() {
            return new Computer(this);
        }
    }
}
```

## 5. Default and Static Keywords in Interfaces (Java 8+)

```java
interface MathOperations {
    double calculate(double a, double b);
    
    default void printResult(double result) {
        System.out.println("Result: " + result);
    }
    
    static boolean isPositive(double number) {
        return number > 0;
    }
}

class Addition implements MathOperations {
    public double calculate(double a, double b) {
        return a + b;
    }
    
    public void printResult(double result) {
        System.out.println("Addition result: " + result);
    }
}

class Multiplication implements MathOperations {
    public double calculate(double a, double b) {
        return a * b;
    }
}
```

## 6. Java Anonymous Classes

```java
abstract class Animal {
    protected String name;
    
    public Animal(String name) { this.name = name; }
    public abstract void makeSound();
    public void sleep() { System.out.println(name + " is sleeping"); }
}

interface Flyable {
    void fly();
    void land();
}

class AnonymousClassDemo {
    public static void main(String[] args) {
        // Anonymous class extending abstract class
        Animal mysteriousAnimal = new Animal("Mystery Beast") {
            public void makeSound() {
                System.out.println(name + " makes mysterious sounds");
            }
        };
        
        // Anonymous class implementing interface
        Flyable drone = new Flyable() {
            public void fly() { System.out.println("Drone is hovering"); }
            public void land() { System.out.println("Drone is landing"); }
        };
        
        mysteriousAnimal.makeSound();
        drone.fly();
        drone.land();
    }
}
```

## 7. Lambda Expressions with Custom Functional Interface

```java
@FunctionalInterface
interface MathOperation {
    double operate(double a, double b);
    
    default void displayResult(String op, double a, double b, double result) {
        System.out.println(op + ": " + a + " and " + b + " = " + result);
    }
}

@FunctionalInterface
interface NumberValidator {
    boolean validate(double number);
}

class LambdaDemo {
    public static void main(String[] args) {
        MathOperation addition = (a, b) -> a + b;
        MathOperation division = (a, b) -> b == 0 ? 0 : a / b;
        
        NumberValidator isPositive = number -> number > 0;
        NumberValidator isEven = number -> number % 2 == 0;
        
        double x = 15, y = 3;
        double result = addition.operate(x, y);
        
        addition.displayResult("Addition", x, y, result);
        System.out.println(x + " is positive: " + isPositive.validate(x));
        System.out.println(y + " is even: " + isEven.validate(y));
    }
}
```

## 8. Calculator with BiFunction and Lambda Expressions

```java
import java.util.function.BiFunction;
import java.util.HashMap;
import java.util.Map;

class LambdaCalculator {
    private Map<String, BiFunction<Double, Double, Double>> operations;
    
    public LambdaCalculator() {
        operations = new HashMap<>();
        operations.put("+", (a, b) -> a + b);
        operations.put("-", (a, b) -> a - b);
        operations.put("*", (a, b) -> a * b);
        operations.put("/", (a, b) -> b == 0 ? 0 : a / b);
    }
    
    public double calculate(String operator, double num1, double num2) {
        BiFunction<Double, Double, Double> operation = operations.get(operator);
        return operation != null ? operation.apply(num1, num2) : 0;
    }
    
    public static void main(String[] args) {
        LambdaCalculator calculator = new LambdaCalculator();
        
        double a = 12.5, b = 4.2;
        
        System.out.println("Addition: " + calculator.calculate("+", a, b));
        System.out.println("Subtraction: " + calculator.calculate("-", a, b));
        System.out.println("Multiplication: " + calculator.calculate("*", a, b));
        System.out.println("Division: " + calculator.calculate("/", a, b));
    }
}
```

## Usage Examples

```java
public class PatternDemo {
    public static void main(String[] args) {
        // Singleton Pattern
        EagerSingleton.getInstance().performTask();
        LazySingleton.getInstance().performTask();
        
        // Factory Method
        new CarFactory().operateVehicle();
        new MotorcycleFactory().operateVehicle();
        
        // Abstract Factory
        new VehicleAssembler(new CarComponentFactory()).assembleVehicle();
        
        // Builder Pattern
        Computer pc = new Computer.ComputerBuilder()
                .setProcessor("Intel i9")
                .setMemory("32GB")
                .setStorage("1TB SSD")
                .build();
        System.out.println(pc);
        
        // Lambda Calculator
        new LambdaCalculator().main(new String[]{});
    }
}
```

## Key Points

- **Singleton**: One instance per JVM, thread-safe implementation
- **Factory Method**: Delegate object creation to subclasses
- **Abstract Factory**: Create families of related objects
- **Builder**: Construct complex objects step by step
- **Interface**: Default and static methods (Java 8+)
- **Anonymous Classes**: Inline implementations
- **Lambda**: Functional programming with concise syntax
- **BiFunction**: Two-parameter operations with lambdas
