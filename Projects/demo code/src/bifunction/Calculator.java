package bifunction;

import java.util.function.BiFunction;

public class Calculator {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> subtract = (a, b) -> a - b;
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        BiFunction<Integer, Integer, Integer> divide = (a, b) -> b != 0 ? a / b : 0;

        int a = 20, b = 5;

        System.out.println("Add: " + add.apply(a, b));
        System.out.println("Subtract: " + subtract.apply(a, b));
        System.out.println("Multiply: " + multiply.apply(a, b));
        System.out.println("Divide: " + divide.apply(a, b));
    }
}
