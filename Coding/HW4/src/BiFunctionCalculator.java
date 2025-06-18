import java.util.function.BiFunction;

public class BiFunctionCalculator {
    public static void main(String[] args) {
        // Define operations using BiFunction and lambdas
        BiFunction<Double, Double, Double> add = (a, b) -> a + b;
        BiFunction<Double, Double, Double> subtract = (a, b) -> a - b;
        BiFunction<Double, Double, Double> multiply = (a, b) -> a * b;
        BiFunction<Double, Double, Double> divide = (a, b) -> {
            if (b == 0) {
                System.out.println("Cannot divide by zero!");
                return 0.0;
            }
            return a / b;
        };

        // Test cases
        double x = 10.0;
        double y = 5.0;

        System.out.println("Add: " + calculate(x, y, add));         // 15.0
        System.out.println("Subtract: " + calculate(x, y, subtract)); // 5.0
        System.out.println("Multiply: " + calculate(x, y, multiply)); // 50.0
        System.out.println("Divide: " + calculate(x, y, divide));     // 2.0
    }

    // Generic calculator method using BiFunction
    public static double calculate(double a, double b, BiFunction<Double, Double, Double> operation) {
        return operation.apply(a, b);
    }
}
