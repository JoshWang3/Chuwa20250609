package hw5;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Group products by category and sort each group by price descending
 */
public class ProductGrouper {
    public static void main(String[] args) {
        List<Product> productList = Arrays.asList(
                new Product("iPhone", "Electronics", 999.0),
                new Product("Samsung TV", "Electronics", 1499.0),
                new Product("Banana", "Groceries", 0.99),
                new Product("Apple", "Groceries", 1.29),
                new Product("Macbook pro", "Electronics", 1200.0),
                new Product("Bread", "Groceries", 2.49),
                new Product("T-Shirt", "Clothing", 19.99),
                new Product("Jacket", "Clothing", 89.99),
                new Product("Jeans", "Clothing", 49.99)
        );

        // key: category
        // value: product list sorted my price descending
        Map<String, List<Product>> groupedProducts = productList.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory, Collectors.collectingAndThen(
                                Collectors.toList(), list -> list.stream()
                                        .sorted((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()))
                                        .toList()
                        )
                ));

        groupedProducts.forEach((category, products) -> {
            System.out.println("Category: " + category);
            products.forEach(System.out::println);

        });

        //Category: Groceries
        //Product{productName='Bread', category='Groceries', price=2.49}
        //Product{productName='Apple', category='Groceries', price=1.29}
        //Product{productName='Banana', category='Groceries', price=0.99}
        //Category: Clothing
        //Product{productName='Jacket', category='Clothing', price=89.99}
        //Product{productName='Jeans', category='Clothing', price=49.99}
        //Product{productName='T-Shirt', category='Clothing', price=19.99}
        //Category: Electronics
        //Product{productName='Samsung TV', category='Electronics', price=1499.0}
        //Product{productName='Macbook pro', category='Electronics', price=1200.0}
        //Product{productName='iPhone', category='Electronics', price=999.0}

    }


}
