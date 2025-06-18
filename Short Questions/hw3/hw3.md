# HW3

## 1. Three Fundamental Concepts of OOP

- **Encapsulation**:  
  Encapsulation in OOP refers to binding the data and the methods to manipulate that data together in a single unit (class).
```java
public class Pet {
  private String name;
  private int age;

  public Pet(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public void speak() {
    System.out.println("The pet makes a sound.");
  }

  // Getter and Setter methods (controlled access)
  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    if(age > 0) {
      this.age = age;
    }
  }
}
```
- **Polymorphism**:  
  polymorphism refers to the same object exhibiting different forms and behaviors.
```java
public class Dog extends Pet {
  public Dog(String name, int age) {
    super(name, age);
  }

  @Override
  public void speak() {
    System.out.println(getName() + " says: Woof!");
  }
}

public class Cat extends Pet {
  public Cat(String name, int age) {
    super(name, age);
  }

  @Override
  public void speak() {
    System.out.println(getName() + " says: Meow!");
  }
}

public class Main {
  public static void main(String[] args) {
    Pet myDog = new Dog("Zaizai", 3);
    Pet myCat = new Cat("Kitty", 2);

    myDog.speak(); // Woof!
    myCat.speak(); // Meow!
  }
}

```
- **Inheritance**:  
  IS A Relationship. for instance, cat is a pet and dog is a pet.
```java
// Pet class is the superclass
// Dog and Cat inherit from Pet
public class Dog extends Pet {
  public Dog(String name, int age) {
    super(name, age);
  }

  public void fetch() {
    System.out.println(getName() + " is fetching the ball!");
  }
}

public class Main {
  public static void main(String[] args) {
    Dog zaizai = new Dog("Zaizai", 3);
    zaizai.speak(); // inherited and overridden method
    zaizai.fetch(); // new behavior
  }
}
```
---

## 2. Wrapper Data Type Classes

- What is wrapper data type classes (e.g., Integer, Double) in Java?
  - Wrapper classes in Java are object representations of the primitive data types. Each primitive type (such as `int`, `double`, `boolean`, etc.) has a corresponding wrapper class (`Integer`, `Double`, `Boolean`, etc.) that encapsulates the primitive value inside an object.

- Why do we need wrapper class?
  - Object Requirement: Many Java APIs and data structures (like collections — `ArrayList`, `HashMap`, etc.) work with objects, not primitive types. Wrapper classes allow primitives to be used where objects are required. 
  - Utility Methods: Wrapper classes provide useful methods for converting between types, parsing strings to numbers, comparing values, etc.
  - Autoboxing/Unboxing: Since Java 5, automatic conversion between primitives and their wrapper objects is supported, making it easier to work with collections and other APIs that require objects. 
  - Nullability: Wrapper classes can represent `null`, which is useful when you want to express the absence of a value — primitives cannot be `null`.

**Example Code:**

```java
int num = 10;  
Integer wrapperNum = Integer.valueOf(num);  // Boxing  
int primitiveNum = wrapperNum.intValue();  // Unboxing  

ArrayList<Integer> list = new ArrayList<>();  
list.add(num);  // Autoboxing happens automatically  
```
---

## 3. HashMap vs HashTable

- What is the difference between HashMap and HashTable?
1. **Thread Safety**
  - HashMap is **not synchronized** and is not thread-safe.
  - HashTable is **synchronized** and thread-safe — all methods are synchronized.

2. **Performance**
  - HashMap is faster because it does not have synchronization overhead.
  - HashTable is slower due to the cost of synchronization.

3. **Null Keys and Values**
  - HashMap allows **one null key** and multiple null values.
  - HashTable does **not allow** null keys or null values — it throws `NullPointerException`.

**Example Code:**

```java
HashMap<String, String> map = new HashMap<>();
map.put(null, "value");  // Allowed

Hashtable<String, String> table = new Hashtable<>();
table.put(null, "value");  // Throws NullPointerException
```

---

## 4. String Pool in Java

- What is String pool in Java?
  - In Java, the **String pool** is a special memory area in the heap that stores unique String literals. 
- Why do we need String pool?
  - This optimization reduces memory usage because duplicate string literals are not created.
- Explain String immutability.
  - In Java, **String objects are immutable** — once a String is created, its value cannot be changed.
