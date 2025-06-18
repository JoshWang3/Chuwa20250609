
import java.util.function.BiFunction;

public class Calculator {

    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> subtract = (a, b) -> a - b;
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        BiFunction<Integer, Integer, Integer> divide = (a, b) -> {
            if (b == 0) {
                throw new ArithmeticException("Division by zero is not allowed.");
            }
            return a / b;
        };

        System.out.println("Calculator addition: " + add.apply(5, 3));
        System.out.println("Calculator subtraction: " + subtract.apply(5, 3));  
        System.out.println("Calculator multiplication: " + multiply.apply(5, 3));
        System.out.println("Calculator division: " + divide.apply(5, 3));
       
    }
}