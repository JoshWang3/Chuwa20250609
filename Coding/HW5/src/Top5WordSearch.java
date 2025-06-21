import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Top5WordSearch {
    static class Solution{
        public List<String> topNWords(String paragraph, int n){
            return Arrays.stream(
                    paragraph.toLowerCase()
                            .replaceAll("[^a-z0-9]+", " ")
                            .split("\\s+")
            )
                    .filter(word->!word.isEmpty())
                    .collect(Collectors.groupingBy(
                            Function.identity(),
                            Collectors.counting()
                    ))
                    .entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                    .limit(n)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

        }


    }
    public static void main(String[] args) {
        String paragraph="GNU is an operating system that is free software—that is, it respects users' freedom."
                + " The GNU operating system consists of GNU packages"
                +" (programs specifically released by the GNU Project)"
                + "as well as free software released by third parties. "
                + "The development of GNU made it possible to use a computer without software that would trample your freedom.";
        System.out.println(new Solution().topNWords(paragraph, 5));
    }
}
