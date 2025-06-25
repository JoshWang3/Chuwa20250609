package hw7;

import java.net.URI;
import java.net.http.*;
import java.util.concurrent.CompletableFuture;

public class hw1_parttwo {
    private static final HttpClient client = HttpClient.newHttpClient();

    public static CompletableFuture<String> fetchProducts() {
        return fetch("https://jsonplaceholder.typicode.com/posts/1");
    }

    public static CompletableFuture<String> fetchReviews() {
        return fetch("https://jsonplaceholder.typicode.com/comments/1");
    }

    public static CompletableFuture<String> fetchInventory() {
        return fetch("https://jsonplaceholder.typicode.com/albums/1");
    }

    private static CompletableFuture<String> fetch(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public static void main(String[] args) {
        CompletableFuture<String> productFuture = fetchProducts();
        CompletableFuture<String> reviewFuture = fetchReviews();
        CompletableFuture<String> inventoryFuture = fetchInventory();

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

