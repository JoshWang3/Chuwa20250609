import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * This class serves as a comprehensive demonstration of various
 * Java design patterns and language features.
 */
public class JavaDemos {

    public static void main(String[] args) {
        System.out.println("--- Singleton ---");
        singletonDemo();

        System.out.println("\n--- Factory Method ---");
        factoryMethodDemo();

        System.out.println("\n--- Abstract Factory ---");
        abstractFactoryDemo();

        System.out.println("\n--- Builder ---");
        builderDemo();

        System.out.println("\n--- Java 8 Interfaces ---");
        java8InterfaceDemo();

        System.out.println("\n--- Anonymous Class ---");
        anonymousClassDemo();

        System.out.println("\n--- Lambda Expression ---");
        lambdaDemo();

        System.out.println("\n--- BiFunction Calculator ---");
        calculatorDemo();
    }

    // --- Demo Methods ---

    public static void singletonDemo() {
        System.out.println("A) Eager Loading Singleton:");
        EagerSingleton eager = EagerSingleton.getInstance();

        System.out.println("\nB) Lazy Loading Singleton:");
        LazySingleton lazy = LazySingleton.getInstance();
    }

    public static void factoryMethodDemo() {
        VehicleFactory carFactory = new CarFactory();
        carFactory.createVehicle().drive(); // Outputs: Driving a car...

        VehicleFactory bikeFactory = new BikeFactory();
        bikeFactory.createVehicle().drive(); // Outputs: Riding a bike...
    }

    public static void abstractFactoryDemo() {
        GUIFactory windowsFactory = new WindowsFactory();
        Application winApp = new Application(windowsFactory);
        winApp.paint();

        GUIFactory macFactory = new MacFactory();
        Application macApp = new Application(macFactory);
        macApp.paint();
    }

    public static void builderDemo() {
        Computer basicComputer = new Computer.ComputerBuilder("500GB HDD", "8GB RAM")
                .build();
        System.out.println("Basic Computer: " + basicComputer);

        Computer gamingComputer = new Computer.ComputerBuilder("1TB SSD", "32GB RAM")
                .setGraphicsCardEnabled(true)
                .setBluetoothEnabled(true)
                .build();
        System.out.println("Gaming Computer: " + gamingComputer);
    }

    public static void java8InterfaceDemo() {
        MyInterface.staticMethod();
        new MyClass().defaultMethod();
    }

    public static void anonymousClassDemo() {
        Greeter englishGreeter = new Greeter("Hello") {
            @Override
            public void greet() {
                System.out.println(this.greetingMessage + " from Anonymous Greeter!");
            }
        };
        englishGreeter.greet();

        Action action = new Action() {
            @Override
            public void execute() {
                System.out.println("Anonymous Action executing.");
            }
        };
        action.execute();
    }

    public static void lambdaDemo() {
        StringOperator toUpper = (s) -> s.toUpperCase();
        StringOperator reverse = (s) -> new StringBuilder(s).reverse().toString();

        String text = "Hello Lambda";
        System.out.println("Original: " + text);
        System.out.println("Uppercase: " + toUpper.operate(text));
        System.out.println("Reversed: " + reverse.operate(text));
    }

    public static void calculatorDemo() {
        Calculator calculator = new Calculator();
        double a = 20;
        double b = 5;

        System.out.printf("%.1f + %.1f = %.1f\n", a, b, calculator.calculate(a, b, "+"));
        System.out.printf("%.1f - %.1f = %.1f\n", a, b, calculator.calculate(a, b, "-"));
        System.out.printf("%.1f * %.1f = %.1f\n", a, b, calculator.calculate(a, b, "*"));
        System.out.printf("%.1f / %.1f = %.1f\n", a, b, calculator.calculate(a, b, "/"));

        try {
            calculator.calculate(a, 0.0, "/");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }
}


// ===================================================================================
// 1. SINGLETON PATTERN
// ===================================================================================

class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();

    // Private constructor to prevent instantiation from other classes.
    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return instance;
    }
}

class LazySingleton {
    private static volatile LazySingleton instance = null;

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


// ===================================================================================
// 2. FACTORY METHOD PATTERN
// ===================================================================================

interface Vehicle {
    void drive();
}

class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a car...");
    }
}

class Bike implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Riding a bike...");
    }
}

abstract class VehicleFactory {
    public abstract Vehicle createVehicle();
}

class CarFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Car();
    }
}

class BikeFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Bike();
    }
}

// ===================================================================================
// 3. ABSTRACT FACTORY PATTERN
// ===================================================================================

interface Button {
    void paint();
}

interface Checkbox {
    void paint();
}

class WindowsButton implements Button {
    @Override
    public void paint() {
        System.out.println("Painting a Windows-style button.");
    }
}

class WindowsCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Painting a Windows-style checkbox.");
    }
}

class MacButton implements Button {
    @Override
    public void paint() {
        System.out.println("Painting a MacOS-style button.");
    }
}

class MacCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Painting a MacOS-style checkbox.");
    }
}

interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }
    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}

// ===================================================================================
// 4. BUILDER PATTERN
// ===================================================================================

class Computer {
    private String hdd;
    private String ram;
    private boolean isGraphicsCardEnabled;
    private boolean isBluetoothEnabled;

    private Computer(ComputerBuilder builder) {
        this.hdd = builder.hdd;
        this.ram = builder.ram;
        this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
    }

    @Override
    public String toString() {
        return "Computer [HDD=" + hdd + ", RAM=" + ram + ", GraphicsCard=" + isGraphicsCardEnabled + ", Bluetooth="
                + isBluetoothEnabled + "]";
    }

    public static class ComputerBuilder {
        private String hdd;
        private String ram;
        private boolean isGraphicsCardEnabled;
        private boolean isBluetoothEnabled;

        public ComputerBuilder(String hdd, String ram) {
            this.hdd = hdd;
            this.ram = ram;
        }

        public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
            this.isGraphicsCardEnabled = isGraphicsCardEnabled;
            return this;
        }

        public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

// ===================================================================================
// 5. JAVA 8 INTERFACE KEYWORDS (default/static)
// ===================================================================================

interface MyInterface {
    static void staticMethod() {
        System.out.println("Interface static method called.");
    }

    default void defaultMethod() {
        System.out.println("Interface default method called.");
    }
}

class MyClass implements MyInterface {}

// ===================================================================================
// 6. JAVA ANONYMOUS CLASS
// ===================================================================================

abstract class Greeter {
    protected String greetingMessage;
    public Greeter(String greetingMessage) {
        this.greetingMessage = greetingMessage;
    }
    public abstract void greet();
}

interface Action {
    void execute();
}

// ===================================================================================
// 7. LAMBDA EXPRESSION WITH FUNCTIONAL INTERFACE
// ===================================================================================

@FunctionalInterface
interface StringOperator {
    String operate(String s);
}


// ===================================================================================
// 8. CALCULATOR WITH BIFUNCTION
// ===================================================================================

class Calculator {
    private final Map<String, BiFunction<Double, Double, Double>> operations = new HashMap<>();

    public Calculator() {
        operations.put("+", (a, b) -> a + b);
        operations.put("-", (a, b) -> a - b);
        operations.put("*", (a, b) -> a * b);
        operations.put("/", (a, b) -> {
            if (b == 0) {
                throw new IllegalArgumentException("Cannot divide by zero.");
            }
            return a / b;
        });
    }

    public double calculate(double a, double b, String operator) {
        BiFunction<Double, Double, Double> operation = operations.get(operator);
        if (operation == null) {
            throw new IllegalArgumentException("Invalid operator: " + operator);
        }
        return operation.apply(a, b);
    }
}