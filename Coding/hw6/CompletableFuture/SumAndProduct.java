package Coding.hw6.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SumAndProduct {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int a = 5;
        int b = 3;

        // Asynchronously compute the sum
        CompletableFuture<Integer> sumFuture = CompletableFuture
                .supplyAsync(() -> {
                    int sum = a + b;
                    System.out.println("Computed sum in thread: " + Thread.currentThread().getName());
                    return sum;
                });

        // Asynchronously compute the product
        CompletableFuture<Integer> productFuture = CompletableFuture
                .supplyAsync(() -> {
                    int prod = a * b;
                    System.out.println("Computed product in thread: " + Thread.currentThread().getName());
                    return prod;
                });

        sumFuture.thenAccept(sum ->
                System.out.println("Sum of " + a + " and " + b + " = " + sum)
        );

        productFuture.thenAccept(prod ->
                System.out.println("Product of " + a + " and " + b + " = " + prod)
        );

        // Wait for both to finish before exiting main
        CompletableFuture.allOf(sumFuture, productFuture).join();
    }
}
