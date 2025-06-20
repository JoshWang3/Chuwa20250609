package hw4;

class lazySingleton {
    private lazySingleton() {} // Prevents from creating singleton using constructor

    private static class SingletonInner {
        public static final lazySingleton instance = new lazySingleton();
    }

    public static lazySingleton getInstance() {
        return SingletonInner.instance;
    }

    public void dummy() {}
}

public class lazySingletonTester
{
    public static void main(String[] args)
    {
        int numThread = 5; // Number of threads
        for (int n = 0; n < numThread; n++) {
            Multithreading object = new Multithreading(lazySingleton::getInstance);
            object.start();
        }
    }
}

// The above code shows that the instance object is not created until getInstance is called.
// After getInstance is getting called, static final ensures that instance is allocated only once in metaspace and immutable.
// In addition, getInstance all reference to the same instance, so it's guaranteed that it's thread-safe