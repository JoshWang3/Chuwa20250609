
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolThread {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for(int i = 0; i < 5; i++){
            executor.execute(() ->{
                System.out.println("Thread Pool Thread is running: " + Thread.currentThread().getName());
            });
        }
        executor.shutdown();

    }
}
