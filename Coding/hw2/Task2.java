package hw2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;

public class Task2 {
    public static void run() throws IllegalAccessException {
        Department devDept = new Department(0614, "Building A");
        Address address1 = new Address("123 Main St", "San Jose", "CA", "95123");
        Address address2 = new Address("456 Maple Ave", "Sunnyvale", "CA", "94086");

        Employee e1 = new Employee("Alice", LocalDate.of(1990, 5, 20), devDept, "123-45-6789", address1);
        Employee e2 = new Employee("Bob", LocalDate.of(1985, 3, 14), devDept, "987-65-4321", address2);
        Class e1Class = e1.getClass();
        Class e2Class = e2.getClass();
        Method[]e1m = e1Class.getDeclaredMethods();
        Method[] e2m = e2Class.getDeclaredMethods();
        Field[] e1f = e1Class.getDeclaredFields();
        Field[] e2f = e2Class.getDeclaredFields();
        System.out.println("Employee 1: " + e1);
        System.out.println("Employee 2: " + e2);

        for(int i=0;i<e1m.length;i++){
            System.out.println("e1m["+i+"]: "+e1m[i] + " e2m["+i+"]: "+e2m[i]);
            System.out.println(e1m[i].equals(e2m[i]));
            System.out.println(e1m[i] == e2m[i]);
        }

        for(int i=0;i<e1f.length;i++){
            System.out.println(e1f[i] + " " + e2f[i]);
            System.out.println("e1f[i].hashCode(): "+ e1f[i].hashCode()+" e2f[i].hashCode(): "+ e2f[i].hashCode());
        }
        System.out.println("\n== JVM Memory Simulation ==");
        System.out.println("e1 reference hash: " + e1.hashCode() + "e1 class hash" + e1.getClass().hashCode());
        System.out.println("e2 reference hash: " + e2.hashCode() + "e3 class hash" + e2.getClass().hashCode());

        //static method
        System.out.println(Employee.staticMethod());
    }

    public static void main(String[] args) throws IllegalAccessException {
        run();
    }
}