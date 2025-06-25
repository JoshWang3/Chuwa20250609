package org.waitNotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
* Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10.
(solution is in ` com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter`)
1. One solution use synchronized and wait notify
2. One solution use ReentrantLock and await, signal
* */
public class AlternatingThreadsReentrantLock {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition oddCondition = lock.newCondition();
    private static final Condition evenCondition = lock.newCondition();
    private static boolean oddTurn = true; // true for odd thread, false for even thread

    public static void main(String[] args) {
        Thread oddThread = new Thread(new OddPrinter(), "Thread-0");
        Thread evenThread = new Thread(new EvenPrinter(), "Thread-1");

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
                        oddCondition.await();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    oddTurn = false;
                    evenCondition.signal();
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
                    System.out.println(Thread.currentThread().getName() + ": " + i);
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
