package hw5;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Return top 5 words by frequency from a paragraph
 */
public class Top5FrequentWords {
    public static void main(String[] args) {
        String demoParagraph = "Earlier this year, Dries Buytaert announced the Starshot Initiative, a bold vision for the future of Drupal. This initiative aims to enhance the ‘Drupal CMS’ by including a set of useful and common modules, offering an improved site-building experience for ambitious site builders.\n" +
                "A key part of this initiative is the development of the Experience Builder, a new layout engine that will revolutionise the way pages are built with layouts and Paragraphs.\n" +
                "As a Certified Drupal Supplier and a supporter of the Drupal Association, Morpht recognises the significance of the Starshot initiative. We've pledged financial support to the DA to help advance these groundbreaking developments, which we believe will have a lasting impact on the Drupal community.";

        List<String> top5FrequentWords = Arrays.stream(demoParagraph.toLowerCase().split("\\W+"))
                .filter(word -> !word.isBlank())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .toList();

        System.out.println("Top 5 frequent words: " + top5FrequentWords);
        // Top 5 frequent words: [the, a, of, drupal, initiative]
    }

}
