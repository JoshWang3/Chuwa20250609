package hw4.abstract_factory;

public class Test {
    public static void main(String[] args) {
        ElectronicStore appleStore = new ElectronicStore();
        AppleFactory appleFactory = new AppleFactory();
        appleStore.setFactory(appleFactory);

        Phone iPhone = appleStore.tryPhone();
        Tablet iPad = appleStore.tryTablet();
        System.out.println(iPhone.getBrand());

        ElectronicStore samsungStore = new ElectronicStore();
        SamsungFactory samsungFactory  = new SamsungFactory();
        samsungStore.setFactory(samsungFactory);

        Phone galaxy = samsungStore.tryPhone();
        Tablet galaxyTab = samsungStore.tryTablet();
        System.out.println(galaxyTab.getBrand());
    }
}
