package hw5;

public class Product {
    private String name;
    private String category;
    private Double price;

    public Product(String name, String category, Double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    // override toString() to print the appropriate format of the product, rather than hw5.Product@63961c42 (hash code)
    @Override
    public String toString() {
        return  name + ":  $" + price;
    }
}
