import java.net.http.*;
import java.net.URI;
import java.util.concurrent.*;

public class OnlineStore {
    static HttpClient client = HttpClient.newHttpClient();
    public static CompletableFuture<String> fetchProducts() {
        return client.sendAsync(
                    HttpRequest.newBuilder()
                        .uri(URI.create("https://fakestoreapi.com/products"))
                        .build(),
                    HttpResponse.BodyHandlers.ofString() //把 HTTP 响应正文转成 String
                ).thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    System.err.println("fetchProducts failed" + ex.getMessage());
                    return "[]";
                });
    }

    public static CompletableFuture<String> fetchReviews() {
        return client.sendAsync(
                HttpRequest.newBuilder()
                        .uri(URI.create("https://jsonplaceholder.typicode.com/comments"))
                        .build(),
                HttpResponse.BodyHandlers.ofString()
            ).thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    System.err.println("fetchReview failed" + ex.getMessage());
                    return "[]";
                });
    }

    public static CompletableFuture<String> fetchInventory() {
        return client.sendAsync(
                        HttpRequest.newBuilder()
                                .uri(URI.create("https://dummyjson.com/products"))
                                .build(),
                        HttpResponse.BodyHandlers.ofString()
                ).thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    System.err.println("fetchInventory failed" + ex.getMessage());
                    return "[]";
                });
    }

    public static void main(String[] args) {
        CompletableFuture<String> products = fetchProducts();
        CompletableFuture<String> reviews = fetchReviews();
        CompletableFuture<String> inventory = fetchInventory();

        CompletableFuture<Void> all = CompletableFuture.allOf(products, reviews, inventory);

        all.thenRun(() -> {
            try {
                String product = products.get().substring(0, 20);
                System.out.println("\n=== Products JSON (前300字符) ===");
                System.out.println(product + "...");
                String review = reviews.get().substring(0, 20);
                System.out.println("\n=== Reviews JSON (前300字符) ===");
                System.out.println(review + "...");
                String invent = inventory.get().substring(0, 20);
                System.out.println("\n=== Inventory JSON (前300字符) ===");
                System.out.println(invent + "...");

                String combined = product + review + invent;
                System.out.println("\n ==== combined result ===");
                System.out.println(combined);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();

    }
}
