package factory;

public class BookFactory {
    public Product createProduct() {
        return new Book();
    }
}
