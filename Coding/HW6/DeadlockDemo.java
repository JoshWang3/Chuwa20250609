public class DeadlockDemo {
    static final Resource r1 = new Resource();
    static final Resource r2 = new Resource();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> r1.method1(), "Thread - 1");
        Thread t2 = new Thread(() -> r2.method2(), "Thread - 2");

        t1.start();
        t2.start();
    }
}
