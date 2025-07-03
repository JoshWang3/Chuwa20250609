package com.example.stream;

import java.util.*;
import java.util.stream.*;

public class Q4TopWordsByFrequency {
    public static void main(String[] args) {
        String paragraph = "Java stream API is powerful. Stream API helps in data processing. Java Java stream.";

        List<String> topWords = Arrays.stream(paragraph.toLowerCase().split("\\W+"))
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("Top 5 words by frequency:");
        System.out.println(topWords);
    }
}
