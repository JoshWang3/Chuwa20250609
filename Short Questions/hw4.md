## HW 4

#### 1.1 Singleton pattern (both lazy loading and eager loading)
```java 
// Example 1: Lazy Loading Singleton
// Instance is created only when first accessed
class LazySingleton {
    private static LazySingleton instance;
    
    // Private constructor prevents instantiation from outside
    private LazySingleton() {
        System.out.println("LazySingleton instance created!");
    }
    
    // Thread-safe lazy initialization
    // Only one thread can execute this method at a time
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton(); //created when method first called 
        }
        return instance;
    }
    
    public void doSomething() {
        System.out.println("LazySingleton is doing something...");
    }
}

// Example 2: Eager Loading Singleton
// Instance is created at class loading time
class EagerSingleton {
    // Instance created immediately when class is loaded
    private static final EagerSingleton instance = new EagerSingleton();
    
    // Private constructor prevents instantiation from outside
    private EagerSingleton() {
        System.out.println("EagerSingleton instance created!");
    }
    
    public static EagerSingleton getInstance() {
        return instance;
    }
    
    public void doSomething() {
        System.out.println("EagerSingleton is doing something...");
    }
}

// Demo class to test both patterns
public class SingletonDemo {
    public static void main(String[] args) {
        System.out.println("=== Lazy Loading Demo ===");
        System.out.println("Before calling getInstance()");
        
        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();
        
        System.out.println("Same instance? " + (lazy1 == lazy2));
        lazy1.doSomething();
        
        System.out.println("\n=== Eager Loading Demo ===");
        System.out.println("Before calling getInstance()");
        
        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();
        
        System.out.println("Same instance? " + (eager1 == eager2));
        eager1.doSomething();
    }
}
```
**Expected Output:**
=== Lazy Loading Demo ===
Before calling getInstance()
LazySingleton instance created!
Same instance? true
LazySingleton is doing something...

=== Eager Loading Demo ===
EagerSingleton instance created!
Before calling getInstance()
Same instance? true
EagerSingleton is doing something...

#### 1.2 Factory method pattern
Factory Method is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.
- Creates one type of product at a time
- Focuses on how to create objects
- Delegates object creation to subclasses
- Solves the problem of creating objects without specifying their exact class
```java 
// Product interface
interface Vehicle {
    void start();
    void stop();
}

// Concrete products
class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car engine started with key");
    }
    
    @Override
    public void stop() {
        System.out.println("Car engine stopped");
    }
}

class Motorcycle implements Vehicle {
    @Override
    public void start() {
        System.out.println("Motorcycle engine started with kick");
    }
    
    @Override
    public void stop() {
        System.out.println("Motorcycle engine stopped");
    }
}



// Creator abstract class
abstract class VehicleFactory {
    // Factory method - subclasses will implement this
    public abstract Vehicle createVehicle();
    
    // Template method that uses the factory method
    public void deliverVehicle() {
        Vehicle vehicle = createVehicle();
        System.out.println("Preparing vehicle for delivery...");
        vehicle.start();
        System.out.println("Vehicle tested and ready!");
        vehicle.stop();
        System.out.println("Vehicle delivered!\n");
    }
}

// Concrete creators
class CarFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        System.out.println("Creating a new Car");
        return new Car();
    }
}

class MotorcycleFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        System.out.println("Creating a new Motorcycle");
        return new Motorcycle();
    }
}



// Client code
public class FactoryMethodDemo {
    public static void main(String[] args) {
        // Create different factories
        VehicleFactory carFactory = new CarFactory();
        VehicleFactory motorcycleFactory = new MotorcycleFactory();
        
        // Use factories to create and deliver vehicles
        System.out.println("=== Car Factory ===");
        carFactory.deliverVehicle();
        
        System.out.println("=== Motorcycle Factory ===");
        motorcycleFactory.deliverVehicle();
        
        // You can also create vehicles directly
        System.out.println("=== Direct Vehicle Creation ===");
        Vehicle myCar = carFactory.createVehicle();
        myCar.start();
        myCar.stop();
    }
}
```

