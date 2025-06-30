import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Top5Words {
    private final static String paragraph = "Java is a powerful programming language. " +
            "Java is used in backend, frontend,and mobile development. " +
            "Learning Java can open many opportunities, and Java is widely adopted.";

    public static void main(String[] args) {
        String[] words = paragraph.replaceAll("[^a-z\\s]", "").toLowerCase().split("\\s+");

        Map<String, Long> wordCount = Arrays.stream(words)
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        List<Map.Entry<String, Long>> top5Words = wordCount.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(5)
                .collect(Collectors.toList());

        System.out.println(top5Words);
    }
}
