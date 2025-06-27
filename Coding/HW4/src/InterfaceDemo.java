interface Animal {
    // Abstract method (must be implemented)
    void makeSound();

    // Default method: provides a base implementation
    default void eat() {
        System.out.println("Animal is eating (default method)");
    }

    // Static method: utility/helper, not inherited
    static void sleep() {
        System.out.println("Animal is sleeping (static method)");
    }
}

// Implementing class
class Dog implements Animal {
    // Must override abstract method
    public void makeSound() {
        System.out.println("Dog barks");
    }

    // Optionally override default method
    @Override
    public void eat() {
        System.out.println("Dog is eating (overridden default method)");
    }

    // Note: Cannot override static method from interface
    // public void sleep() {}  Not allowed
}

class Cat implements Animal {
    // Only override abstract method, use default `eat()`
    public void makeSound() {
        System.out.println("Cat meows");
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.makeSound(); // Dog barks
        dog.eat();       // Overridden default method

        Animal cat = new Cat();
        cat.makeSound(); // Cat meows
        cat.eat();       // Uses default implementation

        // Static method must be accessed via interface name
        Animal.sleep();  // Animal is sleeping (static method)
    }
}
