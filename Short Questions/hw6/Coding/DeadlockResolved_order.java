public class DeadlockResolved_order {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void task1() {
        synchronized (lock1) {
            System.out.println("Task1 locked lock1");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            synchronized (lock2) {
                System.out.println("Task1 locked lock2");
            }
        }
    }

    public void task2() {
        // Use same lock order: lock1 → lock2
        synchronized (lock1) {
            System.out.println("Task2 locked lock1");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            synchronized (lock2) {
                System.out.println("Task2 locked lock2");
            }
        }
    }

    public static void main(String[] args) {
        DeadlockResolved_order demo = new DeadlockResolved_order();

        Thread t1 = new Thread(() -> demo.task1());
        Thread t2 = new Thread(() -> demo.task2());

        t1.start();
        t2.start();
    }
}
