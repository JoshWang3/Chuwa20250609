#### 4. Explain why global variables are NOT recommended, you may use code snippets.
Global variables are variables that are accessible from any part of a program, e.g. `public static` fields in a class.
1. When methods rely on global state, it's not clear from their signatures what data they depend on. This makes code harder to understand.
2. In multi-threaded applications, global variables create race conditions and thread-safety problems. Multiple threads accessing and modifying the same global state can lead to unpredictable issues.
3. Global variables create tight coupling between different parts of your program. Changes to the global variable's type or behavior can break code throughout the entire application.

_Better Alternatives:_
- Dependency Injection: Pass required data as parameters
- Encapsulation: Keep data within appropriate class instances


#### 5. Explain why Strings in Java are considered "Immutable"?
1. String Pool Optimization (memory-efficient) - Java uses a String pool to store string literals. When you create string literals, Java can reuse existing objects. This optimization is only safe because strings can't be modified. If strings were mutable, changing one reference would affect all others.
2. Strings are often used for sensitive data like: URLs, usernames and passwords, file paths, network connections, etc. If strings were mutable, malicious code could potentially modify these values.
3. Immutable objects are inherently thread-safe. Multiple threads can access the same String without synchronization because no thread can modify it.

#### 6. Write code snippets to explain what does "Final" keyword do, and why we need it?
1. Final Variables (Constants) - cannot be reassigned once initialized:
```java
public class FinalVariableExample {
    public static void main(String[] args) {
        final int MAX_USERS = 100;
        MAX_USERS = 200;  // COMPILE ERROR! Cannot assign a value to final variable
        
        // Why we need it: Creates true constants
        final double PI = 3.14159;
        // PI will always be 3.14159 throughout the program
    }
}
```
2. Final Methods - Cannot Be Overridden
Ensures critical behavior isn't accidentally changed. 
```java
class Parent {
    public final void printMessage() {
        System.out.println("This method cannot be changed");
    }
    
    public void normalMethod() {
        System.out.println("This can be overridden");
    }
}

class Child extends Parent {
    // @Override
    // public void printMessage() {  // COMPILE ERROR! Cannot override final method
    //     System.out.println("Trying to change it");
    // }
    
    @Override
    public void normalMethod() {  // This is OK
        System.out.println("Successfully overridden");
    }
}
```
3.  Final Classes - Cannot Be Extended
Prevents inheritance for security or design reasons. Example: String, Integer, and other wrapper classes are final
```java 
final class SecurityManager {
    public void checkPermission() {
        System.out.println("Checking security...");
    }
}

// class HackedManager extends SecurityManager {  // COMPILE ERROR!
//     // Cannot extend final class
// }
```

#### 7. Write code snippets to explain why Java is "pass-by-value", and why do some people think it might be "pass-by-reference"?
- Pass-by-value: The method gets a copy of the value (even if that value is a reference)
- Pass-by-reference: The method gets the actual variable itself (can reassign the original variable)
- In Java, when you pass a parameter to a method, you're always passing a copy of the value stored in the variable:
    - For primitives (int, double, boolean, etc.): You pass a copy of the actual value
    - For objects: You pass a copy of the reference value (the memory address), not the object itself. for example:
```java 
public static void main(String[] args) {
    Person person = new Person("Alice");
    reassignPerson(person);
    System.out.println(person.name); // Still prints "Alice"!
}

public static void reassignPerson(Person p) {
    p = new Person("Bob"); // Only reassigns the local copy of the reference
}
```
- Here's an example explains "Why People Think It's Pass-by-Reference" (People see that the object was modified and think "this might be pass-by-reference!"):
```java
public static void main(String[] args) {
    Person person = new Person("Alice");
    changeName(person);
    System.out.println(person.name); // Prints "Bob" - the object was modified!
}

public static void changeName(Person p) {
    p.name = "Bob"; // Modifies the object through the reference
}
```


#### 8. Write code snippets to explain overloading in Java, explain how does Java define method signature.
- Overloading is when you have multiple methods with the same name but different parameters (types or number or order) in the same class.
```java
public class Calculator {
    // Method with 2 parameters
    public int add(int a, int b) {
        return a + b;
    }
    
    // Overloaded method with 3 parameters
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.add(5, 10));        // Calls first method: 15
        System.out.println(calc.add(5, 10, 20));    // Calls second method: 35
    }
}
```
- **how does Java define method signature?**
    - In Java, a method signature consists of:
      - Method name
      - Parameter list (number, types, and order of parameters)
    - The method signature does NOT include:
      - Return type
      - Access modifiers (public, private, etc.)
      - Thrown exceptions
      - Parameter names (only their types matter)
```java 
public class Example {
    // Method signature: calculate(int, int)
    public int calculate(int a, int b) {
        return a + b;
    }
}
```
#### 10. Use Java collection framework data structures (e.g. Set, Map, List) to solve following Leetcode questions, you MUST use Java and you MUST use data structures provided by Java Collection framework:
##### 10.1 Top K Frequent Elements（LeetCode 347）
```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int num : nums){
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
            new Comparator<int[]>(){
                public int compare(int[] m, int[] n){
                    return m[1] - n[1];
                }
            }
        );

        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()){
            int num = entry.getKey();
            int count = entry.getValue();
            if (pq.size() == k){
                if (pq.peek()[1] < count){
                    pq.poll();
                    pq.offer(new int[]{num, count});
                }
            }else{
                pq.offer(new int[]{num, count});
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++){
            res[i] = pq.poll()[0];
        }
        return res;
        
    }
}
```

##### 10.2 Two Sum（LeetCode 1）
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<Integer,Integer>();
        for (int i=0; i<n; i++){
            int need = target - nums[i];
            if (map.containsKey(need)){
                return new int[]{map.get(need), i};
            }
            map.put(nums[i],i);
        }
        return new int[0];
    }
}
```