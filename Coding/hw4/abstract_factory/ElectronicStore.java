package hw4.abstract_factory;

public class ElectronicStore {
    private DeviceFactory factory;

    public void setFactory(DeviceFactory factory) {
        this.factory = factory;
    }

    public Phone tryPhone() {
        Phone phone = factory.createPhone();
        phone.call();
        phone.takePhoto();
        return phone;
    }

    public Tablet tryTablet() {
        Tablet tablet = factory.createTablet();
        tablet.watchVideo();
        tablet.playGame();
        return tablet;
    }
}
