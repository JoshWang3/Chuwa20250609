package lambda;

@FunctionalInterface
public interface StringTransformer {
    String transform(String input);
}
