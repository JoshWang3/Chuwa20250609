import java.util.function.BiFunction;

public class BiFunctionCalculator {
    public static void main(String[] args) {
        // add
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        // subtract
        BiFunction<Integer, Integer, Integer> subtract = (a, b) -> a - b;
        // multiply
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        // divide
        BiFunction<Integer, Integer, Integer> divide = (a, b) -> {
            if (b == 0) return 0; // avoid /0
            return a / b;
        };

        // test
        System.out.println("Add: " + add.apply(5, 3));        // 8
        System.out.println("Sub: " + subtract.apply(5, 3));    // 2
        System.out.println("Mul: " + multiply.apply(5, 3));    // 15
        System.out.println("Div: " + divide.apply(6, 3));      // 2
    }
}
