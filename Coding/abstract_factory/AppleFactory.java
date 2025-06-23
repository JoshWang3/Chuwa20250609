package abstract_factory;

public class AppleFactory implements DeviceFactory {

    @Override
    public Phone createPhone() {
        return new IPhone();
    }

    @Override
    public Tablet createTablet() {
        return new IPad();
    }
}
