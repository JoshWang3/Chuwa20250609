import java.util.*;
import java.util.stream.*;

public class WordFrequency {
    public static void main(String[] args) {
        String paragraph = "Java is great and Java stream is powerful and Stream API is great too";
        List<String> words = Arrays.asList(paragraph.toLowerCase().split("\\W+"));

        Map<String, Long> freqMap = words.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        List<Map.Entry<String, Long>> top5 = freqMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toList());

        System.out.println("Top 5 frequent words: " + top5);
    }
}
