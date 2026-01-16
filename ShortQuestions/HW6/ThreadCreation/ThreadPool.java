package ThreadCreation;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        Runnable task = () -> {
            System.out.println("Burger made in thread pool - " + Thread.currentThread().getName());
        };

        pool.submit(task);
        pool.submit(task);
        pool.submit(task);

        pool.shutdown();
    }
}

/*
output:
Burger made in thread pool - pool-1-thread-1
Burger made in thread pool - pool-1-thread-3
Burger made in thread pool - pool-1-thread-2

 */