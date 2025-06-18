package Coding;
import java.util.function.BiFunction;
public class LambdaCalculator {
    public static int calculate(int a, int b, BiFunction<Integer, Integer, Integer> operation) {
        return operation.apply(a, b);
    }

    public static void main(String[] args) {
        // 定义四个运算
        BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
        BiFunction<Integer, Integer, Integer> subtract = (x, y) -> x - y;
        BiFunction<Integer, Integer, Integer> multiply = (x, y) -> x * y;
        BiFunction<Integer, Integer, Integer> divide = (x, y) -> {
            if (y == 0) {
                throw new ArithmeticException("Cannot divide by zero");
            }
            return x / y;
        };

        // 测试运算
        System.out.println("5 + 3 = " + calculate(5, 3, add));
        System.out.println("5 - 3 = " + calculate(5, 3, subtract));
        System.out.println("5 * 3 = " + calculate(5, 3, multiply));
        System.out.println("6 / 2 = " + calculate(6, 2, divide));
    }

}
