package DefaultAndStatic;

interface Burger {

    // Abstract Method: must implemented by class
    String getType();

    // Default Method: provide a default implementation, can be called / overridden by implementing classes
    default void printDefaultBurger() {
        System.out.println("This is a default classic burger");
    }

    // Static Method: utility method, called on interface itself
    static void printStaticBurgerInfo() {
        System.out.println("All burgers are made fresh daily!");
    }
}

class CheeseBurger implements Burger {
    @Override
    public String getType() {
        return "Cheese Burger";
    }

    // override default method: this is optional
    @Override
    public void printDefaultBurger() {
        System.out.println("This is a default classic burger overridden with cheese");
    }
}

class VeggieBurger implements Burger {
    @Override
    public String getType() {
        return "Veggie Burger";
    }

    // not override veggie burger here, return default implementation
}

public class BurgerInterfaceDemo {
    public static void main(String[] args) {
        Burger cheeseBurger = new CheeseBurger();
        Burger veggieBurger = new VeggieBurger();

        System.out.println(cheeseBurger.getType());
        cheeseBurger.printDefaultBurger(); // overridden before

        System.out.println(veggieBurger.getType());
        veggieBurger.printDefaultBurger(); // will be default value

        // static method called on interface, not obj
        Burger.printStaticBurgerInfo();

        // static, can't do
        // cheeseBurger.printStaticBurgerInfo();
    }
}