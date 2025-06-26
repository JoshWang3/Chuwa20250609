package hw4.singleton;
// 1. basic lazy loading
// Not thread-safe: If 2 threads call getInstance() at the same time when instance is still null
// both threads may create two different instances
//public class hw4.singleton.LazyLoadSingleton {
//    // no instance when the class loaded
//    private static hw4.singleton.LazyLoadSingleton instance;
//
//    private hw4.singleton.LazyLoadSingleton() {}
//
//    public static hw4.singleton.LazyLoadSingleton getInstance() {
//        // create only when first requested
//        if (instance == null) {
//            instance = new hw4.singleton.LazyLoadSingleton();
//        }
//        return instance;
//    }
//}

// 2. synchronized lazy loading
// thread-safe, but synchronization cost for every call, even after instance already created
//public class hw4.singleton.LazyLoadSingleton {
//    private static hw4.singleton.LazyLoadSingleton instance;
//
//    private hw4.singleton.LazyLoadSingleton() {}
//    // synchronized: thread-safe
//    public static synchronized hw4.singleton.LazyLoadSingleton getInstance() {
//        if (instance == null) {
//            instance = new hw4.singleton.LazyLoadSingleton();
//        }
//        return instance;
//    }
//}

// 3. double-checked locking lazy loading
// thread-safe and better performance
//public class hw4.singleton.LazyLoadSingleton {
//    // volatile: make sure all threads see the most recent value immediately and object is fully constructed before any thread gets reference
//    private static volatile hw4.singleton.LazyLoadSingleton instance;
//
//    private hw4.singleton.LazyLoadSingleton() {}
//
//    public static hw4.singleton.LazyLoadSingleton getInstance() {
//        // first check: no need to get the lock(which is expensive). If there is an instance existed, just return, very fast
//        if (instance == null) {
//            synchronized (hw4.singleton.LazyLoadSingleton.class) {
//                // second check to avoid multiple creations
//                if (instance == null) {
//                    instance = new hw4.singleton.LazyLoadSingleton();
//                }
//            }
//        }
//        return instance;
//    }
//}

// 4. static inner class lazy loading
// best practice: clean and thread safe
// because the instance is created by the static inner class, which isn't loaded when JVM loads the outer class,
// it only loads and initializes the inner class when its attributes or methods are accessed for the first time.
// and the static instance make sure the instance will be instantiated only once
public class LazyLoadSingleton {
    private LazyLoadSingleton() {}

    private static class SingletonHolder {
        private static final LazyLoadSingleton instance = new LazyLoadSingleton();
    }

    public static LazyLoadSingleton getInstance() {
        return SingletonHolder.instance;
    }
}

