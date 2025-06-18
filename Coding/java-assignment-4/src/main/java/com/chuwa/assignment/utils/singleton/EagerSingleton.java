package com.chuwa.assignment.utils.singleton;
// 1. EagerSingleton.java
//    • Eager loading: as soon as the JVM loads this class, INSTANCE is
//      created – even if the program never uses it.
//    • Thread‑safety: guaranteed by the JVM.  Class initialization is
//      atomic and performed exactly once per ClassLoader.
//      Because the JVM’s class‑initialization phase is synchronized – it
//      runs once per class loader.  By the time any thread can execute
//      getInstance(), INSTANCE is already fully constructed.
public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    // private constructor to avoid client applications using the constructor
    private EagerSingleton() {
        System.out.println("Call eagerSingleton...");
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public void log(String msg){
        System.out.println("[EagerSingleton]: " + msg);
    }
}
