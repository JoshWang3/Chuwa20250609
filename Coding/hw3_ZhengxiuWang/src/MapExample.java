import java.util.HashMap;
import java.util.Hashtable;

public class MapExample {
    public static void main(String[] args) {
        // HashMap: allows null key/value
        HashMap<String, Integer> gradeMap = new HashMap<>();
        gradeMap.put("Alice", 90);
        gradeMap.put("Bob", 85);
        gradeMap.put(null, 100); // ok
        gradeMap.put("Charlie", null); // ok

        System.out.println("HashMap grades:");
        for (String name : gradeMap.keySet()) {
            System.out.println(name + ": " + gradeMap.get(name));
        }

        System.out.println();

        // Hashtable: no null key/value allowed
        Hashtable<String, Integer> gradeTable = new Hashtable<>();
        gradeTable.put("Alice", 90);
        gradeTable.put("Bob", 85);
        // gradeTable.put(null, 100); not allowed
        // gradeTable.put("Charlie", null); not allowed

        System.out.println("Hashtable grades:");
        for (String name : gradeTable.keySet()) {
            System.out.println(name + ": " + gradeTable.get(name));
        }
    }
}
