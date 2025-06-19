public class MethodSignatureDemo {
    /*
- method name: same
- parameter: different
- this is overloading
*/
    public static void main(String[] args) {
        printInfo("Alice");
        printInfo("Bob", 20);
        printInfo(101);
    }

    // method 1
    public static void printInfo(String name) {
        System.out.println("Name: " + name);
    }

    // method 2 (overload)
    public static void printInfo(String name, int age) {
        System.out.println("Name: " + name + ", Age: " + age);
    }

    // method 3 (overload)
    public static void printInfo(int userId) {
        System.out.println("User ID: " + userId);
    }
}
