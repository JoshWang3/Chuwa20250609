import java.util.*;
import java.util.stream.*;

public class VowelStringFilter {

    // Store only lowercase vowels
    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');

    public static void main(String[] args) {
        List<String> words = Arrays.asList(
                "Apple", "orange", "banana", "Umbrella", "Eagle", "igloo", "car", "owl", "ink", "airplane"
        );

        List<String> top3LongestVowelWords = words.stream()
                .filter(word -> !word.isEmpty() && isVowel(word.charAt(0)))
                .sorted((a, b) -> b.length() - a.length())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Top 3 longest vowel-starting words: " + top3LongestVowelWords);
    }

    // Convert input to lowercase before checking
    private static boolean isVowel(char ch) {
        return VOWELS.contains(Character.toLowerCase(ch));
    }
}