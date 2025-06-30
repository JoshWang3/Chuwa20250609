package hw4.q2;

public interface Vehicle {

    // Default method (can be overridden by implementing class)
    default void start() {
        System.out.println("Vehicle is starting");
    }

    // Static method (called with Interface name)
    static void printInfo() {
        System.out.println("Vehicle Info");
    }

    // Abstract method (must be implemented)
    void drive();
}
