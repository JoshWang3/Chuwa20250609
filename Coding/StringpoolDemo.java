package Coding;

public class StringpoolDemo {
    public static void main(String[] args) {
        String a = "hello";
        String b = "hello";

        System.out.println("a == b: " + (a == b));             // true，引用相同（来自String Pool）
        System.out.println("a.equals(b): " + a.equals(b));     // true，内容也相同
    }

}
