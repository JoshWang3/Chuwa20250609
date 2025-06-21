package Coding.hw2;

import java.lang.reflect.Field;

public class EmployeeDemo {
    public static void main(String[] args) {
        Employee e1 = new Employee("a", "1-1", "cs", "123", "abc");
        Employee e2 = new Employee("b", "2-1", "cs", "345", "bcd");

        System.out.println(e1.equals(e2));
        System.out.println(e1.hashCode() == e2.hashCode());
        System.out.println(e1.hashCode());
        System.out.println(e2.hashCode());

        // Each time a new employee is created JVM allocate space for the object,
        // and they are assigned to different addresses in the memory

        for (Field field : Employee.class.getDeclaredFields()) {
            System.out.println(field.getName() + ", " + field.getType() + ", " + field.isAccessible());
        }

        System.out.println(Employee.count());

        // Static method can be called without instantiating the class,
        // can call class.method() directly

        int a = 5;
        modify(a);
        System.out.println(a);

        System.out.println(e1.name);
        e1.name = "m";
        System.out.println(e1.name);

        // Employees are created by passing values to the constructor method.
        // Java always passes references to the objects or values to methods.
        // Some people think it's pass-by-reference because you can modify the object fields,
        // but you cannot change the original object or primitive variables.
    }

    private static void modify(int a) {
        a = 10;
        // a does not change
    }

    private int modify(int n, int m) {
        return n+m;
        // This method has two inputs compared to 1 from the above method,
        // but they have the same method name.
        // Method signature = method name + parameters
    }
}
