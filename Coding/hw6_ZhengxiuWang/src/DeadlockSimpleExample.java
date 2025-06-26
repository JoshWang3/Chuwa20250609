public class DeadlockSimpleExample {
    //  This causes deadlock:
    // Thread 1 holds lock1, waits for lock2
    // Thread 2 holds lock2, waits for lock1
    // Both threads are stuck forever

    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        // Thread 1: locks lock1, then tries to get lock2
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: locked lock1");

                try { Thread.sleep(100); } catch (Exception e) {}

                synchronized (lock2) {
                    System.out.println("Thread 1: locked lock2");
                }
            }
        });

        // Thread 2: locks lock2, then tries to get lock1
        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2: locked lock2");

                try { Thread.sleep(100); } catch (Exception e) {}

                synchronized (lock1) {
                    System.out.println("Thread 2: locked lock1");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
