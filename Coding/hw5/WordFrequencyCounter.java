import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyCounter {

    private static List<String> tokenizeParagraph(String paragraph) {
        List<String> words = new ArrayList<>();
        StringBuilder wordBuilder = new StringBuilder();

        for (char c : paragraph.toCharArray()) {
            if (Character.isLetter(c)) {
                wordBuilder.append(Character.toLowerCase(c));
            }
            else if (wordBuilder.length() > 0) {
                words.add(wordBuilder.toString());
                wordBuilder.setLength(0); // Reset for the next word
            }
        }
        if (wordBuilder.length() > 0) {
            words.add(wordBuilder.toString());
        }
        return words;
    }

    public static void main(String[] args) {
        String paragraph = "The quick brown fox jumps over the lazy dog. " +
                "The dog, being lazy, did not jump. " +
                "A quick reaction from the fox was expected, " +
                "but the dog was faster than the fox.";

        List<String> words = tokenizeParagraph(paragraph);


        List<String> top5Words = words.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("Top 5 most frequent words: " + top5Words);
    }
}
