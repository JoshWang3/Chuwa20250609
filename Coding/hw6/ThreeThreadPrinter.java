package hw6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create 3 threads,
 * one thread output 1-10,
 * one thread output 11-20,
 * one thread output 21-22.
 * threads run sequence is random
 */
public class ThreeThreadPrinter {

    private static final Object lock = new Object();
    private static int id;
    private static int turn = 0;

    public static void main(String[] args) {

        Runnable task = () -> {
            int myId;
            synchronized (lock) {
                myId = id++;
            }

            // print range based on myId
            int start = myId * 10 + 1;
            int end = start + 9;

            synchronized (lock) {
                while (turn != myId) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                for (int i = start; i <= end; i++) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
                turn++;
                lock.notifyAll();
            }
        };
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();

    }


}
