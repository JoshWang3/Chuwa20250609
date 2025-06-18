# HW2: Java-Core-Assignment
@ Jun 12, 2025 _Gloria Wang_

## 1. Java POJO named "Employee"
_Coding details are in `Main` `Deparement` `Employee` `Address` class_

## 2. How Employee Objects Are Allocated to JVM Memory
_Coding explanations are in `Main` class_

**Stack:**
- `department` → reference to Department object on heap
- `address` → reference to Address object on heap
- `employee` → reference to Employee object on heap

**Heap:**
- Department object (fields: `id`, `name`)
- Address object (fields: `street`, `district`, `city`, `postcode`)
- Employee object (fields: `name`, `ssn`, `dateOfBirth`, `department`, `address`)
  
  (Employee contains references to Department and Address objects)

#### Instantiate Three Employee Objects

```java
Employee employee1 = new Employee(
        "Harry Potter",
        "123-45-6789",
        LocalDate.of(1980, 7, 31),
        department1,
        address1
);

Employee employee2 = new Employee(
        "Sherlock Holmes",
        "987-65-4321",
        LocalDate.of(1854, 1, 6),   
        department2,
        address2
);

Employee employee3 = new Employee(
        "SpongeBob SquarePants",
        "000-00-0001",
        LocalDate.of(1986, 7, 14),
        department3,
        address3
);
```
#### Java Reflection Utilities to Demonstrate Allocation

```Java
// Static helper function for reflection
public static void printObjectFields(Object obj) throws IllegalAccessException {
    Class<?> clazz = obj.getClass();
    System.out.println("Inspecting: " + clazz.getSimpleName());
    for (Field field : clazz.getDeclaredFields()) {
        field.setAccessible(true);
        Object value = field.get(obj);
        System.out.println("  " + field.getName() + " = " + value);
    }
    System.out.println();
}

printObjectFields(employee1);
/*
Output:
Inspecting: Employee
  employeeCount = 3
  name = Harry Potter
  dateOfBirth = 1980-07-31
  department = [id=123, name=Ministry of Magic]
  ssn = 123-45-6789
  address = Underground of Whitehall and the HM Treasury building, Westminster, London, SW1A 2HQ

 */
```
**Visualization:**

    Stack                        Heap

    employee1 ----+-------> Employee object (Harry Potter)
                                  |
                                  +-- department1 ----> Department object (Ministry of Magic)
                                  +-- address1    ----> Address object (Underground of Whitehall and the HM Treasury building, Westminster, London, SW1A 2HQ)
    
    employee2 ----+-------> Employee object (Sherlock Holmes)
                                  |
                                  +-- department2 ----> Department object (Detective)
                                  +-- address2    ----> Address object (221b Baker St., Marylebone, London, NW1 6XE)
                                  
    employee3 ----+-------> Employee object (SpongeBob SquarePants)
                                  |
                                  +-- department3 ----> Department object (Krusty Krab)
                                  +-- address3    ----> Address object (Krusty Krab, Bikini Bottom, Pacific Ocean, 00000)

**Summary:**
- The `new` keyword allocates an object on the **heap**
- Variable names (like `employee1`) are references stored on the **stack**
- Each `Employee` also holds references to its own `Department` and `Address` objects (which are also on the heap)

---

## 3. How Static Differs from Instance
_Coding explanations are in `Main` class_
- **Static fields/methods** belong to the class itself, not to any one object.
  - Shared across all instances
  - Don’t need to create an object to use
  - Example: `Employee.getEmployeeCount()` or `Employee.printCompanyPolicy()`


- **Instance fields/methods** belong to individual objects.
  - Each object has its own values
  - Must access them via the object
  - Example: `employee1.getName()`


#### Example: Static Utilities in `Employee` Class

