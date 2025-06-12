import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) {

        // Directly use the method (because this method is static)
        System.out.println("Age Calculation (using static methods");
        System.out.println("Age = " + Employee.calculateAge("Feb 29, 1976"));
        System.out.println("Age = " + Employee.calculateAge("Jan 12, 1928"));

        // Have to instantiate the class to access its methods / variables
        Employee e1 = new Employee("Bob", "Feb 15, 1998", "Training", "123-456-7890", "chuwa");
        Employee e2 = new Employee("Jack", "Feb 23, 1997", "Marketing", "987-654-3210", "chuwa");

        System.out.println("\nObject Creation");
        System.out.println("   e1: " + e1);
        System.out.println("   e2: " + e2);
        System.out.println("   Different objects: " + (e1 != e2));

        System.out.println("\nJava Reflection");
        Class<?> empClass = e1.getClass();
        System.out.println("   Class name: " + empClass.getSimpleName());

        Field[] fields = empClass.getDeclaredFields();
        System.out.println("   Fields (" + fields.length + "):");
        for (Field field : fields) {
            System.out.println("     - " + field.getName() + " (" + field.getType().getSimpleName() + ")");
        }

        Method[] methods = empClass.getDeclaredMethods();
        System.out.println("   Methods (" + methods.length + "):");
        for (Method method : methods) {
            System.out.println("     - " + method.getName() + "()");
        }
    }
}
