import java.util.HashMap;
import java.util.Hashtable;

public class HashMapVsHashTable {
    public static void main(String[] args) {
        // Example with HashMap
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "Apple");
        hashMap.put(2, "Banana");
        hashMap.put(null, "Cherry"); // Allows one null key
        hashMap.put(3, null);       // Allows multiple null values

        System.out.println("HashMap:");
        hashMap.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

        // Example with Hashtable
        Hashtable<Integer, String> hashTable = new Hashtable<>();
        hashTable.put(1, "Apple");
        hashTable.put(2, "Banana");
        // hashTable.put(null, "Cherry"); // Throws NullPointerException (null keys not allowed)
        // hashTable.put(3, null);       // Throws NullPointerException (null values not allowed)

        System.out.println("\nHashtable:");
        hashTable.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
    }
}
