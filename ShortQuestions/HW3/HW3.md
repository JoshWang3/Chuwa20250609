# HW3: Java & OOP
@ Jun 16, 2025 _Gloria Wang_

## 1. Write up Example code to demonstrate the three fundamental concepts of OOP
### 1. Encapsulation
- Encapsulation in OOP refers to binding the **data** and the **methods to manipulate** that data together in a single **unit** (class).
- Typically achieved using private fields and public `getter/setter` methods.

```Java
class Singleton.Burger {
    // private fields (encapsulated)
    private String bun;
    private String patty;
    private boolean hasLettuce;
    
    // constructor
    public Singleton.Burger(String bun, String patty, boolean hasLettuce) {
        this.bun = bun;
        this.patty = patty;
        this.hasLettuce = hasLettuce;
    }
    
    // getter / setter 
    public String getBun() {
        return bun;
    }
    public void setBun(String bun) {
        this.bun = bun;
    }

    public String getPatty() {
        return patty;
    }
    public void setPatty(String patty) {
        this.patty = patty;
    }

    public String hasLettuce() {
        return hasLettuce;
    }
    public void setLettuce(boolean hasLettuce) {
        this.hasLettuce = hasLettuce;
    }
    
    public void describe() {
        System.out.println("Singleton.Burger with " + bun + " bun, " + patty + " patty, " + (hasLettuce ? "with lettuce." : "no lettuce."));
    }
}

public class Main {
    public static void main(String[] args) {
        // create a new burger
        Singleton.Burger burger = new Singleton.Burger("sesame", "beef", true);
        
        // use getter to print bun type
        System.out.println("Bun: " + butger.getBun());

        // System.out.println(burger.bun); -> not allowed, must use via getter -> burger.getBun()
        
        // change patty using setter
        burger.serPatty("chicken");
        
        // describe the burger (use private fields via methods)
        burger.describe();
        
        // change lettuce using setter
        burger.setLettuce(false);
        burger.describe();
    }
}

/*
         output:
             Bun: sesame
             Singleton.Burger with sesame bun, chicken patty, with lettuce.
             Singleton.Burger with sesame bun, chicken patty, no lettuce.
 */
```

### 2. Polymorphism
- **Static** Polymorphism - **Overload** (same class) - **compile** time
- **Dynamic** Polymorphism - **Override** (child class) - **run** time

#### Overloading
- methods have the same name but different parameter lists
```Java
class Singleton.Burger {
    public void addTopping(String topping) {
        System.out.println("Added topping: " + topping);
    }
    // Overloaded: different parameter type
    public void addTopping(String topping, int amount) {
        System.out.println("Added " + amount + "x " + topping);
    }
}

public class Main {
    public static void main(String[] args) {
        Singleton.Burger b = new Singleton.Burger();
        b.addTopping("lettuce");    // call first version
        b.addTopping("tomato", 2);  // call overloaded version
    }
}

/*
        output:
            Added topping: lettuce
            Added 2x tomato
 */
```
#### Overriding 
- subclass provides a specific implementation for a method already defined in its superclass.
```Java
class Singleton.Burger {
    public void describe() {
        System.out.println("This is s burger.");
    }
}
class Factory.CheeseBurger extends Singleton.Burger {
    @Override
    public void describe() {
        System.out.println("This is a cheeseburger!");
    }
}

public class Main {
    public static void main(String[] args) {
        Singleton.Burger b1 = new Singleton.Burger();
        Singleton.Burger b2 = new Factory.CheeseBurger(); // upcasting
        
        b1.describe(); // calls Singleton.Burger's describe()
        b2.describe(); // calls Factory.CheeseBurger's describe() due to override
    }
}

/*
        output:
            This is a burger.
            This is a cheeseburger!
 */
```
### 3. Inheritance
- Inheritance provides a way to create a new class from an existing class. 
- The new class is a specialized version of the existing class such that it inherits all the non-private fields (variables) and methods of the existing class.
- The existing class is used as a starting point or as a base to create the new class.

