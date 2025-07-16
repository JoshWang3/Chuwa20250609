package Top5Words;

import java.util.*;
import java.util.stream.*;

public class Top5Words {
    public static void main(String[] args) {
        String paragraph = "One sunny day in Bikini Bottom, SpongeBob SquarePants and his best friend, Patrick Star, decided to go jellyfishing. They grabbed their nets and headed to Jellyfish Fields, excited for a fun day of catching jellyfish.\n" +
                "\n" +
                "“Ready, Patrick?” SpongeBob asked, his eyes sparkling with joy.\n" +
                "\n" +
                "“Ready! Let’s catch the biggest jellyfish ever!” Patrick replied, bouncing with excitement.\n" +
                "\n" +
                "They ran through the fields, their nets swishing in the air. They laughed and shouted as they chased colorful jellyfish, their giggles echoing through the underwater landscape. SpongeBob caught a big, purple jellyfish and cheered, “Look, Patrick! I got one!”\n" +
                "\n" +
                "“Wow! That’s so cool!” Patrick said, clapping his hands. “Let’s catch more!”\n" +
                "\n" +
                "The two friends spent hours catching jellyfish, jumping around, and playing tag. They watched as jellyfish danced in the water, their tentacles waving like ribbons. After a long day of fun, SpongeBob and Patrick decided to head back to the Krusty Krab for some delicious Krabby Patties.\n" +
                "\n" +
                "“I can’t wait to eat!” SpongeBob said, his tummy rumbling." +
                "“Me too! I’m super hungry!” Patrick agreed.\n" +
                "\n" +
                "As they walked back, they chatted about their favorite jellyfish and how they would tell stories to their friends. But when they reached the Krusty Krab, something strange was happening. The doors were locked, and there was no one inside." +
                "“Where is everyone?” SpongeBob wondered, scratching his head.\n" +
                "\n" +
                "Suddenly, they spotted Mr. Krabs looking worried. “SpongeBob! Patrick! You won’t believe what happened! Plankton stole the secret Krabby Patty recipe!”" +
                "“Oh no!” SpongeBob exclaimed. “We have to get it back!”";

        // split paragraph into words (lowercase, remove punctuation)
        String[] words = paragraph.toLowerCase().replaceAll("[^a-z]", " ").split("\\s+");

        // count frequency
        Map<String, Long> wordCounts = Arrays.stream(words)
                .filter(w -> !w.isEmpty()) // removes any empty strings
                .collect(Collectors.groupingBy(w -> w, Collectors.counting())); // groups the words in the stream by the word itself 

        // sort by frequency get top 5
        List<String> top5words = wordCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())) // sort from high freq -> low freq
                .limit(5) // top 5
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(top5words);
    }
}