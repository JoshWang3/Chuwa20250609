package Coding;
import java.util.*;
import java.util.stream.*;

public class LongestVowelStrings {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "orange", "banana", "umbrella", "ice", "egg", "pear", "owl");

        List<String> result = words.stream()
                .filter(s -> s.matches("(?i)^[aeiou].*")) // 开头是元音字母
                .sorted(Comparator.comparingInt(String::length).reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(result); // 输出示例：[umbrella, orange, apple]
    }

}
