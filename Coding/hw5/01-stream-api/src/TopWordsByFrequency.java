import java.util.*;
import java.util.stream.Collectors;

public class TopWordsByFrequency {
    public static void main(String[] args) {
        String paragraph = """
        Two roads diverged in a yellow wood,
        And sorry I could not travel both
        And be one traveler, long I stood
        And looked down one as far as I could
        To where it bent in the undergrowth;
        """;

        // Clean and split paragraph into words
        List<String> words = Arrays.stream(paragraph
                        .toLowerCase()                         // convert to lowercase
                        .replaceAll("[^a-z\\s]", "")           // remove punctuation
                        .split("\\s+"))                        // split by whitespace
                .collect(Collectors.toList());

        // Count frequency of each word and sort by frequency descending
        List<Map.Entry<String, Long>> topWords = words.stream()
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))  // sort by frequency desc
                .limit(5)                                                    // top 5
                .collect(Collectors.toList());

        // Print the top 5 words and their counts
        topWords.forEach(entry ->
                System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
