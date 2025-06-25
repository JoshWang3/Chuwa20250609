// Thread-safe singleton using double-checked locking
public class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton instance;
    
    private ThreadSafeSingleton() {}
    
    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {  // Double-check
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
    
    public static void main(String[] args) {
        // Test singleton from multiple threads
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance();
                System.out.println(Thread.currentThread().getName() + 
                    ": " + singleton.hashCode());
            }).start();
        }
    }
}