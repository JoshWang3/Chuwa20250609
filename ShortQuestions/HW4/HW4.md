# HW4: Java & OOP & Java 8 Features
@ Jun 17, 2025 _Gloria Wang_

> - All demo code can be found in packages under HW4 file, but I also listed them below for easier check and run
## 1. Write code to demo

### 1. Singleton Pattern 
> Demo code can be checked in Singleton package

> use `burgerMainSingleton` to run
#### Burger POJO 🍔
```Java
package Singleton;

public class Burger {
  private String type;
  private boolean hasCheese;

  public Burger(String type, boolean hasCheese) {
    this.type = type;
    this.hasCheese = hasCheese;
  }

  public String getType() { return type; }
  public void setType(String type) { this.type = type; }

  public boolean hasCheese() { return hasCheese; }
  public void setHasCheese(boolean has) { this.hasCheese = has; }

  @Override
  public String toString() {
    return (hasCheese ? "Cheese " : " ") + type + " Burger";
  }
}

```
#### Lazy Loading
- The instance is created only when it is first needed
- Use double-checked locking and volatile ensure thread safety
  - `volatile`: ensures all threads see the same value of instance and prevents certain JVM optimizations that could break double-checked locking
  - **synchronized block** guarantees that only one thread creates the instance if multiple threads call `getInstace()` at the same time

```Java
package Singleton;

public class LazyBurgerSingleton {
  // 1. private static volatile instance variable (not created until needed)
  // volatile ensures correct visibility of instance across threads
  private static volatile LazyBurgerSingleton instance = null;

  // example field: holds a Burger object
  private Burger burger;

  // 2. private constructor prevents external instantiation
  private LazyBurgerSingleton() {
    this.burger = new Burger("Chicken", false);
  }

  // 3. public static method for accessing the singleton instance
  // double check locking: synchronize only when instance is null, then check again inside lock
  // ensures only one instance is created, even in a multi-threaded environment
  public static LazyBurgerSingleton getInstance() {

    // first check: no locking
    if (instance == null) {
      synchronized (LazyBurgerSingleton.class) { // lock only if instance is null

        // second check: weith lock
        if (instance == null) {
          instance = new LazyBurgerSingleton();
        }
      }
    }
    return instance;
  }

  public Burger getBurger() { return burger; }
  public void setBurger(Burger burger) { this.burger = burger; }
}
```
#### Eager Loading
- The instance is created when the class loads
- JVM guarantees thread safety for static initialization
  - JVM loads and initialize the static variable exactly once, even in multi-threaded environments
  - There will only ever be one instance of this singleton

```Java
package Singleton;

public class EagerBurgerSingleton {
  // 1. private static variable
  // eager initialization: created as soon as the class is loaded
  private static final EagerBurgerSingleton instance = new EagerBurgerSingleton();

  // Example field: Burger object held by the singleton
  private Burger burger;

  // 2. private constructor prevents instantiation from outside class
  // ensures only one instance of Singleton.EagerBurgerSingleton can ever be created
  private EagerBurgerSingleton() {
    this.burger = new Burger("Beef", true);
  }

  // 3. static getInstance method to provide access to the singleton instance
  // always return the same instance
  public static EagerBurgerSingleton getInstance() {
    return instance;
  }

  // getter & setter for Burger field
  public Burger getBurger() { return burger; }
  public void setBurger(Burger burger) { this.burger = burger; }
}
```

#### Demo Main Method
> use `burgerMainSingleton` to run

```Java
package Singleton;

public class burgerMainSingleton {
  public static void main(String[] args) {

    // Eager Burger Singleton
    Burger b1 = EagerBurgerSingleton.getInstance().getBurger();
    System.out.println("Eager: " + b1);

    EagerBurgerSingleton.getInstance().setBurger(new Burger("Fish", false));
    System.out.println("Eager (after change): " + EagerBurgerSingleton.getInstance().getBurger());

    // Lazy Burger Singleton
    Burger b2 = LazyBurgerSingleton.getInstance().getBurger();
    System.out.println("Lazy: " + b2);

    LazyBurgerSingleton.getInstance().setBurger(new Burger("Veggie", true));
    System.out.println("Lazy (after change): " + LazyBurgerSingleton.getInstance().getBurger());

    // Confirm singleton property
    System.out.println("Eager singleton is unique: " + (EagerBurgerSingleton.getInstance() == EagerBurgerSingleton.getInstance()));
    System.out.println("Lazy singleton is unique: " + (LazyBurgerSingleton.getInstance() == LazyBurgerSingleton.getInstance()));
  }
}

```
#### Summary
- Eager: Singleton instance is created when the class loads, guaranteed to be only once; always thread-safe.
- Lazy: Singleton instance is created only when first needed; must use locking and volatile for thread safety.

