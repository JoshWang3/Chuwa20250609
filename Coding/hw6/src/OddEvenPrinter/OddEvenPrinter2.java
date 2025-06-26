package OddEvenPrinter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinter2 {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static int val = 1;

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            while (true) {
                lock.lock();
                try {
                    if (val > 10) {
                        condition.signalAll();
                        break;
                    }

                    System.out.println(Thread.currentThread().getName() + ": " + val++);
                    condition.signal();

                    if (val <= 10) {
                        condition.await();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
