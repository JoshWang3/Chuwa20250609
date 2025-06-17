1. Write code to demo

   1. **singleton pattern** (both lazy loading and eager loading),

   ```java
   // Eager loading (饿汉式) - 线程安全
   class EagerSingleton {
       private static final EagerSingleton INSTANCE = new EagerSingleton();
       private EagerSingleton() {}
       public static EagerSingleton getInstance() {
           return INSTANCE;
       }
   }
   
   // Lazy loading (懒汉式，线程安全)
   class LazySingleton {
       private static volatile LazySingleton instance; // volatile保证多线程可见性
       private LazySingleton() {}
       public static LazySingleton getInstance() {
           if (instance == null) {
               synchronized (LazySingleton.class) { // 双重检查锁定
                   if (instance == null) {
                       instance = new LazySingleton();
                   }
               }
           }
           return instance;
       }
   }
   
   ```

   

   1. **factory method pattern**

   ```java
   // 产品接口
   interface Product {
       void use();
   }
   
   // 具体产品
   class ProductA implements Product {
       public void use() { System.out.println("Using ProductA"); }
   }
   
   // 工厂接口
   interface Factory {
       Product createProduct();
   }
   
   // 具体工厂
   class ProductAFactory implements Factory {
       public Product createProduct() { return new ProductA(); }
   }
   
   // 使用
   class FactoryMethodDemo {
       public static void main(String[] args) {
           Factory factory = new ProductAFactory();
           Product product = factory.createProduct();
           product.use();
       }
   }
   
   ```

   

   1. **Abstract factory pattern**

   ```java
   // 产品族接口
   interface Button { void click(); }
   interface TextBox { void type(); }
   
   // 具体产品族
   class WinButton implements Button { public void click() { System.out.println("Windows Button"); } }
   class WinTextBox implements TextBox { public void type() { System.out.println("Windows TextBox"); } }
   class MacButton implements Button { public void click() { System.out.println("Mac Button"); } }
   class MacTextBox implements TextBox { public void type() { System.out.println("Mac TextBox"); } }
   
   // 抽象工厂
   interface GUIFactory {
       Button createButton();
       TextBox createTextBox();
   }
   
   // 具体工厂
   class WinFactory implements GUIFactory {
       public Button createButton() { return new WinButton(); }
       public TextBox createTextBox() { return new WinTextBox(); }
   }
   class MacFactory implements GUIFactory {
       public Button createButton() { return new MacButton(); }
       public TextBox createTextBox() { return new MacTextBox(); }
   }
   
   // 使用
   class AbstractFactoryDemo {
       public static void main(String[] args) {
           GUIFactory factory = new MacFactory();
           Button button = factory.createButton();
           TextBox textBox = factory.createTextBox();
           button.click();
           textBox.type();
       }
   }
   
   ```

   

   1. **Builder pattern**

   ```java
   class User {
       private final String name;
       private final int age;
       private final String email;
   
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
   
       @Override
       public String toString() {
           return "User{name='" + name + "', age=" + age + ", email='" + email + "'}";
       }
   }
   
   class BuilderDemo {
       public static void main(String[] args) {
           User user = new User.Builder()
                   .setName("Alice")
                   .setAge(30)
                   .setEmail("alice@example.com")
                   .build();
           System.out.println(user);
       }
   }
   
   ```

   

   1. PLEASE **DO NOT** USE DEMO CODE USED IN CLASS SESSIONS, PLEASE WRITE YOUR OWN CODE.
   2. Write necessary POJOs together with your core implementation, for your **singleton pattern** implementation, explain how you would guarantee it is **thread-safe** (thread-safe in this scenario means: there is **truely only one** instance in JVM).

   ```java
   public class Config {
       private String dbUrl;
       private String username;
       private String password;
   
       public Config(String dbUrl, String username, String password) {
           this.dbUrl = dbUrl;
           this.username = username;
           this.password = password;
       }
   
       // getters and setters
       public String getDbUrl() { return dbUrl; }
       public void setDbUrl(String dbUrl) { this.dbUrl = dbUrl; }
       public String getUsername() { return username; }
       public void setUsername(String username) { this.username = username; }
       public String getPassword() { return password; }
       public void setPassword(String password) { this.password = password; }
   }
   
   
   public class ConfigManager {
       // volatile 防止指令重排序，多线程下可见性
       private static volatile ConfigManager instance;
       private Config config;
   
       // 构造器私有，外部不能直接 new
       private ConfigManager() {}
   
       // 双重检查锁定 (Double-Checked Locking) 保证线程安全且高效
       public static ConfigManager getInstance() {
           if (instance == null) { // 先检查一次，提升性能
               synchronized (ConfigManager.class) {
                   if (instance == null) { // 第二次检查，防止并发创建多个实例
                       instance = new ConfigManager();
                   }
               }
           }
           return instance;
       }
   
       public void setConfig(Config config) {
           this.config = config;
       }
   
       public Config getConfig() {
           return config;
       }
   }
   
   
   public class Main {
       public static void main(String[] args) {
           // 线程1设置配置
           Config config = new Config("jdbc:mysql://localhost:3306/db", "admin", "123456");
           ConfigManager.getInstance().setConfig(config);
   
           // 线程2获取配置
           Config config2 = ConfigManager.getInstance().getConfig();
           System.out.println(config2.getDbUrl()); // 输出: jdbc:mysql://localhost:3306/db
       }
   }
   
   ```

   

2. Write code to explain how **do default and static** keywords work in interfaces since Java 8

```java
interface MyInterface {
    // default方法
    default void sayHello() {
        System.out.println("Hello from default method");
    }

    // static方法
    static void staticMethod() {
        System.out.println("Hello from static method");
    }
}

class DefaultStaticDemo implements MyInterface {
    public static void main(String[] args) {
        DefaultStaticDemo demo = new DefaultStaticDemo();
        demo.sayHello(); // 调用default方法

        MyInterface.staticMethod(); // 调用static方法
    }
}

```



1. Write code to demo **Java anonymous class**, you may write your own POJOs (e.g. parent abstract classes, child classes, and interfaces)

```java
abstract class Animal {
    abstract void makeSound();
}

class AnonymousClassDemo {
    public static void main(String[] args) {
        Animal dog = new Animal() {
            @Override
            void makeSound() {
                System.out.println("Woof!");
            }
        };
        dog.makeSound();
    }
}

```



1. Write code to explain **Lambda expression** with your own **functional interface**.

   ```java
   @FunctionalInterface
   interface StringOperation {
       String operate(String str);
   }
   
   class LambdaDemo {
       public static void main(String[] args) {
           StringOperation upperCase = s -> s.toUpperCase();
           System.out.println(upperCase.operate("hello world"));
       }
   }
   
   ```

   

2. Write a calculator with **BiFunction<T,U,R>** (an internal functional interface provided by JDK) and **Lambda expression**. Your calculator should support two-number **addition, subtraction, multiplication, division** operations.

```java
import java.util.function.BiFunction;

class Calculator {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> subtract = (a, b) -> a - b;
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        BiFunction<Integer, Integer, Integer> divide = (a, b) -> b != 0 ? a / b : 0;

        System.out.println("2 + 3 = " + add.apply(2, 3));
        System.out.println("5 - 1 = " + subtract.apply(5, 1));
        System.out.println("4 * 7 = " + multiply.apply(4, 7));
        System.out.println("8 / 2 = " + divide.apply(8, 2));
    }
}

```

