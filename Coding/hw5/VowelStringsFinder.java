import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class VowelStringsFinder {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList(
            "Apple", "Banana", "Elephant", "Orange",
            "Umbrella", "Igloo", "Cat", "Dog",
            "Ostrich", "Antelope", "Eucalyptus"
        );

        System.out.println("Original List: " + strings);

        List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

        List<String> longestVowelStrings = strings.stream()
            .filter(s -> !s.isEmpty() && vowels.contains(s.charAt(0)))
            .sorted(Comparator.comparingInt(String::length).reversed())
            .limit(3)
            .collect(Collectors.toList());

        System.out.println("Top 3 longest strings starting with a vowel: " + longestVowelStrings);
    }
}
