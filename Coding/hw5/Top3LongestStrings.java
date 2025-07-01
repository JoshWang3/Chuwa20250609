package hw5;

import java.util.Arrays;
import java.util.List;

/**
 *  Find top 3 longest strings that start with a vowel.
 */
public class Top3LongestStrings {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("apple", "orange", "banana", "Umbrella", "honey", "egg", "sun", "icecream", "ice", "owl", "grape", "lemon");

        List<String> top3LongestVowelStarts = input.stream()
                .filter(s -> s.matches("(?i)^[aeiou].*"))
                .sorted((a, b) -> Integer.compare(b.length(), a.length()))
                .limit(3)
                .toList();
        System.out.println(top3LongestVowelStarts);

    }
}
