package hw5.vowel;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class vowel {
    public static List<String> randomStringHelper(Random random) {
        // string length between 1 and 20

        return IntStream.range(0, 20)
                .mapToObj(i -> random.ints('a', 'z' + 1)
                        .limit(random.nextInt(1, 21))  // string length between 1 and 20
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Random random = new Random();
        List<String> randomStrings = randomStringHelper(random);
        List<String> top3 = randomStrings.stream().filter(s->!s.isEmpty() && "AEIOUaeiou".indexOf(s.charAt(0)) != -1)
                .sorted((a, b) -> Integer.compare(b.length(), a.length()))
                .limit(3).collect(Collectors.toList());
        System.out.println(top3);
    }
}
