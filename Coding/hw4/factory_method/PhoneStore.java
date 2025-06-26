package hw4.factory_method;
// client
public class PhoneStore {
    private PhoneFactory factory;

    public void setFactory(PhoneFactory factory) {
        this.factory = factory;
    }

    public Phone tryPhone() {
        Phone phone = factory.createPhone();
        phone.call();
        phone.takePhoto();
        return phone;
    }
}
