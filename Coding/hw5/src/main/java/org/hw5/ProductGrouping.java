package org.hw5;

import java.util.*;
import java.util.stream.Collectors;


//1.5. Group products by category and sort each group by price descending
public class ProductGrouping {

    // Product class
    static class Product {
        private String name;
        private String category;
        private double price;

        public Product(String name, String category, double price) {
            this.name = name;
            this.category = category;
            this.price = price;
        }

        // Getters
        public String getName() { return name; }
        public String getCategory() { return category; }
        public double getPrice() { return price; }

        @Override
        public String toString() {
            return String.format("%s - $%.2f", name, price);
        }
    }

    /**
     * Groups products by category and sorts each group by price descending
     * @param products List of products to process
     * @return Map with category as key and sorted list of products as value
     */
    public static Map<String, List<Product>> groupAndSortProducts(List<Product> products) {
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

    /**
     * Alternative approach: Group first, then sort each group
     * @param products List of products to process
     * @return Map with category as key and sorted list of products as value
     */
    public static Map<String, List<Product>> groupAndSortProductsAlternative(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getCategory))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                                .collect(Collectors.toList())
                ));
    }

    /**
     * Returns a LinkedHashMap to maintain category order (sorted alphabetically)
     * @param products List of products to process
     * @return LinkedHashMap with categories sorted alphabetically
     */
    public static Map<String, List<Product>> groupSortAndOrderCategories(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        TreeMap::new,  // Use TreeMap for sorted categories
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                                        .collect(Collectors.toList())
                        )
                ));
    }

    // Demo method
    public static void main(String[] args) {
        // Sample data
        List<Product> products = Arrays.asList(
                new Product("iPhone 14", "Electronics", 999.99),
                new Product("Samsung Galaxy", "Electronics", 899.99),
                new Product("iPad Pro", "Electronics", 1099.99),
                new Product("T-Shirt", "Clothing", 29.99),
                new Product("Jeans", "Clothing", 79.99),
                new Product("Jacket", "Clothing", 149.99),
                new Product("Coffee Maker", "Home", 89.99),
                new Product("Blender", "Home", 129.99),
                new Product("Toaster", "Home", 39.99)
        );

        // Apply the algorithm
        Map<String, List<Product>> groupedProducts = groupAndSortProducts(products);

        // Display results
        groupedProducts.forEach((category, productList) -> {
            System.out.println(category + ":");
            productList.forEach(product -> System.out.println("  " + product));
            System.out.println();
        });
    }
}