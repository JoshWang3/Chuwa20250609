package hw6;

import java.net.URI;
import java.net.http.*;
import java.util.concurrent.CompletableFuture;

public class OnlineStoreDataFetcherWithExceptionHandling {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {

        CompletableFuture<String> productFuture = fetchPartialBody(client, "https://fakestoreapi.com/products")
                .exceptionally(ex -> {
                    System.err.println("Failed to fetch products: " + ex.getMessage());
                    return "[Default Product Data]";
                });

        CompletableFuture<String> reviewFuture = fetchPartialBody(client, "https://jsonplaceholder.typicode.com/comments")
                .exceptionally(ex -> {
                    System.err.println("Failed to fetch reviews: " + ex.getMessage());
                    return "[Default Review Data]";
                });

        CompletableFuture<String> inventoryFuture = fetchPartialBody(client, "https://jsonplaceholder.typicode.com/todos")
                .exceptionally(ex -> {
                    System.err.println("Failed to fetch inventory: " + ex.getMessage());
                    return "[Default Inventory Data]";
                });

        CompletableFuture<Void> allDone = CompletableFuture.allOf(productFuture, reviewFuture, inventoryFuture);

        allDone.thenRun(() -> {
            try {
                System.out.println("=== Products ===");
                System.out.println(productFuture.get());

                System.out.println("\n=== Reviews ===");
                System.out.println(reviewFuture.get());

                System.out.println("\n=== Inventory ===");
                System.out.println(inventoryFuture.get());
            } catch (Exception e) {
                System.err.println("Unexpected error when fetching futures: " + e.getMessage());
                e.printStackTrace();
            }
        }).join();
    }

    private static CompletableFuture<String> fetchPartialBody(HttpClient client, String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> body.substring(0, Math.min(200, body.length())) + "...");
    }
}

