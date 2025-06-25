package ThreadCreation;

class BurgerThread extends Thread{
    @Override
    public void run(){
        System.out.println(("Making bruger usin Thread class - " + Thread.currentThread().getName()));
    }
}

public class ExtendingThread {
    public static void main(String[] args) {
        Thread t = new BurgerThread();
        t.start();
    }
}

// output:
// Making bruger usin Thread class - Thread-0