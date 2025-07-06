package Coding.hw6.OddEvenPrinter;

import com.sun.javaws.IconUtil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinterReentrant {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean flag = true;

    public void printOdd() {
        for (int i = 0; i < 10; i += 2) {
            lock.lock();
            try {
                while (!flag) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                flag = false;
                condition.signal();
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
                while (flag) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                flag = true;
                condition.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OddEvenPrinterReentrant printer = new OddEvenPrinterReentrant();
        Thread odd = new Thread(printer::printOdd, "Thread-0");
        Thread even = new Thread(printer::printEven, "Thread-1");

        odd.start();
        even.start();

        odd.join();
        even.join();
    }
}
