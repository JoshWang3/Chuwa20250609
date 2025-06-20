import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Product{" + "name='" + name + '\'' + ", price=" + price + '}';
    }
}

public class ProductGrouper {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.00),
                new Product("Mouse", "Electronics", 25.50),
                new Product("Keyboard", "Electronics", 75.00),
                new Product("T-Shirt", "Apparel", 20.00),
                new Product("Jeans", "Apparel", 90.00),
                new Product("Jacket", "Apparel", 150.00),
                new Product("Coffee Maker", "Home Goods", 80.00),
                new Product("Blender", "Home Goods", 110.00)
        );

        Map<String, List<Product>> groupedAndSortedProducts = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    list.sort(Comparator.comparingDouble(Product::getPrice).reversed());
                                    return list;
                                }
                        )
                ));

        groupedAndSortedProducts.forEach((category, productList) -> {
            System.out.println("Category: " + category);
            productList.forEach(product -> System.out.println("  " + product));
        });
    }
}