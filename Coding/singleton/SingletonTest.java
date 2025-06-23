package singleton;

public class SingletonTest {
    public static void main(String[] args) {
        // singleton.EagerLoadSingleton Test
        EagerLoadSingleton obj1 = EagerLoadSingleton.getInstance();
        EagerLoadSingleton obj2 = EagerLoadSingleton.getInstance();
        LazyLoadSingleton obj3 = LazyLoadSingleton.getInstance();
        LazyLoadSingleton obj4 = LazyLoadSingleton.getInstance();

        // Print their hashCodes to verify they are the same instance
        System.out.println("obj1 hashCode: " + obj1.hashCode());
        System.out.println("obj2 hashCode: " + obj2.hashCode());
        System.out.println("obj3 hashCode: " + obj3.hashCode());
        System.out.println("obj4 hashCode: " + obj4.hashCode());

        // Check reference equality
        if (obj1 == obj2) {
            System.out.println("singleton confirmed: All instances are the same.");
        } else {
            System.out.println("singleton broken: Instances are different.");
        }

        if (obj3 == obj4) {
            System.out.println("singleton confirmed: All instances are the same.");
        } else {
            System.out.println("singleton broken: Instances are different.");
        }
    }
}
