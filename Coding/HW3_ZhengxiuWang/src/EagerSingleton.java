class EagerSingleton {
    /*
  This Singleton uses "eager loading".
  It creates the instance when the class is loaded.

  Java class loading is thread-safe,
  so we don’t need `synchronized`.

  This is very simple and always safe,
  but the object is created even if we never use it.
*/
    private static final EagerSingleton instance = new EagerSingleton(); // 类加载时就实例化

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return instance;
    }
}
