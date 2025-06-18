public class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
        // private constructor
    }

    public static EagerSingleton getInstance() {
        return instance;
    }

    public void print() {
        System.out.println("This is Eager Singleton");
    }
}

