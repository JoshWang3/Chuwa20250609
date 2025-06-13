1. Inside Coding folder
2. Employee objects are stored on heap. That is to say, objects that are created by new Employee() are stored on heap. However, the employee1 and employee2 vairables are stored on stack. They are only reference to the actual object on heap. The Employee.class is stored in method area.
3. Static objects are created when class is loaded by jvm, normal objects are created when class objects are created
4. Global variables can be modified from anywhere, making it hard to track who changed what and when. By encapsulation and using getter and setter they are be better managed
5. For memory effiency reasons, string contents are stored in a shared pool. There are also security and thread-safety reasons
6. 
```
final static int num = 6;
num = 5; // will give error
```
final keyword is used to declare constant, a variable, method, or class declared with final cannot be modified or extended
7.
```
public class Test {
public static void change(int x) { x = 10; }

public static void main(String[] args) {
    int a = 5;
    change(a);
    System.out.println(a);
}
```

5 will be printed instead of 10, which means the change function modifies the copy, not the original number

However, some code might behave like passing by reference
```
class demo {
    public int value = 5;
}

class Test {
    public static void change(Box b) {
        b.value = 10;
    }

    public static void main(String[] args) {
        Box demo = new demo();
        change(box);
        System.out.println(box.value); // Output: 10
    }
}
```
The value inside demo is modified, that is because although a copy is passed, the copy might point to the same heap object, which modifies the object.
8. That defines signature in Java are parameter type and order and method name
```
public int test(int a, int b)
public double test(int a, int b)
// will cause problem, return type doesn't differ signature
// changing the second line into the line below will work:
public double test(int a, double b)
```
9. /
10. 
Two sum:
```
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> myMap = new HashMap<>(); // map of number / position
        for (int n = 0; n < nums.length; n++) {
            if (myMap.containsKey(target-nums[n])) {
                return new int[]{n, myMap.get(target-nums[n])};
            }
            myMap.put(nums[n], n);
        }
        return new int[]{};
    }
    // Runtime O(n), space complexity O(n)
```
Top K Frequent Elements:
```
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
        for (int num : nums) {
            myMap.put(num, myMap.getOrDefault(num, 0)+1);
        }

        List<Integer>[] l = new List[nums.length+1];
        for (int key : myMap.keySet()) {
            int frequency = myMap.get(key);
            if (l[frequency] == null) {
             l[frequency] = new ArrayList<>();
            }
            l[frequency].add(key);
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (int n = l.length-1; n >= 0 && ans.size() < k; n--) {
            if (l[n] != null) {
                ans.addAll(l[n]);
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
```