package Coding;

public class GarbageAndAccessDemo {
    // --------- 1. Demonstrate Garbage Collection ----------
    static class DemoObject {
        int id;

        DemoObject(int id) {
            this.id = id;
            System.out.println("Object " + id + " created.");
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Object " + id + " is garbage collected.");
        }
    }

    public static void garbageCollectionDemo() {
        DemoObject obj1 = new DemoObject(1);
        DemoObject obj2 = new DemoObject(2);

        // Dereference objects so they become eligible for garbage collection
        obj1 = null;
        obj2 = null;

        // Suggest garbage collection
        System.gc();

        // Pause to allow garbage collection to complete
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * 🔍 Garbage Collection in Java:
     * Java automatically removes unreachable (unreferenced) objects from memory.
     * Types of Garbage Collectors (JVM dependent):
     * 1. Serial GC - simple, single-threaded (good for small apps)
     * 2. Parallel GC - multiple threads for minor GC (default in many JVMs)
     * 3. CMS (Concurrent Mark Sweep) - low pause GC for better responsiveness (deprecated)
     * 4. G1 GC - balances throughput and latency, good for large heaps
     * 5. ZGC / Shenandoah - very low pause time, scalable for large memory apps
     */

    // --------- 2. Demonstrate Access Modifiers ----------

    public String publicVar = "Accessible Everywhere";
    protected String protectedVar = "Accessible in same package & subclasses";
    String defaultVar = "Accessible in same package only";
    private String privateVar = "Accessible in this class only";

    public void showAccess() {
        System.out.println("Inside class:");
        System.out.println(publicVar);
        System.out.println(protectedVar);
        System.out.println(defaultVar);
        System.out.println(privateVar);
    }

    /*
     * 🔐 Access Modifiers in Java:
     * - public: accessible from anywhere
     * - protected: accessible within the same package and subclasses
     * - (default): no modifier = package-private
     * - private: accessible only within the class
     */

    public static void main(String[] args) {
        System.out.println("=== Garbage Collection Demo ===");
        garbageCollectionDemo();

        System.out.println("\n=== Access Modifiers Demo ===");
        GarbageAndAccessDemo demo = new GarbageAndAccessDemo();
        demo.showAccess();
    }

}
