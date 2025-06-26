public class Deadlock {
    private static final Object LOCKA = new Object();
    private static final Object LOCKB = new Object();
    public static void main(String[] args) {
        Thread t1= new Thread(()->{
            synchronized (LOCKA) {
                System.out.println("t1:lockA");
                synchronized (LOCKB) {
                    System.out.println("t1:lockB");
                }
            }
        });
        Thread t2= new Thread(()->{
            synchronized (LOCKB) {
                System.out.println("t2:lockB");
                synchronized (LOCKA) {
                    System.out.println("t2:lockA");
                }
            }
        });
        t1.start();
        t2.start();
    }
}
