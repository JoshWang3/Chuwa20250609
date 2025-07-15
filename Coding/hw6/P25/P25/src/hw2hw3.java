import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class hw2hw3 {

    static HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> productsFuture = fetchData("https://jsonplaceholder.typicode.com/posts/1");
        CompletableFuture<String> reviewsFuture = fetchData("https://jsonplaceholder.typicode.com/comments/1");
        CompletableFuture<String> inventoryFuture = fetchData("https://jsonplaceholder.typicode.com/users/1");

        // Combine all futures
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        allFutures.thenRun(() -> {
            try {
                String products = productsFuture.get();
                String reviews = reviewsFuture.get();
                String inventory = inventoryFuture.get();

                System.out.println("Products: " + products);
                System.out.println("Reviews: " + reviews);
                System.out.println("Inventory: " + inventory);

                // Combine/merge processing example
                System.out.println("\n--- Merged Result ---");
                System.out.println("Product Info: " + products);
                System.out.println("Review Info: " + reviews);
                System.out.println("Inventory Info: " + inventory);

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).join(); // Wait for all to complete
    }

    private static CompletableFuture<String> fetchData(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}
