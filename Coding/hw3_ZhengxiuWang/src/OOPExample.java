// Parent class
/*
- Encapsulation: `name` is private, use getter
- Inheritance: Dog extends Pet
- Polymorphism: speak() is overridden
*/
class Pet {
    private String name;  // Encapsulation

    public Pet(String name) {
        this.name = name;
    }

    public void speak() {  // Polymorphism
        System.out.println("Pet makes sound");
    }

    public String getName() {
        return name;
    }
}

// Child class
class Dog extends Pet {   // Inheritance
    public Dog(String name) {
        super(name);
    }

    public void speak() {   // Polymorphism: method override
        System.out.println(getName() + " says: Woof!");
    }
}

// Main class
public class OOPExample {
    public static void main(String[] args) {
        Pet myDog = new Dog("Buddy");  // Polymorphism: parent type, child object
        myDog.speak();  // Output: Buddy says: Woof!
    }
}