```Java
// parent class
class Singleton.Burger {
    private String bun;
    private String patty;

    public Singleton.Burger(String bun, String patty) {
        this.bun = bun;
        this.patty = patty;
    }
    
    public void describe() {
        System.out.println("Singleton.Burger with " + bun + " bun and " + patty + " patty.");
    }
}

// child class inherits from Singleton.Burger
class Factory.CheeseBurger extends Singleton.Burger {

    // child class can have extra method or override parent methods
    
    // Extra Field:
    private boolean hasCheese;
    
    // Extra Constructor:
    public Factory.CheeseBurger(String bun, String patty, boolean hasCheese) {
        
        super(bun, patty); // call the parent constructor
        this.hasCheese = hasCheese;
    }
    
    // Override Method:
    @Override
    public void describe() {
        
        super.describe();
        if (hasCheese) {
            System.out.println("With cheese.");
        } else {
            System.out.println("No cheese.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Singleton.Burger basicBurger = new Singleton.Burger("sesame", "beef");
        Factory.CheeseBurger cheeseBurger = new Factory.CheeseBurger("plain", "chicken", true);
        
        basicBurger.describe(); 
        cheeseBurger.describe();
    }
}

/*
        output:
            Singleton.Burger with sesame bun and beef patty.
            Singleton.Burger with plain bun and chicken patty.
            With cheese.
 */
```

## 2. What is wrapper data type classes (e.g. Integer, Double) in Java and Why we need wrapper class?
- A **wrapper class** is an object class in Java that wraps a primitive data type value inside an object.

Each primitive data type has a corresponding wrapper class:

| Primitive Type | Wrapper Class |
|:--------------:|:------------:|
| byte           | Byte         |
| short          | Short        |
| int            | Integer      |
| long           | Long         |
| float          | Float        |
| double         | Double       |
| char           | Character    |
| boolean        | Boolean      |

```Java
int x = 5; // primitive
Integer xObject = new Integer(5); // wrapper class (object)

Integer y = 10; // autoboxing: Java auto coverts int to Integer
int z = y;      // unboxing: Java converts Integer to int
```

#### Wrapper class is immutable
```Java
// -> means point to
Integer a = 100;        // a -> Integer obj with value 100
Integer b = a;          // b -> same Integer obj as a (value 100)
a = 200;                // a -> NEW Integer obj with value 200, b is unchanged 

System.out.println(b);  // output: 100 
```

### Why we need wrapper class?
#### 1. Java Collections (ArrayList, HashMap)
- Collections cannot store **primitive types**
```Java
ArrayList<int> list = new ArrayList<int>(); // ERROR! Not allowed
ArrayList<Integer> list = new ArrayList<Integer>(); // OK!
```
#### 2. Methods require objects instead of primitives
- Some Java library methods and frameworks expect objects, not primitives.

#### 3. Utility Methods
- Wrapper classes provide some useful methods
```Java
int num = Integer.parseInt("123");
```

#### 4. Null Methods
- Wrapper class can be null, but primitive cannot
```Java
public class Main {
    public static void main(String[] args) {
        Integer a = null;   // This is allowed! Wrapper can be null.
        // int b = null;    // This will cause a compile error! Primitives can't be null.

        if (a == null) {
            System.out.println("a is null (no value)");
        } else {
            System.out.println("a = " + a);
        }
    }
}
/*
       output: a is null (no value)
 */
```
#### 5. Autoboxing and Unboxing
- Java auto converts primitives to their wrapper types and vice versa, making it convenient to work.

### Summary
| Use Case                              | Why Wrapper Needed?                   |
|----------------------------------------|---------------------------------------|
| Java Collections (e.g. ArrayList)      | Collections store objects only        |
| Null values                            | Primitives can’t be null              |
| Utility methods                        | Parsing, converting, etc.             |
| Passing to methods expecting objects   | Primitives not accepted               |
| Autoboxing/unboxing                    | Automatic conversion                  |

## 3. What is the difference between HashMap and HashTable?

