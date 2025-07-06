package Coding.hw6.CompletableFuture;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class DFException {
    static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    public static void main(String[] args) {
        var productF   = fetchAsync("https://jsonplaceholder.typicode.com/posts");
        var reviewF    = fetchAsync("https://jsonplaceholder.typicode.com/comments");
        var inventoryF = fetchAsync("https://jsonplaceholder.typicode.com/todos");

        // CompletableFuture that completes when all three are done (success or failure)
        CompletableFuture<Void> allDone = CompletableFuture
                .allOf(productF, reviewF, inventoryF)
                // handle any exception in the group
                .handle((v, ex) -> {
                    if (ex != null) {
                        System.err.println("One or more fetches failed: " + ex.getMessage());
                    }
                    return null;
                });

        allDone
                .thenRun(() -> printSnippets(productF, reviewF, inventoryF))
                .join();  // wait for completion
    }

    static CompletableFuture<String> fetchAsync(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();

        return client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                // check HTTP status
                .thenCompose(resp -> {
                    if (resp.statusCode() / 100 != 2) {
                        return CompletableFuture.failedFuture(
                                new IOException("HTTP " + resp.statusCode() + " from " + url)
                        );
                    }
                    return CompletableFuture.completedFuture(resp.body());
                })
                // catch any error and return a placeholder
                .exceptionally(ex -> {
                    System.err.println("Fetch failed for " + url + ": " + ex.getMessage());
                    return "";
                });
    }

    static void printSnippets(
            CompletableFuture<String> productF,
            CompletableFuture<String> reviewF,
            CompletableFuture<String> inventoryF
    ) {
        System.out.println("=== Results ===");
        printOne("Product",   productF);
        printOne("Review",    reviewF);
        printOne("Inventory", inventoryF);
    }

    private static void printOne(String name, CompletableFuture<String> cf) {
        try {
            String body = cf.get();
            if (body.isEmpty()) {
                System.out.printf("%s: <no data>%n", name);
            } else {
                System.out.printf("%s snippet:%n%s%n%n", name, truncate(body, 200));
            }
        } catch (Exception e) {
            System.err.printf("%s: failed to retrieve result: %s%n", name, e.getMessage());
        }
    }

    static String truncate(String s, int len) {
        if (s == null || s.isEmpty()) return "<empty>";
        return s.length() <= len ? s : s.substring(0, len) + "...";
    }
}
