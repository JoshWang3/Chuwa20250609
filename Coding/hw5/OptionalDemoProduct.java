package hw5;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OptionalDemoProduct {
    public static void main(String[] args) {
        Map<Integer, Product> warehouse = new HashMap<>();
        warehouse.put(1, new Product("Apple", "Fruit", 2.50));
        warehouse.put(2, new Product("Broccoli", "Vegetable", 3.99));
        warehouse.put(3, new Product("Steak", "Meat", null));
        warehouse.put(4, null);

        addToCart(warehouse, 1);
        addToCart(warehouse, 3);
        addToCart(warehouse, 4);
    }

    public static void addToCart(Map<Integer, Product> warehouse, int productId) {
        Optional<Product> optionalProduct = Optional.ofNullable(warehouse.get(productId));

        // // Using isPresent() to check if the Optional contains a value (the product is null or not)
        if (optionalProduct.isPresent()) {
            // if it exists, can safely get the product
            Product product = optionalProduct.get();
            // check if price is null, if not null, add it to cart if it has price; otherwise it is out of stock
            Optional.ofNullable(product.getPrice())
                    .ifPresentOrElse(
                            // when the value presents(price is not null), add to cart
                            price -> {
                                System.out.println("Adding " + product.getName() + " to cart. Price: $" + price);
                            },
                            // when the value absents(price is null), error message
                            () -> {
                                System.out.println("Cannot add " + product.getName() + " to cart. Price is missing.");
                            }
                    );
        } else {
            System.out.println("Product ID " + productId + " is out of stock.");
        }
    }
}
