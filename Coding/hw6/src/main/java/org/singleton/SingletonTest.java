package org.singleton;

public class SingletonTest {

    public static void main(String[] args) {

        // Number of threads to test concurrency
        int threadCount = 10;

        Thread[] threads = new Thread[threadCount];

        // Create multiple threads that get the Singleton instance
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                Singleton instance = Singleton.getInstance();
                System.out.println("Instance hashCode: " + instance.hashCode());
            });
        }

        // Start all threads
        for (Thread t : threads) {
            t.start();
        }

        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