### 2. Factory Method Pattern 
> Demo code can be checked in Factory package
- Factory Method Pattern define an interface for creating objects but allows subclasses to alter the type of objects trhat will be created
- For the code below:
  - **Abstract Product:** `Burger` is an abstract class with a method `getBurgerType()`
  - **Concrete Products:** `CheeseBurger`, `VeggieBurger`, and `ChickenBUrger` are subclass of `Burger`, each overriding `gerBurgerType()` to return their specific own type
  - **Abstract Factory:** `BurgerFactory` defines the factory method `createBurger()`
  - **Concrete Factory:** `CheeseBurgerFactory`, `VeggieBurgerFactory`, and `ChickenBurgerFactory` implement the `createBurger()` method to initiate their respective burger types
  - **Usage:** `burgerMainFactory` class creates diff factory objs and uses them to produce specific burger objs, demonstrating how the creation logic is seperated
> use `burgerMainFactory` to run
#### Burger POJO 🍔
```Java
package Factory;

public abstract class Burger {
  public abstract String getBurgerType();
}

class CheeseBurger extends Burger {
  @Override
  public String getBurgerType() {
    return "Cheese Burger";
  }
}

class VeggieBurger extends Burger {
  @Override
  public String getBurgerType() {
    return "Veggie Burger";
  }
}

class ChickenBurger extends Burger {
  @Override
  public String getBurgerType() {
    return "Chicken Burger";
  }
}

```

#### BurgerFactory
```Java
package Factory;

public abstract class BurgerFactory {
    // Factory Method
    public abstract Burger createBurger();
}

class CheeseBurgerFactory extends BurgerFactory{
    @Override
    public Burger createBurger() {
        return new CheeseBurger();
    }
}

class VeggieBurgerFactory extends BurgerFactory{
    @Override
    public Burger createBurger() {
        return new VeggieBurger();
    }
}

class ChickenBurgerFactory extends BurgerFactory{
    @Override
    public Burger createBurger() {
        return new ChickenBurger();
    }
}
```

#### burgerMainFactory
> use `burgerMainFactory` to run

```Java
package Factory;

public class burgerMainFactory {
    public static void main(String[] args) {
        // create factory
        BurgerFactory cheeseBurgerFactory = new CheeseBurgerFactory();
        BurgerFactory veggieBurgerFactory = new VeggieBurgerFactory();
        BurgerFactory chickenBurgerFactory = new ChickenBurgerFactory();

        // use factory to create burger
        Burger b1 = cheeseBurgerFactory.createBurger();
        Burger b2 = veggieBurgerFactory.createBurger();
        Burger b3 = chickenBurgerFactory.createBurger();

        // print burger type
        System.out.println(b1.getBurgerType());
        System.out.println(b2.getBurgerType());
        System.out.println(b3.getBurgerType());
    }
}
```

### Abstract Factory Pattern 
> Demo code can be checked in AbstractFactory package
- Modeling a fast food restaurant system where each meal includes both a burger and a drink. 
- For the code below:
  - **Product Interfaces**: Define two main product types: `Burger` and `Drink` as interfaces
  - **Concrete Products**: Define specific implementations for each product: `CheeseBurger`/`VeggieBurger` and `Cola`/`Juice`
  - **Abstract Factory**: `MealFactory` interface declares factory methods for creating each product in the family (`createBurger()` and `createDrink()`)
  - **Concrete Factories**: `CheeseMealFactory` and `VeggieMealFactory` implement the MealFactory interface, ensuring that each factory produces a consistent combination of burger and drink
    - cheese burger always comes with cola, veggie burger with juice
