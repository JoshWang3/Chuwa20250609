public class PassValue {
    public static void changeNumber(int x) {
        x = 100;
    }

    public static void main(String[] args) {
        int a = 10;
        changeNumber(a);
        System.out.println(a); // Output is 10
    }
}