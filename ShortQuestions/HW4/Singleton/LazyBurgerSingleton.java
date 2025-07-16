package Singleton;

public class LazyBurgerSingleton {
    // 1. private static volatile instance variable (not created until needed)
    // volatile ensures correct visibility of instance across threads
    private static volatile LazyBurgerSingleton instance = null;

    // example field: holds a Burger object
    private Burger burger;

    // 2. private constructor prevents external instantiation
    private LazyBurgerSingleton() {
        this.burger = new Burger("Chicken", false);
    }

    // 3. public static method for accessing the singleton instance
    // double check locking: synchronize only when instance is null, then check again inside lock
    // ensures only one instance is created, even in a multi-threaded environment
    public static LazyBurgerSingleton getInstance() {

        // first check: no locking
        if (instance == null) {
            synchronized (LazyBurgerSingleton.class) { // lock only if instance is null

                // second check: weith lock
                if (instance == null) {
                    instance = new LazyBurgerSingleton();
                }
            }
        }
        return instance;
    }

    public Burger getBurger() { return burger; }
    public void setBurger(Burger burger) { this.burger = burger; }
}