| Feature         | HashMap                              | HashTable                              |
|-----------------|--------------------------------------|----------------------------------------|
| Null Key        | Allowed (one null key)               | Not allowed (throws exception)         |
| Null Value      | Allowed (multiple null values)       | Not allowed                            |
| Thread Safety   | Not thread-safe                      | Thread-safe (all methods synchronized) |
| Performance     | Faster (no locking)                  | Slower (synchronization overhead)      |
| Use in New Code | Recommended                          | Not recommended (legacy only)          |

#### Null Key / Value Example
```Java
// HashMap
HashMap<String, String> hashMap = new HashMap<>();
map1.put(null, "hello"); // OK
map1.put("key", null);   // OK

// HashTable
Hashtable<String, String> hashtable = new Hashtable<>();
map2.put(null, "hello"); // Throws NullPointerException!
map2.put("key", null);   // Throws NullPointerException!
```

#### HashMap NOT thread-safe vs Hashtable thread-safe

HashMap:
- methods like `put` and `get` are not synchronized
- -> multiple threads can access and modify the same HashMap obj at the same time

Hashtable
- all methods are synchronized
- -> force only one thread at a time to execute any of those methods on a single Hashtable obj


## 4. What is String pool in Java and why we need String pool? Explain String immunity.
- String pool (string constant pool) is a special area in Java's heap memory where unique string literals are stored 🗃️
- Both s1 and s2 below point to the same string object in the pool cuz they have exact same value 
```Java
String s1 = "burger";
String s2 = "burger";
```

### How does String Pool work?
- whenever we create a string literal (`"burger"`), JVM check if it already exists in the pool
  - if yes -> variable point to the existing object
  - it no -> create a new string object in the pool

### Why Java need String Pool?
- Java is object-oriented language, and every obj created with new goes on th eheap
  - Heap can easily fill up (too many objs, frequent GC)
- String is the most frequently used and most like to be duplicated type
  - e.g. database fields, config logs with many "admin" "OK" "123" ...
- Sting Pool is a special area in the heap dedicated to storing only one copy of each unique string literal -> so everyone shares the same obj
- Since pooled strings with the same value point to the same obj, we can use `==` to compare them

### String immunity
- once String is created, its value cannot be changed
    #### Why
- **Security**: if String is mutable, and everyone pointed to the same obj, once user changing the value would will change it for everyone else -> breaking the pool -> security risk
- **Hashing**: Strings are often used as keys in hash-based collections, if the value changes, the hash code would also changed

### `intern()`
```Java
String s1 = "burger"; 
String s2 = new String("burger"); 
String s3 = s2.intern();
```
1. `"burger"` literal is placed in the pool and s1 point to it
2. create new String obj in the heap (NOT the pool), even though already exists in the pool, and s2 point to the heap obj (NOT the pool)
3. `intern()` checks the string pool
   - if  `"burger"` already exists in the pool, it returns the reference  to the pooled `"burger"`
   - it didn't exist, it add `"burger"` to the pool and return that reference

## 5. Explain garbage collection? Explain types of garbage collection.
- Garbage Collection (GC) is the process of JVM auto frees memory by deleting objs that are no longer reachable
- prevents memory leaks and manage heap memory efficiently
### How does it work?
- JVM tracks objs on the eap
- If an obj cannot be reached by nay running thread (no variable pointes to it) -> considered "garbage"
- GC periodically scans for such unreachable objs and removes them

### Types of GC in Java
#### 1. Serial GC
- Use a single thread of all GC work
- suitable for: simple, single-threaded application
#### 2. Parallel GC
- Use multiple threads to speed up GC in multi-core systems
- Focus on maximizing application throughput
- Default in many JDK versions for server class machines
#### 3. Concurrent Mark Sweep GC
- Minimized GC pause times by doing most of the work concurrently with the application threads
- Designed for applications that need low latency
#### 4. Garbage First (G1) GC
- Splits the heap into regions, collects garbage in regions with most "garbage" first
- Predictable low pause times, good for large heap, server applications
- Default in modern JVMs (Java 9+)
#### 5. ZGC and Shenandoah (very low pause times)
- ZGC and Shenandoah are advanced collectors for large heap and cloud/server use, targeting ultal-low pause times

