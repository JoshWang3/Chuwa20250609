package interface_keywords;

public class Dogs implements Animals {
    // abstract method implementation
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
    // overriding a default method
    @Override
    public void eat() {
        System.out.println("The dog is eating.");
    }
}

