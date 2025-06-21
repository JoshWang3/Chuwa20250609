package Coding.hw8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Product {
    String name;
    String category;
    int price;

    public Product(String name, String category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {return name;}
    public String getCategory() {return category;}
    public int getPrice() {return price;}
}
public class GroupProducts {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(new  Product("a", "shoe", 100),
                new Product("b", "shoe", 200),
                new Product("c", "shirt", 300),
                new Product("d", "jeans", 150),
                new Product("e", "shirt", 250));

        Map<String, List<Product>> map = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingInt(Product::getPrice).reversed())
                                        .collect(Collectors.toList()))));

        map.forEach((k,v)->{
            System.out.println(k + ": ");
            v.forEach(p -> System.out.println(p.getName() + " " + p.getPrice()));
        });
    }
}
