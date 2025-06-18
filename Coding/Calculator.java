import java.util.*;
import java.util.function.BiFunction;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BiFunction<Double,Double,Double> add = (a,b)->a+b;
        BiFunction<Double,Double,Double> subtract = (a,b)->a-b;
        BiFunction<Double,Double,Double> multiply = (a,b)->a*b;
        BiFunction<Double,Double,Double> divide = (a,b)-> {
            if (b==0) {throw new ArithmeticException("Cannot divide by zero");}
            return a/b;
        };

        System.out.print("Enter first number: ");
        double a = sc.nextDouble();

        System.out.print("Enter operator (+, -, *, /): ");
        String op = sc.next();

        System.out.print("Enter second number: ");
        double b = sc.nextDouble();

        BiFunction<Double, Double, Double> operation;
        switch (op) {
            case "+":
                operation = add;
                break;
            case "-":
                operation = subtract;
                break;
            case "*":
                operation = multiply;
                break;
            case "/":
                operation = divide;
                break;
            default:
                System.out.println("Invalid operator.");
                return;
        }

        // Calculate and display result
        try {
            double result = operation.apply(a, b);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
