package hw4.q5;

import java.util.function.BiFunction;


public class Calculator {
    public static void main(String[] args) {

        // define BiFunctions using lambda expression
        BiFunction<Double, Double, Double> add = (x, y) -> x + y;
        BiFunction<Double, Double, Double> subtract = (x, y) -> x - y;
        BiFunction<Double, Double, Double> multiply = (x, y) -> x * y;
        BiFunction<Double, Double, Double> divide = (x, y) -> {
           if(y == 0) {
               throw new ArithmeticException("Division by zero");
           }
           return x / y;
        };

        System.out.println(add.apply(10.0, 5.0)); // 15.0
        System.out.println(subtract.apply(15.0, 10.0)); // 5.0
        System.out.println(multiply.apply(6.0, 5.0)); // 30.0
        System.out.println(divide.apply(4.0, 5.0)); //0.8

    }

}
