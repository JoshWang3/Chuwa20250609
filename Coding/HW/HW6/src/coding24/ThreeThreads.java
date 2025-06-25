package coding24;

public class ThreeThreads {

    private static final Object lock = new Object();
    private static int turn = 1;

    public static void main(String[] args) {
        Thread t1 = new Thread(new Print(1, 1,10));
        Thread t2 = new Thread(new Print(2, 11,20));
        Thread t3 = new Thread(new Print(3, 21,30));

        t1.start();
        t2.start();
        t3.start();
    }

    static class Print implements Runnable {
        private final int myTurn;
        private final int start;
        private final int end;

        public Print(int myTurn, int start, int end) {
            this.myTurn = myTurn;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            synchronized (lock) {
                while (turn != myTurn) {
                    try {lock.wait();} catch (InterruptedException e) {}
                }
                for (int i = start; i <= end; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
                turn++;
                lock.notify();
            }
        }
    }
}
