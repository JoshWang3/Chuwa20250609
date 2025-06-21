package lambda;

public class LambdaExample {
    public static void main(String[] args) {
        StringTransformer upper = s -> s.toUpperCase();
        StringTransformer lower = s -> s.toLowerCase();

        System.out.println(upper.transform("Hello World")); // HELLO WORLD
        System.out.println(lower.transform("Hello World")); // hello world
    }
}
