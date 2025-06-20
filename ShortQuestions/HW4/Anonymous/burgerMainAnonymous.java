package Anonymous;

// Interface
// Any class that implements this interface must provide an implementation for getType()
interface Burger {
    String getType();
}

// Abstract class (parent abstract class)
// Can’t be instantiated directly
// Requires a subclass (or anonymous class) to provide the body for completeOrder().
abstract class BurgerOrder {
    abstract void completeOrder();
}

// Concrete class (concrete child class)
// Has a full implementation and can be instantiated directly
class Bun {
    public String getBunType() {
        return "Regular wheat bun";
    }
}


public class burgerMainAnonymous {
    public static void main(String[] args) {

        // Anonymous class for interface
        Burger spicyBurger = new Burger() {
            @Override
            public String getType() {
                return "Spicy Chicken Burger (from anonymous class)";
            }
        };
        System.out.println(spicyBurger.getType());


        // Anonymous class for abstract parent class
        BurgerOrder makeYourOwnBurger = new BurgerOrder() {
            @Override
            void completeOrder() {
                System.out.println("Order complete: Double cheese burger with no pickles (from anonymous class)");
            }
        };
        makeYourOwnBurger.completeOrder();


        // Anonymous class for concrete child class
        Bun customeBun = new Bun() {
            @Override
            public String getBunType() {
                return "Gluten free bun (overridden in anonymous class)";
            }
        };
        System.out.println(customeBun.getBunType());
    }
}
