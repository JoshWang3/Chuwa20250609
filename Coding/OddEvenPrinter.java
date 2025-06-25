// Two threads alternately print odd and even numbers
public class OddEvenPrinter {
    private final Object lock = new Object();
    private int count = 1;
    
    public static void main(String[] args) {
        OddEvenPrinter p = new OddEvenPrinter();
        new Thread(p::printOdd).start();
        new Thread(p::printEven).start();
    }
    
    private void printOdd() {
        while (count < 10) {
            synchronized (lock) {
                if (count % 2 == 1) {
                    System.out.println(count++);
                    lock.notify();
                } else {
                    try { lock.wait(); } catch (InterruptedException e) {}
                }
            }
        }
    }
    
    private void printEven() {
        while (count <= 10) {
            synchronized (lock) {
                if (count % 2 == 0) {
                    System.out.println(count++);
                    lock.notify();
                } else {
                    try { lock.wait(); } catch (InterruptedException e) {}
                }
            }
        }
    }
}