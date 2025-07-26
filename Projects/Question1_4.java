import java.util.*;
import java.util.stream.Collectors;

public class Question1_4 {
    public static void main(String[] args) {

        String paragraph = "Last week, after much outrage at summer camps and non-profits like the Boys and Girls Club that were facing immediate impacts of the frozen funds, the Office of Management and Budget decided to release the hold on $1.3 billion of the nearly $6 billion in funding that goes to 21st Century Community Learning Centers (21st CCLC) – affecting summer camp and after-school programs.";

        System.out.println(topFiveFreqWords(paragraph));
    }

    public static List<String> topFiveFreqWords(String paragraphs) {

        return Arrays.stream(paragraphs.toLowerCase().split("\\W+"))//Split words by non-words
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet().stream()
                .sorted((a,b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .toList();

    }
}


