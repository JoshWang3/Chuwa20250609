
package Coding;
// question2
public class thread1 {

    // 1. Private static volatile instance (ensures visibility & ordering)
    private static volatile thread1 instance;

    // 2. Private constructor (prevents instantiation)
    private thread1() {
        // Initialization logic here
    }

    // 3. Public method to return instance (with double-checked locking)
    public static thread1 getInstance() {
        if (instance == null) { // First check (no locking)
            synchronized (thread1.class) {
                if (instance == null) { // Second check (with locking)
                    instance = new thread1();
                }
            }
        }
        return instance;
    }
    
}

