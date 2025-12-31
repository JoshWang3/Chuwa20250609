import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockResolved_timeout {
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    public void task1() {
        try {
            if (lock1.tryLock(1, TimeUnit.SECONDS)) {
                System.out.println("Task1 locked lock1");
                try {
                    if (lock2.tryLock(1, TimeUnit.SECONDS)) {
                        System.out.println("Task1 locked lock2");
                        try {
                            // Do work
                        } finally {
                            lock2.unlock();
                        }
                    }
                } finally {
                    lock1.unlock();
                }
            }
        } catch (InterruptedException e) {
            // Handle the exception
            e.printStackTrace();
        }
    }


    public void task2() {
                try {
            if (lock2.tryLock(1, TimeUnit.SECONDS)) {
                System.out.println("Task2 locked lock2");
                try {
                    if (lock1.tryLock(1, TimeUnit.SECONDS)) {
                        System.out.println("Task2 locked lock1");
                        try {
                            // Do work
                        } finally {
                            lock1.unlock();
                        }
                    }
                } finally {
                    lock2.unlock();
                }
            }
        } catch (InterruptedException e) {
            // Handle the exception
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DeadlockResolved_timeout demo = new DeadlockResolved_timeout();

        Thread t1 = new Thread(() -> demo.task1());
        Thread t2 = new Thread(() -> demo.task2());

        t1.start();
        t2.start();
    }
}
