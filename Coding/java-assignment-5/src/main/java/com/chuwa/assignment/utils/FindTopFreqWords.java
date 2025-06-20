package com.chuwa.assignment.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindTopFreqWords {
    //Return top 5 words by frequency from a paragraph
    public static void main(String[] args) {
        String paragraph = "Every employee wants to feel valued. A good manager supports the employee's growth and recognizes performance. " +
                    "When an employee gets feedback, it helps improve performance and builds trust between the employee and manager. ";
        // Tokenise into words
        String[] words = paragraph.toLowerCase().split("\\W+");  // \\W+ = any non-word chars

        // Count frequencies
        Map<String, Long> wordFreq = Arrays.stream(words)
                .filter(word -> word.length() > 1)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Take top5 frequency
        List<String> topFreq = wordFreq.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .limit(5)
                .collect(Collectors.toList());

        System.out.println(topFreq);
    }
}
