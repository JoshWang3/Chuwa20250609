// Q24. Create three threads: one outputs 1–10, one outputs 11–20, one outputs 21–22.
// Threads run in random sequence.

public class threeThreads {

    public static void main(String[] args) {
        // Create three threads with different number ranges
        Thread thread1 = new Thread(new NumberPrinter(1, 10, "Thread-1"));
        Thread thread2 = new Thread(new NumberPrinter(11, 20, "Thread-2"));
        Thread thread3 = new Thread(new NumberPrinter(21, 22, "Thread-3"));

        // Start all threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Wait for all threads to complete
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted: " + e.getMessage());
        }

        System.out.println("All threads completed.");
    }
}

class NumberPrinter implements Runnable {
    private final int start;
    private final int end;
    private final String threadName;

    public NumberPrinter(int start, int end, String threadName) {
        this.start = start;
        this.end = end;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        try {
            for (int i = start; i <= end; i++) {
                System.out.println(threadName + ": " + i);

                // Add random delay to make the execution more unpredictable
                Thread.sleep((long)(Math.random() * 100));
            }
        } catch (InterruptedException e) {
            System.err.println(threadName + " interrupted: " + e.getMessage());
        }
    }
}