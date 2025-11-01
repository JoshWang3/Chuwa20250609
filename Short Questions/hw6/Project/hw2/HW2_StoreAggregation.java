package Project;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.TypeReference;

import java.net.http.*;
import java.net.URI;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

// Simple DTOs
class Product { public int id; public String title; }
class Review  { public int productId; public int rating; public String comment; }
class Inventory { public int productId; public int quantity; }

// Aggregated view for “further processing”
class ProductView {
    public int id;
    public String title;
    public double avgRating;
    public int inventory;

    @Override public String toString() {
        return "ProductView{id=" + id + ", title='" + title + "', avgRating=" + avgRating +
               ", inventory=" + inventory + "}";
    }
}

public class HW2_StoreAggregation {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    // You can point these to real public APIs. Here are “fake” placeholders:
    private static final String PRODUCTS_URL  = "https://jsonplaceholder.typicode.com/albums";    // id, title
    private static final String REVIEWS_URL   = "https://jsonplaceholder.typicode.com/comments";  // postId as productId
    private static final String INVENTORY_URL = "https://jsonplaceholder.typicode.com/todos";     // id as productId, completed -> quantity demo

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(6);

        CompletableFuture<List<Product>> productsF = fetch(PRODUCTS_URL)
            .thenApply(json -> {
                // Map jsonplaceholder “albums” into Product
                record Album(int userId, int id, String title) {}
                try {
                    List<Album> albums = MAPPER.readValue(json, new TypeReference<>() {});
                    return albums.stream().limit(10).map(a -> {
                        Product p = new Product();
                        p.id = a.id(); p.title = a.title();
                        return p;
                    }).collect(Collectors.toList());
                } catch (Exception e) { throw new CompletionException(e); }
            });

        CompletableFuture<List<Review>> reviewsF = fetch(REVIEWS_URL)
            .thenApply(json -> {
                // Map jsonplaceholder “comments” into Review; use postId as productId, random rating
                record Comment(int postId, int id, String name, String email, String body) {}
                try {
                    List<Comment> comments = MAPPER.readValue(json, new TypeReference<>() {});
                    Random r = new Random(42);
                    return comments.stream().limit(100).map(c -> {
                        Review rv = new Review();
                        rv.productId = c.postId();
                        rv.rating = 1 + r.nextInt(5);
                        rv.comment = c.body();
                        return rv;
                    }).collect(Collectors.toList());
                } catch (Exception e) { throw new CompletionException(e); }
            });

        CompletableFuture<List<Inventory>> inventoryF = fetch(INVENTORY_URL)
            .thenApply(json -> {
                // Map jsonplaceholder “todos”: id -> productId, completed -> quantity demo
                record Todo(int userId, int id, String title, boolean completed) {}
                try {
                    List<Todo> todos = MAPPER.readValue(json, new TypeReference<>() {});
                    return todos.stream().limit(20).map(t -> {
                        Inventory inv = new Inventory();
                        inv.productId = t.id();
                        inv.quantity = t.completed() ? 50 : 0; // demo
                        return inv;
                    }).collect(Collectors.toList());
                } catch (Exception e) { throw new CompletionException(e); }
            });

        // Merge all three once complete
        CompletableFuture<List<ProductView>> merged =
            productsF.thenCombine(reviewsF, (products, reviews) -> Map.of("products", products, "reviews", reviews))
                     .thenCombine(inventoryF, (pr, inv) -> {
                         List<Product> products = (List<Product>) pr.get("products");
                         List<Review>  reviews  = (List<Review>)  pr.get("reviews");
                         List<Inventory> inventory = inv;

                         Map<Integer, Double> avgRatingByPid = reviews.stream()
                                 .collect(Collectors.groupingBy(rv -> rv.productId,
                                         Collectors.averagingInt(rv -> rv.rating)));

                         Map<Integer, Integer> qtyByPid = inventory.stream()
                                 .collect(Collectors.toMap(i -> i.productId, i -> i.quantity, (a,b)->a));

                         return products.stream().map(p -> {
                             ProductView v = new ProductView();
                             v.id = p.id;
                             v.title = p.title;
                             v.avgRating = avgRatingByPid.getOrDefault(p.id, 0.0);
                             v.inventory = qtyByPid.getOrDefault(p.id, 0);
                             return v;
                         }).collect(Collectors.toList());
                     });

        // “Further processing”: print top 5 by rating
        merged.thenAccept(list -> list.stream()
                .sorted(Comparator.comparingDouble((ProductView v) -> v.avgRating).reversed())
                .limit(5)
                .forEach(System.out::println)
        ).join();

        pool.shutdown();
    }

    private static CompletableFuture<String> fetch(String url) {
        HttpRequest req = HttpRequest.newBuilder(URI.create(url))
                .GET().timeout(Duration.ofSeconds(10)).build();
        return CLIENT.sendAsync(req, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}
