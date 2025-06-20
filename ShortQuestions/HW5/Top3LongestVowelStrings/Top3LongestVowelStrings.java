package Top3LongestVowelStrings;

import java.util.*;
import java.util.stream.*;


public class Top3LongestVowelStrings {

    // Method to find Vowel String
    private static boolean isVowel(String s) {
        if (s == null || s.length() == 0) return false;
        char firstChar = Character.toLowerCase(s.charAt(0));
        return firstChar == 'a' || firstChar == 'e' || firstChar == 'i' || firstChar == 'o' || firstChar == 'u';
    }

    public static void main(String[] args) {
        List<String> input = Arrays.asList("apple", "dog", "burger", "cat");

        // Stream API to get top 3 longest strings with vowel
        List<String> result = input.stream()
                .filter(Top3LongestVowelStrings::isVowel)
                .sorted(Comparator.comparingInt(String::length).reversed()) // naturally from short -> long, so need reversed
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(result);

    }
}

// output: [apple]