> use `burgerMainAbstractFactory` to run

#### Burger 🍔 (Product Interface)
```Java
package AbstractFactory;

// Product Interface
public interface Burger {
    String getBurgerType();
}
```

#### Drink (Product Interface)
```Java
package AbstractFactory;

// Product Interface
public interface Drink {
String getDrinkType();
}
```

#### CheeseBurger (Concrete Product)
```Java
package AbstractFactory;

// concrete product
public class CheeseBurger implements Burger {
    @Override
    public String getBurgerType() {
        return "Cheese Burger";
    }
}
```

#### VeggieBurger (Concrete Product)
```Java
package AbstractFactory;

// concrete product
public class VeggieBurger implements Burger {
    @Override
    public String getBurgerType() {
        return "Veggie Burger";
    }
}
```

#### Cola (Concrete Product)
```Java
package AbstractFactory;

// concrete product
public class Cola implements Drink{
    @Override
    public String getDrinkType() {
        return "Cola";
    }
}
```

#### Juice (Concrete Product)
```Java
package AbstractFactory;

// concrete product
public class Juice implements Drink{
    @Override
    public String getDrinkType() {
        return "Juice";
    }
}
```

#### MealFactory (Abstract Factory)
```Java
package AbstractFactory;

// Abstract Factory
public interface MealFactory {
    Burger createBurger();
    Drink createDrink();
}
```

#### CheeseMealFactory (Concrete Factory)
```Java
package AbstractFactory;

// Concrete Factory
public class CheeseMealFactory implements MealFactory {

    @Override
    public Burger createBurger() {
        return new CheeseBurger();
    }

    @Override
    public Drink createDrink() {
        return new Cola();
    }
}
```

#### VeggieMealFactory (Concrete Factory)
```Java
package AbstractFactory;

// Concrete Factory
public class VeggieMealFactory implements MealFactory {

    @Override
    public Burger createBurger() {
        return new VeggieBurger();
    }

    @Override
    public Drink createDrink() {
        return new Juice();
    }
}
```
#### burgerMainAbstractFactory
> use `burgerMainAbstractFactory` to run

```Java
package AbstractFactory;

import Factory.BurgerFactory;

public class burgerMainAbstractFactory {
    public static void main(String[] args) {

        // Cheese Meal
        MealFactory cheeseMealFactory = new CheeseMealFactory();
        Burger cheeseBurger = cheeseMealFactory.createBurger();
        Drink cola = cheeseMealFactory.createDrink();
        System.out.println("Eager Meal: " + cheeseBurger.getBurgerType() + " + " + cola.getDrinkType());

        // Veggie Meal
        MealFactory veggieMealFactory = new VeggieMealFactory();
        Burger veggieBurger = veggieMealFactory.createBurger();
        Drink juice = veggieMealFactory.createDrink();
        System.out.println("Veggie Meal: " + veggieBurger.getBurgerType() + " + " + juice.getDrinkType());
    }
}
```
### Builder Pattern 
> Demo code can be checked in Builder package
- Builder Pattern lets us to construct complex objects step by step -> useful when an object has many optional fields or configurations
- for the code below:
  - **Main Class:** `Burger`
    - Has required fields (bunType, pattyType) and optional fields (cheese, lettuce, tomato, onions, bacon)
    - Uses a private constructor to enforce that all burgers are built via the builder
  - **Static Inner Builder**: `Burger.BurgerBuilder`
    - Requires bun and patty type at creation
    - Provides chainable methods (`cheese()`, `lettuce()`...) to add optional ingredients
    - The `build()` method creates the final Burger instance 
