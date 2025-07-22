package ThreadSafeSingleton;

public class BurgerMain {
    public static void main(String[] args) {
        Runnable task = () -> {
            BurgerMaker maker = BurgerMaker.getInstance();
            Burger burger = new Burger("Beef", true);
            maker.makeBurger(burger);
        };

        Thread t1 = new Thread(task, "Thread A");
        Thread t2 = new Thread(task, "Thread B");
        Thread t3 = new Thread(task, "Thread C");

        t1.start();
        t2.start();
        t3.start();
    }
}
