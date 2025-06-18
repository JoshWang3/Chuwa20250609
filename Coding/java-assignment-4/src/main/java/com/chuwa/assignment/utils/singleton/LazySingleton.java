package com.chuwa.assignment.utils.singleton;
// 2. LazySingleton.java (lazy loading)
//    • Lazy loading: we defer object creation until someone actually
//      asks for it.
//    • Thread‑safety: the entire getInstance() method is marked
//      synchronized, so only one thread at a time can enter the null
//      check / creation logic.  This removes the classic double‑create
//      race condition, at the cost of a tiny synchronization overhead.
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("Call lazySingleton...");
    }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
    public void log(String msg) {
        System.out.println("[LazySingleton]: " + msg);
    }
}
