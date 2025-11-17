package Coding;
import java.time.LocalDate;
import java.util.Objects;

public class Employee{
    private String name;
    private LocalDate dateOfBirth;
    private Department department;
    private String socialSecurityNumber;
    private Address homeAddress;

    // Static variable
    private static int  employeeCount = 0;
    

    // Static class
    static class Company{
        private String name;
        public Company(String brand){
            this.name = brand;
        }
        public String getCompanyName(){
            return name;
        }

        public int getEmployeeCount(){
            return employeeCount;
        }

    }

    // instance class
    class Task{
        private String name;
        public Task(String name){
            this.name = name;
        }
        public String getTaskName(){
            return name;
        }
        public String getEmployeeName(){
            return Employee.this.name;
        }
        public int getEmployeeCount(){
            return employeeCount;
        }
    }

    public Employee (String name, LocalDate dateOfBirth, Department department, String socialSecurityNumber, Address homeAddress){
        this.name=name;
        this.dateOfBirth = dateOfBirth;
        this.department=department;
        this.socialSecurityNumber=socialSecurityNumber;
        this.homeAddress=homeAddress;
        employeeCount++;
    }

    @Override
    public String toString(){
        return "Employee {" +
        "\n Name: " + name +
        ",\n Date of Birth: " + dateOfBirth +
        ",\n Department: " + department +
        ",\n SSN: " + socialSecurityNumber + 
        ",\n Address: " + homeAddress +"\n}";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && 
        Objects.equals(dateOfBirth, employee.dateOfBirth) &&
        Objects.equals(department, employee.department) &&
        Objects.equals(socialSecurityNumber, employee.socialSecurityNumber) &&
        Objects.equals(homeAddress, employee.homeAddress);

    }

    @Override
    public int hashCode(){
        return Objects.hash(name, dateOfBirth, department, socialSecurityNumber, homeAddress);
    }
}

class Department{
    private String department;
    public Department(String departmentName){
        this.department = departmentName;
    }
    @Override
    public String toString() {
        return department;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return Objects.equals(department, that.department);

    }

    @Override
    public int hashCode(){
        return Objects.hash(department);
    }
}

class Address{
    private String address;
    public Address(String addressName){
        this.address = addressName;
    }
    @Override
    public String toString() {
        return address;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address that = (Address) o;
        return Objects.equals(address, that.address);

    }

    @Override
    public int hashCode(){
        return Objects.hash(address);
    }
}