package hw5;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Return top 5 words by frequency from a paragraph
public class WordFrequency {
    public static void main(String[] args) {
        String paragraph = "Sometimes there isn't a good answer. No matter how you try to rationalize the outcome, it doesn't make sense. And instead of an answer, you are simply left with a question. Why?";
        // get a words string array, split() return an array
        String[] wordsArray = paragraph.toLowerCase().replaceAll("[^a-zA-Z']", " ").split("\\s+");
        // get a word frequency counter map {no=1, sometimes=1, why=1...}
        Map<String, Long> wordCount = Arrays.stream(wordsArray)
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        // System.out.println(wordCount);

        List<String> filteredWords = wordCount.entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))  // sort by word frequency in descending order
                .limit(5)
                .map(Map.Entry::getKey)  // mapping the element to key to get the word
                .collect(Collectors.toList());
        filteredWords.forEach(System.out::println);
    }
}
