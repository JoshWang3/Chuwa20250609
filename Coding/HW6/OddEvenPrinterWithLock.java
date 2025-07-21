import java.util.concurrent.locks.*;

public class OddEvenPrinterWithLock {
    private final Lock lock = new ReentrantLock();
    private final Condition oddCondition = lock.newCondition();
    private final Condition evenCondition = lock.newCondition();
    private boolean oddTurn = true;

    public void printOdd(){
        lock.lock();

        try {
            for(int i = 0; i <= 9; i += 2){
                while(!oddTurn){
                    oddCondition.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                oddTurn = false;
                evenCondition.signal();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void printEven(){
        lock.lock();

        try {

            for(int i = 2; i <= 10; i+= 2){
                while(oddTurn){
                    evenCondition.await();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
                oddTurn = true;
                oddCondition.signal();
        }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        OddEvenPrinterWithLock printer = new OddEvenPrinterWithLock();

        Thread oddThread = new Thread(printer::printOdd, "Thread-0");
        Thread evenThread = new Thread(printer::printEven, "Thread-1");

        oddThread.start();
        evenThread.start();

        
    }
}
