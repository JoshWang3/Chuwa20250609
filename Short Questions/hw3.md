### Short Questions
1. Write up Example code to demonstrate the three foundmental concepts of OOP.
    - Encapsulation:


         class People{
            
           private String Name;
           private int age;
           private String gender;
        
           public People(){
               this.name = "default name";
               this.age = "default age;
               this.gender = "default gender";
               }
           }

        
           public void setName(String name){
               this.name = name;
           }
        
           public String getName(){
               return this.name;
           }
         
            public void setAge(int age){
               this.age = age;
            }

            public int getAge(){
               return this.age;
            }

            public void setGender(String gender){
               this.gender = gender;
            }

            public String getGender(){
               return this.gender;
            }
        }
   
      public class Main{
         public static void Main(int[] args){
            Person p = new Person();
            p.setName("Chris");
            System.out.println(p.getName());
            p.setAge(10);
            System.out.println(p.getAge());
            p.setGender("Male");
            System.out.println(p.getGender());
         }
      }
   - Polymorphism:
   

         //parent class
         class Person{
            private String name;
            
            public Person(String name){
               this.name = name;
            }
            
            public void speak(){
               System.out.println("Hi, my name is " + this.name);
            }
            
           }
         
           //child class
           class Athlete extends Person{
              @Override
              public void speak(){
                super.speak();
                System.out.println("I am an athlete.");
              }

              //Overload
             public void speak(String content){
                System.out.println(content);
             }
           }

             public class Main{
                public static void Main(String[] args){
                    Person p1 = new Person("Chris");
                    Athlete a1 = new Person("Eileen");
                    p1.speak();
                    a1.speak();
                    a1.speak("I am a world Champion");
                }

             }
        
  - inheritance
        
        //parent class
        class Person{
            private String name;
            public Person(String s){
              this.name = s;
            }
            public void work(){
              System.out.println("working");
            }
        }
  
        //child class
        class Doctor extends Person{
          public Doctor(String name){
            super(name);
          } 
          @Override
          public void work(){
            super.work();
            System.out.println("doing doctor's work");
          }
        }

        //child class 2
        class Niuma extends Person{

          public Niuma(String name){
            super(name);
          }
            @Override
            public void work(){
              System.out.println("Working like a Niuma");
            }
          }

          public class Main{
            publc static void Main(String[] args){
                Person p1 = new Person("Chris");
                Person d1 = new Doctor("Zhang");
                Person n1 = new Niuma("Wang");
                p1.work();
                d1.work();
                n1.work();
              
            }
          }
    
2. What is a wrapper data type classes in Java and why do we need wrapper class?
   An wrapper class wraps primitive data types (eg. int, double, boolean) as an Object class of its relative type.
   Each primitive data type has its corresponding wrapper type.
   - It allows primitive values to be used in situation that requires and Object.(eg. Collections)
   - Wrapper class can be null, while primitive values cannot.
   - We can use utility methods such as compareTo(), equals(), toString() with Objective types, but not primitive values.
   - Jave Generics only allow Objective types.

3. What is the difference between HashMap and HashTable?
   They are different implementation of key-value pair storage.
   HashMap is not synchronized, not thread-safe, and allows one null key and multiple null values.
   HashTable is synchronized, thread-safe, and does not allow null key or values. HashTable is now deprecated.

4. What is String pool in java and why we need String pool? Explain String immunity.
   A String pool is a place in heap memory where all Strings declared in the program is stored. Literals stored in String pool
   are unique. String pool saves memory in multiple Strings of same value are declared. And allows fast comparison.
   String immunity in java means value of any Strings declared in Java cannot be changed. This is achieved by:
    - In String class the char array that holds string value is declared final
    - No setter function is given for String object.

5. Explain garbage collection.
    Garbage collection is an automatic mechanism that reclaims occupied memory from object that are no longer in use.
    Java Garbage collections clears up memory in heap without requiring programmer's constant attention.
    There are mainly seven types of garbage collectors.
    - Serial GC: A single-threaded GC, suitable for small applications.
    - Parallel GC: A multi-threaded GC, optimized for high throughput.
    - Concurrent Mark Sweep GC: Aims to reduce pause times by performing garbage collection concurrently with application execution.
    - G1 GC: A modern garbage collector that divides the heap into regions and performs garbage collection in a concurrent and parallel manner. It is the default GC in recent Java versions.
    - ZGC: A low-latency garbage collector designed for large heaps.
    - Epsilon GC: A no-op GC, useful for performance testing.
    - Shenandoah GC: A concurrent GC focused on lowe pause times.

6. What is access modifiers and their scopes in Java?
    Access Modifiers in java are keywords that define the accessibility and visibility of classes, variables, methods and constructors within a java program.
    There are four access modifiers in java:
    - public: Members declared public are accessible from any other class, regardless of the package.
    - private: Members declared private are only accessible within the class where they are defined.
    - protected: The protected modifier allows access within the same package and by subclasses, even if those subclasses are in different packages.
    - default: When no access modifier is explicitly specified, the member or class has default or package-private access.
               Member with default access are only accessible within the same package.

