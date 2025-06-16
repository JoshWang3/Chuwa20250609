### Short Questions



 Write up Example code to demonstrate the three foundmental concepts of OOP.
1. Encapsulation;
2. Polymorphism;``
3. Inheritance; 

    
    class Animal {
    // Encapsulation
        private String name;  
        public Animal(String name) {
            this.name = name;
        }
       
        // Polymorphic method
        public void speak() { 
            System.out.println(name + " makes a sound");
        }
    
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
    
    // Inheritance
    class Dog extends Animal {
        public Dog(String name) {
            super(name);
        }
    
        @Override
        public void speak() {
            System.out.println(getName() + " barks");
        }
    }
    
    public class Main {
        public static void main(String[] args) {
            Animal a = new Dog("Buddy"); 
            a.speak();
        }
    }
    


2.What is wrapper data type classes (e.g. Integer, Double) in Java and Why we need wrapper class?

a wrapper class is the object version of a primitive type.

```
int ->Integer,double ->Double

List<Integer> list = new ArrayList<>();
list.add(10); 
```

3.What is the difference between HashMap and Hashtable?

`HashMap` is not thread-safe but faster, and it allows one `null` key and multiple `null` values. `Hashtable` is thread-safe because its methods are synchronized, but it does not allow any `null` keys or values. In modern Java, `HashMap` is preferred, and `ConcurrentHashMap` is used for thread-safe needs.

4. What is String pool in Java and why we need String pool? Explain String immunity.

   The **String pool** stores string literals to save memory. When two strings have the same literal, they share one object in the pool. This improves efficiency. Java strings are **immutable**, meaning their content can’t be changed once created. This ensures safety, caching, and consistency.

```
String a = "abc";
String b = "abc";
System.out.println(a == b); // true

```

5.Explain garbage collection? Explain types of garbage collection.

 Common GC types include Serial (single-thread), Parallel (multi-thread), CMS (low pause), and G1 (balanced performance). Developers don’t manually free memory; the JVM handles it

6.What are access modifiers and their scopes in Java?

Java has four access modifiers: `private` (only in same class), default (same package), `protected` (same package + subclasses), and `public` (accessible everywhere).

7.Explain final keyword (Field, Method, Class)

` final means “can’t change”:

- Final variable: can’t be reassigned.
- Final method: can’t be overridden.
- Final class: can’t be extended.

 8.Explain static keyword (Field, Method, Class). When do we usually use it?

The `static` keyword means a member belongs to the class, not to any specific object.

- A static **field** is shared across all instances.
- A static **method** can be called without creating an object.
- A static **class** applies to inner classes only.

```
class A {
    static int count = 0;
    static void show() {
        System.out.println("Static method");
    }
}


```

9.What is the difference between overriding and overloading?

**Overriding** means a subclass provides its own version of a parent method with the **same name and parameters**.
**Overloading** means methods have the **same name** but **different parameter types or numbers** within the same class.

```
// Overloading
void print(int x) {}
void print(String x) {}

// Overriding
class A { void say() {} }
class B extends A { @Override void say() {} }

```

10.Explain how Java defines a method signature, and how it helps on overloading and overriding.

A method signature in Java includes the **method name and parameter list** (types and order). It doesn’t include the return type.
Overloading relies on having different signatures, while overriding must have the **same** signature.

11.What is the difference between `super` and `this`?

- `this` refers to the **current class** object.
- `super` refers to the **parent class** object.

`this()` can call another constructor in the same class.
 `super()` can call a constructor or method from the parent class.



12.Explain how `equals` and `hashCode` work.

The `equals()` method checks if two objects are **logically equal**.
 The `hashCode()` method returns an integer used in hashing (like in `HashMap`).

If `equals()` is overridden, `hashCode()` must also be overridden to ensure consistent behavior in hash-based collections.



13.What is the Java load sequence?

When a class is loaded:

1. Static blocks and static fields are initialized **once** when the class is loaded.
2. Then instance fields and constructors are executed **when objects are created**.

14.What is Polymorphism? And how does Java implement it?

Polymorphism allows the same method to behave differently based on the object. Java uses:

- **Method overriding** (runtime polymorphism)
- **Method overloading** (compile-time polymorphism)

15.What is Encapsulation? How does Java implement it? And why do we need it?

Encapsulation means hiding internal details and exposing only necessary interfaces.
Java uses `private` fields + `public` getters/setters to achieve it.
It improves code safety, flexibility, and prevents misuse

```
class User {
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

16.Compare interface and abstract class with use cases.

An **interface** defines a contract; it has no state, and all methods are abstract by default.

An **abstract class** can have both abstract and concrete methods, and can hold fields and constructors.

interface Flyable {
    void fly();
}

abstract class Animal {
    void eat() { System.out.println("eating"); }
    abstract void speak();
}