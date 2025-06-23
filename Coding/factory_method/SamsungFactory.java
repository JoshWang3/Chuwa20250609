package factory_method;
// concrete creator
public class SamsungFactory implements PhoneFactory {
    @Override
    public Phone createPhone() {
        return new Samsung();
    }
}
