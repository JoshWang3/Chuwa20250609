import java.util.*;
import java.util.stream.Collectors;

public class Top3LongestString {
    public static void main(String[] args) {
        String[] strings = {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape"};
        
        List<String> str = Arrays.asList(strings);
        List<String> top3Longest = str.stream().filter(s -> s.matches("[aeiouAEIOU].*"))    // Filter strings starting with a vowel
                                       .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length())) // Sort by length in descending order
                                       .limit(3) // Limit to top 3 longest strings
                                       .collect(Collectors.toList()); // Collect to a list
        System.out.println("Top 3 longest strings starting with a vowel: " + top3Longest);
    }    
}
