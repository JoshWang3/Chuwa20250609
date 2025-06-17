package Coding;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MapComparisonDemo {
    public static void main(String[] args) {
        // ---------- HashMap 示例 ----------
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("apple", "red");
        hashMap.put(null, "no key");           // 
        hashMap.put("banana", null);           // 

        System.out.println("HashMap:");
        for (String key : hashMap.keySet()) {
            System.out.println("Key: " + key + ", Value: " + hashMap.get(key));
        }

        // ---------- Hashtable 示例 ----------
        Map<String, String> hashTable = new Hashtable<>();
        hashTable.put("apple", "red");

        try {
            hashTable.put(null, "no key");     // 
        } catch (NullPointerException e) {
            System.out.println("\nHashtable does NOT allow null key.");
        }

        try {
            hashTable.put("banana", null);     // 
        } catch (NullPointerException e) {
            System.out.println("Hashtable does NOT allow null value.");
        }

        System.out.println("\nHashtable:");
        for (String key : hashTable.keySet()) {
            System.out.println("Key: " + key + ", Value: " + hashTable.get(key));
        }
    }

}

//HashMap is more tolerant and modern, allows null keys and values, is not thread-safe, and is more efficient

//Hashtable is safer but outdated, does not accept null, is thread-safe but slow