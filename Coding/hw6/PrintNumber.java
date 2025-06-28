package hw6;

public class PrintNumber {
    public static void main(String[] args) {
        // Thread 1: 1–10
        Thread t0 = new Thread(() -> printRange(1, 10));

        // Thread 2: 11–20
        Thread t1 = new Thread(() -> printRange(11, 20));

        // Thread 3: 21–22
        Thread t2 = new Thread(() -> printRange(21, 22));

        // Start threads in parallel; execution order is random
        t0.start();
        t1.start();
        t2.start();
    }

    private static void printRange(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            try {
                Thread.sleep(200); // slow down to better visualize randomness
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
