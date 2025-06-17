# Chuwa hw3

## Question 1

### Encapsulation:

```java
class Account {
    private double balance; 

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

   
    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }
}

```

We can see, our balance is a private double in the class. And if we want to access the balance we should use public methods to interact with it.

### Inheritance

```java
public class Animal {
		public void makeSound() {
				System.out.println("hello");
		}
}

public class Dog extends Animal {
		@override
		public void makeSound() {
				System.out.println("yahoo");
		}
}
```

**Dog inherits** from Animal and **overrides** the makeSound() method. This lets the subclass reuse and customize behavior from the parent class.

### Polymorphism

```java
public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        Animal myDog = new Dog();

        myAnimal.makeSound(); 
        myDog.makeSound();  
    }
}
```

Polymorphism allows an object to take **many forms**. `myDog` is declared as an `Animal`, but it runs `Dog`’s version of `makeSound()` because of **runtime polymorphism**.

## Question 2

```java
public class WrapperDemo {
    public static void main(String[] args) {
        int a = 10;
        Integer b = a;  // autoboxing
        int c = b + 5;  // unboxing + arithmetic

        System.out.println("Primitive int: " + a);
        System.out.println("Wrapper Integer: " + b);
        System.out.println("After arithmetic: " + c);
    }
}
```

Wrapper classes are object representations of primitive data types.

We need wrapper classes because java is an oop languages, and sometimes we have to work with objects. For example, Collections in java don’t support primitive data types. And also wrapper classes have autobox and unbox capabilities.

## Question 3

```java
public class MapDemo {
    public static void main(String[] args) {
        // HashMap
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(null, "nullKey"); // Allowed
        hashMap.put("a", null);       // Allowed
        System.out.println("HashMap: " + hashMap);

        // Hashtable
        Map<String, String> hashTable = new Hashtable<>();
        // hashTable.put(null, "nullKey"); // Throws NullPointerException
        hashTable.put("b", "value");
        System.out.println("Hashtable: " + hashTable);
    }
}
```

1. Hashmap is not thread safe, but Hashtable is.
2. Hashmap can have null key, but Hashtable can’t.
3. Hashmap is faster because it’s unsynchronized.

## Question 4

```java
String a = "hello";
String b = "hello";

System.out.println(a == b); // true (both refer to same object in the pool)
```

in this code, both a and b actually refer to the same String in the String pool.

```java
String s = "abc";
s.concat("def");

System.out.println(s); // Outputs: abc (original string unchanged)
```

Although we use concat and try to change the String s, it is still the same string. We can only reassign it to be the new String.

## Question 5

```java
public class Demo {
    public static void main(String[] args) {
        String s = new String("hello");
        s = null;

        System.gc(); // suggest GC to run
    }
}

```

**Garbage Collection (GC)** is Java’s automatic memory management process.

To automatically **find and remove unused objects** from memory (heap) to prevent memory leaks and OutOfMemoryError.

- **Serial GC**
    
    Uses a single thread for garbage collection; ideal for small applications.
    
- **Parallel GC (Throughput Collector)**
    
    Uses multiple threads to maximize application throughput during GC.
    
- **CMS (Concurrent Mark Sweep)**
    
    Performs most of its work concurrently to reduce pause times (deprecated).
    
- **G1 GC (Garbage First)**
    
    Splits the heap into regions and collects garbage incrementally with low pauses.
    

## Question 6

public: everywhere

```java
public class Car { public void drive() {} }
```

protected: same package or subclass(different pacakge)

```java
protected void startEngine() {}
```

default:same package

```java
void service() {} // default access
```

private:same class

```java
private String VIN = "12345";
```

## Question 7

For variable, you can’t change later

```java
public class Car {
    final int wheels = 4;

    public void test() {
        // wheels = 5; ❌ Error: cannot assign a value to final variable
    }
}

```

For method, you can’t override

```java
class Animal {
    public final void sleep() {
        System.out.println("Sleeping...");
    }
}

class Dog extends Animal {
    // public void sleep() {} ❌ Error: Cannot override final method
}

```

For class, cannot be subclassed

```java
public final class MathUtils {
    public static int square(int x) {
        return x * x;
    }
}

// class MyMath extends MathUtils {} ❌ Error: Cannot subclass final class

```

## Question 8

For variable, the variable is shared all instances of the class

```java
public class Counter {
    static int count = 0; // shared among all instances

    public Counter() {
        count++;
    }
}

```

For method, it can be called without an object

