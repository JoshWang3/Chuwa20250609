package Coding.hw6;

import static java.lang.Thread.sleep;

public class DeadLock {
    private static final Object lock1 = new Object();
    private static final Object lock2  = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread1 holding lock1");
                try {
                    sleep(100);
                } catch (InterruptedException ignored) {}
                System.out.println("Thread1 waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread1 holding lock2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread2 holding lock2");
                try {
                    sleep(100);
                } catch (InterruptedException ignored) {}
                System.out.println("Thread2 waiting for lock1");
                synchronized (lock1) {
                    System.out.println("Thread2 holding lock1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}

/*
To resolve deadlock:
1. We can change the order of the two thread acquiring lock
so that they have the same order (lock1 -> lock2)

Thread thread2 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread2 holding lock1");
                try {
                    sleep(100);
                } catch (InterruptedException ignored) {}
                System.out.println("Thread2 waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread2 holding lock2");
                }
            }
        });
This case both thread acquire lock1 then lock2 and it can prevent deadlock

2. Use tryLock() method
 */
