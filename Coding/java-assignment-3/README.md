**1. Write an example code to demonstrate three concepts of OOP: Encapsulation, Polymorphism, Inheritance (OOPConcept.java)**
- 1.1   Student.java
- 1.2   Animal.java, Cat.java, Dog.java
- 1.3   User.java, AdminUser.java
     
**2. What is wrapper data type class (e.g. Integer, Double) in Java? Why we need wrapper class**

In Java, **wrapper classes** are object representations of primitive data types. For example:
- `int` → `Integer`
- `double` → `Double`
- `char` → `Character`
- `boolean` → `Boolean`

These classes are part of the `java.lang` package.


Wrapper classes (like `Integer`, `Double`) are needed because:
- **Collections require objects** — primitives like `int` can’t be used in `List`, `Map`, etc.
- **They allow nulls**, unlike primitives, which is useful in database and form handling.
- **Provide utility methods** (e.g. `Integer.parseInt()`).
- **Autoboxing/Unboxing**: Java automatically converts between primitive and wrapper types.  
   
   ```java
   List<Integer> list = new ArrayList<>();
   list.add(5); // int 5 is automatically boxed to Integer
   int val = list.get(0); // Integer unboxed to int
   ```

**3. Difference Between HashMap and Hashtable in Java**
| Feature        | HashMap                          | Hashtable                       |
|----------------|----------------------------------|---------------------------------|
| Thread Safety	 | **Not Synchronsized**, not thread safe | **Synchronized**, thread-safe|
| Performance    | Faster in single-threaded apps   | Slower due to synchronization   |
| Null Keys/Values | **Allows one null key** and multiple null values | **Does not allow** null keys or values |
| Legacy Status  | Modern, preferred in new code    | Legacy class, rarely used now   |
| Synchronization | Must be added manually if needed | Built-in, but less efficient    |


- Use `HashMap` for better performance in non-concurrent code. If thread safety is required, consider using `ConcurrentHashMap` instead of `Hashtable`.

```java
import java.util.*;

public class MapComparison {
    public static void main(String[] args) {
        // HashMap allows nulls
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(null, "nullKey");
        hashMap.put("a", null);
        System.out.println("HashMap: " + hashMap);

        // Hashtable does NOT allow nulls
        Map<String, String> hashtable = new Hashtable<>();
        hashtable.put("x", "value");
        hashtable.put("y", "another");

        // hashtable.put(null, "fail");      // Throws NullPointerException
        // hashtable.put("z", null);         // Throws NullPointerException

        System.out.println("Hashtable: " + hashtable);
    }
}

```

**4. What is String pool in Java and why we need String pool? Explain String immunity.**

- The **String Pool (String Constant Pool)** is a special memory area in the Java heap memory where the Java Virtual Machine(JVM) stores `String literals`. This improves memory usage by reusing String objects with the same value.
- When a string literal is created, the JVM checks the **String Constant Pool** in the heap.
	- If an identical string already exists, it returns a **reference** to the existing object.
	- If not, a **new object** is added to the pool.

```java
String a = "hello";
String b = "hello";       // Both a and b point to the same object in the pool
System.out.println(a == b);  // true


String c = new String("hello");  // Created in heap, not pooled
System.out.println(a == c);      // false
```
**Why we need?**
- **Memory efficiency**: reuses common string objects, significantly reducing memory usage in large applications 
- **Performance**: Interned strings can be compared using `==` (reference comparison), which is faster than character-by-character comparison 

**String immunity**: Java Strings are **immutable**, meaning that the content cannot be changed after creation.
- **Thread Safety**: Immutable objects can be safely shared across threads without synchronization since their state cannot change
- **Security**: Prevent accidental or malicious changes to sensitive values like username, password, or file path.
- **Caching**: Immutable string can be reused and cached without risk of modification.
- **String Pool Support**: String immutability is essential for the String pool's functionality. Since multiple references can point to the same String object in the pool, it is crucial that the value of the String cannot be altered.

```java
String s1 = "data";
String s2 = s1.replace("da", "me");  // Creates a new string "meta"
System.out.println(s1);  // "data" remains unchanged
System.out.println(s2);  // "meta"
```

**5. What is Garbage Collection in Java?**

**Garbage Collection** is an automatic memory management process where the JVM identifies and removes objects that are no longer in use in memory, freeing up space and prevent memory leaks.

**Types of Garbage Collection in Java**
- **Serial GC**: Use a single thread for garbage collection; best suited for small applications.
- **Parallel GC**: Use multiple threads for minor collections; best for high throughput applications.
- **Concurrent Mark Sweep(CMS)**: minimize pause times by performing most GC work concurrently. It requires more CPU resources.
- **G1 (Garbage First)**: divides the heap into regions and collects garbage in the most efficient regions first. It aims to balance throughput and pause times, making it suitable for large applications. Java 11+ uses G1.

