# hw2 Submission

## Q1: Write a Java POJO (plain old java object) named "Employee", inside Employ class
In coding file

## Q2: Write code to instantiate at least two instances of above Employee class, use code snippets to explain how these Employee objects are allocated to JVM memory. You may use java relection utilities to demonstrate it.

### Answer:
Compare Class Metadata (Metaspace): Both emp1 and emp2 share the same class definition. The Class object resides in Metaspace, and it’s loaded only once. 

Compare Object Data (Heap): Instance data (e.g., name, ssn, address) is stored in the heap for each object. Fields may have the same values (equals() == true) or be separate objects (== false). If two objects reference the same department object, == will return true. 

## Q3: Write static utilities in your Employee class, demostrate how static content differs from others during class instantiation.

### Answer:
Static Block: runs when the class is loaded. 

Static Field: Shared across all instances of the class. Memory is in the Metaspace (class area), not Heap. It keeps track of how many Employee objects have been created. 

Constructor: Every time you call new Employee(...), this line runs. It increments the shared static counter, not an instance variable.

Static Method: Called using Employee.isValidSSN(...). Can be used without creating any Employee object. Doesn’t depend on instance state.

## Q4: Explain why global variables are NOT recommended, you may use code snippets.

### Answer:
1. Tight Coupling

Global variables create hidden dependencies between unrelated parts of the program.

```java
public class Config {
    public static String appMode = "DEV"; // global variable
}
```

```java
public class ServiceA {
    public void run() {
        if (Config.appMode.equals("DEV")) {
            System.out.println("Running in debug mode");
        }
    }
}
```
 
2. Difficult to Debug and Maintain

Any part of the code can change a global variable at any time, leading to unpredictable state.

```java
Config.appMode = "PROD";  // Somewhere deep in the app
```

Suddenly ServiceA behaves differently — and you may not know why unless you track every modification of Config.appMode.

3. Thread Safety Issues

In a multi-threaded program, global variables can cause race conditions.

```java
public class Counter {
    public static int count = 0;
}
```

```java
// Thread 1
Counter.count++;

// Thread 2
Counter.count++;
```
 
Without synchronization, the result could be incorrect because both threads read and write the same memory at the same time.

## Q5: Explain why Strings in Java are considered "Immutable"?
Java String is immutable, meaning Once a String object is created, it cannot be changed.Any operation that appears to modify a string actually returns a new String object.

### Answer:
Java uses a String Constant Pool to optimize memory: 

When you write: String s1 = "hello";

	Java checks the pool to see if "hello" already exists.

	If yes, it reuses the existing object.

	If no, it creates a new one and adds it to the pool.

This is only safe because strings are immutable — if one object could change, it would affect all other references.

## Q6: Write code snippets to explain what does "Final" keyword do, and what we need it?

### Answer:
1. final Variable: Value Cannot Change

To create constants

Prevent accidental reassignment

Improve readability and safety

2. final Reference (Object): Reference Cannot Change, But Object Can

final does not make the object immutable

It only means the reference cannot point to another object

3. final Method: Cannot Be Overridden

To lock down behavior that should not be changed in subclasses

Ensure security or correctness in critical logic

4. final Class: Cannot Be Extended

To prevent subclassing (e.g., for security, performance, or design reasons)

Used in classes like String, Integer, LocalDate

## Q7: Write code snippets to explain why Java is "pass-by-value", and why do some people think it might be "pass-by-reference"?

### Answer:
In Java, everything is passed by value, even when you're working with objects.

For primitives: the actual value is copied.

For objects: the value of the reference (i.e., memory address) is copied.

So Java passes a copy of the value, not the actual object itself.

## Q8: Write code snippets to explain overloading in Java, explain how does Java define method signature.

### Answer:
Method Overloading means defining multiple methods in the same class with the same name, but different parameter lists. Java uses method signatures to distinguish between them.

A method signature consists of: Method name, Number, types, and order of parameters

It does NOT include: Return type, Access modifier, Exceptions thrown

## Q10: Leetcode
In coding file