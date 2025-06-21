import java.util.*;
import java.util.stream.*;

// 1.5. group products by category and sort each group by price descending    study supplements - pen - $10.0
public class SortedProductByCategory {
    public static void main(String[] args) {
        List<Products> products = Arrays.asList(
            new Products("pen", 10.0, "study supplements"),
            new Products("milk", 4.0, "groceries"),
            new Products("keyboard", 45.0, "electronics"),
            new Products("notebook", 15.0, "study supplements"),
            new Products("mouse", 25.0, "electronics"),
            new Products("apple", 3.0, "groceries")
        );

        List<Products> groupedAndSorted = products.stream()
                .sorted((a, b) -> a.getCategory().compareTo( b.getCategory()))
                .sorted((a, b) -> b.getPrice().compareTo(a.getPrice()))
                .collect(Collectors.toList());

        for (Products p : groupedAndSorted) {
            System.out.println(p.getCategory());
            System.out.println(p.getProduct());
            System.out.println(p.getPrice());
        }
    }
}

class Products {
    private String product;
    private Double price;
    private String category;
    public Products(String product, Double price, String category) {
        this.product = product;
        this.price = price;
        this.category = category;
    }
    public String getProduct() {
        return product;
    }
    public Double getPrice() {
        return price;
    }
    public String getCategory() {
        return category;
    }
}