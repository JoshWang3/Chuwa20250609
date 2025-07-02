import java.time.LocalDate;

public class Employee {
    // -------- Static Content --------
    private static int employeeCount = 0;

    static {
        System.out.println("Static block: Employee class loaded into memory.");
    }

    public static int getEmployeeCount() {
        return employeeCount;
    }

    public static boolean isValidSSN(String ssn) {
        return ssn != null && ssn.matches("\\d{3}-\\d{2}-\\d{4}");
    }


    private String name;
    private LocalDate dateOfBirth;
    private Department department;
    private String ssn;
    private Address address;

    public Employee(String name, LocalDate dateOfBirth, Department department, String ssn, Address address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.ssn = ssn;
        this.address = address;

        employeeCount++;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Department getDepartment() {
        return department;
    }

    public String getSsn() {
        return ssn;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Employee: " + name +
                "\nDOB: " + dateOfBirth +
                "\nDepartment: " + department +
                "\nSSN: " + ssn +
                "\nAddress: " + address;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Employee)) return false;
        Employee other = (Employee) obj;
        return name.equals(other.name) &&
                dateOfBirth.equals(other.dateOfBirth) &&
                department.equals(other.department) &&
                ssn.equals(other.ssn) &&
                address.equals(other.address);
    }
}

class Department {
    private String name;
    private String code;

    public Department(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return name + " (" + code + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Department)) return false;
        Department other = (Department) obj;
        return name.equals(other.name) && code.equals(other.code);
    }
}

class Address {
    private String street;
    private String city;
    private String state;
    private String zip;

    public Address(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " " + zip;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Address)) return false;
        Address other = (Address) obj;
        return street.equals(other.street) &&
                city.equals(other.city) &&
                state.equals(other.state) &&
                zip.equals(other.zip);
    }
}
