# Ryan Ma HW4 Answers and Sample code



## 1. Write code to demo
### 1.1 Singleton pattern(both lazy loading and eager loading)
```java
class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();
    
    private EagerSingleton () {
        
    }
    
    public static EagerSingleton getInstance() {
        return instance;
    }
}
```
- Why eager loading is thread safe?
  - Class loading and static initialization in Java are thread-safe.
  - The instance is created when the EagerSingleton class is loaded by the JVM.
  - The JVM guarantees that the initialization of static fields happens once and only one.
  - Therefore, no matter how many threads try to access getInstance() simultaneously, they will all get the same instance.

```java
class LazySingleton {
    
    private LazySingleton () {
        
    }
    
    private static class SingletonHolder {
        private static final LazySingleton instance = new LazySingleton();
    }
    
    public static LazySingleton getInstance() {
        return SingletonHolder.instance;
    }
}
```
- Why lazy loading is thread safe?
  - The static inner class is not loaded until it is firest referenced, which happens when getInstance() is called.
  - The SingletonHolder class will be loaded and initialized exactly once, when accessed by the firest thread calling getInstance();
  - Therefore, only one LazySingleton will ever be created, even under concurrent access.
  - 
- 1.2 Double-checked Locking Pattern.
```java
class DoubleCheckedSingleton {
    private static volatile DoubleCheckedSingleton instance;
    
    private DoubleCheckedSingleton() {
        
    }
    
    public static DoubleCheckedSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }
}
```
- Why double-checked single is thread safe?
  - The volatile keyword prevents instruction reordering during object initialization.
  -  Ensures visibility across threads, so once instance is assigned, other threads see the fully initialized object.

### 1.2 factory method pattern
- Define an interface for creating an object, but let subclasses decide with class to instantiate.
- Allows object creation to be deferred to subclasses.

```java
interface Shape {
  void draw();
}

class Circle implements Shape {
  @Override
  public void draw() {
    System.out.println();
    n("Drawing a Circle");
  }
}

class Rectangle implements Shape {
  @Override
  public void draw() {
    System.out.print("Drawing a Rectangle");
  }
}

class ShapeFactory {
  public Shape getShape(String shapeType) {
    if (shapeType == null) {
      return null;
    } else if (shapeType.equalsIgnoreCase("Circle")) {
      return new Circle();
    } else if (shapeType.equalsIgnoreCase("Rectangle")) {
      return new Rectangle();
    }
    
    return null;
  }
}

class Main {
  public static void main(String[] args) {
    ShapeFactory shapeFactory = new ShapeFactory();

    Shape shape1 = shapeFactory.getShape("Circle");
    shape1.draw();

    Shape shape2 = shapeFactory.getShape("Rectangle");
    shape2.draw();
  }
}
```
- Explanation
  - Shape (Product interface): Define the draw() method;
  - Circle, Rectangle: Implement the Shape interface.
  - ShapeFactor: Contains getShape(), a factory method that returns the appropriate implementation based on input.

### 1.3 Abstract factory pattern
- Provides an interface for creating families of related or dependent objects without specifying their concrete class.
- It is essentially a factory of factories
```java
// Product Interfaces
interface Shape {
    void draw();
}

interface Color {
    void fill();
}

// Concrete Products
class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing a Circle.");
    }
}

class Rectangle implements Shape {
    public void draw() {
        System.out.println("Drawing a Rectangle.");
    }
}

class Red implements Color {
    public void fill() {
        System.out.println("Filling with Red color.");
    }
}

class Blue implements Color {
    public void fill() {
        System.out.println("Filling with Blue color.");
    }
}

// Abstract Factory
abstract class AbstractFactory {
    abstract Shape getShape(String shapeType);
    abstract Color getColor(String colorType);
}

// Concrete Factories
class ShapeFactory extends AbstractFactory {
    public Shape getShape(String shapeType) {
        if (shapeType == null) return null;
        if (shapeType.equalsIgnoreCase("CIRCLE")) return new Circle();
        else if (shapeType.equalsIgnoreCase("RECTANGLE")) return new Rectangle();
        return null;
    }
    public Color getColor(String colorType) {
        return null; // ShapeFactory doesn't handle Color
    }
}

class ColorFactory extends AbstractFactory {
    public Shape getShape(String shapeType) {
        return null; // ColorFactory doesn't handle Shape
    }
    public Color getColor(String colorType) {
        if (colorType == null) return null;
        if (colorType.equalsIgnoreCase("RED")) return new Red();
        else if (colorType.equalsIgnoreCase("BLUE")) return new Blue();
        return null;
    }
}

// Factory Producer
class FactoryProducer {
    public static AbstractFactory getFactory(String choice) {
        if (choice.equalsIgnoreCase("SHAPE")) return new ShapeFactory();
        else if (choice.equalsIgnoreCase("COLOR")) return new ColorFactory();
        return null;
    }
}

// Main
public class Main {
    public static void main(String[] args) {
        // Get Shape Factory
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();

        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        shape2.draw();

        // Get Color Factory
        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");
        Color color1 = colorFactory.getColor("RED");
        color1.fill();

        Color color2 = colorFactory.getColor("BLUE");
        color2.fill();
    }
}
```

