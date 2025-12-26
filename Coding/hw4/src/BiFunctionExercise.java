import java.util.function.BiFunction;

public class BiFunctionExercise {
    public static void main(String[] args) {

        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

        BiFunction<Integer, Integer, Integer> subtract = (a, b) -> a - b;

        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;

        BiFunction<Integer, Integer, Integer> divide = (a, b) -> a / b;

        int addition = add.apply(2, 3);
        System.out.println(addition);

        int subtraction = subtract.apply(2, 3);
        System.out.println(subtraction);

        int multiplication = multiply.apply(2, 3);
        System.out.println(multiplication);

        int division = divide.apply(2, 3);
        System.out.println(division);
    }
}
