package hw4.singleton;

public class SingletonTest {
    public static void main(String[] args) {
        System.out.println("== Eager Config ==");
        AppConfig eagerConfig = EagerConfigManager.getInstance().getAppConfig();;
        System.out.println(eagerConfig);

        System.out.println("== Lazy Config ==");
        AppConfig lazyConfig = LazyConfigManager.getInstance().getAppConfig();
        System.out.println(lazyConfig);
    }
}
