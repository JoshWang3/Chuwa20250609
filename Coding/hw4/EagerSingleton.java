public class EagerSingleton {
    // Instance is created at class loading time
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    
    private EagerSingleton() {
        // Private constructor prevents external instantiation
        System.out.println("EagerSingleton instance created");
    }
    
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
    
    public void performTask() {
        System.out.println("Eager Singleton performing task");
    }
    
    // Test method
    public static void main(String[] args) {
        System.out.println("=== Eager Singleton Demo ===");
        EagerSingleton instance1 = EagerSingleton.getInstance();
        EagerSingleton instance2 = EagerSingleton.getInstance();
        
        System.out.println("instance1 == instance2: " + (instance1 == instance2));
        System.out.println("instance1.hashCode(): " + instance1.hashCode());
        System.out.println("instance2.hashCode(): " + instance2.hashCode());
        
        instance1.performTask();
        instance2.performTask();
    }
} 