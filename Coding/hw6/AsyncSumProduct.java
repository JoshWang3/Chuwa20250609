package hw6;

import java.util.concurrent.CompletableFuture;

public class AsyncSumProduct {

    public static void main(String[] args) {
        int a = 5;
        int b = 3;

        // Asynchronously compute the sum
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            return a + b;
        });

        // Asynchronously compute the product
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            return a * b;
        });

        // When both are done, print results
        sumFuture.thenAccept(sum -> {
            System.out.println("Sum: " + sum);
        });

        productFuture.thenAccept(product -> {
            System.out.println("Product: " + product);
        });

        // Wait for both tasks to complete before ending main
        CompletableFuture.allOf(sumFuture, productFuture).join();
    }
}
