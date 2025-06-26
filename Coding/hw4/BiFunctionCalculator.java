package hw4;

import java.util.function.BiFunction;

public class BiFunctionCalculator {
    static BiFunction<Double, Double, Double> add = (a, b) -> a + b;
    static BiFunction<Double, Double, Double> subtract = (a, b) -> a - b;
    static BiFunction<Double, Double, Double> multiply = (a, b) -> a * b;
    static BiFunction<Double, Double, Double> divide = (a, b) -> a / b;

    public static double calculate(double t, double u, BiFunction<Double, Double, Double> operation) {
        return operation.apply(t, u);
    }

    public static void main(String[] args) {
        double num1 = 7.0;
        double num2 = 5.0;

        System.out.println("Addition: "+ calculate(num1, num2, add));
        System.out.println("Subtraction: "+ calculate(num1, num2, subtract));
        System.out.println("Multiplication: "+ calculate(num1, num2, multiply));
        System.out.println("Division: "+ calculate(num1, num2, divide));
    }

}
