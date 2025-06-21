package Coding.hw5;

import java.util.*;
import java.util.stream.Collectors;

public class LongestVowelStrings {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "abc", "bca", "cba", "aaa", "ab");

        List<String> ans = list.stream()
                .filter(c -> c.matches("(?i)^[aeiou].*"))
                .sorted(Comparator.comparingInt(String::length).reversed())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(ans);
    }
}