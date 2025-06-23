package factory_method;
// concrete product
public class Samsung implements Phone {
    @Override
    public String getBrand() {
        return "Samsung";
    }

    @Override
    public void call() {
        System.out.println("Samsung is calling");
    }

    @Override
    public void takePhoto() {
        System.out.println("Using Samsung to take a photo");
    }
}
