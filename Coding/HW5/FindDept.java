import java.util.*;
import java.util.stream.Collectors;

class FindDept {
    
    public static void main(String[] args) {
        List<Department> departments = Arrays.asList(
            new Department("HR", Arrays.asList(new Employee("Alice", 90000), new Employee("Bob", 110000), new Employee("Eve", 95000))),
            new Department("Engineering", Arrays.asList(new Employee("Charlie", 1200000), new Employee("David", 130000), new Employee("Frank", 105000))),
            new Department("Sales", Arrays.asList(new Employee("Grace", 115000), new Employee("Heidi", 125000), new Employee("Ivan", 98000)))
        );
        // Find departments with average salary greater than 100000
        List<String> depts = departments.stream()
                                .filter(dept -> dept.getEmployees().stream() 
                                .mapToInt(Employee::getSalary)
                                .average()   
                                .orElse(0)> 100000) // Use mapToInt to get the average salary
                                .map(Department::getName) // Map to department names
                                .collect(Collectors.toList()); // Collect department names into a list
        System.out.println("Departments with average salary greater than 100000: " + depts);
    }
}
