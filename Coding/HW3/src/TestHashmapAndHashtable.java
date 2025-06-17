import java.util.*;
public class TestHashmapAndHashtable {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put(null, "allowed");
        String v = map.get(null);
        System.out.println(v);

        Map<String,String> map2 = new Hashtable<>();
        //map2.put(null, "disallowed");
    }
}
