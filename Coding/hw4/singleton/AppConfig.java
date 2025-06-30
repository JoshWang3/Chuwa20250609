package hw4.singleton;

/**
 * Imagine there is a ConfigurationManager class that loads configuration from a file or database and is shared across the application.
 * I will implement both:
 * Eager Loading Singleton for EagerConfigManager
 * Lazy Loading Singleton for LazyConfigManager (with thread-safe double-checked locking)
 * A POJO (AppConfig) to represent configuration.
 * A demo use case.
 */

public class AppConfig {

    private String appName;
    private String dbUrl;

    public AppConfig(String appName, String dbUrl) {
        this.appName = appName;
        this.dbUrl = dbUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    @Override
    public String toString() {
        return "AppConfig{" + "appName=" + appName + ", dbUrl=" + dbUrl + '}';
    }

}
