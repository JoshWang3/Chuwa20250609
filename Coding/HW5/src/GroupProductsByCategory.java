import java.util.*;
import java.util.stream.Collectors;

class Product {
    String name;
    String category;
    double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

public class GroupProductsByCategory {
    public static  Map<String, List<Product>> groupProductsByCategorySorted(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                                        .collect(Collectors.toList())
                        )
                ));
    }

    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("iPhone", "Electronics", 999),
                new Product("MacBook", "Electronics", 1299),
                new Product("T-Shirt", "Clothing", 29),
                new Product("Jeans", "Clothing", 59),
                new Product("Blender", "Home", 49)
        );
        Map<String, List<Product>> result = groupProductsByCategorySorted(products);
        result.forEach((category, items) -> {
            System.out.println(category + ": " + items);
        });
    }
}
