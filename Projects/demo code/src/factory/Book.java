package factory;

public class Book implements Product {
    @Override
    public void use() {
        System.out.println("Reading a book...");
    }
}