#### 1.3 Abstract factory pattern
Abstract Factory is a creational design pattern that lets you produce families of related objects without specifying their concrete classes.
- Creates families of related products
- Focuses on what objects to create together
- Provides interface for creating families of related objects
Solves the problem of creating consistent sets of related objects
```java
// Abstract products
interface Button {
    void render();
    void click();
}

interface Checkbox {
    void render();
    void check();
}

// Windows concrete products
class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Windows button with blue border");
    }
    
    @Override
    public void click() {
        System.out.println("Windows button clicked");
    }
}

class WindowsCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering Windows checkbox with square design");
    }
    
    @Override
    public void check() {
        System.out.println("Windows checkbox checked");
    }
}

// Mac concrete products
class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Mac button with rounded corners");
    }
    
    @Override
    public void click() {
        System.out.println("Mac button clicked with animation");
    }
}

class MacCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering Mac checkbox with circular design");
    }
    
    @Override
    public void check() {
        System.out.println("Mac checkbox checked with smooth transition");
    }
}

// Abstract Factory
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete Factories
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

// Client class
class Application {
    private Button button;
    private Checkbox checkbox;
    
    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }
    
    public void createUI() {
        System.out.println("Creating UI:");
        button.render();
        checkbox.render();
    }
    
    public void useUI() {
        System.out.println("Using UI:");
        button.click();
        checkbox.check();
        System.out.println();
    }
}

// Demo
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        System.out.println("=== Abstract Factory Pattern Demo ===\n");
        
        // Windows application
        System.out.println("--- Windows App ---");
        GUIFactory windowsFactory = new WindowsFactory();
        Application windowsApp = new Application(windowsFactory);
        windowsApp.createUI();
        windowsApp.useUI();
        
        // Mac application
        System.out.println("--- Mac App ---");
        GUIFactory macFactory = new MacFactory();
        Application macApp = new Application(macFactory);
        macApp.createUI();
        macApp.useUI();
    }
}
```

#### 1.4 Builder pattern
Builder is a creational design pattern that lets you construct complex objects step by step. The pattern allows you to produce different types and representations of an object using the same construction code.
```java
// Person class using Builder pattern
public class Person {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String email;
    private final String phone;
    
    // Private constructor - only Builder can create Person objects
    private Person(PersonBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.email = builder.email;
        this.phone = builder.phone;
    }
    
    // Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    
    @Override
    public String toString() {
        return String.format("Person{firstName='%s', lastName='%s', age=%d, email='%s', phone='%s'}",
                firstName, lastName, age, email, phone);
    }
    
    // Static nested Builder class
    public static class PersonBuilder {
        private String firstName;
        private String lastName;
        private int age;
        private String email;
        private String phone;
        
        // Required fields in constructor
        public PersonBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
        
        // Optional field methods - return this for method chaining
        public PersonBuilder age(int age) {
            this.age = age;
            return this;
        }
        
        public PersonBuilder email(String email) {
            this.email = email;
            return this;
        }
        
        public PersonBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }
        
        // Build method creates the final object
        public Person build() {
            return new Person(this);
        }
    }
}

// Demo class showing how to use the Builder
class BuilderDemo {
    public static void main(String[] args) {
        // Create person with only required fields
        Person person1 = new Person.PersonBuilder("John", "Doe")
                .build();
        
        // Create person with some optional fields
        Person person2 = new Person.PersonBuilder("Jane", "Smith")
                .age(30)
                .email("jane.smith@email.com")
                .build();
        
        // Create person with all fields
        Person person3 = new Person.PersonBuilder("Bob", "Johnson")
                .age(25)
                .email("bob.johnson@email.com")
                .phone("555-1234")
                .build();
        
        // Print results
        System.out.println(person1);
        System.out.println(person2);
        System.out.println(person3);
    }
}
```



