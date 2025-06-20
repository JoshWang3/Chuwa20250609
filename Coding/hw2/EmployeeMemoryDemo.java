import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;

public class EmployeeMemoryDemo {
    public static void main(String[] args) throws IllegalAccessException {
        // Create two different employee instances
        Employee emp1 = new Employee("Alice", LocalDate.of(1990, 1, 1),
                new Department("Engineering", "ENG"),
                "111-22-3333",
                new Address("123 A St", "Seattle", "WA", "98101"));

        Employee emp2 = new Employee("Bob", LocalDate.of(1985, 2, 2),
                new Department("Engineering", "ENG"),
                "222-33-4444",
                new Address("456 B St", "Boston", "MA", "02110"));

        // --- Class comparison (Metaspace) ---
        System.out.println("Class Comparison:");
        System.out.println("emp1.getClass() == emp2.getClass(): " + (emp1.getClass() == emp2.getClass()));
        System.out.println("emp1.getClass().hashCode(): " + emp1.getClass().hashCode());
        System.out.println("emp2.getClass().hashCode(): " + emp2.getClass().hashCode());

        // --- Object identity comparison (Heap) ---
        System.out.println("\nObject Comparison (Heap):");
        System.out.println("emp1 == emp2: " + (emp1 == emp2)); // false
        System.out.println("emp1.equals(emp2): " + emp1.equals(emp2)); // false
        System.out.println("System.identityHashCode(emp1): " + System.identityHashCode(emp1));
        System.out.println("System.identityHashCode(emp2): " + System.identityHashCode(emp2));

        // --- Variable reference (Stack) ---
        System.out.println("\nVariable Reference:");
        Employee alias = emp1;
        System.out.println("alias == emp1: " + (alias == emp1)); // true

        // --- Reflect methods (shared from Class) ---
        System.out.println("\n👉 Methods Comparison (Metaspace - Shared Class Info):");
        Method[] emp1Methods = emp1.getClass().getDeclaredMethods();
        Method[] emp2Methods = emp2.getClass().getDeclaredMethods();

        for (int i = 0; i < emp1Methods.length; i++) {
            Method m1 = emp1Methods[i];
            Method m2 = emp2Methods[i];

            System.out.printf("Method: %-25s | Equal (.equals): %-5s | Same (==): %-5s\n",
                    m1.getName(),
                    m1.equals(m2),
                    m1 == m2);
        }

        // --- Reflect field values (per-object from Heap) ---
        System.out.println("\nField Values Comparison (Heap):");
        Field[] fields = emp1.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object val1 = field.get(emp1);
            Object val2 = field.get(emp2);

            boolean isEqual = val1 != null && val1.equals(val2);
            boolean isSame  = val1 == val2;

            System.out.printf("Field: %-10s | emp1: %-25s | emp2: %-25s | Equal (.equals): %-5s | Same (==): %-5s\n",
                    field.getName(), val1, val2, isEqual, isSame);
        }

    }
}
