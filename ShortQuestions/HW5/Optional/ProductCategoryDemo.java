package Optional;

import ProductGroupedByPrice.Product;
import java.util.*;
import java.util.stream.*;

public class ProductCategoryDemo {
    public static void main(String[] args) {
        Product product = new Product("Golden Spatula", null, 25.0);

        // without optional:
        int categoryLength = 0;
        String category = product.getCategory();
        if (category != null) {
            categoryLength = category.length();
        }
        System.out.println("Length of categories without Optional: " + categoryLength); // output: Length of categories without Optional: 0

        // with optional:
        int categoryLengthWithOptionalL = Optional.ofNullable(product.getCategory())
                .map(String::length)
                .orElse(0);
        System.out.println("Length of categories with Optional: " + categoryLengthWithOptionalL); // output: Length of categories with Optional: 0
    }
}
