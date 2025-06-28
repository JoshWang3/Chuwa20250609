package hw6.odd_even_printer;

import java.util.concurrent.locks.Condition;

public class ReentrantLock {
    private static int number = 1;

    private final java.util.concurrent.locks.ReentrantLock lock = new java.util.concurrent.locks.ReentrantLock();
    private final Condition oddTurn = lock.newCondition();
    private final Condition evenTurn = lock.newCondition();

    public static void main(String[] args) {
        ReentrantLock printer = new ReentrantLock();

        Thread oddThread = new Thread(printer::printOdd, "OddThread");
        Thread evenThread = new Thread(printer::printEven, "EvenThread");

        oddThread.start();
        evenThread.start();
    }

    public void printOdd() {
        while (number <= 10) {
            lock.lock(); // acquire the lock before checking and printing
            try {
                // wait until it's odd's turn
                while (number % 2 == 0) {
                    oddTurn.await(); // releases lock and waits to be signaled
                }
                // odd turn, so print and increment the number
                if (number <= 10) {
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;
                }
                evenTurn.signal(); // signal even thread
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock(); // release the lock
            }
        }
    }

    public void printEven() {
        while (number <= 10) {
            lock.lock();
            try {
                while (number % 2 == 1) {
                    evenTurn.await(); // wait until it's even's turn
                }
                if (number <= 10) {
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;
                }
                oddTurn.signal(); // signal odd thread
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}
