// Demonstrates deadlock: two methods acquire locks in different order
public class DeadlockDemo {
    private final Object lockA = new Object();
    private final Object lockB = new Object();
    
    public void method1() {
        synchronized (lockA) {
            synchronized (lockB) {  // A then B
                System.out.println("Inside method1");
            }
        }
    }
    
    public void method2() {
        synchronized (lockB) {
            synchronized (lockA) {  // B then A - potential deadlock
                System.out.println("Inside method2");
            }
        }
    }
    
    public static void main(String[] args) {
        DeadlockDemo d = new DeadlockDemo();
        new Thread(d::method1).start();
        new Thread(d::method2).start();
    }
}