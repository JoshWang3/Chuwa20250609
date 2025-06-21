import java.util.Optional;

public class Product {
    private String name;
    private String category;
    private Double price;
    private Optional<String> description = Optional.empty();

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = Optional.ofNullable(description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "name: " + name + ", category: " + category + ", price: " + price;
    }
}
