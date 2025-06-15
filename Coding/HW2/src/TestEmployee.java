import java.time.LocalDate;
import java.lang.reflect.Method;
public class TestEmployee {
    public static void main(String[] args) throws Exception {
        Employ.Department dept = new Employ.Department("Engineering");
        Employ.Address addr = new Employ.Address(
                "123 Main St", "City", "CA", "90001"
        );
        Object emp1 = new Employ.Employee(
                "Alice",
                LocalDate.of(1990, 1, 1),
                dept,
                "123-45-6789",
                addr
        );
        System.out.println("Employ Count:"+Employ.Employee.getEmployeeCount());
        Object emp2 = new Employ.Employee(
                "Bob",
                LocalDate.of(1990, 2, 2),
                dept,
                "987-65-4321",
                addr
        );
        System.out.println(emp1);
        System.out.println("Employ Count:"+Employ.Employee.getEmployeeCount());
        System.out.println("Only one instance of static content per class.");

        Class<?> clazz1 = emp1.getClass();
        Class<?> clazz2 = emp2.getClass();
        System.out.println("HashCode of class");
        System.out.println(clazz1.hashCode());
        System.out.println(clazz2.hashCode()); //same

        System.out.println("HashCode of object");
        System.out.println(emp1.hashCode());
        System.out.println(emp2.hashCode()); //different

        System.out.println("HashCode of method");
        System.out.println(clazz1.getDeclaredMethods()[0].hashCode());
        System.out.println(clazz2.getDeclaredMethods()[0].hashCode()); //same
    }
}
