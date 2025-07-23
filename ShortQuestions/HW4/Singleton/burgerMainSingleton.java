package Singleton;

public class burgerMainSingleton {
    public static void main(String[] args) {

        // Eager Burger Singleton
        Burger b1 = EagerBurgerSingleton.getInstance().getBurger();
        System.out.println("Eager: " + b1);

        EagerBurgerSingleton.getInstance().setBurger(new Burger("Fish", false));
        System.out.println("Eager (after change): " + EagerBurgerSingleton.getInstance().getBurger());

        // Lazy Burger Singleton
        Burger b2 = LazyBurgerSingleton.getInstance().getBurger();
        System.out.println("Lazy: " + b2);

        LazyBurgerSingleton.getInstance().setBurger(new Burger("Veggie", true));
        System.out.println("Lazy (after change): " + LazyBurgerSingleton.getInstance().getBurger());

        // Confirm singleton property
        System.out.println("Eager singleton is unique: " + (EagerBurgerSingleton.getInstance() == EagerBurgerSingleton.getInstance()));
        System.out.println("Lazy singleton is unique: " + (LazyBurgerSingleton.getInstance() == LazyBurgerSingleton.getInstance()));
    }
}
