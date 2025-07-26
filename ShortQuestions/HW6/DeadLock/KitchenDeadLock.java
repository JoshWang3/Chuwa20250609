package DeadLock;

public class KitchenDeadLock {

    private static final Object BUN = new Object();
    private static final Object PATTY = new Object();

    public static void main(String[] args) {
        Thread spongebob = new Thread(() -> {
            synchronized (BUN) {
                System.out.println("SpongeBob got the bun, waiting for patty...");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
                synchronized (PATTY) {
                    System.out.println("SpongeBob assembled the Krabby Patty :)");
                }
            }
        });

        Thread squidward = new Thread(() -> {
            synchronized (PATTY) {
                System.out.println("Squidward got the patty, waiting for bun...");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
                synchronized (BUN) {
                    System.out.println("Squidward assembled the Krabby Patty :)");
                }
            }
        });

        spongebob.start();
        squidward.start();

    }

}

/*
output:
SpongeBob got the bun, waiting for patty...
Squidward got the patty, waiting for bun...
 */
