// Main.java
import java.time.LocalDate;
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws Exception {
        Department dept = new Department("Engineering");
        Address addr1 = new Address("123 Main St", "A", "12345");
        Address addr2 = new Address("222 Main Ave", "B", "67890");

        Employee emp1 = new Employee("aEmpy", LocalDate.of(2000, 1, 1), dept, "87654321", addr1);
        Employee emp2 = new Employee("bEmpy", LocalDate.of(1990, 2, 2), dept, "12345678", addr2);

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
