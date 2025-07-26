import java.util.*;
import java.util.stream.Collectors;


public class Question1_5 {
    public static void main(String[] args) {
        List<Products> products = Arrays.asList(
                new Products("iPhone 15", "Electronics", 999.99),
                new Products("Galaxy S24", "Electronics", 899.99),
                new Products("MacBook Pro", "Electronics", 1999.00),
                new Products("Banana", "Grocery", 0.99),
                new Products("Milk", "Grocery", 2.49),
                new Products("Eggs", "Grocery", 3.99),
                new Products("Desk", "Furniture", 120.0),
                new Products("Chair", "Furniture", 85.5),
                new Products("Bookshelf", "Furniture", 150.75)
        );

        System.out.println(toCategory(products));

    }

    public static Map<String, List<Products>> toCategory(List<Products> products) {


        return products.stream()
                .collect(Collectors.groupingBy(Products::getCategory,
                        Collectors.collectingAndThen( //downstream, stream for categories
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted((a,b) -> Double.compare(b.getPrice(), a.getPrice()))
                                        .toList()
                        )));

    }
}

class Products{
    private String name;
    private String category;
    private double price;

    public Products(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "(" + name + ", " + category + ", " + price + ")";
    }
}
