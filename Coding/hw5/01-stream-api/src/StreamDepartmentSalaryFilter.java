import java.util.*;
import java.util.stream.Collectors;

public class StreamDepartmentSalaryFilter {
    public static void main(String[] args) {
        // Sample data
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 120000, "Engineering"),
                new Employee("Bob", 90000, "Engineering"),
                new Employee("Charlie", 105000, "HR"),
                new Employee("David", 95000, "HR"),
                new Employee("Eva", 130000, "Finance"),
                new Employee("Frank", 115000, "Finance")
        );

        // Stream API to filter departments with avg salary > 100000
        List<String> result = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 100000)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("Departments with avg salary > 100,000: " + result);
    }
}
