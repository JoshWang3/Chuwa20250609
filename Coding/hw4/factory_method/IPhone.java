package hw4.factory_method;
// concrete product
public class IPhone implements Phone {
    @Override
    public String getBrand() {
        return "Apple";
    }

    @Override
    public void call() {
        System.out.println("iPhone is calling");
    }

    @Override
    public void takePhoto() {
        System.out.println("Using iPhone to take a photo");
    }
}
