
## Question 1
1. singleton pattern
```java
// eager loading: static final initialized only once so naturally thread-safe
public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();
 
    private EagerSingleton() {
    }
    
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
```
```java
// lazy loading: synchronized keyword ensures thread-safety
public class SynchronizedLazySingleton {
    private static SynchronizedLazySingleton instance;
    
    private SynchronizedLazySingleton() {}
    
    public static synchronized SynchronizedLazySingleton getInstance() {
        if (instance == null) {
            instance = new SynchronizedLazySingleton();
        }
        return instance;
    }
}
```
2. factory method pattern
```java
interface Vehicle {
    void start();
    void stop();
}

class Car implements Vehicle {
    @Override
    public void start() { System.out.println("Car started"); }
    
    @Override
    public void stop() { System.out.println("Car stopped"); }
}
```
3. Abstract factory pattern
```java
interface Vehicle {
    void start();
    void stop();
}

interface Engine {
    void ignite();
}

// Luxury vehicle family
class Car implements Vehicle {
    @Override
    public void start() { System.out.println("car started"); }
    
    @Override
    public void stop() { System.out.println("car stopped"); }
}

class CarEngine implements Engine {
    @Override
    public void ignite() { System.out.println("engine ignited"); }
}

interface VehicleFactory {
    Vehicle createCar();
    Engine createEngine();
}

class CarFactory implements VehicleFactory {
    @Override
    public Vehicle createCar() {
        return new Car();
    }
   
    @Override
    public Engine createEngine() {
        return new CarEngine();
    }
}

// Client 
public class AbstractFactoryDemo {
    private VehicleFactory factory;
    
    public AbstractFactoryDemo(VehicleFactory factory) {
        this.factory = factory;
    }
    
    public void createVehicles() {
        Vehicle car = factory.createCar();
        Engine engine = factory.createEngine();
        
        car.start();
        engine.ignite();
        car.stop();
    }
    
    public static void main(String[] args) {
        AbstractFactoryDemo carDemo = new AbstractFactoryDemo(new carFactory());
        carDemo.createVehicles();
    }
}
```
4. Builder pattern
```java
class Vehicle {
    private final String type;
    private final String engine;
    
    private Vehicle(Builder builder) {
        this.type = builder.type;
        this.engine = builder.engine;
    }
    
    public static class Builder {
        private final String type;
        private String engine = "Standard";
        
        public Builder(String type) { this.type = type; }
        
        public Builder engine(String engine) {
            this.engine = engine;
            return this;
        }
        
        public Vehicle build() { return new Vehicle(this); }
    }
    
    public void start() {
        System.out.println(type + " with " + engine + " started"); //Bike with Standard start
    }
}

public class Demo {
    public static void main(String[] args) {
        Vehicle bike = new Vehicle.Builder("Bike").build();
        bike.start(); 
    }
}
```


## Question 2

```java
interface Calculator {
    // Abstract 
    int add(int a, int b);
    
    // Default (concrete)
    default int multiply(int a, int b) {
        return a * b;
    }
    
    // Static (belong to class)
    static int max(int a, int b) {
        return a > b ? a : b;
    }
    
}

class BasicCalculator implements Calculator {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}

class Usage {
    public static void main(String[] args) {
        Calculator calc = new BasicCalculator();
        System.out.println(calc.add(2, 3));        // 5 
        System.out.println(calc.multiply(2, 3));   // 6 
        System.out.println(Calculator.max(5, 3));  // 5 
    }
}
``` 

## Question 3
```java
abstract class Calculator {
    public abstract double compute(double x, double y);
    
    public void printResult(double result) {
        System.out.println("Result: " + result);
    }
}

public class AnonymousClassDemo {
    public static void main(String[] args) {

        Calculator powerCalc = new Calculator() {
            @Override
            public double compute(double x, double y) {
                return Math.pow(x, y);
            }
        };
        double power = powerCalc.compute(2,  3); 			 
        powerCalc.printResult(power); // 6.0
    }
}
```


## Question 4
```java
@FunctionalInterface
interface Calculator {
    double calculate(double a, double b);
    
    default void printResult(double a, double b) {
        System.out.println("Result: " + calculate(a, b));
    }
}

public class LambdaCalculatorDemo {
    public static void main(String[] args) {
        double x = 1, y = 2;
        Calculator addition = (a, b) -> a + b;
        addition.printResult(x, y);  // Result: 3.0
    }
}
```

## Question 5

```java
import java.util.function.BiFunction;

public class Calculator {
    public static void main(String[] args) {
        double a = 20, b = 4;
        
        BiFunction<Double, Double, Double> addition= (x, y) -> x + y;
        BiFunction<Double, Double, Double> subtraction= (x, y) -> x - y; 
        BiFunction<Double, Double, Double> multiplication= (x, y) -> x * y;
        BiFunction<Double, Double, Double> division= (x, y) -> x / y;
        
        System.out.println("addition: " + addition.apply(a, b));  // 24.0
        System.out.println("subtraction: " + subtraction.apply(a, b));  // 16.0
        System.out.println("multiplication: " + multiplication.apply(a, b));  // 80.0
        System.out.println("division: " + division.apply(a, b));  // 5.0
    }
}
```