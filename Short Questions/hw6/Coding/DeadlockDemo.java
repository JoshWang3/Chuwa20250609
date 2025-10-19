public class DeadlockDemo {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void task1() {
        synchronized (lock1) {
            System.out.println("Task1 locked lock1");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            System.out.println("Task1 waiting for lock2...");
            synchronized (lock2) {
                System.out.println("Task1 acquired lock2");
            }
        }
    }

    public void task2() {
        synchronized (lock2) {
            System.out.println("Task2 locked lock2");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            System.out.println("Task2 waiting for lock1...");
            synchronized (lock1) {
                System.out.println("Task2 acquired lock1");
            }
        }
    }

    public static void main(String[] args) {
        DeadlockDemo demo = new DeadlockDemo();

        Thread t1 = new Thread(() -> demo.task1());
        Thread t2 = new Thread(() -> demo.task2());

        t1.start();
        t2.start();
    }
}
