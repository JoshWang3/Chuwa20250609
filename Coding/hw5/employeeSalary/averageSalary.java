package hw5.employeeSalary;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class averageSalary {
    public static void main(String[] args) {
        Employee employee1 = new Employee("Alice", new Date(2000, 11, 21), Departments.DEPARTMENT_MAP.get("HR"), "245012941", new Address("201 Tree Street", "90007"), 100000);
        Employee employee2 = new Employee("Alex", new Date(2000, 11, 21), Departments.DEPARTMENT_MAP.get("Engineering"), "1034951928", new Address("1103 Harvard Avenue", "92619"), 110000);
        Employee employee3 = new Employee("John", new Date(2000, 11, 21), Departments.DEPARTMENT_MAP.get("Marketing"), "3124532128", new Address("4501 Sunset Boulevard", "10484"), 70000);
        Employee employee4 = new Employee("Frank", new Date(2000, 11, 21), Departments.DEPARTMENT_MAP.get("Engineering"), "39412351928", new Address("29 Lake Drive", "23019"), 150000);

        List<Employee> employees = Arrays.asList(employee1, employee2, employee3, employee4);
        String topDepartment = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(entry -> entry.getKey().getName()).orElse(null);
        System.out.println(topDepartment);
    }
}
