import java.util.function.BiFunction;
import java.util.Scanner;
public class Question5 {
    public static void main(String[] args) {
        Caculator myCaculator = new Caculator();
        Scanner scan = new Scanner(System.in);

        do {
            double number;
            double number2;
            System.out.println("Enter the first number: ");
            if(scan.hasNextDouble()) {
                number = scan.nextDouble();
            } else {
                System.out.println("Invalid input");
                continue;
            }


            System.out.println("Enter the second number: ");
            if(scan.hasNextDouble()) {
                number2 = scan.nextDouble();
            } else {
                System.out.println("Invalid input");
                continue;
            }

            scan.nextLine();
            System.out.println("Enter the symbol of addition(+) or subtraction(-) or multiplication(*) or division(/) ");
            String symbol = scan.nextLine();


            switch (symbol) {
                case "+":
                    System.out.println(myCaculator.addition(number, number2));

                    break;
                case "-":
                    System.out.println(myCaculator.subtraction(number, number2));
                    break;
                case "*":
                    System.out.println(myCaculator.multiplication(number, number2));
                    break;
                case "/":
                    System.out.println(myCaculator.division(number, number2));
                    break;
                default:
                    System.out.println("Invalid symbol");
                    break;
            }

            System.out.println("Enter \" end\" to exit the program or enter any key to calculate another one");

        } while (!scan.nextLine().equals("end"));

    scan.close();

    }
}

class Caculator{
    public double addition(double a,double b){
        BiFunction<Double, Double, Double> addition = (x,y) -> x+y;

        return addition.apply(a, b);
    }

    public double subtraction(double a,double b){
        BiFunction<Double, Double, Double> subtraction = (x, y) -> x - y;
        return subtraction.apply(a,b);

    }

    public double multiplication(double a,double b){
        BiFunction<Double, Double, Double> multiplication = (x, y) -> x*y;
        return multiplication.apply(a,b);
    }

    public double division(double a,double b){
        BiFunction<Double, Double, Double> division = (x, y) -> x/y;
        return division.apply(a,b);
    }

}
