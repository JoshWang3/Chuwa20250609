### 1. Write up Example code to demonstrate the three foundmental concepts of OOP.  
1. Encapsulation
```java
// Hide internal data and provide controlled access

class Student {
    private String name;
    private int age;
    private double gpa;
    
    public Student(String name, int age) {
        this.name = name;
        this.age = age > 0 ? age : 18;
        this.gpa = 0.0;
    }
    
    // Controlled access to private fields
    public void setGPA(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        }
    }
    
    public double getGPA() {
        return gpa;
    }
    
    public String getName() {
        return name;
    }
}
```
2. Polymorphism
```java
// Child class inherits from parent class

class Shape {
    protected String color;
    
    public Shape(String color) {
        this.color = color;
    }
    
    public void display() {
        System.out.println("This is a " + color + " shape");
    }
}

class Circle extends Shape {
    private double radius;
    
    public Circle(String color, double radius) {
        super(color); // Call parent constructor
        this.radius = radius;
    }
    
    @Override
    public void display() {
        System.out.println("This is a " + color + " circle with radius " + radius);
    }
    
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private double width, height;
    
    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }
    
    @Override
    public void display() {
        System.out.println("This is a " + color + " rectangle " + width + "x" + height);
    }
    
    public double getArea() {
        return width * height;
    }
}
```
3. Inheritance
```java
// Same interface, different implementations

abstract class Pet {
    protected String name;
    
    public Pet(String name) {
        this.name = name;
    }
    
    public abstract void makeSound(); // Must be implemented by subclasses
    
    public void eat() {
        System.out.println(name + " is eating");
    }
}

class Dog extends Pet {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + ": Woof!");
    }
}

class Cat extends Pet {
    public Cat(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + ": Meow!");
    }
}
```

