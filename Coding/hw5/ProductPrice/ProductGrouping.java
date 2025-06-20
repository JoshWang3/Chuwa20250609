package hw5.ProductPrice;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductGrouping {
    public static void main(String[] args) {
        List<product> products = List.of(
                new product("iPhone", "Electronics", 999.99),
                new product("MacBook", "Electronics", 1999.99),
                new product("Headphones", "Electronics", 199.99),
                new product("Java Book", "Books", 59.99),
                new product("Comic Book", "Books", 9.99),
                new product("T-shirt", "Clothing", 19.99),
                new product("Jacket", "Clothing", 89.99)
        );

        Map<String, List<product>> m = products.stream().collect(Collectors.groupingBy(product::getCategory));
        List<product> sorted = m.values().stream().flatMap(List::stream).sorted(Comparator.comparing(product::getPrice).reversed()).collect(Collectors.toList());
        sorted.forEach(System.out::println);
    }
}
