package org.completableFuture.hw1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// hw1. Write a simple program that uses CompletableFuture to asynchronously get the sum
// and product of two integers, and print the results.
public class AsyncCalculator {

    public static void main(String[] args) {
        int num1 = 15;
        int num2 = 8;

        System.out.println("Starting asynchronous calculations...");
        System.out.println("Numbers: " + num1 + " and " + num2);

        // Create CompletableFuture for sum calculation
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Calculating sum in thread: " + Thread.currentThread().getName());
            // Simulate some processing time
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return num1 + num2;
        });

        // Create CompletableFuture for product calculation
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Calculating product in thread: " + Thread.currentThread().getName());
            // Simulate some processing time
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return num1 * num2;
        });

        // Combine both futures and print results when both complete
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(sumFuture, productFuture)
                .thenRun(() -> {
                    try {
                        int sum = sumFuture.get();
                        int product = productFuture.get();

                        System.out.println("\n=== Results ===");
                        System.out.println("Sum: " + num1 + " + " + num2 + " = " + sum);
                        System.out.println("Product: " + num1 + " × " + num2 + " = " + product);

                    } catch (InterruptedException | ExecutionException e) {
                        System.err.println("Error getting results: " + e.getMessage());
                    }
                });

        // Alternative approach: Handle results individually as they complete
        sumFuture.thenAccept(result ->
                System.out.println("Sum completed: " + result)
        );

        productFuture.thenAccept(result ->
                System.out.println("Product completed: " + result)
        );

        // Wait for all operations to complete
        try {
            combinedFuture.get(); // This blocks until both calculations are done
            System.out.println("All calculations completed!");
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error waiting for completion: " + e.getMessage());
        }
    }
}
