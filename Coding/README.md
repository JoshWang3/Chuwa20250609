**1. Address.java, Department.java, Employee.java**  
**2. Show how the Employee objects are allocated to JVM memory (EmployeeMemory.java)**  

- **Metaspace**: Class metadata (like `Employee.class`, its methods, and static fields) lives here. This data is shared across all Employee instances — it only loads once when the class is first used.

- **Heap**: Every time you call `new Employee(...)`, a **unique object** is created in the heap.  
  Each instance has its own set of fields like `id` and `name`.  
  You can confirm this by printing `System.identityHashCode(obj)` — all values will differ.

- **Reflection Proof**:  
  By comparing method arrays (`Method[]`) from two different `Employee` objects,  
  we saw that they both refer to the same method definitions in Metaspace.  
  This verifies that **all instances share the same class-level metadata**.


**3. Showed how `static` content behaves differently from instance content during class instantiation (EmployeeStatic.java)** 

- `Employee.getNextId()` was called **before any object was created**, showing that `static` fields belong to the **class** and not to any specific object.
- After creating two `Employee` objects (`e1` and `e2`), each had its own `id` and `name`, proving that instance fields are stored separately in the **heap**.
- The static field `nextId` increased from 1 to 3 and was shared between both instances.

This shows:
- Static fields are **class-level (one copy shared by all objects)**.
- Instance fields are **object-level (each object has its own copy)**.


**4. Why global variables are NOT recommended**  
- In Java, `public static` variables behave like **global variables** — accessible and mutable from anywhere.  Although convenient, they introduce several risks that make code **fragile, hard to test, and prone to bugs**.

```java
public class GlobalExample {
    public static int counter = 0;   // global variable
    
    public static void incrementCounter() {
        counter++;                  
    }

    public static void main(String[] args) {
        incrementCounter();
        System.out.println("Counter: " + counter); // 1

        // Another part of the program directly modifies it
        counter = 100;                           // unexpected overwrite
        System.out.println("Counter: " + counter); // 100
    }
```

- **Uncontrolled access from anywhere: ** Any class in the program can modify counter, even accidentally, making it hard to trace bugs or enforce logic.
- **No encapsulation or protection: ** this would lose the ability to restrict how or when the variable is changed. It breaks the object-oriented principle of data hiding.
- **Hidden dependencies: ** Methods or classes using the global variable don’t declare it. Thus, the dependency is implicit. This makes code harder to understand and maintain.
- **Race conditions in multithreading: **  In concurrent programs, multiple threads may read/write the global variable at the same time, causing inconsistent or corrupted state.


**5. Why is String immutable in Java?**  
- In Java, String is immutable because its class is declared final, meaning it cannot be subclassed. All its internal fields like the character array, are private final, and there are no setters to modify the content once created. This design ensures that String objects can be safely shared in the **string constant pool** without risk of unexpected modification.

```java
public class StringEquality {
    public static void main(String[] args) {
        String s1 = "apple";
        String s2 = "apple";                 // from string constant pool
        String s3 = new String("apple");     // new object in heap

        System.out.println("s1 == s2? " + (s1 == s2));         // true: same pool reference
        System.out.println("s1 == s3? " + (s1 == s3));         // false: different objects
        System.out.println("s1.equals(s3)? " + s1.equals(s3)); // true: same content

        s3 = s3.replace("b", "s");  // creates new object
        System.out.println("After replace, s3 = " + s3);       // apple
        System.out.println("s1 = " + s1);                      // still "apple"
    }
}

```

**6. What does 'Final' keyword do and what we need it?**  

- In Java, the `final` keyword is used to indicate that something **cannot be changed** once assigned. It can be applied to **variables**, **methods**, and **classes**, each with its own purpose.
- **final variables**: A `final` variable can only be assigned once. After its value is set, it cannot be changed.

```java

public class Main {
    public static void main(String[] args) {
        final int x = 10;
        System.out.println(x); // Output: 10

        // x = 20; // This line would cause a compile-time error
    }
}

```