**6. What are access modifiers and their scopes in Java?**

Access modifiers in Java define the visibility or accessibility of classes, methods, and variables.

| Modifier     | Class | Package | Subclass | Other |
|--------------|:-----:|:-------:|:--------:|:-----:|
| `public`     |  ✅   |   ✅    |    ✅    |  ✅   |
| `protected`  |  ✅   |   ✅    |    ✅    |  ❌   |
| *(default)*  |  ✅   |   ✅    |    ❌    |  ❌   |
| `private`    |  ✅   |   ❌    |    ❌    |  ❌   |

**`public`**: Accessible from anywhere.
```java
    public class MyClass {
        public int publicVariable;
        public void publicMethod() {
            // ...
        }
    }
```
- **`protected`**: Accessible within the same package and by subclasses.
		Often used for members that should be accessible to subclasses but not to arbitrary classes.
```java
     class MyClass {
        protected int protectedVariable;
        protected void protectedMethod() {
            // ...
        }
    }
```
- **(default)**: No modifier—accessible only within the same package. No keyword is used.
		Useful for members that are internal implementation details and not intended for outside access.
```java    
    class MyClass {
        int defaultVariable;
        void defaultMethod() {
            // ...
        }
    }
```
- **`private`**: Accessible only within the same class.
	Used to encapsulate data and methods, hiding them from external access.
```java
    class MyClass {
        private int privateVariable;
        private void privateMethod() {
            // ...
        }
    }
```
**7.Explain final key word? (Field, Method, Class)**

The `final` keyword in Java is used to create constants, prevent method overriding, and stop class inheritance.
- **Final variables(fields):**	When a variable is declared final, its value cannot be changed after initialization.
- **Final Methods:** A final method cannot be overridden by subclasses
- **Final class:** A final class cannot be extended(subclassed)
```java
public class FinalExample {

    // Final variable (constant)
    final int MAX_VALUE = 100;

    // Final method
    final void printMessage() {
        System.out.println("This is a final method.");
    }
}

// Final class
final class ImmutableClass {
    private final String name;

    public ImmutableClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

**8.Explan static keyword? (Field, Method, Class). When do we usually use it?**

- **Static Fields(Variables)**
  	- Static variables are associated with the class, not with objects.
        - Shared among all objects of the class.
  	- Accessed directly using the class name
	- Useful for constants or data shared by all instances
```java
public class Example {	
   static int count = 0; // Static field

   public Example() {
        count++;
   }

   public static void main(String[] args) {
       Example obj1 = new Example();
       Example obj2 = new Example();
       System.out.println(Example.count); // Output: 2
   }
}
```
- **Static Methods**
	- A static method belongs to the class and **can be called without creating an object**. 
	- Cannot access non-static members of the class.
	- Often used for utility methods that don't depend on instance state (e.g. Math.pow()).
```java
class Utils {
    static int square(int x) {
        return x * x;
    }
}

// Usage:
int result = Utils.square(4);
```
- **Static Class**
	- Can be declared within another class.
	- Do not require an instance of the outer class to be created.
	- Can access static members of the outer class directly.
        - Useful for grouping related classes or providing utility classes
```java
public class OuterClass {
    static class NestedClass { // static nested class
        void display() {
            System.out.println("Nested static class display method");
        }
    }

    public static void main(String[] args) {
        OuterClass.NestedClass nested = new OuterClass.NestedClass();
        nested.display(); // Output: Nested static class display method
    }
}
```

**9.What is the differences between overriding and overloading?**

- Overriding and overloading are both forms of polymorphism in OOP, but they serve as different purposes:
- **Overloading**
  	- Happens within the same class
	- Same method name but different parameter list
	- Happens at compile time
	- Different method signature (name and parameters)
```java
public class Printer {

    /* ------------ Printer class ------------ */
    static class Printer {
        void print(String s) {                 // original method
            System.out.println(s);
        }
        void print(int n) {                    // overloaded method
            System.out.println(n);
        }
    }

    /* ------------- main -------------------- */
    public static void main(String[] args) {
        Printer p = new Printer();
        p.print("Hello, world!");  // calls print(String)
        p.print(42);               // calls print(int)
    }
}
```
- **Overriding**
	- Happen between a superclass and subclass
	- Same method name and same parameter list
	- Redefines a method from the parent class
	- Happens at runtime
```java
public class AnimalClass {

    /* ------------ base class ------------ */
    static class Animal {
        void sound() {                         // original method
            System.out.println("Some sound");
        }
    }

