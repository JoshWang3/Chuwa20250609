# HW2

## 1. Create a Java POJO: `Employee`

Inside the `Employee` class, include the following fields:

1. Employee's name
2. Employee's Date of Birth
3. Employee's Department (can be another POJO)
4. Employee's Social Security Number
5. Employee's Home Address (can be another POJO)

### Requirements:
- Override `toString()` method: Print out a meaningful and readable representation of the `Employee` object.
- Override `equals()` method: Ensure two employees with identical information are considered the same.

### Answer:
- [Employee.java](..%2F..%2FCoding%2Fhw2%2FEmployee.java)
- [Department.java](..%2F..%2FCoding%2Fhw2%2FDepartment.java)
- [Address.java](..%2F..%2FCoding%2Fhw2%2FAddress.java)
---

## 2. Instantiate Employee Instances

- Create at least **two instances** of the `Employee` class.
- Use code snippets to **explain how these objects are allocated in JVM memory**.
- Optionally use **Java Reflection** utilities for demonstration.

### Answer:
- [Task2.java](..%2F..%2FCoding%2Fhw2%2FTask2.java)
- Class definition is stored in the Method area, e1.getClass() returns a reference to the Employee.class metadata stored in the method area. All Employee objects share this same class info.
- Object instances stored in Heap area, e1 and e2 are two different Employee objects in the heap. Their hashCode() values differ, showing they occupy different memory locations.
- Object References (e.g., variables e1, e2) is stored in Stack
- Field[] and Method[] arrays stored in Heap area, even though e1m[0].equals(e2m[0]) is true (they represent the same method), e1m[0] == e2m[0] is false (they are different objects in memory).
  Their hashCode() values may match if computed from content, not memory address.

## 3. Static Utilities

- Write **static methods** in the `Employee` class.
- Demonstrate how **static content differs** from instance content during class instantiation.

### Answer:
```java
    // Static method
    public static String staticMethod() {
        return "This is a static method";
    }
    
    //utilize it without initialization
    System.out.println(Employee.staticMethod());
```
---

## 4. Why Global Variables Are Not Recommended

- Explain with code snippets **why global variables are discouraged** in Java.

### Answer:

- Break encapsulation
- Create tight coupling between classes
- Make code harder to test, debug, and maintain
- Lead to unexpected side effects (especially in multithreading)
```java
public class GlobalData {
  public static int counter = 0; // Global-like variable
}

public class Main {
  public static void main(String[] args) {
    GlobalData.counter++;
    System.out.println("Counter: " + GlobalData.counter);
  }
}
```

---

## 5. Why Are Strings Immutable in Java?

- Explain **why `String` is immutable** in Java.
- Anwser:
- Because Strings are stored in the String constant pool, a special area of the heap, it helps save memory by reusing immutable string instances.

---

## 6. The `final` Keyword

- Provide code snippets that explain:
    - What the `final` keyword does.
    - Why and when we need it.
- Answer:
  - The final keyword is used to indicate that a variable, method, or class cannot be changed, overridden, or extended.
  - When you want to create constants or ensure a variable value stays unchanged, Or a method cannot be overrided
```java
public class FinalVariableExample {
    public static void main(String[] args) {
        final int x = 10;
        // x = 20; // ❌ Error: cannot assign a value to final variable 'x'
        System.out.println(x);
    }
}
```
---

## 7. Java is "Pass-by-Value"

- Explain with code snippets:
    - Why Java is considered **pass-by-value**.
    - Why it might be **misunderstood as pass-by-reference**.

- Answer:
  - In Java, all method arguments are passed by value — meaning a copy of the variable is passed. However, with objects, this can be confusing, leading some to mistakenly think Java is pass-by-reference.
```java
public class PrimitiveExample {
  public static void changeValue(int num) {
    num = 100;
  }

  public static void main(String[] args) {
    int x = 50;
    changeValue(x);
    System.out.println("x = " + x); // Output: x = 50
  }
}
```
---

## 8. Method Overloading in Java

- Provide code snippets to:
    - Demonstrate **method overloading**.
    - Explain how Java **defines method signatures**.
- Answer:
  -  Method overloading means defining multiple methods with the same name but different parameter lists (method signatures) within the same class.
```java
public class Calculator {

  // Method with 2 int parameters
  public int add(int a, int b) {
    return a + b;
  }

  // Method with 3 int parameters
  public int add(int a, int b, int c) {
    return a + b + c;
  }

  // Method with 2 double parameters
  public double add(double a, double b) {
    return a + b;
  }

  public static void main(String[] args) {
    Calculator calc = new Calculator();
    System.out.println(calc.add(2, 3));         // 5
    System.out.println(calc.add(1, 2, 3));      // 6
    System.out.println(calc.add(2.5, 3.1));     // 5.6
  }
}
```
---

## 9. Code Demo

- You may be asked to **demo and explain** your answers or code in class.

---

## 10. Leetcode Practice with Java Collection Framework

Use Java collection framework data structures such as `Set`, `Map`, `List` to solve the following Leetcode problems:

1. **Top K Frequent Elements** (LeetCode 347)
2. **Two Sum** (LeetCode 1)

### Notes:
- You **MUST use Java**
- You **MUST use data structures provided by the Java Collection Framework**
- This task is **separate from daily Leetcode questions**. Submit your solutions **as part of this assignment**, not in the Leetcode sheet.

### Answer

1. **Top K Frequent Elements** (LeetCode 347)
```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] freq = new int[k];
        //hashmap store index, frequency
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<HashMap.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> {
            return b.getValue() - a.getValue();
        }); 

        for(Map.Entry<Integer, Integer> entry: freqMap.entrySet()){
            pq.offer(entry);
        }  
        while(k > 0){
            freq[k-1] = pq.poll().getKey();
            k--;
        }
        return freq;

        
    }
}
```
2. **Two Sum** (LeetCode 1)
```java
class Solution {
  public int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for(int i = 0; i < nums.length; i++){
    int diff = target - nums[i];
    if(map.containsKey(diff)){
    return new int[]{i, map.get(diff)};
    }
    map.put(nums[i],i);
    }
    return null;
  }
}
```

---
## 11. Submission Format

You can either:
- Include your code snippets in a markdown file, **or**
- Include them in a Java project.