package org.ThreeThreads;

/*
* create 3 threads, one thread output 1-10, one thread output 11-20, one thread output 21-22. threads run  sequence is random.
(solution is in com.chuwa.exercise.t08_multithreading.PrintNumber1)
* */
public class ThreeThreadsExample {

    // Thread class that outputs numbers in a given range
    static class NumberThread extends Thread {
        private int start;
        private int end;

        public NumberThread(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);

                // Optional: Add small delay to make thread interleaving more visible
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create three threads with different number ranges
        NumberThread thread1 = new NumberThread(1, 10);    // 1-10
        NumberThread thread2 = new NumberThread(11, 20);   // 11-20
        NumberThread thread3 = new NumberThread(21, 30);   // 21-30

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
            Thread.currentThread().interrupt();
        }

        System.out.println("All threads completed!");
    }
}
