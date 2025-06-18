package com.chuwa.assignment.biFunction;

import java.util.function.BiFunction;

// BiFunction<T, U, R> is a built-in Java functional interface.
// It takes two input arguments(T, U) and returns result(R)
// This calculator shows how to use BiFunction with Lambda expression for math operations.
public class Calculator {
    public static void main(String[] args){
        System.out.println("\n=== Calculator Using BiFunction ===\n");

        double x = 10.0;
        double y = 5.0;

        // Lambda for addition
        BiFunction<Double, Double, Double> add = (a, b) -> a + b;
        // Lambda for subtraction
        BiFunction<Double, Double, Double> sub = (a, b) -> a - b;
        // Lambda for multiplication
        BiFunction<Double, Double, Double> multiply = (a, b) -> a * b;
        // Lambda for division
        BiFunction<Double, Double, Double> divide = (a, b) -> b != 0 ?  a / b : 0;

        System.out.println("Add: " + add.apply(x, y));
        System.out.println("Subtract: " + sub.apply(x, y));
        System.out.println("Multiply: " + multiply.apply(x, y));
        System.out.println("Divide: " + divide.apply(x, y));
    }
}

// Explanation
// • BiFunction<T, U, R> is part of java.util.function
// • It represents a function that takes two inputs and returns a result
// • We use it with lambda expressions to avoid writing full class implementations
// * add.apply(x, y) applies the lambda to inputs x and y
// Benefit:
// - Concise and expressive logic
// - Clear syntax
// - Perfect for calculators, converters, and decision rules