package LambdaCalculator;
import java.util.function.BiFunction;

public class LambdaCalculator {
    public static void main(String[] args) {
        double x = 15.0;
        double y = 3.0;

        add(x, y);
        subtract(x, y);
        multiply(x, y);
        divide(x, y);
        divide(x, 0);

    }

    // Addition
    public static void add(double x, double y) {
        BiFunction<Double, Double, Double> add = (a, b) -> a + b;
        System.out.println(add.apply(x, y));
    }

    // Subtraction
    public static void subtract(double x, double y) {
        BiFunction<Double, Double, Double> subtract = (a, b) -> a - b;
        System.out.println(subtract.apply(x, y));
    }

    // Multiplication
    public static void multiply(double x, double y) {
        BiFunction<Double, Double, Double> multiply = (a, b) -> a * b;
        System.out.println(multiply.apply(x, y));
    }

    // Division
    public static void divide(double x, double y) {
        BiFunction<Double, Double, Double> divide = (a, b) -> b == 0 ? Double.NaN : a / b;
        System.out.println(divide.apply(x, y));
    }
}