**Example Code:**
```java
String s1 = "Hello";
String s2 = s1;
s1 = s1 + " World";
System.out.println(s1);  // Hello World
System.out.println(s2);  // Hello
```

---

## 5. Garbage Collection

- Explain garbage collection.
  - Garbage Collection (GC) is an automatic process in Java that frees memory by removing objects no longer referenced by the program.

- Explain types of garbage collection.
  1. **Serial GC**: Single-threaded, pauses application during collection, suitable for small apps.
  2. **Parallel GC**: Multi-threaded, faster, pauses application but reduces pause time, good for high throughput.
  3. **CMS (Concurrent Mark-Sweep) GC**: Minimizes pause time by running concurrently with app threads, good for low latency.
  4. **G1 (Garbage-First) GC**: Divides heap into regions, collects most garbage regions first, balances throughput and pause time, default in modern JVMs.

---

## 6. Access Modifiers

- Access modifiers control the visibility of classes, methods, and variables.
- In Java, there are four types:
  - `public`: accessible from anywhere
  - `protected`: accessible within the same package and subclasses
  - (default) package-private: accessible only within the same package (no keyword)
  - `private`: accessible only within the defining class
```java
// File: TestAccess.java
package mypackage;

public class TestAccess {
    public int publicVar = 1;
    protected int protectedVar = 2;
    int defaultVar = 3;  // package-private
    private int privateVar = 4;

    public void showAccess() {
        System.out.println("Public: " + publicVar);
        System.out.println("Protected: " + protectedVar);
        System.out.println("Default: " + defaultVar);
        System.out.println("Private: " + privateVar);
    }
}
// File: Main.java
package mypackage;

public class Main {
  public static void main(String[] args) {
    TestAccess obj = new TestAccess();
    System.out.println(obj.publicVar);      // OK, output: 1
    System.out.println(obj.protectedVar);   // OK, output: 2 (same package)
    System.out.println(obj.defaultVar);     // OK, output: 3 (same package)
    // System.out.println(obj.privateVar);  // Error: privateVar is not accessible
  }
}
```
---

## 7. Final Keyword

- `final` field: value cannot be changed once assigned (constant).
- `final` method: cannot be overridden by subclasses.
- `final` class: cannot be subclassed.
- Use `final` to enforce immutability, prevent modification, or inheritance where necessary.
```java
// Final field example
class MyClass {
    final int constantValue = 100;

    void display() {
        System.out.println("Constant Value: " + constantValue);
        // constantValue = 200; // Error: cannot assign a value to final variable
    }
}

// Final method example
class Parent {
    final void show() {
        System.out.println("Parent show()");
    }
}

class Child extends Parent {
    // void show() { } // Error: cannot override final method
}

// Final class example
final class FinalClass {
    void display() {
        System.out.println("FinalClass display()");
    }
}

// class SubClass extends FinalClass { } // Error: cannot inherit from final class

// Main method
public class Main {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.display();  // Output: Constant Value: 100

        Parent p = new Parent();
        p.show();  // Output: Parent show()

        FinalClass fc = new FinalClass();
        fc.display();  // Output: FinalClass display()
    }
}
```
---

## 8. Static Keyword

- `static` field/method: belongs to the class, not instances. Shared across all objects.
- `static` class: nested class not tied to an instance of the outer class.
- Use static for utilities, constants, or when shared data/method applies to all instances.
```java
class MyClass {
    // static field
    static int staticCounter = 0;
    
    // instance field
    int instanceCounter = 0;

    // static method
    static void staticMethod() {
        System.out.println("Static method called.");
    }

    // instance method
    void instanceMethod() {
        System.out.println("Instance method called.");
    }

    // static nested class
    static class StaticNestedClass {
        void display() {
            System.out.println("Inside static nested class.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Access static field
        System.out.println("Initial staticCounter: " + MyClass.staticCounter);  // Output: 0

        // Access static method
        MyClass.staticMethod();  // Output: Static method called.

        // Create instances
        MyClass obj1 = new MyClass();
        MyClass obj2 = new MyClass();

        // Modify static and instance fields
        obj1.staticCounter++;
        obj1.instanceCounter++;
        obj2.staticCounter++;
        obj2.instanceCounter++;

        // Display results
        System.out.println("obj1 staticCounter: " + obj1.staticCounter);  // Output: 2 (shared)
        System.out.println("obj2 staticCounter: " + obj2.staticCounter);  // Output: 2 (shared)

        System.out.println("obj1 instanceCounter: " + obj1.instanceCounter);  // Output: 1 (own copy)
        System.out.println("obj2 instanceCounter: " + obj2.instanceCounter);  // Output: 1 (own copy)

        // Use static nested class
        MyClass.StaticNestedClass nestedObj = new MyClass.StaticNestedClass();
        nestedObj.display();  // Output: Inside static nested class.
    }
}
```
---