### 2. What is wrapper data type classes (e.g. Integer, Double) in Java and why we need wrapper class?  
(1) Use with Collections: Java collections like ArrayList, HashMap can only store objects, not primitives.
(2) Wrapper classes provide Utility Methods   
**int -- Integer**
```java
Integer.parseInt("123");          // convert string to primitive int
Integer.valueOf("123");           // convert string to Integer object
Integer.toString(123);            // convert int to string
Integer.compare(5, 3);            // compare two ints
Integer.MAX_VALUE;                // maximum value of int
Integer.MIN_VALUE;                // minimum value of int
Integer.bitCount(7);              // count number of 1s in binary representation

```
**double -- Double**  
```java
Double.parseDouble("3.14");       // convert string to primitive double
Double.valueOf("3.14");           // convert string to Double object
Double.toString(3.14);            // convert double to string
Double.isNaN(0.0 / 0);            // check if value is NaN
Double.isInfinite(1.0 / 0);       // check if value is infinite
Double.compare(3.14, 2.71);       // compare two doubles
Double.MAX_VALUE;                 // maximum value of double
Double.MIN_VALUE;                 // smallest positive non-zero double

```
**float -- Float**
```java
Float.parseFloat("3.14f");        // convert string to primitive float
Float.valueOf("3.14");            // convert string to Float object
Float.isNaN(0.0f / 0);            // check if value is NaN
Float.isInfinite(1.0f / 0);       // check if value is infinite
Float.compare(1.1f, 1.0f);        // compare two floats
Float.MAX_VALUE;                  // maximum value of float
Float.MIN_VALUE;                  // smallest positive non-zero float

```
**long -- Long**
```java
Long.parseLong("123456789");      // convert string to primitive long
Long.valueOf("123456789");        // convert string to Long object
Long.toString(123456789L);        // convert long to string
Long.compare(10L, 20L);           // compare two longs
Long.MAX_VALUE;                   // maximum value of long
Long.MIN_VALUE;                   // minimum value of long

```
**short -- Short**
```java
Short.parseShort("100");          // convert string to primitive short
Short.valueOf("100");             // convert string to Short object
Short.toString((short)100);       // convert short to string
Short.compare((short)1, (short)2); // compare two shorts
Short.MAX_VALUE;                  // maximum value of short
Short.MIN_VALUE;                  // minimum value of short

```
**byte -- Byte**
```java
Byte.parseByte("10");             // convert string to primitive byte
Byte.valueOf("10");               // convert string to Byte object
Byte.toString((byte)10);          // convert byte to string
Byte.compare((byte)1, (byte)2);   // compare two bytes
Byte.MAX_VALUE;                   // maximum value of byte
Byte.MIN_VALUE;                   // minimum value of byte

```
**char -- Char**
```java
Character.isDigit('5');           // check if character is a digit
Character.isLetter('a');          // check if character is a letter
Character.toUpperCase('a');       // convert character to uppercase
Character.toLowerCase('A');       // convert character to lowercase
Character.isWhitespace(' ');      // check if character is whitespace
Character.isUpperCase('A');       // check if character is uppercase
Character.isLowerCase('b');       // check if character is lowercase

```
**boolean -- Boolean**
```java
Boolean.parseBoolean("true");     // convert string to primitive boolean
Boolean.valueOf("false");         // convert string to Boolean object
Boolean.toString(true);           // convert boolean to string
Boolean.TRUE;                     // constant for Boolean true
Boolean.FALSE;                    // constant for Boolean false

```
(3) Support for Generics: Java generics work with reference types only.
(4) Nullable values: Primitive type variable can't be null, but wrapper classes can, which is helpful when handling optional or missing values.  
- Why can't primitive types be null?  
Because primitive types are not objects — they occupy a fixed space in memory and must always hold a concrete value. If null were allowed, Java would have to introduce pointer semantics for these non-object types, which would go against its design goals of performance and type safety.  
Primitive types (like int, boolean) do not have pointer semantics — their variables store the actual value directly, not a reference to an address.
- What is "pointer semantics" 指针语义?  
Pointer semantics means that a variable doesn't directly store the value itself, but instead holds a reference (i.e., a memory address) pointing to where the actual data is located. The program accesses the real value indirectly through this reference.  
- Why doesn't Java give primitive types pointer semantics?  
  Primitive values are stored directly on the stack or inside arrays, without requiring heap allocation. Since there's no reference (or pointer) overhead, this makes them faster and more lightweight. So, if Java were to allow something like int to be null, it would mean the compiler and runtime must treat int as a reference under the hood. In that case, int would no longer be a pure value type — it would effectively adopt pointer semantics. That would blur the line between primitive types and object types in Java, and conflict with the language’s original design philosophy.  
### 3. What is the difference between HashMap and HashTable?  
| Feature                  | `HashMap`                                        | `Hashtable`      |
|--------------------------|--------------------------------------------------|------------------|
| Thread-safe              | **No**                                           | Yes              |
| Performance              | **Faster**  <br/>because it is not synchronized. | Slower           |
| Allows null key/value    | **Yes / Yes**                                    | No / No          |
| Part of Collections API? | **Yes**                                          | No               |

### 4. What is String pool in Java and why we need String pool? Explain String Immutability.  
（1）Definition: The String pool  is a special area in the Java heap memory where unique String literals are stored.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;When a String is created as a literal (e.g., "hello"), the JVM checks the pool:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(a) If the same literal already exists, it reuses the reference.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(b) If not, it creates a new String and puts it in the pool.   
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;This avoids creating multiple identical String objects, which **saves memory and improves performance**.  
&nbsp;&nbsp;&nbsp;(2) String immutability means that once a String object is created, its value cannot be changed.
```java
String s = "hello";
s.concat("kitty"); // returns a new string; s is still "abc"
```
### 5. Explain garbage collection? Explain types of garbage collection.  
##### (1) Definition:  
Garbage Collection (GC) is the **automatic process** by which the Java Virtual Machine (JVM) reclaims memory by **identifying and removing objects that are no longer reachable** in the program.
This prevents memory leaks and manual memory management, making Java a **memory-safe** language.  
```java
public void createObject() {
    String temp = new String("hello");
    // temp is only accessible within this method; once it ends, temp is unreachable → eligible for GC
}
```
###### How Does GC Work?  
The JVM tracks object references. If **no references point to an object, it's considered “garbage” and can be removed.**  
Garbage collection usually happens in the **heap** memory.  
Java uses "generational collection", meaning objects are grouped by age:
- Young Generation: new objects  
- Old (Tenured) Generation: long-lived objects  
- Metaspace: stores class metadata (since Java 8)  

