# 6.11 HW2 - Java Core

1. Write a Java POJO (plain old java object) named "Employee", inside Employ class, you should have

   - Employee's name
   - Employee's Date of Birth
   - Employee's Department (it can be another POJO)
   - Employee's Social Security Number
   - Employee's home address (it can be another POJO)

   Override toString method, so that when an Employee object is being printed, the print out looks meaningful and readble.

   Override equals method, so that only when two Employees have identical information, we consider they are the same employee.

   [Employee.java](../../Coding/hw2/Employee.java)

   [Department.java](../../Coding/hw2/Department.java)

   [Address.java](../../Coding/hw2/Address.java)
   
   


2. Write code to instantiate at least two instances of above Employee class, use code snippets to explain how these Employee objects are allocated to JVM memory. You may use java relection utilities to demonstrate it.

   [JvmMemory.java](../../Coding/hw2/JvmMemory.java) - Check the comments for explanation
   
   


3. Write static utilities in your Employee class, demostrate how static content differs from others during class instantiation.

   [Employee.java](../../Coding/hw2/Employee.java) - At the end of code
   
   


4. Explain why global variables are NOT recommended, you may use code snippets.

   Global variables in Java can be achieved by using static variables (class variables), which are class-level information stored in the Heap shared across all threads. If we use too many static variables, it can take up a lot of heap memory. Unlike local variables, which are stored in the stack in every thread, using global variables is not thread-safe. 

   In addition, global variables can be changed from anywhere, so it's hard to maintain and debug the code. It also violated encapsulation in OOP, which reduces code clarity.

   ```java
   public class GlobalVariableDemo {
   
       // static variable, shared across all threads
       public static int counter = 0;
   
       public static void main(String[] args) throws InterruptedException {
           // Create two threads that increment the counter
           Thread t1 = new Thread(() -> {
               for (int i = 0; i < 1000; i++) {
                   counter++;
               }
           });
   
           Thread t2 = new Thread(() -> {
               for (int i = 0; i < 1000; i++) {
                   counter++;
               }
           });
   
           t1.start();
           t2.start();
           t1.join();
           t2.join();
   
           System.out.println("Final counter value: " + counter);
       }
   }
   
   ```

   In the code snippet above, the output of "Final counter value" may not be 2000 as expected, because both threads modify `counter` simultaneously.
   
   


5. Explain why Strings in Java are considered "Immutable"?

   ```java
   public final class String
       implements java.io.Serializable, Comparable<String>, CharSequence {
       /** The value is used for character storage. */
       private final char value[];
   }
   ```

   In the JDK implementation, the String class is final, and the underlying character array is final as well, making it immutable.

   The intentions behind string immutability:

   1. Strings are often used to store some private information like file paths, database URLs, passwords, etc. So it shouldn't be changed.

   2. Strings are stored in the Spring Constant Pool in the heap. When two strings have the same content, they can share the same object reference in the pool, which helps save memory.

   3. Strings are often used as keys in the hash table. When it is immutable, the hashcode() value can be cached, no need for recalculation, which improves the performance of hash-based collections.

      

6. Write code snippets to explain what does "Final" keyword do, and what we need it?

   The final keyword means cannot be changed, which can be used on a variable, method, and class.

   - final variable: can be used to define constants

   ```java
   public class FinalVariableDemo {
       public static void main(String[] args) {
           final int x = 10;
           x = 20; // Compile error: cannot assign a value to final variable
           System.out.println(x);
       }
   }
   ```

   - final method: prevent overriding by subclass, so the behavior of critical methods cannot be changed 

   ```java
   class Parent{
       public final void finalMethod() {
           System.out.println("Final method in Parent");
       }
   }
   
   class Child extends Parent{
   // Cannot be overriden
       public void finalMethod() {
           System.out.println("Final method in Child");
       }
   }
   
   public class FinalMethodDemo {
       public static void main(String[] args) {
           new Child().finalMethod();
       }
   }
   ```

   - final class: prevent inheritance, like Integer, String etc and make class immutable to control over the class behavior

   ```java
   final class FinalClass {
       public void show() {
           System.out.println("Inside final class");
       }
   }
   
   class SubClass extends FinalClass {} // Compile error: Cannot inherit from final class
   
   public class FinalClassExample {
       public static void main(String[] args) {
           new FinalClass().show();
       }
   }
   ```

   

