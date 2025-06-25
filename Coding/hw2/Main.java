package hw2;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Department department = new Department("Cloud Center", "321 11th St SE, Seattle, WA");
        HomeAddress homeAddress1 = new HomeAddress("2133 1th Ave W", "Seattle", "WA", "98101");
        HomeAddress homeAddress2 = new HomeAddress("456 Oak Ave", "Redmond", "WA", "98052");

        System.out.println(Employee.getEmployeeCount()); // call static method
        Employee employee1 = new Employee("Helen", LocalDate.of(1990,2,1), department,"123-45-6789", homeAddress1);
        System.out.println(Employee.getEmployeeCount());
        Employee employee2 = new Employee("Bob", LocalDate.of(1995, 3,12),department,"987-45-6789", homeAddress2);
        System.out.println(Employee.getEmployeeCount());

        System.out.println(employee1);
        System.out.println(employee2);
        System.out.println(employee1.equals(employee2));
        System.out.println("Employee count: " + Employee.getEmployeeCount());

        inspectObject(employee1);

    }

    public static void inspectObject(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        System.out.println("Inspecting object of class: " + clazz.getSimpleName());

        for(Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true); // allow access to private fields
            Object value = field.get(obj);
            System.out.printf("Field: %-15s | Type: %-20s | Value: %s%n",
                    field.getName(),
                    field.getType().getSimpleName(),
                    value);
        }
    }
}
