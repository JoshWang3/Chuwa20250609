public class OverloadingDemo {
    // Same method name, different parameters
    public void print() {
        System.out.println("No parameters");
    }

    public void print(String msg) {
        System.out.println("String: " + msg);
    }

    public void print(int num) {
        System.out.println("Integer: " + num);
    }

    public void print(String msg, int num) {
        System.out.println("String + int: " + msg + ", " + num);
    }

    public void print(int num, String msg) {
        System.out.println("int + String: " + num + ", " + msg);
    }

    // This will cause a compile error — only return type is different
    // public int print(int num) { return num; } ❌

    public static void main(String[] args) {
        OverloadingDemo demo = new OverloadingDemo();

        demo.print();
        demo.print("Hello");
        demo.print(42);
        demo.print("Test", 100);
        demo.print(100, "Test");
    }
}

