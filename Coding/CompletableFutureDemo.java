import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.concurrent.CompletableFuture;

// Demonstrates CompletableFuture with async HTTP requests
public class CompletableFutureDemo {
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        CompletableFuture<Void> postsFuture = fetchAndPrint("posts");
        CompletableFuture<Void> commentsFuture = fetchAndPrint("comments");
        CompletableFuture<Void> albumsFuture = fetchAndPrint("albums");

        // Wait for all async operations to complete
        CompletableFuture.allOf(postsFuture, commentsFuture, albumsFuture).join();
    }

    private static CompletableFuture<Void> fetchAndPrint(String resource) {
        String url = "https://jsonplaceholder.typicode.com/" + resource;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                     .thenAccept(response -> {
                         System.out.println("=== " + resource.toUpperCase() + " ===");
                         String body = response.body();
                         // Print first 200 chars to avoid clutter
                         System.out.println(body.length() > 200 
                             ? body.substring(0, 200) + "..."
                             : body);
                         System.out.println();
                     });
    }
}