### hw2

### 1. 
Write a Java POJO (plain old java object) named "Employee", inside Employ class, you should have
    1. Employee's name
    2. Employee's Date of Birth
    3. Employee's Department (it can be another POJO)
    4. Employee's Social Security Number
    5. Employee's home address (it can be another POJO)
* Override toString method, so that when an Employee object is being printed, the print out looks meaningful and readable.
* Override equals method, so that only when two Employees have identical information, we consider they are the same employee.

#### All code is located at: `Coding/hw2/01-employee`
[Employee](../../Coding/hw2/01-employee/src/Employee.java)

[Address](../../Coding/hw2/01-employee/src/Address.java)


### 2.
Write code to instantiate at least two instances of above Employee class, use code snippets to explain how these Employee objects are allocated to JVM memory. 
You may use java reflection utilities to demonstrate it.

[Main](../../Coding/hw2/01-employee/src/Main.java)

### 3. 
Write static utilities in your Employee class, demonstrate how static content differs from others during class instantiation.

[Employee](../../Coding/hw2/01-employee/src/Employee.java)

- **Static variables** belong to the **class**, not objects.
- **Instance variables** belong to **each object**.

Key Differences:
- **Memory**: Static content is stored once in memory; instance variables are stored per object.
- **Access**: Static variables can be accessed via class name; instance variables require an object.
- **Initialization**: Static variables are initialized when the class loads; instance variables are initialized during object creation.
- **Sharing**: Static variables are shared across all instances; instance variables are not.



### 4. Explain why global variables are NOT recommended, you may use code snippets.
- A global variable is accessible from anywhere in the code.

Key Problems:
- **Tight Coupling**: Increases dependency between classes/modules.
- **Hard to Debug**: Any part of code can modify it → hard to trace bugs.
- **Thread Safety Issues**: Shared mutable state leads to race conditions.

Example:
```java
public class GlobalExample {
    static int count = 0; // global variable

    public static void increment() {
        count++;
    }

    public static void reset() {
        count = 0;
    }

    public static void main(String[] args) {
        increment();
        reset();
        System.out.println(count); // value can be changed anywhere
    }
}

// Hard to know which method modified count.
```
Better Alternative: Encapsulation
```java
public class Counter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

// In this example, count is controlled and safer.
```


### 5. Explain why Strings in Java are considered "Immutable"?
- A String is immutable because **its value cannot be changed after it is created**.

Key Reasons:
- **Stored in String Pool**: Allows reuse and memory optimization.
- **Security**: Prevents data tampering (e.g., in file paths, network connections).
- **Thread-Safety**: Safe to use across threads without synchronization.
- **HashCode Caching**: Immutable strings can cache their hash code for faster lookup.

Technical Explanation:
- `String` is a **final class**.
- Internally uses a **final `char[]` array**, meaning the character contents can't be altered.

Example:
```java
String s = "abc";
s.concat("def"); // this returns a new String, doesn't change 's'
```


### 6. Write code snippets to explain what "Final" keyword does, and why we need it?
- `final` means **no further modification is allowed** to variable, method, or class.


- **Final Variable**: Value assigned once, cannot be changed.
- **Final Method**: Cannot be overridden by subclasses.
- **Final Class**: Cannot be inherited.

Examples:
1. Final Variable
```java
final int x = 5;
x = 10; // ❌ Error: cannot assign a value to final variable
```

2. Final Method
```java
class A {
    final void show() {}
}
class B extends A {
    void show() {} // ❌ Error: cannot override final method
}
```

3. Final Class
```java
final class A {}
class B extends A {} // ❌ Error: cannot subclass final class
```


### 7. Write code snippets to explain why Java is "pass-by-value", and why do some people think it might be "pass-by-reference"?
- Java is always "pass-by-value".


- For **primitives**: the actual value is copied.
- For **objects**: the reference (memory address) is copied. Java passes a copy of the reference, not the actual object or reference itself.

Examples:
1. Primitive (clear Pass-by-Value)
```java
public class PrimitiveExample {
    public static void main(String[] args) {
        int x = 10;
        System.out.println("Before: x = " + x); // 10

        modifyPrimitive(x);
        System.out.println("After: x = " + x);  // Still 10 ❌ Not changed
    }

    public static void modifyPrimitive(int value) {
        value = 20; // Only changes the copy
        System.out.println("Inside method: value = " + value); // 20
    }
}
```

2. Object – Field Modified (looks like Pass-by-Reference)
```java
public class ObjectExample {
    public static void main(String[] args) {
        Person person = new Person("John");
        System.out.println("Before: " + person.name); // John

        modifyObject(person);
        System.out.println("After: " + person.name);  // Alice ✅ Changed!

        // This is why people think it's pass-by-reference
    }

    public static void modifyObject(Person p) {
        // p is a COPY of the reference, pointing to same object
        p.name = "Alice"; // Modifies the actual object
    }
}

class Person {
    String name;
    Person(String name) { this.name = name; }
}
```

3. Object – Reference Reassigned (proof it's still Pass-by-Value)
```java
public class ProofExample {
    public static void main(String[] args) {
        Person person = new Person("John");
        System.out.println("Before: " + person.name); // John

        reassignObject(person);
        System.out.println("After: " + person.name);  // Still John ❌

        // If it were pass-by-reference, this would be "Bob"
    }

    public static void reassignObject(Person p) {
        // p is a copy of the reference
        p = new Person("Bob"); // Only reassigns the copy
        // Original reference remains unchanged
    }
}
```

Why People Get Confused:
- Changing object **fields** works → looks like pass-by-reference.
- But changing the **reference itself** does not affect the original → proves pass-by-value.


In Summary:
- Java always passes copies of values.
- Copy of reference still points to same object.
- Modifying object ✅ works (through reference copy).
- Reassigning reference ❌ fails (only affects copy).


### 8. Write code snippets to explain overloading in Java, explain how does Java define method signature.
- Defining multiple methods with the same name but different **parameters** in the same class.

Method Signature:
- **Definition**: Method name + parameter types (number, type, order).
- Return type is NOT part of the signature.

Examples:
1. Valid Overloading – Different Parameters

```java
class MathUtil {
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

2. Invalid Overloading – Only Return Type Differs (Not Allowed)
```java
// ❌ Compilation Error
int multiply(int a, int b) { return a * b; }
double multiply(int a, int b) { return a * b; } // Same signature

```


### 10. Use Java collection framework data structures (e.g. Set, Map, List) to solve following Leetcode questions, you MUST use Java and you MUST use data structures provided by Java Collection framework:
#### 10.1  LeetCode 347. Top K Frequent Elements
```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: HashMap
        Map<Integer, Integer> num2Freq = new HashMap<>();
        for (int num : nums) {
            num2Freq.put(num, num2Freq.getOrDefault(num, 0) + 1);
        }

        // Step 2: MinHeap
        Queue<Integer> minHeap = new PriorityQueue<>((a, b) ->
                Integer.compare(num2Freq.get(a), num2Freq.get(b)));

        for (int num : num2Freq.keySet()) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.remove(); // remove the least frequent element
            }
        }

        // Step 3: Collect result
        int[] res = new int[k];
        for (int i  = 0; i < k; i++) {
            res[i] = minHeap.poll();
        }

        return res;
    }
}
```

#### 10.2  LeetCode 1. Two Sum（ 
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> num2Index = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int currNum = nums[i];
            int diff = target - currNum;
            if (num2Index.containsKey(diff)) {
                return new int[] { num2Index.get(diff), i };
            }
            num2Index.put(currNum, i);
        }
        // Return an empty array if no solution is found
        return new int[] {};
    }
}
```