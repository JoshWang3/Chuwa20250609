import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


    // FixedThreadPool = reuse fixed number of threads
    // submit() = add task to queue
    // shutdown() = finish tasks and close pool
public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        // Create a thread pool with 2 threads
        ExecutorService pool = Executors.newFixedThreadPool(2);

        // Submit 3 tasks to the pool
        pool.submit(() -> {
            System.out.println("Task 1 by " + Thread.currentThread().getName());
        });

        pool.submit(() -> {
            System.out.println("Task 2 by " + Thread.currentThread().getName());
        });

        pool.submit(() -> {
            System.out.println("Task 3 by " + Thread.currentThread().getName());
        });

        // Stop the pool (no new tasks)
        pool.shutdown();
    }
}
