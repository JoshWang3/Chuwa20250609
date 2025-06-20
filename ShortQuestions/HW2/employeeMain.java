import java.lang.reflect.Field;
import java.time.LocalDate;


public class employeeMain {

    // Static helper function for reflection
    public static void printObjectFields(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        System.out.println("Inspecting: " + clazz.getSimpleName());
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);
            System.out.println("  " + field.getName() + " = " + value);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // Access static variable
        System.out.println("Employee count before instantiation: " + Employee.getEmployeeCount());

        Department department1 = new Department(123, "Ministry of Magic");
        Department department2 = new Department(456, "Detective");
        Department department3 = new Department(789, "Krusty Krab");

        Address address1 = new Address("Underground of Whitehall and the HM Treasury building", "Westminster", "London", "SW1A 2HQ");
        Address address2 = new Address("221b Baker St.", "Marylebone", "London", "NW1 6XE");
        Address address3 = new Address("Krusty Krab", "Bikini Bottom", "Pacific Ocean", "00000");

        Employee employee1 = new Employee(
                "Harry Potter",
                "123-45-6789",
                LocalDate.of(1980, 7, 31),
                department1,
                address1
        );

        Employee employee2 = new Employee(
                "Sherlock Holmes",
                "987-65-4321",
                LocalDate.of(1854, 1, 6),
                department2,
                address2
        );

        Employee employee3 = new Employee(
                "SpongeBob SquarePants",
                "000-00-0001",
                LocalDate.of(1986, 7, 14),
                department3,
                address3
        );

        // Static content is SHARED, not per-instance
        // from 0 -> 3
        System.out.println("Employee count after instantiation: " + Employee.getEmployeeCount());

        // valid but bad, should call class instead of instance:
        // System.out.println("Employee count before instantiation: " + employee1.getEmployeeCount());


        // Call static method - no Employee object needed
        Employee.printCompanyPolicy();
        // Access instance method - requires an object
        System.out.println("Employee1's name: " + employee1.getName());

        // CANNOT work: System.out.println(Employee.getName());
        // should call with object not class


        System.out.println(employee1.toString());
        System.out.println(employee2.toString());
        System.out.println(employee3.toString());

        try {
            printObjectFields(employee1);
            printObjectFields(employee2);
            printObjectFields(employee3);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}

/*
2. How these Employee objects are allocated to JVM memory

 Stack:
   department  ---->  reference to Department object on heap
   address     ---->  reference to Address object on heap
   employee    ---->  reference to Employee object on heap

 Heap:
   Department object (fields: id, name)
   Address object (fields: street, district, city, postcode)
   Employee object (fields: name, ssn, dateOfBirth, department, address)
   (Employee contains references to Department and Address objects)


Stack                        Heap
---------------------------------------------------------
employee1 ----+-------> Employee object (Harry Potter)
                            |
                            +-- department1 ----> Department object (Ministry of Magic)
                            +-- address1    ----> Address object (Underground of Whitehall and the HM Treasury building, Westminster, London, SW1A 2HQ)

employee2 ----+-------> Employee object (Sherlock Holmes)
                            |
                            +-- department2 ----> Department object (Detective)
                            +-- address2    ----> Address object (221b Baker St., Marylebone, London, NW1 6XE)

employee3 ----+-------> Employee object (SpongeBob SquarePants)
                            |
                            +-- department3 ----> Department object (Krusty Krab)
                            +-- address3    ----> Address object (Krusty Krab, Bikini Bottom, Pacific Ocean, 00000)

Summary:
	•	new keyword = allocates an object on the heap
	•	Variable name (employee1) = reference stored on the stack
	•	Each Employee also holds references to its own Department and Address objects (which are also on the heap).

 */


/*
3. How Static Differs from Instance:

	Static fields/methods belong to the class itself, not to any one object.
	•	Shared across all instances.
	•	Don’t need to create an object to use
	Example: Employee.getEmployeeCount() or Employee.printCompanyPolicy()

	Instance fields/methods belong to individual objects.
	•	Each object has its own values.
	•	Must access them via the object
	Example: employee1.getName()
 */