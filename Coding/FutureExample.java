import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) {
        // create a ThreadPool
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // create a Callable task
        Callable<String> task = () -> {
            // mock long time task
            Thread.sleep(5000);
            System.out.println("Printing something...");
            return "Task completed";
        };

        // submit task and get Future object
        Future<String> resFuture = executorService.submit(task);

        System.out.println("Task submitted, waiting for result...");

        try {
            // calling get() will block main thread till task completion
            String result = resFuture.get(2, TimeUnit.SECONDS);
            System.out.println("Result from task: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }

        // close ThreadPool
        executorService.shutdown();
    }
}