## 9. Overriding vs Overloading

- **Overriding:** subclass provides a new implementation of a superclass method with the same signature. Runtime polymorphism.
- **Overloading:** same class has multiple methods with the same name but different parameter lists. Compile-time polymorphism.
```java
class Pet {
    String name;

    Pet(String name) {
        this.name = name;
    }

    void speak() {
        System.out.println(name + " makes a sound.");
    }
}

class Dog extends Pet {
    Dog(String name) {
        super(name);
    }

    // Overriding: subclass provides a new implementation
    @Override
    void speak() {
        System.out.println(name + " barks.");
    }
}

class PetTrainer {
    // Overloading: same method name, different parameters
    void train(Pet pet) {
        System.out.println("Training " + pet.name);
    }

    void train(Pet pet, int duration) {
        System.out.println("Training " + pet.name + " for " + duration + " minutes.");
    }
}

public class Main {
    public static void main(String[] args) {
        // Overriding example
        Pet genericPet = new Pet("GenericPet");
        genericPet.speak();  // Output: GenericPet makes a sound.

        Dog dog = new Dog("Buddy");
        dog.speak();  // Output: Buddy barks.

        Pet petDog = new Dog("Max");
        petDog.speak();  // Output: Max barks. (runtime polymorphism)

        // Overloading example
        PetTrainer trainer = new PetTrainer();
        trainer.train(genericPet);  // Output: Training GenericPet
        trainer.train(dog);         // Output: Training Buddy
        trainer.train(dog, 30);     // Output: Training Buddy for 30 minutes.
    }
}
```
---

## 10. Method Signature

- Method signature includes method name and parameter types (order and type).
- Return type and exceptions are NOT part of the signature.
- Helps Java distinguish overloaded methods by signature; overriding requires exact signature match.
```java
class PetTrainer {
    // Method 1
    void train(Pet pet) {
        System.out.println("Training " + pet.name);
    }

    // Method 2 (Overloaded)
    void train(Pet pet, int duration) {
        System.out.println("Training " + pet.name + " for " + duration + " minutes.");
    }

    // Method 3 (Overloaded)
    void train(Pet pet, String skill) {
        System.out.println("Training " + pet.name + " to " + skill);
    }

    // Return type is NOT part of method signature:
    // int train(Pet pet) { }  // Error: same signature as Method 1
}

public class Main {
    public static void main(String[] args) {
        Pet pet = new Pet("Fluffy");
        PetTrainer trainer = new PetTrainer();

        trainer.train(pet);                    // Calls Method 1
        trainer.train(pet, 30);                // Calls Method 2
        trainer.train(pet, "sit and stay");    // Calls Method 3
    }
}
```
---

## 11. super vs this

- `this`: refers to the current instance of the class.
- `super`: refers to the superclass (parent) of the current object, used to access overridden methods or constructors.
```java
class Pet {
    String name;

    Pet(String name) {
        this.name = name;  // 'this' refers to current instance variable
    }

    void speak() {
        System.out.println(name + " makes a sound.");
    }
}

class Dog extends Pet {
    int age;

    Dog(String name, int age) {
        super(name);  // 'super' calls parent class (Pet) constructor
        this.age = age;  // 'this' refers to current instance's age
    }

    @Override
    void speak() {
        super.speak();  // Call the parent version first
        System.out.println(this.name + " barks.");  // 'this' refers to current Dog object
    }

    void showInfo() {
        System.out.println("Dog name: " + this.name);  // 'this' refers to current object
        System.out.println("Dog age: " + this.age);
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Buddy", 3);

        dog.speak();
        // Output:
        // Buddy makes a sound.  (from super.speak())
        // Buddy barks.          (from Dog's speak)

        dog.showInfo();
        // Output:
        // Dog name: Buddy
        // Dog age: 3
    }
}
```
---

## 12. equals() and hashCode()

