# HW4 - Java, OOP and Java 8

---

## 1. **Write code to demo**

### 1.1 singleton pattern (both lazy loading and eager loading)
Imagine there is a ConfigurationManager that loads configuration from a file or database and is shared across the application.
- **Eager Loading Singleton**: `EagerConfigManager`
- **Lazy Loading Singleton (Thread-safe)**: `LazyConfigManager` with **Double-Checked Locking**

We also include:
- A plain Java object (POJO) `AppConfig` to represent application configuration.
- A `SingletonTest` class to show how the singletons are used in practice.

#### POJO - AppConfig
```java
public class AppConfig {

    private String appName;
    private String dbUrl;

    public AppConfig(String appName, String dbUrl) {
        this.appName = appName;
        this.dbUrl = dbUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    @Override
    public String toString() {
        return "AppConfig{" + "appName=" + appName + ", dbUrl=" + dbUrl + '}';
    }

}


```

#### Eager Loading Singleton - EagerConfigManager
```java
package hw4.singleton;

/**
 * Eager Loading Singleton: EagerConfigManager
 */
public class EagerConfigManager {

    private static final EagerConfigManager INSTANCE = new EagerConfigManager();
    private final AppConfig appConfig;

    private EagerConfigManager() {
        System.out.println("Loading AppConfig eagerly...");
        this.appConfig = new AppConfig("todoAppEager", "dbc:mysql://localhost:3306/todoAppEager" );
    }

    public static EagerConfigManager getInstance() {
        return INSTANCE;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }
    
}

```
#### Lazy Loading Singleton - LazyConfigManager

```java
package hw4.singleton;

/**
 * Lazy Loading Singleton: LazyConfigManager (Thread safe)
 */
public class LazyConfigManager {
    private final AppConfig appConfig;

    private LazyConfigManager() {
        System.out.println("Loading AppConfig lazily...");
        this.appConfig = new AppConfig("todoAppLazily", "jdbc:mysql://localhost:3306/todoAppLazily" );
    }

    // Static inner class to hold the Singleton instance
    private static class LazyConfigManagerHolder {
        private static final LazyConfigManager INSTANCE = new LazyConfigManager();
    }

    public static LazyConfigManager getInstance() {
        return LazyConfigManagerHolder.INSTANCE;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }
}

```

#### Demo - SingletonTest
```java
package hw4.singleton;

public class SingletonTest {
    public static void main(String[] args) {
        System.out.println("== Eager Config ==");
        AppConfig eagerConfig = EagerConfigManager.getInstance().getAppConfig();;
        System.out.println(eagerConfig);

        System.out.println("== Lazy Config ==");
        AppConfig lazyConfig = LazyConfigManager.getInstance().getAppConfig();
        System.out.println(lazyConfig);
    }
}

```
---

### Thread Safety Explanation:
  * **Eager Singleton (`EagerConfigManager`)**
    * Instance created during class loading (JVM guarantees thread safety for class initialization).
    * No synchronization needed — inherently thread-safe.
  * **Lazy Singleton (`LazyConfigManager`)**
    * The inner static class (`SingletonHolder`) is only loaded once by the JVM when getInstance() is first called.
    * Class loading in Java is thread-safe, and the JVM ensures that INSTANCE is initialized exactly once.
    * There’s no need for synchronization or `volatile`, making it both safe and efficient.
    
---

### 1.2 factory method pattern

- Suppose we have a notification system — e.g., sending messages via Email, SMS, or Push. 
- We want a flexible system that can send notifications through different channels. 
- Instead of the client instantiating concrete classes like EmailNotification or SMSNotification, they will ask a factory to create the appropriate notification sender.

#### Step1. POJO - Message
```java
package hw4.factorymethod;

/**
 * Example POJO
 */

public class Message {
    private String content;
    private String recipient;

    public Message(String content, String recipient) {
        this.content = content;
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", recipient='" + recipient + '\'' +
                '}';
    }
}

```

#### Step2. Notification Interface (Product)
```java
package hw4.factorymethod;

public interface Notification {
    void send(String message);
}

```

#### Step3. Concrete Notification Types (Concrete Products)
```java
package hw4.factorymethod;

public class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}

```

```java
package hw4.factorymethod;

public class PushNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending Push Notification: " + message);
    }
}

```

```java
package hw4.factorymethod;

public class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

```

#### Step5. NotificationFactory Interface (Creator)
```java
package hw4.factorymethod;

public interface NotificationFactory {
    Notification createNotification();
}

```

#### Step6. Concrete Factories
```java
package hw4.factorymethod;

public class EmailNotificationFactory implements NotificationFactory {
    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}

```


```java
package hw4.factorymethod;

public class PushNotificationFactory implements NotificationFactory {
    @Override
    public Notification createNotification() {
        return new PushNotification();
    }
}

```

```java
package hw4.factorymethod;

public class SMSNotificationFactory implements NotificationFactory {
    @Override
    public Notification createNotification() {
        return new SMSNotification();
    }
}

```

