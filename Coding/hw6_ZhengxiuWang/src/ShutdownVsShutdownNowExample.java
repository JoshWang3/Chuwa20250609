import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.List;
    // shutdown()     → wait for task to finish
    // shutdownNow()  → try to stop task (interrupt)
    // .submit()      → add task to thread pool
public class ShutdownVsShutdownNowExample {
    public static void main(String[] args) {
        // Create a thread pool with 1 thread
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Submit a task that sleeps for 2 seconds
        executor.submit(() -> {
            try {
                System.out.println("Task started");
                Thread.sleep(2000);  // Simulate work
                System.out.println("Task finished");
            } catch (InterruptedException e) {
                System.out.println("Task was interrupted");
            }
        });

        // ===== shutdown() version =====
        // executor.shutdown();  // Graceful shutdown

        // ===== shutdownNow() version =====
        List<Runnable> notStarted = executor.shutdownNow();  // Force shutdown
        System.out.println("ShutdownNow called, tasks not started: " + notStarted.size());

        // Wait a bit to see output
        try { TimeUnit.SECONDS.sleep(3); } catch (Exception e) {}
        System.out.println("Main thread ends");
    }
}