> use `burgerMainBuilder` to run
#### Burger 🍔
```Java
package Builder;

public class Burger {

    // Required parameters
    private final String bunType;
    private final String pattyType;

    // Optional parameters
    private final boolean hasCheese;
    private final boolean hasLettuce;
    private final boolean hasTomato;
    private final boolean hasOnions;
    private final boolean hasBacon;

    // private constructor only accessible by Builder
    private Burger(BurgerBuilder builder) {
        this.bunType = builder.bunType;
        this.pattyType = builder.pattyType;
        this.hasCheese = builder.hasCheese;
        this.hasLettuce = builder.hasLettuce;
        this.hasTomato = builder.hasTomato;
        this.hasOnions = builder.hasOnions;
        this.hasBacon = builder.hasBacon;
    }

    @Override
    public String toString() {
        return "Burger [" +
                "bunType='" + bunType + '\'' +
                ", pattyType='" + pattyType + '\'' +
                ", cheese=" + hasCheese +
                ", lettuce=" + hasLettuce +
                ", tomato=" + hasTomato +
                ", onions=" + hasOnions +
                ", bacon=" + hasBacon +
                ']';
    }


    // Builder Inner Class
    public static class BurgerBuilder {

        // required
        private final String bunType;
        private final String pattyType;

        // Optional
        private boolean hasCheese = false;
        private boolean hasLettuce = false;
        private boolean hasTomato = false;
        private boolean hasOnions = false;
        private boolean hasBacon = false;

        public BurgerBuilder(String bunType, String pattyType) {
            this.bunType = bunType;
            this.pattyType = pattyType;
        }

        public BurgerBuilder cheese(boolean value) {
            this.hasCheese = value;
            return this;
        }

        public BurgerBuilder lettuce(boolean value) {
            this.hasLettuce = value;
            return this;
        }

        public BurgerBuilder tomato(boolean value) {
            this.hasTomato = value;
            return this;
        }

        public BurgerBuilder onions(boolean value) {
            this.hasOnions = value;
            return this;
        }

        public BurgerBuilder bacon(boolean value) {
            this.hasBacon = value;
            return this;
        }

        public Burger build() {
            return new Burger(this);
        }
    }
}
```

#### burgerMainBuilder
> use `burgerMainBuilder` to run

```Java
package Builder;

public class burgerMainBuilder {
    public static void main(String[] args) {

        // build a burger with cheese, lettuce, and bacon
        Burger eagerBurger = new Burger.BurgerBuilder("Sesame", "Beef")
                .cheese(true)
                .lettuce(true)
                .bacon(true)
                .build();

        System.out.println(eagerBurger);

        // build a veggie burger
        Burger veggieBurger = new Burger.BurgerBuilder("Wheat", "Veggie")
                .tomato(true)
                .onions(true)
                .build();

        System.out.println(veggieBurger);
    }
}
```
## 2. Write code to explain how do default and static keywords work in interfaces since Java 8
> Demo code can be checked in DefaultAndStatic package

#### Default Method: 
- provide default behavior
- can be called on instance & overridden
#### Static Method:
- utility or helper method
- must be called on the interface: `Burger.printStaticBurgerInfo()`, instead of an instance
#### Abstract Method:
- must be implemented by each class `getType()`

> Use `BurgerInterfaceDemo` to run

```Java
package DefaultAndStatic;

interface Burger {

    // Abstract Method: must implemented by class
    String getType();

    // Default Method: provide a default implementation, can be called / overridden by implementing classes
    default void printDefaultBurger() {
        System.out.println("This is a default classic burger");
    }

    // Static Method: utility method, called on interface itself
    static void printStaticBurgerInfo() {
        System.out.println("All burgers are made fresh daily!");
    }
}

class CheeseBurger implements Burger {
    @Override
    public String getType() {
        return "Cheese Burger";
    }

    // override default method: this is optional
    @Override
    public void printDefaultBurger() {
        System.out.println("This is a default classic burger overridden with cheese");
    }
}

class VeggieBurger implements Burger {
    @Override
    public String getType() {
        return "Veggie Burger";
    }

    // not override veggie burger here, return default implementation
}

public class BurgerInterfaceDemo {
    public static void main(String[] args) {
        Burger cheeseBurger = new CheeseBurger();
        Burger veggieBurger = new VeggieBurger();

        System.out.println(cheeseBurger.getType());
        cheeseBurger.printDefaultBurger(); // overridden before

        System.out.println(veggieBurger.getType());
        veggieBurger.printDefaultBurger(); // will be default value

        // static method called on interface, not obj
        Burger.printStaticBurgerInfo();

       // static, can't do 
       // cheeseBurger.printStaticBurgerInfo();
    }
}

/*
        output: 
            Cheese Burger
            This is a default classic burger overridden with cheese
            Veggie Burger
            This is a default classic burger
            All burgers are made fresh daily!
 */
```

