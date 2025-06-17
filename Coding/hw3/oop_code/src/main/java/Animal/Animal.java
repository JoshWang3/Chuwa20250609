package Animal;

public class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println("I'm eating");
    }

    public void sleep() {
        System.out.println("I'm sleeping");
    }

    public void makeNoise() {
        System.out.println("I'm making noise");
    }

    public void displayInfo() {
        System.out.println("My name is " + name + ", I'm " + age + " years old");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("I'm changing my name to \"" + name + "\"");
        this.name = name;
    }
}
