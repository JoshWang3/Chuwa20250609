package hw5;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Group products by category and sort each group by price descending
public class ProductsCategory {
    public static void main(String[] args) {
        List<Product> productsList = Arrays.asList(
                new Product("iPhone", "Electronics", 999.0),
                new Product("Samsung Galaxy", "Electronics", 899.0),
                new Product("MacBook", "Electronics", 1299.0),
                new Product("Banana", "Food", 1.2),
                new Product("Steak", "Food", 25.5),
                new Product("Salmon", "Food", 18.0),
                new Product("Desk Chair", "Furniture", 120.0),
                new Product("Sofa", "Furniture", 700.0)
        );
        // get the grouped the product list by category
        // {Electronics=[iPhone:999.0, Samsung Galaxy:899.0, MacBook:1299.0],...}
        Map<String, List<Product>> grouped = productsList.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        // System.out.println(grouped);

        // turn the "grouped" - the map object to stream of Map.Entry objects
        Map<String, List<Product>> sortedGroup = grouped.entrySet().stream()
                // collect the stream to another new map
                .collect(Collectors.toMap(
                        // use the original map's key as the new map's key
                        Map.Entry::getKey,
                        // process each value (List<Product>) before putting into the new map
                        entry -> entry.getValue().stream()
                                // Sort the product list by price in descending order
                                .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                                // Collect the sorted list back into a List
                                .collect(Collectors.toList())
                ));

        sortedGroup.forEach((category, productList) -> {
            System.out.println(category + ":");
            // productList: List<Product>, need forEach to iterate every product in the list
            productList.forEach(System.out::println);
        });
    }
}
