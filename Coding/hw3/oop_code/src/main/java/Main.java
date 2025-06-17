import Animal.Animal;
import Animal.Cat;
import Animal.Dog;

public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat("Kiki", 3, "Domestic Shorthair", true);
        Dog dog = new Dog("Momo", 5, "Husky", true);

        System.out.println("\n==== Cat example ====");
        cat.displayInfo();
        cat.eat();
        cat.makeNoise();
        cat.messAround();
        cat.sleep();
        cat.setName("Kikokiko");
        System.out.println(cat.getName());

        System.out.println("\n==== Dog example ====");
        dog.displayInfo();
        dog.eat();
        dog.makeNoise();
        dog.fetchBall();
        dog.sleep();
        dog.setName("Momomomo");
        System.out.println(dog.getName());
    }
}