#### Step7. Client Code (Usage)
```java
package hw4.factorymethod;

public class NotificationService {
    public static void main(String[] args) {

        NotificationFactory notificationFactory = new EmailNotificationFactory();

        // use the factory to create a notification sender
        Notification notification = notificationFactory.createNotification();

        // create a message
        Message msg = new Message("Your order has been shipped!", "user@example.com");

        // send the notification
        notification.send(msg.getContent());
    }
}

// Sending email: Your order has been shipped!
```

---

### 1.3 Abstract factory pattern

####  Pizza Factory (Abstract Factory Pattern)
- We want to create different types of Pizza ingredients depending on the region:
  - Dough
  - Sauce
- Each region (e.g., New York or Chicago) uses its own ingredients.
- We'll use:
  - An Abstract Factory to produce ingredients
  - Concrete factories like NewYorkPizzaIngredientFactory and ChicagoPizzaIngredientFactory
  - A Pizza class that uses the ingredient factory

---

#### Step 1: Ingredient Interfaces (Abstract Products)

```java
package hw4.abstractfactory;

public interface Dough {
    String getName();
}

```

```java
package hw4.abstractfactory;

public interface Sauce {
    String getName();
}

```

#### Step 2: Concrete Ingredients (New York Style)

```java
package hw4.abstractfactory;

public class ThinCrustDough implements Dough{
    @Override
    public String getName() {
        return "Thin Crust Dough";
    }
}

```

```java
package hw4.abstractfactory;

public class MarinaraSauce implements Sauce {
    @Override
    public String getName() {
        return "Marinara Sauce";
    }
}

```

#### Step 3: Concrete Ingredients (Chicago Style)
```java
package hw4.abstractfactory;

public class ThickCrustDough implements Dough {
    @Override
    public String getName() {
        return "Thick Crust Dough";
    }
}

```

```java
package hw4.abstractfactory;

public class PlumTomatoSauce implements  Sauce{
    @Override
    public String getName() {
        return "Plum Tomato Sauce";
    }
}

```

#### Step 4: Abstract Factory for Ingredients

```java
package hw4.abstractfactory;

public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
}

```

#### Step 5: Concrete Factories
```java
package hw4.abstractfactory;

public class NewYorkPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }
    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }
}

```

```java
package hw4.abstractfactory;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }
    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }
}


```

#### Step 6: Pizza Class (Client)
```java
package hw4.abstractfactory;

public class Pizza {
    private final Dough dough;
    private final Sauce sauce;

    public Pizza(PizzaIngredientFactory factory) {
        this.dough = factory.createDough();
        this.sauce = factory.createSauce();
    }

    public void prepare() {
        System.out.println("Pizza with " + dough.getName() + " and " + sauce.getName() + " is preparing");
    }
}

```

#### Step 7: Main Method
```java
package hw4.abstractfactory;

public class PizzaStore {
    public static void main(String[] args) {
        PizzaIngredientFactory nyFactory = new NewYorkPizzaIngredientFactory();
        Pizza nyPizza = new Pizza(nyFactory);
        nyPizza.prepare();
        // Output: Pizza with Thin Crust Dough and Marinara Sauce is preparing

        PizzaIngredientFactory chicagoFactory = new ChicagoPizzaIngredientFactory();
        Pizza chicagoPizza = new Pizza(chicagoFactory);
        chicagoPizza.prepare();
        // Output: Pizza with Thick Crust Dough and Plum Tomato Sauce is preparing
    }
}

```

___

### 1.4 Builder pattern

- Imagine we’re assembling a Computer — we can optionally add components like:
  - CPU (required)
  - RAM (optional)
  - Storage (optional)
  - Graphics Card (optional)
- With the Builder Pattern, we can create flexible and readable object construction without writing too many constructors.

#### Step 1: Computer POJO (Product) and Builder

```java
package hw4.builderpattern;


public class Computer {

    // Required
    private final String cpu;

    // Optional
    private final int ramGB;
    private final int storageGB;
    private final boolean hasGraphicsCard;

    // Private constructor
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ramGB = builder.ramGB;
        this.storageGB = builder.storageGB;
        this.hasGraphicsCard = builder.hasGraphicsCard;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ramGB=" + ramGB +
                ", storageGB=" + storageGB +
                ", hasGraphicsCard=" + hasGraphicsCard +
                '}';
    }

    // Builder inner class
    public static class Builder {
        private final String cpu; // required

        private int ramGB = 8;           // default
        private int storageGB = 256;     // default
        private boolean hasGraphicsCard = false;

        public Builder(String cpu) {
            this.cpu = cpu;
        }

       public Builder ramGB(int ramGB) {
            this.ramGB = ramGB;
            return this;
       }
       public Builder storageGB(int storageGB) {
            this.storageGB = storageGB;
            return this;
       }
       public Builder hasGraphicsCard(boolean hasGraphicsCard) {
            this.hasGraphicsCard = hasGraphicsCard;
            return this;
       }

        public Computer build() {
            return new Computer(this);
        }
    }

}

```

