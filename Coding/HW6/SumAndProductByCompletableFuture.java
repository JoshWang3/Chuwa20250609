import java.util.concurrent.*;
public class SumAndProductByCompletableFuture {
    public static void main(String[] args) {
        int n1 = 7;
        int n2 = 17;
        // //异步 .supplyAsync(）
        CompletableFuture<Integer> sumup = CompletableFuture.supplyAsync(() -> n1+n2);
        CompletableFuture<Integer> product = CompletableFuture.supplyAsync(() -> n1*n2);

        sumup.thenAccept(result -> System.out.println("Sum: " + result));
        product.thenAccept(result -> System.out.println("Product: " + result));

        //保证主线程不会提前结束
        sumup.join();
        product.join();
    }
}
