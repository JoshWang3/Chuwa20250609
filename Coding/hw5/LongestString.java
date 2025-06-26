package hw5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Find top 3 longest strings that start with a vowel
public class LongestString {
    public static void main(String[] args) {
        // create an arraylist of words to be processed
        List<String> words = Arrays.asList("student", "video", "painting", "passenger", "honey", "hair", "coffee", "apple", "email", "over");

        List<String> filteredWords = words.stream()  // start a stream pipeline
                .filter(w -> w.matches("(?i)^[aeiou].*"))  // regex to filter the words that start with vowel(case-insensitive)
                .sorted((w1, w2) -> w2.length() - w1.length())  // sort by word length in descending order
                .limit(3)  // keep the top 3 longest words
                .collect(Collectors.toList());  // collect the stream object into list
        // print by using method reference
        filteredWords.forEach(System.out::println);
        // filteredWords.forEach(word -> System.out.println(word));
    }
}
