package Coding;

import java.util.ArrayList;
//In Java, wrapper classes are object representations of the primitive data types.


public class WrapperExample {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        // Autoboxing: 自动将 int 转为 Integer
        list.add(10);
        list.add(20);
        list.add(30);

        int sum = 0;

        for (Integer num : list) {
            // Unboxing: 自动将 Integer 转为 int
            sum += num;
        }

        System.out.println("Sum = " + sum);  // 输出: Sum = 60
    }

}
