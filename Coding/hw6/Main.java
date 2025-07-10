import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.*;

public class Main {

    static ExecutorService executor = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {

        CompletableFuture<String> productFuture = fetchAsync(
                "https://jsonplaceholder.typicode.com/posts/1", "Product"
        );

        CompletableFuture<String> reviewFuture = fetchAsync(
                "https://jsonplaceholder.typicode.com/comments?postId=1", "Review"
        );

        CompletableFuture<String> inventoryFuture = fetchAsync(
                "https://jsonplaceholder.typicode.com/users/1", "Inventory"
        );

        // Combine all three
        CompletableFuture<Void> allDone = CompletableFuture.allOf(
                productFuture, reviewFuture, inventoryFuture
        );

        allDone.thenRun(() -> {
            try {
                String product = productFuture.get();
                String review = reviewFuture.get();
                String inventory = inventoryFuture.get();

                System.out.println("=== Merged API Results ===");
                System.out.println("Product:\n" + product);
                System.out.println("Review:\n" + review);
                System.out.println("Inventory:\n" + inventory);
            } catch (Exception e) {
                System.out.println("Error merging results: " + e.getMessage());
            }
        });

        // Wait for async tasks
        try {
            Thread.sleep(3000);
            executor.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Utility to fetch API and handle exceptions
    public static CompletableFuture<String> fetchAsync(String urlString, String tag) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                URL url = new URL(urlString);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                int code = con.getResponseCode();
                if (code != 200) throw new RuntimeException(tag + " API failed with HTTP code " + code);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream())
                );
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line).append("\n");
                }
                in.close();
                return response.toString();
            } catch (Exception e) {
                System.out.println("[" + tag + "] Error: " + e.getMessage());
                return "[" + tag + "] Default response due to error.";
            }
        }, executor);
    }
}

