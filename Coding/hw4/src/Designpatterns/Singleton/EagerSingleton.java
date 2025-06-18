package Designpatterns.Singleton;

public class EagerSingleton {
    // Set instance before get
    private static EagerSingleton instance = new EagerSingleton();

    // private constructor
    private EagerSingleton() {

    }

    public static EagerSingleton getInstance() {
        System.out.println("Return an eager singleton instance.");
        return instance;
    }
}
