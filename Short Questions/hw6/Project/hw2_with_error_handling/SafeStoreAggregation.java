package Project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.net.http.*;
import java.net.URI;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

class SafeStoreAggregation {
    private static final ObjectMapper M = new ObjectMapper();
    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    private static final String PRODUCTS_URL  = "https://jsonplaceholder.typicode.com/albums";
    private static final String REVIEWS_URL   = "https://jsonplaceholder.typicode.com/comments";
    private static final String INVENTORY_URL = "https://jsonplaceholder.typicode.com/todos";

    // Defaults if something breaks
    private static final List<Product> DEFAULT_PRODUCTS = List.of();
    private static final List<Review>  DEFAULT_REVIEWS  = List.of();
    private static final List<Inventory> DEFAULT_INVENTORY = List.of();

    public static void main(String[] args) {
        CompletableFuture<List<Product>> productsF = fetch(PRODUCTS_URL)
                .orTimeout(8, TimeUnit.SECONDS)
                .thenApply(json -> {
                    record Album(int userId, int id, String title) {}
                    try {
                        List<Album> albums = M.readValue(json, new TypeReference<>() {});
                        return albums.stream().limit(10).map(a -> {
                            Product p = new Product(); p.id = a.id(); p.title = a.title(); return p;
                        }).toList();
                    } catch (Exception e) { throw new CompletionException(e); }
                })
                .exceptionally(ex -> {
                    System.err.println("[products] fallback due to: " + ex);
                    return DEFAULT_PRODUCTS;
                });

        CompletableFuture<List<Review>> reviewsF = fetch(REVIEWS_URL)
                .completeOnTimeout("[]", 8, TimeUnit.SECONDS) // return empty JSON if timeout
                .thenApply(json -> {
                    record Comment(int postId, int id, String name, String email, String body) {}
                    try {
                        List<Comment> cs = M.readValue(json, new TypeReference<>() {});
                        Random r = new Random(42);
                        return cs.stream().limit(100).map(c -> {
                            Review rv = new Review();
                            rv.productId = c.postId(); rv.rating = 1 + r.nextInt(5); rv.comment = c.body();
                            return rv;
                        }).toList();
                    } catch (Exception e) { throw new CompletionException(e); }
                })
                .handle((val, ex) -> {
                    if (ex != null) {
                        System.err.println("[reviews] error: " + ex);
                        return DEFAULT_REVIEWS;
                    }
                    return val;
                });

        CompletableFuture<List<Inventory>> inventoryF = fetch(INVENTORY_URL)
                .orTimeout(8, TimeUnit.SECONDS)
                .thenApply(json -> {
                    record Todo(int userId, int id, String title, boolean completed) {}
                    try {
                        List<Todo> todos = M.readValue(json, new TypeReference<>() {});
                        return todos.stream().limit(20).map(t -> {
                            Inventory inv = new Inventory();
                            inv.productId = t.id();
                            inv.quantity = t.completed() ? 50 : 0;
                            return inv;
                        }).toList();
                    } catch (Exception e) { throw new CompletionException(e); }
                })
                .exceptionally(ex -> {
                    System.err.println("[inventory] fallback due to: " + ex);
                    return DEFAULT_INVENTORY;
                });

        // Use allOf for a clean fan-in and then read results
        CompletableFuture<Void> all = CompletableFuture.allOf(productsF, reviewsF, inventoryF);

        List<ProductView> merged = all.thenApply(v -> {
            List<Product> products = productsF.join();
            List<Review> reviews = reviewsF.join();
            List<Inventory> inventory = inventoryF.join();

            Map<Integer, Double> avgRatingByPid = reviews.stream()
                    .collect(Collectors.groupingBy(rv -> rv.productId,
                            Collectors.averagingInt(rv -> rv.rating)));

            Map<Integer, Integer> qtyByPid = inventory.stream()
                    .collect(Collectors.toMap(i -> i.productId, i -> i.quantity, (a,b)->a));

            return products.stream().map(p -> {
                ProductView v2 = new ProductView();
                v2.id = p.id; v2.title = p.title;
                v2.avgRating = avgRatingByPid.getOrDefault(p.id, 0.0);
                v2.inventory = qtyByPid.getOrDefault(p.id, 0);
                return v2;
            }).toList();
        }).join();

        // Further processing (still works even if one source fell back)
        merged.stream().limit(5).forEach(System.out::println);
    }

    private static CompletableFuture<String> fetch(String url) {
        HttpRequest req = HttpRequest.newBuilder(URI.create(url))
                .GET().timeout(Duration.ofSeconds(10)).build();
        return CLIENT.sendAsync(req, HttpResponse.BodyHandlers.ofString())
                     .thenApply(HttpResponse::body);
    }
}

// Same DTOs as before:
class Product { public int id; public String title; }
class Review  { public int productId; public int rating; public String comment; }
class Inventory { public int productId; public int quantity; }
class ProductView {
    public int id; public String title; public double avgRating; public int inventory;
    @Override public String toString() { return "ProductView{id=" + id + ", title='" + title + "', avgRating=" + avgRating + ", inventory=" + inventory + "}"; }
}
