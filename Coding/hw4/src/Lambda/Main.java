package Lambda;


public class Main {
    public static void main(String[] args) {
        Operation add = (a, b) -> a + b;

        Operation subtract = (a, b) -> a - b;

        Operation multiply = (a, b) -> a * b;

        Operation divide = (a, b) -> a / b;

        System.out.println(add.operate(2,3));
        System.out.println(subtract.operate(2,3));
        System.out.println(multiply.operate(2, 3));
        System.out.println(divide.operate(2, 3));

    }
}
