import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// POJO for Employee
class Employee {
    private String name;
    private String department;
    private double salary;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "name='" + name + '\'' + ", department='" + department + '\'' + ", salary=" + salary + '}';
    }
}


public class HighSalaryDepartments {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", "Engineering", 110000));
        employees.add(new Employee("Bob", "Engineering", 125000));
        employees.add(new Employee("Charlie", "HR", 80000));
        employees.add(new Employee("David", "Sales", 95000));
        employees.add(new Employee("Eve", "Sales", 105000));
        employees.add(new Employee("Frank", "Engineering", 130000));
        employees.add(new Employee("Grace", "HR", 75000));
        employees.add(new Employee("Heidi", "Sales", 115000));

        List<String> highPayingDepartments = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 100000)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()); /

        System.out.println("Departments with average salary > 100,000: " + highPayingDepartments);
    }
}
