public class LazySingleton {
    private static volatile LazySingleton instance;

    private LazySingleton() {
        // private constructor
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

    public void print() {
        System.out.println("This is Lazy Singleton");
    }
}
