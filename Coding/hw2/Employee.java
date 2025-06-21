package Coding.hw2;

import java.util.Objects;

public class Employee {
    String name;
    private final String dob;
    private final String department;
    private final String ssn;
    private final String address;
    static int count = 0;

    public Employee(String name, String dob, String department, String ssn, String address) {
        this.name = name;
        this.dob = dob;
        this.department = department;
        this.ssn = ssn;
        this.address = address;
        count++;
    }

    public static int count() {
        return count;
    }

    @Override
    public String toString() {
        return "Employee: " + "/n" + "name: " + name + "/n" +
                "dob: " + dob + "/n" + "department: " + department + "/n" +
                "ssn: " + ssn + "/n" + "address: " +address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee e =  (Employee) o;
        return Objects.equals(name, e.name) &&
                Objects.equals(dob, e.dob) &&
                Objects.equals(department, e.department) &&
                Objects.equals(ssn, e.ssn) &&
                Objects.equals(address, e.address);
    }
}
