public class PrintNumber {

    private static final Object lock = new Object();
    private static int currentThread = 1;
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock){
                while(currentThread != 1){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); 
                        e.printStackTrace();
                    }
                }

                for(int i = 1; i <= 10; i++){
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
                currentThread = 2;
                lock.notifyAll();
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized(lock){
                while(currentThread != 2){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }

                for(int i = 11; i <= 20; i++){
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
                currentThread = 3;
                lock.notifyAll();
            }
        });

        Thread t3 = new Thread(() ->{
            synchronized (lock) {
                while(currentThread != 3){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
                for(int i = 21; i <= 30; i++){
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
                currentThread = 1; // Reset to first thread
                lock.notifyAll();
            }
        });
    

        t1.start();
        t2.start();
        t3.start();
    }
}
