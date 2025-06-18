class LazySingleton {
    /*
  This Singleton uses "lazy loading".
  It creates the instance only when needed.

  We use `synchronized` and `if (instance == null)` to make it thread-safe.
  The `volatile` keyword helps avoid memory problems in multithreading.

  So, only one object is created, even when many threads call getInstance().
*/
    private static volatile LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
