package Singleton;

public class EagerBurgerSingleton {
    // 1. private static variable
    // eager initialization: created as soon as the class is loaded
    private static final EagerBurgerSingleton instance = new EagerBurgerSingleton();

    // Example field: Burger object held by the singleton
    private Burger burger;

    // 2. private constructor prevents instantiation from outside class
    // ensures only one instance of Singleton.EagerBurgerSingleton can ever be created
    private EagerBurgerSingleton() {
        this.burger = new Burger("Beef", true);
    }

    // 3. static getInstance method to provide access to the singleton instance
    // always return the same instance
    public static EagerBurgerSingleton getInstance() {
        return instance;
    }

    // getter & setter for Burger field
    public Burger getBurger() { return burger; }
    public void setBurger(Burger burger) { this.burger = burger; }
}
