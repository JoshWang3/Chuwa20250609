import java.util.*;
import java.util.stream.Collectors;

class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}

public class CategoryPrice {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("iPhone", "Electronics", 999),
                new Product("MacBook", "Electronics", 1299),
                new Product("Galaxy", "Electronics", 899),
                new Product("Banana", "Groceries", 0.99),
                new Product("Milk", "Groceries", 2.49),
                new Product("Bread", "Groceries", 1.99),
                new Product("T-Shirt", "Clothing", 19.99),
                new Product("Jeans", "Clothing", 49.99),
                new Product("Jacket", "Clothing", 89.99)
        );

        Map<String, List<Product>> productByCategory = products.stream()
                .collect(Collectors.groupingBy(product -> product.getCategory(), Collectors.toList()));

        productByCategory.values().forEach(list ->
                list.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice())));

        productByCategory.forEach((k, v) -> {
            System.out.println(k + ": ");
            v.forEach(product -> System.out.println(product.getName() + " - " + product.getCategory() + " - " + product.getPrice()));
        });
    }
}
