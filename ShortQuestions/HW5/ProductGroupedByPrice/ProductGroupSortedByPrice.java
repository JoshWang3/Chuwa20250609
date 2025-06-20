package ProductGroupedByPrice;

import java.util.*;
import java.util.stream.*;

public class ProductGroupSortedByPrice {
    public static void main(String[] args) {

        List<Product> products = Arrays.asList(
                new Product("Krabby Patty", "Food", 2.99),
                new Product("Kelp Shake", "Drink", 3.49),
                new Product("Chum Burger", "Food", 1.99),
                new Product("Soda", "Drink", 1.49),
                new Product("Golden Spatula", "Merch", 25.00),
                new Product("Bikini Bottom Hat", "Merch", 15.00),
                new Product("Coral Bits", "Food", 3.79)
        );

        Map<String, List<Product>> groupedByPrice = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                                        .collect(Collectors.toList())
                        )
                ));

        groupedByPrice.forEach((category, productList) -> {
            System.out.println(category + ":" + productList);
        });
    }
}

/*
output:
    Merch:[Golden Spatula($25.0), Bikini Bottom Hat($15.0)]
    Drink:[Kelp Shake($3.49), Soda($1.49)]
    Food:[Coral Bits($3.79), Krabby Patty($2.99), Chum Burger($1.99)]
 */