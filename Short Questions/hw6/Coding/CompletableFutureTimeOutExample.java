import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTimeOutExample {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running task
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task completed successfully";
        });

        future.thenApply(result -> {
            System.out.println(result);
            return result;
        }).orTimeout(2, TimeUnit.SECONDS).handle((t, u) -> {
            System.out.println("Task timed out");
            return null;
        });

         try {
             String result = future.get();
             System.out.println("Final result: " + result);
         } catch (InterruptedException | ExecutionException e) {
             e.printStackTrace();
         }
    }
}