//2.
//Singleton：一个类只能创建一个instance
//线程安全（Thread-safe）：在多线程环境下，多个线程同时访问这个类时，仍然只能创建出一个实例，不会出错或重复创建。
public class ThreadSafeSingleton {
    //我们得准备一个变量来保存这个唯一的实例
    private static ThreadSafeSingleton instance;
    //私有构造函数：只能有一个对象，不允许别人用 new Singleton() ，把构造函数设为私有
    private ThreadSafeSingleton() {
        System.out.println("creating singleton");
    }
    //getInstance() is for singleton since we define private constructor
    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}

