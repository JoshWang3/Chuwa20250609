public class LockExample {
    // Object Lock: lock per object
    public synchronized void objectLockMethod() {
        System.out.println("Object lock: " + Thread.currentThread().getName());
    }

    // Class Lock: lock for whole class
    public static synchronized void classLockMethod() {
        System.out.println("Class lock: " + Thread.currentThread().getName());
    }
}
