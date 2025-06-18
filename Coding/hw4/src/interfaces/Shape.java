package interfaces;

public interface Shape {
    // Default method - can be overridden
    default void draw() {
        System.out.println("Draw Shape");
    }

    // Static method - cannot be overridden, belongs to interface
    static void show() {
        System.out.println("Show shape");
    }
}
