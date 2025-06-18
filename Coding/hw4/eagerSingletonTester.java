package hw4;

class eagerSingleton {
    private eagerSingleton () {}
    private static final eagerSingletonTester instance = new eagerSingletonTester();
    public static eagerSingletonTester getInstance() {
        return instance;
    }

    public void dummy() {}
}

public class eagerSingletonTester {
    public static void main(String[] args)
    {
        int numThread = 5; // Number of threads
        for (int n = 0; n < numThread; n++) {
            Multithreading object = new Multithreading(eagerSingleton::getInstance);
            object.start();
        }
    }
}

// The above code shows that the instance object is created when the class is loaded by JVM
// The instance is getting loaded by JVM only once, static final ensures that instance is immutable
// In addition, getInstance all reference to the same instance, so it's guaranteed that it's thread-safe
