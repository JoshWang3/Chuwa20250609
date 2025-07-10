
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureHw2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        CompletableFuture<String> productsFuture = fetchData(client, "https://jsonplaceholder.typicode.com/posts");
        CompletableFuture<String> reviewsFuture = fetchData(client, "https://jsonplaceholder.typicode.com/comments");
        CompletableFuture<String> inventoryFuture = fetchData(client, "https://jsonplaceholder.typicode.com/todos");
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(productsFuture, reviewsFuture, inventoryFuture);

        allFutures.thenRun(() -> {
            try {
                String products = productsFuture.get();
                String reviews = reviewsFuture.get();
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
    

