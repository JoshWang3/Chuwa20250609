package Designpatterns.Singleton;

public class LazySingleton {

    private LazySingleton() {

    }

    private static class SingletonHolder {
        private static final LazySingleton instance = new LazySingleton();
    }

    // set instance only when getting instance
    public static LazySingleton getInstance() {
        System.out.println("Return a lazy singleton instance.");
        return SingletonHolder.instance;
    }
}