7. Explain final keyword.
    The final keyword in java is used to indicate immutability or non=overridability, depending on the contxt. 
    It can be applied to variables, methods, and classes.
    - final variable: A variable marked final connote be changed once it is assigned a value.
        - for primitive types: the value itself is immutable
        - for objects: the reference cannot change, but the object's internal can still be changed.
    - final Method: A method marked final cannot be overridden by subclasses.
    - final Class: A class marked final cannot be inherited(subclassed).

8. Explain static keyword.
    The static keyword in Java is used to define class-level members, meaning they belong to the class itself, not to any specific instance of the class.
    - static Variables: Shared across all instances of a class. Only one copy exists, regardless of how many objects are created.
    - static Methods: Belong to the class, not to any object. Can be called without creating an instance. Cannot access instance variables or methods directly.
    - static Blocks: Used to initialize static variables. Runs once when the class is first loaded into memory.
    - static Classes: A static nested class is a class define inside another class and marked static. It cannot access instance members of the outer class directly.

9. What is the differences between overriding and overloading? 
    Method Overloading means having multiple methods in the same class with the same name but different parameter lists. 
    - Happens at compile-time
    - Can apply to constructors as well as methods.
    - Return type can be different, but must differ in parameters.
    Method overriding means providing a new implementation of an inherited method in a subclass that has same signature as the method in the parent class.
    - Happens at runtime
    - Method name, parameters and return type must match exactly.
    - The method in the parent class must be inheritable and not private of final.

10. Explain how Java defines a method signature, and how it helps on overloading and overriding.
    In Java, a method signature is defined by:
    - The method name.
    - The parameter list.
    Overloading depends directly on the method signature. Two or more methods in the same class can have the same name as long as their signatures are different.
    Overriding requires the method signature in the subclass to exactly match the method signature in the parent class.
    - The method name and parameter list must be identical.
    - The return type must be the same or a covariant type.

11. What is the differences between super and this?
    this refers to the current class instance.
    - Referring to current object's fields when there's a naming conflict.
    - Calling another constructor in the same class.
    - Passing current object as argument.
    Super refers to the parent class of the current class.
    - Accessing superclass's method or variable.
    - Accessing superclass's constructor

12. Explain how equals and hashCode work.
    Equals() checks if instances of the same class are logical equal by checking all fields are equal by given rules.
    hashCode() calculate corresponding hash value by given methods.
    instances can have same hash value, although they may not be logical equal.

13. What is the Java load sequence? 
    1. Parent's static blocks -> 2. Child's static blocks -> 3.Parent's instance blocks -> 4.Parent's constructor -> 5. Child's instance blocks -> 6. Child's constructor.

14. What is Polymorphism? And how Java implements it? 
    In Java, polymorphism allows one interface to be used for different underlying data types or behaviors.
    Java supports two types of polymorphism:
    - Compile-time : Method Overloading
    - Runtime: Method Overriding

15. What is Encapsulation? How Java implements it? And why we need encapsulation?
    Encapsulation is one for the four principles of OOP. It means binding data and behavior together into a single unit, and restricting direct access to the internal state of an object.
    Java support encapsulation primarily through: 
    - Private fields
    - Public getters and setters
    - Classes as containers for data and behavior
16. Compare interface and abstract class with use cases.
    Both interfaces and abstract classes in Java are used to achieve abstraction, but they serve different purposes and have distinct characteristics.
    | Feature                        | **Interface**                                                                                                  | **Abstract Class**                                                |
    | ------------------------------ | -------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------- |
    | **Purpose**                    | Define a **contract** or capability                                                                            | Provide a **base class** for shared behavior                      |
    | **Methods**                    | Can have **abstract**, **default**, and **static** methods (Java 8+)<br>Can have **private** methods (Java 9+) | Can have **abstract** and **concrete (non-abstract)** methods     |
    | **Variables**                  | `public static final` by default (constants only)                                                              | Can have any type of variables (instance/static, final/non-final) |
    | **Constructors**               | No constructors                                                                                              | ✅ Can have constructors                                           |
    | **Inheritance**                | A class can **implement multiple interfaces**                                                                  | A class can **extend only one abstract class**                    |
    | **Access Modifiers (methods)** | All methods are implicitly `public`                                                                            | Can have `public`, `protected`, `private` methods                 |
    | **State**                      | Cannot hold state (no instance fields)                                                                         | Can hold state (instance variables)                               |
    | **When to Use**                | To define capability: `Runnable`, `Comparable`, etc.                                                           | To define base behavior with shared code                          |
    | **Multiple Inheritance?**      | Yes (multiple interfaces)                                                                                    | ❌ No (only one superclass)                                        |
    | **Java Keyword**               | `interface`                                                                                                    | `abstract class`                                                  |
    interface Walkable{
        void walk();
    }

    class Dog implements Walkable{
        public void walk(){
            System.out.println("A Dog walks");
        }

    }

    class Cat implements Walkable{
        public void walk(){
            System.out.println("A cat walks");
        }
    }

    abstract class Vehicle{
        String kind;
        public Vehicle(String kind){
            this.kind  = kind;
        }
        
        abstract void run();

        void repair(){
           System.out.println("repairing");
        }

   }
    
   class Car extends Vehicle{
        public Car(String kind){
            super(kind);
        }
    
        void run(){
            System.out.println("Car is running");
        }
   }
        
            
