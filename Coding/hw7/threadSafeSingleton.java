package hw7;

public class threadSafeSingleton {

    private threadSafeSingleton() {}

    private static class innerClass {
        private static final threadSafeSingleton instance = new threadSafeSingleton();
    }
    public static threadSafeSingleton getInstance() {
        return innerClass.instance;
    }
}

// Second way
/*

public class Singleton {

    private static Singleton instance;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
*/

// Third way
/*
public class Singleton {

    private static Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
 */