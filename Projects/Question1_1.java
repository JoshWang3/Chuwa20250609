import java.util.*;

public class Question1_1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("Apple", "orange", "banana", "umbrella", "elephant", "ice", "eagle", null));


        System.out.println(top3LongestVowelWords(list));

    }

    public static List<String> top3LongestVowelWords(List<String> list) {

        return list.stream()
                .filter(Objects::nonNull) //filter null values
                .filter(str -> str.matches("(?i)^[aeiou].*")) // 使用正则表达式来检查整体匹配
                .sorted((a,b) -> b.length() - a.length()) //负数则把 a 排序在 b 前面
                .limit(3)
                .toList();
    }
}

