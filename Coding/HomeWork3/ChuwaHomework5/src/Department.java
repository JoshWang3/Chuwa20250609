import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Department {

    @JsonProperty("department")
    private String departmentName;
    private List<Employee> employees;


    public Department() {} // required by Jackson

    public Department(String name, List<Employee> employees) {
        this.departmentName = name;
        this.employees = employees;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
