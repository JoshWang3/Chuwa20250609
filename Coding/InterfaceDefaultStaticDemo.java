package Coding;

interface MyInterface {

    // abstract method（必须实现）
    void abstractMethod();

    // default method（可以直接使用或重写）
    default void defaultMethod() {
        System.out.println("Default method in interface");
    }

    // static method（只能通过接口调用）
    static void staticMethod() {
        System.out.println("Static method in interface");
    }
}

// 实现类 A
class MyClassA implements MyInterface {
    @Override
    public void abstractMethod() {
        System.out.println("MyClassA implements abstractMethod");
    }
}

// 实现类 B：重写 default 方法
class MyClassB implements MyInterface {
    @Override
    public void abstractMethod() {
        System.out.println("MyClassB implements abstractMethod");
    }

    @Override
    public void defaultMethod() {
        System.out.println("MyClassB overrides defaultMethod");
    }
}

public class InterfaceDefaultStaticDemo {
    public static void main(String[] args) {
        MyClassA a = new MyClassA();
        a.abstractMethod();       // 输出：MyClassA implements abstractMethod
        a.defaultMethod();        // 输出：Default method in interface

        MyClassB b = new MyClassB();
        b.abstractMethod();       // 输出：MyClassB implements abstractMethod
        b.defaultMethod();        // 输出：MyClassB overrides defaultMethod

        // 调用 static 方法（只能通过接口名）
        MyInterface.staticMethod(); // 输出：Static method in interface

        // b.staticMethod(); //  错误：不能通过实例调用接口静态方法
    }
}

