package hw7;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class lockOddEvenPrinter {
    private final int MAX = 10;
    private int count = 1;
    private final Lock lock = new ReentrantLock();
    private final Condition oddTurn = lock.newCondition();
    private final Condition evenTurn = lock.newCondition();
    private boolean isOddTurn = true;

    public void printOdd() {
        while (count <= MAX) {
            lock.lock();
            try {
                while (!isOddTurn) {
                    oddTurn.await();
                }
                if (count <= MAX) {
                    System.out.println(Thread.currentThread().getName() + ": " + count);
                    count++;
                    isOddTurn = false;
                    evenTurn.signal();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printEven() {
        while (count <= MAX) {
            lock.lock();
            try {
                while (isOddTurn) {
                    evenTurn.await();
                }
                if (count <= MAX) {
                    System.out.println(Thread.currentThread().getName() + ": " + count);
                    count++;
                    isOddTurn = true;
                    oddTurn.signal();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        lockOddEvenPrinter printer = new lockOddEvenPrinter();

        Thread t1 = new Thread(printer::printOdd);
        Thread t2 = new Thread(printer::printEven);

        t1.start();
        t2.start();
    }
}