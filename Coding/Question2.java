public class Question2 {
    public static void main(String[] args) {
        Animal dog1 = new Dog();
        dog1.speak();
        Animal.announce();

    }
}

interface Animal{
    //public void makeSound();

    default void speak(){
        System.out.println("I'm an animal");
    }

    static void announce(){
        System.out.println("This is an animal class");
    }
}

class Dog implements Animal{
    @Override
    public void speak() {
        System.out.println("I'm a dog");
    }
}