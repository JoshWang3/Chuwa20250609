import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        map.put("apple", 1);
        map.put("banana", 2);

        // Thread-safe read/write
        System.out.println(map.get("apple"));

        map.putIfAbsent("banana", 3);  // won't overwrite
        System.out.println(map.get("banana"));  // still 2
    }
}
