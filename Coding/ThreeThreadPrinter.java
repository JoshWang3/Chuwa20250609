// Three threads print different number ranges concurrently
public class ThreeThreadPrinter {
    public static void main(String[] args) {
        Runnable r1 = () -> printRange(1, 10);
        Runnable r2 = () -> printRange(11, 20);
        Runnable r3 = () -> printRange(21, 22);
        
        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r3).start();
    }
    
    private static void printRange(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}