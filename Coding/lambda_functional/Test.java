package lambda_functional;

public class Test {
    public static void main(String[] args) {
        // lambda function provides implementation of sayHello()
        Greeting greet = (name) -> {
            System.out.println("Hello, " + name);
        };
        // lambda
        greet.sayHello("Yunlu");
        // default
        greet.sayGoodbye("Yunlu");
        // static: called by interface name
        Greeting.info();
    }
}
