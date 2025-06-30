public class LambdaDemo {
    public static void main(String[] args) {
        AddCalculate addCal = (a, b) -> System.out.println("Sum: " + (a + b));
        addCal.add(5, 10);
    }
}
