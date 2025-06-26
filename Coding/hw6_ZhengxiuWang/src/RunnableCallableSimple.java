import java.util.concurrent.*;

public class RunnableCallableSimple {
    public static void main(String[] args) throws Exception {
        // Runnable: no return value
        Runnable r = () -> {
            System.out.println("Runnable is running");
        };
        new Thread(r).start();

        // Callable: has return value
        Callable<String> c = () -> {
            return "Callable result";
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> result = executor.submit(c);
        System.out.println(result.get());  // blocks until done

        executor.shutdown();
    }
}
