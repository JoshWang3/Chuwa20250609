import java.util.concurrent.CompletableFuture;
public class Completable {
    public static void main(String[] args) {
        int a = 2, b = 3;
        CompletableFuture<Integer> sumFuture = CompletableFuture
                .supplyAsync(() -> {
                    return a + b;
                });
        CompletableFuture<Integer> prodFuture = CompletableFuture
                .supplyAsync(() -> {
                    return a * b;
                });
        CompletableFuture<Void> resultFuture = sumFuture
                .thenAcceptBoth(prodFuture, (sum, product) -> {
                    System.out.println("Sum:     " + sum);
                    System.out.println("Product: " + product);
                });
        resultFuture.join();
    }
}
