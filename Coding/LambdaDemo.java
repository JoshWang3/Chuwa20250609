package Coding;

@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

public class LambdaDemo {
    public static void main(String[] args) {
        // 使用 Lambda 实现加法
        MathOperation add = (a, b) -> a + b;
        
        // 使用 Lambda 实现乘法
        MathOperation multiply = (a, b) -> a * b;

        // 调用接口方法
        System.out.println("5 + 3 = " + add.operate(5, 3));       // 输出 8
        System.out.println("5 * 3 = " + multiply.operate(5, 3));  // 输出 15
    }

}
