import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Q2 {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean isOdd = true;

    public static void main(String[] args) {
        Q2 printer = new Q2();

        Thread oddThread = new Thread(() -> printer.printOdd());
        Thread evenThread = new Thread(() -> printer.printEven());

        oddThread.start();
        evenThread.start();
    }

    public void printOdd() {
        for (int i = 1; i <= 9; i += 2) {
            lock.lock();
            try {
                while (!isOdd) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                isOdd = false;
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printEven() {
        for (int i = 2; i <= 10; i += 2) {
            lock.lock();
            try {
                while (isOdd) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                isOdd = true;
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
