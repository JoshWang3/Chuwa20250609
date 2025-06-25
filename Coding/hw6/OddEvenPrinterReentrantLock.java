package hw6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10
 * Use ReentrantLock and await, signal
 */
public class OddEvenPrinterReentrantLock {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static int num = 1;
    private static final int MAX = 10;

    public static void main(String[] args) {
        Runnable printOdd = () -> {
            while(num <= MAX) {
                lock.lock();
                try {
                    while(num % 2 == 0) {
                        condition.await();
                    }
                    if(num <= MAX) {
                        System.out.println(Thread.currentThread().getName() + ": " + num);
                        num++;
                        condition.signal();
                    }
                }catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }finally {
                    lock.unlock();
                }

            }
        };
        Runnable printEven = () -> {
            while(num <= MAX) {
                lock.lock();
                try {
                    while(num % 2 == 1) {
                        condition.await();
                    }
                    if(num <= MAX) {
                        System.out.println(Thread.currentThread().getName() + ": " + num);
                        num++;
                        condition.signal();
                    }
                }catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }finally {
                    lock.unlock();
                }
            }
        };

        new Thread(printOdd).start();
        new Thread(printEven).start();
    }
}