- `equals()`: compares object contents for equality (default compares references).
- `hashCode()`: returns an integer hash code; equal objects must have the same hash code.
- Both must be overridden together to maintain contract, especially for use in hash-based collections.
```java
        Pet pet1 = new Pet("Buddy", 3);
        Pet pet2 = new Pet("Buddy", 3);
        Pet pet3 = new Pet("Max", 5);

        // Test equals()
        System.out.println(pet1.equals(pet2));  // Output: true (contents equal)
        System.out.println(pet1.equals(pet3));  // Output: false (different content)

        // Test hashCode()
        System.out.println(pet1.hashCode() == pet2.hashCode());  // Output: true (contract: equal objects -> same hashCode)
        System.out.println(pet1.hashCode() == pet3.hashCode());  // Output: false (likely different)

```
---

## 13. Java Load Sequence

1. **Loading:** JVM loads class bytecode.
2. **Linking:** verifies, prepares (allocates memory), and optionally resolves symbolic references.
3. **Initialization:** executes static initializers and static blocks.
```java
class Pet {
    static {
        System.out.println("Pet static block - Initialization");
    }

    Pet() {
        System.out.println("Pet constructor - Instance created");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Main started");

        // Class is loaded when first referenced
        Pet pet1 = new Pet();
        Pet pet2 = new Pet();
    }
}

/**Main started
Pet static block - Initialization   // Initialization phase (static block)
Pet constructor - Instance created  // Instance created (constructor)
Pet constructor - Instance created  // Instance created (constructor)
**/
```
---

## 14. Polymorphism

- polymorphism refers to the same object exhibiting different forms and behaviors.
- Java implements polymorphism mainly through method overriding and dynamic method dispatch.
```java
//speak can be Overloading 
public class Pet {
    private String name;
    private int age;

    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void speak() {
        System.out.println("The pet makes a sound.");
    }
  public void speak(String a) {
    System.out.println("The pet makes a sound." + a);
  }
}
```
---

## 15. Encapsulation

- Encapsulation in OOP refers to binding the data and the methods to manipulate that data together in a single unit (class).
- Java implements it via `private` fields and public getter/setter methods.
- It protects data integrity and improves modularity.
```java
//pet class contains allthe property and method
public class Pet {
    private String name;
    private int age;

    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void speak() {
        System.out.println("The pet makes a sound.");
    }
}
```
---

## 16. Interface vs Abstract Class

- **Multiple Inheritance**
  - Interface: Supported
  - Abstract Class: Not supported

- **Methods**
  - Interface: Methods are abstract by default (since Java 8, default and static methods are allowed)
  - Abstract Class: Can have both abstract and concrete (implemented) methods

- **Fields**
  - Interface: Only `public static final` constants allowed
  - Abstract Class: Can have instance variables (fields)

- **Use Case**
  - Interface: Used to define a contract for unrelated classes
  - Abstract Class: Used to share code among closely related classes
```java
// Interface example
interface Flyer {
    void fly();  // abstract method by default

    default void takeOff() {
        System.out.println("Taking off...");
    }
}

// Abstract class example
abstract class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }

    abstract void makeSound();  // abstract method

    void eat() {  // concrete method
        System.out.println(name + " is eating.");
    }
}

// Implementing interface
class Bird implements Flyer {
    @Override
    public void fly() {
        System.out.println("Bird is flying.");
    }
}

// Extending abstract class
class Dog extends Animal {
    Dog(String name) {
        super(name);
    }

    @Override
    void makeSound() {
        System.out.println(name + " barks.");
    }
}

public class Main {
    public static void main(String[] args) {
        Flyer flyer = new Bird();
        flyer.takeOff();  // Output: Taking off...
        flyer.fly();      // Output: Bird is flying.

        Animal dog = new Dog("Buddy");
        dog.eat();        // Output: Buddy is eating.
        dog.makeSound();  // Output: Buddy barks.
    }
}
```
---

# Coding Practice: LeetCode

## 1. Top K Frequent Elements (LeetCode 347)

**Requirement**:  
Use Java Collection Framework (Set, Map, List) to solve.

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] freq = new int[k];
        //hashmap store index, frequency
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<HashMap.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> {
            return b.getValue() - a.getValue();
        }); 

        for(Map.Entry<Integer, Integer> entry: freqMap.entrySet()){
            pq.offer(entry);
        }  
        while(k > 0){
            freq[k-1] = pq.poll().getKey();
            k--;
        }
        return freq;

        
    }
}
```