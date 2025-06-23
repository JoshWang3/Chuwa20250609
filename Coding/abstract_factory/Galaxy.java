package abstract_factory;

public class Galaxy implements Phone {
    @Override
    public String getBrand() {
        return "Samsung";
    }

    @Override
    public void call() {
        System.out.println("Galaxy is calling");
    }

    @Override
    public void takePhoto() {
        System.out.println("Using Galaxy to take a photo");
    }
}