###### GC Phases (for most collectors)  
- Mark: Identify reachable objects.
- Sweep: Remove unreachable objects.
- Compact (optional): Rearranging remaining objects to eliminate fragmentation.  

##### (2) Type of Garbage Collectors  

  | Garbage Collector               | Description                                                      |
  |--------------------------------|------------------------------------------------------------------|
  | **Serial GC**                  | Single-threaded, best for small apps with limited CPU            |
  | **Parallel GC**                | Multi-threaded, **default in many JVMs**, good throughput         |
  | **CMS (Concurrent Mark Sweep)**| Low pause time, deprecated in Java 9+                             |
  | **G1 GC (Garbage First)**      | Splits heap into regions, **balances pause time and throughput** |
  | **ZGC** (Java 11+)             | Ultra-low pause time, scalable, experimental in Java 11           |
  | **Shenandoah** (Java 12+)      | Like ZGC, low pause time, RedHat-sponsored                        |

### 6. What are access modifiers and their scopes in Java?  
(1) private: The most restrictive modifier: accessible **only within the same class**. Used for encapsulating implementation details, internal fields, and helper methods.
(2) protected: Accessible **within the same package**, and also **in subclasses from other packages**. Frequently used in **inheritance scenarios** to allow subclass access but still limit external access.
(3) default: If no modifier is specified, the member is accessible **only within the same package**. Not accessible from outside the package, even in subclasses. Good for internal helper classes or components.
(4) public: The member is accessible from **anywhere (any class, any package)**. Commonly used for APIs, public methods, constants, etc.

### 7. Explain final keyword? (Field, Method, Class)  
final keyword in Java is a non-access modifier that can be applied to variables (fields), methods, and classes. It is used to indicate that something cannot be changed after its initial definition.
- final Variable:  once a final variable is assigned a value, it cannot be changed
- final Method: cannot be overridden by subclasses.
```java
class Parent {
    public final void show() {
        System.out.println("Final method");
    }
}

class Child extends Parent {
    // public void show() {} // Cannot override final method
}
```
- final Class: **NO extends**. cannot be extended, prevent inheritance

### 8. Explain static keyword? (Field, Method, Class). When do we usually use it?  

Static Keyword Definition: is a **non-access modifier** used to indicate that a field, method, or nested class **belongs to the class itself**, rather than to instances of the class.


Static Keyword Characteristics:
- Static variables and methods **use memory only once when the program runs**, and this **memory is shared by all the objects of the class**.
- We **do not need to create objects of the class to use static methods**.
- We can **call static members using the class name** directly.
- Static members **belong to the class, not to any specified object**.
- Static members **can not access non-static members**.
- Static methods **cannot be overridden in subclasses because they belong to the class, not to an object**.
- When a member is declared static, it **can be accessed before any objects** of its class are created, and **without reference to any object**.


Apply static keywords with variables, methods, blocks, and nested classes:    
(1) Static Variables (Fields)
  - Declared at class level and shared among all instances
  - Only one copy exists in memory, acting as a class-wide global variable.
  - Used for constants, shared data like counters, and to optimize memory usage.
  - Declared as static, and typically initialized either at declaration or inside a static block  
```java
public class Counter {
    static int count = 0; // shared among all instances

    public Counter() {
        count++; // each time object is created, count increases
    }

    public static void main(String[] args) {
        new Counter();
        new Counter();
        new Counter();
        System.out.println("Total count: " + Counter.count); // Output: 3
    }
}
```

