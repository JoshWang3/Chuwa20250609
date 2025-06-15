import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class hw2 {

    public static void finaDemo() {
        final int Maxuser = 100;
        //Maxuser = 200; //无法将值赋给 final 变量 'Maxuser
        final StringBuilder sb = new StringBuilder("hello");
        sb.append(" world"); // 可以改变内容
        //sb = new StringBuilder("bye");  // 无法将值赋给 final 变量 'sb'

    }

    ///
    public static void PassByValueDemo(){
        int a = 10;
        modifyPrimitive(a);
        System.out.println("a after modifyPrimitive: " + a); // 输出 10，不变

        // === 值传递（引用类型） ===
        StringBuilder sb = new StringBuilder("hello");
        modifyReference(sb);
        System.out.println("sb after modifyReference: " + sb); // 输出 hello world，内容变了

        resetReference(sb);
        System.out.println("sb after resetReference: " + sb);
    }

    public static void modifyPrimitive(int x) {
        x = 999;
    }

    // 引用类型：传的是“引用的副本” → 可以修改原对象内容
    public static void modifyReference(StringBuilder builder) {
        builder.append(" world");
    }

    // 把引用指向新对象：不会影响原引用
    public static void resetReference(StringBuilder builder) {
        builder = new StringBuilder("reset"); // 只是修改了形参的引用，不影响外部变量
    }


    ///

    public static void overloadDemo() {
        sayHello();
        sayHello("Alice");
        sayHello("Bob", 3);
    }

    public static void sayHello() {
        System.out.println("Hello!");
    }

    public static void sayHello(String name) {
        System.out.println("Hello, " + name + "!");
    }

    public static void sayHello(String name, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println("Hello, " + name + "! #" + (i + 1));
        }
    }


    public int[] topKFrequent(int[] nums, int k) {


        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0)+1);


        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0]; // 先按 freq 升序
            return a[1] - b[1]; // 如果 freq 相等，再按 num 升序
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            int num = entry.getKey();
            int freq = entry.getValue();
            heap.offer(new int[]{freq,num});
            if(heap.size()>k){
                heap.poll();
            }
        }

        int[] result = new int[k];

        for (int i = k-1; i>=0; --i){
            result[i] = heap.poll()[1];
        }
        return result;


    }

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i<nums.length; i++){
            int targetNum = target - nums[i];
            if (map.containsKey(targetNum)){
                return new int[] {map.get(targetNum), i};
            }
            map.put(nums[i],i);
        }

        return new int[] {};

    }


}
