public class PolymorphismDemo {
        /*
    Polymorphism:
    - same method name
    - different behavior
    - happens at runtime
    */

    public static void main(String[] args) {
        Animal a1 = new Dog1();
        Animal a2 = new Cat();

        a1.makeSound();  // Woof
        a2.makeSound();  // Meow
    }
}

// base class
class Animal {
    public void makeSound() {
        System.out.println("Animal sound");
    }
}

// child class
class Dog1 extends Animal {
    public void makeSound() {
        System.out.println("Woof");
    }
}

// another child
class Cat extends Animal {
    public void makeSound() {
        System.out.println("Meow");
    }
}
