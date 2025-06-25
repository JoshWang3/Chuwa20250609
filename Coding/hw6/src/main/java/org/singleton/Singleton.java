package org.singleton;

// Bill Pugh Singleton
public class Singleton {

    private Singleton() {
        // private constructor
    }

    // Uses the Java classloader mechanism to ensure that the instance is
    // created only when needed, and is thread-safe without synchronization.
    // JVM ensures that a class’s static initialization happens once and
    // only once, even with multiple threads.
    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}
