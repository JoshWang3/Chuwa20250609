### Codings Assignments

1.Write a Java POJO (plain old java object) named "Employee", inside Employ class, y

```
import java.util.Date;
import java.util.Objects;

public class Employee {
    private String name;
    private Date dateOfBirth;
    private Department department;
    private String ssn;
    private Address address;

    public Employee(String name, Date dateOfBirth, Department department, String ssn, Address address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.ssn = ssn;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + '\'' +
                ", DOB=" + dateOfBirth +
                ", dept=" + department +
                ", ssn='" + ssn + '\'' +
                ", address=" + address + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee emp = (Employee) o;
        return Objects.equals(name, emp.name)
                && Objects.equals(dateOfBirth, emp.dateOfBirth)
                && Objects.equals(department, emp.department)
                && Objects.equals(ssn, emp.ssn)
                && Objects.equals(address, emp.address);
    }

    // Static utility for demo
    public static String COMPANY_NAME = "TechCorp";
    public static void printCompany() {
        System.out.println("Company: " + COMPANY_NAME);
    }
}

class Department {
    private String name;

    public Department(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Address {
    private String city, street;

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }

    @Override
    public String toString() {
        return city + ", " + street;
    }
}

```

```
public class Main {
    public static void main(String[] args) throws Exception {
        Department d1 = new Department("Engineering");
        Address a1 = new Address("New York", "5th Ave");

        Employee e1 = new Employee("Alice", new Date(), d1, "123-45-6789", a1);
        Employee e2 = new Employee("Alice", new Date(), d1, "123-45-6789", a1);

        System.out.println(e1);
        System.out.println(e1.equals(e2));

        // JVM memory check via reflection
        System.out.println("Class loaded: " + e1.getClass().getName());
    }
}

```