## 6. What are access modifiers and their scopes in Java?
- Access Modifiers in Java are keywords to define the visibility (access scope) of classes, methods, and variables
- Control where these memebers can be accessed
#### Access Modifiers
| Modifier    | Access Scope                                          | Use Case                                                                            |
|-------------|-------------------------------------------------------|-------------------------------------------------------------------------------------|
| Default     | Only within the same package                          | Allow classes in the same package to share details, but hide from outside packages. |
| private     | Only inside the same class                            | Hide details and enforce encapsulation.                                             |
| protected   | Same package + subclasses (even if in another package) | For inheritance—share with subclasses, but not general public.                      |
| public      | Everywhere (all classes, all packages)                 | API, utility classes, anything meant for broad use.                                 |

## 7. Explain final key word? (Filed, Method, Class)
### Final Variable
- A `final` variable means its value cannot be changed after it is assigned.
- **Purpose**: define constants, make it immutable, prevent modification.
- Often used for constant
```java
public static final String STR = "a";
STR = "b"; // ❌ Compilation error: cannot assign a value to final variable 'STR'
```

### Final Method
- A `final` method cannot be overridden by subclasses.
- **Purpose**: prevent override
- Often used when you want to lock the behavior in the parent class.
```Java
class Animal {
    public final void speak() {
        System.out.println("Animal speaks");
    }
}

class Dog extends Animal {
    // ❌ Compilation error: Cannot override the final method from Animal
    // public void speak() {}
}
```
### Final Class
- A `final` class cannot be inherited.
- Purpose: prevent inheritance, like Integer, String etc; + Make class immutable.
- Often used when Java designers don’t want anyone to change the behavior of a class (like Integer, String, Math).
```Java
public final class String {
    // The built-in Java String class is final, so no one can extend it.
}

// ❌ Compilation error: Cannot inherit from final class 'String'
class MyString extends String {}

```

## 8. Explan static keyword? (Filed, Method, Class). When do we usually use it?
- `static` makes a field, method, or inner class belong to the class itself, not to any particular obj (instance)
  - which means we can access it without creating an obj of that class
### 1. Static Field (Class Variable)
- Only one copy exists for the entire class, shared by all instances
- **Typical use:** Counters, constants, shared configuration
```Java
class Counter {
    static int count = 0; // static field

    Counter() {
        count++;
    }
}

public class Main {
    public static void main(String[] args) {
        new Counter();
        new Counter();
        System.out.println(Counter.count); // output: 2
    }
}
```
### 2. Static Method (Class Method)
- can be called without creating an object
- can only access static fields or call other static methods directly
- cannot access non-static (instance) variables or methods directly
  - because static methods do not belong to any particular object
- **Typical Use Case:** 
  - utility or helper methods that don't depend on instance data, like `Math.max()`, `Collections.sort()`
  - also for methods that operate only on parameters and static variables

```Java
class MathUtils {
    public static int add(int a, int b) {
        return a + b;
    }
}

public class Main {
    public static void main(String[] args) {
        int sum = MathUtils.add(5, 3); // No need to create MathUtils object
        System.out.println(sum); // output: 8
    }
}
```
#### Without Static
- -> we must create an object to use it
```Java
MyClass obj = new MyClass();
obj.str; // If 'str' is not static
```
#### With Static
- -> we can access it directly, no object needed
```Java
public static String str = "xxx";
MyClass.str;
```

### 3. Static Inner Class
- static inner class doesn't have a reference to the outer class's object
- can be instantiated without an object of the outer class
- **Typical use**: When the inner class doesn’t need access to instance members of the outer class

```Java
class Outer {
    static class Inner {
        void show() {
            System.out.println("I'm a static inner class!");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Outer.Inner obj = new Outer.Inner(); // No need to create Outer instance
        obj.show(); // output: I'm a static inner class!
    }
}
```

