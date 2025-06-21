import java.util.*;
import java.util.stream.*;

// 1.2. return names of departments where avg salary > 100,000
public class DepartmentsWithHighPay {
    public static void main(String[] args) {
        // 构造数据，多个 Department以及员工salary
        List<Department> departments = Arrays.asList(
                new Department("Engineering", Arrays.asList(
                        new Employee("Ayo", 150000), new Employee("Bee", 170000), new Employee("Coco", 190000))),
                new Department("HR", Arrays.asList(
                        new Employee("David", 80000), new Employee("Emma", 100000))),
                new Department("Marketing", Arrays.asList(
                    new Employee("Fiona", 80000), new Employee("Grace", 150000)))
        );
        // use streamAPI， 处理List<Department>
        List<String> highSalaryDepts = departments.stream()
                .filter(dept -> dept.getAvgSalary() > 100000)
                .map(d -> d.getName())
                .collect(Collectors.toList());

        System.out.println("Departments names where employee avg salary > 100,000: " + highSalaryDepts);
    }
}

class Employee {
    private String employeeName;
    private int salary;
    // 构造函数
    public Employee(String employeeName, int salary) {
        this.employeeName = employeeName;
        this.salary = salary;
    }
    //use getter method
    public String getEmployeeName() {
        return employeeName;
    }
    public int getSalary() {
        return salary;
    }
}

class Department {
    private String name;
    private List<Employee> employees;
    public Department(String name, List<Employee> employees) {
        this.name = name; //dept name (hr/marketing/..)
        this.employees = employees;
    }
    public String getName() {
        return name;
    }
    public double getAvgSalary() {
        // 处理List<Employee> employees
        return employees.stream()
                .mapToInt(e -> e.getSalary()) // .mapToInt(Employee::getSalary)
                .average() //// 求平均值 → 返回 OptionalDouble
                .orElse(0);
    }
}