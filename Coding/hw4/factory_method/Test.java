package hw4.factory_method;

public class Test {
    public static void main(String[] args) {
        PhoneStore appleStore = new PhoneStore();
        IPhoneFactory iPhoneFactory = new IPhoneFactory();
        appleStore.setFactory(iPhoneFactory);

        Phone phone = appleStore.tryPhone();
        System.out.println(phone.getBrand());
    }
}
