package ThreadCreation;

import java.util.concurrent.*;

class BurgerCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1000); // simulate work
        return "Burger made by Callable - " + Thread.currentThread().getName();
    }
}

public class CallableInterface {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new BurgerCallable());

        System.out.println("Waiting for burger...");

        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}

// output:
// Waiting for burger...
// Burger made by Callable - pool-1-thread-1