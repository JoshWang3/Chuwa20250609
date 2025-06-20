package com.chuwa.assignment.utils;

import java.util.List;
import java.util.stream.Collectors;

public class TopThreeLongestStrings {
    // Find top 3 longest string that start with a vowel
    private static final List<String> WORDS = List.of(
            "apple", "banana", "elephant", "apricot",
            "angela", "umbrellas", "amusementpark");

    public static void main(String[] args) {
        List<String> resList = WORDS.stream()
                .filter(TopThreeLongestStrings::startsWithVowel)
                .sorted((a, b) -> b.length() - a.length())  // Sorted by the length in descending order
                .limit(3)
                .collect(Collectors.toList());  // collect to a list
        System.out.println(resList);
    }
    private static boolean startsWithVowel(String s) {
        if (s == null || s.isEmpty()) return false;
        char c = Character.toLowerCase(s.charAt(0));
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