```java
public class MathUtil {
    public static int square(int x) {
        return x * x;
    }
}

// Usage
int result = MathUtil.square(5); // ✅ no object needed

```

For class, inner class doesn’t need an outer class object to create it

```java
public class Outer {
    static class Inner {
        void display() {
            System.out.println("Static inner class");
        }
    }
}
Outer.Inner obj = new Outer.Inner(); // ✅
```

Usage: when we want define some variable as constant or global variables and we want some methods to be utilitily methods which means we don’t need to create objects and we can still use that method.

## Question 9

overriding

```java
class Animal {
    public void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}

```

overloading

```java
class Printer {
    public void print(String s) {
        System.out.println(s);
    }

    public void print(int n) {
        System.out.println(n);
    }

    public void print(String s, int n) {
        System.out.println(s + " " + n);
    }
}

```

The key difference is overriding is same method but in different subclasses with different behaviors. Overloading is same method name but with different parameters, and they behave differently in the same class.

## Question 10

method signature is method names + parameter types (in order)

So for overriding must have the same signature, but for overload must have different signatures (return types don’t count)

Code see Question 9

## Question 11

this: refers to the current object (maybe variables, maybe methods)

```java
class Person {
    String name;

    Person(String name) {
        this.name = name; // disambiguates field from parameter
    }
}

```

super: refers to the parent class (maybe constructor, and methods and variables)

```java
class Animal {
    void speak() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    void speak() {
        super.speak(); // calls Animal's version
        System.out.println("Dog barks");
    }
}

```

## Question 12

equals: to compare two objects are logically equal (the default is to compare memory address)

hashcode: returns an int used in hash-based collections

So if two objects return true using equals, they have same hashcode, however if they have same hashcode, it’s not guaranteed that they are equal.

```java
Object a = new Object();
Object b = new Object();
System.out.println(a.equals(b)); // false — different memory

Set<Person> set = new HashSet<>();
set.add(new Person("Alice"));
set.contains(new Person("Alice")); // ❌ false if equals/hashCode not overridden

```

## Question 13

The **Java load (or initialization) sequence** refers to the **order in which classes and their members are loaded, initialized, and executed** during runtime.

Class load

```java
// Done implicitly when you use a class
MyClass obj = new MyClass(); // triggers class loading
```

Static initialization: Static variables and static blocks

```java
class Demo {
    static int x = initX();

    static {
        System.out.println("Static block");
    }

    static int initX() {
        System.out.println("Static variable x initialized");
        return 10;
    }
}

```

Instance initialization: happens everytime when an object is created

```java
class Example {
    int a = initA(); // Step 1

    {                // Step 2
        System.out.println("Instance block");
    }

    Example() {      // Step 3
        System.out.println("Constructor");
    }

    int initA() {
        System.out.println("Instance variable a initialized");
        return 5;
    }
}

```

## Question 14

Polymorphism allows an object to take **many forms**. `myDog` is declared as an `Animal`, but it runs `Dog`’s version of `makeSound()` because of **runtime polymorphism**.

```java
public class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        Animal myDog = new Dog();

        myAnimal.makeSound(); 
        myDog.makeSound();  
    }
}
```

## Question 15

```java
class Account {
    private double balance; 

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

   
    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) balance -= amount;
    }
}

```

**Encapsulation** is the **OOP principle** of **bundling data (fields)** and **methods** that operate on that data into a **single class**, and **restricting direct access** to the internal state.
We need this because

1. For safety, so others can not directly change the variables in the object, have to use getter and setter
2. It’s easy to maintain
3. Hide data from outside for privacy
4. keep classes self-contained and logically separated

## Question 16

```java
abstract class Vehicle {
    int speed;
    Vehicle(int speed) {
        this.speed = speed;
    }
    void start() {
        System.out.println("Vehicle starting...");
    }
    abstract void drive();
}

interface Electric {
    void charge();
}

class Tesla extends Vehicle implements Electric {
    Tesla() { super(100); }

    void drive() {
        System.out.println("Driving Tesla at " + speed + "mph");
    }

    public void charge() {
        System.out.println("Charging...");
    }
}
```

As you can see interface and abstract class are very similar, but they do have some differences, which make use can use them in different cases.

1. So interface is more like a contract, it tells any class using that interface what to do. But abstract class is more like a base class, it tells what and partially how
2. Interface supports multiple inheritance but abstract class doesn’t.
3. Interface doesn’t have constructors but abstract class doesn

So abstract class is more like an is-a relationship and interface is more like a can-do relationship