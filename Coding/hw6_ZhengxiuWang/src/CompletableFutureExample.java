import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public static void main(String[] args) {
        // 1. Run async and return a value
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        });

        // 2. thenApply: transform result
        CompletableFuture<String> upper = future.thenApply(str -> str.toUpperCase());

        // 3. thenAccept: consume result
        upper.thenAccept(result -> {
            System.out.println("Result: " + result);  // HELLO
        });

        // 4. thenRun: run after completion (no input/output)
        upper.thenRun(() -> {
            System.out.println("Task completed");
        });

        // 5. exceptionally: handle exception
        CompletableFuture<String> errorFuture = CompletableFuture.supplyAsync(() -> {
            if (true) throw new RuntimeException("Something went wrong");
            return "OK";
        }).exceptionally(ex -> {
            System.out.println("Caught error: " + ex.getMessage());
            return "Fallback";
        });

        // 6. join(): get result (no checked exception)
        String result = errorFuture.join();
        System.out.println("Final result: " + result);
    }
}
