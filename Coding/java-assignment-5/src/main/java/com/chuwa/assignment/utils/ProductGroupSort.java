package com.chuwa.assignment.utils;

import com.chuwa.assignment.pojos.Product;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductGroupSort {

    public static void main(String[] args) {
    //Group products by category and sort each group by price descending
    List<Product> productList = List.of(
            new Product("iPhone", "Electronics", 999),
            new Product("TV", "Electronics", 799),
            new Product("Laptop", "Electronics", 1200),
            new Product("Banana", "Groceries", 1.5),
            new Product("Steak", "Groceries", 12.5),
            new Product("Orange", "Groceries", 2.0)
    );

    Map<String, List<Product>> productGroupMap = productList.stream()
            .collect(Collectors.groupingBy(
                    Product::getCategory,
                    Collectors.collectingAndThen( Collectors.toList(),
                            p -> p.stream()
                                    .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                                    .collect(Collectors.toList())
                    )
            ));

        productGroupMap.forEach((category, products) ->{
            System.out.println(category + ":");
            products.forEach(product -> System.out.println(" -" + product));
        });
}
}
