package hw7;

import java.net.URI;
import java.net.http.*;
import java.util.concurrent.CompletableFuture;

public class hw1_partthree {
    private static final HttpClient client = HttpClient.newHttpClient();

    public static CompletableFuture<String> fetchWithFallback(String url, String defaultValue) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    System.err.println("Failed to fetch from: " + url);
                    ex.printStackTrace();
                    return defaultValue;
                });
    }

    public static void main(String[] args) {
        CompletableFuture<String> productFuture = fetchWithFallback("https://jsonplaceholder.typicode.com/posts/1", "Default Product");
        CompletableFuture<String> reviewFuture = fetchWithFallback("https://jsonplaceholder.typicode.com/comments/1", "Default Review");
        CompletableFuture<String> inventoryFuture = fetchWithFallback("https://jsonplaceholder.typicode.com/albums/1", "Default Inventory");

        CompletableFuture<Void> all = CompletableFuture.allOf(productFuture, reviewFuture, inventoryFuture);

        all.thenRun(() -> {
            try {
                System.out.println("Product: " + productFuture.get());
                System.out.println("Review: " + reviewFuture.get());
                System.out.println("Inventory: " + inventoryFuture.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();
    }
}

