package org.hw5;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

//1.4. Return top 5 words by frequency from a paragraph
public class WordFrequencyAnalyzer {

    public static List<Map.Entry<String, Long>> getTop5WordsByFrequency(String paragraph) {
        return Arrays.stream(paragraph.toLowerCase()
                        .replaceAll("[^a-zA-Z\\s]", "") // Remove punctuation
                        .split("\\s+")) // Split by whitespace
                .filter(word -> !word.isEmpty()) // Filter empty strings
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting())) // Count frequency
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) // Sort by frequency desc
                .limit(5) // Take top 5
                .collect(Collectors.toList());
    }




    public static void main(String[] args) {
        String paragraph = "The quick brown fox jumps over the lazy dog. " +
                "The dog was lazy but the fox was quick and brown. " +
                "Quick brown animals are amazing to watch.";

        List<Map.Entry<String, Long>> topWordsWithCount = getTop5WordsByFrequency(paragraph);
        System.out.println("Top 5 words with frequency:");
        topWordsWithCount.forEach(entry ->
                System.out.println(entry.getKey() + " -> " + entry.getValue()));

        System.out.println("\n" + "=".repeat(30) + "\n");



    }
}