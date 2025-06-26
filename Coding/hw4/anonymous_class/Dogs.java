package hw4.anonymous_class;

public class Dogs extends Pets {
    @Override
    void eat() {
        System.out.println("The dog is eating");
    }

    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}
