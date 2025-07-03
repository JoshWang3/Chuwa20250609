public interface Animal {
    void speak();
}

public class Dog implements Animal {
    @java.lang.Override
    public void speak() {
        System.out.println("woof");
    }
}

public class Cat implements Animal {
    @java.lang.Override
    public void speak() {
        System.out.println("meow");
    }
}

public abstract class AnimalFactory {
    public abstract Animal createAnimal();
}

public class DogFactory extends AnimalFactory {
    @java.lang.Override
    public Animal createAnimal() {
        return new Dog();
    }
}

public class CatFactory extends AnimalFactory {
    @java.lang.Override
    public Animal createAnimal() {
        return new Cat();
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        AnimalFactory factory1 = new DogFactory();
        Animal dog = factory1.createAnimal();
        dog.speak(); // woof

        AnimalFactory factory2 = new CatFactory();
        Animal cat = factory2.createAnimal();
        cat.speak(); // meow
    }
}