import java.util.concurrent.*;

public class HW1_SumAndProduct {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        int a = 7, b = 9;

        CompletableFuture<Integer> sum =
                CompletableFuture.supplyAsync(() -> a + b, pool);

        CompletableFuture<Integer> product =
                CompletableFuture.supplyAsync(() -> a * b, pool);

        // Option 1: wait for both then print
        sum.thenAcceptBoth(product, (s, p) -> {
            System.out.println("sum = " + s);
            System.out.println("product = " + p);
        }).join(); // block only at the very end to keep main alive

        pool.shutdown();
    }
}
