package hw4.q4;

public class GreetingDemo {
    public static void main(String[] args) {

        // Casual greeting
        GreetingService casual = (name) -> System.out.println("Hello " + name);

        // Formal greeting
        GreetingService formal = (name) -> System.out.println("Good morning " + name);

        casual.greet("John");
        formal.greet("Bob");
    }
}
