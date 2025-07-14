
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Top5WordsByFrequency {

    
    public static void main(String[] args) {
        String text = "I taught her to lie before she could spell her name. Told her people were soft, stupid, and always looking for a story that made them feel good about parting with money. Back then, our narrative was the plain truth: an amputee raising his kid on his own. Before long, we noticed how different people responded to different aspects of our lives. Our narrative then developed into a collection of short stories we kept at the ready. I curated those tales, and she delivered them with big eyes and borrowed grief. ";

        List<String> words = Arrays.stream(text.toLowerCase().split("\\W+"))
                .collect(Collectors.groupingBy(word -> word, Collectors.counting())).entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println("Top 5 words by frequency: " + words);
    }
}
