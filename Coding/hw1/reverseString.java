// Reverse a String
public class reverseString {
    public static void main(String[] args) {
        String str = "abcdef";
        String reversed = new StringBuilder(str).reverse().toString();
        System.out.println(reversed);
    }
}