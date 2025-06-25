package Coding;

public class DeadlockDemo {

    private static final Object LockA = new Object();
    private static final Object LockB = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (LockA) {
                System.out.println("Thread 1: Holding LockA...");

                try { Thread.sleep(100); } catch (InterruptedException ignored) {}

                System.out.println("Thread 1: Waiting for LockB...");
                synchronized (LockB) {
                    System.out.println("Thread 1: Acquired LockB!");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (LockB) {
                System.out.println("Thread 2: Holding LockB...");

                try { Thread.sleep(100); } catch (InterruptedException ignored) {}

                System.out.println("Thread 2: Waiting for LockA...");
                synchronized (LockA) {
                    System.out.println("Thread 2: Acquired LockA!");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}