package org.hw5;

import java.util.*;
import java.util.stream.Collectors;

// Product class with optional category
class Product {
    private String name;
    private String category;  // Can be null
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("%s [%s] - $%.2f", name, category, price);
    }
}

// Category service that might return null
class CategoryService {
    private static final Map<String, String> categoryDescriptions = Map.of(
            "ELECTRONICS", "Electronic devices and gadgets",
            "CLOTHING", "Apparel and fashion items",
            "HOME", "Home and kitchen appliances"
    );

    // Traditional method that can return null - DANGEROUS!
    public static String getCategoryDescription(String category) {
        return categoryDescriptions.get(category);
    }

    // Safe method using Optional
    public static Optional<String> getCategoryDescriptionSafe(String category) {
        return Optional.ofNullable(categoryDescriptions.get(category));
    }

    // Method that might return null product
    public static Product findProductById(List<Product> products, String id) {
        // Simulating database lookup that might return null
        return products.stream()
                .filter(p -> p.getName().contains(id))
                .findFirst()
                .orElse(null);  // DANGEROUS!
    }

    // Safe method using Optional
    public static Optional<Product> findProductByIdSafe(List<Product> products, String id) {
        return products.stream()
                .filter(p -> p.getName().contains(id))
                .findFirst();
    }
}

public class OptionalNullPreventionDemo {

    public static void main(String[] args) {
        // Sample data with some null categories
        List<Product> products = Arrays.asList(
                new Product("iPhone 14", "ELECTRONICS", 999.99),
                new Product("Mystery Item", null, 50.00),  // null category
                new Product("T-Shirt", "CLOTHING", 29.99),
                new Product("Unknown Gadget", null, 75.00)  // null category
        );

        System.out.println("=== DEMONSTRATING NULL POINTER EXCEPTIONS ===\n");

        // 1. Traditional null-prone approach
        demonstrateNullProneCode(products);

        System.out.println("\n=== DEMONSTRATING OPTIONAL SAFETY ===\n");

        // 2. Safe Optional approach
        demonstrateOptionalSafety(products);

        System.out.println("\n=== OPTIONAL CHAINING EXAMPLES ===\n");

        // 3. Optional chaining and transformations
        demonstrateOptionalChaining(products);

        System.out.println("\n=== FILTERING WITH OPTIONAL ===\n");

        // 4. Safe filtering and processing
        demonstrateSafeFiltering(products);
    }

    // DANGEROUS: Traditional null-prone code
    public static void demonstrateNullProneCode(List<Product> products) {
        System.out.println("1. Traditional null-prone approach:");

        for (Product product : products) {
            try {
                // This can throw NullPointerException!
                String category = product.getCategory();
                String description = CategoryService.getCategoryDescription(category);

                // Multiple potential null pointer exceptions here:
                System.out.println("Product: " + product.getName() +
                        ", Category: " + category.toUpperCase() +  // NPE if category is null
                        ", Description: " + description.substring(0, 10)); // NPE if description is null

            } catch (NullPointerException e) {
                System.out.println("💥 NullPointerException for: " + product.getName());
            }
        }
    }

    // SAFE: Optional-based approach
    public static void demonstrateOptionalSafety(List<Product> products) {
        System.out.println("2. Safe Optional approach:");

        for (Product product : products) {
            // Safe handling with Optional
            Optional<String> categoryOpt = Optional.ofNullable(product.getCategory());

            String categoryDisplay = categoryOpt
                    .map(String::toUpperCase)
                    .orElse("UNCATEGORIZED");

            String descriptionDisplay = categoryOpt
                    .flatMap(CategoryService::getCategoryDescriptionSafe)
                    .map(desc -> desc.length() > 10 ? desc.substring(0, 10) + "..." : desc)
                    .orElse("No description");

            System.out.println("✅ Product: " + product.getName() +
                    ", Category: " + categoryDisplay +
                    ", Description: " + descriptionDisplay);
        }
    }

    // Optional chaining and transformations
    public static void demonstrateOptionalChaining(List<Product> products) {
        System.out.println("3. Optional chaining examples:");

        // Chain multiple Optional operations
        products.forEach(product -> {
            String result = Optional.ofNullable(product.getCategory())
                    .filter(cat -> !cat.isEmpty())                    // Filter non-empty
                    .map(String::toUpperCase)                         // Transform to uppercase
                    .flatMap(CategoryService::getCategoryDescriptionSafe)  // Get description
                    .map(desc -> "📝 " + desc)                        // Add emoji
                    .orElse("❓ Category information not available");

            System.out.println(product.getName() + ": " + result);
        });
    }

    // Safe filtering and processing with Optional
    public static void demonstrateSafeFiltering(List<Product> products) {
        System.out.println("4. Safe filtering with Optional:");

        // Group products by category safely
        Map<String, List<Product>> categorizedProducts = products.stream()
                .collect(Collectors.groupingBy(product ->
                        Optional.ofNullable(product.getCategory()).orElse("UNCATEGORIZED")
                ));

        // Safe product lookup
        String searchTerm = "iPhone";
        Optional<Product> foundProduct = CategoryService.findProductByIdSafe(products, searchTerm);

        foundProduct.ifPresentOrElse(
                product -> {
                    String categoryInfo = Optional.ofNullable(product.getCategory())
                            .flatMap(CategoryService::getCategoryDescriptionSafe)
                            .map(desc -> " (" + desc + ")")
                            .orElse(" (No category info)");

                    System.out.println("🔍 Found: " + product.getName() + categoryInfo);
                },
                () -> System.out.println("❌ Product containing '" + searchTerm + "' not found")
        );

        // Safe processing of potentially null values
        System.out.println("\n📊 Category Summary:");
        categorizedProducts.forEach((category, productList) -> {
            double avgPrice = productList.stream()
                    .mapToDouble(Product::getPrice)
                    .average()
                    .orElse(0.0);

            System.out.printf("   %s: %d products, avg price: $%.2f%n",
                    category, productList.size(), avgPrice);
        });
    }

    // Utility methods showing Optional best practices
    public static class OptionalBestPractices {

        // ✅ Good: Return Optional from methods that might not have a value
        public static Optional<Product> findMostExpensiveInCategory(List<Product> products, String category) {
            return products.stream()
                    .filter(p -> Objects.equals(Optional.ofNullable(p.getCategory()).orElse(""), category))
                    .max(Comparator.comparingDouble(Product::getPrice));
        }

        // ✅ Good: Use Optional.ofNullable for potentially null values
        public static Optional<String> processCategory(String category) {
            return Optional.ofNullable(category)
                    .filter(cat -> !cat.trim().isEmpty())
                    .map(String::toUpperCase);
        }

        // ✅ Good: Provide meaningful defaults
        public static String getCategoryDisplayName(Product product) {
            return Optional.ofNullable(product.getCategory())
                    .map(String::toUpperCase)
                    .orElse("GENERAL");
        }

        // ❌ Avoid: Don't use Optional.get() without checking
        // public static String badExample(Product product) {
        //     return Optional.ofNullable(product.getCategory()).get(); // Can throw!
        // }

        // ✅ Good: Use orElseThrow with meaningful exception
        public static String getCategoryOrThrow(Product product) {
            return Optional.ofNullable(product.getCategory())
                    .orElseThrow(() -> new IllegalStateException("Product must have a category: " + product.getName()));
        }
    }
}