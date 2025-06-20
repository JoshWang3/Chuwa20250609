import java.util.*;
import java.util.stream.Collectors;

public class GroupProductsSimple {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Apple", "Fruits", 1.5),
                new Product("Banana", "Fruits", 1.0),
                new Product("Orange", "Fruits", 2.0),
                new Product("Carrot", "Vegetables", 1.2),
                new Product("Broccoli", "Vegetables", 2.5),
                new Product("Tomato", "Vegetables", 1.8)
        );

        Map<String, List<Product>> groupedSorted = products.stream()
                // Step 1: Group by category
                .collect(Collectors.groupingBy(Product::getCategory))  // Map<String, List<Product>>
                .entrySet()
                .stream()
                // Step 2: Sort each group by price descending
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // category name
                        entry -> entry.getValue().stream()
                                .sorted(Comparator.comparing(Product::getPrice).reversed())
                                .collect(Collectors.toList())
                ));

        // Output the result
        groupedSorted.forEach((category, list) -> {
            System.out.println("Category: " + category);
            list.forEach(System.out::println);
            System.out.println();
        });
    }
}
