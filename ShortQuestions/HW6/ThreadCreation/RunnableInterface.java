package ThreadCreation;

class BurgerRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Making burger using Runnable - " + Thread.currentThread().getName());
    }
}

public class RunnableInterface {
    public static void main(String[] args) {
        Thread t = new Thread(new BurgerRunnable());
        t.start();
    }
}

// output:
// Making burger using Runnable - Thread-0
