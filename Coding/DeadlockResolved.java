// Resolves deadlock by using consistent lock ordering
public class DeadlockResolved {
    private final Object lockA = new Object();
    private final Object lockB = new Object();
    
    public void method1() {
        synchronized (lockA) {
            synchronized (lockB) {  // Consistent order: A then B
                System.out.println("Inside method1");
            }
        }
    }
    
    public void method2() {
        synchronized (lockA) {
            synchronized (lockB) {  // Same order: A then B
                System.out.println("Inside method2");
            }
        }
    }
    
    public static void main(String[] args) {
        DeadlockResolved d = new DeadlockResolved();
        new Thread(d::method1).start();
        new Thread(d::method2).start();
    }
}