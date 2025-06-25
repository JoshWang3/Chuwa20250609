package ThreadCommunication;

public class WaitNotify extends Thread {

    private static final Object GRILL = new Object(); // shared monitor grill
    private static int pattyNumber = 1; // shared counter

    public static void main(String[] args) {
        PattyFlipper flipper = new PattyFlipper();
        new Thread(flipper, "SpongeBob").start();
        new Thread(flipper, "Squidward").start();
    }

    static class PattyFlipper implements Runnable {
        @Override
        public void run() {
            synchronized (GRILL) {
                while (pattyNumber <= 10) {
                    System.out.println(Thread.currentThread().getName() + " flips patty #" + pattyNumber++);
                    GRILL.notifyAll(); // wake the other thread
                    try {
                        if (pattyNumber <= 10) {
                            GRILL.wait(); // wait for the other thread to take its turn
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}