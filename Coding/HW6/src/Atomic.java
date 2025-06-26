import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        Runnable r = ()->{
            for(int i=0;i<1000;i++){
                counter.incrementAndGet();
            }
        };
        Thread[] threads = new Thread[10];
        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(r);
            threads[i].start();
        }
        for(Thread thread : threads){
            thread.join();
        }
        System.out.println(counter);
    }
}
