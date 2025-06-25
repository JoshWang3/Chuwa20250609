package hw6;

/**
 * Write a code to create 2 threads, one thread print 1,3,5,7,9, another thread print 2,4,6,8,10
 * Use synchronized and wait notify
 */

public class OddEvenPrinterSynchronized {
    private static final Object lock = new Object();
    private static int num = 1;

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            while (num <= 9) {
                synchronized (lock) {
                    if (num % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + ": " + num);
                        num++;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread evenThread = new Thread(() -> {
            while (num <= 10) {
                synchronized (lock) {
                    if (num % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + num);
                        num++;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        oddThread.start();
        evenThread.start();
    }
}
