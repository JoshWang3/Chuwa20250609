import java.util.*;


public class Question3 {

    public static void main(String[] args) throws InterruptedException {
        Map<Integer, String> table = new Hashtable<>();
        Map<Integer, String> map = new HashMap<>();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                table.put(i, "Value " + i);
                map.put(i, "Value " + i);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Hashtable size: " + table.size()); //Always 1000, thread safe
        System.out.println("Hashmap size: " + map.size()); //not always 1000, thread not safe
    }

    //
}
