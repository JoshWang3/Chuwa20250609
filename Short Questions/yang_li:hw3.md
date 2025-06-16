For all questions below, you should write sample code, which can be ran directly on online compilers such as https://www.onlinegdb.com/online_java_compiler (if possible) to explain your answer

1. Write up Example code to demonstrate the three foundational concepts of OOP.

   - Encapsulation;

   ```java
   public class Main {
       public static void main(String[] args) {
           Animal a = new Animal("Coco");
           a.speak(); // 输出: Coco awwwwwwwwwww.
           System.out.println("Name is: " + a.getName()); 
           a.setName("Mimi"); // 修改名字
           a.speak(); // 输出: Mimi awwwwwwwwwww.
       }
   }
   
   class Animal {
       private String name; // 封装：私有属性
   
       public Animal(String name) {
           this.name = name;
       }
   
       public void speak() {
           System.out.println(name + " awwwwwwwwwww.");
       }
   
       // getter
       public String getName() {
           return name;
       }
   
       // setter
       public void setName(String name) {
           this.name = name;
       }
   }
   
   ```

   

   - Polymorphism;

   ```java
   public class Main{
       public static void main(String[] args) {
           Animal a1 = new Dog("Coco");
           Animal a2 = new Cat("Mimi");
           
           a1.speak();
           a2.speak();
           
           System.out.println("Dog's name: " + a1.getName());
           System.out.println("Cat's name: " + a2.getName());
           
           a1.setName("Lucky");
           a2.setName("kitty");
           a1.speak();
           a2.speak();
       }
   }
   
   
   //Parent class
   class Animal {
       private String name;
       
       public Animal(String name) {
           this.name = name;
       }
       
       //getter
       
       public String getName() {
           return name;
       }
       
       public void setName(String name) {
           this.name = name;
       }
       
       public void speak(){
           System.out.println(name + "makes a sound.");
       }
   }
   
   
   // Subclass Dog
   class Dog extends Animal {
       public Dog(String name) {
           super(name);
       }
       @Override
       public void speak(){
           System.out.println(getName() + ": woof!");
       }
   }
   
   //Subclass Cat
   class Cat extends Animal{
       public Cat(String name) {
           super(name);
       }
       
       @Override
       public void speak() {
           System.out.println(getName() + ": Meow!");
       }
   }
   ```

   

   - Inheritance;

   ```java
   // Parent class
   
   class Aniaml {
       private String name;
       
       public Aniaml(String name) {
           this.name = name;
       }
       
       public void eat() {
           System.out.println(name + " is eating.");
       }
       
       //Getter
       
       public String getName() {
           return name;
       }
       
       public void setName(String name) {
           this.name = name;
       }
   }
   
   
   //Child class 
   class Dog extends Aniaml {
       public Dog(String name) {
           super(name);
       }
       
       
       public void bark(){
           System.out.println(getName() + " is barking: Woof!");
       }
   }
   
   
   public class Main{
       public static void main(String[] args) {
           Dog myDog = new Dog("coco");
           myDog.eat();
           myDog.bark();
       }
   }
   ```

   

2. What is **wrapper data type classes** (e.g. Integer, Double) in Java and Why we need wrapper class?

| Primitive Type | Wrapper Class |
| -------------- | ------------- |
| `byte`         | `Byte`        |
| `short`        | `Short`       |
| `int`          | `Integer`     |
| `long`         | `Long`        |
| `float`        | `Float`       |
| `double`       | `Double`      |
| `char`         | `Character`   |
| `boolean`      | `Boolean`     |

Wrapper classes allow primitives to be used where objects are required, provide helpful methods, and enable features like autoboxing and storing nulls in collections.

1. What is the difference between **HashMap** and **HashTable**? **HashMap** is unsynchronized, allows nulls, and is faster; **HashTable** is synchronized, does not allow nulls, and is legacy.

2. What is **String pool** in Java and why we need String pool? Explain String immunity. The String pool allows Java to reuse String objects with the same value to save memory. String immutability means their values cannot change, making pooling safe and efficient.

3. Explain **garbage collection**? Explain types of garbage collection.   

   Garbage collection in Java is automatic memory management that removes unused objects to free memory.
    Java provides different garbage collectors (Serial, Parallel, CMS, G1, ZGC, etc.) to balance throughput and latency based on application needs.

4. What are **access modifiers** and their scopes in Java?   

   Access modifiers control visibility of class members.
    `public`: everywhere; `protected`: package & subclass; (default): package only; `private`: class only.

5. Explain **final** key word? (Filed, Method, Class)

   The `final` keyword restricts further modification:

   - Final field: value cannot change.
   - Final method: cannot be overridden.
   - Final class: cannot be inherited.

6. Explain **static** keyword? (Filed, Method, Class). When do we usually use it?

   The `static` keyword means the member belongs to the class, not instances.
    Use `static` for constants, utility methods, or fields shared by all objects.

7. What is the differences between **overriding** and **overloading**?

   Overriding changes a parent class method’s behavior in a subclass; overloading allows multiple methods with the same name but different parameters in one class.

8. Explain how Java defines a **method signature**, and how it helps on overloading and overriding.

   The **method signature** is made up of the method name and the parameter types/order.

   **Overloading**: Possible only if signatures are different.

   **Overriding**: Possible only if the signature matches exactly.

9. What is the differences between **super** and **this**?

   `this` refers to the current object, while `super` refers to the parent class object.

10. Explain how `equals` and `hashCode` work.

    `equals` checks if two objects are logically equal; `hashCode` provides a number for efficient storage and retrieval in hash-based collections, and both must be consistent for correct behavior.

11. What is the Java **load sequence**?

    The Java load sequence is:

    1. Parent’s static blocks → 2. Child’s static blocks → 3. Parent’s instance blocks → 4. Parent’s constructor → 5. Child’s instance blocks → 6. Child’s constructor.

12. What is **Polymorphism**? And how Java implements it?

    Polymorphism allows one reference type to refer to objects of different actual types, and the correct overridden method is chosen at runtime, making code flexible and extensible.

13. What is **Encapsulation**? How Java implements it? And why we need encapsulation?

    Encapsulation means restricting access to an object’s internal data and only exposing safe, controlled ways to read or modify it.
     Java uses private fields and public getter/setter methods to implement this.

14. Compare **interface** and **abstract class** with use cases.

    Use **interfaces** to define a set of behaviors that can be shared across unrelated classes, and **abstract classes** for sharing common code and structure in related classes.

    **Interface Example:**

    - “Can fly” for all flying objects, regardless of their inheritance tree.

      ```java
      
      interface Flyable {
          void fly();
      }
      class Bird implements Flyable {
          public void fly() { System.out.println("Bird flies"); }
      }
      class Airplane implements Flyable {
          public void fly() { System.out.println("Plane flies"); }
      }
      ```

    **Abstract Class Example:**

    - All animals have legs, but their sounds differ.

      ```java
      
      abstract class Animal {
          int legs = 4;
          abstract void makeSound();
          void walk() { System.out.println("Walking with " + legs + " legs"); }
      }
      class Dog extends Animal {
          void makeSound() { System.out.println("Woof"); }
      }
      ```

------