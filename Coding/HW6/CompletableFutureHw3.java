
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureHw3 {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        CompletableFuture<String> productsFuture = fetchData(client, "https://jsonplaceholder.typicode.com/posts").exceptionally(e -> {
            System.err.println("Failed to fetch products: " + e.getMessage());
            return "Default Products Data";
        });

        CompletableFuture<String> reviewsFutrue = fetchData(client, "https://jsonplaceholder.typicode.com/comments").exceptionally(e -> {
            System.err.println("Failed to fetch reviews: " + e.getMessage());
            return "Default Reviews Data";
        });

        CompletableFuture<String> inventoryFuture = fetchData(client, "https://jsonplaceholder.typicode.com/todos").exceptionally(e -> {
            System.err.println("Failed to fetch inventory: " + e.getMessage());
            return "Default Inventory Data";
        });

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(productsFuture, reviewsFutrue, inventoryFuture);

        allFutures.thenRun(() -> {
            try {
                String products = productsFuture.get();
                String reviews = reviewsFutrue.get();
                String inventory = inventoryFuture.get();

                System.out.println("Products data: " + products);
                System.out.println("Reviews data: " + reviews);
                System.out.println("Inventory data: " + inventory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();
    }

    private static CompletableFuture<String> fetchData(HttpClient client, String url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return response.body();
            } catch (Exception e) {
                throw new RuntimeException("Failed to fetch data from " + url, e);
            }
        });
    }
    
}
