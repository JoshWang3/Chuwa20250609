package Coding.hw6;

import java.util.concurrent.atomic.AtomicInteger;

public class PrintNumber1 {
    private static final AtomicInteger counter = new AtomicInteger(1);
    private static final int MAX = 22;

    public static void main(String[] args) {
        Runnable worker = () -> {
            int value;
            while ((value = counter.getAndIncrement()) <= MAX) {
                System.out.println(Thread.currentThread().getName() + ": " + value);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        };

        Thread t1 = new Thread(worker, "Thread-0");
        Thread t2 = new Thread(worker, "Thread-1");
        Thread t3 = new Thread(worker, "Thread-2");

        t1.start();
        t2.start();
        t3.start();
    }
}
