class EagerSingleton {
    private String language="English";
    private static final EagerSingleton instance=new EagerSingleton();
    private EagerSingleton() {}
    public static EagerSingleton getInstance() {
        return instance;
    }

    public String getLanguage() {
        return language;
    }
}

class LazySingleton {
    private String language="English";
    private static class InstanceHolder {
        private static final LazySingleton instance=new LazySingleton();
    }
    private LazySingleton() {}
    public static LazySingleton getInstance() {
        return InstanceHolder.instance;
    }

    public String getLanguage() {
        return language;
    }

}
public class TestSingleton {
    public static void main(String[] args) {
        EagerSingleton singleton1=EagerSingleton.getInstance();
        LazySingleton singleton2=LazySingleton.getInstance();
        System.out.println(singleton1.getLanguage());
        System.out.println(singleton2.getLanguage());
    }
}
