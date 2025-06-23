package anonymous_class;

public class Test {
    public static void main(String[] args) {
        // normal concrete class
        Pets dog = new Dogs();
        dog.eat();
        dog.makeSound();
        // anonymous class
        Pets cat = new Pets() {
            // new implementation here
            @Override
            public void makeSound() {
                System.out.println("Meow!");
            }

            @Override
            void eat() {
                System.out.println("The cat is eating.");
            }
        };
        cat.eat();
        cat.makeSound();
    }
}