```java
public class Employee {
  // Static variable: shared by all Employee instances
  private static int employeeCount = 0;

  // Instance fields
  private String name;
  private String ssn;

  // Constructor
  public Employee(String name, String ssn) {
    this.name = name;
    this.ssn = ssn;
    employeeCount++; // Increment static count each time an Employee is created
  }

  // Static utility method: print company policy
  public static void printCompanyPolicy() {
    System.out.println("All employees must follow the Magic World rules!.");
  }

  // Static getter for employeeCount
  public static int getEmployeeCount() {
    return employeeCount;
  }

  // Instance getter
  public String getName() {
    return name;
  }
}
```
#### Demonstration in Main Class
```Java
public class Main {
public static void main(String[] args) {
// Call static method and static variable before any object is created
System.out.println("Employee count before instantiation: " + Employee.getEmployeeCount());
Employee.printCompanyPolicy();

        // Create Employee objects
        Employee employee1 = new Employee("Harry Potter", "123-45-6789");
        Employee employee2 = new Employee("Hermione Granger", "987-65-4321");

        // Static content is SHARED, not per-instance
        // from 0 -> 3
        System.out.println("Employee count after instantiation: " + Employee.getEmployeeCount());

        // valid but bad, should call class instead of instance:
        // System.out.println("Employee count before instantiation: " + employee1.getEmployeeCount());
      
      
        // Call static method - no Employee object needed
        Employee.printCompanyPolicy();
        // Access instance method - requires an object
        System.out.println("Employee1's name: " + employee1.getName());
      
        // CANNOT work: System.out.println(Employee.getName());
        // should call with object not class
    }
}
```

## 4. Why Global Variables are NOT Recommended
_Coding explanations are in `Demo` class_

#### 1. Too Easy to Change

- Any class can change a global variable at any time.
- As we can see in the `Demo` class, it's hard to recognize when and where the global variable is reset.
- `Global.count` can be modified by any method, in any class, so we lose control of where and when the global variable changes.

**Example:**
```java
public class Demo {
    public static void main(String[] args) {
        Global.count = 1;
        resetTo10();    // This may change Global.count unexpectedly
        System.out.println(Global.count); // Value could be changed anytime
    }
    public static void resetTo10() {
        Global.count = 10; // Arbitrary change
    }
}
```
#### 2. Hard to Track Bugs

- If the program behaves unexpectedly, it's hard to know which method or class changed the value of `Global.count`.
- More code -> Harder to detect

**Example:**
```java
public class AnotherClass {
    public void reset() {
        Global.count = 0; // Resets global variable, other classes may not expect this
    }
}
```

#### 3. Testing Becomes Difficult

- The outcome of one test might depend on changes another test made to `Global.count`.
- This causes tests to become unreliable (flaky), as tests are no longer independent.

**Example:**
```java
// Test 1
Global.count = 5; // Sets up state

// Test 2
// Relies on Global.count being 0, but it may be affected by previous tests
```
## 5. Why are Strings in Java Immutable?

- **String immutability** means that once a `String` object is created, its value cannot be changed.
- In Java, all String literals are stored in a special area of heap memory called the **String constant pool**.
- When you write code like `String s1 = "cat"; String s2 = "cat";`, both `s1` and `s2` point to the same memory location in the String pool.

### Why?

1. **String Pool Optimization**
  - Java places all string literals (like `"cat"`) in the constant pool, so there is only one copy of each distinct string value -> Save Memory.
  - If strings were mutable, changing one reference would affect all others, leading to bugs and security risks.

2. **Safety and Thread-Safety**
  - Strings are often used for sensitive information (like usernames, file paths, URLs) -> ⬆️ security.
  - In multithreaded environments, immutable objects are naturally thread-safe because their value can never change.

3. **Hashing and Performance**
  - Strings are commonly used as keys in hash-based collections like `HashMap`.
  - Immutability -> ensures hash code remains consistent, preventing strange behavior in collections.

#### Example:

```java
import org.w3c.dom.ls.LSOutput;

String s1 = "burger";
String s2 = "burger";
// s1 and s2 both point to the same "cat" object in the constant pool

String s3 = new String("burger");
// s3 points to a new object in the heap, with the same content but a different memory address

return s1 == s2 // -> true
return s1 == s3 // -> false
return s1.equals(s3) // -> true
```

