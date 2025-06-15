import java.time.LocalDate;
import java.util.*;
public class Employ {
    public static class Employee {
        private static int employeeCount;
        static {
            System.out.println("Static initializer,only run once when creating class, before any constructor");
            employeeCount = 0;
        }
        public static int getEmployeeCount() {
            return employeeCount;
        }

        private String name;
        private LocalDate dateOfBirth;
        private Department department;
        private String socialSecurityNumber;
        private Address homeAddress;

        public Employee() {
            employeeCount++;
        }
        public Employee(String name, LocalDate dateOfBirth, Department department,
                        String socialSecurityNumber, Address homeAddress) {
            this.name = name;
            this.dateOfBirth = dateOfBirth;
            this.department = department;
            this.socialSecurityNumber = socialSecurityNumber;
            this.homeAddress = homeAddress;
            employeeCount++;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocalDate getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public Department getDepartment() {
            return department;
        }

        public void setDepartment(Department department) {
            this.department = department;
        }

        public String getSocialSecurityNumber() {
            return socialSecurityNumber;
        }

        public void setSocialSecurityNumber(String socialSecurityNumber) {
            this.socialSecurityNumber = socialSecurityNumber;
        }

        public Address getHomeAddress() {
            return homeAddress;
        }

        public void setHomeAddress(Address homeAddress) {
            this.homeAddress = homeAddress;
        }
        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", dateOfBirth=" + dateOfBirth +
                    ", department=" + department +
                    ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                    ", homeAddress=" + homeAddress +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Employee)) return false;
            Employee employee = (Employee) o;
            return Objects.equals(name, employee.name) &&
                    Objects.equals(dateOfBirth, employee.dateOfBirth) &&
                    Objects.equals(department, employee.department) &&
                    Objects.equals(socialSecurityNumber, employee.socialSecurityNumber) &&
                    Objects.equals(homeAddress, employee.homeAddress);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, dateOfBirth, department, socialSecurityNumber, homeAddress);
        }
    }

    public static class Department {
        private String name;

        public Department() {
        }

        public Department(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Department{name='" + name + "'}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Department)) return false;
            Department that = (Department) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    public static class Address {
        private String street;
        private String city;
        private String state;
        private String zipCode;

        public Address() {
        }

        public Address(String street, String city, String state, String zipCode) {
            this.street = street;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "street='" + street + '\'' +
                    ", city='" + city + '\'' +
                    ", state='" + state + '\'' +
                    ", zipCode='" + zipCode + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Address)) return false;
            Address address = (Address) o;
            return Objects.equals(street, address.street) &&
                    Objects.equals(city, address.city) &&
                    Objects.equals(state, address.state) &&
                    Objects.equals(zipCode, address.zipCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(street, city, state, zipCode);
        }
    }
}
