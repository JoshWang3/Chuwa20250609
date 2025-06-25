package coding25;

import java.util.concurrent.CompletableFuture;

public class HomeWork1 {

    public static void main(String[] args) {
        int a = 6;
        int b = 66;

        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            return a + b;
        });

        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            return a * b;
        });

        sumFuture.thenAccept(System.out::println);

        productFuture.thenAccept(System.out::println);

        // wait for both to complete
        CompletableFuture.allOf(sumFuture,productFuture).join();
    }
}
