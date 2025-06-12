# HW2

## Why Global Variables are NOT Recommended

### 1. Tight Coupling
You will never know which part of code is depending on the global variables. This creates hidden dependencies that make code difficult to understand and maintain.

### 2. Data Racing Problems
There could be multiple threads trying to access the same global variables simultaneously.

### 3. Not Atomic (Not Thread-Safe)
Global variable operations are not atomic, making them unsafe in multi-threaded environments.

### 4. Hard to Test
Global state makes unit testing difficult because tests can interfere with each other.

## Why Strings in Java are Considered "Immutable"

### 1. Internal Implementation
String uses "private final char[]" to save characters internally. Once you initialize a string, you cannot modify it because of the "final" keyword.

### 2. New Object Creation
All the "modifications" (e.g. concatenation, slicing, etc.) will return a new String object, the original object will remain the same.

### 3. Thread Safety
String is thread-safe because of its immutability nature. It solves the synchronization problem across different threads.

### 4. Memory Optimization
JVM reuses strings in the String Pool thus improving memory usage and performance.

```java
public class StringPoolExample {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");
        
        System.out.println(str1 == str2);        // true - same reference in pool
        System.out.println(str1 == str3);        // false - different objects
        System.out.println(str1.equals(str3));   // true - same content
    }
}
```

## The "Final" Keyword and Why We Need It

The `final` keyword provides immutability and prevents accidental modifications:

```java
public class FinalKeywordExample {
    public static void main(String[] args) {
        final int SPEED_LIMIT = 60;
        // SPEED_LIMIT = 100; // Compile error - cannot reassign
        
        final List<String> names = new ArrayList<>();
        names.add("Alice");  // This is allowed - modifying content
        // names = new ArrayList<>(); // Compile error - cannot reassign reference
        
        System.out.println("Speed limit: " + SPEED_LIMIT);
    }
}
```

**Why we need it:**
- The "final" keyword makes it clear that `SPEED_LIMIT` is a constant
- Compile-time safety
- Improves code readability and intent

## Java is "Pass-by-Value" - Explanation and Common Misconceptions

**Why people think it's pass-by-reference:**
People think it's pass-by-reference because any changes to an object's state will be reflected back to the caller immediately. However, Java passes the *value* of the reference, not the reference itself.

## Method Overloading and Method Signatures in Java

Method overloading allows multiple methods with the same name but different signatures:

```java
class Printer {
    // Signature is print(int)
    void print(int x) {
        System.out.println("Printing int: " + x);
    }

    // Signature is print(String)
    void print(String s) {
        System.out.println("Printing String: " + s);
    }

    // Signature is print(int, int)
    void print(int x, int y) {
        System.out.println("Sum: " + (x + y));
    }
    
    // Signature is print(double)
    void print(double d) {
        System.out.println("Printing double: " + d);
    }
    
    // Different parameter order creates different signature
    void print(String s, int x) {
        System.out.println("String: " + s + ", Int: " + x);
    }
    
    void print(int x, String s) {
        System.out.println("Int: " + x + ", String: " + s);
    }
}

public class OverloadingExample {
    public static void main(String[] args) {
        Printer printer = new Printer();
        
        printer.print(42);              // Calls print(int)
        printer.print("Hello");         // Calls print(String)
        printer.print(10, 20);          // Calls print(int, int)
        printer.print(3.14);            // Calls print(double)
        printer.print("Test", 100);     // Calls print(String, int)
        printer.print(200, "Demo");     // Calls print(int, String)
    }
}
```

**Method Signature Definition:**
When you overload a method in Java, the method name, parameters (order and count), and parameter types will matter. The return type is NOT part of the method signature for overloading purposes.

## LeetCode using Java Collection Framework

### Solution 1: Top K Frequent Elements (LeetCode 347)

```java
import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Freq map
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(freqMap.entrySet());

        // Sort by frequency (descending), then by value (ascending)
        Collections.sort(entryList, (e1, e2) -> {
            int f1 = e1.getValue(), f2 = e2.getValue();
            if (f1 != f2) {
                return f2 - f1;  // Higher frequency first
            }
            return e1.getKey() - e2.getKey();  // Smaller value first for ties
        });

        // Get top k elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = entryList.get(i).getKey();
        }
        return result;
    }
}
```

### Solution 2: Two Sum (LeetCode 1)

```java
import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];
            int complement = target - currentNum;
            
            if (valueToIndex.containsKey(complement)) {
                return new int[] { valueToIndex.get(complement), i };
            }
            
            valueToIndex.put(currentNum, i);
        }
        
        return new int[0];
    }
}
```

## Additional Notes

### Employee Code Reference
This homework also includes practical implementations in the `employee_code` directory:
- **Employee.java**: Contains the Employee class implementation
- **Main.java**: Contains the usage of the Employee class
