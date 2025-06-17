# Java Short Questions - HW3

## 1. What is wrapper data type classes (e.g. Integer, Double) in Java and Why we need wrapper class?

**Wrapper Classes**: Object representations of primitive data types (int → Integer, double → Double, char → Character, etc.)

**Why needed:**
- Collections only store objects, not primitives
- Null value support (primitives can't be null)
- Utility methods (Integer.parseInt(), Double.valueOf())
- Generics require objects (List<Integer>, not List<int>)

## 2. What is the difference between HashMap and HashTable?

| Feature | HashMap | HashTable |
|---------|---------|-----------|
| Thread Safety | Not thread-safe | Thread-safe (synchronized) |
| Null Values | Allows one null key, multiple null values | No null keys/values |
| Performance | Faster | Slower (due to synchronization) |
| Inheritance | Extends AbstractMap | Extends Dictionary |

## 3. What is String pool in Java and why we need String pool? Explain String immunity.

**String Pool**: Memory area in heap where String literals are stored to avoid duplicates.

**Why needed:**
- Memory optimization (reuses identical strings)
- Performance improvement (faster comparison)
- String literals automatically pooled

**String Immutability**: 
- Strings cannot be modified after creation
- Any "modification" creates a new String object
- Benefits: thread safety, caching hashcode, security

```java
String s1 = "hello";     // stored in pool
String s2 = "hello";     // reuses same object
String s3 = new String("hello"); // creates new object
```

## 4. Explain garbage collection? Explain types of garbage collection.

**Garbage Collection**: Automatic memory management that removes unreferenced objects.

**Types:**
1. **Serial GC**: Single-threaded, suitable for small applications
2. **Parallel GC**: Multi-threaded, default for server applications
3. **G1 GC**: Low-latency collector for large heaps
4. **ZGC/Shenandoah**: Ultra-low latency collectors

**Process**: Mark → Sweep → Compact

## 5. What are access modifiers and their scopes in Java?

| Modifier | Same Class | Same Package | Subclass | Other Package |
|----------|------------|--------------|----------|---------------|
| private | ✓ | ✗ | ✗ | ✗ |
| default | ✓ | ✓ | ✗ | ✗ |
| protected | ✓ | ✓ | ✓ | ✗ |
| public | ✓ | ✓ | ✓ | ✓ |

## 6. Explain final keyword? (Field, Method, Class)

**Field**: Value cannot be changed after initialization (constant)
```java
final int x = 10; // x cannot be reassigned
```

**Method**: Cannot be overridden by subclasses
```java
final void display() { } // cannot override
```

**Class**: Cannot be extended/inherited
```java
final class String { } // cannot extend String
```

## 7. Explain static keyword? (Field, Method, Class). When do we usually use it?

**Field**: Belongs to class, shared by all instances
**Method**: Can be called without creating object
**Class**: Only nested classes can be static

**When to use:**
- Utility methods (Math.max())
- Constants (Math.PI)
- Factory methods
- Helper functions that don't need instance data

```java
public static final double PI = 3.14159;
public static int add(int a, int b) { return a + b; }
```

## 8. What is the differences between overriding and overloading?

| Feature | Overloading | Overriding |
|---------|-------------|------------|
| Definition | Same method name, different parameters | Redefining parent method in child |
| Inheritance | Same class | Required |
| Parameters | Must differ | Must be same |
| Return type | Can differ | Must be same/covariant |
| Binding | Compile-time | Runtime |

## 9. Explain how Java defines a method signature, and how it helps on overloading and overriding.

**Method Signature**: Method name + parameter types (order and number)
- Does NOT include return type or access modifiers

**For Overloading**: Different signatures allow multiple methods with same name
**For Overriding**: Same signature ensures proper method replacement

```java
// Different signatures (overloading)
void print(int x)
void print(String s)
void print(int x, String s)
```

## 10. What is the differences between super and this?

| Keyword | Purpose | Usage |
|---------|---------|-------|
| this | Reference to current object | this.field, this.method(), this() |
| super | Reference to parent class | super.field, super.method(), super() |

```java
class Child extends Parent {
    int x = 20;
    Child() {
        super();    // call parent constructor
        this.x = 30; // current object's field
    }
}
```

## 11. Explain how equals and hashCode work.

**equals()**: Compares object content for equality
**hashCode()**: Returns integer hash value for object

**Contract:**
- If obj1.equals(obj2) is true, then obj1.hashCode() == obj2.hashCode()
- If hashCodes are different, objects are definitely not equal
- Used together in HashMap, HashSet for efficient storage/retrieval

```java
@Override
public boolean equals(Object obj) {
    // compare content
}

@Override  
public int hashCode() {
    return Objects.hash(field1, field2);
}
```

## 12. What is the Java load sequence?

1. **Class Loading**: Load .class files into memory
2. **Linking**: 
   - Verification: Bytecode validation
   - Preparation: Allocate memory for static variables
   - Resolution: Replace symbolic references
3. **Initialization**: Execute static blocks and initialize static variables

**Order**: Static blocks → Static variables → Instance blocks → Constructor

## 13. What is Polymorphism? And how Java implements it?

**Polymorphism**: Same interface, different implementations ("one interface, many forms")

**Java Implementation:**
- **Runtime Polymorphism**: Method overriding + inheritance
- **Compile-time Polymorphism**: Method overloading

```java
Animal animal = new Dog(); // reference type vs object type
animal.sound(); // calls Dog's sound() method (runtime binding)
```

## 14. What is Encapsulation? How Java implements it? And why we need encapsulation?

**Encapsulation**: Bundling data and methods together, hiding internal implementation

**Java Implementation:**
- Private fields
- Public getter/setter methods
- Access modifiers

**Why needed:**
- Data security and integrity
- Code maintainability
- Controlled access to data
- Hide implementation details

```java
class Person {
    private String name; // encapsulated field
    
    public String getName() { return name; } // controlled access
    public void setName(String name) { this.name = name; }
}
```

## 15. Compare interface and abstract class with use cases.

| Feature | Interface | Abstract Class |
|---------|-----------|----------------|
| Multiple Inheritance | Yes | No |
| Method Implementation | Default/static methods only | Can have concrete methods |
| Variables | public static final only | Any type |
| Constructor | No | Yes |
| Access Modifiers | public (default) | Any |

**Use Cases:**
- **Interface**: Contract definition (Drawable, Comparable), multiple inheritance
- **Abstract Class**: Partial implementation, template pattern (Animal → Dog, Cat)

```java
// Interface - pure contract
interface Drawable {
    void draw();
}

// Abstract class - partial implementation
abstract class Animal {
    protected String name;
    abstract void sound();
    void sleep() { System.out.println("Sleeping..."); }
}
```
