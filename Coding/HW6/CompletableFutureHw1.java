
import java.util.concurrent.CompletableFuture;

public class CompletableFutureHw1 {
    public static void main(String[] args) {
        int n1 = 5, n2 = 10;

        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            return n1 + n2;
        });

        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            return n1 * n2;
        });

        sumFuture.thenAccept(sum -> System.out.println("Sum: " + sum));
        productFuture.thenAccept(product -> System.out.println("Product: " + product));
    }
}
