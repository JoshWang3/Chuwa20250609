import java.util.function.BiFunction;

public class Calculator {
    private static double cslculate(double a, double b, BiFunction<Double, Double, Double> operation) {
        return operation.apply(a, b);
    }

    public static void main(String[] args) {
        BiFunction<Double, Double, Double> add = (a, b) -> a + b;
        BiFunction<Double, Double, Double> sub = (a, b) -> a - b;
        BiFunction<Double, Double, Double> mult = (a, b) -> a * b;
        BiFunction<Double, Double, Double> div = (a, b) -> {
            if (b == 0) {
                throw new Exception("Denominator cannot be 0")
            }
            return x/y;
        }

        double a = 6.0, b = 2.0;
        System.out.println("Add: " calculate(a, b, add));
        System.out.println("Sub: " calculate(a, b, sub));
        System.out.println("Mult: " calculate(a, b, mult));
        System.out.println("Div: " calculate(a, b, div));
    }
}