import java.util.*;
import java.util.stream.Collectors;

public class Top3Vowels {

    private static final Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
    public static void main(String[] args) {
        //List<String> strings = new ArrayList<>(Arrays.asList());
        List<String> strings = Arrays.asList("sky", "apple", "eye", "orange", "gym", "tree", "fly", "input", "crwth", "house");

        List<String> top3StartWithVowels = strings.stream()
                .filter(word -> vowels.contains(Character.toLowerCase(word.charAt(0))))
                        .sorted((a, b) -> b.length() - a.length())
                        .limit(3)
                        .collect(Collectors.toList());

        System.out.println(top3StartWithVowels);
    }
}
