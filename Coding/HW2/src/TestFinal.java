final class FinalClass{
    int data;
}
//Not allowed
//class SubClass extends FinalClass
public class TestFinal {
    public static void main(String[] args) {
        final int finalInt = 10;
        System.out.println(finalInt);
        //Not allowed
        //finalInt=20;
    }
}
