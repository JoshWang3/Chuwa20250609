import java.util.*;
import java.util.stream.*;

class Product {
    String name;
    String category;
    double price;
    Product(String name, String category, double price) {
        this.name = name; this.category = category; this.price = price;
    }
}

public class ProductGrouping {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1500),
                new Product("Phone", "Electronics", 1200),
                new Product("TV", "Electronics", 2000),
                new Product("Bread", "Groceries", 3),
                new Product("Milk", "Groceries", 5),
                new Product("Butter", "Groceries", 7)
        );

        Map<String, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(p -> p.category));

        grouped.forEach((category, list) -> {
            List<Product> sortedList = list.stream()
                    .sorted((a, b) -> Double.compare(b.price, a.price))
                    .collect(Collectors.toList());
            System.out.println("Category: " + category);
            sortedList.forEach(p -> System.out.println("  " + p.name + " $" + p.price));
        });
    }
}
