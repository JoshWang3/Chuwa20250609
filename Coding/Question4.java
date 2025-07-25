import java.util.Arrays;
import java.util.function.*;

public class Question4 {
    public static void main(String[] args) {

        //my own functional interface
        NameCard fullName = names -> String.join(" ", names);
        String[] name = new String[]{"John", "Doe"};
        System.out.println(fullName.name(name));

        Greet greet = () -> System.out.println("Hello");
        greet.greeting();



        //JDK's functional interface
        //Consumer - void - do something
        Consumer<String> printIt =System.out::println;
        printIt.accept("Hello");

        //Supplier - get - return a value
        Supplier<String> passcode = () -> "1234567";
        System.out.println(passcode.get());

        //Assertion - test - test a value return bool
        Predicate<Integer> isGreater5 = i -> i>5;
        System.out.println(isGreater5.test(5));

        //Functional - apply - take a value, return a value
        BiFunction<Double, Double, Double> multiplication = (a, b) -> a*b;
        System.out.println(multiplication.apply(1.0, 2.0));


    }
}

@FunctionalInterface
interface NameCard{
    String name(String[] names);
}

@FunctionalInterface
interface Greet{
    void greeting();
}