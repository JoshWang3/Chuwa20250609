package lambda_functional;
// the annotation is not required, but it does a sanity check to make sure there is only one abstract method
@FunctionalInterface
public interface Greeting {
    // only one abstract method
    void sayHello(String name);

    // can have default method since Java8
    default void sayGoodbye(String name) {
        System.out.println("Goodbye, " + name);
    }

    // can have static method since Java8
    static void info() {
        System.out.println("This is a functional interface");
    }
}
