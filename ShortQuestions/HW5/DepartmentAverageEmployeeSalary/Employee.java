package DepartmentAverageEmployeeSalary;

public class Employee {
    private String name;
    private double salary;
    private Department department;

    public Employee(String name, double salary, Department department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }
    public Department getDepartment() { return department; }
}
