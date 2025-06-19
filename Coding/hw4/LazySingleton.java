public class LazySingleton {
    private static volatile LazySingleton instance;
    
    private LazySingleton() {
        // Private constructor prevents external instantiation
        System.out.println("LazySingleton instance created");
    }
    
    // Double-checked locking for thread safety
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
    
    public void performTask() {
        System.out.println("Lazy Singleton performing task");
    }
    
    // Test method with multi-threading
    public static void main(String[] args) {
        System.out.println("=== Lazy Singleton Demo ===");
        
        // Test with multiple threads to verify thread safety
        Runnable task = () -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println("Thread " + Thread.currentThread().getName() + 
                             " got instance: " + instance.hashCode());
            instance.performTask();
        };
        
        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");
        Thread thread3 = new Thread(task, "Thread-3");
        
        thread1.start();
        thread2.start();
        thread3.start();
        
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("All threads completed");
    }
} 