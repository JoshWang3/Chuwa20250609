import java.time.LocalDate;
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws Exception {
        Department dept = new Department("Engineering");

        Employee emp1 = new Employee("aEmpy", LocalDate.of(2000, 1, 1), dept, "87654321", "123 Main St");
        Employee emp2 = new Employee("bEmpy", LocalDate.of(1990, 2, 2), dept,"12345678", "222 Main Ave");

        System.out.println("emp1 identityHashCode = " + System.identityHashCode(emp1));
        System.out.println("emp2 identityHashCode = " + System.identityHashCode(emp2));

        System.out.println("\n-- aEmpy Fields --");
        printFields(emp1);

        System.out.println("\n-- bEmpy Fields --");
        printFields(emp2);
    }

    private static void printFields(Object obj) throws IllegalAccessException {
        Class<?> cls = obj.getClass();
        for (Field f : cls.getDeclaredFields()) {
            f.setAccessible(true);
            System.out.printf("%-20s = %s%n", f.getName(), f.get(obj));
        }
    }
}
