import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CreateNewThread {
    static void directApproach() throws InterruptedException {
        Thread thread = new Thread(()->{
            System.out.println("Thread started: DirectApproach");
        });
        thread.start();
        thread.join();
    }
    static void threadPoolApproach() throws Exception {
        ExecutorService service= Executors.newFixedThreadPool(8);
        Future<String> future = service.submit(()->{
            System.out.println("Thread started: ThreadPoolApproach");
            return "Hello World";
        });
        String result=future.get();
        System.out.println(result);
    }
    public static void main(String[] args) {
        try{
            directApproach();
            threadPoolApproach();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
