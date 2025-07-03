
import java.util.*;
import java.util.stream.*;

public class Q1LongestVowelStrings {
    public static void main(String[] args) {
        List<String> words = List.of("Apple", "orange", "banana", "umbrella", "Ice", "Echo", "Sky", "Ant");

        List<String> result = words.stream()
                .filter(Q1LongestVowelStrings::startsWithVowel)
                .sorted(Comparator.comparingInt(String::length).reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Top 3 longest strings starting with a vowel:");
        System.out.println(result);
    }

    private static boolean startsWithVowel(String word) {
        if (word == null || word.isEmpty()) return false;
        char first = Character.toLowerCase(word.charAt(0));
        return first == 'a' || first == 'e' || first == 'i' || first == 'o' || first == 'u';
    }
}
