public class ThreadSafeSingleton_DCL {
    // volatile ensures visibility across threads
    private static volatile ThreadSafeSingleton_DCL instance = null;

    // private constructor
    private ThreadSafeSingleton_DCL() {}

    public static ThreadSafeSingleton_DCL getInstance() {
        if (instance == null) {  // first check
            synchronized (ThreadSafeSingleton_DCL.class) {
                if (instance == null) {  // second check
                    instance = new ThreadSafeSingleton_DCL();
                }
            }
        }
        return instance;
    }
}
