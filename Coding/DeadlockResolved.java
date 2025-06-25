package Coding;

public class DeadlockResolved {

    private static final Object LockA = new Object();
    private static final Object LockB = new Object();

    public static void main(String[] args) {
        Runnable task = () -> {
            synchronized (LockA) {
                System.out.println(Thread.currentThread().getName() + ": Holding LockA");

                try { Thread.sleep(100); } catch (InterruptedException ignored) {}

                synchronized (LockB) {
                    System.out.println(Thread.currentThread().getName() + ": Acquired LockB");
                }
            }
        };

        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");

        thread1.start();
        thread2.start();
    }
}