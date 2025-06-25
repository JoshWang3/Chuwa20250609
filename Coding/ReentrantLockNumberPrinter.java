import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ReentrantLockNumberPrinter {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition oddCase = lock.newCondition();
    private static final Condition evenCase = lock.newCondition();
    private static boolean oddTurn = true; // Start with odd numbers

    public static void main(String[] args) {
        Thread oddThread = new Thread(new OddPrinter());
        Thread evenThread = new Thread(new EvenPrinter());

        oddThread.start();
        evenThread.start();

        try {
            oddThread.join();
            evenThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static class OddPrinter implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 9; i += 2) {
                lock.lock();
                try {
                    while (!oddTurn) {
                        oddCase.await();
                    }
                    System.out.println("Odd Thread: " + i);
                    oddTurn = false;
                    evenCase.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class EvenPrinter implements Runnable {
        @Override
        public void run() {
            for (int i = 2; i <= 10; i += 2) {
                lock.lock();
                try {
                    while (oddTurn) {
                        evenCondition.await();
                    }
                    System.out.println("Even Thread: " + i);
                    oddTurn = true;
                    oddCondition.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}