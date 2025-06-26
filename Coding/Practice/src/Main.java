import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        Thread consumer = new Thread(() -> {
            try {warehouse.consume();} catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread producer = new Thread(() -> {
            try {
                Thread.sleep(2000);
                warehouse.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        consumer.start();
        producer.start();

    }
}
