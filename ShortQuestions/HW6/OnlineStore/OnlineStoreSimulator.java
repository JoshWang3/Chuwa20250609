package OnlineStore;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.concurrent.CompletableFuture;


public class OnlineStoreSimulator {

    static HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) throws Exception {
        CompletableFuture<String> productFuture = fetchAsync("https://jsonplaceholder.typicode.com/posts");
        CompletableFuture<String> reviewFuture = fetchAsync("https://jsonplaceholder.typicode.com/comments");
        CompletableFuture<String> inventoryFuture = fetchAsync("https://jsonplaceholder.typicode.com/todos");

        CompletableFuture<Void> allDone = CompletableFuture.allOf(productFuture, reviewFuture, inventoryFuture);

        allDone.thenRun(() -> {
            try {
                System.out.println("Product: " + productFuture.get().substring(0, 200));
                System.out.println("Review: " + reviewFuture.get().substring(0, 200));
                System.out.println("Inventory: " + inventoryFuture.get().substring(0, 200));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(3000);
    }
    static CompletableFuture<String> fetchAsync(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}
