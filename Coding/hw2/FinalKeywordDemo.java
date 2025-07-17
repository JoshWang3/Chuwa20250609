public class FinalKeywordDemo {

    // Final variable - cannot be reassigned
    public static final int MAX_USERS = 100;
    // MAX_USERS = 200; Compile error — cannot reassign

    // Final object reference - cannot point to another object, but object is mutable
    public static void finalReferenceExample() {
        final StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World"); // Allowed
        // sb = new StringBuilder("New"); // Compile error
        System.out.println("Final reference object content: " + sb.toString());
    }

    // Final method - cannot be overridden
    static class A {
        public final void show() {
            System.out.println("This is a final method in class A.");
        }
    }

    static class B extends A {
        // public void show() {} // Compile error
    }

    // Final class - cannot be subclassed
    final static class PaymentProcessor {
        public void process() {
            System.out.println("Processing payment...");
        }
    }

    // Uncommenting below will cause a compile error
    // static class CustomProcessor extends PaymentProcessor {}

    public static void main(String[] args) {
        System.out.println("Final variable MAX_USERS = " + MAX_USERS);

        finalReferenceExample();

        A obj = new A();
        obj.show();

        PaymentProcessor processor = new PaymentProcessor();
        processor.process();
    }
}
