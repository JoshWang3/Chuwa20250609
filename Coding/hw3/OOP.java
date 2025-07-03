public abstract class Animal {
    // Encapsulation: setting private fields of String name and int age
    // with access/update by getter/setter methods
    private String name;
    private int age;

    public Animal(String name, int age) {
        setName(name);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Polymorphism: abstract class declare abstract makeSound() method
    // and subclass can override this method with their specific implementations
    public abstract String makeSound();
}

public class Dog extends Animal {
    // Inheritance: Dog class extends the abstract Animal class,
    // reusing the fields and methods from it
    public Dog(String name, int age) {
        super(name, age);
    }

    // Polymorphism: Dog class provides specific implementation
    // of the abstract method
    @Override
    public String makeSound() {
        return "woof";
    }
}