- In this example, x is declared as a final variable and initialized with the value of 10. Once a final variable is assigned a value, it cannot be changed. If you attempt to reassign a value to x as shown in the commented line, the compiler will throw an error.

- **final methods:** Declaring a method as final prevents it from being overridden by subclasses

```java
class ParentClass {
    public final void displayMessage() {
        System.out.println("This is a final method.");
    }
}

class ChildClass extends ParentClass {
    // Attempting to override the final method will cause a compilation error
    // public void displayMessage() {
    //     System.out.println("Attempting to override.");
    // }
}

public class Main {
    public static void main(String[] args) {
        ChildClass child = new ChildClass();
        child.displayMessage(); // Output: This is a final method.
    }
}

```
- **final classes:** cannot be subclassed. It prevents inheritance and make class immutable. For example, the String class in Java is a final class. 

```java
final class FinalClass {
    private int value;

    public FinalClass(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
```

**7. Why Java Is "Pass-by-Value", and why do some people think it might be "pass-by-reference"?**  

Java is **strictly pass-by-value** — it always passes a *copy of the variable* into a method.  
However, this behavior looks confusing when you're dealing with **objects**, because what's being copied is the **reference value**, not the object itself.

```java
public class PassByValueDemo {

    static void change(int x) {
        x = 9;
    }

    static void touch(Box b) {
        b.v = 9;
    }

    static void swap(Box b) {
        b = new Box(); // only reassigns local copy
        b.v = 7;
    }

    static class Box {
        int v = 1;
    }

    public static void main(String[] args) {
        int n = 5;
        change(n);
        System.out.println("n = " + n); // Output: n = 5

        Box box = new Box();
        touch(box);
        System.out.println("box.v after touch = " + box.v); // Output: 9

        swap(box);
        System.out.println("box.v after swap = " + box.v); // Still 9
    }
}
```
- Java is pass-by-value. For primitives, the value is copied. For objects, the reference (address) is copied — not the object itself. That’s why we can mutate an object inside a method, but reassigning the reference has no effect outside the method.

**8. Explain overloading in Java, explain how does Java define method signature**  

```java
public class Overload {
    
    // same method name, different parameters
    public void greet() {
        System.out.println("Hello");
    }

    public void greet(String name) {
        System.out.println("Hello " + name);
    }

    public void greet(String name, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println("Hello " + name);
        }
    }

    // This will NOT compile — return type doesn't matter
    // public int greet(String name) { return 1; }
    
    public static void main(String[] args) {
        Overload ex = new Overload();
        ex.greet();                      // Hello
        ex.greet("Alice");              // Hello Alice
        ex.greet("Bob", 2);             // Hello Bob (x2)
    }
}

```
- **Method overloading: ** multiple methods with the **same name** but **different parameter lists** in the same class.

Java uses **method signature** to differentiate overloaded methods.  
The **method signature** includes: the method **name**, the **number**, **type**, and **order** of parameters.	
The return type is **not** part of the method signature in overloading.
Thus greet(), greet(String), and greet(String,int) are three distinct signatures.

**10-1 Top K Frequent Elements(LeetCode347)**  
```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // TC: O(Nlogk), SC: O(N) -logk is to maintain heap size k
        // Store the frequency in hashmap
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for(int n: nums){
            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
        }

        // Minheap to sort by the frequency in ascending order
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> Integer.compare(freqMap.get(a), freqMap.get(b)));
        for(int n: freqMap.keySet()){
            heap.offer(n);
            if(heap.size() > k){
                heap.poll();
            }
        }

        int[] res = new int[k];
        for(int i = k - 1; i >= 0; i--){
            res[i] = heap.poll();
        }

        return res;
    }
}
```

**10-2 Two Sum (LeetCode1)**  
```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Time: O(n), Space: O(n)
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{ map.get(complement), i };
            }
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }
}

```

