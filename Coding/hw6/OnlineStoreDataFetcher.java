package hw6;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class OnlineStoreDataFetcher {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {

        CompletableFuture<String> productFuture = fetchPartialBody(client, "https://fakestoreapi.com/products");
        CompletableFuture<String> reviewFuture = fetchPartialBody(client, "https://jsonplaceholder.typicode.com/comments");
        CompletableFuture<String> inventoryFuture = fetchPartialBody(client, "https://jsonplaceholder.typicode.com/todos");

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
                e.printStackTrace();
            }
        }).join(); // until all done
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
