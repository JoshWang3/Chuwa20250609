package hw2;

import hw2.Models.Address;
import hw2.Models.Departments;
import hw2.Models.Employee;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void inspect(Object obj) throws IllegalAccessException {
        Class<?> c = obj.getClass();
        System.out.println("Class: " + c.getName());
        System.out.println("Object identity (heap reference): " + System.identityHashCode(obj));

        // field metadata
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // to bypass private access
            Object value = field.get(obj);
            System.out.println("  Field: " + field.getName() + ", Value: " + value +
                    ", Type: " + field.getType().getSimpleName());
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Employee employee1 = new Employee("Alice", new Date(2000, 11, 21), Departments.DEPARTMENT_MAP.get("HR"), "245012941", new Address("201 Tree Street", "90007"));
        Employee employee2 = new Employee("Alex", new Date(2000, 11, 21), Departments.DEPARTMENT_MAP.get("Engineering"), "1034951928", new Address("1103 Harvard Avenue", "92619"));

        try {
            inspect(employee1);
        }
        catch (IllegalAccessException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        };

        try {
            inspect(employee2);
        }
        catch (IllegalAccessException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        };
    }
}