#### 2. Write code to explain how do default and static keywords work in interfaces since Java 8
```java
// Interface demonstrating default and static methods
interface Vehicle {
    // Abstract method (must be implemented by classes)
    void start();
    
    // Default method - provides default implementation
    default void honk() {
        System.out.println("Beep beep! Generic vehicle sound");
    }
    
    // Default method with logic
    default void displayInfo() {
        System.out.println("This is a vehicle. Type: " + getType());
        System.out.println("Max speed: " + getMaxSpeed() + " km/h");
    }
    
    // Static method - belongs to interface, not instances
    static void showVehicleRules() {
        System.out.println("=== VEHICLE RULES ===");
        System.out.println("1. Always wear seatbelt");
        System.out.println("2. Follow speed limits");
        System.out.println("3. Regular maintenance required");
    }
    
    // Static utility method
    static String formatSpeed(int speed) {
        return speed + " km/h";
    }
    
    // Abstract methods for default method to use
    String getType();
    int getMaxSpeed();
}

// Class implementing the interface
class Car implements Vehicle {
    private String model;
    
    public Car(String model) {
        this.model = model;
    }
    
    @Override
    public void start() {
        System.out.println(model + " car engine started!");
    }
    
    // Override default method to provide specific implementation
    @Override
    public void honk() {
        System.out.println("Car horn: HONK HONK!");
    }
    
    @Override
    public String getType() {
        return "Car (" + model + ")";
    }
    
    @Override
    public int getMaxSpeed() {
        return 180;
    }
}

// Another class that uses default implementation
class Bicycle implements Vehicle {
    @Override
    public void start() {
        System.out.println("Bicycle: Ready to pedal!");
    }
    
    // Uses default honk() method
    // Uses default displayInfo() method
    
    @Override
    public String getType() {
        return "Bicycle";
    }
    
    @Override
    public int getMaxSpeed() {
        return 25;
    }
}

// Demonstration class
public class InterfaceDemo {
    public static void main(String[] args) {
        System.out.println("=== JAVA INTERFACE DEFAULT & STATIC METHODS DEMO ===\n");
        
        // Create instances
        Car car = new Car("Toyota");
        Bicycle bike = new Bicycle();
        
        // 1. Static methods - called on interface directly
        System.out.println("1. STATIC METHODS (called on interface):");
        Vehicle.showVehicleRules();
        System.out.println("Formatted speed: " + Vehicle.formatSpeed(120));
        System.out.println();
        
        // 2. Default methods - inherited by implementing classes
        System.out.println("2. CAR BEHAVIOR:");
        car.start();           // Implemented method
        car.honk();            // Overridden default method
        car.displayInfo();     // Uses default implementation
        System.out.println();
        
        System.out.println("3. BICYCLE BEHAVIOR:");
        bike.start();          // Implemented method
        bike.honk();           // Uses default implementation
        bike.displayInfo();    // Uses default implementation
        System.out.println();
        
        // 4. Demonstrate that static methods cannot be called on instances
        System.out.println("4. STATIC METHOD ACCESS:");
        System.out.println("✓ Vehicle.showVehicleRules() - Works");
        System.out.println("✗ car.showVehicleRules() - Would cause compilation error");
        System.out.println("Static methods belong to the interface, not instances");
        System.out.println();
        
        // 5. Show polymorphism with default methods
        System.out.println("5. POLYMORPHISM WITH DEFAULT METHODS:");
        Vehicle[] vehicles = {car, bike};
        for (Vehicle v : vehicles) {
            System.out.println("Vehicle type: " + v.getType());
            v.honk(); // Calls appropriate version (overridden or default)
            System.out.println("---");
        }
    }
}
```

#### 3. Write code to demo Java anonymous class, you may write your own POJOs (e.g. parent abstract classes,  child classes, and interfaces)
- A Java anonymous class is a local class without a name that you declare and instantiate in a single expression. It's essentially a way to create a one-time-use class that extends another class or implements an interface without having to formally declare it.
```java
public class AnonymousClassDemo {
    
    // Interface for demonstration
    interface Greeting {
        void sayHello(String name);
        void sayGoodbye(String name);
    }
    
    // Abstract class for demonstration
    abstract class Animal {
        String name;
        
        Animal(String name) {
            this.name = name;
        }
        
        abstract void makeSound();
        
        void sleep() {
            System.out.println(name + " is sleeping...");
        }
    }
    
    public static void main(String[] args) {
        AnonymousClassDemo demo = new AnonymousClassDemo();
        
        System.out.println("=== Anonymous Class Examples ===\n");
        
        // 1. Anonymous class implementing an interface
        System.out.println("1. Implementing Interface:");
        Greeting greeting = new Greeting() {
            @Override
            public void sayHello(String name) {
                System.out.println("Hello, " + name + "! Nice to meet you.");
            }
            
            @Override
            public void sayGoodbye(String name) {
                System.out.println("Goodbye, " + name + "! See you later.");
            }
        };
        
        greeting.sayHello("Alice");
        greeting.sayGoodbye("Alice");
        System.out.println();
        
        // 2. Anonymous class extending an abstract class
        System.out.println("2. Extending Abstract Class:");
        Animal dog = demo.new Animal("Buddy") {
            @Override
            void makeSound() {
                System.out.println(name + " says: Woof! Woof!");
            }
        };
        
        dog.makeSound();
        dog.sleep();
        System.out.println();
        
        // 3. Anonymous class with Runnable (common use case)
        System.out.println("3. Runnable Implementation:");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 3; i++) {
                    System.out.println("Thread is running... " + i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        
        thread.start();
        
        // Wait for thread to complete
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println();
        
    }
}
```

#### 4. Write code to explain Lambda expression with your own functional interface.
- A **functional interface** is an interface that contains **exactly one(1) abstract method**. It can have multiple default or static methods, but only one abstract method. This single abstract method represents the contract for the functional behavior.
- A **lambda expression** is a concise way to represent anonymous functions. It provides a clear and compact way to implement functional interfaces without creating separate classes. 
- The combination of functional interfaces and lambda expressions forms the foundation for many Java 8+ features like the Stream API, making Java code more expressive and maintainable.
```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

// Without lambda (traditional approach)
Calculator add = new Calculator() {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
};

// With lambda expression
Calculator add = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;
Calculator subtract = (a, b) -> {
    System.out.println("Subtracting " + b + " from " + a);
    return a - b;
};
```

#### 5. Write a calculator with BiFunction<T,U,R> (an internal functional interface provided by JDK) and Lambda  expression. Your calculator should support two-number addition, subtraction, multiplication, division operations.
- (see Coding folder)

