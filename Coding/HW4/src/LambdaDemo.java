@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

public class LambdaDemo {
    public static void main(String[] args) {
        // Lambda for addition
        MathOperation add = (a, b) -> a + b;

        // Lambda for subtraction
        MathOperation subtract = (a, b) -> a - b;

        // Lambda for multiplication
        MathOperation multiply = (a, b) -> a * b;

        // Lambda for division (with check)
        MathOperation divide = (a, b) -> b != 0 ? a / b : 0;

        // Test
        System.out.println("Add: " + add.operate(5, 3));         // 8
        System.out.println("Subtract: " + subtract.operate(5, 3)); // 2
        System.out.println("Multiply: " + multiply.operate(5, 3)); // 15
        System.out.println("Divide: " + divide.operate(6, 3));     // 2
    }
}
