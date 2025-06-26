package Completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

public class hw1 {
    public static void main (String[] args) {
        BiFunction<Integer, Integer, Integer> addition = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;

        CompletableFuture<Integer> addFuture = CompletableFuture.supplyAsync(() -> addition.apply(2, 3));

        CompletableFuture<Integer> multiplyFuture = CompletableFuture.supplyAsync(() -> multiply.apply(2, 3));

        CompletableFuture<Void> combinedFutures = CompletableFuture.allOf(addFuture, multiplyFuture);

        combinedFutures.thenAccept(v -> {
            try {
                System.out.println(addFuture.get());
                System.out.println(multiplyFuture.get());
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
