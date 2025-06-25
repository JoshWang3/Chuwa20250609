package coding25;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HomeWork3 {

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        CompletableFuture<String> productsFuture = fetch(client, "https://jsonplaceholder.typicode.com/users");
        CompletableFuture<String> reviewFuture = fetch(client, "https://jsonplaceholder.typicode.com/comments");
        CompletableFuture<String> inventoryFuture = fetch(client, "https://jsonplaceholder.typicode.com/posts");

        CompletableFuture<Void> allDone = CompletableFuture.allOf(productsFuture,reviewFuture,inventoryFuture);

        CompletableFuture<String> result = allDone.thenApply(v -> {
            try {
                String products = productsFuture.join();
                String reviews = reviewFuture.join();
                String inventory = inventoryFuture.join();

                return products + " " + reviews + " " + inventory;
            } catch (Exception e) {
                return "Error during merging results: " + e.getMessage();
            }
        });

        result.exceptionally(e -> "Final Error: " + e.getMessage()).thenAccept(System.out::println).join();
    }

    public static CompletableFuture<String> fetch(HttpClient client, String url) {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "Unavailable";
        });
    }
}
