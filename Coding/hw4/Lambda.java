public class Lambda {
    @FunctionalInterface
    interface Operation {
        // Provide exactly one abstract method
        int operate(int a, int b);
    }

    public static void main(String[] args) {
        // Provides specific implementation on initialization with lambda
        Operation addition = (int a, int b) -> a+b;
        System.out.println("Result: " + addition.add(1, 2)); // Result: 3 -> (1+2)

        // A different implementation
        Operation mult = (int a, int b) -> a*b;
        System.out.println("Result: " + mult.operate(2, 3)); // Result: 6 -> (2*3)
    }
}