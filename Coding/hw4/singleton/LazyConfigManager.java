package hw4.singleton;

/**
 * Lazy Loading Singleton: LazyConfigManager (Thread safe)
 */
public class LazyConfigManager {
    private final AppConfig appConfig;

    private LazyConfigManager() {
        System.out.println("Loading AppConfig lazily...");
        this.appConfig = new AppConfig("todoAppLazily", "jdbc:mysql://localhost:3306/todoAppLazily" );
    }

    // Static inner class to hold the Singleton instance
    private static class LazyConfigManagerHolder {
        private static final LazyConfigManager INSTANCE = new LazyConfigManager();
    }

    public static LazyConfigManager getInstance() {
        return LazyConfigManagerHolder.INSTANCE;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }
}
