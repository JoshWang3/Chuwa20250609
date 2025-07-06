package Coding.hw6;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 1. extending the Thread class
class NewThread extends Thread {
    @Override
    public void run() {
        // custom implementation
        System.out.println("New thread running");
    }
    // NewThread thread = new NewThread();
    // thread.start();
}

// 2. implementing Runnable interface
class NewRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("New runnable thread");
    }
    // Runnable task = new NewRunnable();
    // Thread thread = new Thread(task);
    // thread.start();
}

/*
A Runnable interface is more flexible than a Thread class as we cannot further
subclass after extending the Thread class plus we can access ExecutorService from
the Runnable interface.
 */

// 3. use Callable interface
class NewCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "New Callable thread";
    }

    // FutureTask<String> f = new FutureTask<>(new NewCallable());
    // Thread thread = new Thread(f);
    // thread.start();
    // String res = f.get();
}

/*
Difference between Runnable and Callable are:
1. Runnable declares a void run() method and cannot return result
where Callable declares a V call() throws Exception method and
returns a type V result, retrievable through Future<V>

2. Runnable can be passed directly to a Thread class or an ExecutorService but with null return
Callable cannot be passed to a Thread but to an ExecutorService with a Future<V> return
  */

/*
When t.start() is called, JVM will allocate and schedule a new thread, and it will
eventually invoke the run() method in that thread. t.start() can only be called once.

Calling t.run() will not create a new thread, and it executes synchronously on the calling thread
 */

// 4. use thread pool (ExecutorService)
class NewThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // executor.submit();
        // executor.shutdown();
    }
}