## 6. What does "Final" keyword do, and when we need it

### Final Variable

A `final` variable means its value cannot be changed after it is assigned.

Often used for constants.
```java
public static final String STR = "a";
STR = "b"; // ❌ Compilation error: cannot assign a value to final variable 'STR'
```

### Final Method
A `final` method cannot be overridden by subclasses.

Often used when you want to lock the behavior in the parent class.
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
A `final` class cannot be inherited.

Often used when Java designers don’t want anyone to change the behavior of a class (like Integer, String, Math).
```Java
public final class String {
    // The built-in Java String class is final, so no one can extend it.
}

// ❌ Compilation error: Cannot inherit from final class 'String'
class MyString extends String {}

```
## 7. Why Java is "Pass-by-Value"

In Java, **all method arguments are passed by value**.  
-> a copy of the variable is passed to the method, not the original variable itself.

#### Example 1: Passing a Value (Primitive Type)

```java
void changeValue(int x) {
    x = 100;
}

int a = 5;
changeValue(a);
System.out.println(a); // Output: 5

// a stays 5 because x is a copy of a.
```

#### Example 2: "Passing an Object Reference" 🍔
- pass a copy of the reference, not object itself && not original reference

```Java
import Singleton.Burger;
import Singleton.BurgerSingletonPOJO;

void changeReference(Burger burger) {
  burger = new Burger("Cheese Singleton.Burger"); // Assigns 'burger' to a new object (local only)
}

Burger myBurger = new Burger("Double Cheese Singleton.Burger");

changeReference(myBurger);
System.out.

println(myBurger.name); // Output: Double Cheese Burger

// 'myBurger' still points to the original object, so its name is still "Double Cheese Singleton.Burger".
// The method only changed its local copy of the reference.
```
#### Example 3: Changing Object’s Content

```Java
import Singleton.Burger;
import Singleton.BurgerSingletonPOJO;

void changeContent(Burger burger) {
  burger.name = "Beef Singleton.Burger"; // Changes the content inside the object
}

Burger yourBurger = new Burger("Fish Singleton.Burger");

changeContent(yourBurger);
System.out.

println(yourBurger.name); // Output: Beef Burger

// You can change the content of the object pointed to by the reference,
// but not which object the reference variable points to.
```
#### Summary:
- **Passing a primitive type**: making a photocopy of a document for you—no matter how you write on the copy, my original doesn’t change.
- **Passing an object reference**: giving you access to my burger 🍔 you can add or remove ingredients 🥒, but you can’t swap it for a new burger


_Java is “pass-by-value” because it always passes a copy never the original variable into the method._

_Even for objects, it copies the reference, not the object or the reference variable itself._

## 8. Method Overloading in Java

### What is Overloading? (Compile Time Polymorphism )
**Method overloading** means having multiple methods in the same class with the same name, but with different parameter lists (different types, numbers, or order of parameters).

### Method signature:

- Method identifier
- Java method signature = method name + order of parameter + type of parameter

#### Example

```java
class InNOut {
    // Order a basic burger
    public String order(String burger) {
        return "Order: " + burger;
    }

    // Order a burger with an addon (e.g., extra cheese)
    public String order(String burger, String addon) {
        return "Order: " + burger + " with " + addon;
    }

    // Order a custom burger with price calculation (e.g., patty weight)
    public double order(double price, double extra) {
        return price + extra;
    }
}

public class Main {
    public static void main(String[] args) {
        InNOut inNOut = new InNOut();

        System.out.println(inNOut.order("Cheeseburger"));                   // Calls order(String)
        System.out.println(inNOut.order("Cheeseburger", "Cheese"));          // Calls order(String, String)
        System.out.println(inNOut.order(5.00, 1.25));                       // Calls order(double, double)
    }
}

/*
output:
  Order: Cheeseburger
  Order: Cheeseburger with Bacon
  6.25
*/
```