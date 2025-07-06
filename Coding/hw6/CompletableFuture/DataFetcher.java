package Coding.hw6.CompletableFuture;

import java.net.URI;
import java.net.http.*;
import java.util.concurrent.CompletableFuture;

public class DataFetcher {
    static HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        var productF   = fetchAsync("https://jsonplaceholder.typicode.com/posts");
        var reviewF    = fetchAsync("https://jsonplaceholder.typicode.com/comments");
        var inventoryF = fetchAsync("https://jsonplaceholder.typicode.com/todos");

        // allDone completes when the three above are done
        CompletableFuture<Void> allDone =
                CompletableFuture.allOf(productF, reviewF, inventoryF);

        allDone
                .thenRun(() -> printSnippets(productF, reviewF, inventoryF))
                .join();
    }

    static CompletableFuture<String> fetchAsync(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        return client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(ex -> {
                    System.err.println("Fetch failed for " + url + ": " + ex);
                    return "";
                });
    }

    static void printSnippets(
            CompletableFuture<String> productF,
            CompletableFuture<String> reviewF,
            CompletableFuture<String> inventoryF
    ) {
        try {
            // we know they’re done, so get() won’t block
            System.out.println("=== Product snippet ===");
            System.out.println(truncate(productF.get(), 200));
            System.out.println("\n=== Review snippet ===");
            System.out.println(truncate(reviewF.get(), 200));
            System.out.println("\n=== Inventory snippet ===");
            System.out.println(truncate(inventoryF.get(), 200));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String truncate(String s, int len) {
        if (s == null) return "";
        return s.length() <= len ? s : s.substring(0, len) + "...";
    }
}
