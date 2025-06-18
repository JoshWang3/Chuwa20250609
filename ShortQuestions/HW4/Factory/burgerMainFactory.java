package Factory;

public class burgerMainFactory {
    public static void main(String[] args) {
        // create factory
        BurgerFactory cheeseBurgerFactory = new CheeseBurgerFactory();
        BurgerFactory veggieBurgerFactory = new VeggieBurgerFactory();
        BurgerFactory chickenBurgerFactory = new ChickenBurgerFactory();

        // use factory to create burger
        Burger b1 = cheeseBurgerFactory.createBurger();
        Burger b2 = veggieBurgerFactory.createBurger();
        Burger b3 = chickenBurgerFactory.createBurger();

        // print burger type
        System.out.println(b1.getBurgerType());
        System.out.println(b2.getBurgerType());
        System.out.println(b3.getBurgerType());
    }
}