(2) Static Methods
- Can be called without creating an object (ClassName.methodName()).
- Can only access other static variables or static methods – cannot refer to instance-level members 
- Cannot be overridden in subclasses; instead, they are hidden by similarly named static methods in derived classes .
```java
public class MathUtil {
    public static int square(int x) {
        return x * x;
    }

    public static void main(String[] args) {
        int result = MathUtil.square(5); // No need to create object
        System.out.println("Square of 5: " + result); // Output: 25
    }
}

```

(3) Static Blocks
- Declared using static { ... }.
- Executed once when the class is loaded by the JVM 
- Used to initialize static variables or to perform class-wide setup like registering JDBC drivers
```java
public class DatabaseConfig {
    static String url;

    static {
        // static block runs once when class is loaded
        System.out.println("Initializing database URL...");
        url = "jdbc:mysql://localhost:3306/mydb";
    }

    public static void main(String[] args) {
        System.out.println("Database URL: " + url);
    }
}

```

(4) Static Nested Classes
- Nested inside another class with static keyword.
- Behave like top-level classes but can be accessed using **OuterClass.NestedClass**
- **Do not require an instance of the outer class** and cannot access its non-static members directly
```java
public class Outer {
    static class Inner {
        void greet() {
            System.out.println("Hello from static nested class!");
        }
    }

    public static void main(String[] args) {
        Outer.Inner obj = new Outer.Inner(); // No need for Outer instance
        obj.greet();
    }
}

```

### 9. What is the difference between overriding and overloading?  
| Feature          | **Overriding**                                  | **Overloading**                                     |
|------------------|--------------------------------------------------|-----------------------------------------------------|
| Belongs to       | Inheritance (Parent–Child relationship)          | Same class (or within same hierarchy)               |
| Method name      | Must be same                                     | Must be same                                        |
| Parameters       | Must be same (type, number, order)               | Must be different (type or number/order)        |
| Return type      | Must be same or subtype (covariant)              | Can be different                                    |
| Access modifier  | Cannot be more restrictive                       | No such restriction                                 |
| Happens at       | Runtime (Dynamic Binding)                        | Compile-time (Static Binding)                       |
| Purpose          | Provide specific implementation in subclass      | Increase method flexibility (e.g. overload `print`) |

### 10. Explain how Java defines a method signature, and how it helps on overloading and overriding.  
```java
method signature： method name + the ordered list of parameter types.
void print(int a)              // signature: print(int)
void print(String s)           // signature: print(String)
int print(int a, String s)     // signature: print(int, String)
```
In method overloading, Java uses the method signature to distinguish between different methods with the same name but different parameter types or count.  
In method overriding, the method signature must be exactly the same as the method in the superclass.

### 11. What is the differences between super and this? 
| Keyword | Refers To                        | Common Use Cases                                                                 |
|---------|----------------------------------|----------------------------------------------------------------------------------|
| this  | Current **object** (same class)  | - Call current class methods/constructors<br>- Access instance variables<br>- Resolve naming conflict |
| super | Parent class (superclass) object | - Call parent class methods/constructors<br>- Access hidden variables or overridden methods |
```java
class Student {
    String name;

    Student(String name) {
        this.name = name; // differentiate between local and instance
    }
}
```
```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Lion extends Animal {
    void roar() {
        super.sound(); // calls Animal's version
        System.out.println("Lion Roars");
    }
}
```
### 12. Explain how equals and hashCode work.  
- equals() Method:   
Defined in java.lang.Object;  
Default behavior: compares memory addresses (reference equality);  
Often overridden to compare actual content (fields) of objects. 

- hashCode() Method:  
Defined in java.lang.Object;   
Returns an integer for the object, used in hash-based collections  
If two objects are equal according to equals(), they must return the same hashCode()  

#### Must be **overridden together**:
Suppose you write a class Person, and you override equals() to compare objects based on the name field. But you do not override hashCode(). Then the default Object.hashCode() will return a hash based on the memory address, which means that even if two objects are equal according to equals(), their hashCode() values will be different.