    /* ----------- subclass --------------- */
    static class Dog extends Animal {
        @Override
        void sound() {                         // overridden method
            System.out.println("Bark");
        }
    }

    /* --------------- main --------------- */
    public static void main(String[] args) {
        Animal generic = new Animal();
        Animal rover   = new Dog();   // up-cast → polymorphism

        System.out.print("Animal: "); generic.sound();  // Some sound
        System.out.print("Dog   : "); rover.sound();    // Bark
    }
}
```

**10. Explain how Java defines a method signature, and how it helps on overloading and overriding.**

**Method Signature** is defined by method name + ordered list of parameter types (excluding return type, access modifiers, exceptions)
- In **overloading**, Java allows multiple methods with the same name if there parameter type list differs.
The return type can be the same or different, but it does not count in the signature.
- In **overriding**, when a subclass overrides a method, it must match the **exact same method signature** as in the parent class.
```java
public class MethodSignature {

    // Overloading: same name, different parameters
    void run(String animal) {
        System.out.println("Running with: " + animal);
    }

    void run(String animal, int age) {
        System.out.println("Running with: " + animal + ", age: " + age);
    }

    public static void main(String[] args) {
        MethodSignature demo = new MethodSignature();
        demo.run("Dog");
        demo.run("Cat", 3);
        
        Parent p = new Parent();
        Parent c = new Child();
        p.greet("Ja");
        c.greet("Angela");
    }
}
// Overriding: same parameters
class Parent {
    void greet(String name) {
        System.out.println("Hello from Parent, " + name);
    }
}

class Child extends Parent {
    @Override
    void greet(String name) {
        System.out.println("Hello from Child, " + name);
    }
}
``` 

**11. What is the differences between super and this?**

In Java, `this` and `super` refer to different objects. `this` refers to the **current object** (the instance of the class where the code is executing). It is used to access current class's fields or methods and call another constructor in the same class. `super` refers to **the parent(superclass) object**. It is used to access superclass's fields or methods and call the superclass constructor.
```java
public class SuperThis {

    /* -------- superclass -------- */
    static class Parent {
        String name = "Parent field";

        void show() {
            System.out.println("Parent.show()");
        }
    }

    /* -------- subclass --------- */
    static class Child extends Parent {
        String name = "Child field";

        @Override
        void show() {
            // `this` refers to the Child object
            System.out.println("this.name  = " + this.name);   // Child field
            // `super` refers to Parent’s version
            System.out.println("super.name = " + super.name);  // Parent field

            super.show();                // call Parent’s method
            System.out.println("Child.show()");
        }
    }

    /* -------- entry point ------- */
    public static void main(String[] args) {
        Parent c = new Child();
        c.show();
    }
}
```

**12. Explain how equals and hashCode work.**

- In Java, `equals()` and `hashCode()` are closely linked methods used to compare objects and store them efficiently in collections like `HashMap`, `HashSet`, and `Hashtable`.
- `hashCode()` is used to generate an integer hash code for an object. It is used to determine which bucket an object belongs to in a hash-based collection.

- Relationship: If two objects are equal according to `equals()`, they must return the same hash code from `hashCode()`.
	       But if two objects have the same `hashCode()`, they may not necessarily be `equals()`.

**equals()**: The `equals()` method determines whether two objects are considered "equal" based on their content or state, not just their memory addresses.
```java
public class EqualsHashCode {
    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person p = (Person) obj;
            return age == p.age && name.equals(p.name);
        }

        @Override
        public int hashCode() {
            return 31 * name.hashCode() + age;
        }
    }

    /* -------- Deliberately bad hash for collision demo -------- */
    static class PersonBad extends Person {
        PersonBad(String n, int a) { super(n, a); }

        @Override
        public int hashCode() { return 42; }   // every instance hashes to 42
    }

    public static void main(String[] args) {
        /* 1. equal ⇒ same hashCode */
        Person p1 = new Person("Alice", 30);
        Person p2 = new Person("Alice", 30);
        System.out.println("p1.equals(p2)  = " + p1.equals(p2));          // true
        System.out.println("p1.hashCode()  = " + p1.hashCode());
        System.out.println("p2.hashCode()  = " + p2.hashCode());          // identical

        /* 2. same hashCode does NOT imply equals (collision) */
        PersonBad x = new PersonBad("Bob", 25);
        PersonBad y = new PersonBad("Carol", 40);
        System.out.println("\nx.equals(y)   = " + x.equals(y));           // false
        System.out.println("x.hashCode()   = " + x.hashCode());           // 42
        System.out.println("y.hashCode()   = " + y.hashCode());           // 42 (same!)
    }
}
```

**13. What is the Java load sequence?**

When we run Java program, the JVM follows a specific **load and execution sequence** for classes:
- **Class loading (from .class files)** : The class loader loads the `.class` bytecode into memory. It then creates a binary stream of data from the class file, parses it, and creates an instance of `java.lang.Class`.
- **Linking**: Verification: checks bytecode format is valid or safe
	      Preparation: allocates memory for static variables and initializes them into default values
	      Resolution: converts symbolic references (like class names) into direct memory references 
- **Initialization**: Static blocks and static variables are initialized in the order they appear. 
		     This is where static variables are assigned their explicit values.
```java
// The JVM loads Demo's bytecode the first time running java Demo
class Demo {
    static int x = initX();	// Memory is reserved for static variables(x) and they get their default value (0 for int).
    static {
        System.out.println("Static block running");
    } 

