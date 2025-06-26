package hw4.interface_keywords;

public class Test {
    public static void main(String[] args) {
        Dogs dog = new Dogs();
        dog.makeSound();
        // call overridden default method
        dog.eat();
        // call static method using Animals interface
        Animals.sleep();
    }
}
