package Coding.hw5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TopWords {
    public static void main(String[] args) {
        String paragraph = "Technology continues to reshape our daily lives, offering new tools to communicate, learn, and solve complex problems. From smartphones to artificial intelligence, innovation is accelerating at an unprecedented pace. As we adapt to these changes, it's important to balance convenience with responsibility, ensuring that progress benefits everyone in society.";

        List<String> ans = Arrays.stream(paragraph.split(" "))
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(ans);
    }
}