### Static does not change memory layout
- static variable are stored once per class, not per object
- no matter how many instances created, there's only one copy of each static variable

### Initialization Order:
- static variables and static blocks are initialized first, when the class is loaded
- then when we create an object, the constructor runs
  - static -> constructor

## 9. What is the differences between overriding and overloading?

| Feature               | Method Overloading                     | Method Overriding                                       |
|-----------------------|----------------------------------------|---------------------------------------------------------|
| When it happens       | Compile time                           | Runtime                                                 |
| Performance           | Faster (resolved at compile time)      | Slower (resolved at runtime)                            |
| Where used            | Within the same class                  | In superclass–subclass (inheritance) relationship       |
| Arguments             | Must be different (number or type)     | Must be the same (same method signature)                |
| Return type Different | ✅ Can be different                     | ❌ Must be the same                                      |
| Private/final methods | ✅ Can be overloaded                    | ❌ Cannot be overridden                                  |
| Purpose               | Increase code readability/flexibility  | Provide specific implementation for a superclass method |
| Example usage         | Same method name, different parameters | Subclass changes/extends base class behavior            |

### Overloading
```Java
void print(int a) {...}
void print(String s) {...} // Overloaded
```
### Overriding
```Java
class Animal {
    void speak() {...}
}
class Dog extends Animal {
    @Override
    void speak() {...} // Overridden
}
```

## 10. Explain how Java defines a method signature, and how it helps on overloading and overriding.

### Method Signature is defined as:
- method name + parameter list (number, order, types of parameters)

### Helps on Overloading:
- **Overloading** allows multiple methods with the same name **but different signatures** in the same class
- Compiler uses the method signature to determine which method to call at compile time
```Java
void add(int a, int b)       // Signature: add(int, int)
void add(double a, double b) // Signature: add(double, double)
```
### Helps on Overriding:
- **Overriding** requires the subclass method to have **exactly the same signature** as the method in its superclass
- Only then will the subclass method override the superclass method at runtime
- Return type must also be the same or subtype
  - if the parent method returns Animal, the child can return Dog.

```Java
class Animal {
    void speak(String msg) { ... }    // Signature: speak(String)
}
class Dog extends Animal {
    @Override
    void speak(String msg) { ... }    // Must have same signature to override
}

class Parent {
    Animal getAnimal() { return new Animal(); }
}

class Child extends Parent {
    @Override
    Dog getAnimal() { return new Dog(); } // Covariant return type
}
```
### Summary
| Concept     | Signature must be: | How Java uses                    |
|-------------|--------------------|----------------------------------|
| Overloading | Different          | Compiler chooses at compile time |
| Overriding  | Same               | JVM chooses at runtime           |

## 11. What is the differences between super and this?
### this
- Refers to the **current object** (the instance of the class where the code if running)
- **Common Use:**
  - accessing the current object's fields or methods when there's a naming conflict
  - calling another constructor within the same class

```Java
class Person {
    private String name;

    public Person(String name) {
        this.name = name; // 'this' refers to the current object
    }

    public void print() {
        System.out.println(this.name); // Access current object's field
    }
}
```

### super
- Refer to the **parent (superclass) object**
- Common Use:
  - accessing fields or methods of the superclass that are hidden or overridden in the subclass
  - calling the superclass constructor
```Java
class Animal {
    String name = "animal";
    void speak() { System.out.println("Animal speaks"); }
}

class Dog extends Animal {
    String name = "dog";
    void speak() { System.out.println("Dog barks"); }

    void print() {
        System.out.println(this.name);  // "dog"
        System.out.println(super.name); // "animal"
        super.speak(); // Calls Animal's speak() -> accessing methods of the superclass
    }

    Dog() {
        super(); // Calls the parent class constructor
    }
}
```
### Summary 
| Keyword | Refers to         | Typical Use                                     |
|---------|-------------------|-------------------------------------------------|
| this    | Current object    | Access own members, call own constructor        |
| super   | Superclass object | Access parent members, call superclass constructor|

