import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class WaitNotifyPrinter{
    private final Object lock = new Object();
    private boolean oddTurn = true;

    public void printOdd() {
        for (int i = 1; i <= 9; i += 2) {
            synchronized (lock) {
                while (!oddTurn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.print(i + " ");
                oddTurn = false;
                lock.notify();
            }
        }
    }
    public void printEven() {
        for (int i = 2; i <= 10; i += 2) {
            synchronized (lock) {
                // wait until it's even's turn
                while (oddTurn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.print(i + " ");
                oddTurn = true;
                lock.notify();
            }
        }
    }
}
class ReentrantPrinter{
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition turnCondition = lock.newCondition();
    private boolean oddTurn = true;
    public void printOdd() {
        for (int i = 1; i <= 9; i += 2) {
            lock.lock();
            try {
                while (!oddTurn) {
                    turnCondition.await();
                }
                System.out.print(i + " ");
                oddTurn = false;
                turnCondition.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printEven() {
        for (int i = 2; i <= 10; i += 2) {
            lock.lock();
            try {
                while (oddTurn) {
                    turnCondition.await();
                }
                System.out.print(i + " ");
                oddTurn = true;
                turnCondition.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}
public class EvenAndOddThreads {
    public static void main(String[] args) throws InterruptedException {
        WaitNotifyPrinter printer = new WaitNotifyPrinter();
        Thread oddThread = new Thread(printer::printOdd);
        Thread evenThread = new Thread(printer::printEven);
        oddThread.start();
        evenThread.start();
        oddThread.join();
        evenThread.join();

        System.out.println();

        ReentrantPrinter printer2 = new ReentrantPrinter();
        Thread oddThread2 = new Thread(printer2::printOdd);
        Thread evenThread2 = new Thread(printer2::printEven);
        oddThread2.start();
        evenThread2.start();
        oddThread2.join();
        evenThread2.join();
    }
}
