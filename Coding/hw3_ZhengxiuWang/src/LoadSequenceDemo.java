public class LoadSequenceDemo {
        /*
    static block → runs once
    instance block → runs before constructor
    constructor → runs per object
    */
    public static void main(String[] args) {
        System.out.println("Main starts");
        CoffeeMachine m1 = new CoffeeMachine();
        CoffeeMachine m2 = new CoffeeMachine();
    }
}

class CoffeeMachine {
    // static block → runs once when class is loaded
    static {
        System.out.println("System loading...");
    }

    // instance block → runs before constructor, every time
    {
        System.out.println("Pre-heating...");
    }

    // constructor → runs after instance block
    public CoffeeMachine() {
        System.out.println("CoffeeMachine ready!");
    }
}