#### Step 2: Usage Demo (Client Code)
```java
package hw4.builderpattern;

public class BuilderPatternDemo {
    public static void main(String[] args) {
        Computer officePC = new Computer.Builder("Intel 15")
                .ramGB(16)
                .storageGB(512)
                .build();

        Computer gamingPC = new Computer.Builder("AMD Ryzen 9")
                .ramGB(32)
                .storageGB(1024)
                .hasGraphicsCard(true)
                .build();

        System.out.println(officePC);
        System.out.println(gamingPC);
    }
}

```


---

## 2. Write code to explain how do default and static keywords work in interfaces since Java 8

- Since Java 8, interfaces in Java can include:

  - `default` methods → with a method body (not abstract!)
  - Default method can be overridden by implementing class

  - `static` methods → accessible from the interface name itself
  - Static method must be called on the interface

#### Example: `Vehicle` Interface

```java
package hw4.q2;

public interface Vehicle {

    // Default method (can be overridden by implementing class)
    default void start() {
        System.out.println("Vehicle is starting");
    }

    // Static method (called with Interface name)
    static void printInfo() {
        System.out.println("Vehicle Info");
    }

    // Abstract method (must be implemented)
    void drive();
}


```

#### `Car` Class Implements `Vehicle`
```java
package hw4.q2;

public class Car implements Vehicle{
    @Override
    public void drive() {
        System.out.println("Car is driving");
    }

    @Override
    public void start() {
        System.out.println("Car is starting");
    }

}

```

#### Main Method (Demo)
```java
package hw4.q2;

public class CarAndVehicleDemo {
    public static void main(String[] args) {
        Vehicle car = new Car();

        car.start();
        car.drive();
        Vehicle.printInfo();
    }
}

```


---

## 3. Write code to demo Java anonymous class, you may write your own POJOs (e.g. parent abstract classes, child classes, and interfaces)

- An anonymous class in Java is a class without a name that is defined and instantiated in a single expression.
- When to Use Anonymous Classes
  - You need to override or implement just one method.
  - The class is only needed in one place.
  - You want to avoid creating many short-lived class files.
  - Common in event-driven programming (e.g., GUI, callbacks, listeners).
- Example: Button click with anonymous class
  - Button class that can register a `ClickListener`
  - `ClickListener` as an interface
  - Use anonymous class to handle the click

#### Step 1: Interface (or abstract class) definition
```java
package hw4.q3;

public interface ClickListener {
    void onClick();
}

```

#### Button Class (Accepts ClickListener)
```java
package hw4.q3;

public class Button {
    private ClickListener clickListener;
    
    public void setClickListener(ClickListener listener) {
        this.clickListener = listener;
    }

    public void click() {
        System.out.println("Button clicked");
        if(clickListener != null) {
            clickListener.onClick();
        }
    }
}


```

#### Step 3: Using Anonymous Class in Client Code
````java
package hw4.q3;

public class AnonymousClassDemo {
    public static void main(String[] args) {
        Button button = new Button();

        // Register listener using an anonymous class
        button.setClickListener(new ClickListener() {
            @Override
            public void onClick() {
                System.out.println("Button handler: Hello from anonymous class!");
            }
        });

        button.click();

    }
}
// output:
// Button clicked
// Button handler: Hello from anonymous class!
````

---

## 4. Write code to explain Lambda expression with your own functional interface.

- We want to greet users with different styles — casual, formal, etc. Instead of creating separate classes, we'll use lambda expressions to define the behavior.

#### Step 1: Define Functional Interface
```java
package hw4.q4;

@FunctionalInterface
public interface GreetingService {
    void greet(String name);
}

```

#### Step 2: Use Lambda Expressions in Client Code
```java
package hw4.q4;

public class GreetingDemo {
    public static void main(String[] args) {

        // Casual greeting
        GreetingService casual = (name) -> System.out.println("Hello " + name);

        // Formal greeting
        GreetingService formal = (name) -> System.out.println("Good morning " + name);

        casual.greet("John");
        formal.greet("Bob");
    }
}
// Output:
// Hello John
// Good morning Bob
```


---

## 5. Write a calculator with BiFunction<T,U,R> (an internal functional interface provided by JDK) and Lambda expression. Your calculator should support two-number addition, subtraction,multiplication,division operations.

```java
package hw4.q5;

import java.util.function.BiFunction;


public class Calculator {
    public static void main(String[] args) {

        // define BiFunctions using lambda expression
        BiFunction<Double, Double, Double> add = (x, y) -> x + y;
        BiFunction<Double, Double, Double> subtract = (x, y) -> x - y;
        BiFunction<Double, Double, Double> multiply = (x, y) -> x * y;
        BiFunction<Double, Double, Double> divide = (x, y) -> {
           if(y == 0) {
               throw new ArithmeticException("Division by zero");
           }
           return x / y;
        };

        System.out.println(add.apply(10.0, 5.0)); // 15.0
        System.out.println(subtract.apply(15.0, 10.0)); // 5.0
        System.out.println(multiply.apply(6.0, 5.0)); // 30.0
        System.out.println(divide.apply(4.0, 5.0)); //0.8
        
    }

}

```
---