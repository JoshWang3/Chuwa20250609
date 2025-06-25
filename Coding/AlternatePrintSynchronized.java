package Coding;

public class AlternatePrintSynchronized {
    private static final Object lock = new Object();
    private static boolean isOddTurn = true;

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            for (int i = 1; i <= 9; i += 2) {
                synchronized (lock) {
                    while (!isOddTurn) {
                        try { lock.wait(); } catch (InterruptedException ignored) {}
                    }
                    System.out.println(i);
                    isOddTurn = false;
                    lock.notify();
                }
            }
        });

        Thread evenThread = new Thread(() -> {
            for (int i = 2; i <= 10; i += 2) {
                synchronized (lock) {
                    while (isOddTurn) {
                        try { lock.wait(); } catch (InterruptedException ignored) {}
                    }
                    System.out.println(i);
                    isOddTurn = true;
                    lock.notify();
                }
            }
        });

        oddThread.start();
        evenThread.start();
    }
}