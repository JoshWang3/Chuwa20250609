import java.util.*;
import java.util.stream.*;

public class LongestVowelStrings {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "orange", "strawberry", "umbrella", "grape", "avocado", "elephant");

        List<String> result = words.stream()
                .filter(w -> w.matches("(?i)^[aeiou].*")) // starts with vowel
                .sorted((a, b) -> b.length() - a.length()) // sort by length desc
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Top 3 longest vowel-starting words: " + result);
    }
}
