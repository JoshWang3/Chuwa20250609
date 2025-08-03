class Animal {
    public void sound() {
        System.out.println("Animal makes a sound");
    }

    // Overloaded methods
    public void eat(String food){
        System.out.println("Animal eats " + food);
    }

    public void eat(int foodAmount) {
        System.out.println("Animal eats " + foodAmount + " units of food");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    @Override
    public void sound() {
        System.out.println("Cat meows");
    }
}

public class PolymorphosmSample {
    public static void main(String[] args) {
        Animal myAnimal = new Dog(); // Polymorphism
        myAnimal.sound(); // Output: Dog barks

        myAnimal = new Cat(); // Polymorphism
        myAnimal.sound(); // Output: Cat meows

        myAnimal.eat("meat"); // Calls the String version
        myAnimal.eat(5); // Calls the int version


        /*
         * Dog barks 
         * Cat meows
         * Animal eats meat
         * Animal eats 5 units of food
         */
    }
}
