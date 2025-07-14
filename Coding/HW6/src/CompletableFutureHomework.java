import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureHomework {

    // Homework 1: Simple sum and product asynchronously
    public static void homework1() throws ExecutionException, InterruptedException {
        int a = 3, b = 4;

        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> a + b);
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> a * b);

        System.out.println("Sum: " + sumFuture.get());
        System.out.println("Product: " + productFuture.get());
    }

    // Homework 2 + 3: Simulated API calls with exception handling
    public static void homework2and3() throws InterruptedException, ExecutionException {
        HttpClient client = HttpClient.newHttpClient();

        CompletableFuture<String> products = fetchAsync(client, "https://jsonplaceholder.typicode.com/posts")
                .exceptionally(ex -> {
                    System.out.println("Failed to fetch products: " + ex.getMessage());
                    return "[]"; // default value
                });

        CompletableFuture<String> reviews = fetchAsync(client, "https://jsonplaceholder.typicode.com/comments")
                .exceptionally(ex -> {
                    System.out.println("Failed to fetch reviews: " + ex.getMessage());
                    return "[]";
                });

        CompletableFuture<String> inventory = fetchAsync(client, "https://jsonplaceholder.typicode.com/albums")
                .exceptionally(ex -> {
                    System.out.println("Failed to fetch inventory: " + ex.getMessage());
                    return "[]";
                });

        CompletableFuture<Void> combined = CompletableFuture.allOf(products, reviews, inventory)
                .thenRun(() -> {
                    try {
                        System.out.println("Merged Product Data:");
                        System.out.println("Products: " + products.get());
                        System.out.println("Reviews: " + reviews.get());
                        System.out.println("Inventory: " + inventory.get());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        combined.get();
    }

    private static CompletableFuture<String> fetchAsync(HttpClient client, String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("--- Homework 1 ---");
        homework1();

        System.out.println("\n--- Homework 2 & 3 ---");
        homework2and3();
    }
}
