
class DefaultStaticDemo implements MyInterface {
    public static void main(String[] args) {
        MyInterface.staticMethod(); // Call static method directly

        DefaultStaticDemo demo = new DefaultStaticDemo();
        demo.defaultMethod(); // Call default method from instance
    }
}