package abstract_factory;

public class SamsungFactory implements DeviceFactory {
    @Override
    public Phone createPhone() {
        return new Galaxy();
    }

    @Override
    public Tablet createTablet() {
        return new GalaxyTab();
    }
}
