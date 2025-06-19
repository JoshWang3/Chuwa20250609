package Coding;
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

    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

public class GroupedProducts {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("MacBook", "Electronics", 1500),
                new Product("iPhone", "Electronics", 1000),
                new Product("Desk", "Furniture", 300),
                new Product("Chair", "Furniture", 150),
                new Product("Monitor", "Electronics", 250)
        );

        Map<String, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                            .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                                            .collect(Collectors.toList())
                        )
                ));

        grouped.forEach((category, list) -> {
            System.out.println(category + ":");
            list.forEach(p -> System.out.println("  " + p));
        });
    }
}
