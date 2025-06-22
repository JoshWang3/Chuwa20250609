import java.util.*;
import java.util.stream.*;

// 1.1. find top 3 longest strings that start with a vowel
public class TopThreeLongestStrings {
    public static void main(String[] args) {
        List<String> dogBreeds = Arrays.asList("Bulldog", "poodle", "Chihuahua",
                "Airedale Terrier", "Afghan Hound", "Maltese");
        List<String> filteredNames = dogBreeds.stream()
                .filter(breeds -> startsWithVowel(breeds)) // First char start with a vowel
                .sorted((a,b) -> b.length() - (a.length())) // Sorted from longest to shortest
                .limit(3)
                .collect(Collectors.toList()); // Collect to a list
        System.out.println("1. Top 3 longest strings that start with a vowel: " + filteredNames);
    }
    private static boolean startsWithVowel(String word) {
        if (word == null || word.isEmpty()) return false;
        char firstc = Character.toLowerCase(word.charAt(0));
        return firstc == 'a' || firstc == 'e' || firstc == 'i' || firstc == 'o' || firstc == 'u';
    }
}
