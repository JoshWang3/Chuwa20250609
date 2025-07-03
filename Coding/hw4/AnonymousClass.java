public class AnonymousClass {
    abstract static class Animal {
        String name;
        Animal(String name) {
            this.name = name;
        }
        abstract void speak();
        void getAnimal() {
            System.out.println("This animal is a " + name);
        }
    }

    public static void main(String[] args) {
        // Provides specific implementation of an abstract class
        // inline on its subclass initialization
        Animal dog = new Animal("dog") {
            @java.lang.Override
            void speak() {
                System.out.println("woof");
            }
        };
        dog.speak(); // woof
        dog.getAnimal(); // This animal is a dog
    }
}