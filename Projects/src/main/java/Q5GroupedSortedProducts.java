package com.example.stream;

import java.util.*;
import java.util.stream.*;

class Product {
    String name;
    String category;
    double price;

    Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public String getName() { return name; }
}

public class Q5GroupedSortedProducts {
    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("Phone", "Electronics", 999),
                new Product("TV", "Electronics", 1299),
                new Product("Shirt", "Clothing", 49),
                new Product("Jeans", "Clothing", 79)
        );

        Map<String, List<Product>> result = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparing(Product::getPrice).reversed())
                                        .collect(Collectors.toList())
                        )
                ));

        result.forEach((category, list) -> {
            System.out.println(category + ":");
            list.forEach(p -> System.out.println("  " + p.getName() + " - $" + p.getPrice()));
        });
    }
}
