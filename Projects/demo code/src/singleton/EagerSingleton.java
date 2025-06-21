package singleton;
public class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
        System.out.println("EagerSingleton created");
    }

    public static EagerSingleton getInstance() {
        return instance;
    }

    public void sayHello() {
        System.out.println("Hello from EagerSingleton!");
    }
}