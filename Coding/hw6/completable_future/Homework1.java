package hw6.completable_future;

import java.util.concurrent.CompletableFuture;

public class Homework1 {
    public static void main(String[] args) {
        int a = 3, b = 5;
        // asynchronously calculation: runs the given task in a separate thread
        // supplyAsync(...) runs in ForkJoinPool.commonPool by default
        // so both sum and product are calculated in parallel
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> a + b);
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> a * b);
        // combine both futures and wait for both to complete
        // CompletableFuture.allOf(...) waits for multiple tasks to complete
        CompletableFuture<Void> combinedFutures = CompletableFuture.allOf(sumFuture, productFuture);
        // when both tasks are done, process the results
        // thenAccept(...) callback when tasks are done
        combinedFutures.thenAccept(v -> {
            try {
                // get() to retrieve the completed results
                Integer sum = sumFuture.get();
                Integer product = productFuture.get();
                System.out.println("Sum: " + sum);
                System.out.println("Product: " + product);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
