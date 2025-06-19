package hw2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;

public class JvmMemory {
    public static void main(String[] args) {
        // JVM Memory Allocation
        // Method Area(Metaspace since Java8): store class-level metadata and related information, like fields, method, interfaces..
        // Heap: store objects

        // when we "new" an object(instance), the object will be stored in heap
        Department department1 = new Department(1, "Tech");
        Department department2 = new Department(1, "Human Resources");

        Address address1 = new Address("123 Main St", "San Jose", "CA", "95133", "US");
        Address address2 = new Address("456 Oak Ave", "Milpitas", "CA", "95035", "US");

        Employee e1 = new Employee("John", LocalDate.of(1990, 5, 15), department1, "123-45-6789", address1);
        Employee e2 = new Employee("Emma", LocalDate.of(1985, 8, 22), department2, "987-65-4321", address2);

        // To get Class objects, which should be stored in Method Area
        Class e1Class = e1.getClass();
        Class e2Class = e2.getClass();
        // use Java Reflection utilities to get Method and Field objects
        Method[] e1m = e1Class.getDeclaredMethods(); // a new Method wrapper objects
        Method[] e2m = e2Class.getDeclaredMethods(); // another different new Method objects
        Field[] e1f = e1Class.getDeclaredFields();
        Field[] e2f = e2Class.getDeclaredFields();

        System.out.println("Demo Method Area: class information");
        // for every method in e1 and e2 Class (Employee), print their information
        System.out.println("--- Methods ---");
        for(int i = 0; i < e1m.length; i++){
            // output: e1m[0]: public java.lang.String hw2.Employee.getName() e2m[0]: public java.lang.String hw2.Employee.getName()
            // They shared the same method signatures
            System.out.println("e1m["+i+"]: "+e1m[i] + " e2m["+i+"]: "+e2m[i]);
            // output: e1m[i].hashCode(): 127498328 e2m[i].hashCode(): 127498328
            // Both the method objects have the same hash values, which means they are from the same class that shared the same memory location in the Method Area
            System.out.println("e1m[i].hashCode(): "+ e1m[i].hashCode()+" e2m[i].hashCode(): "+ e2m[i].hashCode());
            // output: true
            // because they have the same logical identity, represent the same actual methods
            System.out.println(e1m[i].equals(e2m[i]));
            // output: false
            // because e1m[i] and e2m[i] point different wrapper objects(get via reflection) in Heap
            System.out.println(e1m[i] == e2m[i]);
        }
        // like the methods above, fields are also class level information
        System.out.println("--- Fields ---");
        for(int i = 0; i < e1f.length; i++){
            System.out.println("e1f["+i+"]: "+e1f[i] + " e2f["+i+"]: " + e2f[i]);
            System.out.println("e1f[i].hashCode(): "+ e1f[i].hashCode()+" e2f[i].hashCode(): "+ e2f[i].hashCode());
        }

        System.out.println("Demo Heap: Object");
        // output: e1 object hash: -213548185 e1 class hash: 1160460865
        //         e2 object hash: 374016006 e2 class hash: 1160460865
        // e1 and e2 are different objects stored in the Heap, and their hash value is not the same, so they are in different memory locations
        System.out.println("e1 object hash: " + e1.hashCode() + " e1 class hash: " + e1.getClass().hashCode());
        System.out.println("e2 object hash: " + e2.hashCode() + " e2 class hash: " + e2.getClass().hashCode());

    }
}
