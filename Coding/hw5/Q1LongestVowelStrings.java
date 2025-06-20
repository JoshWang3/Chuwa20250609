import java.util.*;
import java.util.stream.*;

public class Q1LongestVowelStrings {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "orange", "umbrella", "ice", "elephant", "grape");

        List<String> result = words.stream()
                .filter(s -> s.matches("(?i)^[aeiou].*"))
                .sorted(Comparator.comparingInt(String::length).reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