### 1.4 Builder pattern
- Used to build complex objects step-by-step
```java
public class User {
    private final String name;
    
    private final int age;
    private final String email;
    
    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
    }
    
    public static class Builder {
        private final String name;
        private int age = 0;
        private String email = "";
        
        public Builder(String name) {
            this.name = name;
        }
        
        public Builder age(int age) {
            this.age = age;
            return this;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public User build() {
            return new User(this);
        }
        
    }

    public void displayInfo() {
      System.out.println(name + "   " + age + "   " + email);
    }
}

class Main {
    public static void main(String[] args) {
        User user = new User.Builder("Tom").age(10).email("@gmail").build();
        
        user.displayInfo();
    }
}
```

## 2. Write code to explain how do default and static keywords work in interface since java 8
- default methods in interface
  - Provide a default implementation inside an interface
- static methods in interfaces
  - Belong to the interface itself
```java
interface Animal {
  void sound();

  default void sleep() {
    System.out.println("This is default");
  }

  static void info() {
    System.out.println("This is static");
  }
}

class Dog implements Animal {
  @Override
  public void sound() {
    System.out.println("Dog barks");
  }
}

class Main {
  public static void main(String[] args) {
      Dog dog = new Dog();
      
      dog.sleep();
      dog.sound();
      
      Animal.info();
  }
}
```

## 3. Write code to demo Java anonymous class, you may write your own POJOs (e.g. parent abstract classes, child classes, and interfaces)
- A class without a name, defined and instantiated in a single expression.
- Commonly used to quickly implement abstract classes or interfaces on the spot.

```java
interface Operation {
  int calculate(int a, int b);
}

class Main {
  public static void main(String[] args) {
    Operation add = new Operation() {
      @Override
      public int calculate(int a, int b) {
            return a + b;
      }
    };
    
    Operation subtract = new Operation() {
        @Override
        public int calculate(int a, int b) {
            return a - b;
        }
    };
    
    int a = 5;
    int b = 1;
    
    System.out.println("Add: " + add.calculate(a, b));
    System.out.println("Subtract:" + subtract.calculate(a, b));
  }
}
```

## 4.Write code to explain Lambda expression with your own functional interface.
- Simplify passing behavior.
- Used with functional interfaces(interfaces with exactly one abstract method).
```java
interface Operation {
    int calculate(int a, int b);
}

class Main {
    public static void main(String[] args) {
        Operation add = (a, b) -> a + b;
        Operation subtract = (a, b) -> a - b;
        Operation multiply = (a, b) -> a * b;
        Operation divide = (a, b) -> {
            if (b == 0) {
                System.out.println("Can not divided by zero!");
                return 0;
            }
            else {
                return a / b;
            }
        };
        
        int m = 6;
        int n = 2;
        
        System.out.println("Add: " + add.calculate(m, n));
        System.out.println("Subtract: " + subtract.calculate(m, n));
        System.out.println("multiply: " + multiply.calculate(m, n));
        System.out.println("divide: " + divide.calculate(m, n));
    }
}
```

## 5. Write a calculator with BiFunction<T,U,R> (an internal functional interface provided by JDK) and Lambda expression. Your calculator should support two-number addition, subtraction,multiplication,division operations.

```java
import java.util.function.BiFunction;

class Main {
  public static void main(String[] args) {
    BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
    BiFunction<Integer, Integer, Integer> subtract = (a, b) -> a - b;
    BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
    BiFunction<Integer, Integer, Integer> divide = (a, b) -> {
        if (b == 0) {
            System.out.println("can not divided by 0");
            return 0;
        }
        return a / b;
    };
    
    
    System.out.println("Add: " + add.apply(10, 2));
    System.out.println("Subtract: " + subtract.apply(10, 2));
    System.out.println("Multiply: " + multiply.apply(10, 2));
    System.out.println("Divide: " + divide.apply(10, 2));
    
  }
}
```