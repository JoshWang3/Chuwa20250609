import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Create a pool with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Run some tasks
        executor.submit(() -> {
            System.out.println("Task 1 running");
        });

        executor.submit(() -> {
            System.out.println("Task 2 running");
        });

        // Stop the pool after tasks finish
        executor.shutdown();
    }
}
