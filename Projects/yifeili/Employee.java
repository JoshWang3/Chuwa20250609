package yifeili;

import java.time.LocalDate;
import java.util.*;

public class Employee{
    private String name;
    private LocalDate dob;
    private Department department;//need create a department class later
    private String ssn;
    private Address address;//anothe POJO
    private static int employCount = 0;

    public Employee(String name, LocalDate dob, Department department, String ssn, Address address){
        this.name = name;
        this.dob = dob;
        this.department = department;
        this.ssn = ssn;
        this.address = address;

        employCount++;
    }

    public String getName() { return name; }
    public LocalDate getDob() { return dob; }
    public Department getDepartment() { return department; }
    public String getSsn() { return ssn; }
    public Address getAddress() { return address; }
    public static int getEmployCount() { return employCount; }

    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dob=" + dob +
                ", department=" + department +
                ", ssn='" + ssn + '\'' +
                ", address=" + address +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee that = (Employee) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(dob, that.dob) &&
                Objects.equals(department, that.department) &&
                Objects.equals(ssn, that.ssn) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dob, department, ssn, address);
    }



}

