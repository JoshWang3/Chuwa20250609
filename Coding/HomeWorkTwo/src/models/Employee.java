package models;

public class Employee {
    private int id;
    private String firstname;
    private String lastname;
    private String dateOfBirth;
    private Department department;
    private String socialSecurityNumber;
    private Address address;

    public static long MAX_SALARY = 192000;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    public Department getDepartment() {
        return department;
    }
    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "name: " + firstname + " " + lastname + ", dateOfBirth: " + dateOfBirth + ", department: " + department.toString() + ", socialSecurityNumber: " + socialSecurityNumber + ", address: " + address.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null ||  getClass() != obj.getClass()) return false;

        Employee employee = (Employee) obj;
        return this.id == employee.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.id);
    }
}
