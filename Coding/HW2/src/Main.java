import java.time.LocalDate;
import java.lang.reflect.Field;

public class Main {
    public static void inspectObject(Object obj) {
        Class<?> clazz = obj.getClass();
        System.out.println("Inspecting object of type: " + clazz.getName());
        System.out.println("Object identityHashCode: " + System.identityHashCode(obj));  // JVM address proxy

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                System.out.println("  " + field.getName() + ": " + value +
                        " [identityHashCode: " + System.identityHashCode(value) + "]");
            } catch (IllegalAccessException e) {
                System.out.println("  " + field.getName() + ": access denied");
            }
        }
    }

    public static void main(String[] args) {
        Department dept = new Department("Engineering", "ENG01");
        Address addr = new Address("123 Main St", "Springfield", "IL", "62704");

        Employee emp1 = new Employee("Alice", LocalDate.of(2000, 1, 11), "123-45-6789", dept, addr);
        Employee emp2 = new Employee("Bob", LocalDate.of(1995, 2, 22), "987-65-4321", dept, addr);

        System.out.println("\n== Inspecting emp1 ==");
        inspectObject(emp1);
        System.out.println("\n== Inspecting emp2 ==");
        inspectObject(emp2);
    }
}