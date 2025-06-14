# Chuwa Hw2

### Question 1

See the Employee.java and Department.java and Address.java in coding folder

### Question 2

See the Main.java in coding folder

### Question 3

See the Main.java in coding folder

### Question 4

Global variables are not recommended because they make code hard to maintain and test. Especially it will introduce risks in multithreaded environments, see code below

```java
public class Counter {
    public static int count = 0; // Global variable (static, shared across all)

    public void increment() {
        count++;
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        Counter a = new Counter();
        Counter b = new Counter();

        a.increment();
        b.increment();

        System.out.println(Counter.count); // Output: 2 — shared state
    }
}
```

it will outputs 2, but in fact, we want 1.

### Question 5

Because for every string in Java, we actually have a constant pool for the string. So for example if you change some String, it actually just create a new String in the pool not change the original one, so that’s why we say String is immutable.

### Question 6

For final variable, we can’t change it’s value. For final method, we can’t override it. For final class, we can not extend it. Why we need final keyword, because sometimes we don’t want something to be modified. For example pi for circle is usually 3.1415926 and we don’t want it to be modified to other numbers like 5.

```java
public static final double PI = 3.14159;
class Animal {
    public final void speak() {
        System.out.println("Animal speaks");
    }
} // cannot be override
```

### Question 7

For primitive types, it passes a copy of the value, so it won’t affect the original value. But for object types like array, it actually pass the copy of the reference, so it seems like pass by reference and will affect the original value.

```java
public class Demo1 {
    public static void change(int x) {
        x = 100;
    }

    public static void main(String[] args) {
        int a = 5;
        change(a);
        System.out.println(a); // Still 5
    }
}
public class Demo2 {
    public static void modifyArray(int[] arr) {
        arr[0] = 99;          
    }

    public static void main(String[] args) {
        int[] nums = {10, 20, 30};
        modifyArray(nums);
        System.out.println(nums[0]); // output is 99
    }
}
```

### Question 8

```java
public class Printer {
    public void print(String s) {
        System.out.println("String: " + s);
    }

    public void print(int i) {
        System.out.println("Integer: " + i);
    }

    public void print(String s, int i) {
        System.out.println("String & Integer: " + s + ", " + i);
    }
}
```

signature: method name + parameter types (in order)

### Question 10

```java
public class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            heap.offer(new int[]{entry.getValue(), entry.getKey()});
            if (heap.size() > k) {
                heap.poll();
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = heap.poll()[1];
        }
        return res;
    }
}
```

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                return new int[] { numMap.get(complement), i };
            }
            numMap.put(nums[i], i);
        }
        return new int[] {}; // No solution found
    }
}
```