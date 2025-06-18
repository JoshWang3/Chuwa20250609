public class LambdaDemo {
    public static void main(String[] args) {
        // lambda for addition
        Calculator add = (a, b) -> a + b;

        int result = add.compute(5, 3);  // 5 + 3 = 8
        System.out.println("Result: " + result);
    }
}

// functional interface
interface Calculator {
    int compute(int x, int y);
}
