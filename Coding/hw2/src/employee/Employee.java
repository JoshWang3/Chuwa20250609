package employee;

import java.util.Date;

public class Employee {
    private static int numOfEmployee = 0;

    private String firstName;

    private String lastName;

    public Date dateOfBirth;

    private Department department;

    private String ssnNumber;

    private Address address;

    public Employee(String firstName, String lastName, Date dateOfBirth, Department department, String ssnNumber, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.ssnNumber = ssnNumber;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Department getDepartment() {
        return department;
    }

    public String getSsnNumber() {
        return ssnNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setSsnNumber(String ssnNumber) {
        this.ssnNumber = ssnNumber;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static void addEmployee() {
        numOfEmployee ++;
        System.out.println(numOfEmployee);
    }

    @Override
    public String toString() {
        return "fistName" + firstName + "lastName" + lastName + "dateOfBirth" + dateOfBirth.toString()
                + "department" + department.getName() + "ssnName" + ssnNumber + "address" + address.getStreetName()
                + address.getCity() + address.getState() + address.getZipCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Employee other = (Employee) obj;
        return other.getFirstName().equals(firstName) && other.getLastName().equals(lastName)
                && other.getDateOfBirth().equals(dateOfBirth)
                && other.getDepartment().getName().equals(department.getName())
                && other.getSsnNumber().equals(ssnNumber)
                && other.address.getStreetName().equals(address.getStreetName());

    }
}
