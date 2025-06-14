**4.Explain why global variables are NOT recommended,you may use code snippets.**  
(1) Break Encapsulation -- If the variable is declared to be global, then the variable can be modified anywhere in the 
    program, makes it hard to keep track of who has this variable or to determine who depends on this variable.      
(2) Cannot track dependency -- A method can use such global variable without declaring it in its parameter, which makes it hard to know what does 
    the method use or change.  
(3) Thread-Safety Issues -- During the concurrent situation, unsynchronized access to a global can lead to race conditions and data corruption.  
    
```java
// External state: a static config value
public class Settings {
    public static String mode = "PRODUCTION";
}

public class Processor {
    // Uses external state Settings.mode
    public void process() {
        if (Settings.mode.equals("DEBUG")) {
            System.out.println("Verbose logging on");
        }
        // …
    }
}
```

**5. Explain why Strings in Java are considered "Immutable"?**  
(1) All the modification on a String variable, actually create a new variable  
The output of s2 is: hello world **Meaning: create a new string**
```java
String s1 = "hello";
        String s2 = s1.concat(" world");
// concat 返回一个新的 String；s1 仍然是 "hello"

        System.out.println(s1);  // 输出: hello
        System.out.println(s2);  // output: hello world 
```
(2) In the source code of Java’s String class, there is no method that allows modifying the contents of an existing String object.    
(3) In the source code of Java’s String class, the internal value field is declared private final, meaning once a String is constructed its contents cannot be changed.  
**6. Write code snippets to explain what does "Final" keyword do,and what we need it?**  
The final keyword can be applied on 3 cases: variable, method, class  
(1) Final Variable: Prevents reassignment once initialized.   
Why use it: Guarantees a constant value or stable reference.
```java
class Main {
public static void main(String[] args) {

    // create a final variable
    final int AGE = 32;

    // try to change the final variable
    AGE = 45;
    System.out.println("Age: " + AGE); // Output:AGE = 45; Cannot assign a value to final variable AGE
    
    }
}
```
(2) Final Method:  the final method CANNOT be overridden by the child class  
Why use it: Locks down behavior in a base class.  
```java
class FinalDemo {
    // create a final method
    public final void display() {
      System.out.println("This is a final method.");
    }
}

class Main extends FinalDemo {
  // try to override final method
  public final void display() {           // we will get a compilation error !! cannot override a final method
    System.out.println("The final method is overridden.");
  }

  public static void main(String[] args) {
    Main obj = new Main();
    obj.display();
  }
}
```
(3) Final Class: the final class CANNOT be inherited by another class  
Why use it: Enforces immutability or sealed behavior.  
```java
// create a final class
final class FinalClass {
  public void display() {
    System.out.println("This is a final method.");
  }
}

// try to extend the final class
class Main extends FinalClass {          // we will get a compilation error !! cannot inherit from final FinalClass
  public  void display() {
    System.out.println("The final method is overridden.");
  }

  public static void main(String[] args) {
    Main obj = new Main();
    obj.display();
  }
}
```
**7. Write code snippets to explain why Java is"pass-by-value",and why do some people think it might be "pass-by-reference"?**  
(1) Primitive types: When we pass a variable to a method, only **a copy of its value is passed**. As a result, the original variable remains unaffected.   

