package org.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeMemoryDemo {
    public static void main(String[] args) {

        // 1. Create Employee instances
        Employee emp1 = new Employee("John Doe", LocalDate.of(1990, 5, 15),
                "Engineering", "123-45-6789",
                "123 Main St, City, State 12345");

        Employee emp2 = new Employee("Jane Smith", LocalDate.of(1985, 8, 22),
                "Marketing", "987-65-4321",
                "456 Oak Ave, Town, State 67890");

        Employee emp3 = new Employee("John Doe", LocalDate.of(1990, 5, 15),
                "Engineering", "123-45-6789",
                "123 Main St, City, State 12345"); // Same data as emp1

        // 2. Demonstrate Method Area (Metaspace)

        Class<?> emp1Class = emp1.getClass();
        Class<?> emp2Class = emp2.getClass();

        System.out.println("   emp1.getClass() == emp2.getClass(): " + (emp1Class == emp2Class));
        System.out.println("   emp1.getClass().hashCode(): " + emp1Class.hashCode());
        System.out.println("   emp2.getClass().hashCode(): " + emp2Class.hashCode());
        System.out.println("   -> Both objects share the same Class object in Method Area\n");

        // Show methods are shared
        Method[] emp1Methods = emp1Class.getDeclaredMethods();
        Method[] emp2Methods = emp2Class.getDeclaredMethods();

        System.out.println("   Comparing first 3 methods:");
        for (int i = 0; i < Math.min(3, emp1Methods.length); i++) {
            System.out.println("   Method: " + emp1Methods[i].getName());
            System.out.println("     emp1 method hashCode: " + emp1Methods[i].hashCode());
            System.out.println("     emp2 method hashCode: " + emp2Methods[i].hashCode());
        }

        // 3. Demonstrate Heap Memory

        System.out.println("   Object Identity HashCodes (memory addresses):");
        System.out.println("   emp1.hashCode(): "+emp1.hashCode()+" emp1.getClass().hashCode(): "+emp1.getClass().hashCode());
        System.out.println("   emp2.hashCode(): "+emp2.hashCode()+" emp2.getClass().hashCode(): "+emp2.getClass().hashCode());
        System.out.println("   emp3.hashCode(): "+emp3.hashCode()+" emp3.getClass().hashCode(): "+emp3.getClass().hashCode());

        System.out.println("   Object equals() comparison:");
        System.out.println("   emp1.equals(emp2): " + emp1.equals(emp2));
        System.out.println("   emp1.equals(emp3): " + emp1.equals(emp3));
        System.out.println("   emp1 == emp3: " + (emp1 == emp3));
        System.out.println("   -> emp1 and emp3 are equal but different objects\n");

        // 4. Demonstrate String Pool

        String dept1 = "Engineering";
        String dept2 = "Engineering";
        String dept3 = new String("Engineering");

        System.out.println("   String literal comparison:");
        System.out.println("   dept1 == dept2: " + (dept1 == dept2) + " (same object in string pool)");
        System.out.println("   dept1 == dept3: " + (dept1 == dept3) + " (different objects)");
        System.out.println("   dept1.equals(dept3): " + dept1.equals(dept3) + " (same content)");

        // 5. Create multiple Employee objects to show heap allocation
        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Employee emp = new Employee("Employee" + i, LocalDate.now(),
                    "Dept" + i, "000-00-000" + i,
                    "Address " + i);
            employees.add(emp);
            System.out.println("   Created Employee " + i +
                    " - Object hashCode: " + System.identityHashCode(emp) +
                    ", Class hashCode: " + emp.getClass().hashCode());
        }
        System.out.println("   -> All objects have different memory addresses but share same Class\n");

        /*
        * Summary:
        * Method Area (or Metaspace in Java 8+):	Class-level data like class metadata, static variables, constant pool
        * Heap:	Instance objects (created with new)
        * Stack:	Method calls, local variables, and references
        * */

    }
}