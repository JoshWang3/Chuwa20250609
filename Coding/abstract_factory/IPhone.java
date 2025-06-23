package abstract_factory;

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
