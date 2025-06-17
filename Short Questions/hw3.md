
## Question 1
```java
// parent class 
class Vehicle {
    // 1. encapsulation
    private double speed;
    
    public Vehicle() {
        this.speed = 0;
    }
    
    public double getSpeed() {
        return speed;
    }
    
    // 3. polymophorism
    public void accelerate(double amount) {
        if (amount > 0) {
            speed += amount;
        }
    }
    
    public void accelerate(int amount) {
        if (amount > 0) {
            speed += amount;
        }
    }
    
    public void vehicleType() {
        System.out.println("general");
    }
}

// 2. inheritance
class Plane extends Vehicle {
    public Plane() {
    }
    
    // 3. polymophorism
    @Override
    public void vehicleType() {
        System.out.println("plane");
    }
}


```

## Question 2

Wrapper classes in Java are object versions of primitive data types. 

We need wrapper classes, because collections only accept objects, and they accepts null values while primitives can't be null. In addition, wrapper classes has some useful utility methods. 

## Question 3
HashTable:
- legacy class, extending legacy class Dictionary. 
- slower. 
- does not allow null. 
- thread-safe. 

HashMap:
- extends AbstractMap. 
- faster. 
- allow null in both key/values. 
- not thread-safe. 


## Question 4
String pool in Java is a special memory area in the heap,  checks existing entries in the memory area for new entry before assigning new memory. 

We need String pool to avoid memory waste and for faster reference comparison. 

String immunity (immutability) means a String object cannot be changed  once it is created.

## Question 5

Garbage collection is an automatic process that frees up memory occupied by objects that are no longer referenced.

Types:
- Serial - single thread, simple approach.
- Parallel - multiple threads, high throughput.
- Concurrent Mark Sweep - runs alongside application, deprecated for G1.
- Garbage First (G1) - region-based heap management, incremental.
- Z Garbage Collector - concurrent collection, low pause time, highly scalable. 
- Shenandoah - concurrent collection, low pause time, consistent low latency.

## Question 6
4 Types:
- default (no keyword) - same package.
- private - same class.
- protected - inherited/same package.
- public - different package, different class.

## Question 7
final:
- field (variable) - must be initialized, can initialize in constructor, cannot reassign. 
- method - cannot be overridden/overwritten.
- class - cannot be extended.

## Question 8
static - belongs to class, not instance:
- field (variable) - e.g. shared counter.
- method - e.g. Math.add(10,  20); called without creating instance.
- class (static nested class) - logically related to the outer class but do not depend on instance of outer class. e.g. setter.

## Question 9
Overloading - same name, different parameters. In same class. 
Overriding - same name, same parameters, different implementation. In subclass.

## Question 10
Method signature: method name + parameter types
- Overloading - different signature, same class. 
- Overriding - same signature, different implementation. In subclass.

## Question 11
this calls child's method (if overridden) and parameters,  super calls parent's method and parameter. 

## Question 12
equals() and hashCode() are fundamental methods of java objects.
 
equals() checks object reference equality. 

hashCode() returns memory address-based hash. 

## Question 13
Java load sequence is the order in which classes, variables, and blocks are loaded and initialized when a Java program runs.

## Question 14
Polymorphism: a single method name to represent different implementations. 

Implemented by: 
- Overloading - same name, different parameters. In same class. 
- Overriding - same name, same parameters, different implementation. In subclass.

## Question 15
Encapsulation: bundling data and methods that operate on that data, while hiding the internal implementation details. 

Implemented by: private fields and getter/setter methods for the fields.

We need encapsulation because it allows data protection, validation and access control. 

## Question 16
Choose Interface if:
-   Need multiple inheritance
-   No shared implementation

Choose Abstract if:
-   Need constructors
-   Have shared implementation