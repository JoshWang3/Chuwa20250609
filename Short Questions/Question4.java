import java.util.*;

public class Question4 {
    public static void main(String[] args){

    String s1 = "hello";
    String s2 = "hello";
    //Both s1 and s2 are pointing to the same memory address in the String pool.
        System.out.println(s1 == s2);
    }
}
