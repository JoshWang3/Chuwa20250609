package Coding.hw6;

public class Singleton {
    private Singleton() {

    }
    // static inner class makes sure the object is created once alone with
    // the calls of the method and is thread safe
    private static class Inner {
        private static final Singleton INSTANCE = new Singleton();
    }

    private static Singleton getInstance() {
        return Inner.INSTANCE;
    }
}
