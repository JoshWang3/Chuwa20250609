//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
public class hw1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int a = 5;
        int b = 3;

        // Asynchronously calculate sum
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            return a + b;
        });

        // Asynchronously calculate product
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            return a * b;
        });

        // Combine both futures and print the results
        sumFuture.thenAccept(sum -> System.out.println("Sum: " + sum));
        productFuture.thenAccept(product -> System.out.println("Product: " + product));

        // Block main thread until both complete (optional)
        CompletableFuture.allOf(sumFuture, productFuture).join();

        System.out.println("Calculation finished.");
    }
}