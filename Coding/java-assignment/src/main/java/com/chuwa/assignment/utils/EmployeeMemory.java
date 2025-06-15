package com.chuwa.assignment.utils;

import com.chuwa.assignment.pojos.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Demonstrates where different pieces of data live in the JVM:
 *   • Metaspace: class structure & method definition & static variable (shared once)
 *   • Stack    : local reference variables (e0, e1, e)
 *   • Heap     : each new Employee object (actual object content)
 */
public class EmployeeMemory {

    public static void main(String[] args) {

        /* ── Stack references, Heap objects ───────────────────── */
        Employee e0 = new Employee(0, "Angela",  new Date(),
                new Department("Engineering"), "111-22-3333",
                new Address("1 Main St", "Phoenix", "AZ"));

        Employee e1 = new Employee(1, "Ja",      new Date(),
                new Department("Finance"),    "444-55-6666",
                new Address("42 First Ave", "Austin", "TX"));

        /* ── Metaspace proof: same Method[] for every instance ─── */
        Method[] m0 = e0.getClass().getDeclaredMethods();
        Method[] m1 = e1.getClass().getDeclaredMethods();

        System.out.println("\n--- Shared Method objects in Metaspace ---");
        for (int i = 0; i < m0.length; i++) {
            System.out.printf("m0[%d].hash=%d | m1[%d].hash=%d | equals? %b%n",
                    i, m0[i].hashCode(), i, m1[i].hashCode(), m0[i].equals(m1[i]));
        }

        /* ── Heap proof: every new Employee has a unique address ─ */
        System.out.println("\n--- Five more Employee heap objects ---");
        for (int i = 0; i < 5; i++) {
            Employee e = new Employee(i, "User" + i, new Date(),
                    new Department("Dept" + i), "000-00-000" + i,
                    new Address(i + " Rd", "City" + i, "ST"));

            System.out.printf("Stack ref e%d -> Heap object:", i);
            System.out.println();
            System.out.println("    identityHash = " + System.identityHashCode(e));
            System.out.println("    class hash   = " + e.getClass().hashCode());
            System.out.println("    contents     = " + e);           // calls Employee.toString()
        }
    }
}
