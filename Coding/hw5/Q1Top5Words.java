import java.util.*;
import java.util.stream.*;

public class Q1Top5Words {
    public static void main(String[] args) {
        String paragraph = "Java is great. Java is versatile. JAVA streams are powerful. Streams make java concise.";

        List<String> result = Arrays.stream(paragraph.toLowerCase().split("\\W+"))
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