Notice that number stays 10 throughout, proving we did not modify the original variable.
```java
public class PassByValueDemo {

    public static void main(String[] args) {
        int number = 10;
        System.out.println("Before: " + number);    // Before: 10

        int abc = modifyNumber(number);            // call and capture return
        System.out.println("After: " + number + " " + abc);
        // number is still 10      abc is modifyNumber’s return
    }

    // Java copies the value of the variable number (e.g., 10) and passes that copy to the method parameter value (i.e., passes by value). 
    // Any modifications to value only affect that copy and do not change the original number.
    // Java 会把变量 number 的值（比如 10）复制 一份，传给方法的形参 value（也就是把值作为实参传递）。后续对 value 的修改只影响这份副本，不会改变原来的 number。
    public static int modifyNumber(int value) {
        value = value * 2;
        System.out.println("Modified: " + value);  // Modified: 20
        return value;
    }
}
```
(2) Reference Type: In Java, object parameters are passed by value in the form of their references.  
In the example below: this means a copy of the reference (i.e. the object’s memory address) is passed into modifyPoint(point),  
so any operations performed on point inside that method act on the original object instance thru the object address.
```java
public class PassByValueDemo {

    public static void main(String[] args) {
        Point point = new Point(2, 3);
        System.out.println("Before: " + point); // Output: Before: (2, 3)
        modifyPoint(point);
        System.out.println("After: " + point); // Output: After: (20, 30)
    }

    public static void modifyPoint(Point point) {
        point.setX(point.getX() * 10);
        point.setY(point.getY() * 10);
        System.out.println("Modified: " + point); // Output: Modified: (20, 30)
    }
}

class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
```
**8. Write code snippets to explain overloading in Java, explain how does Java define method signature.**  
(1) Different number of parameters  
```java
class methodOverloading {
private static void display(int a){
    System.out.println("Arguments: " + a);
}

    private static void display(int a, int b){
        System.out.println("Arguments: " + a + " and " + b);
    }

    public static void main(String[] args) {
        display(1);
        display(1, 4);
    }
}

```
(2) Different data type of parameters  
```java
class methodOverloading {
private static void display(int a){
    System.out.println("Arguments: " + a);
}

    // this method  accepts character object
    private static void display(char a){
        System.out.println("Got character object.");
    }

    public static void main(String[] args) {
        display(1);
        display(1, 4);
    }
}

```
**Method Signature**:  method’s name and the ordered list of its parameter types.  
An ordered list of parameter types: take the data types of a method’s parameters in the exact sequence they appear in the declaration.  
The order matters—changing it produces a different signature even if the same types are present.  
```java
// Signature: process(int, String, double)
void process(int a, String b, double c) { … }

// Different signature: process(String, int, double)
void process(String b, int a, double c) { … }
```
In the first method, the parameter-type list is (int, String, double)—that order is part of its signature.  
In the second, the list is (String, int, double), so it’s a completely separate signature.  

**10-1 Top K Frequent Elements(LeetCode347)**  
```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> frequency = new HashMap<>();
        for(int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        // Initialize a PriorityQueue and set the priority rule 
        // by using the lambda expression, which returns negative if pair1 has a higher priority than pair2
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair1[1] - pair2[1]);  

        for(Map.Entry<Integer, Integer> entry: frequency.entrySet()) {
            // the heap is not full, the size of the heap < k, add the current element
            if(pq.size() < k) {
                pq.add(new int[]{entry.getKey(), entry.getValue()});
            }
            // the heap is full: compare the value of incoming element and the heap root. 
            else {
                // If the imcoming > root, 
                if(pq.peek()[1] < entry.getValue()){
                    // delete the root
                    pq.poll();
                    // add the current entry’s key-value pair to the pq, which orders elements based on the value
                    pq.add(new int[]{entry.getKey(), entry.getValue()});
                }
            }
        }

        // Extract the top k keys from the min-heap into the result array in descending order of frequency
        int[] ans = new int[k];
        for(int i = k - 1; i >= 0; i--) {
            ans[i] = pq.poll()[0];
        }

        return ans;
    }
}
```
**10-2 Two Sum (LeetCode1)**  
```java
class Solution {
public int[] twoSum(int[] nums, int target) {
Map<Integer, Integer> m = new HashMap<>();

        for(int i = 0; ; i++){
            int x = nums[i];

            if(m.containsKey(target - x)){
                return new int[]{m.get(target - x), i};
            }
            m.put(x, i);
        }
    }
}
```
