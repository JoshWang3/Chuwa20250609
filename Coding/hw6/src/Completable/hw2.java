package Completable;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class hw2 {
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static CompletableFuture<String> getProductInformation() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://fakestoreapi.com/products"))
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public static CompletableFuture<String> getReviews() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/comments"))
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public static CompletableFuture<String> getInventory() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos"))
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public static void main(String[] args) {
        CompletableFuture<String> productFuture = getProductInformation();
        CompletableFuture<String> reviewsFuture = getReviews();
        CompletableFuture<String> inventoryFuture = getInventory();

        CompletableFuture<Void> combined = CompletableFuture.allOf(
                productFuture, reviewsFuture, inventoryFuture
        );

        combined.thenRun(() -> {
            try {
                String productInfo = productFuture.get();
                String reviews = reviewsFuture.get();
                String inventory = inventoryFuture.get();

                System.out.println("Products: " + productInfo);
                System.out.println("Reviews: " + reviews);
                System.out.println("Inventory: " + inventory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();
    }
}
