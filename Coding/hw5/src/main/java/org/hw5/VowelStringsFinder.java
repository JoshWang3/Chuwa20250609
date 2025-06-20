package org.hw5;

import java.util.*;
import java.util.stream.Collectors;

//1.1. Find top 3 longest strings that start with a vowel
public class VowelStringsFinder {

    public static void main(String[] args) {
        // Sample data
        List<String> strings = Arrays.asList(
                "apple", "banana", "orange", "elephant", "cat", "dog",
                "umbrella", "independent", "beautiful", "algorithm",
                "extraordinary", "understanding", "programming", "java"
        );

        List<String> result = findTop3LongestVowelStrings(strings);

        System.out.println("Top 3 longest strings starting with vowels:");
        result.forEach(System.out::println);
    }

    /**
     * Finds the top 3 longest strings that start with a vowel
     * @param strings List of input strings
     * @return List of top 3 longest strings starting with vowels
     */
    public static List<String> findTop3LongestVowelStrings(List<String> strings) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

        return strings.stream()
                .filter(s -> s != null && !s.isEmpty()) // Filter out null/empty strings
                .filter(s -> vowels.contains(s.charAt(0))) // Filter strings starting with vowel
                .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length())) // Sort by length descending; Tiny overhead from object creation
                .limit(3) // Take top 3
                .collect(Collectors.toList()); // Collect to list
    }

    /**
     * Alternative implementation using Comparator.comparing()
     */

    public static List<String> findTop3LongestVowelStringsAlternative(List<String> strings) {
        return strings.stream()
                .filter(Objects::nonNull)
                .filter(s -> !s.isEmpty())
                .filter(VowelStringsFinder::startsWithVowel)
                .sorted(Comparator.comparing(String::length).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    /**
     * Helper method to check if string starts with vowel
     */
    private static boolean startsWithVowel(String s) {
        char firstChar = Character.toLowerCase(s.charAt(0));
        return firstChar == 'a' || firstChar == 'e' || firstChar == 'i' ||
                firstChar == 'o' || firstChar == 'u';
    }
}

////For multi-level sorting
//// Clean with Comparator
//.sorted(Comparator.comparing(String::length).reversed()
//        .thenComparing(String::toLowerCase))
//
//// Messy with Lambda
//        .sorted((s1, s2) -> {
//int lengthCompare = Integer.compare(s2.length(), s1.length());
//    if (lengthCompare != 0) return lengthCompare;
//    return s1.toLowerCase().compareTo(s2.toLowerCase());
//        })