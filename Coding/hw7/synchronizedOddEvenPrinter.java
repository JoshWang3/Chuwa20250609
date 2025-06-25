package hw7;

public class synchronizedOddEvenPrinter {
    private final int MAX = 10;
    private int count = 1;
    private boolean isOddTurn = true;

    public synchronized void printOdd() {
        while (count <= MAX) {
            while (!isOddTurn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (count <= MAX) {
                System.out.println(Thread.currentThread().getName() + ": " + count);
                count++;
                isOddTurn = false;
                notifyAll();
            }
        }
    }

    public synchronized void printEven() {
        while (count <= MAX) {
            while (isOddTurn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (count <= MAX) {
                System.out.println(Thread.currentThread().getName() + ": " + count);
                count++;
                isOddTurn = true;
                notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        synchronizedOddEvenPrinter printer = new synchronizedOddEvenPrinter();

        Thread t1 = new Thread(printer::printOdd);
        Thread t2 = new Thread(printer::printEven);

        t1.start();
        t2.start();
    }
}
