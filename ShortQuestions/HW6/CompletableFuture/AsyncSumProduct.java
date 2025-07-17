package CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class AsyncSumProduct {
    public static void main(String[] args) {
        int a = 4;
        int b = 5;

        // asynchronously sum
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            return a + b;
        });

        // asynchronously product
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            return a * b;
        });

        // when both finished -> print result
        sumFuture.thenAccept(sum -> {
            System.out.println("Sum: " + sum);
        });

        productFuture.thenAccept(product -> {
            System.out.println("Product: " + product);
        });

        // keep the main thread alive in order to see output
        try {
            Thread.sleep(1000); // make sure output print before main ends
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
output:
Sum: 9
Product: 20
 */

// CompletableFuture.supplyAsync(...) runs tasks on a separate background thread (usually from the common ForkJoinPool)
//  main method finishes before those background threads print the results, the JVM may terminate early, and we won’t see any output