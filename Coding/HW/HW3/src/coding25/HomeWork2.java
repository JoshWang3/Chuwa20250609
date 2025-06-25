package coding25;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HomeWork2 {

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        CompletableFuture<String> productsFuture = fetch(client, "https://jsonplaceholder.typicode.com/users");
        CompletableFuture<String> reviewFuture = fetch(client, "https://jsonplaceholder.typicode.com/comments");
        CompletableFuture<String> inventoryFuture = fetch(client, "https://jsonplaceholder.typicode.com/posts");

        CompletableFuture<Void> allDone = CompletableFuture.allOf(productsFuture,reviewFuture,inventoryFuture);

        allDone.thenRun(() -> {
            try {
                String products = productsFuture.get();
                String reviews = reviewFuture.get();
                String inventory = inventoryFuture.get();

                System.out.println(products);
                System.out.println(reviews);
                System.out.println(inventory);

            } catch (Exception e) { System.out.println(e.getMessage()); }
        }).join();
    }

    public static CompletableFuture<String> fetch(HttpClient client, String url) {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
    }
}