## 12. Explain how equals and hashCode work.
### `equals()`
- a method in `Object` class
- used to compare two objects for logical equality (check the contents in two objects are the same or not)
- by default, `equals()` check if two references point to the same object 
- can override `equals()` to compare object contents

```Java
Student s1 = new Student("1001");
Student s2 = new Student("1001");
Student s3 = s1;

System.out.println(s1.equals(s2)); // true, same content
System.out.println(s1 == s2); // false, different objects in memory
System.out.println(s1 == s3); // true, both point to the same object
```

### `hashCode()`
- a method in `Object` class
- returns an integer(hash code) value for the object
- used by hash-based collections like HashMap, HashSet to quickly locate objects
- default `hashCode() `is typically the memory address, but we should override it whenever ywe override `equals()`

### why `equals()` & `hashCode()` must be consitent
- if two objects are "equal" (`a.equals(b)` `return true`) -> their hash codes must also be equal (`a.hashCode() == b.hashCode()`)
- Otherwise, collections like HashMap or HashSet will not work correctly (objects may be “lost” or duplicated)
### why need to override `equals()` & `hashCode()`
Because when we use objects as **keys in a `HashMap`** or **elements in a `HashSet`**, Java needs to know:

> 🔍 "Are these two objects the same?"


But objects are **not compared by their content by default** — Java compares their **memory addresses** (`==`)
so in order o compare objects by content, we must override `equals()` and `hashCode()`

- `hashCode()` = tells you **which mailbox** to check (bucket index in the HashMap)
- `equals()` = checks **inside the mailbox** to find the exact letter

So:
1. `hashCode()` finds the right bucket
2. `equals()` checks if two objects are truly equal

```Java
class Student {
    int id;
    String name;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return this.id == other.id && this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

Student s1 = new Student(1001, "Alice");
Student s2 = new Student(1001, "Alice");

System.out.println(s1.equals(s2)); // true, if overridden; false, if not overridden

Set<Student> set = new HashSet<>();
set.add(s1);
System.out.println(set.contains(s2)); // true, if both methods overridden
```

## 13. What is the Java load sequence?
### 1. Class Loading
- JVM loads the class into memory from disk

### 2. Static Variable Initialization & Static Blocks
- All static variables and static blocks in the class are initialized/executed, in the order they appear in the code
- This initialization step happens only once when the class is loaded

### 3. Instance Variable Initialization
- Instance (non-static) variables are initialized to their default values (0, null, false, etc.)

### 4. Instance Initializer Blocks
- Any instance initializer block ({ ... } outside of methods/constructors) runs, in the order defined

### 5. Constructor Execution
- Constructor is called to finished creating the object
- If the class extends a parent class, the parent’s constructor and initialization steps are completed first (using `super()`)

```Java
class Demo {
    static { System.out.println("Static block -> only once"); }
    static int s = 1;

    { System.out.println("Instance initializer"); }
    int x = 10;

    Demo() {
        System.out.println("Constructor");
    }
}

public class Main {
    public static void main(String[] args) {
        Demo d1 = new Demo();
        Demo d2 = new Demo();
    }
}
/*
        output:
            Static block -> only once"
            Instance initializer
            Constructor
            Instance initializer
            Constructor
 */
```

## 14. What is Polymorphism ? And how Java implements it ?

**Polymorphism** is an OOP concept where one interface or reference can represent multiple actual forms or behaviors
> _the same method / object can behave differently depending on which class is implementing or extending it_


### Static Polymorphism (Compile-Time) — Method Overloading
- Definition: Multiple methods in the same class with the same name but different parameter lists.
- Decided at compile time (by the compiler).

```Java
class Printer {
    public void print(String msg) {
        System.out.println("String: " + msg);
    }

    public void print(int number) {
        System.out.println("Integer: " + number);
    }

    public void print(String msg, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println("Repeated: " + msg);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Printer p = new Printer();
        p.print("Hello");       // Output: String: Hello
        p.print(123);           // Output: Integer: 123
        p.print("Hi", 2);       // Output: Repeated: Hi (twice)
    }
}
```