7. Write code snippets to explain why Java is "pass-by-value", and why do some people think it might be "pass-by-reference"?

   "Pass-by-value" means the function receives a copy of the variable's value as parameter while "pass-by-reference" means the function receives the memory address(reference) as parameter.

   - Java is "pass-by-value", the example of passing primitive data type as parameters as follow:

     In the swap() method, the values of a and b are swapped, which doesn't affect x and y. Because the values of a and b are copied from x and y, no matter how the contents of copies are modified, x and y are themselves.	

     ```java
     public class Demo {
         public static void main(String[] args) {
             int x = 5;
             int y = 10;
             swap(x,y);
             System.out.println("x = " + x + " y = " + y);
         }
     
         public static void swap(int a, int b){
             int temp = a;
             a = b;
             b = temp;
             System.out.println("a = " + a + " b = " + b);
         }
     }
     
     // Output:
     // a = 10 b = 5
     // x = 5 y = 10
     ```

   - Some people may think Java might be "pass-by-reference" when passing reference data type parameters, because in this example, the first element in the array has changed. However, it is still passing the value. The value here is the memory address of the array object, but the object intenally changed.

     ```java
         public static void main(String[] args) {
             int[] arr = { 1, 2, 3, 4, 5 };
           	System.out.println(arr[0]);
           	change(arr);
           	System.out.println(arr[0]);
         }
     
         public static void swap(int a, int b){
             // change the first element in the array to 0
           	array[0] = 0;
         }
     }
     
     // Output:
     // 1
     // 0
     ```

     
     

8. Write code snippets to explain overloading in Java, explain how does Java define method signature.

   Overloading means multiple methods can have the same name, but different parameter list (method signature) within the same class.

   ```java
   public class Demo {
       public static int plusMethodInt(int a, int b) {
           return a + b;
       }
   
       public static double plusMethodDouble(double a, double b) {
           return a + b;
       }
       public static void main(String[] args) {
           int calculation1 = plusMethodInt(8, 5);
           double calculation2 = plusMethodDouble(4.3, 6.26);
           System.out.println(calculation1);
           System.out.println(calculation2);
       }
   }
   ```

   Method signature: method name + parameter list (type + order + number)

   

9. Use Java collection framework datastructures (e.g. Set, Map, List) to solve following Leetcode questions, you MUST use Java and you MUST use datastructures provided by Java Collection framwork:

   1. Top K Frequent Elements（LeetCode 347）
   2. Two Sum（LeetCode 1）

   This is a separate coding task other than daily leetcoding questions, please submit your solutions as part of this assignment, rather than in the leetcoding sheet.

   Top K Frequent Elements（LeetCode 347）

   ```java
   class Solution {
       public int[] topKFrequent(int[] nums, int k) {
           HashMap<Integer, Integer> counter = new HashMap<>();
           for (int num : nums) {
               counter.put(num, counter.getOrDefault(num, 0) + 1);
           }
           PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((entry1, entry2) -> {
               return entry1.getValue().compareTo(entry2.getValue());
           });
           for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
               pq.offer(entry);
               if (pq.size() > k) {
                   pq.poll();
               }
           }
           int[] res = new int[k];
           for (int i = k - 1; i >= 0; i--) {
               res[i] = pq.poll().getKey();
           }
           return res;
       }
   }
   ```

   Two Sum（LeetCode 1）

   ```java
   class Solution {
       public int[] twoSum(int[] nums, int target) {
           HashMap<Integer, Integer> hashmap = new HashMap<>();
           for (int i = 0; i < nums.length; i++) {
               int need = target - nums[i];
               if (hashmap.containsKey(need)) {
                   return new int[]{hashmap.get(need), i};
               }
               hashmap.put(nums[i], i);
           }
           return null;
       }
   }
   ```

   

You may be asked to demo and explain your answers/code in class. You can either have your code snippets in your markdown file, or include in a Java project.