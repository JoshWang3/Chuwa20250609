package com.chuwa.assignment.utils.singleton;

public class Singleton {
    public static void main(String[] args) {
        System.out.println("Program started\n");

        // -- Eager: constructor already executed at class‑load time.
        EagerSingleton.getInstance().log("ready");

        // --- Lazy: constructor runs now, on first request.
        LazySingleton.getInstance().log("initialized safely on demand");
    }
}