### 13. What is the Java load sequence?  
[reference](https://yuvrajscorpio.medium.com/order-of-execution-in-java-fd0ef5b10d70)
1. Class Loading  
The Java Virtual Machine (JVM) brings the class file into memory when the class is first accessed. This happens the moment the class is referenced, and it involves reading the corresponding .class file.

2. Static Block Execution  
Once the class is loaded, the JVM executes all static blocks in the order they are defined. These blocks run only once during class loading, and if multiple static blocks exist, they are executed one after another in sequence.

3. Static Variable Initialization  
After the static blocks have run, any static variables are initialized. These are processed in the order they appear in the class definition.

4. Main Method Execution  
If the class has a main method, program execution begins there. The JVM looks for a method that is public, static, returns void, and accepts a String[] argument—this serves as the entry point of the application.  

### 14. What is Polymorphism? And how Java implements it?  
Definition: Polymorphism in Java is one of the core concepts in object-oriented programming (OOP) that allows objects to behave differently based on their specific class type. The word polymorphism means having many forms, and it comes from the Greek words poly (many) and morph (forms), this means one entity can take many forms. In Java, polymorphism allows the same method or object to behave differently based on the context, specially on the project's actual runtime class.

Types of Polymorphism in Java:
- Compile-Time Polymorphism (Static)/Method overloading: this happens when multiple methods in the same class have the same name but different parameters.
```java
// Different Types of Arguments
class Helper {

    // Method 1 with 2 integer parameters
    static int Multiply(int a, int b)
    {
        // Returns product of integer numbers
        return a * b;
    }

    // Method 2 with same name but with 2 double parameters
    static double Multiply(double a, double b)
    {
        // Returns product of double numbers
        return a * b;
    }
}
```

- Runtime Polymorphism (Dynamic)/Method Overriding: it is a process in which a function call to the overridden method is resolved at Runtime.  This type of polymorphism is achieved by Method Overriding.  
Method overriding: subclass declares a method with the exact same signature (name, parameter list and compatible return type) as one in its superclass, but provides a **different implementation (method body)**.
```java
class Parent {

    void Print() {
        System.out.println("parent class");
    }
}
// Different implementation
class subclass1 extends Parent {

    void Print() { 
      System.out.println("subclass1"); 
    }
}

// Different implementation
class subclass2 extends Parent {

    void Print() {
        System.out.println("subclass2");
    }
}

```
### 15. What is Encapsulation? How Java implements it? And why we need encapsulation?  
Definition: bind the data members and methods into a single unit. Encapsulation is used to **hide the implementation part and show the functionality** for better readability and usability.  
Important points:  
(1) Provide Access Methods getter and setter: getter (display the data) and setter method ( modify the data) are used to provide the functionality to access and modify the data, and the implementation of this method is hidden from the user. 
(2) Better Code Management
(3) Simpler Parameter Passing
```java
public class EncapTest {
   private String name;
   private String idNum;
   private int age;

   public int getAge() {
      return age;
   }

   public String getName() {
      return name;
   }

   public String getIdNum() {
      return idNum;
   }
}

public class RunEncap {

   public static void main(String args[]) {
      EncapTest encap = new EncapTest();
      encap.setName("James");
      encap.setAge(20);
      encap.setIdNum("12343ms");

      System.out.print("Name : " + encap.getName() + " Age : " + encap.getAge());
   }
}

public class EncapTest {
   private String name;
   private String idNum;
   private int age;

```
### 16. Compare interface and abstract class with use cases.   
- Use interface when:  
Define a **contract** or capability (what a class can do), but not how.   
Need **multiple inheritance** of behavior across unrelated classes.  
Expect implementers to provide their **own logic**.  

- Use abstract class when:  
Provide **common code, fields, or default behavior**.  
Define some methods but **leave others abstract for subclasses**.  
Maintain state (fields) in the base class.
DO NOT allow Multiple Inheritance. Only single class inheritance.

```java
```