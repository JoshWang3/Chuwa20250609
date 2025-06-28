package hw6.completable_future;

import java.net.URI;
import java.net.http.*;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class Homework2 {
    public static void main(String[] args) {
        // create an instance of HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Product API
        CompletableFuture<String> productFuture = fetchAsync(client, "https://jsonplaceholder.typicode.com/posts/1", "{}");
        // Reviews API
        CompletableFuture<String> reviewsFuture = fetchAsync(client, "https://jsonplaceholder.typicode.com/comments?postId=1", "[]");
        // Inventory API
        CompletableFuture<String> inventoryFuture = fetchAsync(client, "https://jsonplaceholder.typicode.com/todos/1", "{}");
        // test aN invalid API
        // CompletableFuture<String> inventoryFuture = fetchAsync(client, "https://jsonplaceholder.typicode.com/invalidpath", "{}");

        // Wait for all to complete
        CompletableFuture<Void> allFuture = CompletableFuture.allOf(productFuture, reviewsFuture, inventoryFuture);

        allFuture.thenRun(() -> {
            try {
                String productData = productFuture.get();
                String reviewsData = reviewsFuture.get();
                String inventoryData = inventoryFuture.get();

                System.out.println("=== Product Info ===");
                System.out.println(productData);

                System.out.println("\n=== Reviews ===");
                System.out.println(reviewsData);

                System.out.println("\n=== Inventory ===");
                System.out.println(inventoryData);

            } catch (Exception e) {
                // exception handling when call get()
                System.err.println("Unexpected error during result retrieval: " + e.getMessage());
                e.printStackTrace();
            }
        }).join(); // wait for all

    }

    // Helper method for async fetch
    private static CompletableFuture<String> fetchAsync(HttpClient client, String url, String defaultValue) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                // test a timed out API request
                //.uri(URI.create("https://httpstat.us/200?sleep=10000")) // 10秒延迟
                //.timeout(Duration.ofSeconds(2))// 设置超时时间
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                // homework3: exception handling during any API call
                .exceptionally(ex -> {
                    System.err.println("Failed to fetch from: " + url);
                    ex.printStackTrace(); // logging the exception
                    return defaultValue; // return a default value
                });
    }
}


