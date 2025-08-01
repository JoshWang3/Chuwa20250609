package Coding;
import java.lang.reflect.Field;
import java.time.LocalDate;

public class EmployeeTest {
    public static void main(String[] args) throws IllegalAccessException {
        Department dept1 = new Department("Engineering");
        Department dept2 = new Department("HR");

        Address addr1 = new Address("123 Main St, San Francisco, CA, 90000");
        Address addr2 = new Address("456 Elm St, Manhattan, NY, 07000");

        Employee emp1 = new Employee(
                "Alice Smith",
                LocalDate.of(1990, 5, 20),
                dept1,
                "123-45-6789",
                addr1
        );

        Employee emp2 = new Employee(
                "Bob Johnson",
                LocalDate.of(1985, 8, 15),
                dept2,
                "987-65-4321",
                addr2
        );

        // Print out the employees
        System.out.println(emp1);
        System.out.println(emp2);

        // Use reflection to demonstrate allocation and internal fields
        inspectObject(emp1);
        inspectObject(emp2);

        Employee emp3 = new Employee(
                "Bob Johnson",
                LocalDate.of(1985, 8, 15),
                dept2,
                "987-65-4321",
                addr2
        );
        inspectObject(emp3);
        System.out.println("emp1.equals(emp2)?"+emp1.equals(emp2));
        System.out.println("emp2.equals(emp3)?"+emp2.equals(emp3));

        System.out.println("\n\nStatic Nested Class: Company:");
        Employee.Company company = new Employee.Company("Samsung");
        System.out.println("Company name: " + company.getCompanyName());
        System.out.println("\nStatic class access static variable of outer class:");
        System.out.println("Employee Count: " +  company.getEmployeeCount());

        System.out.println("\n\nNon-Static Nested Class (Inner Class):Task:");
        Employee.Task task = emp3.new Task("Testing");
        System.out.println("Task name: " + task.getTaskName());
        System.out.println("\nInstance class access instance variable of outer class:");
        System.out.println("Employee name: " 
        + task.getEmployeeName());
        System.out.println("Instance class access static variable of outer class:");
        System.out.println("Employee Count: " 
        + task.getEmployeeCount());

    }

    private static void inspectObject(Object obj) throws IllegalAccessException {
        System.out.println("\nInspecting object: " + obj.getClass().getSimpleName());
        System.out.println("HashCode (identityHashCode): " + System.identityHashCode(obj));
        System.out.println("hashCode(): " + obj.hashCode());

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println("Field name: " + field.getName() +
                               ", Type: " + field.getType().getSimpleName() +
                               ", Value: " + field.get(obj));
        }
    }
}
