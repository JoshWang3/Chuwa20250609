package org.calculator;

import java.util.function.BiFunction;

public class Calculator {

    // Lambda expressions using BiFunction for arithmetic operations
    private static final BiFunction<Double, Double, Double> ADD = (a, b) -> a + b;
    private static final BiFunction<Double, Double, Double> SUBTRACT = (a, b) -> a - b;
    private static final BiFunction<Double, Double, Double> MULTIPLY = (a, b) -> a * b;
    private static final BiFunction<Double, Double, Double> DIVIDE = (a, b) -> {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a / b;
    };

    public double add(double a, double b) {
        return ADD.apply(a, b);
    }

    public double subtract(double a, double b) {
        return SUBTRACT.apply(a, b);
    }

    public double multiply(double a, double b) {
        return MULTIPLY.apply(a, b);
    }

    public double divide(double a, double b) {
        return DIVIDE.apply(a, b);
    }

    /**
     * Generic method that accepts any BiFunction operation
     * @param a first operand
     * @param b second operand
     * @param operation BiFunction to perform the calculation
     * @return result of the operation
     */
    public double calculate(double a, double b, BiFunction<Double, Double, Double> operation) {
        return operation.apply(a, b);
    }

    /**
     * Main method to demonstrate calculator usage
     */
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        double num1 = 10.0;
        double num2 = 5.0;

        System.out.println("Calculator Demo:");
        System.out.println("================");

        // Using instance methods
        System.out.println("Addition: " + num1 + " + " + num2 + " = " + calc.add(num1, num2));
        System.out.println("Subtraction: " + num1 + " - " + num2 + " = " + calc.subtract(num1, num2));
        System.out.println("Multiplication: " + num1 + " * " + num2 + " = " + calc.multiply(num1, num2));
        System.out.println("Division: " + num1 + " / " + num2 + " = " + calc.divide(num1, num2));

        System.out.println("\nUsing generic calculate method:");
        System.out.println("===============================");

        // Demonstrating division by zero handling
        System.out.println("\nDivision by zero test:");
        System.out.println("=====================");
        try {
            calc.divide(10.0, 0.0);
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}