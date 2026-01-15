package OnlineStore;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.concurrent.CompletableFuture;


public class OnlineStoreWithException {

    // shared HTTP client instance -> in order to make async requests
    static HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) throws Exception {

        // asynchronously fetch product, review, inventory data
        CompletableFuture<String> productFuture = fetchAsync("https://jsonplaceholder.typicode.com/posts")
                .exceptionally(e -> {
                    System.out.println("Failed to fetch products: " + e.getMessage());
                    return "Default Product Data";
                });

        CompletableFuture<String> reviewFuture = fetchAsync("https://jsonplaceholder.typicode.com/comments")
                .exceptionally(e -> {
                    System.out.println("Failed to fetch reviews: " + e.getMessage());
                    return "Default Review Data";
                });

        CompletableFuture<String> inventoryFuture = fetchAsync("https://jsonplaceholder.typicode.com/todos")
                .exceptionally(e -> {
                    System.out.println("Failed to fetch inventory: " + e.getMessage());
                    return "Default Inventory Data";
                });

        // combine all three futures to wait until all complete
        CompletableFuture<Void> allDone = CompletableFuture.allOf(productFuture, reviewFuture, inventoryFuture);

        // once all data is fetched print
        allDone.thenRun(() -> {
            try {
                System.out.println("Product: " + productFuture.get().substring(0, 200));
                System.out.println("Review: " + reviewFuture.get().substring(0, 200));
                System.out.println("Inventory: " + inventoryFuture.get().substring(0, 200));
            } catch (Exception e) {
                System.err.println("Error while merging data " + e.getMessage());
            }
        });

        Thread.sleep(3000); // keep the main thread alive
    }

    // fetch data from URL asynchronously
    static CompletableFuture<String> fetchAsync(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // send request asynchronously and return body as string
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}
