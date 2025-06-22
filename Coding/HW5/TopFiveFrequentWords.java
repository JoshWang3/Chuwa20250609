import java.util.*;
import java.util.stream.*;

// 4. return the top 5 words by frequency from a paragraph
public class TopFiveFrequentWords {
    public static void main(String[] args) {

        String paragraph = "I do not think sir you have a right to command me merely " +
                "because you are older than I or because you have seen more of the world " +
                "than I have your claim to superiority depends on the use you have made " +
                "of your time and experience.";

        //"\\W+"表示“所有非字母数字的符号”（比如空格、标点等）
       List<Map.Entry<String, Long>> topWords = Arrays.stream(paragraph.toLowerCase().split("\\W+"))

                .filter(w -> !w.isEmpty())
                //Collectors.groupingBy(...)：按w本身分组； Map<String, Long>，比如：{"the": 4, "you": 2, ...}
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                //把 Map变stream
                .entrySet().stream()
                //a.getValue() 是单词频率（Long 类型）。
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(5)
                .collect(Collectors.toList());
                //找到w本身，通过map
//                .map(entry -> entry.getKey())
//                .collect(Collectors.toList());

        System.out.println("Frequent Top 5 words: " + topWords);
    }
}