import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenReentrantLock {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static boolean isOddTurn = true;

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            for (int i = 1; i <= 9; i += 2) {
                lock.lock();
                try {
                    while (!isOddTurn) {
                        condition.await();
                    }
                    System.out.println(i);
                    isOddTurn = false;
                    condition.signal();
                } catch (InterruptedException e) {} finally {
                    lock.unlock();
                }
            }
        });

        Thread evenThread = new Thread(() -> {
            for (int i = 2; i <= 10; i += 2) {
                lock.lock();
                try {
                    while (isOddTurn) {
                        condition.await();
                    }
                    System.out.println(i);
                    isOddTurn = true;
                    condition.signal();
                } catch (InterruptedException e) {} finally {
                    lock.unlock();
                }
            }
        });

        oddThread.start();
        evenThread.start();
    }
}
