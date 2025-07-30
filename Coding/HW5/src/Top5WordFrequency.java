import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Top5WordFrequency {
    public static List<String> top5WordsByFrequency(String paragraph) {
        return Arrays.stream(paragraph.toLowerCase().split("\\W+"))
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String paragraph = "Java is great. Java is robust. Java is used for enterprise. Java Java Java!";
        System.out.println(top5WordsByFrequency(paragraph));
    }
}
