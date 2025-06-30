package hw4.singleton;

/**
 * Eager Loading Singleton: EagerConfigManager
 */
public class EagerConfigManager {

    private static final EagerConfigManager INSTANCE = new EagerConfigManager();
    private final AppConfig appConfig;

    private EagerConfigManager() {
        System.out.println("Loading AppConfig eagerly...");
        this.appConfig = new AppConfig("todoAppEager", "dbc:mysql://localhost:3306/todoAppEager" );
    }

    public static EagerConfigManager getInstance() {
        return INSTANCE;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }

}
