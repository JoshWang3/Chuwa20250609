import java.util.*;
import java.util.stream.*;

class Product {
    String name;
    String category;
    double price;

    Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return name + " ($" + price + ")";
    }
}

public class Q1GroupProducts {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("iPhone", "Electronics", 999),
                new Product("TV", "Electronics", 799),
                new Product("Laptop", "Electronics", 1299),
                new Product("Banana", "Groceries", 1),
                new Product("Apple", "Groceries", 2),
                new Product("Orange Juice", "Groceries", 3)
        );

        Map<String, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(p -> p.category,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                                        .collect(Collectors.toList())
                        )));

        grouped.forEach((category, list) -> {
            System.out.println(category + ": " + list);
        });
    }
}

