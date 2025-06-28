package hw6;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadCreation {
    public static void main(String[] args) throws Exception { //
        Thread t1 = new MyThread();
        t1.start();

        Thread t2 = new Thread(new RunnableThread());  // new Thread() 里面可以放的是任何实现了 Runnable 接口的对象
        // lambda for Runnable(functional interface)
        Thread t3 = new Thread(() -> {
            System.out.println("Running from Lambda");
        });
        t2.start();
        t3.start();
        // Thread only accepts Runnable, so need FutureTask to wrap the Callable to be run by a Thread, and return a result via .get()
        // FutureTask implements both Runnable and Future, and it can wrap Callable and Runnable
        FutureTask task = new FutureTask(new CallableThread());
        Thread t4 = new Thread(task);
        t4.start();
        System.out.println(task.get()); // 可能抛 InterruptedException 或 ExecutionException, 在main() 加 throws

    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("extends Thread");
    }
}

class RunnableThread implements Runnable {
    @Override
    public void run() {
        System.out.println("implement Runnable");
    }
}

class CallableThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "implement Callable";
    }
}
