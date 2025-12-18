import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceThreadCreation {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3); // 3 threads

        for (int i = 0; i < 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + ": " +"Running task " + taskId);
            });
        }

        executor.shutdown(); // Graceful shutdown
    }
}