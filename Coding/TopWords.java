package Coding;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopWords {
    public static void main(String[] args) {
        String paragraph = "Java is great. Java is powerful. Streams make Java powerful and expressive. Java!";

        List<String> topWords = Arrays.stream(paragraph.toLowerCase().split("\\W+"))
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(topWords); // 示例输出：[java, is, powerful, make, streams]
    }

}
