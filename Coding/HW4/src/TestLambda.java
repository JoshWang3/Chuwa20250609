interface I{
    void print(int a,String b);
}
public class TestLambda {
    public static void main(String[] args) {
        I printer = (a,b)->{
            System.out.println("int:"+a);
            System.out.println("String:"+b);
        };
        printer.print(1,"hello");
    }
}
