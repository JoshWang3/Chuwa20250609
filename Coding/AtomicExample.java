import java.util.concurrent.atomic.AtomicInteger;

// Thread-safe counter using AtomicInteger
public class AtomicExample {
    private AtomicInteger counter = new AtomicInteger();
    
    public void increment() {
        counter.incrementAndGet();
    }
    
    public int get() {
        return counter.get();
    }
    
    public static void main(String[] args) {
        AtomicExample example = new AtomicExample();
        example.increment();
        System.out.println(example.get());
    }
}