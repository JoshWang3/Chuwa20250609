package Coding;

public class GCDemo {
    public static void main(String[] args) {
        GCDemo obj = new GCDemo();
        obj = null;
        System.gc(); // Request GC
    }
    @Override
    protected void finalize() {
        System.out.println("Object collected");
    }
}