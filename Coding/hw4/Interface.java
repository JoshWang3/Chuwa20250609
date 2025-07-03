public interface Interface {
    void method();
    default void defaultMethod("default");
    static void staticMethod("static");
}

public class Impl implements Interface {
    @java.lang.Override
    public void method() {
        System.out.println("normal");
    }

    //
    @java.lang.Override
    public void defaultMethod() {
        System.out.println("override default");
    }
}

public class Main {
    public static void main(String[] args) {
        Interface obj = new Impl();
        obj.method(); // normal
        obj.defaultMethod(); // override default or default if no override
        Interface.staticMethod(); // static -- method belongs to Interface itself
    }
}