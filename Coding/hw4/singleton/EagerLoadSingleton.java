package hw4.singleton;

public class EagerLoadSingleton {
    // private: no other class can directly access or modify the instance
    // static instance: this single instance will be created when JVM loads the class
    // final: the instance cannot be reassigned
    private static final EagerLoadSingleton instance = new EagerLoadSingleton();
    // private constructor: prevent outside code from creating new instances
    private EagerLoadSingleton() {}
    // public: provide a global access
    // static method: can call getInstance() without an instance
    public static EagerLoadSingleton getInstance() {
        return instance;
    }
}

// JVM loads class hw4.singleton.EagerLoadSingleton.
// Sees private static final hw4.singleton.EagerLoadSingleton instance = new hw4.singleton.EagerLoadSingleton();
// Creates one instance and stores it.
// Later, whenever getInstance() is called — returns that object.
