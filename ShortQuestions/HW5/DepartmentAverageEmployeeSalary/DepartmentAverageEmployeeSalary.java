package DepartmentAverageEmployeeSalary;
import java.util.*;
import java.util.stream.*;

public class DepartmentAverageEmployeeSalary {

    public static void main(String[] args) {
        Department fryCook = new Department("Fry Cook");
        Department janitorial = new Department("Janitorial");
        Department cashier = new Department("Cashier");
        Department management = new Department("Management");
        Department science = new Department("Science");
        Department education = new Department("Education");


        List<Employee> employees = Arrays.asList(
                new Employee("SpongeBob", 95000, fryCook),
                new Employee("Patrick", 80000, janitorial),
                new Employee("Squidward", 105000, cashier),
                new Employee("Mr. Krabs", 160000, management),
                new Employee("Sandy", 120000, science),
                new Employee("Plankton", 99000, science),
                new Employee("Pearl", 100500, management),
                new Employee("Mrs. Puff", 115000, education)
        );

        List<String> result = employees.stream()
                // group each employee by department and for each group calculate average salary
                .collect(Collectors.groupingBy(e -> e.getDepartment().getName(), Collectors.averagingDouble(Employee::getSalary)))
                .entrySet().stream() // convert Map's set of key-value pair into a new Stream
                .filter(entry -> entry.getValue() > 100000) // keep average salary > 100000
                .map(Map.Entry::getKey) // transforms each filters entry into its key
                .collect(Collectors.toList());

        System.out.println(result);
    }
}

// output: [Education, Science, Management, Cashier]
