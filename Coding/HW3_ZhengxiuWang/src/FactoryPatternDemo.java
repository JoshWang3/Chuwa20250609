// 分辨 猫狗不同声音

public class FactoryPatternDemo {
    public static void main(String[] args) {
        AnimalFactory factory = new AnimalFactory();

        Animal myDog = factory.createAnimal("dog");
        if (myDog != null) myDog.makeSound();

        Animal myCat = factory.createAnimal("cat");
        if (myCat != null) myCat.makeSound();
    }
}

interface Animal {
    void makeSound();
}

class Dog implements Animal {
    public void makeSound() {
        System.out.println("Woof!");
    }
}

class Cat implements Animal {
    public void makeSound() {
        System.out.println("Meow!");
    }
}

class AnimalFactory {
    public Animal createAnimal(String type) {
        if (type.equals("dog")) {
            return new Dog();
        } else if (type.equals("cat")) {
            return new Cat();
        } else {
            System.out.println("Unknown type: " + type);
            return null;
        }
    }
}
