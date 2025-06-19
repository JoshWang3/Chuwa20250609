import java.util.function.BiFunction;

class Calculator{
    public static double calculate(double a, double b, BiFunction<Double,Double,Double> operation) {
        return operation.apply(a, b);
    }
    public static final BiFunction<Double,Double,Double> add=(a,b)->a+b;
    public static final BiFunction<Double,Double,Double> mul=(a,b)->a*b;
    public static final BiFunction<Double,Double,Double> sub=(a,b)->a-b;
    public static final BiFunction<Double,Double,Double> div=(a,b)->a/b;
}
public class TestCalculator {
    public static void main(String[] args) {
        double res=1.0;
        res=Calculator.calculate(res,1,Calculator.add);
        res=Calculator.calculate(res,4,Calculator.mul);
        res=Calculator.calculate(res,2,Calculator.sub);
        res=Calculator.calculate(res,3,Calculator.div);
        System.out.println(res);
    }
}