## 3. Write code to demo Java anonymous class
> Demo code can be checked in Anonymous package

#### Interface (`Burger`)
- pure contract: cannot have state or method implementation (except static / default)
#### Abstract class (`BurgerOrder`)
- may contain abstract methods (require subclass or anonymous class to implement), as well as concrete class
#### Concrete class (`Bun`)
- fully implemented: can be subclassed, including anonymous, to customize behavior

> use `burgerMainAnonymous` to run
```Java
package Anonymous;

// Interface
// Any class that implements this interface must provide an implementation for getType()
interface Burger {
    String getType();
}

// Abstract class (parent abstract class)
// Can’t be instantiated directly
// Requires a subclass (or anonymous class) to provide the body for completeOrder().
abstract class BurgerOrder {
    abstract void completeOrder();
}

// Concrete class (concrete child class)
// Has a full implementation and can be instantiated directly
class Bun {
    public String getBunType() {
        return "Regular wheat bun";
    }
}


public class burgerMainAnonymous {
    public static void main(String[] args) {

        // Anonymous class for interface
        Burger spicyBurger = new Burger() {
            @Override
            public String getType() {
                return "Spicy Chicken Burger (from anonymous class)";
            }
        };
        System.out.println(spicyBurger.getType());


        // Anonymous class for abstract parent class
        BurgerOrder makeYourOwnBurger = new BurgerOrder() {
            @Override
            void completeOrder() {
                System.out.println("Order complete: Double cheese burger with no pickles (from anonymous class)");
            }
        };
        makeYourOwnBurger.completeOrder();


        // Anonymous class for concrete child class
        Bun customeBun = new Bun() {
            @Override
            public String getBunType() {
                return "Gluten free bun (overridden in anonymous class)";
            }
        };
        System.out.println(customeBun.getBunType());
    }
}
```

## 4. Write code to explain Lambda expression with functional interface
> Demo code can be checked in LambdaExpression package
- ⚠️ Functional interface has exactly one abstract method: `makeBurger`
- use `burgerMainLambda` to run

```Java
package LambdaExpression;

@FunctionalInterface
interface BurgerMaker {
    String makeBurger(String ingredients);
}

public class burgerMainLambda {
    public static void main(String[] args) {

        // use lambda expression for functional interface here
        // make bacon cheese burger
        BurgerMaker eagerBurgerMaker = (ingredients)-> "Eager Burger with " + ingredients + " and cheese";
        System.out.println(eagerBurgerMaker.makeBurger("Bacon"));

        // make avocado veggie burger
        BurgerMaker veggieBurgerMaker = (ingredients) -> "Veggie burger with " + ingredients;
        System.out.println(veggieBurgerMaker.makeBurger("avocado"));
    }
}
```

## 5. Write a calculator with BiFunction<T,U,R>
> Demo code can be checked in LambdaCalculator package
> use `LambdaCalculator` to run

```Java
package LambdaCalculator;
import java.util.function.BiFunction;

public class LambdaCalculator {
  public static void main(String[] args) {
    double x = 15.0;
    double y = 3.0;

    add(x, y);
    subtract(x, y);
    multiply(x, y);
    divide(x, y);
    divide(x, 0);

  }

  // Addition
  public static void add(double x, double y) {
    BiFunction<Double, Double, Double> add = (a, b) -> a + b;
    System.out.println(add.apply(x, y));
  }

  // Subtraction
  public static void subtract(double x, double y) {
    BiFunction<Double, Double, Double> subtract = (a, b) -> a - b;
    System.out.println(subtract.apply(x, y));
  }

  // Multiplication
  public static void multiply(double x, double y) {
    BiFunction<Double, Double, Double> multiply = (a, b) -> a * b;
    System.out.println(multiply.apply(x, y));
  }

  // Division
  public static void divide(double x, double y) {
    BiFunction<Double, Double, Double> divide = (a, b) -> b == 0 ? Double.NaN : a / b;
    System.out.println(divide.apply(x, y));
  }
}

/*
        output: 
             18.0
             12.0
             45.0
             5.0
             NaN
            
 */
```

and also we can check if it work here:
> `CalculatorTester`