import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {
        Address address1 = new Address("123 Main St", "New York", "NY", "10001");
        Address address2 = new Address("456 Oak Ave", "Los Angeles", "CA", "90210");

        Employee employee1 = new Employee("John Smith", "1990-05-15", "Engineering", "123-45-6789", address1);
        Employee employee2 = new Employee("Sarah Johnson", "1985-12-03", "Marketing", "987-65-4321", address2);

        System.out.println("=== OBJECT INSTANCES ===");
        System.out.println("Employee 1: " + employee1);
        System.out.println("Employee 2: " + employee2);


        System.out.println("\n=== JVM MEMORY ALLOCATION ANALYSIS ===");
        // 1. Object Identity Hash Codes (memory addresses)
        System.out.println("1. Object Identity Hash Codes (indicates memory location):");
        System.out.println("employee1 hash: " + System.identityHashCode(employee1));
        System.out.println("employee2 hash: " + System.identityHashCode(employee2));

        // 2. Class information in Method Area
        System.out.println("\n2. Class Information (stored in Method Area):");
        Class<?> empClass = employee1.getClass();
        System.out.println("Employee class: " + empClass.getName());
        System.out.println("Employee class hash: " + System.identityHashCode(empClass));

        // 3. Field analysis using reflection
        System.out.println("\n3. Field Analysis (instance data in Heap):");
        analyzeObjectFields(employee1, "employee1");
        analyzeObjectFields(employee2, "employee2");

        // 4. Reference vs Object demonstration
        System.out.println("\n4. Reference vs Object Allocation:");
        System.out.println("employee1 reference points to object at hash: " + System.identityHashCode(employee1));
        System.out.println("employee1.homeAddress reference points to object at hash: " + System.identityHashCode(employee1.getHomeAddress()));
        System.out.println("address1 reference points to same object? " + (employee1.getHomeAddress() == address1));

        // 5. String pool demonstration
        System.out.println("\n5. String Pool Analysis:");
        String dept1 = "Engineering";
        String dept2 = "Engineering";
        String dept3 = new String("Engineering");
        System.out.println("dept1 == dept2 (same reference in string pool): " + (dept1 == dept2));
        System.out.println("dept1 == dept3 (different objects): " + (dept1 == dept3));
        System.out.println("dept1 hash: " + System.identityHashCode(dept1));
        System.out.println("dept2 hash: " + System.identityHashCode(dept2));
        System.out.println("dept3 hash: " + System.identityHashCode(dept3));

        // 6. Memory layout summary
        System.out.println("\n6. Memory Layout Summary:");
        System.out.println("HEAP MEMORY:");
        System.out.println("- employee1 object instance data");
        System.out.println("- employee2 object instance data");
        System.out.println("- address1 object instance data");
        System.out.println("- address2 object instance data");
        System.out.println("- String objects (names, departments, etc.)");
        System.out.println("\nMETHOD AREA:");
        System.out.println("- Employee class bytecode and metadata");
        System.out.println("- Address class bytecode and metadata");
        System.out.println("- Method implementations");
        System.out.println("\nSTACK MEMORY:");
        System.out.println("- Local variables (employee1, employee2, address1, address2 references)");
        System.out.println("- Method call frames");


        System.out.println("\n=== STATIC VS INSTANCE ===");
        System.out.println("Accessing static content WITHOUT creating any instances:");
        System.out.println("Employee1's Name: " + employee1.getName());
        System.out.println("Employee2's Name: " + employee2.getName());
        System.out.println("Total Employees: " + Employee.getEmployeeCount());
        System.out.println();
    }

    private static void analyzeObjectFields(Object obj, String objName) {
        try {
            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();

            System.out.println(objName + " fields:");
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(obj);
                System.out.println("  " + field.getName() + " (" + field.getType().getSimpleName() + "): " +
                        value + " [hash: " + System.identityHashCode(value) + "]");
            }
        } catch (Exception e) {
            System.out.println("Error analyzing fields: " + e.getMessage());
        }
    }

}

