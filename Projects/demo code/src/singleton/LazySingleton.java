package singleton;

public class LazySingleton {
    private static volatile LazySingleton instance;

    private LazySingleton() {
        System.out.println("LazySingleton created");
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    public void sayHello() {
        System.out.println("Hello from LazySingleton!");
    }
}
