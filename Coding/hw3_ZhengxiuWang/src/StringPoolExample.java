public class StringPoolExample {
        /*
    - "hello" goes to pool
    - new String() creates new object
    - use equals() for content compare
    */
    public static void main(String[] args) {
        // from string pool
        String s1 = "hello";
        String s2 = "hello";

        // from heap (new object)
        String s3 = new String("hello");

        // compare by reference
        System.out.println(s1 == s2);  // true → same object from pool
        System.out.println(s1 == s3);  // false → different objects
        System.out.println(s1.equals(s3)); // true → same content
    }
}
