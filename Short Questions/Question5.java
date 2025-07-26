public class Question5 {
    public static void main(String[] args) {
        String s = new String("Hello");
        s = null;  // Now "Hello" object is unreachable => Garbage Collector will clean it
    }
}