### 2. Runtime Polymorphism (Dynamic Polymorphism)
- **Method Overriding:** A subclass provides its own implementation of a method declared in the parent class.
- The method to execute is chosen at runtime, based on the actual object type, not the reference type.
- Achieved via inheritance, interfaces, and method overriding.

```Java
class Animal {
    void speak() { System.out.println("Animal speaks"); }
}
class Dog extends Animal {
    @Override
    void speak() { System.out.println("Dog barks"); }
}
class Cat extends Animal {
    @Override
    void speak() { System.out.println("Cat meows"); }
}
public class Main {
    public static void main(String[] args) {
        Animal a1 = new Dog();
        Animal a2 = new Cat();
        
        a1.speak(); // output: Dog barks
        a2.speak(); // output: Cat meows
    }
}
```

## 15. What is Encapsulation ? How Java implements it? And why we need encapsulation?
**Encapsulation** is an OOP concept where we bundle data (fields) and methods that operate on that data together into a single unit (class)
> _Hiding the internal state of an object from the outside world and providing controlled access via methods_

### Java implementation
- **private fields**: variables in a class are marked as `private`, so they cannot be accessed directly from outside the class
- **public getter / setter methods**: to read / update these private fields, provide public methods -> getter / setter

```Java
class Student {
    private String name; // Private field

    public String getName() { // Getter
        return name;
    }
    public void setName(String name) { // Setter
        this.name = name;
    }
}
```

Outside code cannot access name directly, only through `getName()` and `setName()`

### Why need Encapsulation
- **Control access**: prevent outside code from changing data in unexpected ways
- **Protect integrity**: can add validation or logic in setters (like prevent negative age)
- **Hiding complexity**: internal implementation details are hidden, only the interface (methods) is visible
- **Easily maintain**: changes to internal data don't effect code that ses the class, as long as the interface stays the same

## 16. Compare interface and abstract class with use cases
### Abstract
- can have **abstract methods** (no body) and **concrete methods** (with body)
- can have **fields / instance variables** (state)
- can provide **constructor** for subclasses
- used for classes in a **closely related hierarchy** sharing common state and behavior
- **single inheritance** only (a class can extend only one abstract class)
> **Example**: all shapes have color and area calculation, so you make abstract class Shape with fields and methods shared by all shapes
```Java
// Abstract class with shared state and behavior
abstract class Shape {
    String color;

    // Constructor for all shapes
    Shape(String color) {
        this.color = color;
    }

    // Shared method for all shapes
    void displayColor() {
        System.out.println("Color: " + color);
    }

    // Abstract method: Each shape must implement its own area calculation
    abstract double getArea();
}

// Rectangle subclass
class Rectangle extends Shape {
    double width, height;

    Rectangle(String color, double width, double height) {
        super(color); // Call Shape constructor
        this.width = width;
        this.height = height;
    }

    @Override
    double getArea() {
        return width * height;
    }
}

// Circle subclass
class Circle extends Shape {
    double radius;

    Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    double getArea() {
        return Math.PI * radius * radius;
    }
}

public class Main {
    public static void main(String[] args) {
        Shape s1 = new Rectangle("Red", 4, 5);
        Shape s2 = new Circle("Blue", 3);

        s1.displayColor(); // Output: Color: Red
        System.out.println("Area: " + s1.getArea()); // Output: Area: 20.0

        s2.displayColor(); // Output: Color: Blue
        System.out.println("Area: " + s2.getArea()); // Output: Area: 28.274...
    }
}
```
- Shape holds the shared state (`color`) and shared methods (`displayColor()`).
- Each subclass (`Rectangle, Circle`) inherits the color property and provides its own implementation of` getArea()`.
- Use a Shape reference to point to any subclass—demonstrating **polymorphism**

