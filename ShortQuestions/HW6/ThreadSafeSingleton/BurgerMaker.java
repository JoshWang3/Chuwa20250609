package ThreadSafeSingleton;

public class BurgerMaker {

    // 1. volatile ensures visibility and prevents instruction reordering
    private static volatile BurgerMaker instance;


    // 2. private constructor to prevent instantiation from outside
    private BurgerMaker(){
        System.out.println("BurgerMaker instance created.");
    }

    // 3. static synchronized getInstance method
    public static BurgerMaker getInstance(){
        // 4. make sure thread safe
        if (instance == null) { // first check (no locking)
            synchronized (BurgerMaker.class) { // lock class obj
                if (instance == null) { // second check (with lock)
                    instance = new BurgerMaker(); // create instance
                }
            }
        }
        return instance;
    }

    public void makeBurger(Burger burger) {
        System.out.println(Thread.currentThread().getName() + " made: " + burger);
    }
}


