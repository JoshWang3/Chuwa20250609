package Coding;


//Singleton Pattern
class SimpleSingleton {
    private static SimpleSingleton instance = null;
    private SimpleSingleton() {}

    public static SimpleSingleton getInstance() {
        if (instance == null) {
            instance = new SimpleSingleton();
        }
        return instance;
    }

    public void sayHello() {
        System.out.println("Hello from Singleton!");
    }
}

// Factory Method Pattern
interface Animal {
    void speak();
}

class Dog implements Animal {
    public void speak() {
        System.out.println("Woof!");
    }
}

class AnimalFactory {
    public static Animal createAnimal(String type) {
        if (type.equals("dog")) {
            return new Dog();
        }
        return null;
    }
}

//Abstract Factory Pattern
interface Button {
    void paint();
}

class WindowsButton implements Button {
    public void paint() {
        System.out.println("Windows Button");
    }
}

interface GUIFactory {
    Button createButton();
}

class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }
}

//Builder Pattern

class Meal {
    String drink;
    String food;

    public void show() {
        System.out.println("Drink: " + drink + ", Food: " + food);
    }
}

class MealBuilder {
    Meal meal = new Meal();

    public MealBuilder addDrink(String drink) {
        meal.drink = drink;
        return this;
    }

    public MealBuilder addFood(String food) {
        meal.food = food;
        return this;
    }

    public Meal build() {
        return meal;
    }
}



public class patternDemo {

    

    public static void main(String[] args) {
        // Singleton
        SimpleSingleton.getInstance().sayHello();

        // Factory Method
        Animal dog = AnimalFactory.createAnimal("dog");
        if (dog != null) dog.speak();

        // Abstract Factory
        GUIFactory factory = new WindowsFactory();
        Button button = factory.createButton();
        button.paint();

        // Builder
        Meal meal = new MealBuilder()
                        .addDrink("Coke")
                        .addFood("Burger")
                        .build();
        meal.show();
    }
}



