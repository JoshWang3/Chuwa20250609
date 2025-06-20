import java.util.*;
import java.util.stream.Collectors;

class getProducts {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 1200.00, "Electronics"),
            new Product("Smartphone", 800.00, "Electronics"),
            new Product("Tablet", 300.00, "Electronics"),
            new Product("Headphones", 150.00, "Accessories"),
            new Product("Smartwatch", 200.00, "Accessories"),
            new Product("Bluetooth Speaker", 100.00, "Accessories"),
            new Product("Camera", 500.00, "Electronics"),
            new Product("Printer", 250.00, "Electronics"),
            new Product("Monitor", 300.00, "Electronics")
        );

        Map<String, List<Product>> productsByCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory, 
                Collectors.collectingAndThen(
                    Collectors.toList(), 
                    list -> list.stream()
                            .sorted(Comparator.comparingDouble(Product::getPrice))
                            .collect(Collectors.toList())
                )));
        

            productsByCategory.forEach((category, productList) -> {
            System.out.println(category + " : " + productList.stream()
                .map(product -> product.getName() + "$ " + product.getPrice())
                .collect(Collectors.joining(", ")));
            });
        }

}