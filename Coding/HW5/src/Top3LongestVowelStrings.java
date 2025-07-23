import java.util.*;
import java.util.stream.Collectors;

public class Top3LongestVowelStrings {
    public static List<String> top3LongestStartingWithVowel(List<String> strings)  {
        return strings.stream()
                .filter(s -> s.matches("(?i)^[aeiou].*"))
                .sorted((a, b) -> Integer.compare(b.length(), a.length()))
                .limit(3)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> input = List.of("apple", "orange", "banana", "umbrella", "elephant", "owl", "ink");
        System.out.println(top3LongestStartingWithVowel(input));
    }
}
