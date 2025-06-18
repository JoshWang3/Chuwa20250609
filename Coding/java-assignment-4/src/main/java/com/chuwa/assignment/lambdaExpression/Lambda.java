package com.chuwa.assignment.lambdaExpression;

public class Lambda {
    public static void main(String[] args) {
        System.out.println("\n=== Lambda Expression===\n");

        // Lambda for addition
        MathOperation add = (a, b) -> a + b;
        // Lambda for subtraction
        MathOperation sub = (a, b) -> a - b;
        // Lambda for multiplication
        MathOperation mul = (a, b) -> a * b;
        // Lambda for division
        MathOperation div = (a, b) -> b != 0 ? a / b: 0;

        double x = 12.0, y = 4.0;
        System.out.println("Add: " + add.calculate(x, y));
        System.out.println("Sub: " + sub.calculate(x, y));
        System.out.println("Mul: " + mul.calculate(x, y));
        System.out.println("Div: " + div.calculate(x, y));
    }
}

// Explanation
// • Lambda expression help eliminate repetitive, unnecessary code
//  compare to writing full anonymous classes.
// • A functional interface has only one abstract method.
// • Lambdas automatically match the method signature.
// -> Lambda make code more readable for functional-style APIs like streams and event handlers.
