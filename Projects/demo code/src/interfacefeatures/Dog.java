package interfacefeatures;

public class Dog implements Animal {
    @Override
    public void sound() {
        System.out.println("Woof!");
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.sound();           // default 方法
        Animal.info();         // static 方法
    }
}