### Interface
- all methods are **abstract by default** 
- **cannot have instance variables**
- **no constructors**
- used for **common behaviors** taht can be shared by unrelated classes
- **multiple inheritance**: a class can have multiple interfaces
> **Example**: different classes (Fish, Bird, Plane) all Flyable and Swimmable, even if they're not related in class hierarchy

```Java
// Define interfaces for capabilities
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

// Unrelated class: Fish
class Fish implements Swimmable {
    @Override
    public void swim() {
        System.out.println("Fish swims in water.");
    }
}

// Unrelated class: Bird
class Bird implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("Bird flies in the sky.");
    }
    @Override
    public void swim() {
        System.out.println("Bird swims in water.");
    }
}

// Unrelated class: Plane
class Plane implements Flyable {
    @Override
    public void fly() {
        System.out.println("Plane flies in the sky.");
    }
}

// Test usage
public class Main {
    public static void main(String[] args) {
        Flyable f1 = new Bird();
        Flyable f2 = new Plane();
        Swimmable s1 = new Fish();

        f1.fly();   // Output: Bird flies in the sky.
        f2.fly();   // Output: Plane flies in the sky.
        s1.swim();  // Output: Fish swims in water.

        // Demonstrate multiple abilities
        Bird bird = new Bird();
        bird.fly();  // Output: Bird flies in the sky.
        bird.swim(); // Output: Bird swims in water.
    }
}
```

- Fish, Bird, and Plane are not related by inheritance but can share behavior via interfaces
- Interfaces allow unrelated classes to have common abilities (`fly(), swim()`)
- This cannot do with abstract classes alone (Java only allows single inheritance, but multiple interfaces)

### Functionality
| Feature                | Interface                                                         | Abstract Class                                            |
|------------------------|-------------------------------------------------------------------|-----------------------------------------------------------|
| Default Method         | Since Java 8: can provide default implementations                 | ✅ Can provide both abstract and concrete methods          |
| Fields                 | ❌ Cannot have instance variables; <br/>✅ Only public static final | ✅ Can have instance variables (fields)                    |
| Constructors           | ❌                                                                 | ✅ Can have constructors for initializing fields           |
| State (fields)         | ❌ Cannot maintain state                                           | ✅  Can maintain state (instance variables)                |
| Multiple Inheritance   | ✅ Can implement multiple interfaces (supports multiple types)     | ✅ Can extend only one abstract class (single inheritance) |
| Access Modifiers       | Methods are implicitly public (Java 9+ can be private)            | ✅ Can be public, protected, or private                    |
| Use Case               | For unrelated classes to implement shared behavior                | For related classes sharing common code/state             |
| Inheritance Type       | Represents "can do" or "is able to" (e.g., Flyable)               | Represents "is-a" (e.g., Animal, Vehicle)                 |
| Static Methods         | Allowed since Java 8 (belong to interface)                        | Allowed (belong to the abstract class)                    |
| Instantiation          | ❌ Cannot be instantiated directly                                 | ❌ Cannot be instantiated directly (subclass only)         |
| Constructor Calling    | ❌ Cannot invoke interface constructor                             | Subclasses can call super() in constructors               |
| Suitability            | For shared behavior without a shared ancestor                     | For related classes with common behavior/state            |
| Flexibility            | More flexible (supports multiple inheritance)                     | Less flexible (single inheritance only)                   |

### Use Case
| Use Interface When:                                                                  | Use Abstract Class When:                                                    |
|--------------------------------------------------------------------------------------|-----------------------------------------------------------------------------|
| Define common behavior across **unrelated classes**                                  | Share common behavior and state across **related** classes                  |
| Need **multiple inheritance** (a class can implement multiple interfaces)            | Wanna provide d**efault/example implementation** for subclasses             |
| Defining a contract or behavior, and **don’t need to share** state or implementation | **Need to shar**e instance variables (state) or common initialization logic |
| Classes do not share a common ancestor, but need to guarantee certain abilities      | Class hierarchy is closely related and will benefit from code reuse         |