    static int initX() {
        System.out.println("Static method called");
        return 42;
    }

    public static void main(String[] args) {
        System.out.println("Main method: x = " + x);
	// The static method executes first because it appears before the static block.
	// The static block runs next.
	// Finally, main runs after all static initialization is complete.
    }
}
```
					  
**14. What is Polymorphism ? And how Java implements it ?**

**Polymorphism** in Java means **"many forms"**, where the same method or objects behaves differently based on the context. 
- **Method Overloading(Compile-time Polymorphism)**: multiple methods with the same name but **different parameter lists.** 
```java
public class Printer {

    /* ------------ Printer class ------------ */
    static class Printer {
	void print(String s){
	    System.out.println(s);		// original method
	}
	void print(int n){			// overloaded method
	    System.out.println(n);
	}
    }
	
    /* ------------- main -------------------- */
    public static void main(String[] args) {
        Printer p = new Printer();
        p.print("Hello, world!");  // calls print(String)
        p.print(42);               // calls print(int)
    }	
}	
```
- **Method Overriding(Runtime Polymorphism)**: a subclass provides a **specific implementation** of a method defined in its superclass.
```java
public class AnimalClass {

    /* ------------ base class ------------ */
    static class Animal {
        void sound() {                         // original method
            System.out.println("Some sound");
        }
    }

    /* ----------- subclass --------------- */
    static class Dog extends Animal {
        @Override
        void sound() {                         // overridden method
            System.out.println("Bark");
        }
    }

    /* --------------- main --------------- */
    public static void main(String[] args) {
        Animal generic = new Animal();
        Animal rover   = new Dog();   // up-cast → polymorphism

        System.out.print("Animal: "); generic.sound();  // Some sound
        System.out.print("Dog   : "); rover.sound();    // Bark
    }
}
```

**15. What is Encapsulation ? How Java implements it? And why we need encapsulation?**

**Encapsulation**: bundle data(fields) and methods(functions) to operate data into a single unit(a class). It restricts direct access to some of the object's components using `private` access modifier.
- Java implementation: 1. Declare fields as `private` (hides internal data from outside classes)
		       2. Provide `public` getter/setters - controlled access to read or modify fields
```java
class Student {
    private int grade;  // field is hidden from outside

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        if (grade >= 0 && grade <= 100)
            this.grade = grade;
        else
            System.out.println("Invalid grade");
    }
}
```
- Why we need? 
- Protect data integrity (restrict invalid values)
- Data hiding and security 
- Improve reusability and modularity (change internal logic without breaking external code)
- Simplify usage for external code (hide internal complexity)

**16. Compare interface and abstract class with use cases.**

| Feature     | Interface | Abstract class | 
|--------------|:-----:|:-------:|
| Multiple Inheritance     |  Yes   |   No    |
| Constructor  |  No   |   Yes    |  
| Method Implementation |  Default/static methods only  |   Can have concrete methods    |   
| Fields    |  Only `public static final`   |   Can have instance variables   |  
| Example | `Comparable`,`Runnable` | `HttpServlet`,`AbstractList` |
- Use **interface** when: you need to define **shared behavior** across unrelated classes.
		         you want **multiple inheritance of types** (Java doesn't allow multiple class inheritance, but allows multiple interfaces).
- Use **Abstract Class** when: class are **closely related** and share code.
			      you want to **provide default behavior** but allow overriding.
			      you need to define **non-static** fields, constructor or access modifiers other than `public`

```java
// Interface - pure contract
interface Flyable {
    void fly();
}

class Bird implements Flyable {
    public void fly() {
        System.out.println("Bird flies");
    }
}

// Abstract class -partial implementation
abstract class Animal {
    String name;

    public Animal(String name) {
        this.name = name;
    }

    abstract void makeSound();

    void sleep() {
        System.out.println(name + " is sleeping");
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    public void makeSound() {
        System.out.println("Woof");
    }
}

```
