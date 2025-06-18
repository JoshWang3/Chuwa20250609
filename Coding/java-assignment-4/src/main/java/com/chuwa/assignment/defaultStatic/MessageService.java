package com.chuwa.assignment.defaultStatic;
// an interface with default + static
public interface MessageService {
    // Abstract method (must be implemented by classes)
    String getMessage(String name);

    // Default method - can have a body, and classes can override
    default void greet(String name){
        System.out.println("[Default] Hello, " + name + "!");
    }

    // Static method - accessible only via interface
    static boolean isValidName(String name){
        return name.matches("[a-zA-Z]+");
    }
}
