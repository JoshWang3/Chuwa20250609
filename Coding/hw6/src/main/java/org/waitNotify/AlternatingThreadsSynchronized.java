package org.waitNotify;

//com.chuwa.tutorial.t08_multithreading.c05_waitNotify.OddEventPrinter
public class AlternatingThreadsSynchronized {
    private static final Object lock = new Object();
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
                synchronized (lock) {
                    while (!oddTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    oddTurn = false;
                    lock.notify();
                }
            }
        }
    }

    static class EvenPrinter implements Runnable {
        @Override
        public void run() {
            for (int i = 2; i <= 10; i += 2) {
                synchronized (lock) {
                    while (oddTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    oddTurn = true;
                    lock.notify();
                }
            }
        }
    }
}
